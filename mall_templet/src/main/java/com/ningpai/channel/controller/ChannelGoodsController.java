/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.controller;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelStoreyGoods;
import com.ningpai.channel.service.ChannelGoodsService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制器-热销推荐商品
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午3:09:34
 */
@Controller
public class ChannelGoodsController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelGoodsController.class);

    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String LOGGERINFO1 = ",用户名:";
    private static final String QUERYCHANNELGOODSBYPAGEBEAN_HTM = "queryChannelGoodsByPageBean.htm?tempId=";

    /** 模板业务接口 */
    @Resource(name = "TempService")
    private TempService tempService;
    /** 频道业务接口 */
    @Resource(name = "ChannelService")
    private ChannelService channelService;
    /** 模板广告业务接口 */
    @Resource(name = "ChannelGoodsService")
    private ChannelGoodsService channelGoodsService;

    /**
     * 分页查看热销推荐商品设置
     * 
     * @param pb
     * @param tempId
     * @return ModelAndView
     */
    @RequestMapping("/queryChannelGoodsByPageBean")
    public ModelAndView queryChannelGoodsByPageBean(PageBean pb, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        // 热销商品
        mav.addObject("pb", channelGoodsService.selectchannelStoreyGoodsByParam(pb, tempId.toString(), null, null));
        // 模板ID
        mav.addObject("tempId", tempId);
        // 频道ID
        mav.addObject("channelId", channelId);
        // 跳转页面
        mav.setViewName("jsp/channel/channel_goods_list");
        return mav;
    }

    /**
     * 分页查看热销推荐商品设置(新)
     *
     * @param pb
     * @param
     * @return
     */
    @RequestMapping("/querychannelgoodsbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryChannelGoodsByPageBeanAjax(PageBean pb, String tempId, Long channelId, String storeyTagId) {
        // 设置容器
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置集合
        map.put("pb", channelGoodsService.selectchannelStoreyGoodsByParam(pb, tempId, storeyTagId, null));
        // 返回结果
        return map;
    }

    /**
     * 查看热销推荐商品（新）
     *
     * @param channelStoreyGoodsproductId
     * @return ChannelStoreyGoods
     */
    @RequestMapping("/showchannelgoodsajax")
    @ResponseBody
    public ChannelStoreyGoods showChannelGoodsAjax(Long channelStoreyGoodsproductId) {
        return channelGoodsService.getChannelStoreyGoodsById(channelStoreyGoodsproductId);
    }

    /**
     * 查看热销推荐商品
     * 
     * @param channelStoreyGoodsproductId
     * @param tempId
     * @return ModelAndView
     */
    @RequestMapping("/showChannelGoods")
    public ModelAndView showChannelGoods(Long channelStoreyGoodsproductId, Long tempId, Long channelId) {
        ModelAndView mav = new ModelAndView();
        // 判断频道ID是否为Null
        if (null == channelId) {
            // 模板信息
            SysTemp temp = tempService.getSystempById(tempId);
            mav.addObject("temp", temp);
        } else {
            // 频道信息
            Channel channel = channelService.findChannelByID(channelId);
            mav.addObject("channel", channel);
        }
        if (null != channelStoreyGoodsproductId) {
            // 查询热销商品
            ChannelStoreyGoods channelStoreyGoods = channelGoodsService.getChannelStoreyGoodsById(channelStoreyGoodsproductId);
            mav.addObject("channelStoreyGoods", channelStoreyGoods);
        }
        mav.setViewName("jsp/channel/showChannelGoods");
        return mav;
    }

    /**
     * 添加热销推荐商品（新）--添加楼层商品
     * 
     * @param request
     * @param channelStoreyGoods
     * @return int
     */
    @RequestMapping("/createchannelgoodsajax")
    @ResponseBody
    public int createChannelGoodsAjax(HttpServletRequest request, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult, Long tempId, Long channelId) {
        // 判断是否符合隗帆
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // ModelAndView mav = new ModelAndView();
        int n = 0;
        try {
            // 判断登录id是否为空
            if (null == loginUserId) {
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            // 设置模板id
            if (tempId != null) {
                channelStoreyGoods.setTemp1(tempId.toString());
            }
            // 插入
            n = channelGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            // 判断是否插入成功
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "添加热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("添加热销推荐商品成功！");
            } else {
                LOGGER.debug("添加热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("添加热销推荐商品异常！", e);
        }
        // 返回结果
        return n;
    }

    /**
     * 添加热销推荐商品
     *
     * @param request
     * @param channelStoreyGoods
     * @return ModelAndView
     */
    @RequestMapping("/createChannelGoods")
    public ModelAndView createChannelGoods(HttpServletRequest request, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult, Long tempId, Long channelId) {
        // 判断是否符合隗帆
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELGOODSBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 判断登录id是否为空
            if (null == loginUserId) {
                channelStoreyGoods.setCreateUserId(1L);
            } else {
                channelStoreyGoods.setCreateUserId(loginUserId);
            }
            // 设置模板id
            channelStoreyGoods.setTemp1(tempId.toString());
            // 插入
            int n = channelGoodsService.saveChannelStoreyGoods(channelStoreyGoods);
            // 判断是否插入成功
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "添加热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("添加热销推荐商品成功！");
            } else {
                LOGGER.debug("添加热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("添加热销推荐商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELGOODSBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改楼层商品（新）
     *
     * @param request
     * @param response
     * @return int
     */
    @RequestMapping("/updatechannelgoodsajax")
    @ResponseBody
    public int updateChannelGoodsAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long tempId, Long channelId) {
        // 判断是否规范
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        int n = 0;
        try {
            // 判断是否有登陆id
            if (null == loginUserId) {
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            // 修改热销推荐
            n = channelGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "修改热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改热销推荐商品成功！");
            } else {
                LOGGER.debug("修改热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改热销推荐商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELGOODSBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回结果
        return n;
    }

    /**
     * 修改热销推荐商品
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("/updateChannelGoods")
    public ModelAndView updateChannelGoods(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStoreyGoods channelStoreyGoods, BindingResult bindingResult,
            Long tempId, Long channelId) {
        // 判断是否规范
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELGOODSBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 判断是否有登陆id
            if (null == loginUserId) {
                // 设置操作人ID
                channelStoreyGoods.setUpdateUserId(1L);
            } else {
                channelStoreyGoods.setUpdateUserId(loginUserId);
            }
            // 修改热销推荐
            int n = channelGoodsService.updateChannelStoreyGoods(channelStoreyGoods);
            if (n > 0) {
                // 获取用户名
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 记录日志
                OperaLogUtil.addOperaLog(request, customerName, "修改热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug("修改热销推荐商品成功！");
            } else {
                LOGGER.debug("修改热销推荐商品失败！");
            }
        } catch (Exception e) {
            LOGGER.error("修改热销推荐商品异常！", e);
        }
        mav.setView(new RedirectView(QUERYCHANNELGOODSBYPAGEBEAN_HTM + tempId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        // 返回结果
        return mav;
    }

    /**
     * 删除楼层商品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteChannelGoodsAjax")
    @ResponseBody
    public void deleteChannelGoodsAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取id
        String[] storeyGoodsIds = request.getParameterValues("storeyGoodsIds");
        // 删除
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            // 根据主键删除(这个只是进行了状态修改 不是彻底删除 新增商品的时候 就会出现要添加的商品ID存在的问题
            // id是去的当前商品的ID并不是自增长的ID)
            // channelGoodsService.deleteChannelStoreyGoods(Long.valueOf(storeyGoodsIds[i]),
            // loginUserId);

            // 彻底删除了楼层商品
            channelGoodsService.deleteChannelStoreyGoodsnew(Long.valueOf(storeyGoodsIds[i]));

        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除热销推荐商品
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteChannelGoods")
    public void deleteChannelGoods(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        String[] storeyGoodsIds = request.getParameterValues("storeyGoodsIds[]");
        for (int i = 0; i < storeyGoodsIds.length; i++) {
            channelGoodsService.deleteChannelStoreyGoods(Long.valueOf(storeyGoodsIds[i]), loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除热销推荐商品", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

}
