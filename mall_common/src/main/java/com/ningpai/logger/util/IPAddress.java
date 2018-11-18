/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 获取Ip地址
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月26日 上午11:49:37
 * @version 1.0
 */
public final class IPAddress {

    /**
     * unknown ip
     */
    public static final String UNKNOWN = "unknown";

    private IPAddress() {

    }

    /**
     * 获取IP地址
     * 
     * @param request
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
