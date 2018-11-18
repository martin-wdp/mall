/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.service;

import com.ningpai.customer.bean.CustomerAddress;

import java.util.List;

/**
 * 收货地址Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月20日 下午1:52:48
 * @version 0.0.1
 */
public interface CustomerAddressService {
    /**
     * 根据会员编号查询会员收货地址
     * 
     * @param customerId
     *            会员编号 {@link Long}
     * @return List<Object> 收货地址集合 {@link List}
     */
    List<Object> queryCustomerAddress(Long customerId);

    /**
     * 根据会员编号和地址编号查找会员收货地址
     * 
     * @param addressId
     *            地址编号 {@link Long}
     * @param customerId
     *            会员编号 {@link Long}
     * @return 地址对象 {@link CustomerAddress}
     */
    CustomerAddress queryCustomerAddressById(Long addressId, Long customerId);

    /**
     * 根据会员id查询默认地址
     * @param customerId
     * @return
     */
    CustomerAddress queryDefaultAddr(Long customerId);

    /**
     * 修改收货地址
     * 
     * @param address
     *            收货地址
     * @param customerId
     * @return 0修改失败/地址编号与用户不匹配 1修改成功
     */
    int updateAddress(CustomerAddress address, Long customerId);
    /**
     * 修改用户的地址为 0 默认
     *
     * @param customerId
     * @return
     */
    int updateAddressDef(Long customerId);

    /**
     * 添加收货地址
     * 
     * @param address
     *            收货地址
     * @param attribute
     * @return 0修改失败/地址编号与用户不匹配 1修改成功
     */
    int addAddress(CustomerAddress address, Long attribute);

    /**
     * 根据会员id查询上次选中的收货地址
     * 
     * @param customerId
     * @return 收货地址
     * @author NINGPAI-LIH
     */
    CustomerAddress selectByCIdFirst(Long customerId);
}
