/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper;

import com.ningpai.customer.bean.CustomerAddress;

import java.util.List;
import java.util.Map;

/**
 * 收货地址Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 下午1:49:58
 * @version 0.0.1
 */
public interface CustomerAddressMapper {
    /**
     * 根据会员编号查询会员收货地址
     * 
     * @param customerId
     *            会员编号 {@link Long}
     * @return List<Object> 收货地址集合 {@link List}
     */
    List<Object> queryCustomerAddress(Long customerId);

    /**
     * 根据会员id查询默认地址
     * @param customerId
     * @return
     */
    CustomerAddress selectDefaultAddr(Long customerId);

    /**
     * 根据会员编号和地址编号查找会员收货地址
     * @return 地址对象 {@link CustomerAddress}
     */
    CustomerAddress queryCustomerAddressById(Map<String, Object> paramMap);

    /**
     * 修改收货地址
     * 
     * @param address
     * @return 0修改失败/地址编号与用户不匹配 1修改成功
     */
    int updateAddress(CustomerAddress address);

    /**
     * 添加收货地址
     * 
     * @param address
     * @return 0添加失败 1添加成功
     */
    int addAddress(CustomerAddress address);

    /**
     * 根据会员编号查询会员第一条收货地址
     * 
     * @param customerId
     *            用户id
     * @return
     */
    CustomerAddress selectByCIdFirst(Long customerId);
    /**
     * 修改用户的地址为 0 默认
     *
     * @param customerId
     * @return
     */
    int updateAddressDef(Long customerId);
}
