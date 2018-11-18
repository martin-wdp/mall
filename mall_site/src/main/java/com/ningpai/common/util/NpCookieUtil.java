/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.common.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Cookie工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月26日上午10:20:00
 */
public class NpCookieUtil {

    private NpCookieUtil() {
    }

    /**
     * 设置cookie
     * 
     * @param response
     * @param name
     *            cookie名字
     * @param value
     *            cookie值
     * @param maxAge
     *            cookie生命周期 以秒为单位
     * @throws UnsupportedEncodingException
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) throws UnsupportedEncodingException {
        // cookie值转编码
        String value2 = java.net.URLEncoder.encode(value, ConstantUtil.UTF);
        // 创建新的对象
        Cookie cookie = new Cookie(name, value2);
        cookie.setPath("/");
        if (maxAge > 0) {
            cookie.setMaxAge(maxAge);// 设置时间最大值
        }
        // 添加
        response.addCookie(cookie);
    }

    /**
     * 根据名字获取cookie
     * 
     * @param request
     * @param name
     *            cookie名字
     * @return
     */
    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        // 将cookie封装到Map里面
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        // 查找名字匹配的cookie
        if (cookieMap.containsKey(name)) {
            // 获取该cookie
            return (Cookie) cookieMap.get(name);
        } else {
            return null;
        }
    }

    /***
     * 根据名称删除cookie
     * 
     * @param request
     * @param response
     * @param name
     *            cookie名字
     */
    public static void delCookieByName(HttpServletRequest request, HttpServletResponse response, String name) {
        // 将cookie封装到Map里面
        Map<String, Cookie> cookieMap = ReadCookieMap(request);
        // 查找名字匹配的cookie
        if (cookieMap.containsKey(name)) {
            // 获取该cookie
            Cookie cookie = (Cookie) cookieMap.get(name);
            // 设置有效时间为0,即设置失效
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }

    /**
     * 将cookie封装到Map里面
     * 
     * @param request
     * @return
     */
    private static Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
        // 将cookie封装到Map里面
        Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
        // 获取服务器端的cookie
        Cookie[] cookies = request.getCookies();
        // 如果不为空
        if (null != cookies) {
            // 循环添加进map中
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        // 返回
        return cookieMap;
    }

}
