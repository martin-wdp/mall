/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsCate;
import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTypeBrand;
import com.ningpai.goods.dao.GoodsTypeBrandMapper;
import com.ningpai.goods.vo.GoodsTypeBrandVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品类型关联品牌DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月21日 上午10:49:56
 * @version 1.0
 */
@Repository("GoodsTypeBrandMapper")
public class GoodsTypeBrandMapperImpl extends BasicSqlSupport implements
        GoodsTypeBrandMapper {
    /**
     * 根据主键ID删除
     *
     * @param map
     *            主键ID，删除人名称 {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeBrandMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 新增一条记录 （全属性 不推荐）
     *
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsTypeBrand record) {
        return this.insert("com.ningpai.goods.dao.GoodsTypeBrandMapper.insert",
                record);
    }

    /**
     * 新增一条记录 （可选属性 推荐）
     *
     * @param record
     *            需要插入的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsTypeBrand record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTypeBrandMapper.insertSelective",
                record);
    }

    /**
     * 根据主键查询
     *
     * @param typeBrandId
     *            主键ID {@link java.lang.Long}
     * @return 类型关联品牌类实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    public GoodsTypeBrand selectByPrimaryKey(Long typeBrandId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeBrandMapper.selectByPrimaryKey",
                        typeBrandId);
    }

    /**
     * 更新实体（可选属性 推荐）
     *
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTypeBrand record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeBrandMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新实体 （全属性 不推荐）
     *
     * @param record
     *            需要更新的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTypeBrand record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTypeBrandMapper.updateByPrimaryKey",
                        record);
    }

    /**
     * 根据类型ID查询所有的类型关联品牌实体
     *
     * @param typeId
     *            商品类型主键ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    public List<GoodsTypeBrandVo> queryAllTypeBrand(Long typeId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTypeBrandMapper.queryAllTypeBrand",
                typeId);
    }

    /**
     * 根据类别的集合查询出所有的品牌集合
     * @param cate
     * @return
     */
    @Override
    public List<GoodsTypeBrandVo> queryBrandByCatIds(List<GoodsCate> cate) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTypeBrandMapper.queryBrandByCatIds",
                cate);
    }





    /**
     * 根据类型ID和品牌ID查询实体
     *
     * @param map
     *            {@link java.util.Map}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsTypeBrand}
     */
    public GoodsTypeBrand queryTypeBrandByTypeIdAndBrandId(Map<String, Long> map) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsTypeBrandMapper.queryTypeBrandByTypeIdAndBrandId",
                        map);
    }

}
