/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.sld.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.site.sld.bean.DomainCustom;
import com.ningpai.site.sld.mapper.impl.DomainCustomMapperImpl;
import com.ningpai.site.sld.service.DomainCustomService;


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

    @Override
    public DomainCustom findDomainCustom(Long customerId) {
        
        return null;
    }

    @Override
    public int updateDomain(DomainCustom domainCustom) {
        
        return 0;
    }

    @Override
    public DomainCustom queryDomainByID(Long dmCuId) {
        return null;
    }

    @Override
    public Long queryByDomain(String domain) {
        
        return null;
    }

    /*
     * 
     * @see com.ningpai.site.sld.service.DomainCustomService#findAll()
     */
    @Override
    public List<DomainCustom> findAll() {
        return domainCustomMapper.findAll();
    }

}
