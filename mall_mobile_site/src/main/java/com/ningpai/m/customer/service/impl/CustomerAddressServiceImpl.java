/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.service.impl;

import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.m.customer.mapper.CustomerAddressMapper;
import com.ningpai.m.customer.service.CustomerAddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @see com.ningpai.m.customer.service.CustomerAddressService
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 下午1:54:29
 * @version 0.0.1
 */
@Service("customerAddressServiceM")
public class CustomerAddressServiceImpl implements CustomerAddressService {
    // spring注入属性
    private CustomerAddressMapper customerAddressMapper;

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerAddressService#queryCustomerAddress(java.lang.Long)
     */
    @Override
    public List<Object> queryCustomerAddress(Long customerId) {
        return customerAddressMapper.queryCustomerAddress(customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerAddressService#queryCustomerAddressById(java.lang.Long, java.lang.Long)
     */
    @Override
    public CustomerAddress queryCustomerAddressById(Long addressId, Long customerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("addressId", addressId);
        paramMap.put("customerId", customerId);
        return customerAddressMapper.queryCustomerAddressById(paramMap);
    }
    @Override
    public CustomerAddress queryDefaultAddr(Long customerId) {
        return customerAddressMapper.selectDefaultAddr(customerId);
    }
    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerAddressService#updateAddress(com.ningpai.customer.bean.CustomerAddress, java.lang.Long)
     */
    @Override
    public int updateAddress(CustomerAddress address, Long customerId) {
        address.setCustomerId(customerId);
        return customerAddressMapper.updateAddress(address);
    }
    /**
     * 修改用户的地址为 0 默认
     *
     * @param customerId
     * @return
     */
    @Override
    public int updateAddressDef(Long customerId) {
        return customerAddressMapper.updateAddressDef(customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerAddressService#selectByCIdFirst(java.lang.Long)
     */
    @Override
    public CustomerAddress selectByCIdFirst(Long customerId) {
        return customerAddressMapper.selectByCIdFirst(customerId);
    }

    /*
     * 
     * 
     * @see com.ningpai.m.customer.service.CustomerAddressService#addAddress(com.ningpai.customer.bean.CustomerAddress, java.lang.Long)
     */
    @Override
    public int addAddress(CustomerAddress address, Long customerId) {
        address.setCustomerId(customerId);
        return customerAddressMapper.addAddress(address);
    }

    public CustomerAddressMapper getCustomerAddressMapper() {
        return customerAddressMapper;
    }

    @Resource(name = "customerAddressMapperM")
    public void setCustomerAddressMapper(CustomerAddressMapper customerAddressMapper) {
        this.customerAddressMapper = customerAddressMapper;
    }

}
