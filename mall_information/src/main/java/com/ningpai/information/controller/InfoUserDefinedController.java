/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.bean.InfoUserDefined;
import com.ningpai.information.service.InfoUserDefinedService;
import com.ningpai.logger.util.OperaLogUtil;

/**
 * 控制器-文章自定义属性
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月17日 18:55:38
 * @version
 */
@Controller
public class InfoUserDefinedController {

    /**
     * 用户名称
     */
    public static final String NAME = "name";

    /**
     * "operaPath"
     */
    public static final String OPERAPATH = "operaPath";

    public static final String QUERYINFOUSERDEFINED_HTM = "queryInfoUserDefined.htm?CSRFToken=";

    public static final String LOGGERINFO1 = ",用户名:";

    /** SERVICE-文章自定义属性 */
    private InfoUserDefinedService infoUDService;

    /**
     * 查询文章自定义属性
     */
    @RequestMapping(value = "/queryInfoUserDefined")
    public ModelAndView queryInfoUserDefined(HttpServletRequest request, HttpServletResponse response) {
        /** 返回结果 */
        return new ModelAndView("jsp/information/infoUserDefined", "infoUDList", infoUDService.findAllUserDefined());
    }

    /**
     * 添加文章自定义属性
     * 
     * @return ModelAndView
     */
    @RequestMapping("/addInfoUserDefined")
    public ModelAndView addInfoUserDefined(@Valid InfoUserDefined infoUD, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOUSERDEFINED_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 执行添加方法 */
        this.infoUDService.createUserDefined(infoUD);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "添加文章自定义属性", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOUSERDEFINED_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改文章自定义属性
     * 
     * @return ModelAndView
     */
    @RequestMapping("/updateInfoUserDefined")
    public ModelAndView updateInfoUserDefined(@Valid InfoUserDefined infoUD, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOUSERDEFINED_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        /** 执行修改方法 */
        this.infoUDService.updateUserDefined(infoUD);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "修改文章自定义属性", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOUSERDEFINED_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除文章自定义属性
     * 
     * @return ModelAndView
     */
    @RequestMapping("/delInfoUserDefined")
    public ModelAndView delInfoUserDefined(Long infoUDId, HttpServletRequest request) {
        /** 执行删除方法 */
        this.infoUDService.deleteUserDefinedById(infoUDId);
        /** 获取存储在session中的用户名称 */
        String customerName = (String) request.getSession().getAttribute(NAME);
        /** 记录操作日志 */
        OperaLogUtil.addOperaLog(request, customerName, "删除文章自定义属性", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOUSERDEFINED_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    public InfoUserDefinedService getInfoUDService() {
        return infoUDService;
    }

    @Resource(name = "InfoUserDefinedService")
    public void setInfoUDService(InfoUserDefinedService infoUDService) {
        this.infoUDService = infoUDService;
    }

}
