/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.order.dao.OrderMarketingMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderMarketingMapper")
public class OrderMarketingMapperImpl extends BasicSqlSupport implements
        OrderMarketingMapper {

    /**
     * 查询订单参加的促销信息
     * @param orderId
     *            {@link Long}
     * @return
     */
    public List<OrderMarketing> selectOrderMarketingList(Long orderId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderMarketingMapper.selectOrderMarketingList",
                        orderId);
    }

    /**
     * 添加订单参加的促销信息
     * @param orderMarketing
     *            参数
     */
    public void insertSelective(OrderMarketing orderMarketing) {
        this.insert("com.ningpai.web.dao.OrderMarketingMapper.insertSelective",
                orderMarketing);
    }

    /**
     * 查看最新加入的id
     * @return
     */
    public Long selectOrderMarketLastId() {
        return this
                .selectOne("com.ningpai.web.dao.OrderMarketingMapper.selectOrderMarketLastId");
    }

}
