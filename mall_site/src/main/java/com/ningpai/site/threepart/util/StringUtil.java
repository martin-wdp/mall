/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.util;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符串工具
 * @author ggn
 * 
 */
public final class StringUtil {
    /**
     * 私有构造方法
     */
    private StringUtil() {

    }

    /**
     * 格式化字符串
     * 
     * @param resultString
     * @return Map
     */
    public static Map<String, String> formatString(String resultString) {
        Map<String, String> paramMap = null;
        if (resultString.indexOf("access_token") != -1) {
            paramMap = new HashMap<String, String>();
            if (resultString != null && !"".equals(resultString)) {
                String[] temp = resultString.split("&");
                for (int i = 0; i < temp.length; i++) {
                    String[] obj = temp[i].split("=");
                    paramMap.put(obj[0], obj[1]);

                }
            }
        }
        return paramMap;

    }

    /**
     * 格式化字符串
     * @param resultString
     * @return
     */
    public static Map<String, String> formatNetEasyString(String resultString) {
        Map<String, String> paramMap = null;
        if (resultString.indexOf("oauth_token") != -1) {
            paramMap = new HashMap<String, String>();
            if (resultString != null && !"".equals(resultString)) {
                String[] temp = resultString.split("&");
                for (int i = 0; i < temp.length; i++) {
                    String[] obj = temp[i].split("=");
                    paramMap.put(obj[0], obj[1]);

                }
            }
        }
        return paramMap;

    }

    /**
     * 获得打开Id
     * @param str
     * @return
     */
    public static String getOpenId(String str) {
        String[] s = str.split("\"");

        return s[s.length - 2];
    }

}
