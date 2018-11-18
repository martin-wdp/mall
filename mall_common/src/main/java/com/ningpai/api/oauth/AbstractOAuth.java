/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.oauth;

import java.util.Map;

/**
 * 第三方授权登陆顶层抽象,所有针对第三方登录的实现类必须继承本类
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午2:16:05
 * @version 1.0
 */
public abstract class AbstractOAuth {

    // 当前授权名称,如:qq,sina
    private String authName;
    // 申请应用时分配的AppKey
    private String clientId;
    // 申请应用时分配的AppSecret
    private String clientSecret;
    // 授权回调地址
    private String redirectUri;

    public AbstractOAuth() {
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
    public AbstractOAuth(String authName, String clientId, String clientSecret, String redirectUri) {
        super();
        this.authName = authName;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.redirectUri = redirectUri;
    }

    /**
     * 获取请求授权地址
     * 
     * @return 请求授权地址
     */
    public abstract String getAuthorizeUrl();

    /**
     * 获取用户信息
     * 
     * @param code
     *            调用authorize获得的code值
     * @return 用户信息的键值对形式数据
     */
    public abstract Map<String, Object> getUserInfoByCode(String code);

    /**
     * @return the authName
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * @param authName
     *            the authName to set
     */
    public void setAuthName(String authName) {
        this.authName = authName;
    }

    /**
     * @return the clientId
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId
     *            the clientId to set
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    /**
     * @return the clientSecret
     */
    public String getClientSecret() {
        return clientSecret;
    }

    /**
     * @param clientSecret
     *            the clientSecret to set
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    /**
     * @return the redirectUri
     */
    public String getRedirectUri() {
        return redirectUri;
    }

    /**
     * @param redirectUri
     *            the redirectUri to set
     */
    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

}
