/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.controller;

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
import com.ningpai.channel.service.ChannelAdverService;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.publicpackage.InfoImageManagePublic;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.TempService;
import com.ningpai.util.PageBean;
import com.ningpai.util.UploadUtil;

/**
 * 控制器-模板广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月5日上午9:46:09
 */
@Controller
public class TempAdverController {

    // session中保存的登录用户的id
    private static final String LOGINUSERID = "loginUserId";

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    public static final String QUERYTEMPADVERBYPAGEBEAN_HTM = "queryTempAdverByPageBean.htm?tempId=";
    public static final String ADVERTYPE = "&adverType=";
    public static final String ATID = "&atId=";
    public static final String LOGGERINFO1 = ",用户名:";

    /* 业务逻辑层依赖 */
    private TempService tempService;

    private ChannelAdverService channelAdverService;

    @Resource(name = "InfoImageManagePublic")
    private InfoImageManagePublic infoImageManagePublic;

    /**
     * 分页查看模板广告设置
     * 
     * @param pb
     * @param tempId
     * @param atId
     * @param adverType
     * @return
     */
    @RequestMapping("/queryTempAdverByPageBean")
    public ModelAndView queryTempAdverByPageBean(PageBean pb, Long tempId, Long atId, Long adverType) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 模板参数
        SysTemp temp = this.tempService.getSystempById(tempId);
        map.put("temp", temp);
        // 设置分页参数
        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, tempId, null, null, atId, adverType, null, null));
        // 设置参数
        map.put("atId", atId);
        // 设置类型
        map.put("adverType", adverType);
        // 返回
        return new ModelAndView("jsp/temp/temp_adver_list", "map", map);
    }

    /**
     * 分页查看模板广告设置（新）
     *
     * @param pb
     *            分页参数
     * @param atId
     * @param adverType
     * @return
     */
    @RequestMapping("/querytempadverbypagebeanajax")
    @ResponseBody
    public Map<String, Object> queryTempAdverByPageBeanAjax(PageBean pb, Long tempId, Long atId, Long adverType, Long storeyTagId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置列表

        map.put("pb", channelAdverService.selectchannelAdverByParam(pb, null, tempId, null, storeyTagId, atId, adverType, null, null));
        // 设置类型
        map.put("atId", atId);
        return map;
    }

    /**
     * 查看模板广告
     * 
     * @param channelAdverId
     * @param tempId
     * @param atId
     * @param adverType
     * @return
     */
    @RequestMapping("/showTempAdver")
    public ModelAndView showTempAdver(Long channelAdverId, Long tempId, Long atId, Long adverType) {
        Map<String, Object> map = new HashMap<String, Object>();
        SysTemp temp = this.tempService.getSystempById(tempId);
        map.put("temp", temp);
        map.put("atId", atId);
        map.put("adverType", adverType);
        if (null != channelAdverId) {
            ChannelAdver channelAdver = this.channelAdverService.selectByPrimaryKey(channelAdverId);
            map.put("channelAdver", channelAdver);
        }
        return new ModelAndView("jsp/temp/show_temp_adver", "map", map);
    }

    /**
     * 查看模板广告（新）
     *
     * @param channelAdverId
     * @return
     */
    @RequestMapping("/showtempadverajax")
    @ResponseBody
    public ChannelAdver showTempAdverAjax(Long channelAdverId) {
        // 根据id查询模板广告
        return this.channelAdverService.selectByPrimaryKey(channelAdverId);
    }

    /**
     * 添加模板广告
     * 
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/createTempAdver")
    public ModelAndView createTempAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回
            return new ModelAndView(new RedirectView(QUERYTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId() + ADVERTYPE
                    + channelAdver.getAdverType() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置操作id
        channelAdver.setCreateUserId(loginUserId);
        // 保存信息
        channelAdverService.saveChannelAdver(channelAdver);
        // 获取当前用户
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "添加模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId() + ADVERTYPE + channelAdver.getAdverType()
                + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 添加模板广告(新)
     *
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/createtempadverajax")
    @ResponseBody
    public int createTempAdverAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult, Integer width,
            Integer height) {
        // 判断是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 操作人
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        // 添加模板广告
        OperaLogUtil.addOperaLog(request, customerName, "添加模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 设置当前id
        channelAdver.setCreateUserId(loginUserId);
        // 返回插入结果
        // 0：失败 1：成功
        return channelAdverService.saveChannelAdver(channelAdver);
    }

    /**
     * 修改模板广告
     * 
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/updateTempAdver")
    public ModelAndView updateTempAdver(MultipartHttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult,
            Integer width, Integer height) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId() + ADVERTYPE
                    + channelAdver.getAdverType() + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 图片控件
        MultipartFile muFile = request.getFile("imgSrc");
        if (null != muFile && muFile.getSize() > 0) {
            channelAdver.setAdverPath(UploadUtil.uploadFileOne(muFile, request));
            // 保存图片信息，以供以后选择
            infoImageManagePublic.saveImage(channelAdver.getAdverPath());
        }
        // 设置登录id
        channelAdver.setUpdateUserId(loginUserId);
        // 修改模板广告
        channelAdverService.updateChannelAdver(channelAdver);
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "修改模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        return new ModelAndView(new RedirectView(QUERYTEMPADVERBYPAGEBEAN_HTM + channelAdver.getTempId() + ATID + channelAdver.getAtId() + ADVERTYPE + channelAdver.getAdverType()
                + ConstantUtil.CSRF + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改模板广告(新)
     *
     * @param request
     * @param channelAdver
     * @return
     */
    @RequestMapping("/updatetempadverajax")
    @ResponseBody
    public int updateTempAdverAjax(HttpServletRequest request, HttpServletResponse response, @Valid ChannelAdver channelAdver, BindingResult bindingResult, Integer width,
            Integer height) {
        // 判断参数是否符合规范
        if (bindingResult.hasErrors()) {
            // 返回结果
            return 0;
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "修改模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        // 设置登录id
        channelAdver.setUpdateUserId(loginUserId);
        // 修改模板广告
        // 返回结果 1：成功 0：失败
        return channelAdverService.updateChannelAdver(channelAdver);
    }

    /**
     * 删除模板广告（新）
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deletetempadverajax")
    @ResponseBody
    public void deleteTempAdverAjax(HttpServletRequest request, HttpServletResponse response) {
        // 获取登录参数
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 获取需要删除的参数
        String[] adverIds = request.getParameterValues("adverId");
        for (int i = 0; i < adverIds.length; i++) {
            // 获取删除id
            Long id = Long.valueOf(adverIds[i]);
            // 删除
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        // 获取用户名
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 记录日志
        OperaLogUtil.addOperaLog(request, customerName, "删除模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    /**
     * 删除模板广告
     * 
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/deleteTempAdver")
    public void deleteTempAdver(HttpServletRequest request, HttpServletResponse response) {
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        String[] adverIds = request.getParameterValues("adverIds[]");
        for (int i = 0; i < adverIds.length; i++) {
            Long id = Long.valueOf(adverIds[i]);
            channelAdverService.deleteChannelAdver(id, loginUserId);
        }
        String customerName = (String) request.getSession().getAttribute(NAME);
        OperaLogUtil.addOperaLog(request, customerName, "删除模板广告", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
    }

    public TempService getTempService() {
        return tempService;
    }

    @Resource(name = "TempService")
    public void setTempService(TempService tempService) {
        this.tempService = tempService;
    }

    public ChannelAdverService getChannelAdverService() {
        return channelAdverService;
    }

    @Resource(name = "ChannelAdverService")
    public void setChannelAdverService(ChannelAdverService channelAdverService) {
        this.channelAdverService = channelAdverService;
    }

}
