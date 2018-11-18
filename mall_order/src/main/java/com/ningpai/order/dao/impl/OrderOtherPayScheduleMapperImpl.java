/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.dao.OrderOtherPayScheduleMapper;

/**
 * 订单支付信息
 * @author ggn
 *
 */
@Repository("OrderOtherPayScheduleMapper")
public class OrderOtherPayScheduleMapperImpl extends BasicSqlSupport implements
        OrderOtherPayScheduleMapper {


    /**
     * 根据参数插入信息
     * @param record
     * @return
     */
    public int insertSelective(OrderOtherPaySchedule record) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayScheduleMapper.insertSelective",
                        record);

    }

    /**
     * 根据订单编号查询信息
     * @param orderCode
     * @return
     */
    public OrderOtherPaySchedule selectByPrimaryKey(String orderCode) {
        return this
                .selectOne(
                        "com.ningpai.web.order.dao.OrderOtherPayScheduleMapper.selectByPrimaryKey",
                        orderCode);
    }

    /**
     * 根据订单编号修改信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(OrderOtherPaySchedule record) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayScheduleMapper.updateByPrimaryKeySelective",
                        record);

    }

    /**
     * 代付付款
     * @param otherPaySchedule
     * @return
     */
    public int payOther(OrderOtherPaySchedule otherPaySchedule) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayScheduleMapper.payOther",
                        otherPaySchedule);

    }

    /**
     * 查询需要退款的多人代付
     * @return
     */
    @Override
    public List<OrderOtherPaySchedule> queryOrderOtherPayRefund() {
        return this
                .selectList("com.ningpai.web.order.dao.OrderOtherPayScheduleMapper.queryOrderOtherPayRefund");
    }

}
