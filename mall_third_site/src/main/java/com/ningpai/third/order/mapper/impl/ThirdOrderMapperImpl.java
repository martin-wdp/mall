/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.mapper.impl;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.Order;
import com.ningpai.third.order.mapper.ThirdOrderMapper;
import com.ningpai.util.DaysOrderUtil;

/**
 * 第三方订单Dao实现
 * 
 * @AUTHOR NINGPAI-LIH
 * @since 2014年5月19日下午5：16：04
 */
@Repository("ThridOrderMapper")
public class ThirdOrderMapperImpl extends BasicSqlSupport implements ThirdOrderMapper {

    /**
     * 根据条件查询单个对象
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    public int searchThridOrderCountRow(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.searchOrderCount", map);
    }

    /**
     * 根据条件查询订单集合
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    public List<Object> searchThridOrderList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchOrderList", map);
    }

    /**
     * 根据主键查询单个订单对象
     * @param orderId
     *            要查询的订单id
     * @return
     */
    public Order searcharOrderByParam(Long orderId) {
        return this.selectOne("com.ningpai.web.dao.OrderMapper.orderDetail",
                orderId);
    }

    /**
     * 修改单个订单对象
     * @param order
     *            要更新的订单
     * @return
     */
    public int updateThirdOrderByParam(Order order) {

        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateByPrimaryKeySelective",
                order);
    }

    /**
     * 修改第三方商家后台订单信息
     * @see
     * com.ningpai.third.order.mapper.ThirdOrderMapper#updateThirdOrder(
     * com.ningpai.order.bean.Order)
     */
    public int updateThirdOrder(Order order) {

        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateThirdOrderNew",
                order);
    }

    /**
     * 查询单个对象
     * @see
     * com.ningpai.third.order.mapper.ThirdOrderMapper#queryOrderCountBySta(
     * java.util.Map)
     */
    public int queryOrderCountBySta(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.queryOrderCountBySta", map);
    }

    /**
     * 获得查询特定日期的订单的数量和总价格的辅助Bean
     * @see
     * com.ningpai.third.order.mapper.ThirdOrderMapper#queryYestSalesOrderCount
     * (java.util.Map)
     */
    public DaysOrderUtil queryYestSalesOrderCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.queryOrderCountYester", map);
    }

    /**
     * 根据条件获取查询特定日期的订单的数量和总价格的辅助Bean
     * @param map
     *            封装参数{@link Map}
     * @return
     */
    public DaysOrderUtil queryTodaySalesOrderCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.queryOrderCountToday", map);
    }

    /**
     * 根据条件获取单个的查询特定日期的订单的数量和总价格的辅助Bean
     * @param map
     *            封装参数的Map
     * @return
     */
    @Override
    public int queryCustomerOrderCountBySta(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.queryCustomerOrderCountBySta",
                map);
    }

    /**
     * 根据条件获取单个的查询特定日期的订单的数量和总价格的辅助Bean
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public int searchPuyThridOrderCountRow(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.ThirdOrderMapper.selectThirdOrderCount",
                map);
    }

    /**
     * 根据条件获取订单集合
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public List<Object> searchPuyThridOrderList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.ThirdOrderMapper.selectThirdOrderList",
                map);
    }

}
