/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.sld.filter;

/**
 * 常量类
 * 
 * @Description 常量类
 * @author Songhl
 * @since 2015年8月28日 15:02:57
 */
public class Constants {

    /**
     * 域名
     */
    public static String DOMAIN = "zqmirsss.com";
    /**
     * www+域名
     */
    public static String WWW_DOMAIN = "www." + DOMAIN;

    private Constants() {
    }

    /**
     * 私有的第二域名
     * 
     * @param secondDomain
     * @return
     */
    public static boolean isPrivateSecondDomain(String secondDomain) {
        return false;
    }
}
