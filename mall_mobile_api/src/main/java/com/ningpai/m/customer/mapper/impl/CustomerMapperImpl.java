/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.m.customer.bean.Customer;
import com.ningpai.m.customer.mapper.CustomerMapper;
import com.ningpai.m.customer.vo.CustomerAllInfo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.m.customer.mapper.CustomerMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年8月19日 上午11:17:19
 * @version 0.0.1
 */
@Repository("customerMapperM")
public class CustomerMapperImpl extends BasicSqlSupport implements CustomerMapper {

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#updateByPrimaryKeySelective (com.ningpai.customer.bean.Customer)
     */
    @Override
    public int updateByPrimaryKeySelective(Customer record) {
        return this.update("com.ningpai.m.customer.mapper.CustomerMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#checkExistsByCustNameAndType (java.util.Map)
     */
    @Override
    public Long checkExistsByCustNameAndType(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.checkExistsByCustNameAndType", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#selectCustomerByNamePwdAndType (java.util.Map)
     */
    @Override
    public Customer selectCustomerByNamePwdAndType(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.selectCustomerByNamePwdAndType", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#selectByPrimaryKey(java. lang.Long)
     */
    @Override
    public CustomerAllInfo selectByPrimaryKey(Long customerId) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerMapper.selectByPrimaryKey", customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#selectCaptcha(java.lang. Long)
     */
    @Override
    public Customer selectCaptcha(Long customerId) {
        return this.selectOne("com.ningpai.customer.dao.CustomerMapper.selectCaptcha", customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#updateSmsCaptcha(com.ningpai .customer.bean.Customer)
     */
    @Override
    public int updateSmsCaptcha(Customer customer) {
        return this.update("com.ningpai.customer.dao.CustomerMapper.updateSmsCaptcha", customer);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerMapper#updateCusomerPwd(com.ningpai .m.customer.vo.CustomerAllInfo)
     */
    @Override
    public int updateCusomerPwd(CustomerAllInfo allInfo) {
        return this.update("com.ningpai.m.customer.mapper.CustomerMapper.updateCusomerPwd", allInfo);
    }
    /*
     * 修改用户信息
     * (non-Javadoc)
     * @see com.ningpai.m.customer.mapper.CustomerMapper#updateCustomerInfo(com.ningpai.m.customer.vo.CustomerAllInfo)
     */
    @Override
    public int updateCustomerInfo(CustomerAllInfo allInfo) {
        return this.update("com.ningpai.m.customer.mapper.CustomerMapper.updateByInfoSelective", allInfo);
    }

    /*
     * 积分数量
     * @see com.ningpai.m.customer.mapper.CustomerMapper#queryPointMCount(java.util.Map)
     */
    @Override
    public Long queryPointMCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.msite.customer.dao.CustomerPointMapper.queryPointRcCount", paramMap);
    }

    /*
     * 积分列表
     * @see com.ningpai.m.customer.mapper.CustomerMapper#queryAllPointMList(java.util.Map)
     */
    @Override
    public List<Object> queryAllPointMList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.msite.customer.dao.CustomerPointMapper.queryAllPointRc", paramMap);
    }

}
