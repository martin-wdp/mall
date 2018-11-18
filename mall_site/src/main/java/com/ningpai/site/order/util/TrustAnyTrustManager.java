package com.ningpai.site.order.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.X509TrustManager;

/**
 * Created by pl on 2015/11/13.
 * Desc:
 */
public class TrustAnyTrustManager implements X509TrustManager{
    public void checkClientTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    public void checkServerTrusted(X509Certificate[] chain, String authType)
            throws CertificateException {

    }

    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
}
