/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.api.bean.Auth;
import com.ningpai.api.dao.AuthMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 第三方授权信息DAO实现
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午6:00:15
 * @version 1.0
 */
@Repository("AuthMapper")
public class AuthMapperImpl extends BasicSqlSupport implements AuthMapper {

    /*
     * 
     * 根据ID获取授权信息
     * @see com.ningpai.api.dao.AuthMapper#findAuthById(java.lang.Long)
     */
    @Override
    public Auth findAuthById(Long authId) {
        return this.selectOne("com.ningpai.api.dao.AuthMapper.findAuthById", authId);
    }

    /*
     * 根据授权名称获取授权信息
     * 
     * @see com.ningpai.api.dao.AuthMapper#findAuthByName(java.lang.String)
     */
    @Override
    public Auth findAuthByName(String authName) {
        return this.selectOne("com.ningpai.api.dao.AuthMapper.findAuthByName", authName);
    }

    /*
     * 查询全部未删除的授权信息
     * 
     * @see com.ningpai.api.dao.AuthMapper#findAuths()
     */
    @Override
    public List<Auth> findAuths() {
        return this.selectList("com.ningpai.api.dao.AuthMapper.findAuths");
    }

}
