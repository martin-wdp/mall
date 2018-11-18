/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderGoodsInfoCoupon;

/**
 * @author ggn
 * 
 */
public interface OrderGoodsInfoCouponMapper {

    /**
     * 查询货品赠送的优惠券
     * 
     * @param orderGoodsId
     *            {@link Long}
     * @return List
     */
    List<OrderGoodsInfoCoupon> selectOrderGoodsInfoCoupon(Long orderGoodsId);

    /**
     * 插入商品赠送优惠券
     * 
     * @param list
     * @return int
     */
    int insertOrderInfoCoupon(List<OrderGoodsInfoCoupon> list);
}
