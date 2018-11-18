/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service;

import com.ningpai.comment.bean.Comment;
import com.ningpai.site.customer.vo.GoodsBean;

import javax.servlet.http.HttpServletRequest;

/**
 * 商品评论Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月16日 上午10:07:23
 * @version 0.0.1
 */
public interface GoodsCommentService {

    /**
     * 查询订单商品
     * 
     * @param goodsId
     *            商品编号
     * @param orderId
     *            订单编号
     * @param customerId
     *            会员编号
     * @return 订单商品 {@link GoodsBean}
     */
    GoodsBean selectOrderGoods(Long goodsId, Long orderId, Long customerId);

    /**
     * 添加商品评论
     * 
     * @param request
     * @param comment
     *            评论实体 {@link Comment}
     * @return 0失败 1成功
     */
    int addGoodsComment(HttpServletRequest request, Comment comment, Long orderGoodsId);
    /**
     * 添加商品评论
     *
     * @param request
     * @param comment
     *            评论实体 {@link Comment}
     * @return 评论编号
     */
    Long addGoodsComment(HttpServletRequest request, Comment comment);

    /**
     * 查询商品评论
     * 
     * @param goodsId
     *            商品编号 {@link Long}
     * @param orderId
     *            订单编号 {@link Long}
     * @param customerId
     *            会员编号 {@link Long}
     * @return 评论 {@link Comment}
     */
    Comment selectGoodsComment(Long goodsId, Long orderId, Long customerId);

    /**
     * 查询订单商品 -- 评论
     * 
     * @param goodsId
     *            商品编号
     * @param orderId
     *            订单编号
     * @param customerId
     *            会员编号
     * @return 订单商品 {@link GoodsBean}
     */
    GoodsBean selectOrderGoodsToComment(Long goodsId, Long orderId, Long customerId);

    /**
     * 检验订单商品是否已评价
     * 
     * @param orderId
     *            订单编号 {@link Long}
     * @param goodsId
     *            商品编号 {@link Long}
     * @return 0没评价 1已评价
     */
    Long checkCommGoodFlag(Long orderId, Long goodsId);
    
    /**
     * 第三方店铺评级
     * @param thirdId
     *             店铺Id{@link java.lang.Long}
     * @return
     *       对象
     */
    Comment selectSellerComment(Long thirdId);

    /**
     * 判断是订单商品是否是会员的
     * @param orderGoodsId 订单货品编号
     * @param customerId 用户编号
     *@param flag  1 评论 2 晒单
     * @return boolean
     */
    boolean checkCommGoodIsUser(Long orderGoodsId, Long customerId,String flag);

}
