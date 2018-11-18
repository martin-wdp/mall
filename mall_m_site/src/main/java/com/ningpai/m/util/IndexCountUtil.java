/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 首页访问次数工具类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月16日上午9:34:03
 */
@Controller
public class IndexCountUtil {
    /** 首页访问次数变量名 */
    private static final String COUNT = "indexcount";

    /**
     * 获取首页访问次数
     * 
     * @param request
     * @return
     */
    @RequestMapping("/getCount")
    @ResponseBody
    public Object getCount(HttpServletRequest request) {
        return request.getSession().getAttribute(COUNT);
    }

    /**
     * 设置首页访问次数
     * 
     * @param request   
     */
    @RequestMapping("/setCount")
    @ResponseBody
    public int setCount(HttpServletRequest request, int count) {
        request.getSession().setAttribute(COUNT, count);
        return count;
    }
}
