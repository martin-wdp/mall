/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service.impl;

import com.ningpai.goods.site.goods.dao.GoodsMapper;
import com.ningpai.goods.site.goods.service.GoodsCateService;
import com.ningpai.goods.site.goods.service.GoodsService;
import com.ningpai.goods.site.goods.util.GoodsSiteSearchBean;
import com.ningpai.goods.site.goods.util.ValueUtil;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;
import com.ningpai.goods.site.goods.vo.GoodsDetailVo;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 商品Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月14日 上午10:44:52
 * @version 1.0
 */
@Service("GoodsSiteGoodsService")
public class GoodsServiceImpl implements GoodsService {
    /**
     * 商品Dao
     */
    private GoodsMapper goodsMapper;

    /**
     * 商品分类serivce接口
     */
    private GoodsCateService cateService;

    public GoodsCateService getCateService() {
        return cateService;
    }

    @Resource(name = "GoodsSiteGoodsCateService")
    public void setCateService(GoodsCateService cateService) {
        this.cateService = cateService;
    }

    public GoodsMapper getGoodsMapper() {
        return goodsMapper;
    }

    @Resource(name = "GoodsSiteGoodsMapper")
    public void setGoodsMapper(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 查询最新的三条记录
     *
     * @param catId
     *            分类Id
     * @return 查询到的集合
     */
    public List<Object> queryTopThreeNew(Long catId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 定义一个ArrayList集合
            List<Long> catIds = new ArrayList<Long>();
            // 计算出分类下所有子分类的ID
            this.cateService.calcAllSonCatIds(this.cateService.queryCateById(catId), catIds);
            map.put(ValueUtil.CATIDS, catIds);
            // 执行操作返回结果
            return this.goodsMapper.queryNewInfoTopThree(map);
        } finally {
            map = null;
        }
    }

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
    public PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 定义一个ArrayList集合
        List<Object> goodsList = new ArrayList<Object>();
        // 把查询参数置空
        searchBean.setTitle("");
        // 创建List集合
        List<Long> catIds = new ArrayList<Long>();
        GoodsCateVo cate = null;
        try {
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            // 获取分类的Vo信息
            cate = this.cateService.queryCateById(catId);
            // 获取出所有的子级分类的ID集合
            this.cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            selListToPageBean(pb, map, goodsList);
            return pb;
        } finally {
            map = null;
            goodsList = null;
            catIds = null;
            cate = null;
        }
    }

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
    public PageBean searchGoods(PageBean pb, GoodsSiteSearchBean searchBean, Long catId, String[] params) {
        // 定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        // 定义一个ArrayList集合
        List<Object> goodsList = new ArrayList<Object>();
        String brandId = "";
        List<String> paramsList = new ArrayList<String>();
        // 把查询参数置空
        searchBean.setTitle("");
        List<Long> catIds = new ArrayList<Long>();
        GoodsCateVo cate = null;
        try {
            map.put(ValueUtil.SEARCHBEAN, searchBean);
            // 获取分类的Vo信息
            cate = this.cateService.queryCateById(catId);
            // 获取出所有的子级分类的ID集合
            this.cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            // 判断传入的params数组是否长度大于0
            // 如果大于0就循环数组
            // 判断数组内的值的第一个字符是否等于b
            if (null != params && params.length > 0) {
                for (int i = 0; i < params.length; i++) {
                    if ("b".equals(params[i].substring(0, 1))) {
                        if (!"-1".equals(params[i].substring(1, params[i].length()))) {
                            brandId = params[i].substring(1, params[i].length()).toString();
                        }
                    } else if ("e".equals(params[i].substring(0, 1))) {
                        paramsList.add(params[i].subSequence(1, params[i].length()).toString());
                    }
                }
            }
            if (null != brandId && !"".equals(brandId)) {
                map.put("brandId", brandId);
            }
            if (!paramsList.isEmpty()) {
                map.put("params", paramsList);
                map.put("paramLengh", paramsList.size());
            }
            // 查询List放进PageBean中
            selListToPageBean(pb, map, goodsList);
            return pb;
        } finally {
            map = null;
            goodsList = null;
            brandId = null;
            paramsList = null;
            catIds = null;
            cate = null;
        }
    }

    /***
     * 查询List放进PageBean中
     * 
     * @param pb
     *            分页帮助Bean
     * @param map
     *            参数Map
     * @param goodsList
     *            商品List
     */
    private void selListToPageBean(PageBean pb, Map<String, Object> map, List<Object> goodsList) {
        // 根据分类ID和参数查询行数
        pb.setRows(this.goodsMapper.searchTotalCountByCatId(map) == null ? 0 : this.goodsMapper.searchTotalCountByCatId(map));
        map.put("startRowNum", pb.getStartRowNum());
        map.put("endRowNum", pb.getEndRowNum());
        if (null != goodsList && !goodsList.isEmpty()) {
            goodsList.get(0);
        }
        // 根据分类ID查询商品列表
        List<Object> goods = this.goodsMapper.queryGoodsListByCatId(map);
        pb.setList(goods);
    }

    /**
     * 根据商品ID查询商品的详细信息
     *
     * @param goodsId
     *            商品ID
     * @return 查询到的详细信息Bean
     */
    public GoodsDetailVo queryGoodsDetailVoByGoodsId(Long goodsId) {
        // 根据商品ID查询详细信息
        return this.goodsMapper.queryGoodsDetailVoByGoodsId(goodsId);
    }

    /**
     * 根据商品详细信息返回默认的货品信息
     *
     * @param goodsDetailVo
     *            商品详细信息
     * @return 默认的货品信息
     */
    public GoodsProductVo returnDefaultGoodsProduct(GoodsDetailVo goodsDetailVo) {
        // 判断商品分类中的货品分类是否为Null并且大于0
        // 如果大于0就直接返回
        // 否则就对货品分类进行操作赋值
        if (null != goodsDetailVo.getProductVos() && !goodsDetailVo.getProductVos().isEmpty()) {
            return goodsDetailVo.getProductVos().get(0);
        } else {
            // 创建新的对象
            GoodsProductVo goodsProductVo = new GoodsProductVo();
            try {
                // 赋值
                goodsProductVo.setGoodsInfoStock(0L);
                goodsProductVo.setGoodsInfoCostPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoMarketPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoPreferPrice(goodsDetailVo.getGoodsPrice());
                goodsProductVo.setGoodsInfoImgId(goodsDetailVo.getGoodsImg());
                return goodsProductVo;
            } finally {
                goodsProductVo = null;
            }
        }
    }

}
