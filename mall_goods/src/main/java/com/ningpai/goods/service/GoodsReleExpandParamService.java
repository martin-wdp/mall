/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.GoodsReleExpandParam;

/**
 * 商品关联类型扩展参数Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 上午11:57:54
 * @version 1.0
 */
public interface GoodsReleExpandParamService {
    /**
     * 保存商品关联类型扩展参数
     * 
     * @param username
     *            操作人名称
     * @param goodsId
     *            商品ID
     * @param expandParamIds
     *            扩展属性ID
     * @param expandParamValue
     *            扩展属性值ID
     * @return 插入的行数
     */
    int saveExpandParam(String username, Long goodsId, Long expandParamIds,
            Long expandParamValue);

    /**
     * 根据商品ID和扩展属性ID查询商品关联扩展属性的记录
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @param expandParamId
     *            扩展属性ID {@link java.lang.Long}
     * @return 查询到的记录
     */
    GoodsReleExpandParam queryByGoodsIdAndExpandParamId(Long goodsId,
            Long expandParamId);

    /**
     * 更新商品关联扩展属性信息
     * 
     * @param goodsReleExpandParam
     *            商品关联扩展属性信息实体
     *            {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int updateGoodsReleExpandParam(GoodsReleExpandParam goodsReleExpandParam,
            String username);

    /**
     * 删除商品关联扩展属性记录
     * 
     * @param expandParamId
     *            关联的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数 {@link java.lang.Integer}
     */
    int delGoodsReleExpandParam(Long expandParamId, String username);

    /**
     * 根据商品ID删除所有的关联信息
     * 
     * @param goodsId
     *            商品的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delAllExpandParamByGoodsId(Long goodsId, String username);

}
