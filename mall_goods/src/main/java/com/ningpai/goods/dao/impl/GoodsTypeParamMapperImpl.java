/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTypeParam;
import com.ningpai.goods.dao.GoodsTypeParamMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品类型详细参数值DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午3:35:18
 * @version 1.0
 */
@Repository("GoodsTypeParamMapper")
public class GoodsTypeParamMapperImpl extends BasicSqlSupport implements
        GoodsTypeParamMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeParamMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入记录 （全属性 不推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 插入的行数
     */
    public int insert(GoodsTypeParam record) {
        return this.insert("com.ningpai.goods.dao.GoodsTypeParamMapper.insert",
                record);
    }

    /**
     * 插入记录 （可选属性 推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 插入的行数
     */
    public int insertSelective(GoodsTypeParam record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTypeParamMapper.insertSelective",
                record);
    }

    /**
     * 根据主键ID查询
     *
     * @param paramId
     *            主键ID{@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    public GoodsTypeParam selectByPrimaryKey(Long paramId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeParamMapper.selectByPrimaryKey",
                        paramId);
    }

    /**
     * 更新（可选属性 推荐）
     *
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTypeParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeParamMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新（全属性 不推荐）
     *
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeParam}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTypeParam record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeParamMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据类型ID查询详细参数列表
     *
     * @param typeId
     *            类型ID {@link java.lang.Long}
     * @return 查询到的详细参数列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeParam}
     */
    public List<GoodsTypeParam> queryTypeParamByTypeId(Long typeId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsTypeParamMapper.queryTypeParamByTypeId",
                        typeId);
    }

}
