/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.List;

/**
 * 装箱单关系实体
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:37:35
 * 
 */
public class OrderContainerRelation {
    /**
     * 关系id
     */
    private Long relationId;

    /**
     * 删除标记
     */
    private Long delFlge;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 用来存放装箱单下的所有商品
     */
    private List<OrderContainer> containers;

    /**
     * 是否有商品
     */
    private String isGoodsInfos;

    /**
     * 是否有赠品
     */
    private String isGifts;

    /**
     * 物流单号
     */
    private String expressNo;

    /**
     * 物流id
     */
    private Long orderExpressId;

    /**
     * 运单名称
     */
    private String expressName;

    /**
     * 物流信息链接（快递100返回）
     */
    private String expressInfoUrl;

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

    public Long getOrderExpressId() {
        return orderExpressId;
    }

    public void setOrderExpressId(Long orderExpressId) {
        this.orderExpressId = orderExpressId;
    }

    public String getIsGoodsInfos() {
        return isGoodsInfos;
    }

    public void setIsGoodsInfos(String isGoodsInfos) {
        this.isGoodsInfos = isGoodsInfos;
    }

    public String getIsGifts() {
        return isGifts;
    }

    public void setIsGifts(String isGifts) {
        this.isGifts = isGifts;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public Long getDelFlge() {
        return delFlge;
    }

    public void setDelFlge(Long delFlge) {
        this.delFlge = delFlge;
    }

    public List<OrderContainer> getContainers() {
        return containers;
    }

    public void setContainers(List<OrderContainer> containers) {
        this.containers = containers;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getExpressInfoUrl() {
        return expressInfoUrl;
    }

    public void setExpressInfoUrl(String expressInfoUrl) {
        this.expressInfoUrl = expressInfoUrl;
    }
}
