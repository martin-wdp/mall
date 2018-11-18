/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.comment.bean.Comment;
import com.ningpai.m.customer.vo.GoodsBean;

/**
 * 会员订单Mapper
 * 
 * @author jiping
 * @since 2014年8月20日 下午1:41:48
 * @version 0.0.1
 */
public interface CustomerOrderMapper {
    /**
     * 查询所有订单
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    List<Object> queryAllMyOrders(Map<String, Object> paramMap);

    /**
     * 查询所有订单数量
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    Long searchTotalCount(Map<String, Object> paramMap);

    /**
     * 按条件查询所有订单数量
     * 
     * @param paramMap
     *            查询订单条件
     * @return
     */
    Long searchTotalCountO(Map<String, Object> paramMap);

    /**
     * 根据订单 会员编号查找订单信息
     * 
     * @param orderId
     *            订单编号
     * @return OrderInfoBean
     */
    Object queryOrderByParamMap(Map<String, Object> paramMap);

    /**
     * 查询待评论的订单
     * 
     * @param paramMap
     *            查询条件
     * @return Long {@link java.lang.Long}
     */
    Long selectpendingOrderNotice(Map<String, Object> paramMap);

    /**
     * 取消订单
     * 
     * @param orderId
     *            订单编号
     * @return 0失败 1 成功
     */
    int cancelOrder(Map<String, Object> paramMap);

    /**
     * 确认收货
     * 
     * @param orderId
     *            订单编号
     * @return
     */
    int comfirmofGoods(Long orderId);

    /**
     * 添加商品评论
     * 
     * @param comment
     *            评论信息 {@link Comment}
     * @return 评论编号
     */
    int addGoodsComment(Comment comment);

    /**
     * 修改订单商品评论信息
     * 
     * @param goodsBean
     *            辅助商品类
     * @return 0失败 1成功
     */
    int updateOrderGoods(GoodsBean goodsBean);

}
