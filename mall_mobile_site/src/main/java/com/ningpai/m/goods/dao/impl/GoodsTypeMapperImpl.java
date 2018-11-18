/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.m.goods.dao.GoodsTypeMapper;
import com.ningpai.m.goods.vo.GoodsTypeVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 类型DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午7:14:31
 * @version 1.0
 */
@Repository("HsiteGoodsTypeMapper")
public class GoodsTypeMapperImpl extends BasicSqlSupport implements GoodsTypeMapper {
    /**
     * 根据主键ID查询VO
     *
     * @param catId
     *            分类ID
     * @return 查询到的实体
     */
    public GoodsTypeVo queryTypeVoByCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsTypeMapper.queryTypeVoByCatId", catId);
    }

}
