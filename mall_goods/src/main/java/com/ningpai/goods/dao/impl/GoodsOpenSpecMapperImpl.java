/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsOpenSpec;
import com.ningpai.goods.dao.GoodsOpenSpecMapper;
import com.ningpai.goods.vo.GoodsOpenSpecVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品开启规格Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午2:55:49
 * @version 1.0
 */
@Repository("GoodsOpenSpecMapper")
public class GoodsOpenSpecMapperImpl extends BasicSqlSupport implements
        GoodsOpenSpecMapper {
    /**
     * 插入一条记录
     *
     * @param record
     *            待插入的实体 {@link GoodsOpenSpec}
     * @return 插入的行数 {@link Integer}
     */
    public int insertSelective(GoodsOpenSpec record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsOpenSpecMapper.insertSelective",
                record);
    }

    /**
     * 根据商品ID查询开启的规格的集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的集合{@link List}
     */
    public List<GoodsOpenSpecVo> queryOpenSpecListByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsOpenSpecMapper.queryOpenSpecListByGoodsId",
                        goodsId);
    }

    /**
     * 根据商品id，删除商品与规格之间关系
     *
     * @param goodsId
     *            商品id
     */
    @Override
    public void deleteByGoodsId(Long goodsId) {
        this.delete(
                "com.ningpai.goods.dao.GoodsOpenSpecMapper.deleteByGoodsId",
                goodsId);
    }

}
