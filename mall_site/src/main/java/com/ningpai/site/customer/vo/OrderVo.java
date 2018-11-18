/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.vo;

/**
 * 查询订单所需条件
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年2月14日 上午10:56:08
 * @version 0.0.1
 */
public class OrderVo {
    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 订单状态
     * 
     * @see #getStatus()
     * @see #setStatus(Long)
     */
    private Long status;
    /**
     * 发货状态
     * 
     * @see #getShipStatus()
     * @see #setShipStatus(Long)
     */
    private Long shipStatus;

    /**
     * 退款状态
     * 
     * @see #getRefundStatus()
     * @see #setRefundStatus(Character)
     */
    private Character refundStatus;
    /**
     * 商品名称
     * 
     * @see #getGoodsName()
     * @see #setGoodsName(String)
     */
    private String goodsName;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getShipStatus() {
        return shipStatus;
    }

    public void setShipStatus(Long shipStatus) {
        this.shipStatus = shipStatus;
    }

    public Character getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(Character refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

}
