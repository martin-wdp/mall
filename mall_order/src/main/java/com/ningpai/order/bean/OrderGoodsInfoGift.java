/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.gift.bean.Gift;

/**
 * 订单商品赠品信息
 * 
 * @author ggn 2014-04-09
 * 
 */
public class OrderGoodsInfoGift {
    /**
     * 主键ID
     */
    private Long goodsGiftId;
    /**
     * 订单商品明细ID
     */
    private Long orderGoodsId;
    /**
     * 赠品ID
     */
    private Long giftId;
    /**
     * 赠品信息
     */
    private Gift gift;

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Long getGoodsGiftId() {
        return goodsGiftId;
    }

    public void setGoodsGiftId(Long goodsGiftId) {
        this.goodsGiftId = goodsGiftId;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }
}
