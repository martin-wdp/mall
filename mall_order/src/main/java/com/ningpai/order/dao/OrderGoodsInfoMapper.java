/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfo;

/**
 * 查询货品id和货品总数dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日14:51:40
 * 
 */
public interface OrderGoodsInfoMapper {
    /**
     * 查询货品id和货品总数
     * 
     * @param list
     *            所需要查询的订单id
     * @return 查询货品id和货品总数
     * @since 2014年6月25日14:51:15
     */
    List<OrderGoodsInfo> queryByGoodsInfosCount(List<Long> list);

    /**
     * 查询订单所属赠品id和赠品总数
     * 
     * @param list
     *            所需要查询的订单id
     * @return 赠品id和赠品总数
     * @since 2014年6月26日10:33:40
     */
    List<OrderGoodsInfo> queryGiftCountByOrderIds(List<Long> list);

    /**
     * 查询货品所属的赠品id和赠品总数
     * 
     * @param list
     *            所需要查询的订单id
     * @return 赠品id和赠品总数
     * @since 2014年6月26日10:33:40
     */
    List<OrderGoodsInfo> queryGiftCountByGoodsIds(List<Long> list);

    /**
     * 根据货品id集合查询货品详细信息
     * 
     * @param goodsinfo
     * @return
     */
    List<GoodsProductDetailViewVo> queryViewVoByProductIds(
            List<OrderGoods> goodsinfo);

    /**
     * 根据订单id查询订单所属商品促销赠品
     * 
     * @return 订单所属商品促销赠品
     */
    List<OrderGoodsInfo> selectGiftByOrderIdInGoods(Long orderId);

    /**
     * 根据订单id查询订单所属订单促销赠品
     * 
     * @return 订单所属订单促销赠品
     */
    List<OrderGoodsInfo> selectGiftByOrderIdInOrder(Long orderId);

}
