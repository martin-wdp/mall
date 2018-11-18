/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.mapper.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.customer.mapper.GoodsCommentMapper;
import com.ningpai.site.customer.vo.GoodsBean;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * @see com.ningpai.site.customer.mapper.GoodsCommentMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年6月16日 上午11:18:44
 * @version 0.0.1
 */
@Repository("goodsCommentMapper")
public class GoodsCommentMapperImpl extends BasicSqlSupport implements
        GoodsCommentMapper {
    /**
     * 查询订单商品
     * @param paramMap
     *            查询参数 {@link Map}
     * @return
     */
    @Override
    public GoodsBean selectOrderGoods(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.GoodsCommentMapper.selectOrderGoods",
                        paramMap);
    }

    /**
     * 修改订单商品评论信息
     * @param goodsBean
     *            辅助商品类
     * @return
     */
    @Override
    public int updateOrderGoods(GoodsBean goodsBean) {
        return this
                .update("com.ningpai.site.customer.mapper.GoodsCommentMapper.updateOrderGoods",
                        goodsBean);
    }

    /**
     * 查询订单商品
     * @param paramMap
     *            查询参数 {@link Map}
     * @return
     */
    @Override
    public GoodsBean selectOrderGoodsToComment(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.GoodsCommentMapper.selectOrderGoodsToComment",
                        paramMap);
    }

    /**
     * 检验订单商品是否已评价
     * @param paramMap
     * @return
     */
    @Override
    public Long checkCommGoodFlag(Map<String, Object> paramMap) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.GoodsCommentMapper.checkCommGoodFlag",
                        paramMap);
    }

    /**
     * 判断是订单商品是否是会员的
     * @param map orderGoodsId 订单货品编号 customerId 用户编号
     * @return
     */
    @Override
    public Long checkCommGoodIsUser(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.site.customer.mapper.GoodsCommentMapper.checkCommGoodIsUser",
                        map);
    }

}
