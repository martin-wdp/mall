/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.dao;

import java.util.List;

import com.ningpai.api.bean.Auth;

/**
 * 第三方授权信息DAO
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午5:52:19
 * @version 1.0
 */
public interface AuthMapper {

    /**
     * 根据ID获取授权信息
     * 
     * @param authId
     *            ID
     * @return 第三方授权信息
     */
    Auth findAuthById(Long authId);

    /**
     * 根据授权名称获取授权信息
     * 
     * @param authName
     *            授权名称
     * @return 第三方授权信息
     */
    Auth findAuthByName(String authName);

    /**
     * 查询全部未删除的授权信息
     * 
     * @return 第三方授权信息
     */
    List<Auth> findAuths();
}
