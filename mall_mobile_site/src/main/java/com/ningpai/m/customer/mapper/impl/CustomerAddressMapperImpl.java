/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper.impl;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.m.customer.mapper.CustomerAddressMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.m.customer.mapper.CustomerAddressMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 下午1:51:12
 * @version 0.0.1
 */
@Repository("customerAddressMapperM")
public class CustomerAddressMapperImpl extends BasicSqlSupport implements CustomerAddressMapper {

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerAddressMapper#queryCustomerAddress(java.lang.Long)
     */
    @Override
    public List<Object> queryCustomerAddress(Long customerId) {
        return this.selectList("com.ningpai.m.customer.mapper.CustomerAddressMapper.selectByCId", customerId);
    }

    @Override
    public CustomerAddress selectDefaultAddr(Long customerId) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerAddressMapper.selectDefaultAddr", customerId);
    }
    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerAddressMapper#queryCustomerAddressById(java.util.Map)
     */
    @Override
    public CustomerAddress queryCustomerAddressById(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerAddressMapper.queryCustomerAddressById", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerAddressMapper#updateAddress(com.ningpai.customer.bean.CustomerAddress)
     */
    @Override
    public int updateAddress(CustomerAddress address) {
        return this.update("com.ningpai.m.customer.mapper.CustomerAddressMapper.updateAddress", address);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerAddressMapper#addAddress(com.ningpai.customer.bean.CustomerAddress)
     */
    @Override
    public int addAddress(CustomerAddress address) {
        return this.insert("com.ningpai.m.customer.mapper.CustomerAddressMapper.insertSelective", address);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.mapper.CustomerAddressMapper#selectByCIdFirst(java.lang.Long)
     */
    @Override
    public CustomerAddress selectByCIdFirst(Long customerId) {
        return this.selectOne("com.ningpai.m.customer.mapper.CustomerAddressMapper.selectByCIdFirst", customerId);
    }
    /**
     * 修改用户的地址为 0 默认
     *
     * @param customerId
     * @return
     */
    @Override
    public int updateAddressDef(Long customerId) {
        return this.update("com.ningpai.m.customer.mapper.CustomerAddressMapper.updateAddressDef", customerId);
    }

}
