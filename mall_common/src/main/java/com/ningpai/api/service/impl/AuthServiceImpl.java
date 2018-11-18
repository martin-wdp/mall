/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.api.bean.Auth;
import com.ningpai.api.dao.AuthMapper;
import com.ningpai.api.service.AuthService;

/**
 * @author NINGPAI-ZHOUY
 * @since 2013年12月24日上午9:36:36
 * @version 1.0
 */
@Service("AuthService")
public class AuthServiceImpl implements AuthService {

    private AuthMapper authMapper;

    /**
     * @return the authMapper
     */
    public AuthMapper getAuthMapper() {
        return authMapper;
    }

    /**
     * @param authMapper
     *            the authMapper to set
     */
    @Resource(name = "AuthMapper")
    public void setAuthMapper(AuthMapper authMapper) {
        this.authMapper = authMapper;
    }

    /*
     * 根据ID获取授权信息
     * 
     * @see com.ningpai.api.service.AuthService#findAuthById(java.lang.Long)
     */
    @Override
    public Auth findAuthById(Long authId) {
        return authMapper.findAuthById(authId);
    }

    /*
     * 根据授权名称获取授权信息
     * 
     * @see com.ningpai.api.service.AuthService#findAuthByName(java.lang.String)
     */
    @Override
    public Auth findAuthByName(String authName) {
        return authMapper.findAuthByName(authName);
    }

    /*
     * 查询全部未删除的授权信息
     * 
     * @see com.ningpai.api.service.AuthService#findAuths()
     */
    @Override
    public List<Auth> findAuths() {
        return authMapper.findAuths();
    }

}
