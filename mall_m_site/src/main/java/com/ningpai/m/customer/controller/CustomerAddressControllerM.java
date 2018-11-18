/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.CustomerAddressService;
import com.ningpai.m.customer.service.CustomerService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 上午11:39:47
 * @version 0.0.1
 */
@Controller
public class CustomerAddressControllerM {

    // spring 注解 会员service
    private CustomerService customerService;
    private CustomerAddressService customerAddressService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 查收货地址
     * 
     * @param request
     * @return
     */
    @RequestMapping("/customer/address")
    public ModelAndView queryCustomerAddress(HttpServletRequest request) {
        if (LoginUtil.checkLoginStatus(request)) {
            return seoService.getCurrSeo(new ModelAndView(CustomerConstants.CUSTOMERADDRESS).addObject("addresses",
                    customerAddressService.queryCustomerAddress((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID))));
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 根据地址编号查找收货地址
     * 
     * @param addressId
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/filladdress")
    public ModelAndView fillAddress(HttpServletRequest request, Long addressId) {
        if (LoginUtil.checkLoginStatus(request)) {
            if (addressId == null) {
                return seoService.getCurrSeo(new ModelAndView(CustomerConstants.FILLADDRESS));
            } else {
                return seoService.getCurrSeo(new ModelAndView(CustomerConstants.FILLADDRESS).addObject("addr",
                        customerAddressService.queryCustomerAddressById(addressId, (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID))));
            }

        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 修改收货地址
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/updateaddress")
    public ModelAndView updateAddress(HttpServletRequest request, CustomerAddress address) {
        if (LoginUtil.checkLoginStatus(request)) {
            customerAddressService.updateAddress(address, (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
            return new ModelAndView("redirect:/customer/address.html");
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 添加收货地址
     * 
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/addaddress")
    public ModelAndView addAddress(HttpServletRequest request, CustomerAddress address) {
        if (LoginUtil.checkLoginStatus(request)) {
            customerAddressService.addAddress(address, (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
            return new ModelAndView("redirect:/customer/address.html");
        } else {
            return new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
    }

    /**
     * 提交订单页面中获取该用户的所有地址
     * @param request
     * @return
     */
    @RequestMapping("/customer/getAllAddress")
    @ResponseBody
    public Map<String,Object> getAllCustomerAddress(HttpServletRequest request){
        Map<String,Object> result =new HashMap<String,Object>();
        if (LoginUtil.checkLoginStatus(request)) {
            List<Object> addressList=customerAddressService.queryCustomerAddress((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
            result.put("success", true);
            result.put("datas",addressList);
        }else{
            result.put("success", false);
        }
        return result;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Resource(name = "customerServiceM")
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    public CustomerAddressService getCustomerAddressService() {
        return customerAddressService;
    }

    @Resource(name = "customerAddressServiceM")
    public void setCustomerAddressService(CustomerAddressService customerAddressService) {
        this.customerAddressService = customerAddressService;
    }
}
