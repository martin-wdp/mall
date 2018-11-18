/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.controller;

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
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * 控制器-频道楼层标签商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午3:09:34
 */
@Controller
public class ChannelStoreyTagGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyTagGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";
    /**
     * 用户名称
     */
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYSTOREYTAGGOODSBYPAGEBEAN_HTM = "queryStoreyTagGoodsByPageBean.htm?storeyTagId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 频道楼层业务接口 */
    private ChannelStoreyService storeyService;
    /** 频道楼层标签业务接口 */
    private ChannelStoreyTagService storeyTagService;
    /** 频道楼层业务接口 */
    private ChannelStoreyGoodsService storeyGoodsService;
    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;

    /**
     * 分页查看频道楼层标签商品设置
     * 
     * @param pb
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/queryStoreyTagGoodsByPageBean")
    public ModelAndView queryStoreyTagGoodsByPageBean(PageBean pb, Long storeyTagId) {
        ChannelStoreyTag storeyTag = this.storeyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.storeyService.getChannelStoreyById(storeyTag.getStoreyId());
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("jsp/channel/channel_storey_tag_goods_list", "pb", storeyGoodsService.selectchannelStoreyGoodsByParam(pb, null, storeyTagId, null)).addObject(
                "storeyTag", storeyTag).addObject("channelStorey", channelStorey);
    }

    /**
     * 查看频道楼层标签商品
     * 
     * @param storeyGoodsproductId
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/showStoreyTagGoods")
    public ModelAndView showStoreyTagGoods(Long storeyGoodsproductId, Long storeyTagId) {
        ModelAndView mav = new ModelAndView();
        ChannelStoreyTag storeyTag = this.storeyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.storeyService.getChannelStoreyById(storeyTag.getStoreyId());
        if (null != storeyGoodsproductId) {
            ChannelStoreyGoods channelStoreyGoods = storeyGoodsService.getChannelStoreyGoodsById(storeyGoodsproductId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.addObject("storeyTag", storeyTag);
        mav.addObject("channelStorey", channelStorey);
        mav.setViewName("jsp/channel/showChannelStoreyTagGoods");
        return mav;
    }

    /**
     * 添加楼层标签商品
     * 
     * @param channelStoreyGoods
     * @param bindingResult
     * @return
     */
    @RequestMapping("/createStoreyTagGoods")
    public ModelAndView createStoreyTagGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long storeyTagId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGGOODSBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStoreyGoods.setCreateUserId(loginUserId);
            int n = storeyGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层标签商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道楼层商品成功！");
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYSTOREYTAGGOODSBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改楼层标签商品
     * 
     * @param channelStoreyGoods
     * @param bindingResult
     * @return
     */
    @RequestMapping("/updateStoreyTagGoods")
    public ModelAndView updateStoreyTagGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long storeyTagId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSTOREYTAGGOODSBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                loginUserId = 1L;
            }
            channelStoreyGoods.setUpdateUserId(loginUserId);
            int n = storeyGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层标签商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道楼层商品成功！");
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYSTOREYTAGGOODSBYPAGEBEAN_HTM + storeyTagId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除楼层标签商品
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteStoreyTagGoods")
    public void deleteStoreyTagGoods(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        String[] storeyGoodsIds = request.getParameterValues("storeyGoodsIds[]");
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            storeyGoodsService.deleteChannelStoreyGoods(Long.valueOf(storeyGoodsIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层标签商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ChannelStoreyService getStoreyService() {
        return storeyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setStoreyService(ChannelStoreyService storeyService) {
        this.storeyService = storeyService;
    }

    public ChannelStoreyTagService getStoreyTagService() {
        return storeyTagService;
    }

    @Resource(name = "ChannelStoreyTagService")
    public void setStoreyTagService(ChannelStoreyTagService storeyTagService) {
        this.storeyTagService = storeyTagService;
    }

    public ChannelStoreyGoodsService getStoreyGoodsService() {
        return storeyGoodsService;
    }

    @Resource(name = "ChannelStoreyGoodsService")
    public void setStoreyGoodsService(ChannelStoreyGoodsService storeyGoodsService) {
        this.storeyGoodsService = storeyGoodsService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

}
