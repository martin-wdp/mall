/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.vo.GoodsOpenSpecValueVo;

/**
 * 商品规格dao
 *
 * */
public interface GoodsOpenSpecValueService {
    /**
     * 保存商品开启规格值记录
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @param specValueId
     *            规格值ID {@link Long}
     * @param valueImg
     *            规格图片 {@link String}
     * @param valueRemark
     *            自定义规格名称 {@link String}
     * @return 插入的行数 {@link Integer}
     */
    int saveOpenSpecVal(Long goodsId, Long specId, Long specValueId,
            String valueImg, String valueRemark);

    /**
     * 根据商品ID和规格ID查询开启的规格值集合
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @param specId
     *            规格ID {@link Long}
     * @return 查询到的开启的规格值的集合 {@link GoodsOpenSpecValueVo}
     */
    List<GoodsOpenSpecValueVo> queryOpenListByGoodsAndSpecId(Long goodsId,
            Long specId);

    /**
     * 根据商品id和规格值id查询是否开启规则值
     * 
     * @param goodsId
     *            商品id
     * @param specVlueId
     *            规格值id
     * @return
     */
    int queryOpenListByGoodsAndSpecValueId(Long goodsId, String[] specValueId);

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    void deleteByGoodsId(Long goodsId);
}
