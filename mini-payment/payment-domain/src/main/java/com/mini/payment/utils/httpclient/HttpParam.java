package com.mini.payment.utils.httpclient;

import java.util.HashMap;
import java.util.Map;

public class HttpParam {
    private String url;
    private String method = HttpUtils.HTTP_METHOD_GET;
    private Map<String, Object> parameters = new HashMap<>();
    private String charSet = HttpUtils.DEFAULT_CHARSET;
    private boolean sslVerify = false;
    private int maxResultSize = HttpUtils.MAX_FETCH_SIZE;
    private Map<String, Object> headers = new HashMap<>();
    private int readTimeout = HttpUtils.DEFAULT_READ_TIMEOUT;
    private int connectTimeout = HttpUtils.DEFAULT_CONNECT_TIMEOUT;
    private boolean ignoreContentIfNotSuccess = true;
    private String postData;
    private ClientKeyStore clientKeyStore;
    private TrustKeyStore trustKeyStore;
    private boolean hostnameVerify = false;

    public HttpParam(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public boolean isSslVerify() {
        return sslVerify;
    }

    public void setSslVerify(boolean sslVerify) {
        this.sslVerify = sslVerify;
    }

    public int getMaxResultSize() {
        return maxResultSize;
    }

    public void setMaxResultSize(int maxResultSize) {
        this.maxResultSize = maxResultSize;
    }

    public Map<String, Object> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, Object> headers) {
        this.headers = headers;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public boolean isIgnoreContentIfNotSuccess() {
        return ignoreContentIfNotSuccess;
    }

    public void setIgnoreContentIfNotSuccess(boolean ignoreContentIfNotSuccess) {
        this.ignoreContentIfNotSuccess = ignoreContentIfNotSuccess;
    }

    public String getPostData() {
        return postData;
    }

    public void setPostData(String postData) {
        this.postData = postData;
    }

    public ClientKeyStore getClientKeyStore() {
        return clientKeyStore;
    }

    public void setClientKeyStore(ClientKeyStore clientKeyStore) {
        this.clientKeyStore = clientKeyStore;
    }

    public TrustKeyStore getTrustKeyStore() {
        return trustKeyStore;
    }

    public void setTrustKeyStore(TrustKeyStore trustKeyStore) {
        this.trustKeyStore = trustKeyStore;
    }

    public boolean isHostnameVerify() {
        return hostnameVerify;
    }

    public void setHostnameVerify(boolean hostnameVerify) {
        this.hostnameVerify = hostnameVerify;
    }
}
