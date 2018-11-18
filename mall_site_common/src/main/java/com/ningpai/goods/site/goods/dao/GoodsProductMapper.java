/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;

/**
 * 货品信息Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月20日 上午10:52:57
 * @version 1.0
 */
public interface GoodsProductMapper {
    /**
     * 根据商品ID查询所有的货品
     * 
     * @param goodsId 货品Id
     * @return 查询到的货品列表
     */
    List<Object> queryProductByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询货品信息
     *  productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品信息
     */
    GoodsProductVo queryPrductByProductId(Long productId);

    /**
     * 根据参数查询销量最高的货品
     * 
     * @param map
     *            参数 catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map);

    /**
     * 根据参数查询最新上架的货品
     * 
     * @param map
     *            catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopNewInfoByCatIds(Map<String, Object> map);

    /**
     * 根据货品ID查询货品详细信息
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品详细信息 {@link com.ningpai.goods.site.goods.vo.GoodsProductVo}
     */
    GoodsProductVo queryDetailByProductId(Long productId);

    /**
     * 根据商品ID查询生成的第一个货品
     * 
     * @param goodsId
     *            商品ID {@link java.lang.Long}
     * @return 查询到的货品信息 {@link com.ningpai.goods.bean.GoodsProduct}
     */
    GoodsProduct queryFirstProductByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询属于同一组合下的货品
     * 
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的货品集合 {@link com.ningpai.goods.bean.GoodsProduct}
     */
    List<GoodsProduct> queryGroupProductByProductId(Long productId);

    /**
     * 根据货品ID的数组查询货品集合
     * 
     * @param map
     *            货品Id数组
     * @return 查询到的货品集合
     */
    List<GoodsProductVo> queryProductsByProductIds(Map<String, Object> map);

    /**
     * 保存商品浏览记录
     * @param map
     *            参数
     * @return 插入的行数
     */
    int saveGoodsBrow(Map<String, Object> map);

    /**
     * 保存商品咨询信息
     * 
     * @param map
     *            参数
     * @return 保存是否成功
     */
    int saveAskComment(Map<String, Object> map);
}
