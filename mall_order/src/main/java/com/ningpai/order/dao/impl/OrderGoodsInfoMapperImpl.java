/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao.impl;

import java.util.List;

import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.order.bean.OrderGoods;
import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderGoodsInfo;
import com.ningpai.order.dao.OrderGoodsInfoMapper;

/**
 * 查询货品id和货品总数dao实现
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日14:51:40
 * 
 */
@Repository("OrderGoodsInfoMapper")
public class OrderGoodsInfoMapperImpl extends BasicSqlSupport implements
        OrderGoodsInfoMapper {

    /**
     * 查询货品id和货品总数
     * @param list
     *            所需要查询的订单id
     * @return
     */
    public List<OrderGoodsInfo> queryByGoodsInfosCount(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsInfo.queryByGoodsInfosCount",
                list);
    }

    /**
     * 查询订单所属赠品id和赠品总数
     * @param list
     *            所需要查询的订单id
     * @return
     */
    public List<OrderGoodsInfo> queryGiftCountByOrderIds(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsInfo.queryGiftCountByOrderIds",
                list);
    }

    /**
     * 查询货品所属的赠品id和赠品总数
     * @param list
     *            所需要查询的订单id
     * @return
     */
    public List<OrderGoodsInfo> queryGiftCountByGoodsIds(List<Long> list) {
        return this.selectList(
                "com.ningpai.web.dao.OrderGoodsInfo.queryGiftCountByGoodsIds",
                list);
    }

    /**
     * 根据货品id集合查询货品详细信息
     * @param goodsinfo
     * @return
     */
    @Override
    public List<GoodsProductDetailViewVo> queryViewVoByProductIds(
            List<OrderGoods> goodsinfo) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductMapper.queryViewVoByProductIds",
                        goodsinfo);
    }

    /**
     * 根据订单id查询订单所属商品促销赠品
     * @param orderId
     * @return
     */
    public List<OrderGoodsInfo> selectGiftByOrderIdInGoods(Long orderId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsInfo.selectGiftByOrderIdInGoods",
                        orderId);
    }

    /**
     * 根据订单id查询订单所属订单促销赠品
     * @param orderId
     * @return
     */
    public List<OrderGoodsInfo> selectGiftByOrderIdInOrder(Long orderId) {
        return this
                .selectList(
                        "com.ningpai.web.dao.OrderGoodsInfo.selectGiftByOrderIdInOrder",
                        orderId);
    }
}
