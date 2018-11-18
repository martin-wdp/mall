/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.order.bean.OrderGoods;

/**
 * @author ggn
 * 
 */
public interface OrderGoodsMapper {

    /**
     * 查询订单里的货品列表
     * 
     * @param orderId
     *            {@link Long}
     * @return List
     */
    List<OrderGoods> selectOrderGoodsList(Long orderId);
    /**
     * 查询订单里的货品列表
     *
     * @param orderId
     *            {@link Long}
     * @return List
     */
    List<OrderGoods> selectSimpleOrderGoodsList(Long orderId);

    /**
     * 根据商品id查询个数 boss后台删除商品用
     * @param goodsIds
     * @return
     */
    int queryOrderCountBygoodsIds(Long[] goodsIds);


    /**
     * 根据货品id查询个数 boss后台删除货品用
     * @param goodsInfoIds
     * @return
     */
    int queryOrderCountBygoodsInfoIds(Long[] goodsInfoIds);

    /**
     * 查询某用户最近购买的某商品的数量
     * 
     * @param map
     * @return
     */
    Long selectGoodsInfoCount(Map<String, Object> map);

    /**
     * 根据退单集合的订单id查询订单商品
     * 
     * @param backorders
     * @return
     */
    List<OrderGoods> selectOrderGoodsListByOrders(List<Object> backorders);

    /**
     * 插入订单商品
     * 
     * @param og
     * @return int
     */
    int insertOrderGoodsInfo(OrderGoods og);

    /**
     * 查询最新的ID
     * 
     * @return Long
     */
    Long selectLastId();

    /**
     * 查询退货单商品
     * 
     * @param orderId
     * @return List
     */
    List<OrderGoods> selectBackGoodsList(Long orderId);

    /**
     * 查询换货单商品
     * 
     * @param orderCode
     *            换单编号
     * @return List<OrderGoods>
     */
    List<OrderGoods> selectBarterGoodList(String orderCode);

    /**
     * 查询每天前十条数量
     * 
     * @return
     */
    List<OrderGoods> selectTopOrderGoods();

    /**
     * 根据客户id查询商品
     * 
     * @param list
     * @return
     */
    List<OrderGoods> queryProGoodsInfoByCustomerId(List<Long> list);

    /**
     * 根据商品id查询类似id
     * 
     * @param goodsInfo
     * @return
     */
    List<OrderGoods> queryProGoodsInfoCustomer(Long goodsInfo);

    /**
     * 更新订单货品表 标记为退单货品
     * 
     * @param param
     * @return
     */
    int updateOrderGoodsBack(Map<String, Object> param);

    /**
     * 查询退货商品信息（根据orderId和退单编号查询）
     * 
     * @param map
     * @return
     */
    List<OrderGoods> queryOrderGoodsByOrderIdAndBackCode(Map<String, Object> map);

}
