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
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.third.goods.service.ThirdOtherService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午3:09:34
 */
@Controller
public class ThirdTempStoreyGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ThirdTempStoreyGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String SHOW_VIEW = "showThirdTempStoreyGoods.htm?storeyId=";
    private static final String LIST_VIEW = "queryThirdTemplStoreyGoodsByPageBean.htm?storeyId=";

    /** 楼层业务接口 */
    @Resource(name = "ChannelStoreyService")
    private ChannelStoreyService channelStoreyService;
    /** 楼层商品业务接口 */
    @Resource(name = "ChannelStoreyGoodsService")
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    @Resource(name = "ThirdOtherService")
    private ThirdOtherService thirdOtherService;

    /**
     * 分页查看频道楼层商品设置
     * 
     * @param pb
     * @param
     * @return
     */
    @RequestMapping("/queryThirdTemplStoreyGoodsByPageBean")
    public ModelAndView queryThirdTempStoreyGoodsByPageBean(HttpServletRequest request, PageBean pb, Long storeyId) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        /* 设置查询参数，获取查询后的pb */
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pb", channelStoreyGoodsService.selectchannelStoreyGoodsByParam(pb, storeyId, null, null));
        map.put("channelStorey", channelStorey);
        map.put("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute("thirdId")));
        return new ModelAndView("temp/temp_storeygoods_list", "map", map);
    }

    /**
     * 查看楼层商品
     * 
     * @param channelStoreyGoodsproductId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showThirdTempStoreyGoods")
    public ModelAndView showThirdTempStoreyGoods(Long channelStoreyGoodsproductId, Long storeyId, HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        if (null != channelStoreyGoodsproductId) {
            ChannelStoreyGoods channelStoreyGoods = channelStoreyGoodsService.getChannelStoreyGoodsById(channelStoreyGoodsproductId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.addObject("channelStorey", channelStorey);
        mav.addObject("brandlist", thirdOtherService.queryGrandBrandByThirdId((Long) request.getSession().getAttribute("thirdId")));
        mav.setViewName("temp/show_temp_storeygoods");
        return mav;
    }

    /**
     * 根据id获取楼层商品信息
     * 
     * @param channelGoodsStoreyId
     *            楼层商品id
     * @return 楼层商品信息
     */
    @RequestMapping("/getChannelStoreyGoodsById")
    @ResponseBody
    public ChannelStoreyGoods getChannelStoreyGoodsById(Long channelGoodsStoreyId) {
        return channelStoreyGoodsService.getChannelStoreyGoodsById(channelGoodsStoreyId);
    }

    /**
     * 添加楼层商品
     * 
     * @param channelStoreyGoods
     * @param storeyId
     * @return
     */
    @RequestMapping("/createThirdTempStoreyGoods")
    public ModelAndView createThirdTempStoreyGoods(HttpServletRequest request, HttpServletResponse response, ChannelStoreyGoods channelStoreyGoods, Long storeyId) {
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
                mav.setView(new RedirectView(LIST_VIEW + storeyId));
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
                mav.setView(new RedirectView(SHOW_VIEW + storeyId));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + storeyId));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改楼层商品
     * 
     * @param channelStoreyGoods
     * @param storeyId
     * @return
     */
    @RequestMapping("/updateThirdTempStoreyGoods")
    public ModelAndView updateThirdTempStoreyGoods(HttpServletRequest request, HttpServletResponse response, ChannelStoreyGoods channelStoreyGoods, Long storeyId) {
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
                mav.setView(new RedirectView(LIST_VIEW + storeyId));
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
                mav.setView(new RedirectView(SHOW_VIEW + storeyId));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
            mav.setView(new RedirectView(SHOW_VIEW + storeyId));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除楼层商品
     * 
     * @param storeyGoodsIds
     * @param storeyId
     * @return
     */
    @RequestMapping("/deleteThirdTempStoreyGoods")
    public ModelAndView deleteThirdTempStoreyGoods(HttpServletRequest request, Long[] storeyGoodsIds, Long storeyId) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            channelStoreyGoodsService.deleteChannelStoreyGoods(storeyGoodsIds[i], loginUserId);
        }
        return new ModelAndView(new RedirectView(LIST_VIEW + storeyId));
    }

}
