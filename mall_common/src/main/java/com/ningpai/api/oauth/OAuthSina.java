/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ningpai.api.util.HttpUtil;
import com.ningpai.api.util.JsonUtil;
import com.ningpai.api.util.TokenUtil;
import com.ningpai.util.MyLogger;

/**
 * 新浪微博第三方登录实现
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午4:04:10
 * @version 1.0
 */
public class OAuthSina extends AbstractOAuth {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(OAuthSina.class);

    /**
     * 请求授权
     */
    private static final String AUTH_URL = "https://api.weibo.com/oauth2/authorize";
    /**
     * 获取授权
     */
    private static final String TOKEN_URL = "https://api.weibo.com/oauth2/access_token";
    /**
     * 授权查询
     */
    private static final String TOKEN_INFO_URL = "https://api.weibo.com/oauth2/get_token_info";
    /**
     * 用户信息
     */
    private static final String USER_INFO_URL = "https://api.weibo.com/2/users/show.json";

    public static final String getAuthorizeUrl1 = null;

    /**
     * OAuthSina
     */
    public OAuthSina() {
        super();
    }

    
    /**
     * @param authName
     *            授权名称
     * @param clientId
     *            申请应用时分配的AppKey
     * @param clientSecret
     *            请应用时分配的AppSecret
     * @param redirectUri
     *            授权回调地址
     */
    public OAuthSina(String authName, String clientId, String clientSecret, String redirectUri) {
        super(authName, clientId, clientSecret, redirectUri);
    }

    /**
     * getAuthorizeUrl
     * @return
     */
    @Override
    public String getAuthorizeUrl() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("state", "sina");
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        try {
            return HttpUtil.appendQueryParams(AUTH_URL, params);
        } catch (IOException e) {
            LOGGER.error(""+e);
            return getAuthorizeUrl1;
        }
    }

    /*
     * getUserInfoByCode
     * 
     * @see com.ningpai.api.oauth.OAuth#getUserInfoByCode(java.lang.String)
     */
    @Override
    public Map<String, Object> getUserInfoByCode(String code) {
        String accessToken = getTokenByCode(code);
        String openId = getTokenInfo(accessToken);
        String userinfo = getUserInfo(accessToken, openId);
        return JsonUtil.getMapFromJson(userinfo);
    }

    /**
     * 获取授权过的Access Token
     * 
     * @param code
     *            调用authorize获得的code值
     * @return 授权过的Access Token
     */
    private String getTokenByCode(String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("client_id", getClientId());
        params.put("client_secret", getClientSecret());
        params.put("grant_type", "authorization_code");
        params.put("redirect_uri", getRedirectUri());
        try {
            return TokenUtil.getAccessToken(HttpUtil.postForm(TOKEN_URL, params));
        } catch (IOException e) {
            LOGGER.error(""+e);
            return getAuthorizeUrl1;
        }
    }

    /**
     * 获取OpenID
     * 
     * @param accessToken
     *            授权过的Access Token
     * @return OpenID
     */
    private String getTokenInfo(String accessToken) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        try {
            return TokenUtil.getAccessToken(HttpUtil.postForm(TOKEN_INFO_URL, params));
        } catch (IOException e) {
            LOGGER.error(""+e);
            return getAuthorizeUrl1;
        }
    }

    /**
     * 获取用户信息JSON数据
     * 
     * @param accessToken
     *            授权过的Access Token
     * @param openId
     *            用户OpenID
     * @return
     */
    private String getUserInfo(String accessToken, String openId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("uid", openId);
        try {
            return HttpUtil.postForm(USER_INFO_URL, params);
        } catch (IOException e) {
            LOGGER.error(""+e);
            return getAuthorizeUrl1;
        }
    }

}
