/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.controller;

import java.io.UnsupportedEncodingException;

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

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.mobile.bean.MobSinglepageMark;
import com.ningpai.mobile.service.MobSinglepageMarkService;
import com.ningpai.mobile.service.MobSinglepageService;
import com.ningpai.util.MyLogger;

/**
 * 移动版单页标签控制层
 * 
 * @author zhangsl
 * @since 2014年11月24日 上午11:02:04
 * @version 0.0.1
 */
@Controller
public class MobSinglepageMarkController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(MobSinglepageMarkController.class);

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String LOGINUSERID = "loginUserId";

    private static final String SHOWALLMOBMARKINFO_HTM = "showAllMobMarkInfo.htm?CSRFToken=";

    private static final String LOGGERINFO1 = ",用户名：";

    /** 移动版单页标签service层接口 */
    @Resource(name = "MobSinglepageMarkService")
    private MobSinglepageMarkService mobSinglepageMarkService;

    /** 移动版单页服务层接口 */
    @Resource(name = "MobSinglepageService")
    private MobSinglepageService mobSinglepageService;

    /**
     * 显示所有移动版单页标签列表信息
     * 
     * @return
     */
    @RequestMapping("/showAllMobMarkInfo")
    public ModelAndView showAllMobMarkInfo() {
        /** 返回结果 查询删除状态为不删除的所有移动版单页标签信息列表 */
        return new ModelAndView("jsp/mobile/mobSingleMark", "mobMarkList", mobSinglepageMarkService.queryAllMarkInfoByDel());
    }

    /**
     * 添加移动版单页标签信息
     * 
     * @param mobSinglepageMark
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addMobMarkInfo")
    public ModelAndView addMobMarkInfo(@Valid MobSinglepageMark mobSinglepageMark, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SHOWALLMOBMARKINFO_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("========添加移动版单页标签============");
        /** 非空验证 标示名称 */
        if (null != mobSinglepageMark.getName()) {
            LOGGER.info("添加移动版单页标签信息,添加的标签名称为：" + mobSinglepageMark.getName());
        }
        /** 获取session存储的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            /** 设置创建者id为当前登录id */
            mobSinglepageMark.setCreateUserId(loginUserId);
            /** 执行添加方法 */
            mobSinglepageMarkService.insertSelective(mobSinglepageMark);
            /** 获取session存储的用户名 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "添加移动版单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        } catch (Exception e) {
            LOGGER.error("============添加移动版单页标签失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(SHOWALLMOBMARKINFO_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));

    }

    /**
     * 更新移动版单页标签信息
     * 
     * @param mobSinglepageMark
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateMobMarkInfo")
    public ModelAndView updateMobMarkInfo(@Valid MobSinglepageMark mobSinglepageMark, HttpServletRequest request, HttpServletResponse response, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(SHOWALLMOBMARKINFO_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }

        LOGGER.debug("========更新移动版单页标签============");
        /** 非空验证 标示名称 */
        if (null != mobSinglepageMark.getName()) {
            LOGGER.info("更新移动版单页标签信息,更新的标签名称为：" + mobSinglepageMark.getName());
        }
        /** 获取session存储的登录id */
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        try {
            /** 设置更新用户id为登录id */
            mobSinglepageMark.setUpdateUserId(loginUserId);
            /** 执行更新方法 */
            mobSinglepageMarkService.updateMobMarkById(mobSinglepageMark);
            /** 获取session存储的用户名 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "更新移动版单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("============更新移动版单页标签失败：" ,e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(SHOWALLMOBMARKINFO_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 逻辑删除移动版单页标签信息
     * 
     * @param request
     * @param response
     * @param markId
     * @return
     */
    @RequestMapping("/deleteMobMarkInfo")
    public ModelAndView deleteMobMarkInfo(HttpServletRequest request, HttpServletResponse response, Long markId) {
        LOGGER.debug("========删除移动版单页标签============");
        /** 非空验证 移动版单页主键ID */
        if (null != markId) {
            LOGGER.info("逻辑删除移动版单页标签信息,删除的标签名称为：" + mobSinglepageMarkService.selectMobMarkById(markId).getName());
        }
        try {
            /** 执行更新方法 */
            mobSinglepageMarkService.updateDelStatus(markId);
            /** 获取session存储的用户名 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 执行操作日志 */
            OperaLogUtil.addOperaLog(request, customerName, "删除移动版单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);

        } catch (Exception e) {
            LOGGER.error("============删除移动版单页标签失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(SHOWALLMOBMARKINFO_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));

    }

    /**
     * 检验markId是否被引用 引用则返回 false 未引用返回true
     * 
     * @param markId
     * @return
     */
    @RequestMapping("/checkDelExist")
    @ResponseBody
    public boolean checkDelExist(long markId) {
        /** 根据MarkId查询符合条件的总条数 */
        return mobSinglepageService.queryCountByMarkId(markId) > 0 ? false : true;
    }

    /**
     * 检验移动版单页标签name是否已存在
     * 
     * @param request
     * @param response
     * @return 返回false 存在 true 不存在可用
     * @throws java.io.UnsupportedEncodingException
     */
    @RequestMapping("/checkNameExist")
    @ResponseBody
    public boolean checkNameExist(HttpServletRequest request, HttpServletResponse response) {
        String name = "";
        try {
            name = new String(request.getParameter("name").getBytes("ISO-8859-1"), ConstantUtil.UTF);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("检验移动版单页标签name是否已存在，转码失败！", e);
        }
        /** 返回结果 验证name是否存在 */
        return mobSinglepageMarkService.checkNameExist(name) > 0 ? false : true;
    }

}
