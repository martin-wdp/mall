/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.GoodsGroupReleProduct;

/**
 * 组合关联货品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月2日 下午2:22:23
 * @version 1.0
 */
public interface GoodsGroupReleProductService {
    /**
     * 根据组合ID和货品ID查询关联对象
     * 
     * @param groupId
     *            组合ID{@link java.lang.Long}
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的对象{@link com.ningpai.goods.bean.GoodsGroupReleProduct}
     */
    GoodsGroupReleProduct queryGroupReleProductByGroupIdAndProductId(
            Long groupId, Long productId);

    /**
     * 根据组合ID和货品ID删除记录
     * 
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 删除的行数{@link java.lang.Integer}
     */
    int delGroupReleProductByGroupIdAndProductId(Long groupId, Long productId);

    /**
     * 根据组合ID和货品ID数组批量删除
     * 
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productIds
     *            货品ID的数组 {@link java.lang.Long}
     * @return 删除的行数{@link java.lang.Integer}
     */
    int batchDelGroupReleProductByGroupIdAndProductIds(Long groupId,
            Long[] productIds);

    /**
     * 根据组合ID和货品ID数组添加
     * 
     * @param groupId
     *            组合ID {@link java.lang.Long}
     * @param productIds
     *            货品ID数组 {@link java.lang.Long}
     * @return 插入的行数
     */
    int addGroupReleProduct(Long groupId, Long[] productIds);
}
