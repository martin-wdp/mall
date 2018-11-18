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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.channel.service.ISysDictionaryBiz;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-频道楼层广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelStoreyAdverController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelStoreyAdverController.class);

    private static final String LOGINUSERID = "loginUserId";
    private static final Long ATID = 161L;
    private static final Long ADVERTTYPE = 151L;
    /**
     * 用户名称
     */
    public static final String NAME = "name";
    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";
    public static final String QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM = "queryChannelStoreyAdverByPageBean.htm?storeyId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;

    /** 频道楼层业务接口 */
    private ChannelStoreyService channelStoreyService;

    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;

    /** 系统字典业务接口 */
    private ISysDictionaryBiz sysDicBiz;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看频道楼层广告设置
     * 
     * @param pb
     * @param storeyId
     *            楼层ID
     * @param request
     *            页面请求
     * @return ModelAndView
     */
    @RequestMapping("/queryChannelStoreyAdverByPageBean")
    public ModelAndView queryChannelStoreyAdverByPageBean(PageBean pb, Long storeyId, HttpServletRequest request) {
        LOGGER.debug("======分页查看频道楼层广告设置======");
        // 分页查看频道楼层广告设置
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);

        // 设置分页参数的url
        pb.setUrl(QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM + storeyId + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN));
        return new ModelAndView("jsp/channel/channel_storey_adver_list", "pb", channelAdverService.selectchannelAdverByParam(pb, null, null, storeyId, null, ATID, ADVERTTYPE,
                null, null)).addObject("channelStorey", channelStorey);
    }

    /**
     * 分页查看频道楼层广告设置
     * 
     * @param pb
     *            PageBean
     * @param storeyId
     *            楼层ID
     * @param request
     *            页面请求
     * @return Map
     */
    @RequestMapping("/querychannelstoreyadverbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryChannelStoreyAdverByPageBeanAjax(PageBean pb, Long storeyId, HttpServletRequest request) {
        // 声明Map
        Map<String, Object> map = new HashMap<String, Object>();
        // 查询频道页广告
        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, null, storeyId, null, ATID, ADVERTTYPE, null, null));
        return map;
    }

    /**
     * 查看频道楼层广告
     * 
     * @param channelAdverId
     *            广告ID
     * @param storeyId
     *            楼层ID
     * @return ModelAndView
     */
    @RequestMapping("/showChannelStoreyAdver")
    public ModelAndView showChannelStoreyAdver(Long channelAdverId, Long storeyId) {
        LOGGER.debug("======查看频道楼层广告======");
        // 查询楼层信息
        ChannelStorey channelStorey = this.channelStoreyService.getChannelStoreyById(storeyId);
        // 申明Map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelStorey", channelStorey);
        // 判断频道广告是否为null
        if (null != channelAdverId) {
            // 查询广告信息
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("jsp/channel/showChannelStoreyAdver", "map", map);
    }

    /**
     * 添加频道楼层广告
     * 
     * @param request
     *            请求
     * @param response
     *            请求响应
     * @param channelAdver
     *            图片
     * @param bindingResult
     * @param width
     *            宽度
     * @param height
     *            高度
     * @return ModelAndView
     */
    @RequestMapping("/createChannelStoreyAdver")
    public ModelAndView createChannelStoreyAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM + channelAdver.getFloorId() + ConstantUtil.CSRF
                    + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======添加频道楼层广告======");
        // 获取登录ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取图片信息
        MultipartFile muFile = request.getFile("imgSrc");
        // 判断图片信息
        if (null != muFile && muFile.getSize() > 0) {
            // 上传图片并赋值
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置创建人ID
        channelAdver.setCreateUserId(loginUserId);
        // 保存楼层广告
        channelAdverService.saveChannelAdver(channelAdver);
        // 获取登录人名册
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 插入日志
        OperaLogUtil.addOperaLog(request, customerName, "添加楼层广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM + channelAdver.getFloorId() + ConstantUtil.CSRF
                + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改频道楼层广告
     * 
     * @param request
     *            请求
     * @param response
     *            响应
     * @param channelAdver
     * @param bindingResult
     * @param width
     *            宽度
     * @param height
     *            高度
     * @return ModelAndView
     */
    @RequestMapping("/updateChannelStoreyAdver")
    public ModelAndView updateChannelStoreyAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM + channelAdver.getFloorId() + ConstantUtil.CSRF
                    + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("======修改频道楼层广告======");
        // 获取当前登录ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取图片信息
        MultipartFile muFile = request.getFile("imgSrc");
        // 判断图片是否为null
        if (null != muFile && muFile.getSize() > 0) {
            // 上传图片并赋值
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置修改人ID
        channelAdver.setUpdateUserId(loginUserId);
        // 修改广告
        channelAdverService.updateChannelAdver(channelAdver);
        // 获取操作人名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 插入日志
        OperaLogUtil.addOperaLog(request, customerName, "修改楼层广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCHANNELSTOREYADVERBYPAGEBEAN_HTM + channelAdver.getFloorId() + ConstantUtil.CSRF
                + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除频道楼层广告
     * 
     * @param request
     * @param response
     */
    @RequestMapping("/deleteChannelStoreyAdver")
    public void deleteChannelStoreyAdver(HttpServletRequest request, HttpServletResponse response) {
        LOGGER.debug("======删除频道楼层广告======");
        // 获取登录ID
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取广告ID数组
        String[] adverIds = request.getParameterValues("adverIds[]");
        // 循环删除
        for (int i = 0; i < adverIds.length; i++) {
            Long id = Long.valueOf(adverIds[i]);
            // 删除
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        // 获取创建人名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 插入日志
        OperaLogUtil.addOperaLog(request, customerName, "删除楼层广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

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

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    /**
     * Spring注入
     * 
     * @param channelAdverService
     */
    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    public ISysDictionaryBiz getSysDicBiz() {
        return sysDicBiz;
    }

    /**
     * Spring注入
     * 
     * @param sysDicBiz
     */
    @Resource(name = "channelSysDictionaryBizImpl")
    public void setSysDicBiz(ISysDictionaryBiz sysDicBiz) {
        this.sysDicBiz = sysDicBiz;
    }

    public ChannelStoreyService getChannelStoreyService() {
        return channelStoreyService;
    }

    /**
     * Spring注入
     * 
     * @param channelStoreyService
     */
    @Resource(name = "ChannelStoreyService")
    public void setChannelStoreyService(ChannelStoreyService channelStoreyService) {
        this.channelStoreyService = channelStoreyService;
    }

}
