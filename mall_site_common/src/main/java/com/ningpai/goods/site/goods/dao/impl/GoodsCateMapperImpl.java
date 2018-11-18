/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.site.goods.dao.GoodsCateMapper;
import com.ningpai.goods.site.goods.vo.GoodsBreadCrumbVo;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;
import com.ningpai.manager.base.BasicSqlSupport;

/***
 * 商品分类DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午5:49:41
 * @version 1.0
 */
@Repository("GoodsSiteGoodsCateMapper")
public class GoodsCateMapperImpl extends BasicSqlSupport implements GoodsCateMapper {
    /**
     * 查询所有的商品分类
     *
     * @return 分类列表 {@link java.util.List} {@link com.ningpai.goods.vo.GoodsCateVo}
     * @see com.ningpai.goods.dao.GoodsCateMapper#queryAllCate()
     */
    public List<GoodsCateVo> queryAllCate() {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsCateMapper.queryAllCate");
    }

    /**
    * 查询最上级分类
     *
     * @return 查询到的分类 {@link com.ningpai.goods.vo.GoodsCateVo}
     * @see com.ningpai.goods.dao.GoodsCateMapper#queryTopParent()
     */
    public GoodsCateVo queryTopParent() {
        return (GoodsCateVo) this.selectList("com.ningpai.util.site.goods.dao.GoodsCateMapper.queryTopParent");
    }

    /**
      * 根据分类ID查询分类Vo
     *
     * @param catId
     *            分类ID
     * @return 查询到的Vo实体
     * @see com.ningpai.goods.dao.GoodsCateMapper#queryCateVoByCatId(java.lang.Long)
     */
    public GoodsCateVo queryCateVoByCatId(Long catId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsCateMapper.queryCateVoByCatId", catId);
    }

    /**
     * 根据指定分类ID查询下一级的分类ID
     *
     * @param catId
     *            分类ID
     * @return 查询到的集合
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#querySonCateByCatId(java.lang .Long)
     */
    public List<GoodsCate> querySonCateByCatId(Long catId) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsCateMapper.querySonCateByCatId", catId);
    }

    /**
      * 根据主键查询
     *
     * @param catId
     *            主键ID
     * @return 查询到的分类信息
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#queryCateByPrimaryKey(java .lang.Long)
     */
    public GoodsCate queryCateByPrimaryKey(Long catId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsCateMapper.selectByPrimaryKey", catId);
    }

    /**
     * 根据分类ID查询当前分类和父分类
     *
     * @param catId
     *            分类ID
     * @return 查询到的分类信息
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#queryCateAndParCateByCatId (java.lang.Long)
     */
    public GoodsCateVo queryCateAndParCateByCatId(Long catId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsCateMapper.queryCateAndParCateByCatId", catId);
    }

    /**
     * 根据分类ID查询面包屑辅助Bean
     *
     * @param catId
     *            分类ID {@link Long}
     * @return 查询到的辅助Bean {@link GoodsBreadCrumbVo}
     * @see com.ningpai.site.goods.dao.GoodsCateMapper#queryBreadCrubByCatId(java .lang.Long)
     */
    public GoodsBreadCrumbVo queryBreadCrubByCatId(Long catId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsCateMapper.queryBreadCrubByCatId", catId);
    }

}
