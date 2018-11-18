/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.common.util;

import java.io.UnsupportedEncodingException;

/**
 * 字符串处理工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月22日下午2:10:27
 */
public final class StringCommonUtil {
    /**
     * IOS字符编码
     */
    public static final String ISO88591 = "ISO-8859-1";
    /**
     * gb2312字符编码
     */
    public static final String GB2312 = "GB2312";
    /**
     * gbk字符编码
     */
    public static final String GBK = "GBK";
    /**
     * utf-8字符编码
     */
    public static final String UTF8 = ConstantUtil.UTF;

    /**
     * 私有构造函数
     */
    private StringCommonUtil() {
        super();
    }

    /**
     * 字符串字符集转换方法
     * 
     * @param str
     *            要转换的字符串
     * @param oldCharset
     *            老字符集
     * @param newCharset
     *            新字符集
     * @return @see java.lang.String 转换好的字符串
     * @throws UnsupportedEncodingException
     */
    public static final String charsetConversion(String str, String oldCharset, String newCharset) throws UnsupportedEncodingException {
        return new String(str.getBytes(oldCharset), newCharset);
    }

    /**
     * 字符串转码
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String changeToUtf8(String str) throws UnsupportedEncodingException {
        // 查看字符串的字符集
        String charset = checkCharset(str);
        // 字符串字符集转换方法
        return charsetConversion(str, charset, UTF8);
    }

    /**
     * 查看字符串的字符集
     * 
     * @param str
     *            要验证的字符串
     * @return 字符集
     * @throws UnsupportedEncodingException
     */
    public static final String checkCharset(String str) throws UnsupportedEncodingException {
        // 返回ISO字符集
        if (str.equals(new String(str.getBytes(ISO88591), ISO88591))) {
            return ISO88591;
            // 返回GB2312字符集
        } else if (str.equals(new String(str.getBytes(GB2312), GB2312))) {
            return GB2312;
            // 返回GBK字符集
        } else if (str.equals(new String(str.getBytes(GBK), GBK))) {
            return GBK;
        } else {
            // 返回UTF8字符集
            return UTF8;
        }
    }

}
