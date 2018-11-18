/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.api.bean.Auth;
import com.ningpai.api.oauth.AbstractOAuth;
import com.ningpai.api.oauth.OAuthFactory;
import com.ningpai.api.service.AuthService;

/**
 * 第三方授权登录
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午5:12:48
 * @version 1.0
 */
@Controller
public class OAuthController {

    private AuthService authService;

    /**
     * @return the authService
     */
    public AuthService getAuthService() {
        return authService;
    }

    /**
     * @param authService
     *            the authService to set
     */
    @Resource(name = "AuthService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    /**
     * 第三方登录跳转
     * 
     * @param provider
     *            登录名称,入腾讯的QQ
     * @return 如果provider不为空,且当前想要登录的已经启用将会跳转到第三方登录授权页面
     */
    @RequestMapping(value = "/auth", params = "id")
    public ModelAndView oauth(@RequestParam("id") final String provider) {
        // 非空验证
        if (null == provider || "".equals(provider)) {
            return null;
        }
        Auth auth = authService.findAuthByName(provider);
        AbstractOAuth oauth = OAuthFactory.getInstance().buildOAuth(auth);
        if (oauth != null) {
            return new ModelAndView(new RedirectView(oauth.getAuthorizeUrl()));
        }
        return null;
    }

    /**
     * 第三方授权登陆处理
     * 
     * @param provider
     *            登录名称,入腾讯的QQ
     * @return 用户绑定页面
     */
    @RequestMapping(value = "/auth/callback")
    public ModelAndView callback(final String id, final String code, HttpServletRequest request, HttpServletResponse response) {
        // 非空验证
        if (null == id || "".equals(id)) {
            return null;
        }
        Auth auth = authService.findAuthByName(id);
        AbstractOAuth oauth = OAuthFactory.getInstance().buildOAuth(auth);
        if (oauth != null) {
            Map<String, Object> userinfo = oauth.getUserInfoByCode(code);
            ModelAndView view = new ModelAndView("../jsp/auth/auth");
            view.addObject("userinfo", userinfo);
            return view;
        }
        return null;
    }
}
