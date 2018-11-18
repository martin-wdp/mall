package com.ningpai.des;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;

/**
 * 基础加密组件 Created by Mr.Song on 2015/5/25.
 */
public abstract class Coder {

    /**
     * Secure Hash Algorithm，安全散列算法
     */
    public static final String KEY_SHA = "SHA";

    /**
     * Message Digest algorithm 5，信息摘要算法
     */
    public static final String KEY_MD5 = "md5";

    /**
     * Hash Message Authentication Code，散列消息鉴别码
     */
    public static final String KEY_MAC = "HmacMD5";

    /**
     * BASE64解密
     *
     * @param key
     *            要解密的字符串
     * @return 解密过的字符串
     * @throws Exception
     *             解密过程抛出的异常
     */
    public static byte[] decryptBASE64(String key) throws Exception {
        return (new BASE64Decoder()).decodeBuffer(key);
    }

    /**
     * BASE64加密
     *
     * @param key
     *            要加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     *             加密过程抛出的异常
     */
    public static String encryptBASE64(byte[] key) throws Exception {
        return (new BASE64Encoder()).encodeBuffer(key);
    }

    /**
     * MD5加密
     *
     * @param data
     *            要加密的数据
     * @return
     * @throws Exception
     */
    public static byte[] encryptMD5(byte[] data) throws Exception {

        MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
        md5.update(data);

        return md5.digest();

    }

    /**
     * SHA加密
     *
     * @param data 要进行SHA加密的数据
     * @return  SHA加密后的数据
     * @throws Exception 加密过程中抛出的异常
     */
    public static byte[] encryptSHA(byte[] data) throws Exception {

        MessageDigest sha = MessageDigest.getInstance(KEY_SHA);
        sha.update(data);

        return sha.digest();

    }

    /**
     * 初始化HMAC密钥
     *
     * @return 初始化后的HMAC秘钥
     * @throws Exception 初始化过程中抛出的异常
     */
    public static String initMacKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_MAC);

        SecretKey secretKey = keyGenerator.generateKey();
        return encryptBASE64(secretKey.getEncoded());
    }

    /**
     * HMAC加密
     *
     * @param data HMAC加密前的字符串
     * @param key 加密的秘钥
     * @return HMAC加密后的数据
     * @throws Exception 加密过程中抛出的异常
     */
    public static byte[] encryptHMAC(byte[] data, String key) throws Exception {

        SecretKey secretKey = new SecretKeySpec(decryptBASE64(key), KEY_MAC);
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);

        return mac.doFinal(data);

    }
}
