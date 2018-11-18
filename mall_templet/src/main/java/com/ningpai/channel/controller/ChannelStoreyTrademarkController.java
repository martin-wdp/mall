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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelTrademarkService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道楼层品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelStoreyTrademarkController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyTrademarkController.class);

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;
    /** 模板品牌设置业务类 */
    private ChannelTrademarkService channelTrademarkService;

    private static final String LOGINUSERID = "loginUserId";
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    private static final String STOREYID = "storeyId";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String QUERYCHANNELSTOREYTRADEMARKBYPAGEBEAN_HTM = "queryChannelStoreyTrademarkByPageBean.htm?storeyId=";

    /**
     * 分页查询频道品牌
     * 
     * @param pb
     * @param storeyId
     * @param request
     * @return
     * @throws NPException
     */
    @RequestMapping("/queryChannelStoreyTrademarkByPageBean")
    public ModelAndView queryChannelStoreyTrademarkByPageBean(PageBean pb, Long storeyId, HttpServletRequest request) {
        // 获取楼层信息
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        // 返回结果
        return new ModelAndView("jsp/channel/channel_storey_Trademark_list", "pb",
                channelTrademarkService.selectchannelTrademarkByParam(pb, null, null, storeyId, null, null, null)).addObject("channelStorey", channelStorey);
    }

    /**
     * 分页查询频道品牌
     *
     * @param pb
     * @param storeyId
     * @param request
     * @return
     * @throws NPException
     */
    @RequestMapping("/querychannelstoreytrademarkbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryChannelStoreyTrademarkByPageBeanAjax(PageBean pb, Long storeyId, HttpServletRequest request, Long storeyTagId) {
        // 设置map容器
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合
        map.put("pb", channelTrademarkService.selectchannelTrademarkByParam(pb, null, null, storeyId, storeyTagId, null, null));
        // 返回值
        return map;
    }

    /**
     * 查看频道品牌
     * 
     * @param trademarkId
     * @param storeyId
     * @return
     * @throws NPException
     */
    @RequestMapping("/showChannelStoreyTrademark")
    public ModelAndView showChannelStoreyTrademark(Long trademarkId, Long storeyId) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", channelStorey);
        if (null != trademarkId) {
            ChannelTrademark channelTrademark = this.channelTrademarkService.getChannelTrademarkById(trademarkId);
            map.put("channelTrademark", channelTrademark);
        }
        return new ModelAndView("jsp/channel/showChannelStoreyTrademark", "map", map);
    }

    /**
     * 查看频道品牌
     *
     * @param trademarkId
     * @return
     * @throws NPException
     */
    @RequestMapping("/showchannelstoreytrademarkajax")
    @ResponseBody
    public ChannelTrademark showChannelStoreyTrademarkAjax(Long trademarkId) {
        return this.channelTrademarkService.getChannelTrademarkById(trademarkId);
    }

    /**
     * 添加楼层品牌
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createChannelStoreyTrademark")
    public ModelAndView createChannelStoreyTrademark(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark,
            BindingResult bindingResult, Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYTRADEMARKBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获得登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取返回对象
        ModelAndView mav = new ModelAndView();
        try {
            // 判断用户名是否为空
            if (null == loginUserId) {
                // 判断登陆id
                loginUserId = 1L;
            }
            // 设置创建id
            channelTrademark.setCreateUserId(loginUserId);
            // 添加频道品牌
            int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
            // 添加信息
            mav.addObject(STOREYID, storeyId);
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            } else {
                // 记录失败日志
                LOGGER.debug("保存频道品牌失败！");
            }
        } catch (Exception e) {
            // 记录失败日志
            LOGGER.error("保存频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYTRADEMARKBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 添加楼层品牌
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createchannelstoreytrademarkajax")
    @ResponseBody
    public int createChannelStoreyTrademarkAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult,
            Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获得登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取返回对象
        ModelAndView mav = new ModelAndView();
        channelTrademark.setCreateUserId(loginUserId);
        // 添加频道品牌
        int n = channelTrademarkService.saveChannelTrademark(channelTrademark);
        // 添加信息
        mav.addObject(STOREYID, storeyId);
        if (n > 0) {
            // 获取用户名
            String customerName = (String) request.getSession().getAttribute(NAME);
            // 记录日志
            OperaLogUtil.addOperaLog(request, customerName, "添加楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } else {
            // 记录失败日志
            LOGGER.debug("保存频道品牌失败！");
        }
        return n;
    }

    /**
     * 修改楼层品牌
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateChannelStoreyTrademark")
    public ModelAndView updateChannelStoreyTrademark(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark,
            BindingResult bindingResult, Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYTRADEMARKBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
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
            mav.addObject(STOREYID, storeyId);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改频道品牌成功！");
            } else {
                LOGGER.debug("修改频道品牌失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改频道品牌异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYTRADEMARKBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        return mav;
    }

    /**
     * 修改楼层品牌
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatechannelstoreytrademarkajax")
    @ResponseBody
    public int updateChannelStoreyTrademarkAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelTrademark channelTrademark, BindingResult bindingResult,
            Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 设置登陆id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);

        // 设置修改用户
        channelTrademark.setUpdateUserId(loginUserId);
        // 修改频道品牌
        int n = this.channelTrademarkService.updateChannelTrademark(channelTrademark);
        // 判断是否
        if (n > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "修改楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            LOGGER.debug("修改频道品牌成功！");
        } else {
            LOGGER.debug("修改频道品牌失败！");
        }

        return n;
    }

    /**
     * 删除楼层品牌
     * 
     * @param request
     * @param response
     * @return
     * @throws NPException
     */
    @RequestMapping("/deleteChannelStoreyTrademark")
    public void deleteChannelStoreyTrademark(HttpServletRequest request, HttpServletResponse response) {
        String[] trademarkIds = request.getParameterValues("trademarkIds[]");
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除楼层品牌
     *
     * @param request
     * @param response
     * @return
     * @throws NPException
     */
    @RequestMapping("/deletechannelstoreytrademarkajax")
    @ResponseBody
    public void deleteChannelStoreyTrademarkAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取删除id
        String[] trademarkIds = request.getParameterValues("trademarkId");

        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < trademarkIds.length; i++) {
            channelTrademarkService.deleteChannelTrademark(Long.valueOf(trademarkIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层品牌", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
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

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }

}
