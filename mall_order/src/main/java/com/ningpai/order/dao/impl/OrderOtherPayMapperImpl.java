/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.dao.OrderOtherPayMapper;
/**
 * 代付实现类
 *
 * @author NINGPAI-LIH
 *
 */
@Repository("OrderOtherPayMapper")
public class OrderOtherPayMapperImpl extends BasicSqlSupport implements
        OrderOtherPayMapper {

    /**
     * 根据订单编号查看其所有代付信息
     * @param orderOtherPay
     * @return
     */
    public OrderOtherPay queryOrderPayByOrderCode(OrderOtherPay orderOtherPay) {
        return this
                .selectOne(
                        "com.ningpai.web.order.dao.OrderOtherPayMapper.queryOrderPayByOrderCode",
                        orderOtherPay);
    }

    /**
     * 根据订单编号修改代付信息
     * @param orderOtherPay
     * @return
     */
    public int updateByOrderCodex(OrderOtherPay orderOtherPay) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayMapper.updateByOrderCodex",
                        orderOtherPay);
    }

    /**
     * 根据代付id修改代付信息
     * @param orderOtherPay
     * @return
     */
    public int updateByOtherPayId(OrderOtherPay orderOtherPay) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayMapper.updateByOrderCodex",
                        orderOtherPay);
    }

    /**
     * 插入代付信息
     * @param orderOtherPay
     * @return
     */
    public int insertSelective(OrderOtherPay orderOtherPay) {
        return this
                .update("com.ningpai.web.order.dao.OrderOtherPayMapper.insertSelective",
                        orderOtherPay);
    }

    /**
     * 根据代付编号查询单个代付信息
     * @param orderPayCode
     * @return
     */
    public OrderOtherPay queryOrderByCode(String orderPayCode) {
        return this
                .selectOne(
                        "com.ningpai.web.order.dao.OrderOtherPayMapper.queryOrderByCode",
                        orderPayCode);
    }

    /**
     * 查询5分钟内发起的未完成付款
     * @param orderCode
     * @return
     */
    public List<OrderOtherPay> queryOrderPayBylately(String orderCode) {
        return this
                .selectList(
                        "com.ningpai.web.order.dao.OrderOtherPayMapper.queryOrderPayBylately",
                        orderCode);
    }

    /**
     * 根据订单编号查询代付成功的信息
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public List<OrderOtherPay> queryOrderPayBySuccess(String orderCode) {
        return this
                .selectList(
                        "com.ningpai.web.order.dao.OrderOtherPayMapper.queryOrderPayBySuccess",
                        orderCode);
    }

    /**
     * 根据 订单编号查询需要退款付款信息
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public List<OrderOtherPay> queryOrderPayRefund(String orderCode) {
        return this
                .selectList(
                        "com.ningpai.web.order.dao.OrderOtherPayMapper.queryOrderPayRefund",
                        orderCode);
    }

}
