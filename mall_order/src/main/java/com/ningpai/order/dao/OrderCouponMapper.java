/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderCoupon;

/**
 * @author ggn
 * 
 */
public interface OrderCouponMapper {

    /**
     * 查询订单赠送的优惠券
     * 
     * @param orderMarketingId
     *            {@link Long}
     * @return List
     */
    List<OrderCoupon> selectOrderCoupon(Long orderMarketingId);

    /**
     * 插入所有的订单促销赠送的优惠劵
     * 
     * @param coupons
     */
    void insertCouponInfoGift(List<OrderCoupon> coupons);

}
