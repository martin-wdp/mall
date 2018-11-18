/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTypeSpec;
import com.ningpai.goods.dao.GoodsTypeSpecMapper;
import com.ningpai.goods.vo.GoodsTypeSpecVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品类型关联规格
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 下午4:26:20
 * @version 1.0
 */
@Repository("GoodsTypeSpecMapper")
public class GoodsTypeSpecMapperImpl extends BasicSqlSupport implements
        GoodsTypeSpecMapper {

    /**
     * 根据主键删除
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsTypeSpecMapper.deleteByPrimaryKey",
                map);
    }

    /**
     * 插入 （全属性 不推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 插入的行数
     */
    public int insert(GoodsTypeSpec record) {
        return this.insert("com.ningpai.goods.dao.GoodsTypeSpecMapper.insert",
                record);
    }

    /**
     * 插入 （可选属性 推荐）
     *
     * @param record
     *            插入的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 插入的行数
     */
    public int insertSelective(GoodsTypeSpec record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTypeSpecMapper.insertSelective",
                record);
    }

    /**
     * 根据主键ID查询
     *
     * @param typeSpecId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     */
    public GoodsTypeSpec selectByPrimaryKey(Long typeSpecId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTypeSpecMapper.selectByPrimaryKey",
                typeSpecId);
    }

    /**
     * 更新 （可选属性 推荐）
     *
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTypeSpec record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeSpecMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新 （全属性 不 推荐）
     *
     * @param record
     *            更新的实体 {@link com.ningpai.goods.bean.GoodsTypeSpec}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTypeSpec record) {
        return this.update(
                "com.ningpai.goods.dao.GoodsTypeSpecMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据类型ID查询列表
     *
     * @param typeId
     *            类型ID {@link java.lang.Longs}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeSpec}
     */
    public List<GoodsTypeSpecVo> queryTypeSpecBytypeId(Long typeId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsTypeSpecMapper.queryTypeSpecBytypeId",
                        typeId);
    }

    /**
     * 根据类型ID和规格id查询
     *
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 查询到的类型关联规格
     */
    public GoodsTypeSpec queryTypeSpecByTypeIdAndSpecId(Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeSpecMapper.queryTypeSpecByTypeIdAndSpecId",
                        map);
    }

}
