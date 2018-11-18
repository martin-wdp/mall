/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-频道楼层广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelStoreyTagAdverController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyTagAdverController.class);

    private static final String LOGINUSERID = "loginUserId";
    private static final Long ATID = 161L;
    private static final Long ADVERTTYPE = 151L;
    /**
     * 用户名称
     */
    public static final String NAME = "name";
    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYSTOREYTAGADVERBYPAGEBEAN_HTM = "queryStoreyTagAdverByPageBean.htm?storeyTagId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;

    /** 频道楼层标签业务接口 */
    private ChannelStoreyTagService channelStoreyTagService;

    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看楼层标签广告设置
     * 
     * @param pb
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/queryStoreyTagAdverByPageBean")
    public ModelAndView queryStoreyTagAdverByPageBean(PageBean pb, Long storeyTagId) {
        LOGGER.debug("======分页查看频道楼层标签广告设置======");
        ChannelStoreyTag storeyTag = channelStoreyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        // 设置分页参数的url
        return new ModelAndView("jsp/channel/channel_storey_tag_adver_list", "pb", channelAdverService.selectchannelAdverByParam(pb, null, null, null, storeyTagId, ATID,
                ADVERTTYPE, null, null)).addObject("channelStorey", channelStorey).addObject("storeyTag", storeyTag);
    }

    /**
     * 查看楼层标签广告
     * 
     * @param channelAdverId
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/showStoreyTagAdver")
    public ModelAndView showStoreyTagAdver(Long channelAdverId, Long storeyTagId) {
        LOGGER.debug("======查看频道楼层标签广告======");
        Map<String, Object> map = new HashMap<String, Object>();
        ChannelStoreyTag storeyTag = channelStoreyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        map.put("storeyTag", storeyTag);
        map.put("channelStorey", channelStorey);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("jsp/channel/showChannelStoreyTagAdver", "map", map);
    }

    /**
     * 添加楼层标签广告
     * 
     * @param request
     * @param response
     * @param channelAdver
     * @param width
     * @param height
     * @return
     */
    @RequestMapping("/createStoreyTagAdver")
    public ModelAndView createStoreyTagAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGADVERBYPAGEBEAN_HTM + channelAdver.getFloorTagId() + ConstantUtil.CSRF
                    + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======添加频道楼层标签广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setCreateUserId(loginUserId);
        channelAdverService.saveChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加楼层标签广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYSTOREYTAGADVERBYPAGEBEAN_HTM + channelAdver.getFloorTagId() + ConstantUtil.CSRF
                + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改楼层标签广告
     * 
     * @param request
     * @param response
     * @param channelAdver
     * @param width
     * @param height
     * @return
     */
    @RequestMapping("/updateStoreyTagAdver")
    public ModelAndView updateStoreyTagAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGADVERBYPAGEBEAN_HTM + channelAdver.getFloorTagId() + ConstantUtil.CSRF
                    + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======修改频道楼层标签广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setUpdateUserId(loginUserId);
        channelAdverService.updateChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改楼层标签广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYSTOREYTAGADVERBYPAGEBEAN_HTM + channelAdver.getFloorTagId() + ConstantUtil.CSRF
                + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除楼层标签广告
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteStoreyTagAdver")
    public void deleteStoreyTagAdver(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("======删除频道楼层标签广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        String[] adverIds = request.getParameterValues("adverIds[]");
        for (int i = 0; i < adverIds.length; i++) {
            Long id = Long.valueOf(adverIds[i]);
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层标签广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    public ChannelStoreyTagService getChannelStoreyTagService() {
        return channelStoreyTagService;
    }

    @Resource(name = "ChannelStoreyTagService")
    public void setChannelStoreyTagService(ChannelStoreyTagService channelStoreyTagService) {
        this.channelStoreyTagService = channelStoreyTagService;
    }

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }

}
