package com.mini.payment.utils.httpclient;

import javax.net.ssl.KeyManagerFactory;

public class ClientKeyStore {
    private KeyManagerFactory keyManagerFactory;

    public ClientKeyStore(KeyManagerFactory keyManagerFactory) {
        this.keyManagerFactory = keyManagerFactory;
    }

    public KeyManagerFactory getKeyManagerFactory() {
        return keyManagerFactory;
    }

    public void setKeyManagerFactory(KeyManagerFactory keyManagerFactory) {
        this.keyManagerFactory = keyManagerFactory;
    }
}
