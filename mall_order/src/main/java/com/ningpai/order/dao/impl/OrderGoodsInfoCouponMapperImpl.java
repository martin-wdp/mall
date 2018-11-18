/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderGoodsInfoCouponMapper")
public class OrderGoodsInfoCouponMapperImpl extends BasicSqlSupport implements
        OrderGoodsInfoCouponMapper {

    /**
     * 查询货品赠送的优惠券
     * @param orderGoodsId
     *            {@link Long}
     * @return
     */
    public List<OrderGoodsInfoCoupon> selectOrderGoodsInfoCoupon(
            Long orderGoodsId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsInfoCouponMapper.selectOrderGoodsInfoCoupon",
                        orderGoodsId);
    }

    /**
     * 插入商品赠送优惠券
     * @param list
     * @return
     */
    public int insertOrderInfoCoupon(List<OrderGoodsInfoCoupon> list) {
        return this
                .insert("com.ningpai.web.dao.OrderGoodsInfoCouponMapper.insertOrderInfoCoupon",
                        list);
    }

}
