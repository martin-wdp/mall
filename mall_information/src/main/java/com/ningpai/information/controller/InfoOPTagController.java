/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.service.InfoOPTagService;
import com.ningpai.information.service.InformationOnePageService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.service.InfoSysTempService;
import com.ningpai.util.MenuSession;
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
import java.util.List;

/**
 * 控制器-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月17日 18:55:38
 * @version
 */
@Controller
public class InfoOPTagController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InfoOPTagController.class);

    public static final String NAME = "name";

    public static final String OPERAPATH = "operaPath";

    private static final String QUERYINFOOPTAG_HTM = "queryInfoOPTag.htm?CSRFToken=";
    private static final String LOGGERINFO1 = ",用户名:";
    private static final String LOGGERINFO2 = "==============================";

    /** SERVICE-单页标签 */
    private InfoOPTagService infoOPTagService;

    /** 频道模板设置业务类 */
    private InfoSysTempService channelSysTempService;

    /** 资讯单页Service */
    @Resource(name = "InformationOnePageService")
    private InformationOnePageService infoOnePageService;

    /**
     * 查询单页标签
     * 
     * @param request
     * @param pb
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryInfoOPTag")
    public ModelAndView queryInfoOPTag(HttpServletRequest request, PageBean pb, HttpServletResponse response) {
        /** 获取模板列表 */
        MenuSession.sessionMenu(request);
        /** 获取所有模板设置对象集合 */
        List<SysTemp> sysTempList = channelSysTempService.queryAllSystemp();
        /** 返回结果 */
        return new ModelAndView("jsp/information/infoOPTag", "infoOPTagList", infoOPTagService.findAllTag()).addObject("sysTempList", sysTempList);
    }

    /**
     * 添加单页标签
     * 
     * @param infoOPTag
     * @param bindingResult
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/addInfoOPTag")
    public ModelAndView addInfoOPTag(@Valid InfoOPTag infoOPTag, BindingResult bindingResult, HttpServletRequest request) {
        /** 如果验证不通过则重定向到查询单页标签页面 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOOPTAG_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("==============================修改单页标签==============================");
        try {
            /** 调用Service层添加单页标签 */
            this.infoOPTagService.createInfoOPTag(infoOPTag);
            /** 获取存储在session中的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录日志 */
            OperaLogUtil.addOperaLog(request, customerName, "添加单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================修改单页标签失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOOPTAG_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 修改单页标签
     * 
     * @param infoOPTag
     * @param bindingResult
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/updateInfoOPTag")
    public ModelAndView updateInfoOPTag(@Valid InfoOPTag infoOPTag, BindingResult bindingResult, HttpServletRequest request) {
        /** 如果验证不通过则重定向到查询单页标签页面 */
        if (bindingResult.hasErrors()) {
            return new ModelAndView(new RedirectView(QUERYINFOOPTAG_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
        }
        LOGGER.debug("==============================修改单页标签==============================");
        try {
            /** 调用Service层单页标签更新方法 */
            this.infoOPTagService.updateInfoOPTag(infoOPTag);
            /** 获取session中的客户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录日志 */
            OperaLogUtil.addOperaLog(request, customerName, "修改单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================修改单页标签失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOOPTAG_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 删除单页标签
     * 
     * @param infoOPTagId
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/delInfoOPTag")
    public ModelAndView delInfoOPTag(Long infoOPTagId, HttpServletRequest request) {
        LOGGER.debug("==============================删除单页标签==============================");
        try {
            /** 调用Service层单页标签删除方法 */
            this.infoOPTagService.deleteInfoOPTagById(infoOPTagId);
            /** 获取存储在session里的用户名称 */
            String customerName = (String) request.getSession().getAttribute(NAME);
            /** 记录日志 */
            OperaLogUtil.addOperaLog(request, customerName, "删除单页标签", request.getSession().getAttribute(OPERAPATH) + LOGGERINFO1 + customerName);
        } catch (Exception e) {
            LOGGER.error("==============================删除单页标签失败：",e);
        }
        /** 返回结果 */
        return new ModelAndView(new RedirectView(QUERYINFOOPTAG_HTM + request.getParameter(ConstantUtil.CSRFTOKEN)));
    }

    /**
     * 验证单页标签是否可删除
     * 
     * @param infoOPTagId
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkDelTag")
    public boolean checkDelTag(Long infoOPTagId) {
        /**
         * 调用Service层方法 如果方法返回结果大于0就返回false给页面调用者 如果service层返回结果小于0就返回true给页面调用者
         */
        return this.infoOnePageService.selectInfoOPCountByTag(infoOPTagId) > 0 ? false : true;
    }

    public InfoOPTagService getInfoOPTagService() {
        return infoOPTagService;
    }

    @Resource(name = "InfoOPTagService")
    public void setInfoOPTagService(InfoOPTagService infoOPTagService) {
        this.infoOPTagService = infoOPTagService;
    }

    public InfoSysTempService getChannelSysTempService() {
        return channelSysTempService;
    }

    @Resource(name = "InfoSysTempService")
    public void setChannelSysTempService(InfoSysTempService channelSysTempService) {
        this.channelSysTempService = channelSysTempService;
    }

}
