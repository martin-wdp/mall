/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.sld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.third.sld.bean.DomainCustom;
import com.ningpai.third.sld.dao.impl.DomainCustomMapperImpl;
import com.ningpai.third.sld.service.DomainCustomService;

/**
 * 二级域名关联service实现类
 * @author jiping 
 * @since 2014年12月4日 上午9:33:39 
 * @version 0.0.1
 */
@Service("domainCustomService")
public class DomainCustomServiceImpl implements DomainCustomService{
    //spring注解
    private DomainCustomMapperImpl domainCustomMapper;
    
    public DomainCustomMapperImpl getDomainCustomMapper() {
        return domainCustomMapper;
    }

    @Resource(name="domainCustomMapper")
    public void setDomainCustomMapper(DomainCustomMapperImpl domainCustomMapper) {
        this.domainCustomMapper = domainCustomMapper;
    }

    /*
     * 
     * @see com.ningpai.third.sld.service.DomainCustomService#findDomainCustom(java.lang.Long)
     */
    @Override
    public DomainCustom findDomainCustom(Long customerId) {
        return domainCustomMapper.findByCustId(customerId);
    }

    /*
     * 
     * @see com.ningpai.third.sld.service.DomainCustomService#updateDomain(com.ningpai.third.sld.bean.DomainCustom)
     */
    @Override
    public int updateDomain(DomainCustom domainCustom) {
        return domainCustomMapper.updateByPrimaryKeySelective(domainCustom);
    }

    /*
     * 
     * @see com.ningpai.third.sld.service.DomainCustomService#queryDomainByID(java.lang.Long)
     */
    @Override
    public DomainCustom queryDomainByID(Long dmCuId) {
        return domainCustomMapper.selectByPrimaryKey(dmCuId);
    }

    /*
     * 
     * @see com.ningpai.third.sld.service.DomainCustomService#queryByDomain(java.lang.String)
     */
    @Override
    public Long queryByDomain(String domain) {
        return domainCustomMapper.queryByDomain(domain);
    }

    /*
     * 
     * @see com.ningpai.third.sld.service.DomainCustomService#findAll()
     */
    @Override
    public List<DomainCustom> findAll() {
        return domainCustomMapper.findAll();
    }

}
