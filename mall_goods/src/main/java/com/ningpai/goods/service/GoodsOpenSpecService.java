/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.vo.GoodsOpenSpecVo;

/**
 * 商品开启规格Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午3:02:01
 * @version 1.0
 */
public interface GoodsOpenSpecService {
    /**
     * 保存商品开启规格信息
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @return 插入的行数{@link　Integer}
     */
    int saveOpenSpec(Long goodsId, Long specId);

    /**
     * 根据商品ID查询开启的规格集合
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsOpenSpecVo> queryOpenListByGoodsId(Long goodsId);

    /**
     * 根据商品ID查询开启的规格集合
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    List<GoodsOpenSpecVo> queryOpenListByGoodsIdInBoss(Long goodsId);

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    void deleteByGoodsId(Long goodsId);

}
