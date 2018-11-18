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

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.channel.service.ISysDictionaryBiz;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:25:56
 */
@Controller
public class ChannelAdverController {

    private static final String LOGINUSERID = "loginUserId";
    private static final String ATID = "atId";
    private static final String ADVERTYPE = "adverType";
    public static final String NAME = "name";
    public static final String OPERAPATH = "operaPath";
    private static final String QUERYCHANNELADVERBYPAGEBEAN_HTM = "queryChannelAdverByPageBean.htm?CSRFToken=";
    private static final String CHANNELID = "&channelId=";
    private static final String LOGGERINFO1 = ",用户名:";

    /** 频道业务接口 */
    private ChannelService channelService;
    /** 广告业务接口 */
    private ChannelAdverService channelAdverService;
    /** 系统字典业务接口 */
    private ISysDictionaryBiz sysDicBiz;
    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看频道广告设置
     * 
     * @param pb
     *            分页参数
     * @param channelId
     *            频道页id
     * @param floorId
     *            楼层id
     * @param floorTagId
     *            楼层标签id
     * @param atId
     *            id
     * @param adverType
     *            类型
     * @param request
     *            请求参数
     * @author Tiancl
     * @return ModelAndView
     */
    @RequestMapping("/queryChannelAdverByPageBean")
    @ResponseBody
    public Map queryChannelAdverByPageBean(PageBean pb, Long channelId, Long floorId, Long floorTagId, Long atId, Long adverType, HttpServletRequest request) {
        Map<String, Object> maps = new HashMap<>();
        Channel channel = this.channelService.findChannelByID(channelId);
        PageBean pageBean = channelAdverService.selectchannelAdverByParam(pb, channel.getChannelId(), null, null, null, atId, adverType, null, null);
        maps.put("pb", pageBean);
        maps.put("channel", channel);
        maps.put(ATID, atId);
        maps.put(ADVERTYPE, adverType);
        // 设置分页参数的url
        pb.setUrl(QUERYCHANNELADVERBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN) + CHANNELID + channelId);
        return maps;
    }

    /**
     * 查看频道广告
     * 
     * @param channelId
     *            频道页id
     * @param atId
     *            id
     * @param adverType
     *            类型
     * @param channelAdverId
     *            频道广告id
     * @return ModelAndView
     */
    @RequestMapping("/showChannelAdver")
    public ModelAndView showChannelAdver(Long channelAdverId, Long channelId, Long atId, Long adverType) {
        // 查询频道信息
        Channel channel = this.channelService.findChannelByID(channelId);
        // 参数
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channel", channel);
        map.put(ATID, atId);
        map.put(ADVERTYPE, adverType);
        // 判断是否为空
        if (null != channelAdverId) {
            // 频道广告信息
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        // 返回页面
        return new ModelAndView("jsp/channel/showChannelAdver", "map", map);
    }

    /**
     * 添加频道广告
     * 
     * @param request
     *            请求参数
     * @param response
     *            请求参数
     * @param channelAdver
     *            添加信息
     * @param bindingResult
     *            请求参数
     * @param width
     *            宽
     * @param height
     *            高
     * @return ModelAndView
     */
    @RequestMapping("/createChannelAdver")
    public ModelAndView createChannelAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回
            new ModelAndView(new RedirectView(QUERYCHANNELADVERBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN) + CHANNELID + channelAdver.getChannelId())).addObject(
                    ATID, channelAdver.getAtId()).addObject(ADVERTYPE, channelAdver.getAdverType());
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取图片
        MultipartFile muFile = request.getFile("imgSrc");
        // 查看图片
        if (null != muFile && muFile.getSize() > 0) {
            // 设置图片地址
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置创建人id
        channelAdver.setCreateUserId(loginUserId);
        // 添加
        channelAdverService.saveChannelAdver(channelAdver);
        // 日志操作
        // 记录用户名
        // 记录用户操作
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "添加频道广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCHANNELADVERBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN) + CHANNELID + channelAdver.getChannelId()))
                .addObject(ATID, channelAdver.getAtId()).addObject(ADVERTYPE, channelAdver.getAdverType());
    }

    /**
     * 修改频道广告
     * 
     * @param request
     *            请求参数
     * @param channelAdver
     *            修改的信息
     * @param bindingResult
     *            图片上传
     * @return ModelAndView
     */
    @RequestMapping("/updateChannelAdver")
    public ModelAndView updateChannelAdver(MultipartHttpServletRequest request, @Valid ChannelAdver channelAdver, BindingResult bindingResult) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回到页面
            new ModelAndView(new RedirectView(QUERYCHANNELADVERBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN) + CHANNELID + channelAdver.getChannelId())).addObject(
                    ATID, channelAdver.getAtId()).addObject(ADVERTYPE, channelAdver.getAdverType());
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取图片
        MultipartFile muFile = request.getFile("imgSrc");
        // 判断是否为空
        if (null != muFile && muFile.getSize() > 0) {
            // 设置图片地址
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置修改人id
        channelAdver.setUpdateUserId(loginUserId);
        // 修改信息
        channelAdverService.updateChannelAdver(channelAdver);
        // 日志操作
        // 记录用户名
        // 记录用户操作
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改频道广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYCHANNELADVERBYPAGEBEAN_HTM + request.getParameter(ConstantUtil.CSRFTOKEN) + CHANNELID + channelAdver.getChannelId()))
                .addObject(ATID, channelAdver.getAtId()).addObject(ADVERTYPE, channelAdver.getAdverType());
    }

    /**
     * 删除频道广告 1、获取id 2、进行删除 3、记录日志
     * 
     * @param request
     *            请求参数
     */
    @RequestMapping("/deleteChannelAdver")
    public void deleteChannelAdver(HttpServletRequest request) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取删除
        String[] adverIds = request.getParameterValues("adverIds[]");
        for (int i = 0; i < adverIds.length; i++) {
            Long id = Long.valueOf(adverIds[i]);
            // 进行删除
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        // 日志操作
        // 记录用户名
        // 记录用户操作
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除频道广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public ChannelService getChannelService() {
        return channelService;
    }

    /*
     * spring注入
     */
    @Resource(name = "ChannelService")
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    /*
     * spring注入
     */
    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

    public ISysDictionaryBiz getSysDicBiz() {
        return sysDicBiz;
    }

    /*
     * spring注入
     */
    @Resource(name = "channelSysDictionaryBizImpl")
    public void setSysDicBiz(ISysDictionaryBiz sysDicBiz) {
        this.sysDicBiz = sysDicBiz;
    }

}
