/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.service;

import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;

import java.math.BigDecimal;

/**
 * 货品分仓Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月28日 下午7:48:49
 * @version
 */
public interface ProductWareService {
    /**
     * 查询仓库信息
     * 
     * @param did
     * @return
     */
    WareHouse findWare(Long did);

    /**
     * 保存关联信息
     * 
     * @param productId
     *            货品ID
     * @param productStocks
     *            货品库存数组
     * @param prouctPrices
     *            货品库存价格
     * @param prouctPrices
     *            货品库存会员价格
     * @return 插入的价格
     */
    int calcProductWare(Long productId, Long[] productStocks,
            BigDecimal[] productPrices, BigDecimal[] productVipPrices, Long[] wareId);

    /**
     * 根据货品ID和地区ID查询关联记录
     * 
     * @param productId
     *            货品ID
     * @param distinctId
     *            地区ID
     * @return 查询到的记录
     */
    ProductWare queryProductWareByProductIdAndDistinctId(Long productId,
            Long distinctId);

}
