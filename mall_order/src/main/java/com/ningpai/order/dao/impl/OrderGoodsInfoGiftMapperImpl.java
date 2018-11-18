/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderGoodsInfoGift;
import com.ningpai.order.dao.OrderGoodsInfoGiftMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderGoodsInfoGiftMapper")
public class OrderGoodsInfoGiftMapperImpl extends BasicSqlSupport implements
        OrderGoodsInfoGiftMapper {

    /**
     * 查询货品促销赠品信息
     * @param orderGoodsId
     *            {@link Long}
     * @return
     */
    public List<OrderGoodsInfoGift> selectOrderGoodsInfoGift(Long orderGoodsId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsInfoGiftMapper.selectOrderGoodsInfoGift",
                        orderGoodsId);
    }

    /**
     * 插入赠品信息
     * @param list
     * @return
     */
    public int insertOrderInfoGift(List<OrderGoodsInfoGift> list) {
        return this
                .insert("com.ningpai.web.dao.OrderGoodsInfoGiftMapper.insertOrderInfoGift",
                        list);
    }

}
