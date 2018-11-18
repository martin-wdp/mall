/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsReleParam;
import com.ningpai.goods.dao.GoodsReleParamMapper;
import com.ningpai.goods.vo.GoodsReleParamVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关联类型详细参数
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午2:10:27
 * @version 1.0
 */
@Repository("GoodsReleParamMapper")
public class GoodsReleParamMapperImpl extends BasicSqlSupport implements
        GoodsReleParamMapper {
    /**
     * 删除记录(更新删除标记)
     *
     * @param map
     *            封装删除参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleParamMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条记录（全属性 不推荐）
     *
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 插入的行数{@link java.lang.Integer}
     */
    public int insert(GoodsReleParam record) {
        return this.insert("com.ningpai.goods.dao.GoodsReleParamMapper.insert",
                record);
    }

    /**
     * 插入一条记录（可选属性 推荐）
     *
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 插入的行数{@link java.lang.Integer}
     */
    public int insertSelective(GoodsReleParam record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsReleParamMapper.insertSelective",
                record);
    }

    /**
     * 根据主键ID查询
     *
     * @param releParamId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    public GoodsReleParam selectByPrimaryKey(Long releParamId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsReleParamMapper.selectByPrimaryKey",
                        releParamId);
    }

    /**
     * 更新一条记录（可选属性 推荐）
     *
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 更新的行数{@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsReleParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleParamMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新一条记录（全属性 不推荐）
     *
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsReleParam}
     * @return 更新的行数{@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsReleParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleParamMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据商品ID查询所有关联的属性
     *
     * @param goodsId
     *            商品主键ID {@link java.lang.Long}
     * @return 查询到的关联的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.util.GoodsReleParamVo}
     */
    public List<GoodsReleParamVo> queryAllByGoodsId(Long goodsId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsReleParamMapper.queryAllByGoodsId",
                goodsId);
    }

    /**
     * 根据商品ID和详细参数ID查询对象
     *
     * @param map
     *            封装的查询对象 {@link java.util.Map}
     * @return 查询到的实体对象 {@link com.ningpai.goods.bean.GoodsReleParam}
     */
    public GoodsReleParam queryByGoodsIdAndParamId(Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsReleParamMapper.queryByGoodsIdAndParamId",
                        map);
    }

    /**
     * 根据商品ID删除所有的关联的详细参数的记录
     *
     * @param map
     *            封装的所需要的参数
     * @return 删除的行数{@link java.lang.Integer}
     */
    public int delAllReleParamByGoodsId(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleParamMapper.delAllReleParamByGoodsId",
                        map);
    }

}
