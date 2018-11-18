/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service;

import java.util.List;

import com.ningpai.goods.site.goods.util.GoodsSiteSearchBean;
import com.ningpai.goods.site.goods.vo.GoodsDetailVo;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;
import com.ningpai.util.PageBean;

/**
 * 商品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:43:30
 * @version 1.0
 */
public interface GoodsService {

    /**
     * 查询最新的三条记录
     * 
     * @param catId
     *            分类Id
     * @return 查询到的集合
     */
    List<Object> queryTopThreeNew(Long catId);

    /**
     * 根据参数和分类ID查询商品列表
     * 
     * @param pb
     *            分页辅助
     * @param searchBean
     *            查询参数
     * @param catId
     *            分类ID
     * @return 封装了List的分页辅助
     */
    PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId);

    /**
     * 根据扩展参数和分类ID查询商品列表
     * 
     * @param pb
     *            分页辅助
     * @param searchBean
     *            查询参数
     * @param catId
     *            分类ID
     * @return 封装了List的分页辅助
     */
    PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params);

    /**
     * 根据商品ID查询商品的详细信息
     * 
     * @param goodsId
     *            商品ID
     * @return 查询到的详细信息Bean
     */
    GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId);

    /**
     * 根据商品详细信息返回默认的货品信息
     * 
     * @param goodsDetailVo
     *            商品详细信息
     * @return 默认的货品信息
     */
    GoodsProductVo returnDefaultGoodsProduct(GoodsDetailVo goodsDetailVo);
}
