/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsReleTag;
import com.ningpai.goods.vo.GoodsReleTagVo;

/**
 * 商品关联标签Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午9:03:33
 * @version 1.0
 */
public interface GoodsReleTagService {
    /**
     * 保存关联标签
     * 
     * @param tagId
     *            标签的ID
     * @param goodsId
     *            商品的ID
     * @param username
     *            操作人名称
     * @return 添加的行数
     */
    int saveReleTag(Long tagId, Long goodsId, String username);

    /**
     * 根据商品ID和标签ID查询实体
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param tagId
     *            标签ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    GoodsReleTag queryByGoodsIdAndTagId(Long goodsId, Long tagId);

    /**
     * 更新商品关联标签记录
     * 
     * @param releTag
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int update(GoodsReleTag releTag, String username);

    /**
     * 删除关联标签的记录
     * 
     * @param releTagId
     *            关联的标签ID {@link java.lang.Long}
     * @param username
     *            用户名名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int deleteByPrimaryKey(Long releTagId, String username);

    /**
     * 根据货品ID查询关联的记录
     * 
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的集合 {@link GoodsReleTagVo}
     */
    List<GoodsReleTagVo> queryreleListByProductId(Long productId);
}
