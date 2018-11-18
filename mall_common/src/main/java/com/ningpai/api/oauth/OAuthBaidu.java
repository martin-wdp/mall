/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.oauth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.ningpai.api.util.APIValueUtil;
import com.ningpai.api.util.HttpUtil;
import com.ningpai.api.util.JsonUtil;
import com.ningpai.api.util.TokenUtil;
import com.ningpai.logger.util.OperaLogUtil;

/**
 * 百度第三方登录实现
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午3:56:59
 * @version 1.0
 */
public class OAuthBaidu extends AbstractOAuth {

    private static final String EXINFO = "百度第三方登录实现";

    /**
     * 请求授权
     */
    private static final String AUTH_URL = "https://openapi.baidu.com/oauth/2.0/authorize";
    /**
     * 获取授权
     */
    private static final String TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token";
    /**
     * 用户信息
     */
    private static final String USER_INFO_URL = "https://openapi.baidu.com/rest/2.0/passport/users/getInfo";

    /**
     * 百度用户头像链接
     */
    private static final String PHOTO_URL = "http://tb.himg.baidu.com/sys/portrait/item/";

    /**
     * OAuthBaidu
     */
    public OAuthBaidu() {
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
    public OAuthBaidu(String authName, String clientId, String clientSecret, String redirectUri) {
        super(authName, clientId, clientSecret, redirectUri);
    }

    /**
     * getAuthorizeUrl
     * @return
     */
    @Override
    public String getAuthorizeUrl() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("response_type", "code");
        params.put("client_id", getClientId());
        params.put("redirect_uri", getRedirectUri());
        try {
            return HttpUtil.appendQueryParams(AUTH_URL, params);
        } catch (IOException e) {
            OperaLogUtil.addOperaException(EXINFO, e, null);
        }
        return null;
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
        String userInfo = getUserInfo(accessToken);

        Map<String, Object> userinfo = JsonUtil.getMapFromJson(userInfo);
        // 因为百度第三方登录只提供用户头像图片名称,没有路径显示,所有在这人进行修改添加路径
        if (null != userinfo.get(APIValueUtil.PORTRAIT) && !"".equals(userinfo.get(APIValueUtil.PORTRAIT))) {
            userinfo.put(APIValueUtil.PORTRAIT, PHOTO_URL + userinfo.get(APIValueUtil.PORTRAIT).toString());
        }
        return userinfo;
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
            OperaLogUtil.addOperaException(EXINFO, e, null);
        }
        return null;
    }

    /**
     * 获取用户信息JSON数据
     * 
     * @param accessToken
     *            授权过的Access Token
     * @return 用户信息JSON字符串
     */
    private String getUserInfo(String accessToken) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        try {
            return HttpUtil.postForm(USER_INFO_URL, params);
        } catch (IOException e) {
            OperaLogUtil.addOperaException(EXINFO, e, null);
        }
        return null;
    }

}
