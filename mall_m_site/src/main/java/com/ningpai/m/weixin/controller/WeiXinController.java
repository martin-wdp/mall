/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * 微信接入
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月26日 下午3:33:51
 * @version 0.0.1
 */
@Controller
public class WeiXinController {

    private static final String WEIXIN_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=";
    private static final String REDIRECT_URI = "&redirect_uri=";
    private static final String WECHAT_REDIRECT = "&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
    private static final String LOGGERINFO1 = "Sending getwxcode request failed!";
    private static final String LOGGERINFO2 = "Getting WEIXIN set failed!";

    private AuthService authService;

    /**
     * 获取微信用户Code
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getwxcode")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("9");
        if (auth != null) {
            String url = WEIXIN_URL + auth.getAuthClientId() + REDIRECT_URI + auth.getAuthRedirectUri() + WECHAT_REDIRECT;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                OperaLogUtil.addOperaException(LOGGERINFO1, e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException(LOGGERINFO2, e, request);
            }
        }

    }

    /**
     * 获取微信用户Code
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getwxcode1")
    protected void doGet1(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("9");
        if (auth != null) {
            String url = WEIXIN_URL + auth.getAuthClientId() + REDIRECT_URI + auth.getAuthRedirectUri() + WECHAT_REDIRECT;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                OperaLogUtil.addOperaException(LOGGERINFO1, e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException(LOGGERINFO2, e, request);
            }
        }

    }

    /**
     * 获取微信用户Code
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getwxcode2")
    protected void doGet2(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("9");
        if (auth != null) {
            String url = WEIXIN_URL + auth.getAuthClientId() + REDIRECT_URI + auth.getAuthRedirectUri() + WECHAT_REDIRECT;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                OperaLogUtil.addOperaException(LOGGERINFO1, e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException(LOGGERINFO2, e, request);
            }
        }

    }

    /**
     * 获取微信用户Code -- 转发登录
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getwxcode3")
    protected void doGet2(HttpServletRequest request, HttpServletResponse response, String toUrl) {
        Auth auth = authService.findAuthByAuthType("9");
        request.getSession().setAttribute("otherPayUrl", toUrl);
        if (auth != null) {
            String url = WEIXIN_URL + auth.getAuthClientId() + REDIRECT_URI + auth.getAuthRedirectUri() + WECHAT_REDIRECT;
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                OperaLogUtil.addOperaException(LOGGERINFO1, e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException(LOGGERINFO2, e, request);
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
