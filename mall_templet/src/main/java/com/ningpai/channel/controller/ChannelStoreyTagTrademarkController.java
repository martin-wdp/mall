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
import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道楼层品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelStoreyTagTrademarkController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyTagTrademarkController.class);

    private static final String LOGINUSERID = "loginUserId";
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYSTOREYTAGTRADEMARKBYPAGEBEAN_HTM = "queryStoreyTagTrademarkByPageBean.htm?storeyTagId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;
    /** 频道楼层标签业务接口 */
    private ChannelStoreyTagService channelStoreyTagService;
    /** 模板品牌设置业务类 */
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 分页查询频道品牌
     * 
     * @param pb
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/queryStoreyTagTrademarkByPageBean")
    public ModelAndView queryStoreyTagTrademarkByPageBean(PageBean pb, Long storeyTagId) {
        ChannelStoreyTag storeyTag = channelStoreyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        return new ModelAndView("jsp/channel/channel_storey_tag_trademark_list", "pb", channelTrademarkService.selectchannelTrademarkByParam(pb, null, null, null, storeyTagId,
                null, null)).addObject("storeyTag", storeyTag).addObject("channelStorey", channelStorey);
    }

    /**
     * 查看频道品牌
     * 
     * @param trademarkId
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/showStoreyTagTrademark")
    public ModelAndView showStoreyTagTrademark(Long trademarkId, Long storeyTagId) {
        Map<String, Object> map = new HashMap<String, Object>();
        ChannelStoreyTag storeyTag = channelStoreyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        map.put("storeyTag", storeyTag);
        map.put("channelStorey", channelStorey);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("jsp/channel/showChannelStoreyTagTrademark", "map", map);
    }

    /**
     * 添加楼层标签品牌
     * 
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createStoreyTagTrademark")
    public ModelAndView createStoreyTagTrademark(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, Long storeyTagId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGTRADEMARKBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelTrademark.setCreateUserId(loginUserId);
            // 添加频道品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层标签品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道品牌成功！");
            } else {
                LOGGER.debug("保存频道品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYSTOREYTAGTRADEMARKBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 修改楼层标签品牌
     * 
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateStoreyTagTrademark")
    public ModelAndView updateStoreyTagTrademark(HttpServletRequest request, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult, Long storeyTagId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGTRADEMARKBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelTrademark.setUpdateUserId(loginUserId);
            // 修改频道品牌
            int n = this.channelTrademarkService.updateChannelTrademark(channelTrademark);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层标签品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改频道品牌成功！");
            } else {
                LOGGER.debug("修改频道品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYSTOREYTAGTRADEMARKBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 删除楼层标签品牌
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteStoreyTagTrademark")
    public void deleteStoreyTagTrademark(HttpServletRequest request, HttpServletResponse response) {
        String[] trademarkIds = request.getParameterValues("trademarkIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层标签品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelTrademarkService getChannelTrademarkService() {
        return channelTrademarkService;
    }

    @Resource(name = "ChannelTrademarkService")
    public void setChannelTrademarkService(ChannelTrademarkService channelTrademarkService) {
        this.channelTrademarkService = channelTrademarkService;
    }

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }

    public ChannelStoreyTagService getChannelStoreyTagService() {
        return channelStoreyTagService;
    }

    @Resource(name = "ChannelStoreyTagService")
    public void setChannelStoreyTagService(ChannelStoreyTagService channelStoreyTagService) {
        this.channelStoreyTagService = channelStoreyTagService;
    }

}
