/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderGift;

/**
 * @author ggn
 * 
 */
public interface OrderGiftMapper {

    /**
     * 查询订单赠送的赠品
     * 
     * @param orderMarketingId
     *            {@link Long}
     * @return List
     */
    List<OrderGift> selectOrderGiftList(Long orderMarketingId);

    /**
     * 插入所有赠品
     * 
     * @param gift
     */
    void insertOrderInfoGift(List<OrderGift> gift);

}
