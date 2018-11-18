/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderMarketing;

/**
 * @author ggn
 * 
 */
public interface OrderMarketingMapper {

    /**
     * 查询订单参加的促销信息
     * 
     * @param orderId
     *            {@link Long}
     * @return List
     */
    List<OrderMarketing> selectOrderMarketingList(Long orderId);

    /**
     * 添加订单参加的促销信息
     * 
     * @param orderMarketing
     *            参数
     * @author NINGPAI-LIH
     */
    void insertSelective(OrderMarketing orderMarketing);

    /**
     * 查看最新加入的id
     * 
     * @return 新加入的id
     */
    Long selectOrderMarketLastId();
}
