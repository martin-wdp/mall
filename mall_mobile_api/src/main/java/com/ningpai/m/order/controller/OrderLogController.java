/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;

/**
 * 日志控制器
 * 
 * @author NINGPAI-LIH
 * @since 2014年9月18日15:03:42
 * 
 */
@Controller
public class OrderLogController {

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 报错页面
     * 
     * @param message
     *            报错信息
     * @return
     */
    @RequestMapping("/errorpage")
    public ModelAndView errorPage(String message) {
        ModelAndView mav = new ModelAndView("order/error_page").addObject("message", message);
        return seoService.getCurrSeo(mav);
    }
}
