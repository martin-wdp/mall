/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.shoppingcart.bean;

/**
 * 购物车工具类
 * 
 * @author NINGPAI-LIH
 * 
 */
public class ShopCarUtil {
    // 货品ID
    private Long productId;
    // 数量
    private Integer goodsNum;
    // 地区ID
    private Long distinctId;
    // 套装ID
    private Long fitId;
    // 单品团购促销优惠id
    private Long goodsGroupMarketingId;
    // 单品优惠id
    private Long marketId;
    // 活动优惠id
    private Long marketActiveId;
    // 当前订单选中状态
    private Long status;

    public Long getMarketId() {
        return marketId;
    }

    public void setMarketId(Long marketId) {
        this.marketId = marketId;
    }

    public Long getMarketActiveId() {
        return marketActiveId;
    }

    public void setMarketActiveId(Long marketActiveId) {
        this.marketActiveId = marketActiveId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Integer goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

    public Long getFitId() {
        return fitId;
    }

    public void setFitId(Long fitId) {
        this.fitId = fitId;
    }

    public Long getGoodsGroupMarketingId() {
        return goodsGroupMarketingId;
    }

    public void setGoodsGroupMarketingId(Long goodsGroupMarketingId) {
        this.goodsGroupMarketingId = goodsGroupMarketingId;
    }

}
