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
 * @author ggn QQ微博登陆
 */
@Controller
public class LoginQQWeiboController {
    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginQQWeiboController.class);

    private AuthService authService;

    /**
     * QQ微博登陆
     * 
     * @param request
     * @param response
     */
    @RequestMapping("loginqqweibo")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

        Auth auth = authService.findAuthByAuthType("3");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String redirectURI = auth.getAuthRedirectUri();
            String url = "https://open.t.qq.com/cgi-bin/oauth2/authorize?client_id=" + appID + "&response_type=code&redirect_uri=" + redirectURI;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                LOGGER.error("QQ微博登陆错误：" + e);
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
