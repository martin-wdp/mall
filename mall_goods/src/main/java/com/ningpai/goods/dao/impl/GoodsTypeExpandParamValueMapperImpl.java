/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTypeExpandParamValue;
import com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品扩展属性值Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午3:07:04
 * @version 1.0
 */
@Repository("GoodsTypeExpandParamValueMapper")
public class GoodsTypeExpandParamValueMapperImpl extends BasicSqlSupport
        implements GoodsTypeExpandParamValueMapper {
    /**
     * 删除类型扩展属性值
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条记录 （全属性 不推荐）
     *
     * @param record
     *            待插入的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsTypeExpandParamValue record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.insert",
                record);
    }

    /**
     * 插入一条记录 （可选属性 推荐）
     *
     * @param record
     *            待插入的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsTypeExpandParamValue record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键ID查询
     *
     * @param expandparamValueId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    public GoodsTypeExpandParamValue selectByPrimaryKey(Long expandparamValueId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.selectByPrimaryKey",
                        expandparamValueId);
    }

    /**
     * 更新 （可选属性 推荐）
     *
     * @param record
     *            待更新的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTypeExpandParamValue record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新 （全属性 不推荐）
     *
     * @param record
     *            待更新的实体
     *            {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTypeExpandParamValue record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据属性ID查询所有的属性值
     *
     * @param paramId
     *            扩展属性的ID {@link java.lang.Long}
     * @return 查询到的实体列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeExpandParamValue}
     */
    public List<GoodsTypeExpandParamValue> queryParamValueByParamId(Long paramId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsTypeExpandParamValueMapper.queryParamValueByParamId",
                        paramId);
    }

}
