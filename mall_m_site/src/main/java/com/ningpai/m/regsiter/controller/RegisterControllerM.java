/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.regsiter.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.ningpai.common.util.ConstantUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.login.service.LoginService;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.IPAddress;

/**
 * 手机端注册Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月21日 下午5:09:05
 * @version 0.0.1
 */
@Controller
public class RegisterControllerM {

    // spring 注解 会员服务Service
    private CustomerServiceMapper customerServiceMapper;
    private LoginService loginService;
    private CustomerPointServiceMapper customerPointServiceMapper;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 跳转注册页面
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/register")
    public ModelAndView toRegister() {

        return seoService.getCurrSeo(new ModelAndView(CustomerConstants.REGOSTER).addObject(ConstantUtil.PAGENAME,"账号注册"));
    }

    /**
     * 注册
     * 
     * @param request
     * @param allInfo
     * @return
     */
    @RequestMapping("/customer/addcustomer")
    public ModelAndView register(HttpServletRequest request, @Valid CustomerAllInfo allInfo, String code) {
        if (allInfo.getCustomerUsername() == null || allInfo.getCustomerPassword() == null || code == null || (code != null && code.trim().length() == 0)) {
            throw new NullPointerException();
        }
        if (!code.equals((int) request.getSession().getAttribute("mcCode") + "")) {
            throw new IllegalArgumentException();
        }
        allInfo.setLoginIp(IPAddress.getIpAddr(request));
        allInfo.setIsSeller("0");
        allInfo.setIsMobile("1");
        allInfo.setIsEnterprise("0");
        customerServiceMapper.addCustomer(allInfo);
        loginService.checkCustomerExists(request, allInfo.getCustomerUsername(), allInfo.getCustomerPassword());
        customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute("customerId"), "0");
        return new ModelAndView("redirect:/customer/index.html?tag=4");
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name = "loginServiceM")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }
}
