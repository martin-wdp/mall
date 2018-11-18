/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsSpecDetail;
import com.ningpai.goods.dao.GoodsSpecDetailMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 规格值DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 下午4:10:52
 * @version 1.0
 */
@Repository("GoodsSpecDetailMapper")
public class GoodsSpecDetailMapperImpl extends BasicSqlSupport implements
        GoodsSpecDetailMapper {
    /**
     * 根据规格值主键删除
     *
     * @param map
     *            封装的删除的参数包括 待删除的ID，删除人名称 {@link java.util.Map}
     * @return {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsSpecDetailMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条规格值(全属性 不推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @return {@link java.lang.Integer}
     */
    public int insert(GoodsSpecDetail record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsSpecDetailMapper.insert", record);
    }

    /**
     * 插入一条规格值(可选属性 推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @return {@link java.lang.Integer}
     */
    public int insertSelective(GoodsSpecDetail record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsSpecDetailMapper.insertSelective",
                record);
    }

    /**
     * 根据主键查询规格值
     *
     * @param specDetailId
     *            {@link java.lang.Long}
     * @return {@link com.ningpai.goods.bean.GoodsSpecDetail}
     */
    public GoodsSpecDetail selectByPrimaryKey(Long specDetailId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsSpecDetailMapper.selectByPrimaryKey",
                        specDetailId);
    }

    /**
     * 更新规格值(可选属性 推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @return {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsSpecDetail record) {
        return this
                .update("com.ningpai.goods.dao.GoodsSpecDetailMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新规格值(全属性 不推荐)
     *
     * @param record
     *            {@link com.ningpai.goods.bean.GoodsSpecDetail}
     * @return {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsSpecDetail record) {
        return this
                .update("com.ningpai.goods.dao.GoodsSpecDetailMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据规格ID查询规格值列表
     *
     * @param specId
     *            {@link java.lang.Long}
     * @return {@link java.util.List}
     */
    public List<Object> querySpecDeetailBySpecId(Long specId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsSpecDetailMapper.querySpecDeetailBySpecId",
                        specId);
    }

    /**
     * 根据规格ID查询规格值列表
     *
     * @param specId
     *            {@link java.lang.Long}
     * @return {@link java.util.List}
     * @author NINGPAI-LIH
     */
    @Override
    public List<GoodsSpecDetail> selectSpecDeetailBySpecId(Long specId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsSpecDetailMapper.querySpecDeetailBySpecId",
                        specId);
    }

}
