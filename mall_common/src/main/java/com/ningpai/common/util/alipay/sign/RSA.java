package com.ningpai.common.util.alipay.sign;

import com.ningpai.util.MyLogger;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

/**
 * RSA
 */
public class RSA {

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public static final MyLogger LOGGER = new MyLogger(RSA.class);

    public static final String sign1 = null;

    private RSA() {
    }

    /**
     * RSA签名
     *
     * @param content
     *            待签名数据
     * @param privateKey
     *            商户私钥
     * @param inputCharset
     *            编码格式
     * @return 签名值
     */
    public static String sign(String content, String privateKey, String inputCharset) {
        try {
            PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.decode(privateKey));
            KeyFactory keyf = KeyFactory.getInstance("RSA");
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initSign(priKey);
            signature.update(content.getBytes(inputCharset));

            byte[] signed = signature.sign();

            return Base64.encode(signed);
        } catch (Exception e) {
            LOGGER.error(""+e);
            return sign1;
        }

    }

    /**
     * RSA验签名检查
     *
     * @param content
     *            待签名数据
     * @param sign
     *            签名值
     * @param aliPublicKey
     *            支付宝公钥
     * @param inputCharset
     *            编码格式
     * @return 布尔值
     */
    public static boolean verify(String content, String sign, String aliPublicKey, String inputCharset) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(aliPublicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));

            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(content.getBytes(inputCharset));

            return signature.verify(Base64.decode(sign));

        } catch (Exception e) {
            LOGGER.error(""+e);
            return false;
        }

    }

    /**
     * 解密
     *
     * @param content
     *            密文
     * @param privateKey
     *            商户私钥
     * @param inputCharset
     *            编码格式
     * @return 解密后的字符串
     */
    public static String decrypt(String content, String privateKey, String inputCharset) throws Exception {
        PrivateKey prikey = getPrivateKey(privateKey);

        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, prikey);

        InputStream ins = new ByteArrayInputStream(Base64.decode(content));
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        // rsa解密的字节大小最多是128，将需要解密的内容，按128位拆开解密
        byte[] buf = new byte[128];
        int bufl;

        while ((bufl = ins.read(buf)) != -1) {
            byte[] block = null;

            if (buf.length == bufl) {
                block = buf;
            } else {
                block = new byte[bufl];
                for (int i = 0; i < bufl; i++) {
                    block[i] = buf[i];
                }
            }

            writer.write(cipher.doFinal(block));
        }

        return new String(writer.toByteArray(), inputCharset);
    }

    /**
     * 得到私钥
     *
     * @param key
     *            密钥字符串（经过base64编码）
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String key) throws Exception {

        byte[] keyBytes;

        keyBytes = Base64.decode(key);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");

        return keyFactory.generatePrivate(keySpec);
    }
}
