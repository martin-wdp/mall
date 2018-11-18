/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.GoodsCateService;
import com.ningpai.channel.service.impl.ChannelStoreyServiceImpl;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月29日下午7:09:45
 */
@Controller
public class ChannelStoreyController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyController.class);

    private static final String LOGINUSERID = "loginUserId";

    private static final String SHOW_ACTION = "showChannelStorey.htm";

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    public static final String LOGGERINFO1 = ",用户名:";
    public static final String LOGGERINFO2 = "保存楼层设置成功！";
    public static final String LOGGERINFO3 = "保存楼层设置失败！";
    public static final String QUERYCHANNELSTOREYBYPAGEBEAN_HTM = "queryChannelStoreyByPageBean.htm?CSRFToken=";

    /** 频道业务接口 */
    private ChannelService channelService;

    /** 频道楼层业务接口 */
    private ChannelStoreyServiceImpl channelStoreyServiceImpl;

    /** 商品分类业务接口 */
    private GoodsCateService goodsCateService;

    /**
     * Spring注入
     */
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    public ChannelService getChannelService() {
        return channelService;
    }

    /**
     * Spring注入
     * 
     * @param channelService
     */
    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public GoodsCateService getGoodsCateService() {
        return goodsCateService;
    }

    /**
     * Spring注入
     * 
     * @param goodsCateService
     */
    @Resource(name = "ChannelGoodsCateService")
    public void setGoodsCateService(GoodsCateService goodsCateService) {
        this.goodsCateService = goodsCateService;
    }

    public ChannelStoreyServiceImpl getChannelStoreyServiceImpl() {
        return channelStoreyServiceImpl;
    }

    /**
     * Spring注入
     * 
     * @param channelStoreyServiceImpl
     */
    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyServiceImpl(ChannelStoreyServiceImpl channelStoreyServiceImpl) {
        this.channelStoreyServiceImpl = channelStoreyServiceImpl;
    }

    /**
     * 分页查看频道楼层设置
     * 
     * @param pb
     * @param channelId
     *            频道ID
     * @param request
     * @author Tiancl
     * @return ModelAndView
     */
    @RequestMapping("/queryChannelStoreyByPageBean")
    @ResponseBody
    public Map queryChannelStoreyByPageBean(PageBean pb, Long channelId, HttpServletRequest request) {
        // 设置查询参数
        Map<String, Object> map = new HashMap<String, Object>();
        Channel channel = this.channelService.findChannelByID(channelId);
        map.put("pb", channelStoreyServiceImpl.selectchannelStoreyByParam(pb, channelId, null, null));
        map.put("channel", channel);
        // 设置楼层分类
        map.put("goodsCate", this.goodsCateService.queryAllFirstGradeGoosCate());
        /* 设置查询参数，获取查询后的pb */
        return map;
    }

    /**
     * 查看频道楼层
     * 
     * @param storeyId
     *            楼层ID
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/showChannelStorey")
    public ModelAndView showChannelStorey(Long storeyId, Long channelId) {
        // 声明Map
        Map<String, Object> map = new HashMap<String, Object>();
        // 根据ID获取频道信息
        Channel channel = this.channelService.findChannelByID(channelId);
        map.put("channel", channel);
        // 查询分类列表根据父分类ID
        List<GoodsCate> goodsCates = this.goodsCateService.queryGoosCateByParentId(channel.getGoodsCatId());
        map.put("goodsCates", goodsCates);
        // 判断频道ID是否为null
        if (null != storeyId) {
            // 获取频道详细
            ChannelStorey channelStorey = this.channelStoreyServiceImpl.getChannelStoreyById(storeyId);
            map.put("channelStorey", channelStorey);
        }
        return new ModelAndView("jsp/channel/showChannelStorey", "map", map);
    }

    /**
     * 添加频道的楼层
     * 
     * @param request
     * @param response
     * @param channelStorey
     * @param bindingResult
     * @param channelId
     * @return
     */
    @RequestMapping("/createChannelStoreyForChannel")
    @ResponseBody
    public int createChannelStorey(HttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult, Long channelId,
            Long tempId) {
        if (bindingResult.hasErrors()) {
            return 0;
        }
        // 获取登录名称
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 设置频道id
        channelStorey.setTempId(tempId);
        channelStorey.setChannelId(channelId);
        // 设置登录id
        channelStorey.setCreateUserId(loginUserId);
        // 添加楼层设置
        int n = channelStoreyServiceImpl.saveChannelStorey(channelStorey);
        if (n > 0) {
            String customerName = (String) request.getSession().getAttribute(NAME);
            OperaLogUtil.addOperaLog(request, customerName, "添加模板楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
            LOGGER.debug(LOGGERINFO2);
        } else {
            LOGGER.debug(LOGGERINFO3);
        }
        return n;
    }

    /**
     * 添加频道楼层
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     * @param channelStorey
     *            频道
     * @param bindingResult
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/createChannelStorey")
    public ModelAndView createChannelStorey(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult,
            Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取创建人ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file.getSize() != 0) {
                channelStorey.setStoreyImg(UploadUtil.uploadFileOne(file, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getStoreyImg());
            }
            // 设置频道ID
            channelStorey.setChannelId(channelId);
            // 判断登录Id是否为null
            channelStorey.setCreateUserId(null == loginUserId ? 1L : loginUserId);
            // 添加楼层设置
            int n = channelStoreyServiceImpl.saveChannelStorey(channelStorey);
            mav.addObject("channelId", channelId);
            if (n > 0) {
                // 获取登录人名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 注入日志
                OperaLogUtil.addOperaLog(request, customerName, "添加频道楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
                mav.setView(new RedirectView(QUERYCHANNELSTOREYBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                LOGGER.debug(LOGGERINFO3);
                mav.setView(new RedirectView(SHOW_ACTION));
            }
        } catch (Exception e) {
            LOGGER.error("保存楼层设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 修改频道楼层
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     * @param channelStorey
     * @param bindingResult
     * @param channelId
     *            频道ID
     * @return ModelAndView
     */
    @RequestMapping("/updateChannelStorey")
    public ModelAndView updateChannelStorey(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelStorey channelStorey, BindingResult bindingResult,
            Long channelId) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        ModelAndView mav = new ModelAndView();
        try {
            // 待文件上传区
            MultipartFile file = request.getFile("netLogo");
            // 若有数据则上传文件
            if (file.getSize() != 0) {
                channelStorey.setStoreyImg(UploadUtil.uploadFileOne(file, request));
                // 保存图片信息，以供以后选择
                infoImageManagePublic.saveImage(channelStorey.getStoreyImg());
            }
            // 设置频道ID
            channelStorey.setChannelId(channelId);
            // 判断登录Id是否为null
            channelStorey.setUpdateUserId(null == loginUserId ? 1L : loginUserId);
            // 修改频道楼层
            int n = channelStoreyServiceImpl.updateChannelStorey(channelStorey);
            mav.addObject("channelId", channelId);
            if (n > 0) {
                // 获取登录名称
                String customerName = (String) request.getSession().getAttribute(NAME);
                // 插入日志
                OperaLogUtil.addOperaLog(request, customerName, "修改频道楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
                LOGGER.debug(LOGGERINFO2);
                mav.setView(new RedirectView(QUERYCHANNELSTOREYBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
            } else {
                LOGGER.debug(LOGGERINFO3);
                mav.setView(new RedirectView(SHOW_ACTION));
            }
        } catch (Exception e) {
            LOGGER.error("保存楼层设置对象异常！", e);
            mav.setView(new RedirectView(SHOW_ACTION));
        }
        // 返回楼层设置列表
        return mav;
    }

    /**
     * 删除频道楼层
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     */
    @RequestMapping("/deleteChannelStorey")
    public void deleteChannelStorey(HttpServletRequest request, HttpServletResponse response) {
        // 获取操作人ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 获取楼层ID数组
        String[] storeyIds = request.getParameterValues("storeyIds[]");
        // 循环
        for (int i = 0; i < storeyIds.length; i++) {
            // 删除频道楼层
            channelStoreyServiceImpl.deleteChannelStorey(Long.valueOf(storeyIds[i]), loginUserId);
        }
        // 获取登录人名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 插入日志
        OperaLogUtil.addOperaLog(request, customerName, "删除频道楼层", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }
}
