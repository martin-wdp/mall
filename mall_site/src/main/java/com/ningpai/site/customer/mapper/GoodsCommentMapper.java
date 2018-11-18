/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper;

import com.ningpai.site.customer.vo.GoodsBean;

import java.util.Map;

/**
 * 商品评论Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月16日 上午11:15:12
 * @version 0.0.1
 */
public interface GoodsCommentMapper {
    /**
     * 查询订单商品
     * 
     * @param paramMap
     *            查询参数 {@link Map}
     * @return
     */
    GoodsBean selectOrderGoods(Map<String, Object> paramMap);

    /**
     * 修改订单商品评论信息
     * 
     * @param goodsBean
     *            辅助商品类
     * @return 0失败 1成功
     */
    int updateOrderGoods(GoodsBean goodsBean);

    /**
     * 查询订单商品 -- 评论
     * 
     * @param paramMap
     *            查询参数 {@link Map}
     * @return
     */
    GoodsBean selectOrderGoodsToComment(Map<String, Object> paramMap);

    /**
     * 检验订单商品是否已评价
     * 
     * @return 0没评价 1已评价
     */
    Long checkCommGoodFlag(Map<String, Object> paramMap);

    /**
     * 判断是订单商品是否是会员的
     * @param map orderGoodsId 订单货品编号 customerId 用户编号
     * @return int 1是 0 否
     */
    Long checkCommGoodIsUser(Map<String, Object> map);
}
