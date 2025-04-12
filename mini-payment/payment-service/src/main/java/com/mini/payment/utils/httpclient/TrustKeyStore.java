package com.mini.payment.utils.httpclient;

import javax.net.ssl.TrustManagerFactory;

public class TrustKeyStore {
    private TrustManagerFactory trustManagerFactory;

    public TrustKeyStore(TrustManagerFactory trustManagerFactory) {
        this.trustManagerFactory = trustManagerFactory;
    }

    public TrustManagerFactory getTrustManagerFactory() {
        return this.trustManagerFactory;
    }
}
