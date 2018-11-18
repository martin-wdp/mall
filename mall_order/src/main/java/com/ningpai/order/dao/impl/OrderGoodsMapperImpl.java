/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.dao.OrderGoodsMapper;

/**
 * @author ggn
 * 
 */
@Repository("OrderGoodsMapper")
public class OrderGoodsMapperImpl extends BasicSqlSupport implements
        OrderGoodsMapper {

    /**
     * 查询订单里的货品列表
     * @param orderId
     *            {@link Long}
     * @return
     */
    public List<OrderGoods> selectOrderGoodsList(Long orderId) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsMapper.selectOrderGoodsList",
                orderId);
    }
    /**
     * 查询订单里的货品列表
     * @param orderId
     *            {@link Long}
     * @return
     */
    public List<OrderGoods> selectSimpleOrderGoodsList(Long orderId) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsMapper.selectOrderGoodsListSimle",
                orderId);
    }

    /**
     * 根据退单集合的订单id查询订单商品
     * @param backorder
     * @return
     */
    @Override
    public List<OrderGoods> selectOrderGoodsListByOrders(List<Object> backorder) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsMapper.selectOrderGoodsListByOrders",
                        backorder);
    }

    /**
     * 插入订单商品
     * @param og
     * @return
     */
    public int insertOrderGoodsInfo(OrderGoods og) {
        return this
                .insert("com.ningpai.web.dao.OrderGoodsMapper.insertOrderGoodsInfo",
                        og);
    }

    /**
     * 查询最新的ID
     * @return
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.web.dao.OrderGoodsMapper.selectLastId");
    }

    /**
     * 查询退货单商品
     * @param orderId
     * @return
     */
    public List<OrderGoods> selectBackGoodsList(Long orderId) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsMapper.selectBackGoodsList",
                orderId);
    }

    /**
     * 查询换货单商品
     * @param orderCode
     *            换单编号
     * @return
     */
    public List<OrderGoods> selectBarterGoodList(String orderCode) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsMapper.selectBarterGoodList",
                orderCode);
    }

    /**
     * 查询每天前十条数量
     * @return
     */
    @Override
    public List<OrderGoods> selectTopOrderGoods() {
        return this
                .selectList("com.ningpai.web.dao.OrderGoodsMapper.selectTopOrderGoods");
    }

    /**
     * 根据客户id查询商品
     * @param list
     * @return
     */
    @Override
    public List<OrderGoods> queryProGoodsInfoByCustomerId(List<Long> list) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsMapper.queryProGoodsInfoByCustomerId",
                        list);

    }

    /**
     * 根据商品id查询类似id
     * @param goodsInfo
     * @return
     */
    @Override
    public List<OrderGoods> queryProGoodsInfoCustomer(Long goodsInfo) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsMapper.queryProGoodsInfoCustomer",
                        goodsInfo);

    }

    @Override
    public int queryOrderCountBygoodsIds(Long[] goodsIds) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.OrderGoodsMapper.queryOrderCountBygoodsIds",
                        goodsIds);

    }

    @Override
    public int queryOrderCountBygoodsInfoIds(Long[] goodsInfoIds) {
        return this
                .selectOne(
                        "com.ningpai.web.dao.OrderGoodsMapper.queryOrderCountBygoodsInfoIds",
                        goodsInfoIds);

    }

    /**
     * 查询某用户最近购买的某商品的数量
     * @param map
     * @return
     */
    @Override
    public Long selectGoodsInfoCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.web.dao.OrderGoodsMapper.selectGoodsInfoCount",
                map);
    }

    /**
     * 更新订单货品表 标记为退单货品
     * @param map
     * @return
     */
    @Override
    public int updateOrderGoodsBack(Map<String, Object> map) {
        return this.update(
                "com.ningpai.web.dao.OrderGoodsMapper.updateOrderGoodsBack",
                map);
    }

    /**
     * 查询退货商品信息（根据orderId和退单编号查询）
     * 
     * @return
     */
    @Override
    public List<OrderGoods> queryOrderGoodsByOrderIdAndBackCode(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsMapper.queryOrderGoodsByOrderIdAndBackCode",
                        map);
    }

}
