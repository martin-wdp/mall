/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.dao.OrderGiftMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderGiftMapper")
public class OrderGiftMapperImpl extends BasicSqlSupport implements
        OrderGiftMapper {

    /**
     * 查询订单赠送的赠品
     * @param orderMarketingId
     *            {@link Long}
     * @return
     */
    public List<OrderGift> selectOrderGiftList(Long orderMarketingId) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGiftMapper.selectOrderGiftList",
                orderMarketingId);
    }

    /**
     * 插入所有赠品
     * @param gift
     */
    public void insertOrderInfoGift(List<OrderGift> gift) {
        this.insert("com.ningpai.web.dao.OrderGiftMapper.insertOrderInfoGift",
                gift);
    }

}
