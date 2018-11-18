/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.vo;

import com.ningpai.customer.bean.Customer;

/**
 * 会员统计vo
 *
 * @author jiping
 * @version 0.0.1
 * @since 2015年2月3日 下午5:03:04
 */
public class CustomerRankVo extends Customer {


    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    //会员订单的姓名
    private String addressName;

    //会员订单的电话号码
    private String addressPhone;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressPhone() {
        return addressPhone;
    }

    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }
}
