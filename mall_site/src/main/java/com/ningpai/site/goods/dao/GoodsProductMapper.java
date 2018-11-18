/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.goods.vo.ListFinalBuyVo;

/**
 * 货品信息Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月20日 上午10:52:57
 * @version 1.0
 */
public interface GoodsProductMapper {
    /***
     * 新增积分兑换记录
     * @return int
     */
    int insertExchangeCusmomer(Map<String, Object> map);
    
    /**
     * 根据商品ID查询所有的货品
     * 
     * @param goodsId
     * @return 查询到的货品列表
     */
    List<Object> queryProductByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询货品信息
     * 
     * @return 查询到的货品信息
     */
    GoodsProductVo queryPrductByProductId(Long productId);
    
    /**
     * 根据货品ID查询货品简要信息（注意 只查询货品表 主表的查询 不做子查询）
     * @param productId
     * @return
     */
    GoodsProductVo querySimpleProductByProductId(Long productId);

    /**
     * 根据参数查询销量最高的货品
     * 
     * @param map
     *            参数 catIds 分类ID的集合 rowCount 查询的行数
     * @return 查询到的集合
     */
    List<GoodsProduct> queryTopSalesInfoByCatIds(Map<String, Object> map);

    /**
     * 根据分类ID查询最近一月的热销商品
     * 
     * @param map
     *            参数 catId
     * @return 查询到的集合{@link List}
     */
    List<GoodsProduct> queryHotSalesByCatId(Map<String, Object> map);

    /**
     *  根据分类查询最近一月销量最高的同价位的商品
     * @param map
     * @return
     */
    List<GoodsProduct> queryHotSalesByCatIdandPrice(Map<String, Object> map);


    /**
     * 根据分类查询最近一月销量最高的同价位的商品
     * @param map
     * @return
     */
    List<GoodsProduct> queryHotSalesByCatIdandBrand(Map<String, Object> map);

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
     *            货品ID {@link Long}
     * @return 查询到的货品详细信息
     */
    GoodsProductVo queryDetailByProductId(Long productId);
    
    /**
     * 根据组合ID查询货品详细信息
     * 
     * @param groupId
     *           组合ID {@link Long}
     * @return 查询到的货品详细信息
     */
    List<GoodsProductVo> queryDetailByGroupId(Long groupId);

    /**
     * 根据商品ID查询生成的第一个货品
     * 
     * @param goodsId
     *            商品ID {@link Long}
     * @return 查询到的货品信息 {@link com.ningpai.goods.bean.GoodsProduct}
     */
    GoodsProduct queryFirstProductByGoodsId(Long goodsId);

    /**
     * 根据货品ID查询属于同一组合下的货品
     * 
     * @param productId
     *            货品ID {@link Long}
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
     * 
     * @return 插入的行数
     */
    int saveGoodsBrow(Map<String, Object> map);
    /**
     * 删除商品浏览记录 根据货品编号
     *
     * @return 是否成功 0 未成功
     */
    int updateGoodsBrowStatus(Map<String, Object> map);


    /**
     * 保存商品咨询信息
     * 
     * @param map
     *            参数
     * @return 保存是否成功
     */
    int saveAskComment(Map<String, Object> map);

    /**
     * 根据分类ID查询最终购买的商品以及百分比
     * 
     * @param map
     *            参数
     * @return 查询到的辅助Bean的集合
     */
    List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Map<String, Object> map);

    /**
     * 根据货品ID查询最近一个月销量最高的几件货品
     * 
     * @param map
     *            参数
     * @return 查询到的货品集合
     */
    List<GoodsProduct> queryTopSalesInfoByProductId(Map<String, Object> map);

    /**
     * 团购总数量
     * 
     * @return 总数量
     * @author NINGPAI-LIH
     */
    int selectGrouponCount();
    /**
     * 团购总数量
     * ADD BY LY
     * @return 总数量
     * @author NINGPAI-LIH
     */
   int selectGrouponCount(Long parentId);

    /**
     * 团购列表
     * 
     * @param map
     * @return
     * @author NINGPAI-LIH
     */
    List<GoodsProductVo> selectGrouponList(Map<String, Object> map);
    
    /**
     *  根据货品ID 查询这个货品是否是上架状态以及未删除状态
     * @param map
     * @return
     */
    int queryProductByGoodsInfoId(Map<String, Object> map);
    
    /**
     * 抢购总数量
     * 
     * @return 总数量
     */
    int selectMarketingRushCount();

    /**
     * 抢购列表
     * 
     * @param map
     * @return
     */
    List<GoodsProductVo> selectMarketingRushList(Map<String, Object> map);
    /**
     * 抢购列表
     *  ADD BY LUYONG
     * @param
     * @return
     */
    List<GoodsProductVo> selectMarketingRushAllList();
}
