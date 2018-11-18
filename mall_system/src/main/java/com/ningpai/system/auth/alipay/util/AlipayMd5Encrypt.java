package com.ningpai.system.auth.alipay.util;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.digest.DigestUtils;

import com.ningpai.system.auth.alipay.config.AlipayConfig;
import com.ningpai.util.MyLogger;

/**
 * 功能：支付宝MD5签名处理核心文件，不需要修改 版本：3.1 修改日期：2010-11-01 说明：
 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 * 该代码仅供学习和研究支付宝接口使用，只是提供一个
 * */

public final class AlipayMd5Encrypt {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(AlipayMd5Encrypt.class);

    /**
     * 构造函数
     */
    private AlipayMd5Encrypt() {

    }

    /**
     * 对字符串进行MD5签名
     * 
     * @param text
     *            明文
     * 
     * @return 密文
     */
    public static String md5(String text) {

        return DigestUtils.md5Hex(getContentBytes(text, AlipayConfig.INPUT_CHARSET));

    }

    /**
     * @param content
     * @param charset
     * @return
     * @throws java.io.UnsupportedEncodingException
     */
    private static byte[] getContentBytes(String content, String charset) {
        if (charset == null || "".equals(charset)) {
            return content.getBytes();
        }

        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" ,e);
            return new byte[0];
        }
    }
}
