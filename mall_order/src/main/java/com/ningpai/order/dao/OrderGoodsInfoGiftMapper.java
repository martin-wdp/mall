/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderGoodsInfoGift;

/**
 * @author ggn
 * 
 */
public interface OrderGoodsInfoGiftMapper {

    /**
     * 查询货品促销赠品信息
     * 
     * @param orderGoodsId
     *            {@link Long}
     * @return List
     */
    List<OrderGoodsInfoGift> selectOrderGoodsInfoGift(Long orderGoodsId);

    /**
     * 插入赠品信息
     * 
     * @param glist
     * @return int
     */
    int insertOrderInfoGift(List<OrderGoodsInfoGift> glist);

}
