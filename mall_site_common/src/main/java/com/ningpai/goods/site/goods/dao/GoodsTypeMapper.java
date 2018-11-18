/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao;

import com.ningpai.goods.site.goods.vo.GoodsTypeVo;

/**
 * 商品类型Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月16日 下午4:36:16
 * @version 1.0
 */
public interface GoodsTypeMapper {
    /**
     * 根据主键ID查询VO
     * 
     * @param catId
     *            分类ID
     * @return 查询到的实体
     */
    GoodsTypeVo queryTypeVoByCatId(Long catId);
}
