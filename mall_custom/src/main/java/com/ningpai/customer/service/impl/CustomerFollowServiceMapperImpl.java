/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.bean.CustomerFollow;
import com.ningpai.customer.dao.CustomerFollowMapper;
import com.ningpai.customer.service.CustomerFollowServiceMapper;

/**
 * @see com.ningpai.customer.service.CustomerFollowServiceMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年4月1日 下午5:56:53
 * @version 0.0.1
 */
@Service("customerFollowServiceMapper")
public class CustomerFollowServiceMapperImpl implements
        CustomerFollowServiceMapper {
    // spring 注解
    private CustomerFollowMapper customerFollowMapper;

    public CustomerFollowMapper getCustomerFollowMapper() {
        return customerFollowMapper;
    }

    @Resource(name = "customerFollowMapper")
    public void setCustomerFollowMapper(
            CustomerFollowMapper customerFollowMapper) {
        this.customerFollowMapper = customerFollowMapper;
    }

    /*
     * 根据会员编号查找会员关注商品
     * 
     * @see
     * com.ningpai.customer.service.CustomerFollowServiceMapper#selectByCustomerId
     * (java.lang.Long)
     */
    @Override
    public List<CustomerFollow> selectByCustomerId(Long customerId) {
        return customerFollowMapper.selectCustFollowByCustId(customerId);
    }
    /*
     * 根据会员编号查找会员关注商品Count
     *
     * @see
     * com.ningpai.customer.service.CustomerFollowServiceMapper#selectByCustomerId
     * (java.lang.Long)
     */
    @Override
    public int selectByCustomerIdCount(Long customerId) {
        return customerFollowMapper.selectCustFollowByCustIdCount(customerId);
    }

    /*
     * 根据货品id查询关注的会员id
     * 
     * @see com.ningpai.customer.service.CustomerFollowServiceMapper#selectSendId(java.lang.Long)
     */
    @Override
    public List<CustomerFollow> selectSendId(Long goodsInfoId) {

        return customerFollowMapper.selectSendId(goodsInfoId);
    }

}
