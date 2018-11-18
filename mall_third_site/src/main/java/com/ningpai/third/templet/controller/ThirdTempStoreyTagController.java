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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-第三方楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月30日下午5:04:23
 */
@Controller
public class ThirdTempStoreyTagController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyTagController.class);

    private static final String LIST_ACTION = "queryThirdTempStoreyTagByPageBean.htm";

    private static final String SHOW_ACTION = "showThirdTempStoreyTag.htm";

    private static final String LOGINUSERID = "loginUserId";

    private static final String REDIRECT = "?storeyId=";

    /** 频道楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;

    /** 频道楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService channelStoreyTagService;

    /**
     * 分页查询楼层标签
     * 
     * @param pb
     * @param storeyId
     * @param request
     * @return
     */
    @RequestMapping("/queryThirdTempStoreyTagByPageBean")
    public ModelAndView queryThirdTempStoreyTagByPageBean(PageBean pb, Long storeyId, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", this.channelStoreyService.getChannelStoreyById(storeyId));
        pb.setUrl("queryThirdTempStoreyTagByPageBean.htm");
        map.put("pb", channelStoreyTagService.selectchannelStoreyTagByParam(pb, storeyId, null, null));
        return new ModelAndView("temp/temp_storeytag_list", "map", map);
    }

    /**
     * 查看楼层标签
     * 
     * @param tagId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showThirdTempStoreyTag")
    public ModelAndView showThirdTempStoreyTag(Long tagId, Long storeyId) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", channelStorey);
        if (null != tagId) {
            ChannelStoreyTag channelStoreyTag = channelStoreyTagService.getChannelStoreyTagById(tagId);
            map.put("channelStoreyTag", channelStoreyTag);
        }
        return new ModelAndView("temp/show_temp_storeytag", "map", map);
    }

    /**
     * 根据ID查询楼层标签信息
     * 
     * @param storeyTagId
     *            楼层标签ID
     * @return 楼层标签信息
     */
    @RequestMapping("/getChannelStoreyTagById")
    @ResponseBody
    public ChannelStoreyTag getChannelStoreyTagById(Long storeyTagId) {
        return channelStoreyTagService.getChannelStoreyTagById(storeyTagId);
    }

    /**
     * 添加楼层标签
     * 
     * @param channelStoreyTag
     * @param storeyId
     * @return
     */
    @RequestMapping("/createThirdTempStoreyTag")
    public ModelAndView createThirdTempStoreyTag(HttpServletRequest request, HttpServletResponse response, ChannelStoreyTag channelStoreyTag, Long storeyId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStoreyTag.setCreateUserId(loginUserId);
            // 添加频道标签
            int n = channelStoreyTagService.saveChannelStoreyTag(channelStoreyTag);
            if (n > 0) {
                LOGGER.debug("保存频道品牌成功！");
                mav.setView(new RedirectView(LIST_ACTION + REDIRECT + storeyId));
            } else {
                LOGGER.debug("保存频道品牌失败！");
                mav.setView(new RedirectView(SHOW_ACTION));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道品牌异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION));
        }
        return mav;
    }

    /**
     * 修改楼层标签
     * 
     * @param channelStoreyTag
     * @param storeyId
     * @return
     */
    @RequestMapping("/updateThirdTempStoreyTag")
    public ModelAndView updateThirdTempStoreyTag(HttpServletRequest request, HttpServletResponse response, ChannelStoreyTag channelStoreyTag, Long storeyId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStoreyTag.setUpdateUserId(loginUserId);
            // 修改频道标签
            int n = this.channelStoreyTagService.updateChannelStoreyTag(channelStoreyTag);
            if (n > 0) {
                LOGGER.debug("修改频道品牌成功！");
                mav.setView(new RedirectView(LIST_ACTION + REDIRECT + storeyId));
            } else {
                LOGGER.debug("修改频道品牌失败！");
                mav.setView(new RedirectView(SHOW_ACTION));
            }
        } catch (Exception e) {
            LOGGER.error("修改频道品牌异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION));
        }
        return mav;
    }

    /**
     * 删除楼层标签
     * 
     * @param tagIds
     * @param storeyId
     * @return
     */
    @RequestMapping("/deleteThirdTempStoreyTag")
    public ModelAndView deleteThirdTempStoreyTag(HttpServletRequest request, Long[] tagIds, Long storeyId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteChannelStoreyTag(tagIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_ACTION + REDIRECT + storeyId));
    }

}
