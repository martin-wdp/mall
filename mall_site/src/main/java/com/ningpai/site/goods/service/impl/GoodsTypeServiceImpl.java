/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service.impl;

import com.ningpai.site.goods.dao.GoodsTypeMapper;
import com.ningpai.site.goods.service.GoodsTypeService;
import com.ningpai.site.goods.vo.GoodsTypeVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 商品类型Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月16日 下午4:47:31
 * @version 1.0
 */
@Service("HsiteGoodsTypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
    private GoodsTypeMapper goodsTypeMapper;

    public GoodsTypeMapper getGoodsTypeMapper() {
        return goodsTypeMapper;
    }

    @Resource(name = "HsiteGoodsTypeMapper")
    public void setGoodsTypeMapper(GoodsTypeMapper goodsTypeMapper) {
        this.goodsTypeMapper = goodsTypeMapper;
    }

    /**
     * 根据分类ID查询类型的Vo实体
     * @param catId
     *            分类ID
     * @return
     */
    public GoodsTypeVo queryGoodsTypeByCatId(Long catId) {
        //根据分类ID查询类型的Vo实体
        return this.goodsTypeMapper.queryTypeVoByCatId(catId);
    }
}
