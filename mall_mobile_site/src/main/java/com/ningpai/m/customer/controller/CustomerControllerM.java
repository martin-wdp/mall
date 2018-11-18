/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.bean.Customer;
import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerAllInfo;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;
import com.ningpai.util.PageBean;

/**
 * 手机端会员控制层
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 上午10:16:32
 * @version 0.0.1
 */
@Controller
public class CustomerControllerM {

    // spring 注解 会员service
    private CustomerService customerService;

    @Resource(name = "SeoService")
    private SeoService seoService;
    
    /**
     * 会员中心
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/indexm")
    public ModelAndView index(HttpServletRequest request) {
        // 跳转个人中心
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.CUSTOMERINDEX);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/customer/index.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 发送手机验证码
     * 
     * @throws IOException
     */
    @RequestMapping("/sendcode")
    @ResponseBody
    public int sendcode(HttpServletRequest request, String moblie) throws IOException {
        return customerService.sendPost(request, moblie);
    }

    /**
     * 检查手机验证码
     * 
     * @return 0不同 1相同
     * @throws IOException
     */
    @RequestMapping("validate/getMCode")
    @ResponseBody
    public int getMCode(HttpServletRequest request, String code) throws IOException {
        return customerService.getMCode(request, code);
    }
    
    /**
     * 我的账户
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/myaccount")
    public ModelAndView myaccount(HttpServletRequest request) {
        // 跳转我的账户
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                request.getSession().setAttribute("cust", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.MYACCOUNT);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/myaccount.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }
    
    /**
     * 跳转到修改昵称页面
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/changenickname")
    public ModelAndView toChangeNickName(HttpServletRequest request) {
        ModelAndView mav = null;
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                mav = new ModelAndView(CustomerConstants.CHANGENAME);
                mav.addObject("customer", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/myaccount.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }
    
    /**
     * 修改昵称
     * @param request
     * @param changeName
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/savenickname")
    public ModelAndView saveNickName(HttpServletRequest request, String changeName) throws UnsupportedEncodingException{
        // 跳转我的账户
        ModelAndView mav = null;
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                Customer customer = new Customer();
                customer.setCustomerId((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
                customer.setCustomerNickname(new String(changeName.getBytes("ISO-8859-1"),"UTF-8"));
                this.customerService.updateCustomer(customer);
                mav = new ModelAndView("redirect:/myaccount.html");
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/myaccount.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }
    /**
     * 跳转到修改姓名页面
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/changename")
    public ModelAndView toChangeName(HttpServletRequest request) {
        ModelAndView mav = null;
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                mav = new ModelAndView(CustomerConstants.CHANGENAME2);
                mav.addObject("customer", customerService.selectByPrimaryKey((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/myaccount.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }
    
    /**
     * 修改姓名
     * @param request
     * @param changeName
     * @return
     * @throws UnsupportedEncodingException 
     */
    @RequestMapping("/savename")
    public ModelAndView saveName(HttpServletRequest request, String changeName) throws UnsupportedEncodingException{
        // 跳转我的账户
        ModelAndView mav = null;
        try {
            // 验证登录
            if (LoginUtil.checkLoginStatus(request)) {
                CustomerAllInfo customer = new CustomerAllInfo();
                customer.setCustomerId((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
                customer.setInfoRealname(new String(changeName.getBytes("ISO-8859-1"),"UTF-8"));
                this.customerService.updateCustomerInfo(customer);
                mav = new ModelAndView("redirect:/myaccount.html");
            } else {
                mav = new ModelAndView("redirect:/login.html?url=/myaccount.html");
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }

    
    /**
     * 我的积分
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/myintegral")
    public ModelAndView  queryCustomerPoint(HttpServletRequest request,PageBean pb,Long date,String type){
        pb.setUrl("1".equals(date.toString()) ? CustomerConstants.CUSTOMER + "/myintegral" : CustomerConstants.CUSTOMER + "/myintegral-" + date);
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            return new ModelAndView(CustomerConstants.MYINTEGRAL)
            .addObject("pb", customerService.selectAllCustomerPoint(customerId, pb,date,type))
            .addObject("customer",customerService.selectByPrimaryKey(customerId))
            .addObject(CustomerConstants.DATE, date)
            .addObject("type",type);
        }else{
             // 没登录的用户跳转到登录页面
            return  new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }
    
    /**
     * 查询我的积分加载数据
     * @param request
     * @param pb
     * @return
     */
    @RequestMapping("/allmyintegral")
    @ResponseBody
    public  PageBean allMyIntegral(HttpServletRequest request,PageBean pb,String type){
        PageBean pbNew = pb;
        if (LoginUtil.checkLoginStatus(request)) {
            Long customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
            pbNew= customerService.selectAllCustomerPoint(customerId, pbNew,1L,type);
        }
        return pbNew;
    }
    
    

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Resource(name = "customerServiceM")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

}
