/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.dao.OrderCouponMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderCouponMapper")
public class OrderCouponMapperImpl extends BasicSqlSupport implements
        OrderCouponMapper {

    /**
     * 查询订单赠送的优惠券
     * @param orderMarketingId
     *            {@link Long}
     * @return
     */
    public List<OrderCoupon> selectOrderCoupon(Long orderMarketingId) {
        return this.selectList(
                "com.ningpai.web.dao.OrderCouponMapper.selectOrderCoupon",
                orderMarketingId);
    }

    /**
     * 插入所有的订单促销赠送的优惠劵
     * @param coupons
     */
    public void insertCouponInfoGift(List<OrderCoupon> coupons) {
        this.insert(
                "com.ningpai.web.dao.OrderCouponMapper.insertCouponInfoGift",
                coupons);
    }

}
