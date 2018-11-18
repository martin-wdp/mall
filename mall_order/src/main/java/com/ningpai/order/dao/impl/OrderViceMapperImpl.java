/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.dao.OrderViceMapper;
/**
 * 抢购团购订单实现类
 *
 * @author NINGPAI-LIH
 * @since 2014年12月3日16:49:00
 *
 */
@Repository("OrderViceMapper")
public class OrderViceMapperImpl extends BasicSqlSupport implements
        OrderViceMapper {

    /**
     * 查询抢购或者团购订单的总数量
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    @Override
    public int searchOrderViceCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.order.dao.OrderViceMapper.searchOrderViceCount",
                map);
    }

    /**
     * 查询抢购或者团购订单列表
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    @Override
    public List<Object> searchOrderViceList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.order.dao.OrderViceMapper.searchOrderViceList",
                map);
    }

    /**
     * 订单详情
     * @param orderId
     *            主键id
     * @return
     */
    @Override
    public OrderVice selectByPrimaryKey(Long orderId) {
        return this.selectOne(
                "com.ningpai.order.dao.OrderViceMapper.selectByPrimaryKey",
                orderId);
    }

    /**
     * 插入团购或抢购订单
     * @param orderVice
     *            订单信息
     * @return
     */
    @Override
    public int insertSelective(OrderVice orderVice) {
        return this.insert(
                "com.ningpai.order.dao.OrderViceMapper.insertSelective",
                orderVice);
    }

    /**
     * 根据订单编号查询订单信息
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public OrderVice selectByOrderCode(String orderCode) {
        return this.selectOne(
                "com.ningpai.order.dao.OrderViceMapper.selectByOrderCode",
                orderCode);
    }

    /**
     *查询订单是否存在
     * @param orderCode
     *            订单号
     * @return
     */
    @Override
    public Long existOrderCode(String orderCode) {
        return this.selectOne(
                "com.ningpai.order.dao.OrderViceMapper.existOrderCode",
                orderCode);
    }

    /**
     * 修改订单信息
     * @param orderVice
     *            要修改的信息
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(OrderVice orderVice) {
        return this
                .update("com.ningpai.order.dao.OrderViceMapper.updateByPrimaryKeySelective",
                        orderVice);
    }

    /**
     * 查询抢购或者团购订单的总数量--前台
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    @Override
    public long selectOrderViceCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.order.dao.OrderViceMapper.selectOrderViceCount",
                map);
    }

    /**
     * 查询抢购或者团购订单列表--前台
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    @Override
    public List<Object> selectOrderViceList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.order.dao.OrderViceMapper.selectOrderViceList",
                map);
    }

}
