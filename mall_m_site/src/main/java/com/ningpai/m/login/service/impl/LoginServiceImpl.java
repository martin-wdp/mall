/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.login.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.m.customer.mapper.CustomerMapper;
import com.ningpai.m.login.service.LoginService;
import com.ningpai.m.shoppingcart.service.ShoppingCartService;
import com.ningpai.other.util.IPAddress;
import com.ningpai.util.UtilDate;

/**
 * @see com.ningpai.m.login.service.LoginService
 * @author NINGPAI-zhangqiang
 * @since 2014年8月19日 上午10:33:36
 * @version 0.0.1
 */
@Service("loginServiceM")
public class LoginServiceImpl implements LoginService {

    // spring注解 会员Mapper
    private CustomerMapper customerMapper;

    private Customer customer;

    private CustomerAddress address;

    private CustomerAddressMapper addressMapper;

    private CustomerPointServiceMapper customerPointServiceMapper;

    private ShoppingCartService shoppingCartService;

    private static final String UTYPE = "uType";

    /*
     * 
     * 
     * @see
     * com.ningpai.m.login.service.LoginService#checkCustomerExists(javax.servlet
     * .http.HttpServletRequest, java.lang.String, java.lang.String)
     */
    @SuppressWarnings("static-access")
    @Override
    public int checkCustomerExists(HttpServletRequest request, String username, String password) {
        Map<String, Object> paramMap = null;
        String nameEmp = username.trim();
        paramMap = new HashMap<String, Object>();
        if (nameEmp.indexOf("@") != -1) {
            paramMap.put(UTYPE, "email");
        } else if (Pattern.compile("^0?(13|15|17|18|14)[0-9]{9}$").matcher(nameEmp).find()) {
            paramMap.put(UTYPE, "mobile");
        } else {
            paramMap.put(UTYPE, "username");
        }
        paramMap.put("username", username);
        Long existsFlag = customerMapper.checkExistsByCustNameAndType(paramMap);
        if (!"0".equals(existsFlag.toString())) {
            paramMap.put("password", password);
            customer = customerMapper.selectCustomerByNamePwdAndType(paramMap);
            if (customer != null) {
                // 增加登录积分
                if (!UtilDate.todayFormatString(new Date()).equals(UtilDate.todayFormatString(customer.getLoginTime()))) {
                    customerPointServiceMapper.addIntegralByType(customer.getCustomerId(), "1");
                }
                customer.setCustomerPassword(null);
                // 设置登录时间
                customer.setLoginTime(new Date());
                // 设置登录Ip
                customer.setLoginIp(IPAddress.getIpAddr(request));
                if (customer.getAeadTime() == null) {
                    Calendar calendar = new GregorianCalendar();
                    calendar.setTime(new Date());
                    calendar.add(calendar.DATE, -1);
                    customer.setAeadTime(calendar.getTime());
                }
                customerMapper.updateByPrimaryKeySelective(customer);
                request.getSession().setAttribute("cust", customer);
                request.getSession().setAttribute("vip", customer.getIsEnterprise());// 2015-12-27 wuyanbo 添加会员信息
                request.getSession().setAttribute("customerId", customer.getCustomerId());
                // 保存默认收获地址
                address = addressMapper.selectDefaultAddr(customer.getCustomerId());
                request.getSession().setAttribute("address", address);
                // 密码正确
                shoppingCartService.loadCoodeShopping(request);
                return 1;
            } else {
                // 密码错误
                return 0;
            }
        } else {
            // 用户名不存在
            return 2;
        }
    }

    /**
     * 向cookie中存贮是否保存用户密码的开关
     * @param request
     * @param isMerPwd
     * @return
     */
    @Override
    public int addIsMerPwdCookie(HttpServletRequest request, String isMerPwd,HttpServletResponse response,
    String password,String userName) {
        //向cookie中存贮是否保存用户密码的开关
        Cookie isMrP = new Cookie("isMerPwd", isMerPwd);
        response.addCookie(isMrP);
        if("1".equals(isMerPwd)){
            Cookie userPwd = new Cookie("qp_password", password);
            Cookie _username = new Cookie("qp_username", userName);
            response.addCookie(userPwd);
            response.addCookie(_username);
        }
        return 1;
    }
    /**
     * 从cookie中读取是否保存用户密码的开关
     * added by luyong f2016 02 03
     * @param request
     * @return
     */
    @Override
    public Map<String,String> getIsMerPwdCookie(HttpServletRequest request) {
        Map<String,String> result = new HashMap<String,String>();
        // 拿到Cookie
        Cookie[] cookies = request.getCookies();
        // 从cookie中获取开关和用户的值
        for(Cookie cookie:cookies){
            if("isMerPwd".equals(cookie.getName())){
                result.put("isMerPwd",cookie.getValue());
            }
            if("qp_username".equals(cookie.getName())){
                result.put("qp_username",cookie.getValue());
            }
            if("qp_password".equals(cookie.getName())){
                result.put("qp_password",cookie.getValue());
            }
        }
        return result;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapperM")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerAddressMapper getAddressMapper() {
        return addressMapper;
    }

    @Resource(name = "customerAddressMapper")
    public void setAddressMapper(CustomerAddressMapper addressMapper) {
        this.addressMapper = addressMapper;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

    public ShoppingCartService getShoppingCartService() {
        return shoppingCartService;
    }

    @Resource(name = "ShoppingCartService")
    public void setShoppingCartService(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

}
