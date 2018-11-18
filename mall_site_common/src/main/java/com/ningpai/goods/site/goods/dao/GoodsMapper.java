/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.site.goods.vo.GoodsDetailVo;

/**
 * 商品Dao
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:24:12
 * @version 1.0
 */
public interface GoodsMapper {

    /**
     * 根据参数查询商品列表
     * 
     * @param map
     *            封装了查询参数 {@link java.util.Map}
     * @return 查询到的商品列表
     */
    List<Object> searchGoods(Map<String, Object> map);

    /**
     * 根据查询的参数查询行数
     * 
     * @param map
     *            封装了查询的参数 {@link java.util.Map}
     * @return 查询到的行数
     */
    Integer searchTotalCount(Map<String, Object> map);

    /**
     * 根据商品ID查询所有的货品库存总和
     * 
     * @param goodsId
     *            商品ID
     * @return 查询到的库存值
     */
    Integer queryStockByGoodsId(Long goodsId);

    /**
     * 查询最新的三条记录
     * 
     * @param map
     *            封装查询参数
     * @return 查询到的集合
     */
    List<Object> queryNewInfoTopThree(Map<String, Object> map);

    /**
     * 根据分类ID查询商品列表
     * 
     * @param map
     *            封装查询参数
     * @return 查询到的商品列表
     */
    List<Object> queryGoodsListByCatId(Map<String, Object> map);

    /**
     * 根据分类ID和参数查询行数
     * 
     * @return 查询到的行数
     */
    Integer searchTotalCountByCatId(Map<String, Object> map);

    /**
     * 根据商品ID查询详细信息
     * 
     * @param goodsId
     *            商品ID
     * @return 详细信息
     */
    GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId);

    /**
     * 根据分类ID查询
     * 
     * @param map
     *            参数
     * @return 查询到的列表
     */
    List<Goods> queryPromGoodsByCatIds(Map<String, Object> map);
}
