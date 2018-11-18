/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsReleTag;
import com.ningpai.goods.dao.GoodsReleTagMapper;
import com.ningpai.goods.vo.GoodsReleTagVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关联标签
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月24日 下午8:13:52
 * @version 1.0
 */
@Repository("GoodsReleTagMapper")
public class GoodsReleTagMapperImpl extends BasicSqlSupport implements
        GoodsReleTagMapper {
    /**
     * 删除记录 （更新字段）
     *
     * @param map
     *            封装参数
     * @return 删除的行数
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsReleTagMapper.deleteByPrimaryKey",
                map);
    }

    /**
     * 插入一条记录(全属性 不推荐)
     *
     * @param record
     *            需要插入的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsReleTag record) {
        return this.insert("com.ningpai.goods.dao.GoodsReleTagMapper.insert",
                record);
    }

    /**
     * 插入一条记录(可选属性 推荐)
     *
     * @param record
     *            需要插入的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsReleTag record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsReleTagMapper.insertSelective",
                record);
    }

    /**
     * 根据主键查询实体
     *
     * @param relaTagId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    public GoodsReleTag selectByPrimaryKey(Long relaTagId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsReleTagMapper.selectByPrimaryKey",
                relaTagId);
    }

    /**
     * 更新一条记录(可选属性 推荐)
     *
     * @param record
     *            需要更新的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsReleTag record) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleTagMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 插入一条记录(全属性 不推荐)
     *
     * @param record
     *            需要更新的实体{@link com.ningpai.goods.bean.GoodsReleTag}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsReleTag record) {
        return this.update(
                "com.ningpai.goods.dao.GoodsReleTagMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据商品ID查询所有的关联记录
     *
     * @param goodsId
     *            商品的ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    public List<GoodsReleTagVo> queryAllByGoodsId(Long goodsId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsReleTagMapper.queryAllByGoodsId",
                goodsId);
    }

    /**
     * 根据商品ID和标签ID查询实体
     *
     * @param map
     *            {@link java.util.Map}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleTag}
     */
    public GoodsReleTag queryByGoodsIdAndTagId(Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsReleTagMapper.queryByGoodsIdAndTagId",
                        map);
    }

    /**
     * 根据货品ID查询关联的标签信息
     *
     * @param productId
     *            货品ID {@link Long}
     * @return 查询到的关联的集合 {@link GoodsReleTagVo}
     */
    public List<GoodsReleTagVo> queryAllByProductId(Long productId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsReleTagMapper.queryAllByProductId",
                productId);
    }

}
