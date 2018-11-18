/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service;

import com.ningpai.m.goods.vo.GoodsTypeVo;

/**
 * 商品类型Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月16日 下午4:42:02
 * @version 1.0
 */
public interface GoodsTypeService {
    /**
     * 根据分类ID查询类型的Vo实体
     * 
     * @param catId
     *            分类ID
     * @return 查询到的实体
     */
    GoodsTypeVo queryGoodsTypeByCatId(Long catId);
}
