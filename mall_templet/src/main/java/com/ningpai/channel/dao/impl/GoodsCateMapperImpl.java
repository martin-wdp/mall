/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.channel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.channel.bean.GoodsCate;
import com.ningpai.channel.dao.GoodsCateMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品分类Dao实现类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:42:29
 * @version 1.0
 */
@Repository("ChannelGoodsCateMapper")
public class GoodsCateMapperImpl extends BasicSqlSupport implements GoodsCateMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.GoodsCateMapper#selectGoosCateById(java.lang.Long)
     */
    @Override
    public GoodsCate selectGoosCateById(Long catId) {
        return this.selectOne("com.ningpai.channel.dao.GoodsCateMapper.selectGoosCateById", catId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.GoodsCateMapper#queryAllFirstGradeGoosCate()
     */
    @Override
    public List<GoodsCate> queryAllFirstGradeGoosCate() {
        return this.selectList("com.ningpai.channel.dao.GoodsCateMapper.queryAllFirstGradeGoosCate");
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.GoodsCateMapper#queryGoosCateByParentId(java.lang.Long)
     */
    @Override
    public List<GoodsCate> queryGoosCateByParentId(Long parentId) {
        return this.selectList("com.ningpai.channel.dao.GoodsCateMapper.queryGoosCateByParentId", parentId);
    }
}
