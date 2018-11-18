/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.dao.impl;

import com.ningpai.goods.bean.GoodsBrand;
import com.ningpai.goods.bean.GoodsCate;
import com.ningpai.goods.bean.GoodsTag;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.goods.dao.ThirdOtherMapper;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * 第三方Other DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月9日 上午10:34:16
 * @version 2.0
 */
@Repository("ThirdOtherMapper")
public class ThirdOtherMapperImpl extends BasicSqlSupport implements ThirdOtherMapper {

    @Override
    public List<GoodsCate> queryGrandCateForThirdnew(Map<String, Object> map) {
        return this.selectList("com.ningpai.third.goods.dao.ThirdOtherMapper.queryGrandCateForThirdnew", map);
    }

    /**
     * 根据商家ID查询商品品牌
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public List<GoodsBrand> queryGrandListByThirdId(Long thirdId) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.selectGrandBrandByThirdId",
                        thirdId);
    }

    /**
     * 查询所有的商品标签
     * @return
     */
    public List<GoodsTag> queryAllGoodsTagForThirdId() {
        return this
                .selectList("com.ningpai.third.goods.dao.ThirdOtherMapper.queryAllGoodsTagForThirdId");
    }

    /**
     * 根据商家ID查询商品类型
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public List<GoodsCate> queryAllGoodsCateForThird(Long thirdId) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryAllGoodsCateForThird",
                        thirdId);
    }

    /**
     * 根据指定类型下面所有的商品数量
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return
     */
    public int queryAboutGoodsCountByCatId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryAboutGoodsCountByCatId",
                        map);
    }

    /**
     * 查询指定类型下面的商品集合
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return
     */
    public List<Object> queryAboutGoodsListByCatId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryAboutGoodsListByCatId",
                        map);
    }

    /**
     * 查询商品组合
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return
     */
    public List<Object> queryThirdGroupByParam(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryThirdGroupByParam",
                        map);
    }

    /**
     *
     * @param map
     *            封装参数的Map {@link java.util.Map}
     * @return
     */
    public int queryThirdTotalAcount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryThirdTotalAcount",
                        map);
    }

    /**
     * 根据参数查询商品组合总行数
     * @see
     * .util.Map)
     */
    public int insertThirdGrantbrand(Map<String, Object> map) {
        return this
                .insert("com.ningpai.third.goods.dao.ThirdOtherMapper.insertThirdGrantbrand",
                        map);
    }

    /**
     * 插入签约的分类信息
     * @see
     * .util.Map)
     */
    public int insertThirdGrantCat(Map<String, Object> map) {
        return this
                .insert("com.ningpai.third.goods.dao.ThirdOtherMapper.insertThirdGrantCat",
                        map);
    }

    /**
     * 根据第三方ID查询所有的签约的分类信息
     * @param map
     * @return
     */
    @Override
    public List<GoodsCate> queryAllGoodsCateForThirdtwo(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryAllGoodsCateForThirdtwo",
                        map);
    }

    /**
     * 根据第三方分类ID查询第三方签约的分类信息
     * @param map
     * @return
     */
    @Override
    public GoodsCate queryGoodsCateForThirdById(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdOtherMapper.queryGoodsCateForThirdById",
                        map);
    }

}
