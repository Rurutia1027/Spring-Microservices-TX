package com.mini.payment.utils.httpclient;

import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;

public class TrustKeyStore {
    private TrustManagerFactory trustManagerFactory;

    public TrustKeyStore(TrustManagerFactory trustManagerFactory) {
        this.trustManagerFactory = trustManagerFactory;
    }

    public TrustManagerFactory getTrustManagerFactory() {
        return this.trustManagerFactory;
    }

    public static TrustKeyStore loadTrustKeyStore(String keyStorePath, String keyStorePass){
        try{
            return loadTrustKeyStore(new FileInputStream(keyStorePath), keyStorePass);
        }catch(Exception e){
            return null;
        }
    }

    public static TrustKeyStore loadTrustKeyStore(InputStream keyStoreStream, String keyStorePass){
        try{
            TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
            KeyStore ks = KeyStore.getInstance("JKS");
            ks.load(keyStoreStream, keyStorePass.toCharArray());
            tmf.init(ks);
            return new TrustKeyStore(tmf);
        }catch(Exception e){
            return null;
        }
    }
}
