/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.service;

import com.ningpai.goods.bean.EsGoodsInfo;
import com.ningpai.goods.util.SearchPageBean;
import com.ningpai.site.goods.util.GoodsSiteSearchBean;
import com.ningpai.site.goods.vo.GoodsDetailVo;
import com.ningpai.site.goods.vo.GoodsListScreenVo;
import com.ningpai.site.goods.vo.GoodsProductVo;
import com.ningpai.site.goods.vo.GoodsTypeVo;
import com.ningpai.site.thirdseller.bean.ThirdGoodsSearchBean;
import com.ningpai.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 商品Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:43:30
 * @version 1.0
 */
public interface GoodsService {
    /**
     *
     *根据货品的Id 查询商品分类
     * @param goodsInfoId
     * @return
     */
    Long searchGoodsCatIdByGoodsInfoId(Long goodsInfoId);
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
    PageBean searchGood(PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params, String distinctId);
    /**
     * 根据扩展参数和分类ID查询商品列表
     *
     * @param pb
     *            分页辅助
     * @param searchBean
     *            查询参数
     *            分类ID
     *            地区ID
     * @return 封装了List的分页辅助
     */
    PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, String[] params);
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

    /**
     * 参数查询第三方店铺商品列表
     * @param pb 分页工具类
     * @param searchBean 查询参数
     * @param distinctId 地区ID
     * @return PageBean 包含查询结果的分页工具类
     */
    PageBean thirdGoodsList(PageBean pb,ThirdGoodsSearchBean searchBean,Long distinctId,Long thirdId);


    /**
     * 参数查询第三方店铺商品列表
     * @param pb 分页工具类
     * @param searchBean 查询参数
     * @param distinctId 地区ID
     * @return Map 包含查询结果的分页工具类
     */
    Map<String,Object> thirdGoodsListEs(SearchPageBean<EsGoodsInfo> pb,ThirdGoodsSearchBean searchBean,Long distinctId,Long thirdId,String isThird);
    
    /**
     * 根据品牌ID查询该品牌的货品
     * @param brandId
     * @return
     */
    PageBean selectProductsByBrandId(PageBean pb,Long brandId,Long distinctId);
    
    /**
     * 根据品牌ID查出分类ID集合
     * @param brandId
     * @return
     */
    public List<Long> selectCatIdByBrandId(Long brandId);
}
