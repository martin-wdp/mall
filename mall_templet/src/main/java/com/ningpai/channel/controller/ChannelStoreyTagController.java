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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月30日下午5:04:23
 */
@Controller
public class ChannelStoreyTagController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyTagController.class);

    private static final String LOGINUSERID = "loginUserId";
    /**
     * 用户名称
     */
    public static final String NAME = "name";
    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYCHANNELSTOREYTAGBYPAGEBEAN_HTM = "queryChannelStoreyTagByPageBean.htm?storeyId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;

    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;

    /** 频道楼层标签业务接口 */
    private ChannelStoreyTagService channelStoreyTagService;

    /**
     * 分页查询频道标签
     * 
     * @param pb
     * @param storeyId
     * @param request
     * @return
     */
    @RequestMapping("/queryChannelStoreyTagByPageBean")
    public ModelAndView queryChannelStoreyTagByPageBean(PageBean pb, Long storeyId, HttpServletRequest request) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        return new ModelAndView("jsp/channel/channel_storey_Tag_list", "pb", channelStoreyTagService.selectchannelStoreyTagByParam(pb, storeyId, null, null)).addObject(
                "channelStorey", channelStorey);
    }

    /**
     * 查看频道标签
     * 
     * @param tagId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showChannelStoreyTag")
    public ModelAndView showChannelStoreyTag(Long tagId, Long storeyId) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", channelStorey);
        if (null != tagId) {
            ChannelStoreyTag channelStoreyTag = channelStoreyTagService.getChannelStoreyTagById(tagId);
            map.put("channelStoreyTag", channelStoreyTag);
        }
        return new ModelAndView("jsp/channel/showChannelStoreyTag", "map", map);
    }

    /**
     * 添加频道标签
     * 
     * @param channelStoreyTag
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createChannelStoreyTag")
    public ModelAndView createChannelStoreyTag(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult,
            Long storeyId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYTAGBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
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
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道品牌成功！");
            } else {
                LOGGER.debug("保存频道品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYTAGBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 修改频道标签
     * 
     * @param channelStoreyTag
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateChannelStoreyTag")
    public ModelAndView updateChannelStoreyTag(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyTag channelStoreyTag, BindingResult bindingResult,
            Long storeyId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYTAGBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
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
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改频道品牌成功！");
            } else {
                LOGGER.debug("修改频道品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYTAGBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 删除频道标签
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteChannelStoreyTag")
    public void deleteChannelStoreyTag(HttpServletRequest request, HttpServletResponse response) {
        String[] tagIds = request.getParameterValues("tagIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteChannelStoreyTag(Long.valueOf(tagIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除频道标签
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteChannelStoreyTagPro")
    public void deleteChannelStoreyTagPro(HttpServletRequest request, HttpServletResponse response) {
        String[] tagIds = request.getParameterValues("tagIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < tagIds.length; i++) {
            channelStoreyTagService.deleteByPrimaryKeyCallPro(Long.valueOf(tagIds[i]));
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
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
