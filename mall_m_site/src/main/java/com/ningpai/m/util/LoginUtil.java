/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.util;

import com.ningpai.customer.bean.Customer;

import javax.servlet.http.HttpServletRequest;
/**
 *登录帮助类
 * */
public final class LoginUtil {
    /**
     * 无参构造
     * */
    private LoginUtil(){
    }

    /**
     * 验证登录
     * 
     * @param request
     * @return false 未登录 true已登录
     */
    public static boolean checkLoginStatus(HttpServletRequest request) {
        Customer customer = (Customer) request.getSession().getAttribute("cust");
        if ( customer == null) {
            request.getSession().setAttribute("vip","0");
            return false;
        }
        request.getSession().setAttribute("vip",customer.getIsEnterprise());
        return true;
    }
}
