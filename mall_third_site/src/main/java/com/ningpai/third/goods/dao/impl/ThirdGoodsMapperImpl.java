/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.third.goods.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.third.goods.dao.ThirdGoodsMapper;
import com.ningpai.third.goods.vo.SalesProductVo;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * 第三方商品DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年5月12日 下午3:04:16
 * @version 2.0
 */
@Repository("ThirdGoodsMapper")
public class ThirdGoodsMapperImpl extends BasicSqlSupport implements ThirdGoodsMapper {


    /**
     * 根据条件查询第三方商品集合
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    public List<Object> queryThirdGoodsList(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsList",
                        map);
    }

    /**
     * 根据条件获取商家商品的数量
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    public int queryThirdGoodsCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsCount",
                        map);
    }

    /**
     * 根据条件查询商家商品的单个对象
     * @param map
     * @return
     */
    public int queryThirdGoodsCountByFlag(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsCountByFlag",
                        map);
    }

    /**
     * 根据条件查询商家商品的对象集合
     * @param map
     * @return
     */
    public List<Object> queryThirdGoodsListByFlag(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsListByFlag",
                        map);
    }

    /**
     * 根据条件获取销售的商品集合
     * @param map
     *            参数Map
     * @return
     */
    public List<SalesProductVo> queryTopSalesByThirdId(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdProductSalesNum",
                        map);
    }

    /**
     * 根据条件获取商家商品集合
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public List<Object> queryThirdGoodsCopyList(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsCopyList",
                        map);
    }

    /**
     * 根据条件获取商家商品的数量
     * @param map
     *            封装查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public int queryThirdGoodsCopyCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.third.goods.dao.ThirdGoodsMapper.queryThirdGoodsCopyCount",
                        map);
    }

    /**
     *
     * @return
     */
    @Override
    public String bsetUrl() {
        return this
                .selectOne("com.ningpai.third.goods.dao.ThirdGoodsMapper.bsetUrl");
    }
}
