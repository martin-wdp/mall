/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.vo;

/**
 * 订单装箱单关系实体
 * 
 * @author jiping
 * @since 2014年11月7日 下午1:47:05
 * @version 0.0.1
 */
public class ExpressNoVo {
    // 关系id
    private Long relationId;

    // 物流单号
    private String expressNo;

    // 运单名称
    private String expressName;

    private Long orderExpressId;

    /**
     * 快递查询的后反返回的URL地址
     */
    private String expressUrl;

    public String getExpressUrl() {
        return expressUrl;
    }

    public void setExpressUrl(String expressUrl) {
        this.expressUrl = expressUrl;
    }

    public Long getOrderExpressId() {
        return orderExpressId;
    }

    public void setOrderExpressId(Long orderExpressId) {
        this.orderExpressId = orderExpressId;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

}
