/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsProductReleSpec;
import com.ningpai.goods.dao.GoodsProductReleSpecMapper;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 货品关联规格值Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月27日 下午4:23:38
 * @version 1.0
 */
@Repository("GoodsProductReleSpecMapper")
public class GoodsProductReleSpecMapperImpl extends BasicSqlSupport implements
        GoodsProductReleSpecMapper {
    /**
     * 删除记录
     *
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 删除的ID
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .delete("com.ningpai.goods.dao.GoodsProductReleSpecMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 根据货品id删除记录
     *
     * @param productId
     *            货品id
     * @return
     */
    @Override
    public int deleteByProductId(Long productId) {
        return this
                .delete("com.ningpai.goods.dao.GoodsProductReleSpecMapper.deleteByProductId",
                        productId);
    }

    /**
     * 插入一条记录(可选属性 推荐)
     *
     * @param record
     *            待插入的实体{@link com.ningpai.goods.bean.GoodsProductReleSpec}
     * @return 插入的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsProductReleSpec record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsProductReleSpecMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param releSpecDetailId
     *            主键ID {@link java.lang.Long}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    public GoodsProductReleSpec selectByPrimaryKey(Long releSpecDetailId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsProductReleSpecMapper.selectByPrimaryKey",
                        releSpecDetailId);
    }

    /**
     * 更新实体
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     * @return 更新的行数
     */
    public int updateByPrimaryKeySelective(GoodsProductReleSpec record) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductReleSpecMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据货品ID查询关联的规格值列表
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    public List<GoodsProductReleSpecVo> queryAllSimpleByProductId(Long productId) {
       return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductReleSpecMapper.queryAllSimpleByProductId",
                        productId);
    }

    /**
     * 根据货品ID查询关联的规格值列表
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    public List<GoodsProductReleSpecVo> queryAllByProductId(Long productId) {
        return this
                .selectList(
                        "com.ningpai.goods.dao.GoodsProductReleSpecMapper.queryAllByProductId",
                        productId);
    }

    /**
     * 根据货品ID和规格ID更新关联关系
     *
     * @param map
     *            封装参数的 {@link java.util.Map}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateProductReleSpec(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsProductReleSpecMapper.updateProductReleSpec",
                        map);
    }

}
