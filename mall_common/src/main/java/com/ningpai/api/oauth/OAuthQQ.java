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
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.util.MyLogger;

/**
 * 腾讯QQ第三方登录实现
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午2:42:55
 * @version 1.0
 */
public class OAuthQQ extends AbstractOAuth {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(OAuthQQ.class);

    /**
     * 请求授权
     */
    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    /**
     * 获取授权
     */
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    /**
     * 授权查询
     */
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    /**
     * 用户信息
     */
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";

    public static final String getAuthorizeUrl1 = null;

    /**
     * OAuthQQ
     */
    public OAuthQQ() {
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
    public OAuthQQ(String authName, String clientId, String clientSecret, String redirectUri) {
        super(authName, clientId, clientSecret, redirectUri);
    }

    /**
     * getAuthorizeUrl
     * @return
     */
    @Override
    public String getAuthorizeUrl() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("state", "qq");
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

    /**
     * getUserInfoByCode
     * @param code
     *            调用authorize获得的code值
     * @return
     */
    @Override
    public Map<String, Object> getUserInfoByCode(String code) {
        String accessToken = getTokenByCode(code);
        String openId = getTokenInfo(accessToken);
        Map<String, Object> dataMap = JsonUtil.getMapFromJson(getUserInfo(accessToken, openId));
        dataMap.put(ConstantUtil.OPENID, openId);
        return dataMap;
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
            return TokenUtil.getAccessToken(HttpUtil.get(HttpUtil.appendQueryParams(TOKEN_URL, params)));
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
            return TokenUtil.getAccessToken(HttpUtil.get(HttpUtil.appendQueryParams(TOKEN_INFO_URL, params)));
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
        params.put("oauth_consumer_key", getClientId());
        params.put(ConstantUtil.OPENID, openId);
        params.put("format", "json");
        try {
            return HttpUtil.get(HttpUtil.appendQueryParams(USER_INFO_URL, params));
        } catch (IOException e) {
            LOGGER.error(""+e);
            return getAuthorizeUrl1;
        }
    }

}
