/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.m.catebar.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.mobile.service.MobCateBarService;

/**
 * 控制器-移动版分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月20日下午7:44:20
 */
@Controller
public class MobCateBarSiteController {

    @Resource(name = "MobCateBarService")
    private MobCateBarService mobCateBarService;
    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 获取所有未删除、已启用的移动版分类导航
     * 
     * @return
     */
    @RequestMapping("/queryMobCateBar")
    public ModelAndView queryMobCateBar() {
        System.out.println(this.mobCateBarService.selectMobCateBarForSite().get(0).getName());
        return seoService.getCurrSeo(new ModelAndView("catebar/cates", "catebars", this.mobCateBarService.selectMobCateBarForSite()));
    }
}
