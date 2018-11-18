/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.vo.GoodsSpecVo;

import java.util.List;

/**
 * 货品信息关联规格值Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月30日 上午11:23:37
 * @version 1.0
 */
public interface GoodsProductReleSpecService {
    /**
     * 插入一条货品关联规格值记录
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @param specId
     *            规格ID {@link java.lang.Long}
     * @param specDetailId
     *            规格值ID {@link java.lang.Long}
     * @param specRemark
     *            自定义规格名称 {@link String}
     * @param useranme
     *            操作人名称
     * @return 保存的行数 {@link java.lang.Integer}
     */
    int saveProductReleSpec(Long productId, Long specId, Long specDetailId,
            String specRemark, String useranme);

    /**
     * 修改货品关联规格
     * @return
     */
    int updateProductReleSpec(Long productId, Long specId, Long specDetailId,
            String specValueRemark);

    /**
     * 根据货品ID查询关联的规格
     *
     * @param goodsId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsSpecVo}
     */
    List<GoodsSpecVo> querySpecVoByGoodsInfoId(Long goodsId);

    /**
     * 根据货品id删除所有规格和规格值
     * 
     * @param productId
     *            货品id
     * @return
     */
    int deleteByProductId(Long productId);

}
