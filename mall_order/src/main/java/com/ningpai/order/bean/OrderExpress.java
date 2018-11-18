/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

/**
 * 订单物流信息
 * 
 * @author ggn 2014-04-09
 * 
 */
public class OrderExpress {

    /**
     * 订单物流ID
     */
    private Long orderExpressId;
    /**
     * 订单Id
     */
    private Long orderId;
    /**
     * 物流公司名称
     */
    private String expressName;
    /**
     * 物流号
     */
    private String expressNo;
    /**
     * 物流公司ID
     */
    private Long expressId;
    /**
     * 是否删除
     */
    private String delFlag;
    /**
     * 选择配送方式 0 快递配送 1 上门自提
     */
    private Long expressTypeId;
    /**
     * 配送方式名称
     */
    private String expressTypeName;

    public String getExpressTypeName() {
        return expressTypeName;
    }

    public void setExpressTypeName(String expressTypeName) {
        this.expressTypeName = expressTypeName;
    }

    public Long getExpressTypeId() {
        return expressTypeId;
    }

    public void setExpressTypeId(Long expressTypeId) {
        this.expressTypeId = expressTypeId;
    }

    public Long getOrderExpressId() {
        return orderExpressId;
    }

    public void setOrderExpressId(Long orderExpressId) {
        this.orderExpressId = orderExpressId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
