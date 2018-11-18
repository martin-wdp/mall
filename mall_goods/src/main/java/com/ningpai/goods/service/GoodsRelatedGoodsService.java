/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.GoodsRelatedGoods;

/**
 * 商品关联商品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午4:47:46
 * @version 1.0
 */
public interface GoodsRelatedGoodsService {

    /**
     * 根据商品ID 关联商品ID删除 商品关联商品
     * @param relatedId  商品关联ID
     * @param relatedProductId 商品ID
     * @return
     */
    int deleteRelatedProduct(Long relatedId,Long relatedProductId);
    /**
     * 添加商品关联商品记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param aboutGoodsId
     *            关联的商品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int save(Long goodsId, Long aboutGoodsId, String username);

    /**
     * 根据商品ID删除所有的商品关联记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delAllRelaGoodsByGoodsId(Long goodsId, String username);

    /**
     * 根据商品ID和关联的商品ID查询记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param relaGoodsId
     *            关联的商品ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsRelatedGoods}
     */
    GoodsRelatedGoods queryByGoodsIdAndRelaGoodsIdIncludeDel(Long goodsId,
            Long relaGoodsId);

    /**
     * 更新商品关联商品信息
     * 
     * @param relaGoods
     *            待更新的实体{@link com.ningpai.goods.bean.GoodsRelatedGoods}
     * @param usernmae
     *            操作人名称
     * @return 更新的行数
     */
    int updateRelaGoods(GoodsRelatedGoods relaGoods, String usernmae);

    /**
     * 根据商品ID和选中的ID删除未被选中的关联记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Longs}
     * @param ralaGoodsIds
     *            选中的商品ID的数组{@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delRelaGoodsByGoodsIdAndRelaGoodsIds(Long goodsId,
            String[] ralaGoodsIds, String username);
}
