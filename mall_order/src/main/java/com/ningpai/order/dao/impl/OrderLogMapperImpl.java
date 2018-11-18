/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderLog;
import com.ningpai.order.dao.OrderLogMapper;

/**
 * 订单操作日志dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月8日10:23:24
 * 
 */
@Repository("OrderLogMapper")
public class OrderLogMapperImpl extends BasicSqlSupport implements
        OrderLogMapper {

    /**
     * 插入订单记录信息
     * @param record
     *            订单记录信息
     * @return
     */
    @Override
    public int insertSelective(OrderLog record) {
        return this.insert(
                "com.ningpai.order.dao.OrderLogMapper.insertSelective", record);
    }

    /**
     *
     * @param orderLogId
     * @return
     */
    @Override
    public OrderLog selectByPrimaryKey(Long orderLogId) {
        return null;
    }

    /**
     * 根据订单id查询订单操作记录
     * @param orderId
     *            订单id
     * @return
     */
    @Override
    public List<OrderLog> selectOrderLogByParam(Long orderId) {
        return this.selectList(
                "com.ningpai.order.dao.OrderLogMapper.selectOrderLogByParam",
                orderId);
    }

}
