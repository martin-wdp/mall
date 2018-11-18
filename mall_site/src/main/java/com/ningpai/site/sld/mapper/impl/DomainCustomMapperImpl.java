/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.sld.mapper.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.sld.bean.DomainCustom;
import com.ningpai.site.sld.mapper.DomainCustomMapper;

/**
 * @see com.ningpai.site.sld.mapper.DomainCustomMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年10月20日 上午11:19:50
 * @version 0.0.1
 */
 @Repository("domainCustomMapper")
public class DomainCustomMapperImpl extends BasicSqlSupport implements DomainCustomMapper {

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#deleteByPrimaryKey(java .lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long dmCuId) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#insert(com.ningpai.site .sld.bean.DomainCustom)
     */
    @Override
    public int insert(DomainCustom record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#insertSelective(com.ningpai .site.sld.bean.DomainCustom)
     */
    @Override
    public int insertSelective(DomainCustom record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#selectByPrimaryKey(java .lang.Long)
     */
    @Override
    public DomainCustom selectByPrimaryKey(Long dmCuId) {
        return null;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#updateByPrimaryKeySelective (com.ningpai.site.sld.bean.DomainCustom)
     */
    @Override
    public int updateByPrimaryKeySelective(DomainCustom record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#updateByPrimaryKey(com .ningpai.site.sld.bean.DomainCustom)
     */
    @Override
    public int updateByPrimaryKey(DomainCustom record) {
        return 0;
    }

    /*
     * 
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#findAll()
     */
    @Override
    public List<DomainCustom> findAll() {
        return this.selectList("com.ningpai.site.sld.mapper.DomainCustomMapper.findAll");
    }
}
