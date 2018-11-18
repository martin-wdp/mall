/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.service.MobSiteBasicService;

/**
 * 控制器-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:55:29
 */
@Controller
public class MobSiteBasicController {

    @Resource(name = "MobSiteBasicService")
    private MobSiteBasicService mobSiteBasicService;

    /** 用户名称 */
    private static final String NAME = "name";

    /** "operaPath" */
    private static final String OPERAPATH = "operaPath";

    private static final Logger LOGGER = Logger.getLogger(MobSiteBasicController.class);

    /**
     * 查看移动版站点基础设置
     * 
     * @return
     */
    @RequestMapping("/showMobSiteBasic")
    public ModelAndView showMobSiteBasic(HttpServletRequest request) {

        return new ModelAndView("jsp/mobile/show_site_basic", "mobSiteBasic", this.mobSiteBasicService.selectCurrMobSiteBasic(request));
    }

    /**
     * 修改移动版站点基础设置
     * 
     * @param mobSiteBasic
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping("/updateMobSiteBasic")
    public ModelAndView updateMobSiteBasic(@Valid MobSiteBasic mobSiteBasic, BindingResult bindingResult, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
            LOGGER.debug("修改移动版站点基础设置属性错误");
            throw new RuntimeException("修改移动版站点基础设置属性错误");
        }

        this.mobSiteBasicService.updateMobSiteBasic(mobSiteBasic);

        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版站点基础设置", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView("showMobSiteBasic.htm"));
    }

    /**
     * Ajax查询微信转发信息和logo
     * 
     * @Title: ajaxQueryMobSiteBasic
     * @Description: Ajax查询微信转发信息和logo
     * @param request
     * @return
     */
    @RequestMapping("/ajaxQueryMobSiteBasic")
    @ResponseBody
    public Map<String, String> ajaxQueryMobSiteBasic(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        MobSiteBasic msb = this.mobSiteBasicService.selectCurrMobSiteBasic(request);
        map.put("logo", msb.getTemp1());
        map.put("title", msb.getName());
        map.put("shareTimelineDesc", msb.getTemp3());
        map.put("sendAppMessageDesc", msb.getTemp4());
        return map;
    }
}
