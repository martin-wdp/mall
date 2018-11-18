/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.oauth;

import com.ningpai.api.bean.Auth;

/**
 * 根据Auth 实体类构建OAuth,重新实现的的第三方授权,必须在本类注册
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月24日上午9:40:41
 * @version 1.0
 */
public final class OAuthFactory {

    private static OAuthFactory instance;

    private OAuthFactory() {
    }

    /**
     * @return the instance
     */
    public static OAuthFactory getInstance() {
        if (instance == null) {
            // 双重检查锁定,防止多线程同时创建实例
            synchronized (OAuthFactory.class) {
                if (instance == null) {
                    instance = new OAuthFactory();
                }
            }
        }
        return instance;
    }

    /**
     * 根据Auth实体类带来的第三方授权信息构建OAuth(授权认证类)
     * 
     * @param auth
     *            第三方授权信息
     * @return OAuth(授权认证类)
     */
    public AbstractOAuth buildOAuth(Auth auth) {
        // 不允许为空
        if (null == auth) {
            return null;
        }
        AbstractOAuth oauth = null;
        String authName = auth.getAuthName();
        String clientId = auth.getAuthClientId();
        String clientSecret = auth.getAuthClientSecret();
        String redirectUri = auth.getAuthRedirectUri();

        if ("QQ".equalsIgnoreCase(authName)) {
            oauth = new OAuthQQ(authName, clientId, clientSecret, redirectUri);
        } else if ("baidu".equalsIgnoreCase(authName)) {
            oauth = new OAuthBaidu(authName, clientId, clientSecret, redirectUri);
        } else if ("sina".equalsIgnoreCase(authName)) {
            oauth = new OAuthSina(authName, clientId, clientSecret, redirectUri);
        }
        return oauth;
    }
}
