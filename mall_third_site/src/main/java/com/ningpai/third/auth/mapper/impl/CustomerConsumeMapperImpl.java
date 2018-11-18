/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.third.auth.mapper.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.auth.bean.CustomerConsume;
import com.ningpai.third.auth.mapper.CustomerConsumeMapper;

/**
 * <p>DAO实现类-会员消费记录</p>
 * @author zhanghl
 * @since 20150730
 * @version 2.0
 */
@Repository("siteCustomerConsumeMapper")
public class CustomerConsumeMapperImpl extends BasicSqlSupport implements CustomerConsumeMapper {

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#deleteByPrimaryKey (java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long balanceId) {

        return this.delete("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.deleteByPrimaryKey", balanceId);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#insert(com.ningpai .site.customer.bean.CustomerConsume)
     */
    @Override
    public int insert(CustomerConsume record) {

        return this.insert("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#insertSelective (com.ningpai.site.customer.bean.CustomerConsume)
     */
    @Override
    public int insertSelective(CustomerConsume record) {

        return this.insert("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper# updateByPrimaryKeySelective (com.ningpai.site.customer.bean.CustomerConsume)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerConsume record) {

        return this.update("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#updateByPrimaryKey (com.ningpai.site.customer.bean.CustomerConsume)
     */
    @Override
    public int updateByPrimaryKey(CustomerConsume record) {

        return this.update("", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#selectByPrimaryKey (java.lang.Long)
     */
    @Override
    public CustomerConsume selectByPrimaryKey(Long balanceId) {

        return this.selectOne("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.selectByPrimaryKey", balanceId);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#queryConsumeByCidCount (java.util.Map)
     */
    @Override
    public int queryConsumeByCidCount(Map<String, Object> map) {

        return this.selectOne("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.queryConsumeByCidCount", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#queryAllConsumeByCid (java.util.Map)
     */
    @Override
    public List<Object> queryAllConsumeByCid(Map<String, Object> map) {

        return this.selectList("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.queryAllConsumeByCid", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.site.customer.mapper.CustomerConsumeMapper#selectTotalNumByCid (java.lang.Long)
     */
    @Override
    public BigDecimal selectTotalNumByCid(Long customerId) {

        return this.selectOne("com.ningpai.third.mybatis.mapper.CustomerConsumeMapper.selectTotalNumByCid", customerId);
    }

}
