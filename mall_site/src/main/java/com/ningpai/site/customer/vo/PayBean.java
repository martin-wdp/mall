/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.vo;

/**
 * 支付方式Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月2日 下午4:35:32
 * @version 1.0
 */
public class PayBean {
    /**
     * 支付方式id
     */
    private Long payId;
    /**
     * 支付方式名称
     */
    private String payName;

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

}
