/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.dao.GoodsImageMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 货品图片Dao实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月3日 下午4:14:23
 * @version 1.0
 */
@Repository("GoodsImageMapper")
public class GoodsImageMapperImpl extends BasicSqlSupport implements
        GoodsImageMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            {@link java.util.Map}
     * @return 删除的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsImageMapper.deleteByPrimaryKey",
                map);
    }

    /**
     * 插入一条记录
     *
     * @param record
     *            待插入的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @return 插入的行数 {@link java.lang.Long}
     */
    public Long insertSelective(GoodsImage record) {
        this.insert("com.ningpai.goods.dao.GoodsImageMapper.insertSelective",
                record);
        return this
                .selectOne("com.ningpai.goods.dao.GoodsImageMapper.selectLastId");
    }

    /**
     * 根据主键ID查询
     *
     * @param goodsImgId
     *            主键ID
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsImage}
     */
    public GoodsImage selectByPrimaryKey(Long goodsImgId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsImageMapper.selectByPrimaryKey",
                goodsImgId);
    }

    /**
     * 更新记录
     *
     * @param record
     *            待更新的实体 {@link com.ningpai.goods.bean.GoodsImage}
     * @return 更新的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsImage record) {
        return this
                .update("com.ningpai.goods.dao.GoodsImageMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据货品ID查询所有的关联记录
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsImage}
     */
    public List<GoodsImage> queryByProductId(Long productId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsImageMapper.queryByProductId",
                productId);
    }

    /**
     * 根据sku id修改商品排序
     * 
     * @param productId
     *            skuId
     * @return
     */
    @Override
    public int updateByProductInfoId(Long productId) {
        return this.update(
                "com.ningpai.goods.dao.GoodsImageMapper.updateByProductInfoId",
                productId);
    }

    /**
     * 设置sku 默认图片
     * 
     * @param goodsImgId
     *            商品图片id
     * @return
     */
    @Override
    public int setDefaultImage(Long goodsImgId) {
        return this.update(
                "com.ningpai.goods.dao.GoodsImageMapper.setDefaultImage",
                goodsImgId);
    }

}
