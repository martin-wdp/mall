/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.site.goods.dao.impl;

import com.ningpai.goods.bean.Goods;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.site.goods.dao.GoodsMapper;
import com.ningpai.site.goods.vo.GoodsDetailVo;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午6:07:33
 * @version
 */
@Repository("HsiteGoodsMapper")
public class GoodsMapperImpl extends BasicSqlSupport implements GoodsMapper {
    /**
     * 根据参数查询商品列表
     * @param map
     *            封装了查询参数 {@link java.util.Map}
     * @return
     */
    public List<Object> searchGoods(Map<String, Object> map) {
        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.searchGoods", map);
    }

    /**
     * 根据查询的参数查询行数
     * @param map
     *            封装了查询的参数 {@link java.util.Map}
     * @return
     */
    public Integer searchTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpaihsite.goods.dao.GoodsMapper.searchTotalCount", map);
    }

    /**
     * 根据商品ID查询所有的货品库存总和
     * @param goodsId
     *            商品ID
     * @return
     */
    public Integer queryStockByGoodsId(Long goodsId) {
        return this.selectOne(
                "com.ningpaihsite.goods.dao.GoodsMapper.queryStockByGoodsId",
                goodsId);
    }

    /**
     * 查询最新的三条记录
     * @param map
     * @return
     */
    public List<Object> queryNewInfoTopThree(Map<String, Object> map) {
        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.queryNewInfo", map);
    }

    /**
     * 根据分类ID查询商品列表
     * @param map
     *            封装查询参数
     * @return
     */
    public List<Object> queryGoodsListByCatId(Map<String, Object> map) {
        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.queryGoodsListByCatId",
                map);
    }

    /**
     * 根据分类ID查询商品列表
     * @param map
     *            封装查询参数
     * @return
     */
    @Override
    public List<Object> queryGoodsListByPid(Map<String, Object> map) {
        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.queryGoodsListByPid",
                map);
    }

    /**
     * 根据分类ID和参数查询行数
     * @param map
     * @return
     */
    public Integer searchTotalCountByCatId(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsMapper.searchTotalCountByParamAndCatId",
                        map);
    }

    /**
     * 根据商品ID查询详细信息
     * @param goodsId
     *            商品ID
     * @return
     */
    public GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsMapper.queryGoodsDetailVoByGoodsId",
                        goodsId);
    }

    /**
     * 根据分类ID查询
     * @param map
     *            参数
     * @return
     */
    public List<Goods> queryPromGoodsByCatIds(Map<String, Object> map) {
        return new ArrayList<Goods>();
    }

    /**
     * 根据参数查询商品总条数
     * @param map 封装了查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public int searchGoodsCount(Map<String, Object> map) {

        return this.selectOne(
                "com.ningpaihsite.goods.dao.GoodsMapper.searchGoodsCount", map);
    }

    /**
     * 根据参数查询第三方货品列表
     * @param map
     *            封装了查询参数 {@link java.util.Map}
     * @return
     */
    @Override
    public List<Object> serchThirdGoods(Map<String, Object> map) {

        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.searcThirdGoods", map);
    }

    /**
     * 根据货品的Id 查询商品分类
     * @param goodsInfoId
     * @return
     */
    @Override
    public Long searchGoodsCatIdByGoodsInfoId(Long goodsInfoId) {
        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsMapper.searchGoodsCatIdByGoodsInfoId",
                        goodsInfoId);
    }

    /**
     * 根据品牌ID查询该品牌的货品
     * @param map
     * @return
     */
    @Override
    public List<Object> selectProductsByBrandId(Map<String, Object> map) {

        return this
                .selectList(
                        "com.ningpaihsite.goods.dao.GoodsMapper.selectProductsByBrandId",
                        map);
    }

    /**
     * 根据分类查询货品数量
     * @param map
     * @return
     */
    @Override
    public int selectProductsCountByBrandId(Map<String, Object> map) {

        return this
                .selectOne(
                        "com.ningpaihsite.goods.dao.GoodsMapper.selectProductsCountByBrandId",
                        map);
    }

    /**
     * 根据品牌ID查出分类ID集合
     * @param brandId
     * @return
     */
    @Override
    public List<Long> selectCatIdByBrandId(Long brandId) {

        return this.selectList(
                "com.ningpaihsite.goods.dao.GoodsMapper.selectCatIdByBrandId",
                brandId);
    }

}
