/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.GoodsReleParam;

/**
 * 商品关联类型详细参数Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午2:15:23
 * @version 1.0
 */
public interface GoodsReleParamService {
    /**
     * 保存商品关联类型详细参数
     * 
     * @param paramId
     *            详细参数ID
     * @param paramValue
     *            详细参数值
     * @param goodsId
     *            商品ID
     * @return 插入的行数 {@link java.lang.Integer}
     */
    int saveGoodsReleParam(Long goodsId, Long paramId, String paramValue,
            String username);

    /**
     * 更新商品关联类型详细参数信息
     * 
     * @param goodsReleParam
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @param username
     *            操作人名称
     * @return 更新的行数 {@link java.lang.Integer}
     */
    int updateReleParam(GoodsReleParam goodsReleParam, String username);

    /**
     * 根据商品ID和详细参数ID查询
     * 
     * @param goodsId
     *            商品ID{@link java.lang.Long}
     * @param paramId
     *            详细参数ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    GoodsReleParam queryReleParamByGoodsIdAndParamId(Long goodsId, Long paramId);

    /**
     * 根据商品ID删除所有的关联的记录
     * 
     * @param goodsId
     *            商品的ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的记录数
     */
    int delAllReleParamByGoodsId(Long goodsId, String username);
}
