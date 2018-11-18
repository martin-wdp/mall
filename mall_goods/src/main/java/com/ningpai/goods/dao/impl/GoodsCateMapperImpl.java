/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.dao.GoodsCateMapper;
import com.ningpai.goods.vo.GoodsCateVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品分类Dao实现类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午4:42:29
 * @version 1.0
 */
@Repository("GoodsCateMapper")
public class GoodsCateMapperImpl extends BasicSqlSupport implements
        GoodsCateMapper {
    /**
     * 根据主键删除
     *
     * @param map
     *            分类主键ID和删除人名称 {@link java.lang.Long}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int deleteByPrimaryKey(Map<String, String> map) {
        return this
                .update("com.ningpai.goods.dao.GoodsCateMapper.deleteByPrimaryKey",
                        map);
    }

    /**
     * 插入一条数据(全属性，不推荐)
     *
     * @param record
     *            插入的分类实体{@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insert(GoodsCate record) {
        return this.insert("com.ningpai.goods.dao.GoodsCateMapper.insert",
                record);
    }

    /**
     * 插入一条商品分类信息 (可选属性 推荐)
     *
     * @param record
     *            待插入的商品分类实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int insertSelective(GoodsCate record) {
        return this
                .insert("com.ningpai.goods.dao.GoodsCateMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     *
     * @param catId
     *            分类ID {@link java.lang.Integer}
     * @return 查询到的实体 {@link com.ningpai.goods.bean.GoodsCate}
     */
    public GoodsCateVo selectByPrimaryKey(Long catId) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsCateMapper.selectByPrimaryKey",
                catId);
    }

    /**
     * 更新商品分类 (可选属性 推荐)
     *
     * @param record
     *            更新的商品实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKeySelective(GoodsCate record) {
        return this
                .update("com.ningpai.goods.dao.GoodsCateMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新商品分类
     *
     * @param record
     *            更新商品分类的实体 {@link com.ningpai.goods.bean.GoodsCate}
     * @return 受影响的行数 {@link java.lang.Integer}
     */
    public int updateByPrimaryKey(GoodsCate record) {
        return this.update(
                "com.ningpai.goods.dao.GoodsCateMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据pageBean分页查询分类列表
     *
     * @param map
     *            封装分页参数 {@link java.util.Map}
     * @return 查询到的列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsCateMapper.queryByPageBean", map);
    }

    /**
     * 查询所有的记录行数
     *
     * @return {@link java.lang.Integer}
     */
    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsCateMapper.queryTotalCount", map);
    }

    /**
     * 查询所有的商品分类
     *
     * @return 所有的商品分类列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsCate}
     */
    public List<GoodsCateVo> queryAllGoosCate() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsCateMapper.queryAllGoosCate");
    }

    @Override
    public List<GoodsCateVo> queryFirstLevelGoodsCate() {
        return this.selectList("com.ningpai.goods.dao.GoodsCateMapper.queryFirstLevelGoodsCate");
    }

    /**
     * 根据父分类ID查询子分类的个数
     *
     * @param cateId
     * @return
     */
    public int querySonCountByParentId(Long cateId) {
        return this
                .selectOne(
                        "com.ningpai.goods.dao.GoodsCateMapper.querySonCountByParentId",
                        cateId);
    }

    /**
     * 根据分类名称搜索商品分类
     *
     * @param cateName
     *            分类名称
     * @return 查询到的分类信息
     */
    public GoodsCate queryCateByCateName(String cateName) {
        return this.selectOne(
                "com.ningpai.goods.dao.GoodsCateMapper.queryCateByCateName",
                cateName);
    }

    /**
     * 根据list查询所有商品分类
     *
     * @param list
     * @return List
     */
    public List<Object> selectProductCateList(List<Long> list) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsCateMapper.selectProductCateList",
                list);
    }

    /**
     * 查询所有商品分类
     *
     * @return List
     */
    public List<GoodsCate> queryAllGoodCate() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsCateMapper.queryAllGoodCate");
    }

    /**
     * 根据分类查询所有的子分类
     *
     * @param parentId
     *            父分类ID {@link Long}
     * @return 查询到的分类列表 {@link List}
     */
    public List<GoodsCate> querySonCatByParentId(Long parentId) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsCateMapper.querySonCatByParentId",
                parentId);
    }

    /**
     * 查询所有商品三级分类
     *
     * @return List
     */
    @Override
    public List<GoodsCate> queryAllGoodThirdCate() {
        return this
                .selectList("com.ningpai.goods.dao.GoodsCateMapper.queryAllGoodThirdCate");
    }

    /**
     * 根据参数查询商品分类
     *
     * @param paramMap
     *            查询参数
     * @return 返回商品分类list
     */
    @Override
    public List<GoodsCate> querySonCatByParm(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsCateMapper.querySonCatByParm",
                paramMap);
    }

    /**
     * 根据参数查询商品分类
     *
     * @param paramMap
     *            查询参数
     * @return 返回商品分类list
     */
    @Override
    public List<GoodsCateVo> querySonCatVoByParm(Map<String, Object> paramMap) {
        return this.selectList(
                "com.ningpai.goods.dao.GoodsCateMapper.querySonCatVoByParm",
                paramMap);
    }
}
