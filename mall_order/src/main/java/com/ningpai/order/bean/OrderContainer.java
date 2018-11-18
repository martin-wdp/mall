/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.gift.bean.Gift;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;

/**
 * 装箱单表
 * 
 * @author NINGPAI-LIH
 * @since 2014年7月1日15:25:32
 * 
 */
public class OrderContainer {
    /**
     * 主键id
     */
    private Long containerId;
    /**
     * 商品id
     */
    private Long goodsInfoId;

    /**
     * 商品数量
     */
    private Long goodsNum;

    /**
     * 关系id
     */
    private Long relationId;

    /**
     * 状态 0:表示此为商品 1：表示此为赠品
     */
    private String containerStatus;

    /**
     * 赠品实体
     */
    private Gift gift;

    private GoodsProductDetailViewVo goodsProductDetailViewVo;

    public GoodsProductDetailViewVo getGoodsProductDetailViewVo() {
        return goodsProductDetailViewVo;
    }

    public void setGoodsProductDetailViewVo(
            GoodsProductDetailViewVo goodsProductDetailViewVo) {
        this.goodsProductDetailViewVo = goodsProductDetailViewVo;
    }

    public Long getRelationId() {
        return relationId;
    }

    public void setRelationId(Long relationId) {
        this.relationId = relationId;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public String getContainerStatus() {
        return containerStatus;
    }

    public void setContainerStatus(String containerStatus) {
        this.containerStatus = containerStatus;
    }

    public Long getContainerId() {
        return containerId;
    }

    public void setContainerId(Long containerId) {
        this.containerId = containerId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }
}
