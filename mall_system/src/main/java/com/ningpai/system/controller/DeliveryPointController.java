/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.DeliveryPoint;
import com.ningpai.system.service.DeliveryPointService;
import com.ningpai.util.PageBean;

/**
 * 控制器-自提点
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月18日上午11:26:47
 */
@Controller
public class DeliveryPointController {

    /** logger日志 */
    private static final Logger LOGGER = Logger.getLogger(DeliveryPointController.class);

    /** 自提点注入 */
    @Resource(name = "DeliveryPointService")
    private DeliveryPointService deliveryPointService;

    // 常量LOGINUSERID
    private static final String LOGINUSERID = "loginUserId";

    /** 用户名称 */
    private static final String NAME = "name";

    /** "operaPath" */
    private static final String OPERAPATH = "operaPath";

    private static final String REDIRECT = "queryDeliveryPointByPb.htm";

    /**
     * 根据区县ID分页查询自提点
     * 
     * @param pb
     * @param districtId
     * @return
     */
    @RequestMapping("/queryDeliveryPointByPb")
    public ModelAndView queryDeliveryPointByPb(PageBean pb, Long districtId) {
        return new ModelAndView("jsp/system/deliverypoint/deliverypoint_list", "pb", this.deliveryPointService.selectAllDeliveryPointByPb(pb, districtId));
    }

    /**
     * 根据ID查看自提点
     * 
     * @param deliveryPointId
     * @return
     */
    @RequestMapping("/showDeliveryPoint")
    public ModelAndView showDeliveryPoint(Long deliveryPointId) {
        // 初始化mav
        ModelAndView mav = new ModelAndView();
        // 根据id获取自提点
        DeliveryPoint dp = this.deliveryPointService.getDeliveryPoint(deliveryPointId);
        if (null != dp) {
            mav.addObject("deliveryPoint", dp);
        }
        mav.setViewName("jsp/system/deliverypoint/show_deliverypoint");
        return mav;
    }

    /**
     * 根据ID查看自提点
     * 
     * @param deliveryPointId
     * @return
     */
    @RequestMapping("/selectDeliveryPointById")
    @ResponseBody
    public DeliveryPoint selectDeliveryPointById(Long deliveryPointId) {
        return this.deliveryPointService.getDeliveryPoint(deliveryPointId);
    }

    /**
     * 添加自提点
     * 
     * @param deliveryPoint
     * @param request
     * @return
     */
    @RequestMapping("/createDeliveryPoint")
    public ModelAndView createDeliveryPoint(@Valid DeliveryPoint deliveryPoint, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            // 日志输出
            LOGGER.debug("添加自提点属性错误");
            throw new RuntimeException("添加自提点属性错误");
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 登录id为null时，设置为1
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 添加人
        deliveryPoint.setCreateUserId(loginUserId);
        // 添加自提点
        this.deliveryPointService.saveDeliveryPoint(deliveryPoint);
        // 会员名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "添加自提点", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改自提点
     * 
     * @param deliveryPoint
     * @param request
     * @return
     */
    @RequestMapping("/updateDeliveryPoint")
    public ModelAndView updateDeliveryPoint(@Valid DeliveryPoint deliveryPoint, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            LOGGER.debug("修改自提点属性错误");
            throw new RuntimeException("修改自提点属性错误");
        }
        // 获取登录id
        Long loginUserId = (Long) request.getSession().getAttribute(LOGINUSERID);
        // 登录id为null时，设置为1
        if (null == loginUserId) {
            loginUserId = 1L;
        }
        // 设置修改人
        deliveryPoint.setUpdateUserId(loginUserId);
        // 修改自提点
        this.deliveryPointService.updateDeliveryPoint(deliveryPoint);
        // 会员名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "修改自提点", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 删除自提点
     * 
     * @param deliveryPointId
     * @param request
     * @return
     */
    @RequestMapping("/deleteDeliveryPoint")
    public ModelAndView deleteDeliveryPoint(Long deliveryPointId, HttpServletRequest request) {
        // 根据id删除自提点
        this.deliveryPointService.deleteDeliveryPoint(deliveryPointId);
        // 登录名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "删除自提点", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 批量删除自提点
     * 
     * @param dpid
     * @param request
     * @return
     */
    @RequestMapping("/batchDelDeliveryPoint")
    public ModelAndView batchDelDeliveryPoint(Long[] dpid, HttpServletRequest request) {
        // 批量删除自提点
        this.deliveryPointService.batchDelDeliveryPoint(dpid);
        // 登录名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "批量删除自提点", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView(REDIRECT));
    }

    /**
     * 修改自提点启用状态
     * 
     * @param deliveryPointId
     * @param request
     * @return
     */
    @RequestMapping("changeDelDeliveryPointUserdStatus")
    public ModelAndView changeDelDeliveryPointUserdStatus(Long deliveryPointId, HttpServletRequest request) {
        // 修改自提点启用状态
        this.deliveryPointService.changeUserdStatus(deliveryPointId);
        // 登录名称
        String customerName = (String) request.getSession().getAttribute(NAME);
        // 日志输出
        OperaLogUtil.addOperaLog(request, customerName, "批量删除自提点", request.getSession().getAttribute(OPERAPATH).toString());
        return new ModelAndView(new RedirectView(REDIRECT));
    }
}
