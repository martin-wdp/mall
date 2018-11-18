/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.dao;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import java.util.List;
import java.util.Map;

/**
 * 第三方Other DAO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:31:24
 * @version 2.0
 */
public interface ThirdOtherMapper {

    /**
     * 根据第三方ID查询所有的签约的分类信息
     *
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryGrandCateForThirdnew(Map<String, Object> map);
    /**
     * 根据第三方ID查询所有签约的品牌
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的品牌集合 {@link com.ningpai.goods.bean.GoodsBrand}
     */
    List<GoodsBrand> queryGrandListByThirdId(Long thirdId);

    /**
     * 查询所有的商品标签For 第三方
     *
     * @return 查询到的标签列表 {@link com.ningpai.goods.bean.GoodsTag}
     */
    List<GoodsTag> queryAllGoodsTagForThirdId();

    /**
     * 根据第三方ID查询已经签约的商品分类
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryAllGoodsCateForThird(Long thirdId);

    /**
     * 根据第三方ID查询所有的签约的分类信息
     *
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    List<GoodsCate> queryAllGoodsCateForThirdtwo(Map<String, Object> map);

    /**
     * 根据第三方分类ID查询第三方签约的分类信息
     *
     * @return 查询到的分类列表 {@link com.ningpai.goods.bean.GoodsCate}
     */
    GoodsCate queryGoodsCateForThirdById(Map<String, Object> map);

    /**
     * 根据参数查询相关商品的数量
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的行数 {@link Integer}
     */
    int queryAboutGoodsCountByCatId(Map<String, Object> map);

    /**
     * 根据参数查询相关商品
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的相关商品的集合 {@link java.util.List}
     */
    List<Object> queryAboutGoodsListByCatId(Map<String, Object> map);

    /**
     * 根据参数查询商品组合
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的商品组合的集合 {@link java.util.List}
     * @author NINGPAI-LIH
     */
    List<Object> queryThirdGroupByParam(Map<String, Object> map);

    /**
     * 根据参数查询商品组合总行数
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return 查询到的商品组合的总行数
     * @author NINGPAI-LIH
     */
    int queryThirdTotalAcount(Map<String, Object> map);

    /**
     * 插入签约的品牌信息
     *
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 插入的行数 {@link Integer}
     */
    int insertThirdGrantbrand(Map<String, Object> map);

    /**
     * 插入签约的分类信息
     *
     * @param map
     *            封装参数 {@link java.util.Map}
     * @return 插入的行数 {@link Integer}
     */
    int insertThirdGrantCat(Map<String, Object> map);
}
