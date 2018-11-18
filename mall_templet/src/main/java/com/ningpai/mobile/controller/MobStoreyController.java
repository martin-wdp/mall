/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.mobile.bean.MobStorey;
import com.ningpai.mobile.service.MobStoreyService;
import com.ningpai.util.PageBean;

/**
 * 控制器-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午5:45:53
 */
@Controller
public class MobStoreyController {

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    private static final String REDIRECT = "queryMobStoreyByPageBean.htm";

    @Resource(name = "MobStoreyService")
    private MobStoreyService mobStoreyService;

    /**
     * 分页查看移动版楼层
     * 
     * @param pb
     * @return
     */
    @RequestMapping("/queryMobStoreyByPageBean")
    public ModelAndView queryMobStoreyByPageBean(PageBean pb) {
        return new ModelAndView("jsp/mobile/mobstorey_list", "pb", this.mobStoreyService.selectMobStoreyByPb(pb));
    }

    /**
     * 查看移动版楼层
     * 
     * @param mobStoreyId
     * @return
     */
    @RequestMapping("/showMobStorey")
    public ModelAndView showMobStorey(Long mobStoreyId) {
        ModelAndView mav = new ModelAndView();
        if (null != mobStoreyId) {
            MobStorey mobStorey = this.mobStoreyService.getMobStorey(mobStoreyId);
            mav.addObject("mobStorey", mobStorey);
        }
        mav.setViewName("jsp/mobile/show_mobstorey");
        return mav;
    }

    /**
     * 添加移动版楼层
     * 
     * @param request
     * @param mobStorey
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createMobStorey")
    public ModelAndView createMobStorey(HttpServletRequest request, @Valid MobStorey mobStorey, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        this.mobStoreyService.createMobStorey(mobStorey);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加移动版楼层", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改移动版楼层
     * 
     * @param request
     * @param mobStorey
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateMobStorey")
    public ModelAndView updateMobStorey(HttpServletRequest request, @Valid MobStorey mobStorey, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT));
        }
        this.mobStoreyService.updateMobStorey(mobStorey);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版楼层", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 删除移动版楼层
     * 
     * @param mobStoreyId
     * @param request
     * @return
     */
    @RequestMapping("/deleteMobStorey")
    public ModelAndView deleteMobStorey(Long mobStoreyId, HttpServletRequest request) {
        this.mobStoreyService.deleteMobStorey(mobStoreyId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除移动版楼层", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改移动版楼层启用状态
     * 
     * @param mobStoreyId
     * @param request
     * @return
     */
    @RequestMapping("/changeMobStoreyUserdStatus")
    public ModelAndView changeMobStoreyUserdStatus(Long mobStoreyId, HttpServletRequest request) {
        this.mobStoreyService.changeMobStoreyUserdStatus(mobStoreyId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版楼层启用状态", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT));
    }

}
