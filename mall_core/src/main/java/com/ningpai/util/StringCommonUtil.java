/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.util;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * 字符串处理工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月22日下午2:10:27
 */
public final class StringCommonUtil {

    public static final String ISO88591 = "ISO-8859-1";
    public static final String GB2312 = "GB2312";
    public static final String GBK = "GBK";
    public static final String UTF8 = "UTF-8";

    /**
     * 待转义的字符集合-待完善
     */
    private static final Map<String, String> ceMap = new HashMap<String, String>();

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
     * 字符串字符集转换为UTF-8
     * 
     * @param str
     * @return
     * @throws UnsupportedEncodingException
     */
    public static final String changeToUtf8(String str) throws UnsupportedEncodingException {
        String charset = checkCharset(str);
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
        if (str.equals(new String(str.getBytes(ISO88591), ISO88591))) {
            return ISO88591;
        } else if (str.equals(new String(str.getBytes(GB2312), GB2312))) {
            return GB2312;
        } else if (str.equals(new String(str.getBytes(GBK), GBK))) {
            return GBK;
        } else {
            return UTF8;
        }
    }

    /**
     * 转义字符实体
     * 
     * @param str
     * @return
     */
    public static final String escapeCharacterEntities(String str) {
        String strNew = str;
        for (String key : ceMap.keySet()) {
            strNew = strNew.replace(key, ceMap.get(key));
        }
        return strNew;
    }

    /**
     * 验证是否有待转义的字符
     * 
     * @param str
     * @return
     */
    public static final boolean checkSpecialCharacter(String str) {
        int n = -1;
        for (String key : ceMap.keySet()) {
            int m = str.indexOf(key);
            if (m > -1) {
                n = m;
            }
        }
        return n > -1 ? false : true;
    }

    static {
        ceMap.put("'", "&quot;");
        ceMap.put("&", "&amp;");
        ceMap.put("<", "&lt;");
        ceMap.put(">", "&gt;");

    }
}
