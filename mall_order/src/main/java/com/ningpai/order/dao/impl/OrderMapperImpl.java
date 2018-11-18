/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.Order;
import com.ningpai.order.dao.OrderMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author NINGPAI-LIH 订单实现类
 */
@Repository("OrderMapper")
public class OrderMapperImpl extends BasicSqlSupport implements OrderMapper {

    /**
     * 查询订单总数
     * @param paramMap
     *            {@link java.util.Map}
     * @return
     */
    public int searchOrderCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.searchOrderCount", paramMap);
    }

    /**
     * 修改订单信息
     * @param order
     * @return
     */
    public int updateByPrimaryKeySelective(Order order) {
        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateByPrimaryKeySelective",
                order);
    }

    /**
     * 查询订单列表
     * @param paramMap
     *            {@link java.util.Map}
     * @return
     */
    public List<Object> searchOrderList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchOrderList", paramMap);
    }

    /**
     * 订单详情
     * @param orderId
     *            {@link Long}
     * @return
     */
    public Order orderDetail(Long orderId) {
        return this.selectOne("com.ningpai.web.dao.OrderMapper.orderDetail",
                orderId);
    }

    /**
     * 订单详情
     * @param orderId
     *            {@link Long}
     * @return
     */
    public Order orderSimpleDetail(Long orderId) {
        return this.selectOne("com.ningpai.web.dao.OrderMapper.orderSimpleDetail",
                orderId);
    }

    /**
     * 修改订单发货
     * @param orderId
     *            {@link Long}
     * @return
     */
    public int sendOrder(Long orderId) {
        return this
                .update("com.ningpai.web.dao.OrderMapper.sendOrder", orderId);
    }

    /**
     * 出入订单
     * @param order
     * @return
     */
    public int insertOrder(Order order) {
        return this
                .insert("com.ningpai.web.dao.OrderMapper.insertOrder", order);
    }

    /**
     * 查询刚插入ID
     * @return
     */
    public Long selectLastId() {
        return this.selectOne("com.ningpai.web.dao.OrderMapper.selectLastId");
    }

    /**
     * 确认支付
     * @param orderId
     * @return
     */
    public int payOrder(Long orderId) {
        return this.update("com.ningpai.web.dao.OrderMapper.payOrder", orderId);
    }
    /**
     * 已提交至银行等待处理
     * @param orderId
     * @return
     */
    public int subBankPayOrder(Long orderId) {
        return this.update("com.ningpai.web.dao.OrderMapper.subBankPayOrder", orderId);
    }

    /**
     * 查询订单
     * @param orderCode
     * @return
     */
    public Order getPayOrderByCode(String orderCode) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.getPayOrderByCode", orderCode);
    }

    /**
     * 更改订单状态
     * @param map
     */
    public void updateOrderStatusByOrderIds(Map<String, Object> map) {
        this.update(
                "com.ningpai.web.dao.OrderMapper.updateOrderStatusByOrderIds",
                map);
    }

    /**
     * 根据orderId数组查询订单数据
     * @param orderIds
     * @return
     */
    public List<Order> selectOrderList(List<Long> orderIds) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.selectOrderList", orderIds);
    }

    @Override
    public List<Order> receiptConfirmation() {
        return this
                .selectList("com.ningpai.web.dao.OrderMapper.receiptConfirmation");
    }

    /**
     * 查询前十条近一个月新加订单
     * @return
     */
    public List<Order> selectNewOrderByParam() {
        return this
                .selectList("com.ningpai.web.dao.OrderMapper.selectNewOrderByParam");
    }

    /**
     * 近一个月新加订单总数
     * @return
     */
    public Long selectNewOrderCountByParam() {
        return this
                .selectOne("com.ningpai.web.dao.OrderMapper.selectNewOrderCountByParam");
    }

    /**
     * 更新最近一个月新订单为已查看
     * @param orderId
     */
    public void updateOrdreNewStauts(Long orderId) {
        this.selectOne("com.ningpai.web.dao.OrderMapper.updateOrdreNewStauts",
                orderId);
    }

    /**
     * 根据订单状态id查询订单
     * @param map
     * @return
     */
    public List<Object> searchOrderListByOrderIds(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchOrderListByOrderIds",
                map);
    }
    /**
     * 根据订单状态id查询订单
     * @param map
     * @return
     */
    public List<Object> searchSimpleOrderListByOrderIds(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchSimpleOrderListByOrderIds",
                map);
    }

    /**
     * 根据订单状态id查询订单总行数
     * @param map
     * @return
     */
    public int searchOrderCountByOrderIds(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.searchOrderCountByOrderIds",
                map);
    }

    /**
     * 根据订单id集合查询订单
     * @param map
     *            订单id集合
     * @return
     */
    public List<Object> searchOrderListByOrderIdList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchOrderListByOrderIdList",
                map);
    }

    /**
     * 根据订单id集合查询订单
     * @param map
     *            订单id集合
     * @return
     */
    public List<Object> searchSimpleOrderListByOrderIdList(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchSimpleOrderListByOrderIdList",
                map);
    }

    /**
     * 根据订单id集合查询订单总行数
     * @param map
     *            订单id集合
     * @return
     */
    public int searchOrderCountByOrderIdsList(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.OrderMapper.searchOrderCountByOrderIdsList",
                        map);
    }

    /**
     * 修改订单金额
     * @param map
     */
    public void modifyOrderPrice(Map<String, Object> map) {
        this.update("com.ningpai.web.dao.OrderMapper.modifyOrderPrice", map);
    }

    /**
     * 修改订单的出库状态
     * @param order
     *            订单状态
     * @return
     */
    @Override
    public int updateSetCargoStatusByOrderId(Order order) {
        return this
                .update("com.ningpai.web.dao.OrderMapper.updateSetCargoStatusByOrderId",
                        order);
    }

    /**
     * 修改第三方订单的出库状态
     * @param order
     *            订单状态
     * @return
     */
    @Override
    public int updateSetCargoStatusByThirdOrderId(Order order) {
        return this
                .update("com.ningpai.web.dao.OrderMapper.updateSetCargoStatusByThirdOrderId",
                        order);
    }

    /**
     * 批量修改订单出库状态
     *
     * @param map
     * @return
     */
    @Override
    public int updateSetCargoStatusByOrderIds(Map<String, Object> map) {
        return this
                .update("com.ningpai.web.dao.OrderMapper.updateSetCargoStatusByOrderIds",
                        map);
    }

    /**
     * 批量修改第三方订单出库状态
     *
     * @param map
     * @return
     */
    @Override
    public int updateSetCargoStatusByThirdOrderIds(Map<String, Object> map) {
        return this
                .update("com.ningpai.web.dao.OrderMapper.updateSetCargoStatusByThirdOrderIds",
                        map);
    }

    /**
     * 根据本日订单总数
     * @return
     */
    @Override
    public int selectOrderCountByCurdate() {
        return this
                .selectOne("com.ningpai.web.dao.OrderMapper.selectOrderCountByCurdate");

    }

    @Override
    public int deleteBackOrderById(Long backOrderId, Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("backOrderId", backOrderId);
        map.put("customerId", customerId);
        return this.update(
                "com.ningpai.web.dao.OrderMapper.deleteBackOrderById", map);
    }

    /**
     * 根据时间查询订单信息
     * @param map
     * @return
     */
    @Override
    public List<Order> selectOrderListByTime(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.selectOrderListByTime", map);
    }

    /**
     * 根据参数查询订单列表
     * 
     * @param paramMap
     *            查询所用的参数
     * @return
     */
    @Override
    public List<Order> selectByParam(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.web.dao.OrderMapper.selectByParam",
                paramMap);
    }

    /**
     * 根据时间分组查询每天销售量
     * 
     * @param map
     * @return
     */
    @Override
    public List<Order> querySaleCountByDay(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.querySaleCountByDay", map);
    }

    /**
     * 根据时间分组查询每天的销售额
     * 
     * @param map
     * @return
     */
    @Override
    public List<Order> querySaleMoneyByDay(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.querySaleMoneyByDay", map);
    }

    /**
     * 删除订单
     * 
     * @param orderId
     * @return
     */
    @Override
    public int deleteOrderById(Long orderId) {
        return this.update("com.ningpai.web.dao.OrderMapper.deleteOrderById",
                orderId);
    }

    /**
     * 查询第三方订单的数量
     * 
     * @param
     * @return
     */
    @Override
    public int searchThirdOrderCount(Map<String, Object> paramMap) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderMapper.searchThirdOrderCount",
                paramMap);
    }

    /**
     * 查询第三方订单
     * 
     * @param paramMap
     * @return
     */
    @Override
    public List<Object> searchThirdOrderList(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.searchThirdOrderList",
                paramMap);
    }

    /**
     * 自动更新未付款订单七天后作废
     */
    @Override
    public void updateStatusByCreateTime(Long time) {
        this.update("com.ningpai.web.dao.OrderMapper.updateStatusByCreateTime",
                time);
    }

    @Override
    public void updateStatusByCancleOrder(Map<String,Object> map) {
        this.update("com.ningpai.web.dao.OrderMapper.updateStatusByCancleOrder",
                map);
    }

    /**
     * 根据总订单号查询订单信息
     * 
     * @param orderOldCode
     * @return
     */
    @Override
    public List<Order> getPayOrderByOldCode(String orderOldCode) {
        return this.selectList(
                "com.ningpai.web.dao.OrderMapper.getPayOrderByOldCode",
                orderOldCode);
    }

    /**
     * 根据订单id更改订单状态
     * 
     * @param param
     * @return
     */
    @Override
    public int updateStatusBackById(Map<String, Object> param) {
        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateStatusBackById", param);
    }

    /**
     * 根据订单编号，商家编号 修改订单状态
     * @param order
     * @return
     */
    @Override
    public int updateOrderStatusByorderId(Order order) {
        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateStatusByorderId", order);
    }

    @Override
    public int updateOrderStatusByorderIdFortask(Order order) {
        return this.update(
                "com.ningpai.web.dao.OrderMapper.updateOrderStatusByorderIdFortask", order);
    }

    /**
     * 查询所有订单信息 此方法供后台订单列表中的导出订单列表功能使用
     *
     * @return
     * @author houyichang 2015/8/14
     */
    @Override
    public List<Order> queryAllOrderList() {
        return this.selectList("com.ningpai.web.dao.OrderMapper.selectAllOrder");
    }

}
