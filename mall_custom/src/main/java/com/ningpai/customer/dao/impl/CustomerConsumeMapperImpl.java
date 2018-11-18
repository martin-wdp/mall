/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 *@see
 *@see 
 */
package com.ningpai.customer.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.dao.CustomerConsumeMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.customer.dao.CustomerConsumeMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午3:08:38
 * @version 0.0.1
 */
@Repository("customerConsumeMapper")
public class CustomerConsumeMapperImpl extends BasicSqlSupport implements
        CustomerConsumeMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#deleteByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long balanceId) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#insert(com.ningpai.customer
     * .bean.CustomerConsume)
     */
    @Override
    public int insert(CustomerConsume record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#insertSelective(com.ningpai
     * .customer.bean.CustomerConsume)
     */
    @Override
    public int insertSelective(CustomerConsume record) {
        return this
                .insert("com.ningpai.customer.dao.CustomerConsumeMapper.insertSelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#selectByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public CustomerConsume selectByPrimaryKey(Long balanceId) {
        return null;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.CustomerConsume)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerConsume record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#updateByPrimaryKey(com
     * .ningpai.customer.bean.CustomerConsume)
     */
    @Override
    public int updateByPrimaryKey(CustomerConsume record) {
        return 0;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#selectCustmerConsumeSize(
     * com.ningpai.customer.bean.CustomerConsume)
     */
    @Override
    public int selectCustmerConsumeSize(CustomerConsume consume) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerConsumeMapper.selectCustmerConsumeSize",
                        consume);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#selectCustConsumeByParamMap
     * (java.util.Map)
     */
    @Override
    public List<Object> selectCustConsumeByParamMap(Map<String, Object> paramMap) {
        return this
                .selectList(
                        "com.ningpai.customer.dao.CustomerConsumeMapper.selectCustConsumeByParamMap",
                        paramMap);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerConsumeMapper#deleteCustomerConsumeByBids
     * (java.util.Map)
     */
    @Override
    public int deleteCustomerConsumeByBids(Map<String, Object> paramMap) {
        return this
                .delete("com.ningpai.customer.dao.CustomerConsumeMapper.deleteCustomerConsumeByBids",
                        paramMap);
    }

}
