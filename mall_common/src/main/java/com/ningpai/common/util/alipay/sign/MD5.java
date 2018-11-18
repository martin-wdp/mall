package com.ningpai.common.util.alipay.sign;

import java.io.UnsupportedEncodingException;
import java.security.SignatureException;

import com.ningpai.util.MyLogger;
import org.apache.commons.codec.digest.DigestUtils;

/**
 * 功能：支付宝MD5签名处理核心文件，不需要修改 版本：3.3 修改日期：2012-08-17 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 */

public class MD5 {

    public static final MyLogger LOGGER = new MyLogger(MD5.class);

    /**
     * 签名字符串
     *
     * @param text
     *            需要签名的字符串
     * @param key
     *            密钥
     * @param inputCharset
     *            编码格式
     * @return 签名结果
     */
    public static String sign(String text, String key, String inputCharset) {
        String text1 = text;
        text1 = text1 + key;
        return DigestUtils.md5Hex(getContentBytes(text1, inputCharset));
    }

    /**
     * 签名字符串
     *
     * @param text
     *            需要签名的字符串
     * @param sign
     *            签名结果
     * @param key
     *            密钥
     * @param inputCharset
     *            编码格式
     * @return 签名结果
     */
    public static boolean verify(String text, String sign, String key, String inputCharset) {
        String text1 = text;
        text1 = text1 + key;
        String mysign = DigestUtils.md5Hex(getContentBytes(text1, inputCharset));
        LOGGER.info(mysign + "===" + sign);
        return mysign.equals(sign);
    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws SignatureException
     * @throws UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
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