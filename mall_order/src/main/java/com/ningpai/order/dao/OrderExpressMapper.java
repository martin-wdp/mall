/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import com.ningpai.order.bean.OrderExpress;

/**
 * @author ggn
 * 
 */
public interface OrderExpressMapper {

    /**
     * 查询物流信息
     * 
     * @param orderId
     *            {@link Long}
     * @return OrderExpress
     */
    OrderExpress selectOrderExpress(Long orderId);

    /**
     * 修改物流信息
     * 
     * @param orderExpress
     *            {@link com.ningpai.order.bean.OrderExpress}
     * @return int
     */
    int updateExpress(OrderExpress orderExpress);

    /**
     * 插入物流信息
     * 
     * @param orderExpress
     * @return int
     */
    int insertOrderExpress(OrderExpress orderExpress);
}
