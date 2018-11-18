/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.dao;

import com.ningpai.customer.bean.CustomerAddress;

import java.util.List;

/**
 * 会员收货地址接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月20日 上午9:25:44
 * @version 1.0
 */
public interface CustomerAddressMapper {

    /**
     * 添加收货地址
     * 
     * @param record
     * @return
     */
    int insertSelective(CustomerAddress record);
    /**
     * 添加收货地址
     *
     * @param record
     * @return
     */
    Long insertSelectiveNew(CustomerAddress record);

    /**
     * 根据id删除售后地址
     * @param addressId
     * @return
     */
    int deleteByPrimaryKey(Long addressId);



    /**
     * 修改收货地址信息
     * 
     * @param
     * @return
     */
    int updateByPrimaryKeySelective(CustomerAddress customerAddress);

    /**
     * 根据会员编号查找对应的默认收货地址
     * 
     * @param customerId
     *            会员编号
     * @return 会员收货地址
     */
    CustomerAddress selectDefaultAddr(Long customerId);

    /**
     * 根据收货地址id查询收货信息
     * @return
     */
    CustomerAddress selectByaddressId(Long addressId);

    /**
     * 查询用户上一次收货地址
     * 
     * @param customerId
     * @return
     */
    CustomerAddress selectByCIdFirst(Long customerId);

    /**
     * 根据用户Id查询所有地址
     * @param customerId
     * @return
     */
    List<CustomerAddress> selectAllCustomerAddressByCustomerId(Long customerId);
}
