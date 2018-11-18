/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerPointLevelMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 会员积分等级接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月19日 下午3:28:37
 * @version 1.0
 */
@Repository("customerPointLevelMapper")
public class CustomerPointLevelMapperImpl extends BasicSqlSupport implements
        CustomerPointLevelMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectAllPointLevel()
     */
    @Override
    public List<CustomerPointLevel> selectAllPointLevel() {
        return this
                .selectList("com.ningpai.customer.dao.CustomerPointLevelMapper.selectAll");
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.dao.CustomerPointLevelMapper#selectAllCount()
     */
    @Override
    public Integer selectAllCount() {
        return this
                .selectOne("com.ningpai.customer.dao.CustomerPointLevelMapper.selectAllCount");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectPointLevelByLimit
     * (java.util.Map)
     */
    @Override
    public List<Object> selectPointLevelByLimit(Map<String, Integer> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerPointLevelMapper.selectPointLevelByLimit",
                        paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.customer.dao.CustomerPointLevelMapper#insertSelective
     * (com.ningpai.customer.bean.CustomerPointLevel)
     */
    @Override
    public int insertSelective(CustomerPointLevel customerPointLevel) {
        return this
                .insert("com.ningpai.customer.dao.CustomerPointLevelMapper.insertSelective",
                        customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectByPrimaryKey(
     * java.lang.Long)
     */
    @Override
    public CustomerPointLevel selectByPrimaryKey(Long pointLelvelId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPointLevelMapper.selectByPrimaryKey",
                        pointLelvelId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.CustomerPointLevel)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerPointLevel customerPointLevel) {
        return this
                .update("com.ningpai.customer.dao.CustomerPointLevelMapper.updateByPrimaryKeySelective",
                        customerPointLevel);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#deleteByPrimaryKey(
     * java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long pointLelvelId) {
        return this
                .delete("com.ningpai.customer.dao.CustomerPointLevelMapper.deleteByPrimaryKey",
                        pointLelvelId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectByPrimaryName
     * (java.lang.String)
     */
    @Override
    public Long selectByPrimaryName(String pointLevelName) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPointLevelMapper.selectByName",
                        pointLevelName);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectDefaultPointLevel
     * ()
     */
    @Override
    public Long selectDefaultPointLevel() {
        return this
                .selectOne("com.ningpai.customer.dao.CustomerPointLevelMapper.selectDefaultPointLevel");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#deletePointLevelByIds
     * (java.util.Map)
     */
    @Override
    public Integer deletePointLevelByIds(Map<String, Object> paramMap) {
        return this
                .update("com.ningpai.customer.dao.CustomerPointLevelMapper.deletePointLevelByIds",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#cancelBeforeDefault()
     */
    @Override
    public int cancelBeforeDefault() {
        return this
                .update("com.ningpai.customer.dao.CustomerPointLevelMapper.cancelBeforeDefault");
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectDefaultCustomerLevel
     * ()
     */
    @Override
    public CustomerPointLevel selectDefaultCustomerLevel() {
        return this
                .selectOne("com.ningpai.customer.dao.CustomerPointLevelMapper.selectDefaultCustomerLevel");
    }

    /*
     * 
     * @see
     * com.ningpai.customer.dao.CustomerPointLevelMapper#selectCustomerLevelById
     * (java.lang.Long)
     */
    @Override
    public CustomerPointLevel selectCustomerLevelById(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerPointLevelMapper.selectCustomerLevelById",
                        customerId);
    }

    /**
     * 查询所有设置为默认等级的行数
     *
     * @return int
     */
    @Override
    public int queryIsDefaultCount() {
        return this.selectOne("com.ningpai.customer.dao.CustomerPointLevelMapper.selectIsDefaultCount");
    }

}
