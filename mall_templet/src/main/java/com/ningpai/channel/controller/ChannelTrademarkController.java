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

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelTrademarkController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelTrademarkController.class);

    private static final String LOGINUSERID = "loginUserId";
    private static final String SHOW_ACTION = "showChannelTrademark.htm";
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYCHANNELTRADEMARKBYPAGEBEAN_HTM = "queryChannelTrademarkByPageBean.htm?channelId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 模板品牌设置业务类 */
    private ChannelTrademarkService channelTrademarkService;

    /**
     * 分页查询频道品牌
     * 
     * @param pb
     * @param channelId
     * @param request
     * @return
     * @throws NPException
     */
    @RequestMapping("/queryChannelTrademarkByPageBean")
    public ModelAndView queryChannelTrademarkByPageBean(PageBean pb, Long channelId, HttpServletRequest request) {
        Channel channel = this.channelService.findChannelByID(channelId);
        return new ModelAndView("jsp/channel/channel_Trademark_list", "pb", channelTrademarkService.selectchannelTrademarkByParam(pb, channelId, channel.getTempId(), null, null,
                null, null)).addObject("channel", channel);
    }

    /**
     * 查看频道品牌
     * 
     * @param trademarkId
     * @param channelId
     * @return
     * @throws NPException
     */
    @RequestMapping("/showChannelTrademark")
    public ModelAndView showChannelTrademark(Long trademarkId, Long channelId) {
        Channel channel = this.channelService.findChannelByID(channelId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channel", channel);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("jsp/channel/showChannelTrademark", "map", map);
    }

    /**
     * 添加频道品牌
     * 
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createChannelTrademark")
    public ModelAndView createChannelTrademark(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult,
            Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELTRADEMARKBYPAGEBEAN_HTM + channelId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setCreateUserId(1L);
            } else {
                channelTrademark.setCreateUserId(loginUserId);
            }
            // 添加频道品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            mav.addObject("channelId", channelId);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加频道品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道品牌成功！");
                mav.setView(new RedirectView(QUERYCHANNELTRADEMARKBYPAGEBEAN_HTM + channelId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
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
     * 修改频道品牌
     * 
     * @param channelTrademark
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateChannelTrademark")
    public ModelAndView updateChannelTrademark(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult,
            Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELTRADEMARKBYPAGEBEAN_HTM + channelId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelTrademark.setUpdateUserId(1L);
            } else {
                channelTrademark.setUpdateUserId(loginUserId);
            }
            // 修改频道品牌
            int n = this.channelTrademarkService.updateChannelTrademark(channelTrademark);
            mav.addObject("channelId", channelId);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改频道品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改频道品牌成功！");
                mav.setView(new RedirectView(QUERYCHANNELTRADEMARKBYPAGEBEAN_HTM + channelId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
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
     * 删除频道品牌
     * 
     * @param request
     * @param response
     * @return
     * @throws NPException
     */
    @RequestMapping("/deleteChannelTrademark")
    public void deleteChannelTrademark(HttpServletRequest request, HttpServletResponse response) {
        String[] trademarkIds = request.getParameterValues("trademarkIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除频道品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ChannelTrademarkService getChannelTrademarkService() {
        return channelTrademarkService;
    }

    @Resource(name = "ChannelTrademarkService")
    public void setChannelTrademarkService(ChannelTrademarkService channelTrademarkService) {
        this.channelTrademarkService = channelTrademarkService;
    }
}
