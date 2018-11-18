/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.site.goods.dao.GoodsProductMapper;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 货品DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午6:21:16
 * @version 1.0
 */
@Repository("GoodsSiteGoodsProductMapper")
public class GoodsProductMapperImpl extends BasicSqlSupport implements GoodsProductMapper {
    /**
     * 根据商品ID查询所有的货品
     *
     * @param goodsId 货品Id
     * @return 查询到的货品列表
     * @see com.ningpai.goods.dao.GoodsProductMapper#queryProductByGoodsId(java.lang .Long)
     */
    public List<Object> queryProductByGoodsId(Long goodsId) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryProductByGoodsId", goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     *  productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品信息
     * @see com.ningpai.goods.dao.GoodsProductMapper#queryPrductByProductId(java. lang.Long)
     */
    public GoodsProductVo queryPrductByProductId(Long productId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryPrductByProductId", productId);
    }

    /**
     * 根据参数查询销量最高的货品
     *
     * @param map
     *            参数 catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryTopSalesInfoByCatIds (java.util.Map)
     */
    public List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryTopSalesInfoByCatIds", map);
    }

    /**
     * 根据参数查询最新上架的货品
     *
     * @param map
     *            catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryTopNewInfoByCatIds (java.util.Map)
     */
    public List<GoodsProduct> queryTopNewInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryTopNewInfoByCatIds", map);
    }

    /**
     * 根据货品ID查询货品详细信息
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品详细信息 {@link com.ningpai.goods.site.goods.vo.GoodsProductVo}
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryDetailByProductId( java.lang.Long)
     */
    public GoodsProductVo queryDetailByProductId(Long productId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryDetailByProductId", productId);
    }

    /**
     * 根据商品ID查询生成的第一个货品
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的货品信息 {@link com.ningpai.goods.bean.GoodsProduct}
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryFirstProductByGoodsId (java.lang.Long)
     */
    public GoodsProduct queryFirstProductByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryFirstProductByGoodsId", goodsId);
    }

    /**
     * 根据货品ID查询属于同一组合下的货品
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品集合 {@link com.ningpai.goods.bean.GoodsProduct}
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryGroupProductByProductId (java.lang.Long)
     */
    public List<GoodsProduct> queryGroupProductByProductId(Long productId) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryGroupProductByProductId", productId);
    }

    /**
     * 根据货品ID的数组查询货品集合
     *
     * @param map
     *            货品Id数组
     * @return 查询到的货品集合
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryProductsByProductIds (java.util.Map)
     */
    public List<GoodsProductVo> queryProductsByProductIds(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsProductMapper.queryProductsByProductIds", map);
    }

    /**
     * 保存商品浏览记录
     * @param map
     *            参数
     * @return 插入的行数
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#saveGoodsBrow(java.util .Map)
     */
    public int saveGoodsBrow(Map<String, Object> map) {
        return this.insert("com.ningpai.util.site.goods.dao.GoodsProductMapper.saveGoodsBrow", map);
    }

    /**
     * 保存商品咨询信息
     *
     * @param map
     *            参数
     * @return 保存是否成功
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#saveAskComment(java.util .Map)
     */
    public int saveAskComment(Map<String, Object> map) {
        return this.insert("com.ningpai.util.site.goods.dao.GoodsProductMapper.saveAskComment", map);
    }

}
