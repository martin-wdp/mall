/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao.impl;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.goods.dao.GoodsProductMapper;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.goods.vo.ListFinalBuyVo;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 货品DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午6:21:16
 * @version 1.0
 */
@Repository("HsiteGoodsProductMapper")
public class GoodsProductMapperImpl extends BasicSqlSupport implements GoodsProductMapper {
    /**
     * 根据商品ID查询所有的货品
     * 
     * @see com.ningpai.goods.dao.GoodsProductMapper# .Long)
     */
    public List<Object> queryProductByGoodsId(Long goodsId) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryProductByGoodsId", goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     * 
     * @see com.ningpai.goods.dao.GoodsProductMapper
     */
    public GoodsProductVo queryPrductByProductId(Long productId) {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.queryPrductByProductId", productId);
    }

    /**
     * 根据参数查询销量最高的货品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryTopSalesInfoByCatIds
     *      (java.util.Map)
     */
    public List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryTopSalesInfoByCatIds", map);
    }

    /**
     * 根据分类ID查询最近一月的热销商品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper
     */
    public List<GoodsProduct> queryHotSalesByCatId(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryHotSalesByCatId", map);
    }

    /**
     * 根据分类查询最近一月销量最高的同价位的商品
     * 
     * @param map
     * @return
     */
    @Override
    public List<GoodsProduct> queryHotSalesByCatIdandPrice(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryHotSalesByCatIdandPrice", map);
    }

    /**
     * 根据分类查询最近一月销量最高的同价位的商品
     * 
     * @param map
     * @return
     */
    @Override
    public List<GoodsProduct> queryHotSalesByCatIdandBrand(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryHotSalesByCatIdandBrand", map);
    }

    /**
     * 根据参数查询最新上架的货品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryTopNewInfoByCatIds
     *      (java.util.Map)
     */
    public List<GoodsProduct> queryTopNewInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryTopNewInfoByCatIds", map);
    }

    /**
     * 根据货品ID查询货品详细信息
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryDetailByProductId(Long)
     */
    public GoodsProductVo queryDetailByProductId(Long productId) {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.queryDetailByProductId", productId);
    }

    /**
     * 根据商品ID查询生成的第一个货品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryFirstProductByGoodsId
     *      (java.lang.Long)
     */
    public GoodsProduct queryFirstProductByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.queryFirstProductByGoodsId", goodsId);
    }

    /**
     * 根据货品ID查询属于同一组合下的货品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryGroupProductByProductId
     *      (java.lang.Long)
     */
    public List<GoodsProduct> queryGroupProductByProductId(Long productId) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryGroupProductByProductId", productId);
    }

    /**
     * 根据货品ID的数组查询货品集合
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryProductsByProductIds
     *      (java.util.Map)
     */
    public List<GoodsProductVo> queryProductsByProductIds(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryProductsByProductIds", map);
    }

    /**
     * 保存商品浏览记录
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#
     */
    public int saveGoodsBrow(Map<String, Object> map) {
        return this.insert("com.ningpaihsite.goods.dao.GoodsProductMapper.saveGoodsBrow", map);
    }

    /**
     * 删除商品浏览记录 根据货品编号
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#updateGoodsBrowStatus(java.util.Map)
     */
    @Override
    public int updateGoodsBrowStatus(Map<String, Object> map) {
        return this.update("com.ningpaihsite.goods.dao.GoodsProductMapper.updateGoodsBrowStatus", map);
    }

    /**
     * 保存商品咨询信息
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper
     */
    public int saveAskComment(Map<String, Object> map) {
        return this.insert("com.ningpaihsite.goods.dao.GoodsProductMapper.saveAskComment", map);
    }

    /**
     * 根据分类ID查询最终购买的商品以及百分比
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#browCatFinalBuyAndPrecent
     *      (java.util.Map)
     */
    public List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.browCatFinalBuyAndPrecent", map);
    }

    /**
     * 根据货品ID查询最近一个月销量最高的几件货品
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryTopSalesInfoByProductId
     *      (java.util.Map)
     */
    public List<GoodsProduct> queryTopSalesInfoByProductId(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryTopSalesInfoByProductId", map);
    }

    /**
     * 团购总数量
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#selectGrouponCount()
     */
    @Override
    public int selectGrouponCount() {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.selectGrouponCount");
    }
    /**
     * 团购总数量
     * ADD BY LY
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#selectGrouponCount()
     */
    @Override
    public int selectGrouponCount(Long parentId) {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.selectGrouponCount");
    }

    /**
     * 团购列表
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper
     */
    @Override
    public List<GoodsProductVo> selectGrouponList(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.selectGrouponList", map);
    }

    /**
     * 根据货品ID 查询这个货品是否是上架状态以及未删除状态
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#queryProductByGoodsInfoId
     *      (java.lang.Long)
     */
    @Override
    public int queryProductByGoodsInfoId(Map<String, Object> map) {

        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.queryProductByGoodsInfoId", map);
    }

    /**
     * 抢购总数量
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#selectMarketingRushCount()
     */
    @Override
    public int selectMarketingRushCount() {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.selectMarketingRushCount");
    }

    /**
     * 抢购列表
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#selectMarketingRushList
     *      (java.util.Map)
     */
    @Override
    public List<GoodsProductVo> selectMarketingRushList(Map<String, Object> map) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.selectMarketingRushList", map);
    }

    /**
     * 抢购列表 不分页
     *  ADD BY LUYONG
     * @param
     * @return
     */
    @Override
    public List<GoodsProductVo> selectMarketingRushAllList(){
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.selectMarketingRushAllList");
    }
    /**
     * 根据组合ID查询货品详细信息
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper
     */
    @Override
    public List<GoodsProductVo> queryDetailByGroupId(Long groupId) {
        return this.selectList("com.ningpaihsite.goods.dao.GoodsProductMapper.queryDetailByGroupId", groupId);
    }

    /**
     * 新增积分兑换记录
     * 
     * @see com.ningpai.site.goods.dao.GoodsProductMapper#
     */
    @Override
    public int insertExchangeCusmomer(Map<String, Object> map) {
        return this.insert("com.ningpaihsite.goods.dao.GoodsProductMapper.insertExchangeCusmomer", map);
    }

    /**
     * 查询货品 简要信息
     */
    @Override
    public GoodsProductVo querySimpleProductByProductId(Long productId) {
        return this.selectOne("com.ningpaihsite.goods.dao.GoodsProductMapper.querySimpleProductByProductId", productId);
    }

}
