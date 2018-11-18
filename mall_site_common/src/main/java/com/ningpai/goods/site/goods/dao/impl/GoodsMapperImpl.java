/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.dao.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.site.goods.dao.GoodsMapper;
import com.ningpai.goods.site.goods.vo.GoodsDetailVo;
import com.ningpai.manager.base.BasicSqlSupport;

/****
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年4月8日 下午6:07:33
 * @version
 */
@Repository("GoodsSiteGoodsMapper")
public class GoodsMapperImpl extends BasicSqlSupport implements GoodsMapper {
    /***
     * 根据参数查询商品列表
     *
     * @param map
     *            封装了查询参数 {@link java.util.Map}
     * @return 查询到的商品列表
     * @see com.ningpai.goods.dao.GoodsMapper#searchGoods(java.util.Map)
     */
    public List<Object> searchGoods(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsMapper.searchGoods", map);
    }

    /***
     * 根据查询的参数查询行数
     *
     * @param map
     *            封装了查询的参数 {@link java.util.Map}
     * @return 查询到的行数
     * @see com.ningpai.goods.dao.GoodsMapper#searchTotalCount(java.util.Map)
     */
    public Integer searchTotalCount(Map<String, Object> map) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsMapper.searchTotalCount", map);
    }

    /***
     * 根据商品ID查询所有的货品库存总和
     *
     * @param goodsId
     *            商品ID
     * @return 查询到的库存值
     * @see com.ningpai.goods.dao.GoodsMapper#queryStockByGoodsId(java.lang.Long)
     */
    public Integer queryStockByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsMapper.queryStockByGoodsId", goodsId);
    }

    /***
     * 查询最新的三条记录
     *
     * @param map
     *            封装查询参数
     * @return 查询到的集合
     * @see com.ningpai.goods.dao.GoodsMapper#queryNewInfoTopThree(java.util.Map)
     */
    public List<Object> queryNewInfoTopThree(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsMapper.queryNewInfo", map);
    }

    /***
     *根据分类ID查询商品列表
     *
     * @param map
     *            封装查询参数
     * @return 查询到的商品列表
     * @see com.ningpai.goods.dao.GoodsMapper#queryGoodsListByCatId(java.util.Map)
     */
    public List<Object> queryGoodsListByCatId(Map<String, Object> map) {
        return this.selectList("com.ningpai.util.site.goods.dao.GoodsMapper.queryGoodsListByCatId", map);
    }

    /***
     * 根据分类ID和参数查询行数
     *
     * @return 查询到的行数
     * @see com.ningpai.goods.dao.GoodsMapper#searchTotalCountByCatId(java.util.Map)
     */
    public Integer searchTotalCountByCatId(Map<String, Object> map) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsMapper.searchTotalCountByParamAndCatId", map);
    }

    /***
     * 根据商品ID查询详细信息
     *
     * @param goodsId
     *            商品ID
     * @return 详细信息
     * @see com.ningpai.goods.dao.GoodsMapper#queryGoodsDetailVoByGoodsId(java.lang .Long)
     */
    public GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId) {
        return this.selectOne("com.ningpai.util.site.goods.dao.GoodsMapper.queryGoodsDetailVoByGoodsId", goodsId);
    }

    /***
     * 根据分类ID查询
     *
     * @param map
     *            参数
     * @return 查询到的列表
     * @see com.ningpai.goods.dao.GoodsMapper#queryPromGoodsByCatIds(java.util.Map)
     */
    public List<Goods> queryPromGoodsByCatIds(Map<String, Object> map) {
        return Collections.emptyList();
    }

}
