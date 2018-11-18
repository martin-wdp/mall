/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTypeExpandParam;
import com.ningpai.goods.dao.GoodsTypeExpandParamMapper;
import com.ningpai.goods.vo.GoodsTypeExpandParamVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品类型扩展属性Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午2:40:07
 * @version 1.0
 */
@Repository("GoodsTypeExpandParamMapper")
public class GoodsTypeExpandParamMapperImpl extends BasicSqlSupport implements
        GoodsTypeExpandParamMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入记录 （全属性 不推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsTypeExpandParam record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTypeExpandParamMapper.insert",
                record);
    }

    /**
     * 插入记录 （可选属性 推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsTypeExpandParam record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsTypeExpandParamMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param expandparamId
     *            扩展属性主键 {@link java.lang.Long}
     * @return 查询到的扩展属性实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    public GoodsTypeExpandParam selectByPrimaryKey(Long expandparamId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeExpandParamMapper.selectByPrimaryKey",
                        expandparamId);
    }

    /**
     * 更新扩展属性 (可选属性 推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTypeExpandParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新扩展属性 (全属性 不推荐)
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTypeExpandParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据类型ID查询所有的扩展属性
     *
     * @param typeId
     *            商品类型的主键ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParam}
     */
    public List<GoodsTypeExpandParamVo> queryAllExpandParam(Long typeId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsTypeExpandParamMapper.queryAllExpandParam",
                        typeId);
    }

    /**
     * 查询最后插入的id
     *
     * @return 查询到的ID
     */
    public Long selectLastId() {
        return this
                .selectOne("com.ningpai.goods.dao.GoodsTypeExpandParamMapper.selectLastId");
    }

}
