/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.order.bean.Order;
import com.ningpai.util.DaysOrderUtil;

/**
 * 第三方订单Dao
 * @author zhanghl
 * @since 2014年5月19日 下午5:15:01
 * @version 2.0
 */
public interface ThirdOrderMapper {

    /**
     * 查询第三方订单行数
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 第三方订单行数
     */
    int searchThridOrderCountRow(Map<String, Object> map);

    /**
     * 查询第三方订单列表
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 第三方订单列表
     */
    List<Object> searchThridOrderList(Map<String, Object> map);

    /**
     * 根据id查询订单
     * 
     * @param orderId
     *            要查询的订单id
     * @return 根据id返回的订单实体类
     */
    Order searcharOrderByParam(Long orderId);

    /**
     * 根据id更新订单
     * 
     * @param order
     *            要更新的订单
     * @return 更新结果
     */
    int updateThirdOrderByParam(Order order);

    /**
     * 根据id更新第三方订单（thirdId）
     *
     * @param order
     *            要更新的订单
     * @return 更新结果
     */
    int updateThirdOrder(Order order);

    /**
     * 根据参数查询订单个数
     * 
     * @param map
     *            封装参数的Map
     * @return 查询到的数量
     */
    int queryOrderCountBySta(Map<String, Object> map);

    /**
     * 查询前一天的订单数量和订单总金额
     * 
     * @param map
     *            封装参数 {@link Map}
     * @return 查询到的辅助Bean集合 {@link List}
     */
    DaysOrderUtil queryYestSalesOrderCount(Map<String, Object> map);

    /**
     * 查询今天的订单数量和订单金 额
     * 
     * @param map
     *            封装参数{@link Map}
     * @return 查询到的辅助Bean集合{@link List}
     */
    DaysOrderUtil queryTodaySalesOrderCount(Map<String, Object> map);
    /**
     * 根据参数查询已买订单个数
     * 
     * @param map
     *            封装参数的Map
     * @return 查询到的数量
     */
    int queryCustomerOrderCountBySta(Map<String, Object> map);
    
    /**
     * 查询第三方已购买订单行数
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 第三方订单行数
     */
    int searchPuyThridOrderCountRow(Map<String, Object> map);
    /**
     * 查询第三方已购买订单列表
     * 
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return 第三方订单列表
     */
    List<Object> searchPuyThridOrderList(Map<String, Object> map);
}
