/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao.impl;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.goods.dao.GoodsCateMapper;
import com.ningpai.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.site.goods.vo.GoodsCateVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品分类DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午5:49:41
 * @version 1.0
 */
@Repository("HsiteGoodsCateMapper")
public class GoodsCateMapperImpl extends BasicSqlSupport implements
        GoodsCateMapper {
    /**
     * 查询三级分类的第一个
     * 
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#findCid(Long)
     */
    @Override
    public GoodsCate findCid(Long catId) {
        return this.selectOne(
                "com.ningpaihsite.goods.dao.GoodsCateMapper.findCid", catId);
    }

    /**查询分类
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#findCatGrade(Long)
     */
    @Override
    public int findCatGrade(Long catId) {
        return this.selectOne(
                "com.ningpaihsite.goods.dao.GoodsCateMapper.findCatGrade",
                catId);
    }

    /**查询所有的商品分类
     * 
     * 
     * @see com.ningpai.goods.dao.GoodsCateMapper#queryAllCate()
     */
    public List<GoodsCateVo> queryAllCate() {
        return this
                .selectList("com.ningpaihsite.goods.dao.GoodsCateMapper.queryAllCate");
    }

    /** 查询最上级分类
     * 
     * 
     * @see com.ningpai.goods.dao.GoodsCateMapper#queryTopParent()
     */
    public GoodsCateVo queryTopParent() {
        return (GoodsCateVo) this
                .selectList("com.ningpaihsite.goods.dao.GoodsCateMapper.queryTopParent");
    }

    /** 根据分类ID查询分类Vo
     * 
     * 
     * @see
     * com.ningpai.goods.dao.GoodsCateMapper#queryCateVoByCatId(Long)
     */
    public GoodsCateVo queryCateVoByCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryCateVoByCatId",
                        catId);
    }

    /** 根据指定分类ID查询下一级的分类ID
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#querySonCateByCatId(java.lang
     * .Long)
     */
    public List<GoodsCate> querySonCateByCatId(Long catId) {
        return this
                .selectList(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.querySonCateByCatId",
                        catId);
    }

    /** 根据主键查询
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryCateByPrimaryKey(java
     * .lang.Long)
     */
    public GoodsCate queryCateByPrimaryKey(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.selectByPrimaryKey",
                        catId);
    }

    /** 根据分类ID查询当前分类和父分类
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryCateAndParCateByCatId
     * (java.lang.Long)
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryCateAndParCateByCatId",
                        catId);
    }

    /** 根据分类ID查询面包屑辅助Bean
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryBreadCrubByCatId(java
     * .lang.Long)
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryBreadCrubByCatId",
                        catId);
    }

    /** 根据分类Name查询面包屑辅助Bean
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#queryBreadCrubByCatName(java.util.Map)
     */
    @Override
    public GoodsBreadCrumbVo queryBreadCrubByCatName(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryBreadCrubByCatName",
                        map);
    }

    /** 根据第一季分类ID查询第一个第三级子分类
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryFirstCatIdByFirstCatId
     * (java.lang.Long)
     */
    public Long queryFirstCatIdByFirstCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryFirstCatIdByFirstCatId",
                        catId);
    }

    /** 根据第二级分类查询父分类ID
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryParentIdBySecondCatId
     * (java.lang.Long)
     */
    public Long queryParentIdBySecondCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryParentIdBySecondCatId",
                        catId);
    }

    /** 根据第二级分类查询第一个第三级子分类
     * 
     * 
     * @see
     * com.ningpai.site.goods.dao.GoodsCateMapper#queryFirstSonCatIdBySecondCatId
     * (java.lang.Long)
     */
    public Long queryFirstSonCatIdBySecondCatId(Long catId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsCateMapper.queryFirstSonCatIdBySecondCatId",
                        catId);
    }

}
