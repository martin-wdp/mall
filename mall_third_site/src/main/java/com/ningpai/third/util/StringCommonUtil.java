/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.util;

import java.io.UnsupportedEncodingException;

import com.ningpai.common.util.ConstantUtil;

/**
 * 字符串处理工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月22日下午2:10:27
 */
public final class StringCommonUtil {

    /**
     * 中文转码
     */
    public static final String ISO88591 = "ISO-8859-1";
    /**
     * 中文转码
     */
    public static final String UTF8 = ConstantUtil.UTF;

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
        String strNew = str;
        strNew = new String(strNew.getBytes(oldCharset), newCharset);
        return strNew;
    }

}
