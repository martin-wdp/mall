/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.site.goods.dao.GoodsTypeMapper;
import com.ningpai.goods.site.goods.vo.GoodsTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 类型DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午7:14:31
 * @version 1.0
 */
@Repository("GoodsSiteGoodsTypeMapper")
public class GoodsTypeMapperImpl extends BasicSqlSupport implements GoodsTypeMapper {
    /**
     * 根据主键ID查询VO
     *
     * @param catId
     *            分类ID
     * @return 查询到的实体
     * @see com.ningpai.site.goods.dao.GoodsTypeMapper#queryTypeVoByCatId(java.lang .Long)
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsTypeMapper.queryTypeVoByCatId", catId);
    }

}
