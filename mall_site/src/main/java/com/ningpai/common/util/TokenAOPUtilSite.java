/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.common.util;

import com.ningpai.common.safe.CSRFTokenManager;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * AOP验证token
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年8月12日 下午5:42:04
 * @version 1.0
 */
public class TokenAOPUtilSite {
    // 不需要验证token的控制器,必须加上/
    String[] urls = new String[] { "/customer/index.htm", "/myorder.htm", "/orderdetails.htm", "/myfollw.htm", "/browserecord.htm", "/refundlist.htm", "/browserecord.htm", "/myinfo.htm", "/securitycenter.htm", "/consume.htm", "/myintegral.htm", "/comment.htm", "/consult.htm", "/orderdetail.htm", "/tocomment.htm", "/toshare.htm", "/queryorderexpress.htm", "/queryMyConsume.htm", "/queryCustomerAddress.htm", "/queryMyCoupon.htm", "/checkmobileexist.htm", "/checkemailexist.htm", "/checkcustomerpassword.htm", "/sendcode.htm", "/validate/getMCode.htm", "/validate/validateidentity.htm", "/validate/reirectpem.htm", "/validate/sucess.htm", "/validate/modifypem.htm", "/validate/sendcheckidemail.htm", "/validate/bindsucess.htm", "/validate/validbindemail.htm", "/validate/valididemail.htm",
            "/validate/sendeamil.htm", "/loadgoodstag.htm", "/getAllProvince.htm", "/getAllCityByPid.htm", "/getAllDistrictByCid.htm", "/getAllStreetByDid.htm", "/customer/tocomplain.htm", "/customer/ordercomplain.htm", "/customer/complainsuccess.htm", "/customer/complainlist.htm", };

    /**
     * 切点
     */
    @Pointcut("execution(* com.ningpai.site.customer.controller.*.*(..))")
    private void pointCut() {
    }

    /**
     * 验证token的AOP
     */
    @Before("pointCut()")
    public void valiToken() {
        //获取request请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取请求中的path
        String path = request.getServletPath();
        boolean bool = true;
        try {
            /* 如果是放开的控制器就不验证token */
            for (String url : urls) {
                if (url.equals(path)) {
                    bool = false;
                }
            }
            if (bool) {
                //获取token
                String token = request.getParameter(ConstantUtil.CSRFTOKEN);
                if (null != token && !"".equals(token)) {
                    if (!CSRFTokenManager.valiToken(token, request.getSession())) {
                        throw new RuntimeException("token不匹配");
                    }
                } else {
                    // token不匹配就抛出异常
                    throw new RuntimeException("token不匹配");
                }
            }
        } finally {
            request = null;
        }
    }

    /**
     * 登录切点
     */
    @Pointcut("execution(* com.ningpai.site.login.service.LoginService.checkCustomerExists(..))")
    private void loginPointCut() {
    }

    /**
     * 登陆成功后创建token
     */
    @After("loginPointCut()")
    public void afterLogin() {
        //获取request请求
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try {
            // 如果登陆成功创建token
            if (null != request.getSession().getAttribute("cust")) {
                //获取token值
                CSRFTokenManager.getTokenForSession(request.getSession());
            }
        } finally {
            request = null;
        }
    }
}
