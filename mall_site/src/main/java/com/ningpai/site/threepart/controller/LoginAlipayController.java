/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.util.MyLogger;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * @author ggn 支付宝登陆
 */
@Controller
public class LoginAlipayController {
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginAlipayController.class);

    private AuthService authService;

    /**
     * 支付宝登陆
     * 
     * @param request
     * @param response
     */
    @RequestMapping("loginalipay")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("4");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String redirectURI = auth.getAuthRedirectUri();
            String url = "https://openauth.alipay.com/oauth2/authorize.htm?client_id=" + appID + "&redirect_uri=" + redirectURI + "&scope=&state=&view=";
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOGGER.error("支付宝登陆错误" + e);
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
