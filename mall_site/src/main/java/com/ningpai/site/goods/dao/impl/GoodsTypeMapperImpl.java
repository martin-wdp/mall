/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.site.goods.vo.GoodsTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.goods.dao.GoodsTypeMapper;

/**
 * 类型DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午7:14:31
 * @version 1.0
 */
@Repository("HsiteGoodsTypeMapper")
public class GoodsTypeMapperImpl extends BasicSqlSupport implements
        GoodsTypeMapper {
    /**
     * 根据主键ID查询VO
     * @param catId
     *            分类ID
     * @return
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsTypeMapper.queryTypeVoByCatId",
                        catId);
    }

}
