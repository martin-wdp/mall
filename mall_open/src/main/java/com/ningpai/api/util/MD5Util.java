package com.ningpai.api.util;

import com.ningpai.util.MyLogger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Static functions to simplifiy common {@link MessageDigest} tasks. This class
 * is thread safe.
 *
 * @author 99bill
 *
 */
public class MD5Util {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(MD5Util.class);

    private static BigInteger private_d = new BigInteger("3206586642942415709865087389521403230384599658161226562177807849299468150139");
    private static BigInteger n = new BigInteger("7318321375709168120463791861978437703461807315898125152257493378072925281977");

    private MD5Util() {
    }

    /**
     * Returns a MessageDigest for the given <code>algorithm</code>.
     *
     * The MessageDigest algorithm name.
     * 
     * @return An MD5 digest instance.
     * @throws RuntimeException
     *             when a {@link NoSuchAlgorithmException} is caught
     */

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data
     *            Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(byte[] data) {
        return getDigest().digest(data);
    }

    /**
     * Calculates the MD5 digest and returns the value as a 16 element
     * <code>byte[]</code>.
     *
     * @param data
     *            Data to digest
     * @return MD5 digest
     */
    public static byte[] md5(String data) {
        return md5(data.getBytes());
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data
     *            Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(byte[] data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * Calculates the MD5 digest and returns the value as a 32 character hex
     * string.
     *
     * @param data
     *            Data to digest
     * @return MD5 digest as a hex string
     */
    public static String md5Hex(String data) {
        return HexUtil.toHexString(md5(data));
    }

    /**
     * 加密
     * @param str
     * @return
     */
    public static String getDecryptLoginPassword(String str) {
        byte[] ptext = HexUtil.toByteArray(str);
        BigInteger encryC = new BigInteger(ptext);

        BigInteger privateM = encryC.modPow(private_d, n);
        // 计算明文对应的字符串
        byte[] mt = privateM.toByteArray();
        StringBuilder buffer = new StringBuilder();
        for (int i = mt.length - 1; i > -1; i--) {
            buffer.append((char) mt[i]);
        }

        return buffer.substring(0, buffer.length() - 10).toString();
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws UnsupportedEncodingException
     */
    public static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("",e);
            throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
        }
    }
}
