/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.service.impl;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.dao.GoodsRelatedGoodsMapper;
import com.ningpai.goods.dao.GoodsReleExpandParamMapper;
import com.ningpai.goods.dao.GoodsReleParamMapper;
import com.ningpai.goods.dao.GoodsReleTagMapper;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.site.goods.bean.GoodsDetailBean;
import com.ningpai.goods.site.goods.dao.GoodsProductMapper;
import com.ningpai.goods.site.goods.service.GoodsCateService;
import com.ningpai.goods.site.goods.service.GoodsProductService;
import com.ningpai.goods.site.goods.vo.GoodsCateVo;
import com.ningpai.goods.site.goods.vo.GoodsProductVo;
import com.ningpai.goods.vo.GoodsRelatedGoodsVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 货品信息Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年1月20日 上午11:02:38
 * @version 1.0
 */
@Service("GoodsSiteGoodsProductService")
public class GoodsProductServiceImpl implements GoodsProductService {
    /**
     *货品信息Dao
     */
    private GoodsProductMapper goodsProductMapper;
    /**
     *商品分类serivce接口
     */
    private GoodsCateService cateService;
    /**
     *商品关联标签DAO
     */
    private GoodsReleTagMapper goodsReleTagMapper;
    /**
     *商品品牌service层接口
     */
    private GoodsBrandService goodsBrandService;
    /**
     *商品关联类型扩展属性
     */
    private GoodsReleExpandParamMapper expandParamMapper;
    /**
     *商品关联商品类型详细参数
     */
    private GoodsReleParamMapper goodsReleParamMapper;
    /**
     *商品关联商品DAO
     */
    private GoodsRelatedGoodsMapper goodsRelatedGoodsMapper;
    /**
     *货品分仓Service
     */
    private ProductWareService productWareService;

    /**
     *商品最终类
     */
    private GoodsPub goodsPub;

    /**
     * 根据商品ID查询货品列表
     *
     * @param goodsId
     *            商品ID
     * @return 货品列表
     * @see com.ningpai.goods.service.GoodsProductService#queryAllProductListByGoodsId (java.lang.Long)
     */
    public List<Object> queryAllProductListByGoodsId(Long goodsId) {
        //执行操作并返回结果
        return this.goodsProductMapper.queryProductByGoodsId(goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     *
     * @param productId
     *            货品信息ID
     * @return 查询到的货品信息实体
     * @see com.ningpai.goods.service.GoodsProductService#queryProductByProductId (java.lang.Long)
     */
    public GoodsProductVo queryProductByProductId(Long productId) {
        //执行操作并返回结果
        return this.goodsProductMapper.queryPrductByProductId(productId);
    }

    /**
     * 根据分类ID查询货品信息的集合
     *
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的集合
     * @see com.ningpai.site.goods.service.GoodsProductService#queryTopSalesByCatIds (java.lang.Long, java.lang.Integer)
     */
    public List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount) {
        //执行操作并赋值给商品分类
        GoodsCateVo cate = this.cateService.queryCateById(catId);
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        //定义一个ArrayList集合
        List<Long> catIds = new ArrayList<Long>();
        try {
            //执行方法
            cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            map.put("rowCount", rowCount);
            //根据参数查询销量最高的货品
            return this.goodsProductMapper.queryTopSalesInfoByCatIds(map);
        } finally {
            cate = null;
            map = null;
            catIds = null;
        }
    }

    /**
     * 根据分类ID查询最新上架的货品
     *
     * @param catId
     *            分类ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品
     * @see com.ningpai.site.goods.service.GoodsProductService#queryTopNewByCatIds (java.lang.Long, java.lang.Integer)
     */
    public List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount) {
        //执行操作并赋值给商品分类
        GoodsCateVo cate = this.cateService.queryCateById(catId);
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        //定义一个ArrayList集合
        List<Long> catIds = new ArrayList<Long>();
        try {
            //计算出分类下所有子分类的ID
            cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            map.put("rowCount", rowCount);
            //根据参数查询最新上架的货品
            return this.goodsProductMapper.queryTopNewInfoByCatIds(map);
        } finally {
            cate = null;
            map = null;
            catIds = null;
        }
    }

    /**
     * 根据货品ID查询
     *
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @param distinctId
     *            选择的地区ID
     * @return 查询到的组装商品详细页的Bean {@link com.ningpai.goods.site.goods.bean.GoodsDetailBean}
     * @see com.ningpai.site.goods.service.GoodsProductService#queryDetailBeanByProductId (java.lang.Long)
     */
    public GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId) {
        //new一个商品详情实体bean
        GoodsDetailBean detailBean = new GoodsDetailBean();
        //定义一个ArrayList集合
        List<GoodsProduct> relaProduct = new ArrayList<GoodsProduct>();
        //声明货品Vo
        GoodsProductVo productVo;
        //货品根据仓库分别维护价格和库存
        ProductWare productWare;
        try {
            //为商品详情的货品vo赋值
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
            if (null != detailBean.getProductVo()) {
                // 如果查询到的货品信息不为空就查询所属的分类信息
                detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                // 查询商品关联的标签
                detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询品牌
                detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                // 查询商品关联的扩展属性
                detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的详细参数
                detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                // 查询商品关联的货品
                List<GoodsRelatedGoodsVo> relaProducts = this.goodsRelatedGoodsMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId());
                // 如果关联的不为空
                if (null != relaProducts && !relaProducts.isEmpty()) {
                    for (int i = 0; i < relaProducts.size(); i++) {
                        //根据商品ID查询生成的第一个货品
                        relaProduct.add(this.goodsProductMapper.queryFirstProductByGoodsId(relaProducts.get(i).getReleatedGoods().get(0).getGoodsId()));
                    }
                    detailBean.setReleProductList(relaProduct);
                }
                // 查询组合集合
                detailBean.setGroupVos(this.goodsPub.getGoodsGroupService().queryGroupVoListByProductId(detailBean.getProductVo().getGoodsInfoId()));
            }
            // 如果不是第三方商品就去查分仓库存
            if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                /** 根据选择的地区查询库存及价格信息 */
                    productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
                    /** 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                    if (null != productWare) {
                        productVo = detailBean.getProductVo();
                        //赋值
                        productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                        productVo.setGoodsInfoStock(productWare.getWareStock());
                        detailBean.setProductVo(productVo);
                    } else {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoStock(0L);
                        detailBean.setProductVo(productVo);
                    }
            }
            return detailBean;
        } finally {
            productVo = null;
            productWare = null;
            detailBean = null;
        }
    }

    /**
     * 保存商品咨询
     *
     * @param type
     *            咨询类型
     * @param comment
     *            咨询内容
     * @param custId
     *            会员ID
     * @param productId
     *            货品ID {@link java.lang.Long }
     * @return 是否保存成功
     * @see com.ningpai.site.goods.service.GoodsProductService#saveProductCommentAsk (int, java.lang.String)
     */
    public int saveProductCommentAsk(int type, String comment, Long custId, Long productId) {
        //定义一个HashMap集合
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            //设置参数
            map.put("type", type);
            map.put("comment", comment);
            map.put("custId", custId);
            map.put("productId", productId);
            //执行操作返回结果
            return this.goodsProductMapper.saveAskComment(map);
        } finally {
            map = null;
        }
    }

    /**
     * 进行对比
     *
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     * @see com.ningpai.site.goods.service.GoodsProductService#execCompProduct(javax .servlet.http.HttpServletRequest)
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds) {
        //定义一个ArrayList集合
        List<GoodsDetailBean> lists = new ArrayList<GoodsDetailBean>();
        try {
            if (null != productIds && !productIds.isEmpty()) {
                for (int i = 0; i < productIds.size(); i++) {
                    GoodsDetailBean detailBean = new GoodsDetailBean();
                    try {
                        detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productIds.get(i)));
                        if (null != detailBean.getProductVo()) {
                            // 如果查询到的货品信息不为空就查询所属的分类信息
                            detailBean.setCateVo(this.cateService.queryCateAndParCateByCatId(detailBean.getProductVo().getGoods().getCatId()));
                            // 查询商品关联的标签
                            detailBean.setTags(goodsReleTagMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询品牌
                            detailBean.setBrand(goodsBrandService.queryBrandById(detailBean.getProductVo().getGoods().getBrandId()));
                            // 查询商品关联的扩展属性
                            detailBean.setExpandPrams(expandParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                            // 查询商品关联的详细参数
                            detailBean.setParam(goodsReleParamMapper.queryAllByGoodsId(detailBean.getProductVo().getGoodsId()));
                        }
                        lists.add(detailBean);
                    } finally {
                        detailBean = null;
                    }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    public GoodsCateService getCateService() {
        return cateService;
    }

    @Resource(name = "GoodsSiteGoodsCateService")
    public void setCateService(GoodsCateService cateService) {
        this.cateService = cateService;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "GoodsSiteGoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

    public GoodsReleTagMapper getGoodsReleTagMapper() {
        return goodsReleTagMapper;
    }

    @Resource(name = "GoodsReleTagMapper")
    public void setGoodsReleTagMapper(GoodsReleTagMapper goodsReleTagMapper) {
        this.goodsReleTagMapper = goodsReleTagMapper;
    }

    public GoodsBrandService getGoodsBrandService() {
        return goodsBrandService;
    }

    @Resource(name = "GoodsBrandService")
    public void setGoodsBrandService(GoodsBrandService goodsBrandService) {
        this.goodsBrandService = goodsBrandService;
    }

    public GoodsReleExpandParamMapper getExpandParamMapper() {
        return expandParamMapper;
    }

    @Resource(name = "GoodsReleExpandParamMapper")
    public void setExpandParamMapper(GoodsReleExpandParamMapper expandParamMapper) {
        this.expandParamMapper = expandParamMapper;
    }

    public GoodsReleParamMapper getGoodsReleParamMapper() {
        return goodsReleParamMapper;
    }

    @Resource(name = "")
    public void setGoodsReleParamMapper(GoodsReleParamMapper goodsReleParamMapper) {
        this.goodsReleParamMapper = goodsReleParamMapper;
    }

    public GoodsRelatedGoodsMapper getGoodsRelatedGoodsMapper() {
        return goodsRelatedGoodsMapper;
    }

    @Resource(name = "GoodsRelatedGoodsMapper")
    public void setGoodsRelatedGoodsMapper(GoodsRelatedGoodsMapper goodsRelatedGoodsMapper) {
        this.goodsRelatedGoodsMapper = goodsRelatedGoodsMapper;
    }

    public ProductWareService getProductWareService() {
        return productWareService;
    }

    @Resource(name = "ProductWareService")
    public void setProductWareService(ProductWareService productWareService) {
        this.productWareService = productWareService;
    }

    public GoodsPub getGoodsPub() {
        return goodsPub;
    }

    @Resource(name = "goodsPub")
    public void setGoodsPub(GoodsPub goodsPub) {
        this.goodsPub = goodsPub;
    }
}
