/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.m.goods.dao.GoodsTypeMapper;
import com.ningpai.m.goods.service.GoodsTypeService;
import com.ningpai.m.goods.vo.GoodsTypeVo;

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
     *
     * @param catId
     *            分类ID
     * @return 查询到的实体
     */
    public GoodsTypeVo queryGoodsTypeByCatId(Long catId) {
        return this.goodsTypeMapper.queryTypeVoByCatId(catId);
    }
}
