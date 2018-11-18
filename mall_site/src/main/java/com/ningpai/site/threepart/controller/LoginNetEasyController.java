/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.threepart.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.util.MyLogger;
import com.ningpai.site.threepart.util.StringUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;
import com.ningpai.util.RanddomMath;

/**
 * @author ggn 网易微博登陆
 */
@Controller
public class LoginNetEasyController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(LoginNetEasyController.class);

    private AuthService authService;

    /**
     * 网易微博登陆
     * 
     * @param request
     * @param response
     */
    @RequestMapping("loginneteasy")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("5");
        if (auth != null) {
            String appID = auth.getAuthClientId();
            String appKEY = auth.getAuthClientSecret();
            String redirectURI = auth.getAuthRedirectUri();
            String url = "http://api.t.163.com/oauth/request_token?auth_consumer_key=" + appID + "&oauth_signature_method=HMAC-SHA1&oauth_signature=" + appKEY
                    + "&oauth_timestamp=" + System.currentTimeMillis() + "&oauth_nonce=" + RanddomMath.randomString(32) + "&oauth_version=OAuth 1.0a";
            String userInfo;
            GetMethod getMethod = new GetMethod(url);
            HttpClient client = new HttpClient();
            Map<String, String> pmap = null;
            try {
                client.executeMethod(getMethod);
                userInfo = getMethod.getResponseBodyAsString();
                pmap = StringUtil.formatNetEasyString(userInfo);
            } catch (Exception e) {
                LOGGER.error("网易登陆报错" + e);
            }
            String token = pmap.get("oauth_token");
            String reurl = "http://api.t.163.com/oauth/request_token?oauth_token=" + token + "&oauth_callback=" + redirectURI;
            try {
                response.sendRedirect(reurl);
            } catch (IOException e) {
                LOGGER.error("网易登陆报错" + e);
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
