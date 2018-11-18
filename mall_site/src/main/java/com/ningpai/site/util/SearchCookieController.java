/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.util.MyLogger;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.NpCookieUtil;

/**
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年6月26日上午10:32:28
 */
@Controller
public class SearchCookieController {

    /** cookie的名称 */
    public static final String COOKIENAME = "searchProduct";

    // 生命周期
    public static final int MAXAGE = 60 * 60 * 24 * 3;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(SearchCookieController.class);

    /**
     * Ajax获取前10条搜索记录的Cookie，前台显示
     * 
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping("/getSearchCookieTop10")
    public String[] getSearchCookieTop10(HttpServletRequest request, HttpServletResponse response) {
        String[] searchs = null;
        Cookie cookie = NpCookieUtil.getCookieByName(request, COOKIENAME);
        if (null != cookie) {
            try {
                String cookies = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                searchs = cookies.split(",");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
            }

        }
        return searchs;
    }

    /**
     * Ajax删除搜索记录的Cookie
     * 
     * @param request
     * @param response
     */
    @ResponseBody
    @RequestMapping("/delSearchCookie")
    public void delSearchCookie(String title, HttpServletRequest request, HttpServletResponse response) {
        String list = "";
        String[] searchs = null;
        Cookie cookie = NpCookieUtil.getCookieByName(request, COOKIENAME);
        if (null != cookie) {
            try {
                String cookies = URLDecoder.decode(cookie.getValue(), ConstantUtil.UTF);
                searchs = cookies.split(",");
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
            }
            // 遍历搜索历史
            for (int i = 0; i < searchs.length; i++) {
                // 如果不是要删除的，添加进新的cookie字符串里
                if (!searchs[i].equals(title)) {
                    if ("".equals(list)) {
                        list = searchs[i] + ",";
                    } else {
                        list = list + searchs[i] + ",";
                    }
                }
            }
            try {
                if ("".equals(list)) {
                    NpCookieUtil.addCookie(response, COOKIENAME, list, 0);
                } else {
                    NpCookieUtil.addCookie(response, COOKIENAME, list, MAXAGE);
                }
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("",e);
            }
        }
    }
}
