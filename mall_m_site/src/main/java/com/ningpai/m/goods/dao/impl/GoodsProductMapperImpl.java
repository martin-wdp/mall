/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao.impl;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.m.goods.dao.GoodsProductMapper;
import com.ningpai.m.goods.vo.GoodsProductVo;
import com.ningpai.m.goods.vo.ListFinalBuyVo;
import com.ningpai.manager.base.BasicSqlSupport;
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
     * 删除商品浏览记录 根据货品编号
     *
     * @see com.ningpai.site.goods.dao
     */
    @Override
    public int updateGoodsBrowStatus(Map<String, Object> map) {
        return this.update("com.ningpaimsite.goods.dao.GoodsProductMapper.updateGoodsBrowStatus", map);
    }
    /**
     * 根据商品ID查询所有的货品
     *
     * @param goodsId
     * @return 查询到的货品列表
     */
    public List<Object> queryProductByGoodsId(Long goodsId) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryProductByGoodsId", goodsId);
    }

    /**
     * 根据货品id查询货品详细信息
     * @param goodsInfoId
     * @return
     */    @Override
    public GoodsProduct queryByGoodsInfoDetail(Long goodsInfoId) {
        return this.selectOne("com.ningpai.goods.dao.GoodsProductMapper.queryByGoodsInfoDetail", goodsInfoId);
    }

    /**
     * 根据套装id查询所有商品
     * @param groupId
     * @return
     */
    @Override
    public List<GoodsProductVo> queryDetailByGroupId(Long groupId) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryDetailByGroupId", groupId);
    }
    /**
     * 根据货品ID查询货品信息
     *
     * @return 查询到的货品信息
     */
    public GoodsProductVo queryPrductByProductId(Long productId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsProductMapper.queryPrductByProductId", productId);
    }

    /**
     * 根据参数查询销量最高的货品
     *
     * @param map
     *            参数 catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    public List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryTopSalesInfoByCatIds", map);
    }

    /**
     * 根据分类ID查询最近一月的热销商品
     *
     * @param map
     *            参数 catId
     * @return 查询到的集合{@link List}
     */
    public List<GoodsProduct> queryHotSalesByCatId(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryHotSalesByCatId", map);
    }

    /**
     * 根据参数查询最新上架的货品
     *
     * @param map
     *            catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    public List<GoodsProduct> queryTopNewInfoByCatIds(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryTopNewInfoByCatIds", map);
    }

    /**
     * 根据货品ID查询货品详细信息
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品详细信息
     */
    public GoodsProductVo queryDetailByProductId(Long productId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsProductMapper.queryDetailByProductId", productId);
    }

    /**
     * 根据商品ID查询生成的第一个货品
     *
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的货品信息 {@link com.ningpai.goods.bean.GoodsProduct}
     */
    public GoodsProduct queryFirstProductByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsProductMapper.queryFirstProductByGoodsId", goodsId);
    }

    /**
     * 根据货品ID查询属于同一组合下的货品
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品集合 {@link com.ningpai.goods.bean.GoodsProduct}
     */
    public List<GoodsProduct> queryGroupProductByProductId(Long productId) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryGroupProductByProductId", productId);
    }

    /**
     * 根据货品ID的数组查询货品集合
     *
     * @param map
     *            货品Id数组
     * @return 查询到的货品集合
     */
    public List<GoodsProductVo> queryProductsByProductIds(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryProductsByProductIds", map);
    }

    /**
     * 保存商品浏览记录
     *
     * @return 插入的行数
     */
    public int saveGoodsBrow(Map<String, Object> map) {
        return this.insert("com.ningpaimsite.goods.dao.GoodsProductMapper.saveGoodsBrow", map);
    }

    /**
     * 保存商品咨询信息
     *
     * @param map
     *            参数
     * @return 保存是否成功
     */
    public int saveAskComment(Map<String, Object> map) {
        return this.insert("com.ningpaimsite.goods.dao.GoodsProductMapper.saveAskComment", map);
    }

    /**
     * 根据分类ID查询最终购买的商品以及百分比
     *
     * @param map
     *            参数
     * @return 查询到的辅助Bean的集合
     */
    public List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.browCatFinalBuyAndPrecent", map);
    }

    /**
     * 根据货品ID查询最近一个月销量最高的几件货品
     *
     * @param map
     *            参数
     * @return 查询到的货品集合
     */
    public List<GoodsProduct> queryTopSalesInfoByProductId(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsProductMapper.queryTopSalesInfoByProductId", map);
    }
    /**
     * 新增兑换积分
     * @param map
     * @return
     */
    @Override
    public int insertExchangeCusmomer(Map<String, Object> map) {
        return this.insert("com.ningpaimsite.goods.dao.GoodsProductMapper.insertExchangeCusmomer", map);
    }

}
