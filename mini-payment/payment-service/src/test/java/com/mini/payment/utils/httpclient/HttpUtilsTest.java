package com.mini.payment.utils.httpclient;

import org.junit.jupiter.api.Test;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HttpUtilsTest {
    @Test
    public void testHttpRequest_withInvalidUrl_shouldThrowException() {
        HttpParam param = new HttpParam("");
        param.setMethod("GET");
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            HttpUtils.httpRequest(param);
        });
        assertTrue(ex.getMessage().contains("invalid url"));
    }

    @Test
    public void testHttpRequest_withUnsupportedMethod_shouldThrow() {
        HttpParam param = new HttpParam("http://example.com");
        param.setMethod("PUT"); // we only support GET/POST in our http util
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            HttpUtils.httpRequest(param);
        });
        assertTrue(ex.getMessage().contains("invalid http method"));
    }

    @Test
    public void testSetSSLSocketFactory_withCustomTrustManager() throws Exception {
        HttpsURLConnection mockConn = mock(HttpsURLConnection.class);
        TrustKeyStore trustKeyStore = mock(TrustKeyStore.class);
        TrustManagerFactory mockFactory = mock(TrustManagerFactory.class);
        when(trustKeyStore.getTrustManagerFactory()).thenReturn(mockFactory);
        when(mockFactory.getTrustManagers()).thenReturn(new TrustManager[]{new TrustAnyTrustManager()});

        // will not throw
        HttpUtils.setSSLSocketFactory(mockConn, true, true, trustKeyStore, null);
        verify(mockConn).setSSLSocketFactory(any(SSLSocketFactory.class));
    }

    @Test
    void testLoadTrustKeyStore_withInvalidPath_shouldReturnNull() {
        TrustKeyStore ks = TrustKeyStore.loadTrustKeyStore("not-exist.jks", "123456");
        assertNull(ks);
    }
}