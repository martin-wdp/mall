/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.controller;

import com.ningpai.customer.bean.InsideLetter;
import com.ningpai.customer.service.InsideLetterServiceMapper;
import com.ningpai.customer.vo.InsideLetterVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 站内信控制器
 * 
 * @Description 站内信控制器
 * @author Songhl
 * @since 2015年8月27日 20:42:21
 */
@Controller
public class InsideLettersController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(InsideLettersController.class);

    private static final String LOGOERINFO = "】-->用户名：";

    /**
     * spring注解
     */
    private InsideLetterServiceMapper insideLetterService;

    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 跳转到站内信
     *
     * @param request
     * @return
     */
    @RequestMapping("/customer/insideletter")
    public ModelAndView showInsideLetter(HttpServletRequest request, PageBean pb) {
        ModelAndView mav = null;
        pb.setUrl(CustomerConstantStr.CUSTOMER + "/insideletter");
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (checkLoginStatus(request)) {
                resultMap.put(CustomerConstantStr.CUSTOMERID, (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
                // 查询全部站内信
                mav = new ModelAndView("customer/newinsideletter").addObject(CustomerConstantStr.PB, insideLetterService.queryInsideLetter(resultMap, pb));

            } else {
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT).addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + "/insideletter.html");
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 标记为已读
     *
     * @param request
     * @return
     */
    @RequestMapping("/customer/readletter")
    @ResponseBody
    public int readedLetter(HttpServletRequest request, @Valid InsideLetterVo inside) {
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (customerId != null) {
            // 当前登录成功的会员信息
            CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
            // 用户编号
            inside.setCustomerId(customerId);
            // 非空验证 站内信标题
            if (null != inside.getLetterTitle()) {
                // 操作日志
                OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "更改站内信状态",
                        "更改站内信状态-->站内信标题【" + inside.getLetterTitle() + LOGOERINFO + customerAllInfo.getCustomerUsername());
                // 日志记录
                LOGGER.info("标题为【" + inside.getLetterTitle() + "】的站内信标记已读！");
            }
            // 标记为已读
            return insideLetterService.readedLetter(inside);
        }
        return 0;
    }

    /**
     * 是否为已读
     *
     * @param request
     * @return
     */
    @RequestMapping("/customer/letterisread")
    @ResponseBody
    public int letterIsRead(HttpServletRequest request, Long letterId) {
        // 根据ID获取站内信详细信息
        InsideLetter insideLetter = insideLetterService.selectByPrimaryKey(letterId);
        // 当前登录的会员ID
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customer = customerServiceInterface.selectByPrimaryKey(customerId);
        // 非空验证 站内信标题
        if (null != insideLetter.getLetterTitle()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "更改站内信状态",
                    "更改站内信状态-->站内信标题【" + insideLetter.getLetterTitle() + LOGOERINFO + customer.getCustomerUsername());
            // 日志记录
            LOGGER.info("判断标题为【" + insideLetter.getLetterTitle() + "】的站内信是否已读！");
        }
        Map<String, Object> resultMap = new HashMap<String, Object>();

        resultMap.put("customerId", customerId);
        resultMap.put("letterId", letterId);
        // 是否已读
        return Integer.parseInt(insideLetterService.letterIsRead(resultMap) + "");
    }

    /**
     * 未读删除
     */
    @RequestMapping("/customer/deleteletterno")
    @ResponseBody
    public int deleteLetter(HttpServletRequest request, InsideLetterVo inside) {

        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customer = customerServiceInterface.selectByPrimaryKey(customerId);
        // 会员编号
        inside.setCustomerId(customerId);
        // 删除
        int result = insideLetterService.deleteLetterNo(inside);
        // 非空验证 站内信标题
        if (null != inside.getLetterTitle() && 1 == result) {
            // 操作日志
            OperaLogUtil
                    .addOperaLog(request, customer.getCustomerUsername(), "未读站内信删除", "未读站内信删除-->站内信标题【" + inside.getLetterTitle() + LOGOERINFO + customer.getCustomerUsername());
            // 日志记录
            LOGGER.info("删除标题为【" + inside.getLetterTitle() + "】的站内信成功！");
        }
        return result;
    }

    /**
     * 删除
     */
    @RequestMapping("/customer/deleteletter")
    @ResponseBody
    public int deleteLetter(HttpServletRequest request, Long relaId, Long letterId) {
        // 根据ID获取站内信详细信息
        InsideLetter insideLetter = insideLetterService.selectByPrimaryKey(letterId);
        // 当前登录会员id
        Long customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 当前登录成功的会员信息
        CustomerAllInfo customer = customerServiceInterface.selectByPrimaryKey(customerId);
        // 非空验证 站内信标题
        if (null != insideLetter.getLetterTitle()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "站内信删除",
                    "站内信删除-->站内信标题【" + insideLetter.getLetterTitle() + LOGOERINFO + customer.getCustomerUsername());

            // 日志记录
            LOGGER.info("删除标题为【" + insideLetter.getLetterTitle() + "】的站内信成功！");
        }
        return insideLetterService.deleteLetter(relaId);
    }

    /**
     * 根据letterId,customerId删除
     */
    @RequestMapping("/customer/deletebylcid")
    @ResponseBody
    public int deleteByLetterIdCustId(HttpServletRequest request, Long letterId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = null;
        customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        // 根据ID获取站内信详细信息
        InsideLetter insideLetter = insideLetterService.selectByPrimaryKey(letterId);
        // 根据会员ID获取会员详细信息
        CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
        resultMap.put("customerId", customerId);
        resultMap.put("letterId", letterId);
        // 根据会员Id和letteId删除
        int count = Integer.parseInt(insideLetterService.deleteByLetterIdCustId(resultMap) + "");
        // 是否删除成功 站内信标题是否为空 会员用户名是否为空
        if (1 == count && null != insideLetter.getLetterTitle() && null != customerAllInfo.getCustomerUsername()) {
            // 操作日志
            OperaLogUtil.addOperaLog(request, customerAllInfo.getCustomerUsername(), "删除站内信",
                    "删除站内信-->站内信标题【" + insideLetter.getLetterTitle() + LOGOERINFO + customerAllInfo.getCustomerUsername());
            LOGGER.info("删除会员【" + customerAllInfo.getCustomerUsername() + "】,标题为【" + insideLetter.getLetterTitle() + "】的站内信！");
        }
        return count;
    }

    /**
     * 检查是否登录
     * 
     * @param request
     *            请求
     * @return
     */
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute("cust") == null) {
            return false;
        }
        return true;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public InsideLetterServiceMapper getInsideLetterService() {
        return insideLetterService;
    }

    @Resource(name = "insideLetterServiceMapper")
    public void setInsideLetterService(InsideLetterServiceMapper insideLetterService) {
        this.insideLetterService = insideLetterService;
    }
}
