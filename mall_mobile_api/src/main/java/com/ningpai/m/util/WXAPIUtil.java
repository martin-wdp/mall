/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 微信Api帮助类
 * */
@Controller
public class WXAPIUtil {
    /**
     * 告警接口
     * 
     * @param rquest
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/wxerr")
    public void respWXNoticeApi(HttpServletRequest rquest, HttpServletResponse resp) throws IOException {
        PrintWriter out;
        out = resp.getWriter();
        out.append("success");
    }

    /**
     * 维权接口
     * 
     * @param rquest
     * @param resp
     * @throws IOException
     */
    @RequestMapping("/help")
    public void respWxAdultsApi(HttpServletRequest rquest, HttpServletResponse resp) throws IOException {
        PrintWriter out;
        out = resp.getWriter();
        out.append("success");
    }
}
