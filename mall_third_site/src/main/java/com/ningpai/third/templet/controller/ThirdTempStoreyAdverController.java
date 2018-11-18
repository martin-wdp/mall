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
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelStoreyService;
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
public class ThirdTempStoreyAdverController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyAdverController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String REDIRECT = "queryThirdTempStoreyAdverByPageBean.htm?storeyId=";

    /** 频道楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;

    /** 广告业务接口 */
    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看频道楼层广告设置
     * 
     * @param pb
     * @param storeyId
     * @return
     */
    @RequestMapping("/queryThirdTempStoreyAdverByPageBean")
    public ModelAndView queryThirdTempStoreyAdverByPageBean(PageBean pb, Long storeyId, HttpServletRequest request) {
        LOGGER.debug("======分页查看楼层广告设置======");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", this.channelStoreyService.getChannelStoreyById(storeyId));
        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, null, storeyId, null, 161L, 151L, null, null));
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("temp/temp_storeyadvert_list", "map", map);
    }

    /**
     * 查看频道楼层广告
     * 
     * @param channelAdverId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showThirdTempStoreyAdver")
    public ModelAndView showThirdTempStoreyAdver(Long channelAdverId, Long storeyId) {
        LOGGER.debug("======查看频道楼层广告======");
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", channelStorey);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("temp/show_temp_storeyadvert", "map", map);
    }

    /**
     * 根据ID查询楼层广告信息
     * 
     * @param channelStoreyAdverId
     *            楼层广告ID
     * @return 楼层广告信息
     */
    @RequestMapping("/getChannelStoreyAdverById")
    @ResponseBody
    public ChannelAdver getChannelStoreyAdverById(Long channelStoreyAdverId) {
        return channelAdverService.selectByPrimaryKey(channelStoreyAdverId);
    }

    /**
     * 添加频道楼层广告
     * 
     * @param channelAdver
     * @param width
     *
     * @return
     */
    @RequestMapping("/createThirdTempStoreyAdver")
    public ModelAndView createThirdTempStoreyAdver(MultipartHttpServletRequest request, ChannelAdver channelAdver, Integer width, Integer height) {
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
        return new ModelAndView(new RedirectView(REDIRECT + channelAdver.getFloorId()));
    }

    /**
     * 修改频道楼层广告
     * 
     * @param channelAdver
     * @param width
     *
     * @return
     */
    @RequestMapping("/updateThirdTempStoreyAdver")
    public ModelAndView updateThirdTempStoreyAdver(MultipartHttpServletRequest request, ChannelAdver channelAdver, Integer width, Integer height) {
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
        return new ModelAndView(new RedirectView(REDIRECT + channelAdver.getFloorId()));
    }

    /**
     * 删除频道楼层广告
     * 
     * @param adverIds
     * @param storeyId
     * 
     * @return
     */
    @RequestMapping("/deleteThirdTempStoreyAdver")
    public ModelAndView deleteThirdTempStoreyAdver(HttpServletRequest request, Long[] adverIds, Long storeyId) {
        LOGGER.debug("======删除频道楼层广告======");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        for (int i = 0; i < adverIds.length; i++) {
            channelAdverService.deleteChannelAdver(adverIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(REDIRECT + storeyId));
    }

}
