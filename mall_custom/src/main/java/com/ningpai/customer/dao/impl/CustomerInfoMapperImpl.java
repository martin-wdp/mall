/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.other.bean.CustomerAllInfo;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 会员详细信息接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月19日 下午3:29:13
 * @version
 */
@Repository("customerInfoMapper")
public class CustomerInfoMapperImpl extends BasicSqlSupport implements
        CustomerInfoMapper {
    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerInfoMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.CustomerAllInfo)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerAllInfo customerInfo) {
        return this
                .update("com.ningpai.customer.dao.CustomerInfoMapper.updateByPrimaryKeySelective",
                        customerInfo);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerInfoMapper#selectCustInfoById(java.lang
     * .Long)
     */
    @Override
    public CustomerInfo selectCustInfoById(Long customerId) {
        return this.selectOne("com.ningpai.customer.dao.CustomerInfoMapper.selectCustInfoById", customerId);
    }

    @Override
    public CustomerInfo selectByPrimaryKey(Long customerId) {
        return this.selectOne("com.ningpai.customer.dao.CustomerInfoMapper.selectByPrimaryKey", customerId);
    }   @Override
    public CustomerInfo selectByCustomerId(Long customerId) {
        return this.selectOne("com.ningpai.customer.dao.CustomerInfoMapper.selectByCustomerId", customerId);
    }

    @Override
    public CustomerInfo selectByMobile(String infoMobile) {
        return this.selectOne("com.ningpai.customer.dao.CustomerInfoMapper.selectByMobile", infoMobile);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerInfoMapper#updateInfoByCustId(com.ningpai
     * .customer.bean.CustomerInfo)
     */
    @Override
    public int updateInfoByCustId(CustomerInfo info) {
        return this
                .update("com.ningpai.customer.dao.CustomerInfoMapper.updateInfoByCustId",
                        info);
    }

    @Override
    public CustomerInfo email(Long customerId) {

        return this.selectOne(
                "com.ningpai.customer.dao.CustomerInfoMapper.selectemail",
                customerId);
    }

    @Override
    public CustomerInfo mobile(Long customerId) {

        return this.selectOne(
                "com.ningpai.customer.dao.CustomerInfoMapper.selectmobile",
                customerId);
    }

    // @Override
    // public int updateCusSumPoint(Map<String, Object> paraMap) {
    //
    // return
    // this.update("com.ningpai.customer.dao.CustomerInfoMapper.updateCusSumPoint",
    // paraMap);
    // }

    @Override
    public Customer selectCusById(Long cusId) {
        return this.selectOne(
                "com.ningpai.customer.dao.CustomerMapper.selectCusById", cusId);
    }

    @Override
    public int insertRegisterPoint(RegisterPoint point) {
        return this.insert(
                "com.ningpai.customer.dao.CustomerMapper.insertRegisterPoint",
                point);
    }

    @Override
    public int upCusLevel(Map<String, Object> paraMap) {
        return this.update(
                "com.ningpai.customer.dao.CustomerMapper.upCusLevel", paraMap);
    }

    @Override
    public int insertSelective(CustomerAllInfo customerAllInfo) {
        return this.insert("com.ningpai.customer.dao.CustomerInfoMapper.insertSelective",customerAllInfo);
    }

}
