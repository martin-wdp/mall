/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.order.bean.OrderVice;

/**
 * 抢购团购订单
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月3日16:49:00
 * 
 */
public interface OrderViceMapper {
    /**
     * 查询抢购或者团购订单的总数量
     * 
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    int searchOrderViceCount(Map<String, Object> map);

    /**
     * 查询抢购或者团购订单列表
     * 
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    List<Object> searchOrderViceList(Map<String, Object> map);

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return 订单详情
     */
    OrderVice selectByPrimaryKey(Long orderId);

    /**
     * 插入团购或抢购订单
     * 
     * @param orderVice
     *            订单信息
     * @return 插入结果
     */
    int insertSelective(OrderVice orderVice);

    /**
     * 根据订单编号查询订单信息
     * 
     * @param orderCode
     *            订单编号
     * @return 订单信息
     */
    OrderVice selectByOrderCode(String orderCode);

    /**
     * 查询订单是否存在
     * 
     * @param orderCode
     *            订单号
     * @return 查询到的条数
     */
    Long existOrderCode(String orderCode);

    /**
     * 修改订单信息
     * 
     * @param orderVice
     *            要修改的信息
     * @return 修改结果
     */
    int updateByPrimaryKeySelective(OrderVice orderVice);

    /**
     * 查询抢购或者团购订单的总数量--前台
     * 
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    long selectOrderViceCount(Map<String, Object> map);

    /**
     * 查询抢购或者团购订单列表--前台
     * 
     * @param map
     *            orderType 0：团购订单 1：抢购订单
     * @return
     */
    List<Object> selectOrderViceList(Map<String, Object> map);
}
