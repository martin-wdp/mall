/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.pub.customer;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.customer.service.CustomerFollowServiceMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;

/**
 * 会员模块接口调用
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月14日 上午10:05:30
 * @version 0.0.1
 */
@Service("customerPub")
public class CustomerPub {
    // 会员信息服务接口
    private CustomerServiceMapper customerServiceMapper;
    // 会员积分服务接口
    private CustomerPointServiceMapper customerPointServiceMapper;
    // 会员等级服务接口
    private PointLevelServiceMapper pointLevelServiceMapper;
    // 会员关注接口
    private CustomerFollowServiceMapper customerFollowServiceMapper;

    /**
     * 会员积分服务类接口
     * 
     * @return {@link CustomerPointServiceMapper}
     */
    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    /**
     * 会员等级服务接口
     * 
     * @return {@link PointLevelServiceMapper}
     */
    public PointLevelServiceMapper getPointLevelServiceMapper() {
        return pointLevelServiceMapper;
    }

    /**
     * 会员信息服务类接口
     * 
     * @return {@link CustomerServiceMapper}
     */
    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    /**
     * 会员关注服务接口
     * 
     * @return {@link CustomerFollowServiceMapper}
     */
    public CustomerFollowServiceMapper getCustomerFollowServiceMapper() {
        return customerFollowServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    @Resource(name = "customerFollowServiceMapper")
    public void setCustomerFollowServiceMapper(CustomerFollowServiceMapper customerFollowServiceMapper) {
        this.customerFollowServiceMapper = customerFollowServiceMapper;
    }

    @Resource(name = "pointLevelServiceMapper")
    public void setPointLevelServiceMapper(PointLevelServiceMapper pointLevelServiceMapper) {
        this.pointLevelServiceMapper = pointLevelServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

}
