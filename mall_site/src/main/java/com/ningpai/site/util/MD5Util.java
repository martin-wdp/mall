package com.ningpai.site.util;

import com.ningpai.util.MyLogger;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Static functions to simplifiy common {@link java.security.MessageDigest}
 * tasks. This class is thread safe.
 * 
 * @author 99bill
 * 
 */
public class MD5Util {
    private static BigInteger private_d = new BigInteger("3206586642942415709865087389521403230384599658161226562177807849299468150139");
    private static BigInteger n = new BigInteger("7318321375709168120463791861978437703461807315898125152257493378072925281977");

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(MD5Util.class);

    private MD5Util() {
    }

    /**
     * Returns a MessageDigest for the given <code>algorithm</code>.
     * 
     * The MessageDigest algorithm name.
     * 
     * @return An MD5 digest instance.
     * @throws RuntimeException
     *             when a {@link java.security.NoSuchAlgorithmException} is
     *             caught
     */

    static MessageDigest getDigest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
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

    // public static void main(String args[]) {
    // String oldpwd = "of111111";//
    // 0a7b1752d64afbe90a3cdcc3458aeb6c8e479e9f52ed84fc24686160f43d35a8
    // String tempflag = "0";
    //
    // System.out.print(MD5Util.md5Hex("HNHYJDCSSHA0010PEKAZ)suMyA"));
    // if (!(tempflag.equals("1"))) {
    // oldpwd = MD5Util.md5Hex(oldpwd);
    // } else {
    // oldpwd = MD5Util
    // .md5Hex("A354957"
    // + MD5Util.md5Hex(oldpwd)
    // + "da076095a5c7c54e9c9873076a50b531ff5111c7193e5e7021c6181469308552");
    // }
    // System.out.println("oldpwd------------" + oldpwd);
    // String str =
    // getDecryptLoginPassword("04158ce7360c8611fded5fe0281fdd3de0e534c1d2615242b04592e082a05ea8");
    // System.out.println("str------------" + str);
    // }

    /**
     * 获取登录密码
     * 
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
     * 获取转码后字符串
     * 
     * @param content
     * @param charset
     * @return
     * @throws java.io.UnsupportedEncodingException
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
