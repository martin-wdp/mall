/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.sld.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.sld.bean.DomainCustom;
import com.ningpai.third.sld.dao.DomainCustomMapper;
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
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long dmCuId) {
        return 0;
    }
    /*
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#insertSelective(com.ningpai.site.sld.bean.DomainCustom)
     */
    @Override
    public int insertSelective(DomainCustom record) {
        return this.insert("com.ningpai.third.mybatis.mapper.DomainCustomMapper.insertSelective", record);
    }
    /*
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public DomainCustom selectByPrimaryKey(Long dmCuId) {
        return this.selectOne("com.ningpai.third.mybatis.mapper.DomainCustomMapper.selectByPrimaryKey", dmCuId);
    }
    /*
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#updateByPrimaryKeySelective(com.ningpai.site.sld.bean.DomainCustom)
     */
    @Override
    public int updateByPrimaryKeySelective(DomainCustom record) {
        return this.update("com.ningpai.third.mybatis.mapper.DomainCustomMapper.updateByPrimaryKeySelective", record);
    }
    /*
     * 
     * @see com.ningpai.site.sld.mapper.DomainCustomMapper#findAll()
     */
    @Override
    public List<DomainCustom> findAll() {
        return this.selectList("com.ningpai.third.mybatis.mapper.DomainCustomMapper.findAll");
    }
    
    /*
     * 
     * @see com.ningpai.third.sld.dao.DomainCustomMapper#findByCustId(java.lang.Long)
     */
    @Override
    public DomainCustom findByCustId(Long thirdId) {
        DomainCustom domainCustom=null;
        domainCustom=this.selectOne("com.ningpai.third.mybatis.mapper.DomainCustomMapper.findByCustId", thirdId);
        if(domainCustom==null){
            domainCustom=new DomainCustom();
            domainCustom.setCustomerId(thirdId);
            domainCustom.setDomain(thirdId.toString());
            domainCustom.setUseFlag("0");
            this.insert("com.ningpai.third.mybatis.mapper.DomainCustomMapper.insertSelective", domainCustom);
        }
        return domainCustom;
    }
    
    /*
     * 
     * @see com.ningpai.third.sld.dao.DomainCustomMapper#queryByDomain(java.lang.String)
     */
    @Override
    public Long queryByDomain(String domain) {
        return this.selectOne("com.ningpai.third.mybatis.mapper.DomainCustomMapper.findByDomain", domain);
    }
}
