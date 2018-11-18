package com.ningpai.cloudshop.service;

import com.ningpai.cloudshop.bean.CloudshopAuthorInfo;
import com.qianmi.open.api.ApiException;
import com.qianmi.open.api.response.TokenResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户授权信息service Created by liangck on 15/6/25.
 */
public interface CloudshopAuthorInfoService {

    /**
     * 插入用户授权信息
     * 
     * @param authorInfo
     *            用户授权信息
     * @return 操作结果
     */
    boolean insertAuthorInfo(CloudshopAuthorInfo authorInfo);

    /**
     * 更新用户授权信息
     * 
     * @param authorInfo
     *            用户授权信息
     * @return 操作结果
     */
    boolean updateAuthorInfo(CloudshopAuthorInfo authorInfo);

    /**
     * 获取云销用户授权信息（user_id accessToken refresh_token 等信息）
     * 
     * @param code
     *            授权码
     * @return 用户授权信息
     */
    TokenResponse getAuthodAccessInfo(String code) throws ApiException;

    /**
     * 处理得到到授权信息
     * 
     * @param response
     *            tokenResponse
     * @return 用户授权信息，为null时，获取授权失败
     */
    CloudshopAuthorInfo processTokenResponse(HttpServletRequest request,
            TokenResponse response);

    /**
     *
     * @return
     */
    CloudshopAuthorInfo queryAuthoredInfo();

    /**
     * 清楚授权账户信息
     * 
     * @return 操作结果
     */
    boolean clearAuthorInfo();
}
