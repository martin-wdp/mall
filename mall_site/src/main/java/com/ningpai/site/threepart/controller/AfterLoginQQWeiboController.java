/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.util.MyLogger;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.customer.mapper.CustomerMapper;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.threepart.bean.ThreePart;
import com.ningpai.site.threepart.service.ThreePartService;
import com.ningpai.site.threepart.util.QQWeiBoMessage;
import com.ningpai.site.threepart.util.StringUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * @author ggn QQ微博登陆回调
 */

@Controller
public class AfterLoginQQWeiboController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(AfterLoginQQWeiboController.class);

    private AuthService authService;

    private CustomerServiceInterface customerServiceInterface;

    private CustomerServiceMapper customerServiceMapper;

    private CustomerMapper customerMapper;

    private ThreePartService threePartService;

    /**
     * QQ微博回调
     * 
     * @param request
     * @param response
     * @return ModelAndView
     */
    @RequestMapping("afterloginqqweibo")
    public ModelAndView doGet(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html; charset=utf-8");
        Auth auth = authService.findAuthByAuthType("3");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String appKEY = auth.getAuthClientSecret();
            String redirectURI = auth.getAuthRedirectUri();
            String code = request.getParameter("code");
            String url = "https://open.t.qq.com/cgi-bin/oauth2/access_token?client_id=" + appID + "&client_secret=" + appKEY + "&redirect_uri=" + redirectURI
                    + "&grant_type=authorization_code&code=" + code;
            String userInfo;
            GetMethod getMethod = new GetMethod(url);
            HttpClient client = new HttpClient();
            Map<String, String> pmap = null;
            try {
                client.executeMethod(getMethod);
                userInfo = getMethod.getResponseBodyAsString();
                pmap = StringUtil.formatString(userInfo);
            } catch (Exception e) {
                LOGGER.error("QQ微博错误" + e);
            }
            if (pmap == null) {
                return new ModelAndView(new RedirectView("404.html"));
            }
            String openid = request.getParameter(ConstantUtil.OPENID);
            String accessToken = pmap.get("access_token");
            ThreePart tp = threePartService.selectThreePart(openid);
            if (tp != null) {
                // 获取用户信息
                Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                request.getSession().setAttribute("cust", cus);
                request.getSession().setAttribute("customerId", cus.getCustomerId());

            } else {
                // 注册流程 获取QQ信息
                Map<String, String> userData = null;
                try {
                    userData = QQWeiBoMessage.getQQWeiBoMessage(appID, accessToken, openid);
                } catch (IOException e1) {
                    LOGGER.error("QQ微博错误" + e1);
                }
                CustomerAllInfo allInfo = new CustomerAllInfo();
                allInfo.setLoginIp(IPAddress.getIpAddr(request));
                allInfo.setPointLevelId(2L);
                allInfo.setCustomerUsername(accessToken);
                allInfo.setCustomerPassword("");
                allInfo.setCustomerNickname(userData.get("nickname"));
                allInfo.setInfoGender("1".equals(userData.get("gender")) ? "1" : "2");
                allInfo.setCustomerImg(userData.get("headimg"));
                int f = customerServiceMapper.addCustomer(allInfo);
                if (f == 1) {
                    Map<String, Object> paramMap = new HashMap<String, Object>();
                    paramMap.put("username", accessToken);
                    paramMap.put("password", "");
                    Customer customer = customerMapper.selectCustomerByNamePwd(paramMap);
                    tp = new ThreePart();
                    tp.setThreePartUid(openid);
                    tp.setThreePartToken(accessToken);
                    tp.setThreePartMemberId(customer.getCustomerId());
                    threePartService.insertThreePart(tp);
                }

                Customer cus = customerServiceInterface.queryCustomerById(tp.getThreePartMemberId());
                request.getSession().setAttribute("cust", cus);
                request.getSession().setAttribute("customerId", cus.getCustomerId());

            }

        }

        return new ModelAndView(new RedirectView("index.html"));

    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public CustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "customerMapperSite")
    public void setCustomerMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }

    public ThreePartService getThreePartService() {
        return threePartService;
    }

    @Resource(name = "ThreePartService")
    public void setThreePartService(ThreePartService threePartService) {
        this.threePartService = threePartService;
    }

}
