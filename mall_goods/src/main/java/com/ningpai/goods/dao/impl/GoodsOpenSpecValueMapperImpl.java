/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsOpenSpecValue;
import com.ningpai.goods.dao.GoodsOpenSpecValueMapper;
import com.ningpai.goods.vo.GoodsOpenSpecValueVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品开启规格值DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月30日 下午5:43:25
 * @version 1.0
 */
@Repository("GoodsOpenSpecValueMapper")
public class GoodsOpenSpecValueMapperImpl extends BasicSqlSupport implements
        GoodsOpenSpecValueMapper {

    /**
     * 插入记录
     *
     * @param record
     *            待插入的实体 {@link GoodsOpenSpecValue}
     * @return 插入的行数 {@link Integer}
     */
    public int insertSelective(GoodsOpenSpecValue record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsOpenSpecValueMapper.insertSelective",
                        record);
    }

    /**
     * 根据规格ID查询关联集合
     *
     * @param specId
     *            规格ID {@link Long}
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecValue> queryOpenValListBySpecId(Long specId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsOpenSpecValueMapper.queryOpenValListBySpecId",
                        specId);
    }

    /**
     * 根据商品ID查询关联集合
     *
     * @param goodsId
     *            商品ID {@link Long}
     * @return 插叙到的集合 {@link List}
     */
    public List<GoodsOpenSpecValue> queryOpenValListByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsOpenSpecValueMapper.queryOpenValListByGoodsId",
                        goodsId);
    }

    /**
     * 根据商品ID和规格ID查询开启的规格列表
     *
     * @param map
     *            商品ID 和 规格ID
     * @return 查询到的集合 {@link List}
     */
    public List<GoodsOpenSpecValueVo> queryOpenValueListByGoodsIdAndSpecId(
            Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsOpenSpecValueMapper.queryOpenValueListByGoodsIdAndSpecId",
                        map);
    }

    /**
     * 根据商品ID和规格值ID查询开启的规格列表
     *
     * @param map
     *            商品ID 和 规格ID
     * @return 查询到的集合 {@link List}
     */
    @Override
    public GoodsOpenSpecValueVo queryOpenValueListByGoodsIdAndSpecValueId(
            Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsOpenSpecValueMapper.queryOpenValueListByGoodsIdAndSpecValueId",
                        map);
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
                "com.ningpai.goods.dao.GoodsOpenSpecValueMapper.deleteByGoodsId",
                goodsId);
    }

}
