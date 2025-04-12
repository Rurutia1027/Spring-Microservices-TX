package com.mini.payment.utils.httpclient;

import com.mini.payment.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HttpUtils {
    private static Logger LOG = LoggerFactory.getLogger(HttpUtils.class);
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String HTTP_METHOD_POST = "POST";
    public static final String HTTP_METHOD_GET = "GET";
    public static final int DEFAULT_READ_TIMEOUT = 20000;
    public static final int DEFAULT_CONNECT_TIMEOUT = 10000;
    public static final String HTTP_PREFIX = "http://";
    public static final String HTTPS_PREFIX = "https://";
    public static final int MAX_FETCH_SIZE = 5000;
    private static TrustManager[] trustAnyManagers =
            new TrustManager[]{new TrustAnyTrustManager()};

    // build HttpURLConnection and then send request
    public static String httpRequest(String url, Map<String, Object> params, String method,
                                     String charSet, Map<String, Object> headers) {
        HttpParam param = new HttpParam(url);
        if (Objects.nonNull(params) && params.size() > 0) {
            param.setParameters(params);
        }

        if (Objects.nonNull(headers) && headers.size() > 0) {
            param.setHeaders(headers);
        }
        param.setCharSet(charSet);
        param.setMethod(method);
        HttpResult result = httpRequest(param);

        if (Objects.isNull(result) || !result.isSuccess()) {
            return null;
        } else {
            return result.getContent();
        }
    }

    public static HttpResult httpRequest(HttpParam httpParam) {
        String url = httpParam.getUrl();
        Map<String, Object> parameters = httpParam.getParameters();
        String sMethod = httpParam.getMethod();
        String charSet = httpParam.getCharSet();
        boolean sslVerify = httpParam.isSslVerify();
        int maxResultSize = httpParam.getMaxResultSize();
        Map<String, Object> headers = httpParam.getHeaders();
        int readTimeout = httpParam.getReadTimeout();
        int connectTimeout = httpParam.getConnectTimeout();
        boolean ignoreContentIfUnsuccess = httpParam.isIgnoreContentIfNotSuccess();
        boolean hostnameVerify = httpParam.isHostnameVerify();
        TrustKeyStore trustKeyStore = httpParam.getTrustKeyStore();
        ClientKeyStore clientKeyStore = httpParam.getClientKeyStore();

        if (url == null || url.trim().length() == 0) {
            throw new IllegalArgumentException("invalid url : " + url);
        }
        if (maxResultSize <= 0) {
            throw new IllegalArgumentException("maxResultSize must be positive : " + maxResultSize);
        }
        Charset.forName(charSet);
        HttpURLConnection urlConn = null;
        URL destURL = null;

        String baseUrl = url.trim();
        if (!baseUrl.toLowerCase().startsWith(HTTPS_PREFIX) && !baseUrl.toLowerCase().startsWith(HTTP_PREFIX)) {
            baseUrl = HTTP_PREFIX + baseUrl;
        }

        String method = null;
        if (sMethod != null) {
            method = sMethod.toUpperCase();
        }
        if (method == null
                || !(method.equals(HTTP_METHOD_POST) || method
                .equals(HTTP_METHOD_GET))) {
            throw new IllegalArgumentException("invalid http method : "
                    + method);
        }

        int index = baseUrl.indexOf("?");
        if (index > 0) {
            baseUrl = urlEncode(baseUrl, charSet);
        } else if (index == 0) {
            throw new IllegalArgumentException("invalid url : " + url);
        }

        String queryString = mapToQueryString(parameters, charSet);
        String targetUrl = "";
        if (method.equals(HTTP_METHOD_POST)) {
            targetUrl = baseUrl;
        } else {
            if (index > 0) {
                targetUrl = baseUrl + "&" + queryString;
            } else {
                targetUrl = baseUrl + "?" + queryString;
            }
        }
        try {
            destURL = new URL(targetUrl);
            urlConn = (HttpURLConnection) destURL.openConnection();

            setSSLSocketFactory(urlConn, sslVerify, hostnameVerify, trustKeyStore, clientKeyStore);


            boolean hasContentType = false;
            boolean hasUserAgent = false;
            for (String key : headers.keySet()) {
                if ("Content-Type".equalsIgnoreCase(key)) {
                    hasContentType = true;
                }
                if ("user-agent".equalsIgnoreCase(key)) {
                    hasUserAgent = true;
                }
            }
            if (!hasContentType) {
                headers.put("Content-Type", "application/x-www-form-urlencoded; charset=" + charSet);
            }
            if (!hasUserAgent) {
                headers.put("user-agent", "PlatSystem");
            }

            if (headers != null && !headers.isEmpty()) {
                for (Map.Entry<String, Object> entry : headers.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    List<String> values = StringUtil.makeStringList(value);
                    for (String v : values) {
                        urlConn.addRequestProperty(key, v);
                    }
                }
            }
            urlConn.setDoOutput(true);
            urlConn.setDoInput(true);
            urlConn.setAllowUserInteraction(false);
            urlConn.setUseCaches(false);
            urlConn.setRequestMethod(method);
            urlConn.setConnectTimeout(connectTimeout);
            urlConn.setReadTimeout(readTimeout);


            if (method.equals(HTTP_METHOD_POST)) {
                String postData = queryString.length() == 0 ? httpParam.getPostData() : queryString;
                if (postData != null && postData.trim().length() > 0) {
                    OutputStream os = urlConn.getOutputStream();
                    OutputStreamWriter osw = new OutputStreamWriter(os, charSet);
                    osw.write(postData);
                    osw.flush();
                    osw.close();
                }
            }

            int responseCode = urlConn.getResponseCode();
            Map<String, List<String>> responseHeaders = urlConn.getHeaderFields();
            String contentType = urlConn.getContentType();

            HttpResult result = new HttpResult(responseCode);
            result.setHeaders(responseHeaders);
            result.setContentType(contentType);

            if (responseCode != 200 && ignoreContentIfUnsuccess) {
                return result;
            }

            InputStream is = urlConn.getInputStream();
            byte[] temp = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int readBytes = is.read(temp);
            while (readBytes > 0) {
                baos.write(temp, 0, readBytes);
                readBytes = is.read(temp);
            }
            String resultString = new String(baos.toByteArray(), charSet); //new String(buffer.array(), charSet);
            baos.close();
            result.setContent(resultString);
            return result;
        } catch (Exception e) {
            LOG.warn("connection error : " + e.getMessage());
            return new HttpResult(e);
        } finally {
            if (urlConn != null) {
                urlConn.disconnect();
            }
        }
    }

    public static String urlEncode(String url, String charSet) {
        return "";
    }

    public static String mapToQueryString(Map<String, Object> params, String charSet) {
        return "";
    }

    public static void setSSLSocketFactory(HttpURLConnection urlConn, boolean sslVerify,
                                            boolean hostnameVerify,
                                            TrustKeyStore trustCertFactory,
                                            ClientKeyStore clientKeyFactory) {
        try {
            SSLSocketFactory socketFactory = null;
            if (trustCertFactory != null || clientKeyFactory != null || !sslVerify) {
                SSLContext sc = SSLContext.getInstance("SSL");
                TrustManager[] trustManagers = null;
                KeyManager[] keyManagers = null;
                if (trustCertFactory != null) {
                    trustManagers = trustCertFactory.getTrustManagerFactory().getTrustManagers();
                }
                if (clientKeyFactory != null) {
                    keyManagers = clientKeyFactory.getKeyManagerFactory().getKeyManagers();
                }
                if (!sslVerify) {
                    trustManagers = trustAnyManagers;
                    hostnameVerify = false;
                }
                sc.init(keyManagers, trustManagers, new java.security.SecureRandom());
                socketFactory = sc.getSocketFactory();
            }

            if (urlConn instanceof HttpsURLConnection) {
                HttpsURLConnection httpsUrlCon = (HttpsURLConnection) urlConn;
                if (socketFactory != null) {
                    httpsUrlCon.setSSLSocketFactory(socketFactory);
                }
                // set whether verify hostname
                if (!hostnameVerify) {
                    httpsUrlCon.setHostnameVerifier(new TrustAnyHostnameVerifier());
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

}
