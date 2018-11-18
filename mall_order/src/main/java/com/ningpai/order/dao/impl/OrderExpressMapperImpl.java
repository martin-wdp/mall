/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderExpress;
import com.ningpai.order.dao.OrderExpressMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderExpressMapper")
public class OrderExpressMapperImpl extends BasicSqlSupport implements
        OrderExpressMapper {

    /**
     * 查询物流信息
     * @param orderId
     *            {@link Long}
     * @return
     */
    public OrderExpress selectOrderExpress(Long orderId) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderExpressMapper.selectOrderExpress",
                orderId);
    }

    /**
     * 修改物流信息
     *
     * @param orderExpress
     *            {@link com.ningpai.order.bean.OrderExpress}
     * @return int
     */
    public int updateExpress(OrderExpress orderExpress) {
        return this.update(
                "com.ningpai.web.dao.OrderExpressMapper.updateExpress",
                orderExpress);
    }

    /**
     * 插入物流信息
     * @param orderExpress
     * @return
     */
    public int insertOrderExpress(OrderExpress orderExpress) {
        return this.update(
                "com.ningpai.web.dao.OrderExpressMapper.insertOrderExpress",
                orderExpress);
    }

}
