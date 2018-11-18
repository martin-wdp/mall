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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyGoodsService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-频道楼层商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午3:09:34
 */
@Controller
public class ChannelStoreyGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String CHANNELSTOREY = "channelStorey";
    private static final String QUERYCHANNELSTOREYGOODSBYPAGEBEAN_HTM = "queryChannelStoreyGoodsByPageBean.htm?storeyId=";
    private static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;
    /** 频道楼层业务接口 */
    private ChannelStoreyGoodsService channelStoreyGoodsService;
    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;

    /**
     * 分页查看频道楼层商品设置
     * 
     * @param pb
     * @param
     * @return
     */
    @RequestMapping("/queryChannelStoreyGoodsByPageBean")
    public ModelAndView queryChannelStoreyGoodsByPageBean(PageBean pb, Long storeyId) {
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        /* 设置查询参数，获取查询后的pb */
        return new ModelAndView("jsp/channel/channel_storey_goods_list", "pb", channelStoreyGoodsService.selectchannelStoreyGoodsByParam(pb, storeyId, null, null)).addObject(
                CHANNELSTOREY, channelStorey);
    }

    /**
     * 分页查看频道楼层商品设置(新)
     *
     * @param pb
     * @param
     * @return
     */
    @RequestMapping("/querychannelstoreygoodsbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryChannelStoreyGoodsByPageBeanAjax(PageBean pb, Long storeyId, Long storeyTagId) {
        // 设置map容器
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置楼层商品
        map.put(CHANNELSTOREY, this.channelStoreyService.getChannelStoreyById(storeyId));
        /* 设置查询参数，获取查询后的pb */
        map.put("pb", channelStoreyGoodsService.selectchannelStoreyGoodsByParam(pb, storeyId, storeyTagId, null));
        // 返回集合
        return map;
    }

    /**
     * 查看频道楼层商品
     *
     * @param channelStoreyGoodsproductId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showChannelStoreyGoods")
    public ModelAndView showChannelStoreyGoods(Long channelStoreyGoodsproductId, Long storeyId) {
        ModelAndView mav = new ModelAndView();
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        if (null != channelStoreyGoodsproductId) {
            ChannelStoreyGoods channelStoreyGoods = channelStoreyGoodsService.getChannelStoreyGoodsById(channelStoreyGoodsproductId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.addObject(CHANNELSTOREY, channelStorey);
        mav.setViewName("jsp/channel/showChannelStoreyGoods");
        return mav;
    }

    /**
     * 查看频道楼层商品(新)
     *
     * @param channelStoreyGoodsproductId
     * @param storeyId
     * @return
     */
    @RequestMapping("/showchannelstoreygoodsajax")
    public ChannelStoreyGoods showChannelStoreyGoodsAjax(Long channelStoreyGoodsproductId, Long storeyId) {
        return channelStoreyGoodsService.getChannelStoreyGoodsById(channelStoreyGoodsproductId);
    }

    /**
     * 添加频道楼层商品
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createChannelStoreyGoods")
    public ModelAndView createChannelStoreyGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods,
            BindingResult bindingResult, Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYGOODSBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置返回集合
        ModelAndView mav = new ModelAndView();
        try {
            // 判断登陆id是否为空
            if (null == loginUserId) {
                // 为空设为1
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                // 为空设为1
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            // 插入数据
            int n = channelStoreyGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            // 判断数据是否符合规范
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道楼层商品成功！");
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYGOODSBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 添加频道楼层商品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/createchannelstoreygoodsajax")
    @ResponseBody
    public int createChannelStoreyGoodsAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long storeyId) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        int n = 0;
        try {
            // 判断登陆id是否为空
            if (null == loginUserId) {
                // 为空设为1
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                // 为空设为1
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            // 插入数据
            n = channelStoreyGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            // 判断数据是否符合规范
            if (n > 0) {
                String customerName = (String) request.getSession().getAttribute(NAME);
                OperaLogUtil.addOperaLog(request, customerName, "添加楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("保存频道楼层商品成功！");
            } else {
                LOGGER.debug("保存频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("保存频道楼层商品异常！", e);
        }
        return n;
    }

    /**
     * 修改楼层商品
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateChannelStoreyGoods")
    public ModelAndView updateChannelStoreyGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods,
            BindingResult bindingResult, Long storeyId) {
        // 判断是否符合验证
        if (bindingResult.hasErrors()) {
            // 返回结果
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYGOODSBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 判断登录id是否为空
            if (null == loginUserId) {
                // 为空则设置为1
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                // 设置登录id
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            // 修改结果
            int n = channelStoreyGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                // 记录日志
                LOGGER.debug("修改频道楼层商品成功！");
            } else {
                // 记录日志
                LOGGER.debug("修改频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改频道楼层商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELSTOREYGOODSBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改楼层商品(新)
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updatechannelstoreygoodsajax")
    @ResponseBody
    public int updateChannelStoreyGoodsAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long storeyId) {
        // 判断是否符合验证
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        int n = 0;
        try {
            // 判断登录id是否为空
            if (null == loginUserId) {
                // 为空则设置为1
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                // 设置登录id
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            // 修改结果
            n = channelStoreyGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "修改楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                // 记录日志
                LOGGER.debug("修改频道楼层商品成功！");
            } else {
                // 记录日志
                LOGGER.debug("修改频道楼层商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改频道楼层商品异常！", e);
        }
        return n;
    }

    /**
     * 删除楼层商品
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteChannelStoreyGoods")
    public void deleteChannelStoreyGoods(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        String[] storeyGoodsIds = request.getParameterValues("storeyGoodsIds[]");
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            channelStoreyGoodsService.deleteChannelStoreyGoods(Long.valueOf(storeyGoodsIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除楼层商品（新）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletechannelstoreygoodsajax")
    @ResponseBody
    public void deleteChannelStoreyGoodsAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获得登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 获取需要删除的id
        String[] storeyGoodsIds = request.getParameterValues("storeyGoodsId");
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            // 批量删除
            channelStoreyGoodsService.deleteChannelStoreyGoods(Long.valueOf(storeyGoodsIds[i]), loginUserId);
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 添加日志
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ChannelStoreyGoodsService getChannelStoreyGoodsService() {
        return channelStoreyGoodsService;
    }

    @Resource(name = "ChannelStoreyGoodsService")
    public void setChannelStoreyGoodsService(ChannelStoreyGoodsService channelStoreyGoodsService) {
        this.channelStoreyGoodsService = channelStoreyGoodsService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }
}
