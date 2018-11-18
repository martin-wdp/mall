/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.comment.util.EmailUtilsSite;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.util.FeedBackEmailController;

/**
 * 反馈控制器
 *
 * */
@Controller
public class FreeBackSiteController {

    // spring 注解 会员service
    private CustomerServiceMapper customerServiceMapper;
    // 记录日志记录
    private static final MyLogger LOGGER = new MyLogger(FreeBackSiteController.class);

    private EmailUtilsSite emailUtilsSite;

    /**
     * 用户反馈发送邮件
     */
    @RequestMapping("/sendemailusersite")
    @ResponseBody
    public int sendEmailToStore(HttpServletRequest request, @NotNull String feedbackcontent, String feedbackname) {
        // 非空验证 用户名称 和反馈内容
        if (null != feedbackcontent && null != feedbackname) {
            LOGGER.info("来自用户" + feedbackname + "的反馈：反馈内容为:" + feedbackcontent);
        }
        return emailUtilsSite.sendToStore(FeedBackEmailController.getFeedBackEmailPath(), "来自用户" + feedbackname + "的反馈：<br/>  反馈内容为：" + feedbackcontent);
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public EmailUtilsSite getEmailUtilsSite() {
        return emailUtilsSite;
    }

    @Resource(name = "emailSiteUtils")
    public void setEmailUtilsSite(EmailUtilsSite emailUtilsSite) {
        this.emailUtilsSite = emailUtilsSite;
    }

}
