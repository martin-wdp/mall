/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service;

import java.util.List;

import com.ningpai.m.goods.util.GoodsSiteSearchBean;
import com.ningpai.m.goods.vo.GoodsDetailVo;
import com.ningpai.m.goods.vo.GoodsListScreenVo;
import com.ningpai.m.goods.vo.GoodsProductVo;
import com.ningpai.m.goods.vo.GoodsTypeVo;
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
     * 查询商品列表
     * 
     * @param pb
     *            分页帮助类
     * @param searchBean
     *            查询帮助类
     * @return 查询到的商品列表
     */
    PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean);

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
     * @param distinctId
     *            地区ID
     * @return 封装了List的分页辅助
     */
    PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params, String distinctId);

    /**
     * 根据扩展参数和分类ID查询商品列表
     *
     * @param pb
     *            分页辅助
     * @param searchBean
     *            查询参数
     * @param catId
     *            分类ID
     * @param distinctId
     *            地区ID
     * @return 封装了List的分页辅助
     */
    PageBean searchGoodsByType(String type,PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params, String distinctId);

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

    /**
     * 根据传递的参数和类型Vo计算已经选择的条件
     * 
     * @param params
     *            已经选择的条件 {@link String}
     * @param goodsTypeVo
     *            类型Vo {@link GoodsTypeVo}
     * @return 计算好的选中值的集合 {@link List}
     */
    List<GoodsListScreenVo> calcScreenParam(String[] params, GoodsTypeVo goodsTypeVo);

    /**
     * 根据计算好的选中的参数和类型Vo计算vo中需要显示的信息
     * 
     * @param screenList
     *            计算好的选中的参数 {@link String}
     * @param typeVo
     *            类型Vo {@link GoodsTypeVo}
     * @return 计算好的typeVo {@link GoodsTypeVo}
     */
    GoodsTypeVo calcTypeVo(List<GoodsListScreenVo> screenList, GoodsTypeVo typeVo);

}
