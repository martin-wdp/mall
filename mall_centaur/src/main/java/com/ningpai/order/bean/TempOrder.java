/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 临时订单实体
 * 
 * @author qiyuanyuan
 * 
 */
@XStreamAlias("order")
public class TempOrder {

    /*
     * 订单对象
     */
    private OrderInfo orderInfo;

    /*
     * 货品类目
     */
    private List<ProductItem> productInfo;

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public List<ProductItem> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(List<ProductItem> productInfo) {
        this.productInfo = productInfo;
    }

}
