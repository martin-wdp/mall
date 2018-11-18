/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.weixin.service.WXActivityService;
import com.ningpai.m.weixin.util.WeiXinUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * 用于微信端,活动按钮点击获取用户openid
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年9月3日 下午2:10:08
 * @version 0.0.01
 */
@Controller
public class WXActivityController {

    private static final String REDIRECT_ACTIVITY_HTML = "redirect:/activity.html";

    private AuthService authService;

    private WXActivityService wxActivityService;

    /**
     * 获取微信用户Code
     * 
     * @param request
     * @param response
     */
    @RequestMapping("getcodetoevent")
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        Auth auth = authService.findAuthByAuthType("8");
        if (auth != null) {
            String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + auth.getAuthClientId() + "&redirect_uri=" + auth.getAuthRedirectUri()
                    + "&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
            try {
                response.sendRedirect(url);
            } catch (IOException e) {
                OperaLogUtil.addOperaException("Sending getwxcode request failed!", e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException("Getting WEIXIN set failed!", e, request);
            }
        }

    }

    /**
     * 获取微信用户Code
     * 
     * @param request
     * @param response
     * @throws IOException
     * @throws HttpException
     */
    @RequestMapping("gettokentoevent")
    protected ModelAndView getWXToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取code
        String code = request.getParameter("code");
        if (code == null) {
            return new ModelAndView(REDIRECT_ACTIVITY_HTML);
        }
        // 获取微信接口AppId...
        Auth auth = authService.findAuthByAuthType("8");
        if (auth != null) {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + auth.getAuthClientId() + "&secret=" + auth.getAuthClientSecret() + "&code=" + code
                    + "&grant_type=authorization_code";
            GetMethod getTokenMethod = new GetMethod(url);
            HttpClient clientToken = new HttpClient();
            getTokenMethod.getParams().setContentCharset(ConstantUtil.UTF);
            String res = "";
            Map<String, String> resultMap = null;
            try {
                // 获取token
                clientToken.executeMethod(getTokenMethod);
                res = getTokenMethod.getResponseBodyAsString();
                resultMap = WeiXinUtil.getWeiToken(res);
                if (resultMap == null) {
                    try {
                        // 获取数据失败 throw NullPointerException ...
                        throw new NullPointerException();
                    } catch (Exception e) {
                        // 获取token失败...
                        OperaLogUtil.addOperaException("Getting token failed!", e, request);
                        return new ModelAndView(REDIRECT_ACTIVITY_HTML);
                    }
                }
                try {
                    if (!wxActivityService.checkOpenIdExist(resultMap.get(ConstantUtil.OPENID))) {
                        wxActivityService.addWxUserIdToGroup(resultMap.get(ConstantUtil.OPENID));
                    }
                } catch (Exception e) {
                    // 添加微信用户到群发组失败...
                    OperaLogUtil.addOperaException("add wxuser to group failed!", e, request);
                    return new ModelAndView(REDIRECT_ACTIVITY_HTML);
                }

            } catch (Exception e) {
                // 发送获取token请求失败
                OperaLogUtil.addOperaException("Sending getwxtoken request failed!", e, request);
                return new ModelAndView(REDIRECT_ACTIVITY_HTML);
            }
        } else {
            try {
                // 微信接口数据未获取 throw NullPointerException...
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException("Getting WEIXIN set failed!", e, request);
                return new ModelAndView(REDIRECT_ACTIVITY_HTML);
            }
        }
        return new ModelAndView(REDIRECT_ACTIVITY_HTML);
    }

    /**
     * 活动
     * 
     * @return
     */
    @RequestMapping("/activity")
    public ModelAndView toActivity() {
        return new ModelAndView("customer/activity");
    }

    public WXActivityService getWxActivityService() {
        return wxActivityService;
    }

    @Resource(name = "wXActivityService")
    public void setWxActivityService(WXActivityService wxActivityService) {
        this.wxActivityService = wxActivityService;
    }

    public AuthService getAuthService() {
        return authService;
    }

    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}
