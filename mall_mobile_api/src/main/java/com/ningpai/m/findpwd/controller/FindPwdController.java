/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.findpwd.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.findpwd.util.FindPwdUtil;
import com.ningpai.util.MyLogger;

/**
 * 手机端找回密码Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月22日 上午10:23:14
 * @version 0.0.1
 */
@Controller
public class FindPwdController {

    // spring 属性注入
    private CustomerService customerService;

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(FindPwdController.class);
    

    /***
     * 跳转找回密码页面
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/m/valididentity")
    public ModelAndView toCheckUserIndentity(HttpServletRequest request, String code) {
        return new ModelAndView(CustomerConstants.REDIRECTTOFINDPWD);
    }

    /***
     * 验证用户身份
     * 
     * @param request
     * @param code
     *            手机验证
     * @return {@link ModelAndView}
     */
    @RequestMapping("/m/govalididentity")
    public ModelAndView checkUserIndentity(HttpServletRequest request, String code) {
        try {
            // 服务端验证短信验证码 the code is null or empty will be throw IllegalArgumentException
            if (FindPwdUtil.checkCode(request, code)) {
                return new ModelAndView("redirect:/resetpassword.html");
            }

        } catch (Exception e) {
            LOGGER.error("参数异常",e);
        }
        return new ModelAndView(CustomerConstants.REDIRECTTOINDEX);
    }

    /***
     * 跳转重置密码页面
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/m/resetpassword")
    public ModelAndView toResetUserPwd(HttpServletRequest request, String code) {
        try {
            // 服务端验证 the code is null or empty will be throw IllegalArgumentException
            if (FindPwdUtil.checkSessionCode(request)) {
                return new ModelAndView(CustomerConstants.REDIRECTTORESETPWD);
            }

        } catch (Exception e) {
            LOGGER.error("参数异常",e);
        }
        return new ModelAndView(CustomerConstants.REDIRECTTOINDEX);
    }

    /**
     * 修改密码
     * 
     * @param request
     * @param userKey
     *            用户新密码
     * @return {@link ModelAndView}
     */
    @RequestMapping("/m/goresetpassword")
    public ModelAndView resetUserPwd(HttpServletRequest request, String userKey) {
        try {
            // 服务端验证 the code is null or empty will be throw IllegalArgumentException
            if (FindPwdUtil.checkSessionCode(request)) {
                customerService.updateCusomerPwd(request, userKey);
                return new ModelAndView("redirect:/success.html");
            }

        } catch (Exception e) {
            LOGGER.error("参数异常",e);
        }
        return new ModelAndView(CustomerConstants.REDIRECTTOINDEX);
    }

    /**
     * 修改密码成功回调
     * 
     * @param request
     * @return {@link ModelAndView}
     */
    @RequestMapping("/m/success")
    public ModelAndView resetUserPwd(HttpServletRequest request) {
        try {
            // 服务端验证 the muFlag is null or empty will be throw IllegalArgumentException
            if (FindPwdUtil.checkSuccessCode(request)) {
                return new ModelAndView(CustomerConstants.REDIRECTTORESETPWDSUCC);
            }

        } catch (Exception e) {
            LOGGER.error("参数异常",e);
        }
        return new ModelAndView(CustomerConstants.REDIRECTTOINDEX);
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Resource(name = "customerServiceM")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
