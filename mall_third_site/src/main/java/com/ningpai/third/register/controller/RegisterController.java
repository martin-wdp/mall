/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.register.controller;

import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.other.util.IPAddress;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.third.login.service.LoginService;
import com.ningpai.third.register.service.RegisterService;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.io.IOException;

/**
 * 前台注册Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月16日 下午5:11:01
 * @version 0.0.1
 */
@Controller
public class RegisterController {
    // spring 注解 商家会员注册
    @Resource(name = "registerService")
    private RegisterService registerService;

    @Resource(name = "loginServiceThird")
    private LoginService loginService;

    /**
     * 站点设置服务层接口接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 跳转注册页面r
     * 
     * @return ModelAndView
     */
    @RequestMapping("/customer/register")
    public ModelAndView jumpRegister() {

        return new ModelAndView("/register/newregister").addObject("basicSet", basicSetService.findBasicSet());
    }

    /**
     * 发送手机验证码
     *
     * @throws java.io.IOException
     */
    @RequestMapping("/thirdsendcode")
    @ResponseBody
    public int sendcode(HttpServletRequest request, String moblie) throws IOException {
        return registerService.sendPost(request, moblie);
    }

    /**
     * 根据手机号码验证身份
     *
     * @throws IOException
     */
    @RequestMapping("/sendcodetovalidate")
    @ResponseBody
    public int sendcodeToValidate(HttpServletRequest request, String mobile) throws IOException {
        // 发送手机验证码
        return registerService.newsendPost(request, mobile);
    }

    /**
     * 检查手机是否被使用
     * 
     * @param request
     * @param mobile
     * @return
     */
    @RequestMapping("/checkthirdmobileexist")
    @ResponseBody
    public Long checkmobileexist(HttpServletRequest request, String mobile) {
        return customerServiceMapper.checkMobileExist(mobile);
    }

    /**
     * 判断手机验证码是否输入正确
     * 
     * @param request
     * @param mobile
     * @param codetext
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkmobilecode")
    @ResponseBody
    public int checkmobilecode(HttpServletRequest request, String mobile, String codetext) throws IOException {
        String userMobile = request.getSession().getAttribute("userMobile") != null ? request.getSession().getAttribute("userMobile").toString() : "";
        if (userMobile.equals(mobile)) {
            String mcCode = request.getSession().getAttribute("mcCode") != null ? request.getSession().getAttribute("mcCode").toString() : "";
            if (mcCode.equals(codetext)) {
                return 1;
            }
        }
        // 手机验证码输入错误
        return 0;
    }

    /**
     * 注册时用户名
     * 
     * @param request
     * @param customerUsername
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkUserNameExist")
    @ResponseBody
    public Long checkUserNameExist(HttpServletRequest request, String customerUsername) throws IOException {
        return customerServiceMapper.selectCustomerByName(customerUsername);
    }

    /**
     * 忘记密码时用户名判定,过滤掉前台的用户到第三方来忘记密码
     * 
     * @param customerUsername
     * @return
     * @throws IOException
     */
    @RequestMapping("/checkUserNameExistForforgeting")
    @ResponseBody
    public Long checkUserNameExistForforgeting(String customerUsername) throws IOException {
        return customerServiceMapper.selectCustomerByNameForThird(customerUsername);
    }

    /**
     * 第三方商家注册
     * 
     * @param allInfo
     * @param varification
     * @param request
     * @return
     */
    @RequestMapping("/thirdRegisterCustomer")
    public ModelAndView thirdRegisterCustomer(@Valid CustomerAllInfo allInfo, String varification, HttpServletRequest request) {

        // 注册验证
        if (!registerCustomerValidate(allInfo, (String) request.getSession().getAttribute("PATCHCA"), varification)) {
            // 非法操作，跳至首页
            return new ModelAndView(new RedirectView(CustomerConstantStr.THIRDLOGIN));
        }

        // 获取IP地址
        allInfo.setLoginIp(IPAddress.getIpAddr(request));
        // 未通过审核在第三方注册的商家
        allInfo.setIsSeller("3");
        allInfo.setIsMobile("1");

        // 添加用户信息
        if (isAddCustomerSuccess(allInfo)) {
            // 进行登录 登录成功进入后台商城 失败 返回登录页面
            if (isloginSystemSuccess(request, allInfo)) {
                return new ModelAndView(new RedirectView(CustomerConstantStr.GOENTERPAGE));
            }

            return new ModelAndView(new RedirectView(CustomerConstantStr.THIRDLOGIN));
        } else {
            return new ModelAndView(new RedirectView(CustomerConstantStr.THIRDLOGIN));
        }
    }

    /**
     * 添加用户信息 是否成功
     * 
     * @return 用户信息 添加成功返回true 否则返回false 1表示成功
     */
    private boolean isAddCustomerSuccess(CustomerAllInfo allInfo) {
        return customerServiceMapper.addThirdCustomer(allInfo) == 1;
    }

    /**
     * 登录系统
     * 
     * @return
     */
    private boolean isloginSystemSuccess(HttpServletRequest request, CustomerAllInfo allInfo) {
        return 1 == loginService.checkCustomerExists(request, allInfo.getCustomerUsername(), allInfo.getCustomerPassword());
    }

    /**
     * 第三方商家的注册验证 验证通过返回true 失败 返回false
     */
    private boolean registerCustomerValidate(CustomerAllInfo allInfo, String sessionPatchca, String userPatchca) {
        if (null == allInfo) {
            return false;
        }

        // 判断输入的用户名和密码 是否为空
        if (allInfo.isNameAndPasswordEmpty()) {
            return false;
        }

        return validateUserNameAndPatchcaCode(sessionPatchca, allInfo.getCustomerUsername(), userPatchca);
    }

    /**
     * 校验用户和验证码 是否正确
     * 
     * @param sessionPatchca
     *            session中的验证码
     * @param userName
     *            用户注册的名称
     * @param userPatchca
     *            用户输入的验证码
     * @return 如果用户名称不存在 并且验证码输入正确 则返回true 否则返回false
     */
    private boolean validateUserNameAndPatchcaCode(String sessionPatchca, String userName, String userPatchca) {
        // 如果验证码 不存在 则直接返回false
        if (StringUtils.isEmpty(sessionPatchca) || StringUtils.isEmpty(userPatchca)) {
            return false;
        }
        return 0 == customerServiceMapper.selectCustomerByName(userName) && sessionPatchca.equals(userPatchca);
    }

}
