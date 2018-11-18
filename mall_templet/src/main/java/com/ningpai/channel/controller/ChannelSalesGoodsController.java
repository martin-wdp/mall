/*
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
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

import com.ningpai.channel.bean.ChannelGoods;
import com.ningpai.channel.service.ChannelSalesGoodsService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * @author zhangyue
 * @date 2014年6月4日 下午4:48:40
 * @version V1.0
 */
@Controller
public class ChannelSalesGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelSalesGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";
    private static final String SHOW_ACTION = "showChannelSalesGoods.htm?channelGoodsId=";
    private static final String QUERYSALESCHANNELGOODSBYPAGEBEAN_HTM = "querySalesChannelGoodsByPageBean.htm?channelId=";
    private static final String LOGGERINFO1 = ",用户名:";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    /** 频道促销商品service */
    private ChannelSalesGoodsService channelSalesGoodsService;

    /**
     * 分页查看热销推荐商品设置
     * 
     * @param pb
     *            页面
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/querySalesChannelGoodsByPageBean")
    public ModelAndView queryChannelGoodsByPageBean(PageBean pb, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("pb", channelSalesGoodsService.selectChannelGoodsByParam(pb, channelId));
        mav.addObject("tempId", tempId);
        mav.addObject("channelId", channelId);
        mav.setViewName("jsp/channel/channel_sales_goods_list");
        return mav;
    }

    /**
     * 添加热销推荐商品
     * 
     * @param request
     * @param channelGoods
     *            商品
     * @param bindingResult
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/createSalesChannelGoods")
    public ModelAndView createChannelGoods(HttpServletRequest request, @Valid ChannelGoods channelGoods, BindingResult bindingResult, String tempId, Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSALESCHANNELGOODSBYPAGEBEAN_HTM + channelId));
        }
        // 获取操作人ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 判断操作人ID是否为Null
            if (null == loginUserId) {
                // 设置ID为1
                channelGoods.setChannelGoodsCreateUser(1L);
            } else {
                // 设置ID为当前的操作人ID
                channelGoods.setChannelGoodsCreateUser(loginUserId);
            }
            // 插入频道商品
            int n = channelSalesGoodsService.insertSelective(channelGoods);
            // 判断插入是否成功
            if (n > 0) {
                // 获取操作人名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 插入日志
                OperaLogUtil.addOperaLog(request, customerName, "添加热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道促销商品成功！");
                mav.setView(new RedirectView(QUERYSALESCHANNELGOODSBYPAGEBEAN_HTM + channelId));
            } else {
                LOGGER.debug("保存频道促销商品失败！");
                mav.setView(new RedirectView(SHOW_ACTION + channelGoods.getChannelGoodsId()));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道促销商品异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION + channelGoods.getChannelGoodsId()));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改热销推荐商品
     * 
     * @param request
     * @param response
     * @param channelGoods
     *            频道商品
     * @param bindingResult
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/updateSalesChannelGoods")
    public ModelAndView updateChannelGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelGoods channelGoods, BindingResult bindingResult, String tempId,
            Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYSALESCHANNELGOODSBYPAGEBEAN_HTM + channelId));
        }
        // 获取当前用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            if (null == loginUserId) {
                // 设置ID为1
                channelGoods.setChannelGoodsCreateUser(1L);
            } else {
                // 设置ID为当前的操作人ID
                channelGoods.setChannelGoodsCreateUser(loginUserId);
            }
            // 修改频道商品
            int n = channelSalesGoodsService.updateByPrimaryKeySelective(channelGoods);
            // 判断是否修改成功
            if (n > 0) {
                // 获取当前操作人名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 插入日志
                OperaLogUtil.addOperaLog(request, customerName, "修改热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改频道商品成功！");
                mav.setView(new RedirectView(QUERYSALESCHANNELGOODSBYPAGEBEAN_HTM + channelId));
            } else {
                LOGGER.debug("修改频道商品失败！");
                mav.setView(new RedirectView(SHOW_ACTION + channelGoods.getChannelGoodsId()));
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION + channelGoods.getChannelGoodsId()));
        }
        return mav;
    }

    /**
     * 删除热销推荐商品
     * 
     * @param request
     *            页面请求
     * @param response
     *            页面response
     */
    @RequestMapping("/deleteSalesChannelGoods")
    public void deleteChannelGoods(HttpServletRequest request, HttpServletResponse response) {
        // 获取当前用户ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 如果用户ID为null
        if (null == loginUserId) {
            // 设置ID为1
            loginUserId = 1L;
        }
        // 获取页面频道商品ID数组
        String[] storeyGoodsIds = request.getParameterValues("channelGoodsIds[]");
        // 循环
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            // 删除频道热销商品
            channelSalesGoodsService.deleteByPrimaryKey(Long.valueOf(storeyGoodsIds[i]), loginUserId);
        }
        // 获取当前用户名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 写入日志
        OperaLogUtil.addOperaLog(request, customerName, "删除热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 查看热销推荐商品
     * 
     * @param channelGoodsId
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/showChannelSalesGoods")
    public ModelAndView showChannelGoods(Long channelGoodsId, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        // 判断商品ID是否为null
        if (null != channelGoodsId) {
            // 查询热销商品
            ChannelGoods channelStoreyGoods = channelSalesGoodsService.selectByPrimaryKey(channelGoodsId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.setViewName("jsp/channel/showChannelSalesGoods");
        return mav;
    }

    public ChannelSalesGoodsService getChannelSalesGoodsService() {
        return channelSalesGoodsService;
    }

    // Spring注入
    @Resource(name = "ChannelSalesGoodsService")
    public void setChannelSalesGoodsService(ChannelSalesGoodsService channelSalesGoodsService) {
        this.channelSalesGoodsService = channelSalesGoodsService;
    }

}
