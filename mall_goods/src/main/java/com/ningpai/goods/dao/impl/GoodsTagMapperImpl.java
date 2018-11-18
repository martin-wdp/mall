/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.goods.dao.GoodsTagMapper;
import com.ningpai.goods.util.SelectBean;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品标签数据层实现类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月14日 下午2:13:25
 * @version 1.0
 */
@Repository("GoodsTagMapperImpl")
public class GoodsTagMapperImpl extends BasicSqlSupport implements
        GoodsTagMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            标签ID，删除人名称{@link java.util.Map}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this.update(
                "com.ningpai.goods.dao.GoodsTagMapper.deleteByPrimaryKey", map);
    }

    /**
     * 插入一个商品标签(全属性)
     *
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.baan.GoodsTag}
     * @return 影响的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsTag record) {

        return 0;
    }

    /**
     * 插入一个商品标签(可选属性)
     *
     * @param record
     *            待插入的商品标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsTag record) {
        return this.insert(
                "com.ningpai.goods.dao.GoodsTagMapper.insertSelective", record);
    }

    /**
     * 根据主键查询标签
     *
     * @param tagId
     *            标签的主键ID {@link java.lang.Long}
     * @return 查询到的标签实体 {@link com.ningpai.goods.bean.GoodsTag}
     */
    public GoodsTag selectByPrimaryKey(Long tagId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTagMapper.selectByPrimaryKey",
                tagId);
    }

    /**
     * 更新商品标签(可选属性)
     *
     * @param record
     *            待更新的标签实体
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsTag record) {
        return this
                .update("com.ningpai.goods.dao.GoodsTagMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新商品标签(全属性)
     *
     * @param record
     *            待更新的标签实体
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsTag record) {

        return 0;
    }

    /**
     * 查询所有的商品标签
     *
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     *         {@link java.util.Map}
     */
    public List<Object> selectAllTag(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTagMapper.selectAllTag", map);
    }

    /**
     * 根据条件模糊查询
     *
     * @param map
     *            {@link java.util.Map}
     * @return {@link com.ningpai.goods.bean.GoodsTag} {@link java.util.List}
     */
    public List<GoodsTag> queryTagByParam(Map<String, String> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsTagMapper.queryTagByParam", map);
    }

    /**
     * 查询所有的行数
     *
     * @return {@link java.lang.Integer}
     */
    public int queryTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTagMapper.queryTotalCount",
                selectBean);
    }

    /**
     * 查询所有的商品标签，已经删除的也算
     *
     * @return 查询到的标签列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsTag}
     */
    public List<GoodsTag> queryAllTag() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsTagMapper.queryAllTag");
    }

    /**
     * 根据标签名称查询行数
     *
     * @param tagName
     *            标签名称
     * @return 行数
     */
    public int queryByTagName(String tagName) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsTagMapper.queryByTagName", tagName);
    }
}
