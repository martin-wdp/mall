/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsReleExpandParam;
import com.ningpai.goods.dao.GoodsReleExpandParamMapper;
import com.ningpai.goods.vo.GoodsReleExpandParamVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品关联类型扩展参数表
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 上午11:52:20
 * @version 1.0
 */
@Repository("GoodsReleExpandParamMapper")
public class GoodsReleExpandParamMapperImpl extends BasicSqlSupport implements
        GoodsReleExpandParamMapper {
    /**
     * 删除商品关联类型扩展属性
     *
     * @param map
     *            封装相关参数 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleExpandParamMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入记录 (全属性 不推荐)
     *
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsReleExpandParam record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsReleExpandParamMapper.insert",
                record);
    }

    /**
     * 插入记录 (可选属性 推荐)
     *
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsReleExpandParam record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsReleExpandParamMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param releExpandparamId
     *            主键id {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     */
    public GoodsReleExpandParam selectByPrimaryKey(Long releExpandparamId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsReleExpandParamMapper.selectByPrimaryKey",
                        releExpandparamId);
    }

    /**
     * 更新信息 (可选属性 推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsReleExpandParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleExpandParamMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新信息 (全属性 不推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsReleExpandParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleExpandParamMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据商品ID查询关联的所有的扩展属性
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.vo.GoodsReleExpandParamVo}
     */
    public List<GoodsReleExpandParamVo> queryAllByGoodsId(Long goodsId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsReleExpandParamMapper.queryAllByGoodsId",
                        goodsId);
    }

    /**
     * 根据商品ID和扩展属性ID查询记录
     *
     * @param map
     *            封装的参数 {@link java.util.Map}
     * @return 查询到的记录 {@link com.ningpai.goods.bean.GoodsReleExpandParam}
     */
    public GoodsReleExpandParam queryByGoodsIdAndExpandParamId(
            Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsReleExpandParamMapper.queryByGoodsIdAndExpandParamId",
                        map);
    }

    /**
     * 根据商品ID删除所有的扩展属性
     *
     * @param map
     *            封装的参数
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int delAllExpandParamByGoodsId(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsReleExpandParamMapper.delAllExpandParamByGoodsId",
                        map);
    }

}
