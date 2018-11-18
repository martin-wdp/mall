/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.controller;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.mobile.bean.MobAdver;
import com.ningpai.mobile.service.MobAdverService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-移动版广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午10:17:28
 */
@Controller
public class MobAdverController {

    private static final String REDIRECT = "queryMobAdverByStoreyIdAndPb.htm?storeyId=";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    @Resource(name = "MobAdverService")
    private MobAdverService mobAdverService;

    /**
     * 分页查询列表
     * 
     * @param pb
     * @param storeyId
     * @return
     */
    @RequestMapping("/queryMobAdverByStoreyIdAndPb")
    public ModelAndView queryMobAdverByStoreyIdAndPb(PageBean pb, Long storeyId) {
        return new ModelAndView("jsp/mobile/mobadver_list", "pb", mobAdverService.selectByStoreyIdAndPb(pb, storeyId)).addObject("storeyId", storeyId);
    }

    /**
     * 查看移动版广告<br/>
     * 添加、修改，如果要关联商品，在页面上添加提示，跳转到商品页的路径示例
     * 
     * @param mobAdverId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showMobAdver")
    public ModelAndView showMobAdver(Long mobAdverId, Long storeyId) {
        ModelAndView mav = new ModelAndView();
        if (null != mobAdverId) {
            MobAdver mobAdver = this.mobAdverService.getMobAdver(mobAdverId);
            if (null != mobAdver) {
                mav.addObject("mobAdver", mobAdver);
            }
        }
        mav.addObject("storeyId", storeyId);
        mav.setViewName("jsp/mobile/show_mobadver");
        return mav;
    }

    /**
     * 添加移动版广告
     * 
     * @param mobAdver
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping("/createMobAdver")
    public ModelAndView createMobAdver(@Valid MobAdver mobAdver, BindingResult bindingResult, MultipartHttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT + mobAdver.getStoreyId()));
        }
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            mobAdver.setAdverImgSrc(UploadUtil.uploadFileOne(muFile, request));
        }
        this.mobAdverService.createMobAdver(mobAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加移动版广告", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT + mobAdver.getStoreyId()));
    }

    /**
     * 修改移动版广告
     * 
     * @param mobAdver
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping("/updateMobAdver")
    public ModelAndView updateMobAdver(@Valid MobAdver mobAdver, BindingResult bindingResult, MultipartHttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(REDIRECT + mobAdver.getStoreyId()));
        }
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            mobAdver.setAdverImgSrc(UploadUtil.uploadFileOne(muFile, request));
        }
        this.mobAdverService.updateMobAdver(mobAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版广告", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT + mobAdver.getStoreyId()));
    }

    /**
     * 批量删除移动版广告
     * 
     * @param ids
     * @param request
     * @return
     */
    @RequestMapping("/batchDelMobAdver")
    public ModelAndView batchDelMobAdver(Long[] adverId, Long storeyId, HttpServletRequest request) {
        this.mobAdverService.batchDelMobAdver(Arrays.asList(adverId));
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除移动版广告", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT + storeyId));
    }

    /**
     * 修改移动版广告启用状态
     * 
     * @param mobAdverId
     * @param storeyId
     * @return
     */
    @RequestMapping("/changeMobAdverUserdStatus")
    public ModelAndView changeMobAdverUserdStatus(Long mobAdverId, Long storeyId, HttpServletRequest request) {
        this.mobAdverService.changeMobAdverUserdStatus(mobAdverId);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改移动版广告启用状态", (String) request.getSession().getAttribute(OPERAPATH));
        return new ModelAndView(new RedirectView(REDIRECT + storeyId));
    }

}
