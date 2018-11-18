/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.m.goods.bean.GoodsCate;
import com.ningpai.m.goods.dao.GoodsCateMapper;
import com.ningpai.m.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.m.goods.vo.GoodsCateVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 商品分类DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午5:49:41
 * @version 1.0
 */
@Repository("HsiteGoodsCateMapper")
public class GoodsCateMapperImpl extends BasicSqlSupport implements GoodsCateMapper {
    /**
     * 查询所有的商品分类
     *
     * @return 分类列表 {@link java.util.List} {@link com.ningpai.goods.vo.GoodsCateVo}
     */
    public List<GoodsCateVo> queryAllCate() {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsCateMapper.queryAllCate");
    }

    /**
     * 查询最上级分类
     *
     * @return 查询到的分类 {@link com.ningpai.goods.vo.GoodsCateVo}
     */
    public GoodsCateVo queryTopParent() {
        return (GoodsCateVo) this.selectList("com.ningpaimsite.goods.dao.GoodsCateMapper.queryTopParent");
    }

    /**
     * 根据分类ID查询分类Vo
     *
     * @param catId
     *            分类ID
     * @return 查询到的Vo实体
     */
    public GoodsCateVo queryCateVoByCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryCateVoByCatId", catId);
    }

    /**
     * 根据指定分类ID查询下一级的分类ID
     *
     * @param catId
     *            分类ID
     * @return 查询到的集合
     */
    public List<GoodsCate> querySonCateByCatId(Long catId) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsCateMapper.querySonCateByCatId", catId);
    }

    /**
     * 根据主键查询
     *
     * @param catId
     *            主键ID
     * @return 查询到的分类信息
     */
    public GoodsCate queryCateByPrimaryKey(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.selectByPrimaryKey", catId);
    }

    /**
     * 根据分类ID查询当前分类和父分类
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryCateAndParCateByCatId", catId);
    }

    /**
     * 根据分类ID查询面包屑辅助Bean
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的辅助Bean {@link GoodsBreadCrumbVo}
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryBreadCrubByCatId", catId);
    }

    /**
     * 根据第一季分类ID查询第一个第三级子分类
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的分类ID {@link Long}
     */
    public Long queryFirstCatIdByFirstCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryFirstCatIdByFirstCatId", catId);
    }

    /**
     * 根据第二级分类查询父分类ID
     *
     * @param catId
     *            {@link Long}
     * @return 查询到的分类ID {@link Long}
     */
    public Long queryParentIdBySecondCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryParentIdBySecondCatId", catId);
    }

    /**
     * 根据第二级分类查询第一个第三级子分类
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的子分类ID {@link Long}
     */
    public Long queryFirstSonCatIdBySecondCatId(Long catId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsCateMapper.queryFirstSonCatIdBySecondCatId", catId);
    }

    /**
     * 根据list查询所有商品分类
     *
     * @param list
     * @return List
     */
    public List<Object> selectProductCateList(List<Long> list) {
        return this.selectList(
                "com.ningpaimsite.goods.dao.GoodsCateMapper.selectProductCateList",
                list);
    }
}
