/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.vo;

/**
 * 订单装箱单关系实体
 * 
 * @author jiping
 * @since 2014年11月7日 下午1:47:05
 * @version 0.0.1
 */
public class ExpressNoVo {
    /**
     * 关系id
     */
    private Long relationId;

    /**
     * 物流单号
     */
    private String expressNo;

    /**
     * 运单名称
     */
    private String expressName;

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
