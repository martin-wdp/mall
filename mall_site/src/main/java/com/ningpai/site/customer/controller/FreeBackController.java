/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.controller;

import com.ningpai.common.util.FeedBackEmail;
import com.ningpai.customer.vo.FreeBackVo;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerAllInfo;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
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
 * 用户反馈控制层
 * 
 * @author jiping
 * @since 2014年7月28日 上午11:42:29
 * @version 0.0.1
 */
@Controller
public class FreeBackController {
    /**
     * spring 注解 会员service
     */
    private CustomerServiceInterface customerServiceInterface;
    /**
     * 站点设置服务层接口
     */
    private BasicSetService basicSetService;
    /**
     * 用户反馈邮件工具类
     */
    private FeedBackEmail feedBacKEmail;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(FreeBackController.class);

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 跳转用户反馈
     * 
     * @param request
     * @return
     */
    @RequestMapping("customer/feedback")
    public ModelAndView feedback(HttpServletRequest request) {
        // 反馈中心
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        Long customerId = null;
        try {
            // 验证登录
            if (checkLoginStatus(request)) {
                customerId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
                // 根据主键获取单个的会员对象
                CustomerAllInfo customerAllInfo = customerServiceInterface.selectByPrimaryKey(customerId);
                // 非空验证 会员用户名
                if (null != customerAllInfo.getCustomerUsername()) {
                    LOGGER.info("跳转到会员【" + customerAllInfo.getCustomerUsername() + "】的用户反馈页面");
                }
                // 根据会员编号查询会员信息
                resultMap.put(CustomerConstantStr.CUSTOMER, customerServiceInterface.queryCustomerById(customerId));

                mav = new ModelAndView("customer/feedback");
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("/login/redirect").addObject(CustomerConstantStr.URL, CustomerConstantStr.CUSTOMERS + CustomerConstantStr.CENTERHTML);
            }
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
            resultMap = null;
            customerId = null;
        }
    }

    /**
     * 用户反馈发送邮件
     */
    @RequestMapping("/sendemailuser")
    @ResponseBody
    public Object sendEmailToStore(HttpServletRequest request, @Valid FreeBackVo freeBack) {
        // 验证用户是否存在
        if (checkLoginStatus(request)) {
            // 用户反馈发送邮件
            return feedBacKEmail.sendToStore(basicSetService.findBasicSet().getBsetEmail(), "来自用户" + freeBack.getFeedbackname() + "的反馈：<br/>反馈内容关于：" + freeBack.getFeedbacktype()
                    + "<br/>反馈内容为：" + freeBack.getFeedbackcontent());
        } else {
            return -1;
        }

    }

    /**
     * 验证用户是否存在
     * 
     * @param request
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

    public FeedBackEmail getFeedBacKEmail() {
        return feedBacKEmail;
    }

    @Resource(name = "feedBackEmail")
    public void setFeedBacKEmail(FeedBackEmail feedBacKEmail) {
        this.feedBacKEmail = feedBacKEmail;
    }

    public BasicSetService getBasicSetService() {
        return basicSetService;
    }

    @Resource(name = "basicSetService")
    public void setBasicSetService(BasicSetService basicSetService) {
        this.basicSetService = basicSetService;
    }
}
