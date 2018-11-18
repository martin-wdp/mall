/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * @author ggn 人人网登陆
 */
@Controller
public class LoginRenRenController {

    private AuthService authService;

    /**
     * 人人网
     * 
     * @param request
     * @param response
     * @return ModelAndView
     * @throws Exception
     */
    @RequestMapping("loginrenren")
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Auth auth = authService.findAuthByAuthType("6");
        String appID = auth.getAuthClientId();
        String redirectURI = auth.getAuthRedirectUri();
        String url = "https://graph.renren.com/oauth/authorize?client_id=" + appID + "&redirect_uri=" + redirectURI + "&response_type=code";
        response.sendRedirect(url);
        return null;
    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
