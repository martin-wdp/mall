/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.templet.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-楼层标签商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午3:09:34
 */
@Controller
public class ThirdTempStoreyTagGoodsController {

    /** 楼层标签广告列表控制器 */
    private static final String LIST_ACTION = "queryThirdTemplStoreyTagGoodsByPageBean.htm?storeyTagId=";
    private static final String SHOW_ACTION = "showThirdTempStoreyTagGoods.htm?storeyTagId=";

    private static final String LOGINUSERID = "loginUserId";

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyTagGoodsController.class);

    /** 楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;
    /** 楼层标签业务接口 */
    @Resource(name = "ChannelStoreyTagService")
    private ChannelStoreyTagService storeyTagService;
    /** 楼层商品业务接口 */
    @Resource(name = "ChannelStoreyGoodsService")
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    @Resource(name = "ThirdOtherService")
    private ThirdOtherService thirdOtherService;

    /**
     * 分页查看楼层标签商品设置
     * 
     * @param pb
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/queryThirdTemplStoreyTagGoodsByPageBean")
    public ModelAndView queryThirdTempStoreyTagGoodsByPageBean(HttpServletRequest request, PageBean pb, Long storeyTagId) {
        Map<String, Object> map = new HashMap<String, Object>();
        ChannelStoreyTag storeyTag = storeyTagService.getChannelStoreyTagById(storeyTagId);
        map.put("storeyTag", storeyTagService.getChannelStoreyTagById(storeyTagId));
        map.put("channelStorey", this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId()));
        map.put("pb", channelStoreyGoodsService.selectchannelStoreyGoodsByParam(pb, null, storeyTagId, null));
        map.put("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute("thirdId")));
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("temp/temp_storeytaggoods_list", "map", map);
    }

    /**
     * 查看楼层标签商品
     * 
     * @param storeyTagGoodsId
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/showThirdTempStoreyTagGoods")
    public ModelAndView showThirdTempStoreyTagGoods(Long storeyTagGoodsId, Long storeyTagId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        ChannelStoreyTag storeyTag = storeyTagService.getChannelStoreyTagById(storeyTagId);
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyTag.getStoreyId());
        mav.addObject("channelStorey", channelStorey);
        mav.addObject("storeyTag", storeyTag);
        if (null != storeyTagGoodsId) {
            ChannelStoreyGoods channelStoreyGoods = channelStoreyGoodsService.getChannelStoreyGoodsById(storeyTagGoodsId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.addObject("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute("thirdId")));
        mav.setViewName("temp/show_temp_storeytaggoods");
        return mav;
    }

    /**
     * 根据ID查询标签商品信息
     * 
     * @param tagGoodsId
     *            标签商品ID
     * @return 标签商品信息
     */
    @RequestMapping("/getTagGoodsInfoById")
    @ResponseBody
    public ChannelStoreyGoods getTagGoodsById(Long tagGoodsId) {
        return channelStoreyGoodsService.getChannelStoreyGoodsById(tagGoodsId);
    }

    /**
     * 添加楼层标签商品
     * 
     * @param channelStoreyGoods
     * @param storeyTagId
     *
     * @return
     */
    @RequestMapping("/createThirdTempStoreyTagGoods")
    public ModelAndView createThirdTempStoreyTagGoods(HttpServletRequest request, HttpServletResponse response, ChannelStoreyGoods channelStoreyGoods, Long storeyTagId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            int n = channelStoreyGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                LOGGER.debug("保存频道楼层商品成功！");
                mav.setView(new RedirectView(LIST_ACTION + storeyTagId));
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
                mav.setView(new RedirectView(SHOW_ACTION + storeyTagId));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION + storeyTagId));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改楼层标签商品
     * 
     * @param channelStoreyGoods
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/updateThirdTempStoreyTagGoods")
    public ModelAndView updateThirdTempStoreyTagGoods(HttpServletRequest request, ChannelStoreyGoods channelStoreyGoods, Long storeyTagId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            int n = channelStoreyGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                LOGGER.debug("保存频道楼层商品成功！");
                mav.setView(new RedirectView(LIST_ACTION + storeyTagId));
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
                mav.setView(new RedirectView(SHOW_ACTION + storeyTagId));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION + storeyTagId));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除楼层标签商品
     * 
     * @param storeyGoodsIds
     * @param storeyTagId
     * @return
     */
    @RequestMapping("/deleteThirdTempStoreyTagGoods")
    public ModelAndView deleteThirdTempStoreyTagGoods(HttpServletRequest request, Long[] storeyGoodsIds, Long storeyTagId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            channelStoreyGoodsService.deleteChannelStoreyGoods(storeyGoodsIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_ACTION + storeyTagId));
    }

}
