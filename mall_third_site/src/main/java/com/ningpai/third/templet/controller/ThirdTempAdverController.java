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
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.ThirdTempService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-模板广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午9:46:09
 */
@Controller
public class ThirdTempAdverController {

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";

    private static final String THIRDID = "thirdId";
    private static final String ATID = "&atId=";
    private static final String QUERYTHIRDTEMPADVERBYPAGEBEAN_HTM = "queryThirdTempAdverByPageBean.htm?tempId=";

    /* 业务逻辑层依赖 */
    @Resource(name = "ThirdTempService")
    private ThirdTempService tempService;

    @Resource(name = "ChannelAdverService")
    private ChannelAdverService channelAdverService;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看模板广告设置
     * 
     * @param pb
     * @param tempId
     * @param atId
     * @return
     */
    @RequestMapping("/queryThirdTempAdverByPageBean")
    public ModelAndView queryThirdTempAdverByPageBean(HttpServletRequest request, PageBean pb, Long tempId, Long atId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        map.put("temp", temp);
        map.put(THIRDID, thirdId);
        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, tempId, null, null, atId, 151L, null, thirdId));
        map.put("atId", atId);
        return new ModelAndView("temp/temp_advert_list", "map", map);
    }

    /**
     * 查看模板广告
     * 
     * @param channelAdverId
     * @param tempId
     * @param atId
     * @return
     */
    @RequestMapping("/showThirdTempAdver")
    public ModelAndView showThirdTempAdver(HttpServletRequest request, Long channelAdverId, Long tempId, Long atId) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        // 获取第三方商家ID
        String thirdId = ((Long) request.getSession().getAttribute(THIRDID)).toString();
        map.put("temp", temp);
        map.put("atId", atId);
        map.put(THIRDID, thirdId);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("temp/show_temp_advert", "map", map);
    }

    /**
     * 根據id異步獲取第三方模板广告信息
     * 
     * @param request
     * @param channelAdverId
     * @return
     */
    @RequestMapping("/getChannelAdverById")
    @ResponseBody
    public ChannelAdver getChannelAdverById(HttpServletRequest request, Long channelAdverId) {
        return channelAdverService.selectByPrimaryKey(channelAdverId);
    }

    /**
     * 添加模板广告
     * 
     * @param channelAdver
     * @param width
     * @return
     */
    @RequestMapping("/createThirdTempAdver")
    public ModelAndView createThirdTempAdver(MultipartHttpServletRequest request, HttpServletResponse response, ChannelAdver channelAdver, Integer width, Integer height) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setCreateUserId(loginUserId);
        channelAdverService.saveChannelAdver(channelAdver);
        return new ModelAndView(new RedirectView(QUERYTHIRDTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId()));
    }

    /**
     * 修改模板广告
     * 
     * @param channelAdver
     * @param width
     * @return
     */
    @RequestMapping("/updateThirdTempAdver")
    public ModelAndView updateThirdTempAdver(MultipartHttpServletRequest request, HttpServletResponse response, ChannelAdver channelAdver, Integer width, Integer height) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        channelAdver.setUpdateUserId(loginUserId);
        channelAdverService.updateChannelAdver(channelAdver);
        return new ModelAndView(new RedirectView(QUERYTHIRDTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId()));
    }

    /**
     * 删除模板广告
     * 
     * @param channelAdverId
     * @param tempId
     * @return
     */
    @RequestMapping("/deleteThirdTempAdver")
    public ModelAndView deleteThirdTempAdver(HttpServletRequest request, Long[] channelAdverId, Long tempId, Long atId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        for (int i = 0; i < channelAdverId.length; i++) {
            channelAdverService.deleteChannelAdver(channelAdverId[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(QUERYTHIRDTEMPADVERBYPAGEBEAN_HTM + tempId + ATID + atId));
    }

}
