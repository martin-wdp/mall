/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import java.util.List;

import com.ningpai.goods.bean.GoodsTypeParam;

/**
 * 商品详细参数Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月23日 上午9:56:38
 * @version
 */
public interface GoodsTypeParamService {
    /**
     * 添加商品详细参数
     * 
     * @param goodsTypeParam
     *            详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @param username
     *            操作人名称
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    int saveTypeParam(GoodsTypeParam goodsTypeParam, String username);

    /**
     * 根据主键ID删除详细参数
     * 
     * @param paramId
     *            详细参数的主键ID {@link java.lang.Long}
     * @param username
     *            操作人名称
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int delTypeParam(Long paramId, String username);

    /**
     * 更新商品详细参数
     * 
     * @param goodsTypeParam
     *            详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @param username
     *            操作人名称呢过
     * @return 影响的行数 {@link java.lang.Integer}
     */
    int update(GoodsTypeParam goodsTypeParam, String username);

    /**
     * 根据主键ID查询商品详细参数
     * 
     * @param paramId
     *            详细参数的主键ID {@link java.lang.Long}
     * @return 详细参数实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    GoodsTypeParam queryByPrimaryKey(Long paramId);

    /**
     * 根据类型ID查询详细参数的列表
     * 
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的详细参数的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    List<GoodsTypeParam> queryParamListByTypeId(Long typeId);

    /**
     * 批量进行操作
     * 
     * @param typeId
     *            类型ID
     * @param username
     *            操作人名称
     * @param typeParamId
     *            详细参数ID
     * @param paramDelflag
     *            删除标记
     * @param paramname
     *            详细参数名称
     * @param paramnickname
     *            详细参数别名
     * @return 受影响的行数
     */
    int batchUpateParam(Long typeId, String username, String[] typeParamId,
            String[] paramDelflag, String[] paramname, String[] paramnickname);
}
