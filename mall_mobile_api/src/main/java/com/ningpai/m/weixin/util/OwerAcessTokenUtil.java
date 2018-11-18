/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.weixin.util;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.springframework.stereotype.Service;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.system.bean.Auth;
import com.ningpai.system.service.AuthService;

/**
 * 此工具类用于获取自己微信公众号的access_token
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月30日 上午10:36:30
 * @version 0.0.1
 */
@Service
public class OwerAcessTokenUtil {

    /**
     * 第三方登录服务层接口
     */
    private static AuthService authService;

    /**
     * 获取acess_token<br/>
     * <strong>注意:此token非获取用户信息时候的token</strong>
     * 
     * @return String
     */
    public static String getAcessToken(HttpServletRequest request, HttpServletResponse response) {
        //登录接口实体类
        Auth auth = authService.findAuthByAuthType("7");

        if (auth != null) {
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + auth.getAuthClientId() + "&secret=" + auth.getAuthClientSecret();
            GetMethod getTokenMethod = new GetMethod(url);
            HttpClient clientToken = new HttpClient();
            getTokenMethod.getParams().setContentCharset(ConstantUtil.UTF);
            String res = "";
            String token = "";
            try {
                // 获取token
                clientToken.executeMethod(getTokenMethod);
                res = getTokenMethod.getResponseBodyAsString();
                token = WeiXinUtil.getWxAcessToken(res);
                if (token == null) {
                    try {
                        // 获取数据失败 throw NullPointerException ...
                        throw new NullPointerException();
                    } catch (Exception e) {
                        // 获取accesstoken失败
                        OperaLogUtil.addOperaException("Getting accesstoken failed!", e, request);
                    }
                }
                return token;
            } catch (Exception e) {
                // 发送获取accesstoken请求失败
                OperaLogUtil.addOperaException("Sedding accesstoken request failed!", e, request);
            }
        } else {
            try {
                throw new NullPointerException();
            } catch (Exception e) {
                OperaLogUtil.addOperaException("Getting WEIXIN set failed!", e, request);
            }
        }
        return null;
    }
    /**
     * 获取
     * */
    public AuthService getAuthService() {
        return authService;
    }
    /**
     * Spring注入
     * */
    @Resource(name = "authService")
    public void setAuthService(AuthService authService) {
        OwerAcessTokenUtil.authService = authService;
    }
}
