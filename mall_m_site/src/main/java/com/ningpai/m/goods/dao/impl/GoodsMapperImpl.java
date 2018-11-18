/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.Goods;
import com.ningpai.m.goods.dao.GoodsMapper;
import com.ningpai.m.goods.vo.GoodsDetailVo;
import com.ningpai.manager.base.BasicSqlSupport;

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
     *
     * @param map
     *            封装了查询参数 {@link java.util.Map}
     * @return 查询到的商品列表
     */
    public List<Object> searchGoods(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsMapper.searchGoods", map);
    }

    /**
     * 根据查询的参数查询行数
     *
     * @param map
     *            封装了查询的参数 {@link java.util.Map}
     * @return 查询到的行数
     */
    public Integer searchTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsMapper.searchTotalCount", map);
    }

    /**
     * 根据商品ID查询所有的货品库存总和
     *
     * @param goodsId
     *            商品ID
     * @return 查询到的库存值
     */
    public Integer queryStockByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsMapper.queryStockByGoodsId", goodsId);
    }

    /**
     * 查询最新的三条记录
     *
     * @param
     * @return 查询到的集合
     */
    public List<Object> queryNewInfoTopThree(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsMapper.queryNewInfo", map);
    }

    /**
     * 根据分类ID查询商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的商品列表
     */
    public List<Object> queryGoodsListByCatId(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsMapper.queryGoodsListByCatId", map);
    }

    /**
     * 根据分类ID查询商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的商品列表
     */
    public List<Object> queryGoodsListByCatIdAndType(Map<String, Object> map) {
        return this.selectList("com.ningpaimsite.goods.dao.GoodsMapper.queryGoodsListByCatIdAndType", map);
    }

    /**
     * 根据分类ID和参数查询行数
     *
     * @return 查询到的行数
     */
    public Integer searchTotalCountByCatId(Map<String, Object> map) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsMapper.searchTotalCountByParamAndCatId", map);
    }

    /**
     * 根据商品ID查询详细信息
     *
     * @param goodsId
     *            商品ID
     * @return 详细信息
     */
    public GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpaimsite.goods.dao.GoodsMapper.queryGoodsDetailVoByGoodsId", goodsId);
    }

    /**
     * 根据分类ID查询
     *
     * @param map
     *            参数
     * @return 查询到的列表
     */
    public List<Goods> queryPromGoodsByCatIds(Map<String, Object> map) {
        return new ArrayList<Goods>();
    }

}
