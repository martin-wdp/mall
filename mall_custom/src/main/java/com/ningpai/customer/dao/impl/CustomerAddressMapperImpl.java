/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.manager.base.BasicSqlSupport;

import java.util.List;

/**
 * 会员收货地址接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 下午4:55:37
 * @version 1.0
 */
@Repository("customerAddressMapper")
public class CustomerAddressMapperImpl extends BasicSqlSupport implements
        CustomerAddressMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerAddressMapper#insertSelective(com.ningpai
     * .customer.bean.CustomerAllInfo)
     */
    @Override
    public int insertSelective(CustomerAddress record) {
        return this
                .insert("com.ningpai.customer.dao.CustomerAddressMapper.insertSelective",
                        record);
    }
    /*
     *
     *
     * @see
     * com.ningpai.customer.dao.CustomerAddressMapper#insertSelective(com.ningpai
     * .customer.bean.CustomerAllInfo)
     */
    @Override
    public Long insertSelectiveNew(CustomerAddress record) {
        record.setAddressId(null);
        int cout =  this
                .insert("com.ningpai.customer.dao.CustomerAddressMapper.insertSelective",
                        record);
        if(cout!=1){
            return null;
        }
        return this.selectOne("com.ningpai.customer.dao.CustomerAddressMapper.selectLastId");

    }

    @Override
    public int deleteByPrimaryKey(Long addressId) {
        return this
                .delete("com.ningpai.customer.dao.CustomerAddressMapper.deleteByPrimaryKey",
                        addressId);
    }



    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerAddressMapper#updateByPrimaryKeySelective
     * (com.ningpai.customer.bean.CustomerAddress)
     */
    @Override
    public int updateByPrimaryKeySelective(CustomerAddress customerAddress) {
        return this
                .update("com.ningpai.customer.dao.CustomerAddressMapper.updateByPrimaryKeySelective",
                        customerAddress);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.customer.dao.CustomerAddressMapper#selectDefaultAddr(java
     * .lang.Long)
     */
    @Override
    public CustomerAddress selectDefaultAddr(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerAddressMapper.selectDefaultAddr",
                        customerId);
    }

    @Override
    public CustomerAddress selectByaddressId(Long addressId) {
        return this.selectOne("com.ningpai.customer.dao.CustomerAddressMapper.selectByPrimaryKey", addressId);
    }
    @Override
    public CustomerAddress selectByCIdFirst(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerAddressMapper.selectByCIdFirst",
                        customerId);
    }


    @Override
    public List<CustomerAddress> selectAllCustomerAddressByCustomerId(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.customer.dao.CustomerAddressMapper.selectAllCustomerAddressByCustomerId",
                        customerId);
    }
}
