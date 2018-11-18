/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-楼层标签广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ThirdTempStoreyTagAdverController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyTagAdverController.class);

    /** 楼层标签广告列表控制器 */
    private static final String LIST_ACTION = "queryThirdTempStoreyTagAdverByPageBean.htm";

    private static final String LOGINUSERID = "loginUserId";

    private static final String REDIRECT = "?storeyTagId=";

    /** 楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;

    /** 楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService storeyTagService;

    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
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
    @RequestMapping("/queryThirdTempStoreyTagAdverByPageBean")
    public ModelAndView queryThirdTempStoreyTagAdverByPageBean(PageBean pb, Long storeyTagId) {
        LOGGER.debug("======分页查看楼层广告设置======");
        Map<String, Object> map = new HashMap<String, Object>();
        ChannelStoreyTag storeyTag = storeyTagService.getChannelStoreyTagById(storeyTagId);
        map.put("storeyTag", storeyTag);
        // ChannelStorey channelStorey =
        // this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        map.put("channelStorey", this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId()));
        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, null, null, storeyTagId, 161L, 151L, null, null));
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("temp/temp_storeytagadvert_list", "map", map);
    }

    /**
     * 查看楼层标签广告
     * 
     * @param channelAdverId
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/showThirdTempStoreyTagAdver")
    public ModelAndView showThirdTempStoreyTagAdver(Long channelAdverId, Long storeyTagId) {
        LOGGER.debug("======查看频道楼层广告======");
        ChannelStoreyTag storeyTag = storeyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeyTag", storeyTag);
        map.put("channelStorey", channelStorey);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("temp/show_temp_storeytagadvert", "map", map);
    }

    /**
     * 根据ID查询楼层标签广告信息
     * 
     * @param tagAvderId
     *            标签广告ID
     * @return 标签广告信息
     */
    @RequestMapping("/getStoreyTagAdverById")
    @ResponseBody
    public ChannelAdver getStoreyTagAdverById(Long tagAvderId) {
        return channelAdverService.selectByPrimaryKey(tagAvderId);
    }

    /**
     * 添加楼层标签广告
     * 
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/createThirdTempStoreyTagAdver")
    public ModelAndView createThirdTempStoreyTagAdver(MultipartHttpServletRequest request, ChannelAdver channelAdver, Integer width, Integer height) {
        LOGGER.debug("======添加频道楼层广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setCreateUserId(loginUserId);
        channelAdverService.saveChannelAdver(channelAdver);
        return new ModelAndView(new RedirectView(LIST_ACTION + REDIRECT + channelAdver.getFloorTagId()));
    }

    /**
     * 修改楼层标签广告
     * 
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/updateThirdTempStoreyTagAdver")
    public ModelAndView updateThirdTempStoreyTagAdver(MultipartHttpServletRequest request, ChannelAdver channelAdver, Integer width, Integer height) {
        LOGGER.debug("======修改频道楼层广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setUpdateUserId(loginUserId);
        channelAdverService.updateChannelAdver(channelAdver);
        return new ModelAndView(new RedirectView(LIST_ACTION + REDIRECT + channelAdver.getFloorTagId()));
    }

    /**
     * 删除楼层标签广告
     * 
     * @param request
     * @param adverIds
     * @return
     */
    @RequestMapping("/deleteThirdTempStoreyTagAdver")
    public ModelAndView deleteThirdTempStoreyTagAdver(HttpServletRequest request, Long[] adverIds, Long storeyTagId) {
        LOGGER.debug("======删除频道楼层广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        for (int i = 0; i < adverIds.length; i++) {
            channelAdverService.deleteChannelAdver(adverIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_ACTION + REDIRECT + storeyTagId));
    }

}
