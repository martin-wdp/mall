/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.gift.bean.Gift;

/**
 * 订单-促销赠品
 * 
 * @author ggn 2014-04-09
 * 
 */
public class OrderGift {
    /**
     * 订单赠品ID
     */
    private Long orderGiftId;
    /**
     * 营销ID
     */
    private Long orderMarketingId;
    /**
     * 赠品ID
     */
    private Long giftId;
    /**
     * 赠品详细
     */
    private Gift gift;

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Long getOrderGiftId() {
        return orderGiftId;
    }

    public void setOrderGiftId(Long orderGiftId) {
        this.orderGiftId = orderGiftId;
    }

    public Long getOrderMarketingId() {
        return orderMarketingId;
    }

    public void setOrderMarketingId(Long orderMarketingId) {
        this.orderMarketingId = orderMarketingId;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }
}
