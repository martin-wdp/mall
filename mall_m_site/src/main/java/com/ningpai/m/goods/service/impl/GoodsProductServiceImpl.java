/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.service.impl;

import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.GoodsRelatedGoodsMapper;
import com.ningpai.goods.dao.GoodsReleExpandParamMapper;
import com.ningpai.goods.dao.GoodsReleParamMapper;
import com.ningpai.goods.dao.GoodsReleTagMapper;
import com.ningpai.goods.pub.GoodsPub;
import com.ningpai.goods.service.GoodsBrandService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.vo.GoodsRelatedGoodsVo;
import com.ningpai.m.goods.bean.GoodsDetailBean;
import com.ningpai.m.goods.dao.GoodsProductMapper;
import com.ningpai.m.goods.service.GoodsCateService;
import com.ningpai.m.goods.service.GoodsProductService;
import com.ningpai.m.goods.vo.GoodsCateVo;
import com.ningpai.m.goods.vo.GoodsProductVo;
import com.ningpai.m.goods.vo.ListFinalBuyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 货品信息Service实现
 *
 * @author NINGPAI-YuanKangKang
 * @version 1.0
 * @since 2014年1月20日 上午11:02:38
 */
@Service("HsiteGoodsProductService")
public class GoodsProductServiceImpl implements GoodsProductService {

    private static final String CATID = "catId";
    private static final String ROWCOUNT = "rowCount";

    private GoodsProductMapper goodsProductMapper;
    private GoodsCateService cateService;
    private GoodsReleTagMapper goodsReleTagMapper;
    private GoodsBrandService goodsBrandService;
    private GoodsReleExpandParamMapper expandParamMapper;
    private GoodsReleParamMapper goodsReleParamMapper;
    private GoodsRelatedGoodsMapper goodsRelatedGoodsMapper;
    private ProductWareService productWareService;
    private GoodsPub goodsPub;

    /**
     * 根据商品ID查询货品列表
     *
     * @param goodsId
     *            商品ID
     * @return 货品列表
     */
    public List<Object> queryAllProductListByGoodsId(Long goodsId) {
        return this.goodsProductMapper.queryProductByGoodsId(goodsId);
    }

    /**
     * 根据货品ID查询货品信息
     *
     * @param productId
     *            货品信息ID
     * @return 查询到的货品信息实体
     */
    public GoodsProductVo queryProductByProductId(Long productId) {
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
     */
    public List<GoodsProduct> queryTopSalesByCatIds(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByCatIds(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询最近一月该分类下的热销商品
     *
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询行数 {@link Integer}
     * @return 查询到的商品集合 {@link List}
     */
    public List<GoodsProduct> queryHotSalesTopSix(Long catId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryHotSalesByCatId(map);
        } finally {
            map = null;
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
     */
    public List<GoodsProduct> queryTopNewByCatIds(Long catId, Integer rowCount) {
        GoodsCateVo cate = this.cateService.queryCateById(catId);
        Map<String, Object> map = new HashMap<String, Object>();
        List<Long> catIds = new ArrayList<Long>();
        try {
            cateService.calcAllSonCatIds(cate, catIds);
            map.put("catIds", catIds);
            map.put(ROWCOUNT, rowCount);
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
     * @return 查询到的组装商品详细页的Bean
     */
    public GoodsDetailBean queryDetailBeanByProductId(Long productId, Long distinctId) {
        // 商品详情
        GoodsDetailBean detailBean = new GoodsDetailBean();
        List<GoodsProduct> relaProduct = new ArrayList<GoodsProduct>();
        GoodsProductVo productVo;
        ProductWare productWare;
        try {

            // 获取货品的详细信息
            detailBean.setProductVo(this.goodsProductMapper.queryDetailByProductId(productId));
            // 判断商品是否已经下架和在手机端不显示
            if (detailBean.getProductVo() == null || "0".equals(detailBean.getProductVo().getShowMobile()) || "0".equals(detailBean.getProductVo().getGoodsInfoAdded())) {
                return null;
            }
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
                        relaProduct.add(this.goodsProductMapper.queryFirstProductByGoodsId(relaProducts.get(i).getReleatedGoods().get(0).getGoodsId()));
                    }
                    detailBean.setReleProductList(relaProduct);
                }
                // 查询组合集合
                detailBean.setGroupVos(this.goodsPub.getGoodsGroupService().queryGroupVoListWithOutProductId(detailBean.getProductVo().getGoodsInfoId()));
            }
            // 如果不是第三方商品就去查分仓库存
            if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                /* 根据选择的地区查询库存及价格信息 */
                    productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(productId, distinctId);
                    /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                    if (null != productWare) {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                        productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice());
                        productVo.setGoodsInfoStock(productWare.getWareStock());
                        detailBean.setProductVo(productVo);
                    } else {
                        productVo = detailBean.getProductVo();
                        productVo.setGoodsInfoStock(0L);
                        detailBean.setProductVo(productVo);
                    }
            }
            /* 查询所有的关联的服务支持 */
            /*
             * detailBean.setSuppList(this.goodsProductSuppService.
             * queryAllSuppVoByProId
             * (detailBean.getProductVo().getGoodsInfoId()));
             */
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
     * @return 是否保存成功
     */
    public int saveProductCommentAsk(int type, String comment, Long custId, Long productId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("type", type);
            map.put("comment", comment);
            map.put("custId", custId);
            map.put("productId", productId);
            return this.goodsProductMapper.saveAskComment(map);
        } finally {
            map = null;
        }
    }

    /**
     * 新增货品id
     *
     * @see
     */
    public int insertCompProductId(Long productId, HttpServletRequest request, HttpServletResponse response) {
        return 0;
    }

    /**
     * 删除货品id
     *
     * @see
     */
    public int delCompProductId(Long productId, HttpServletRequest request, HttpServletResponse response) {
        return 0;
    }

    /**
     * 无效方法
     *
     * @see
     */
    public List<GoodsDetailBean> execCompProduct(HttpServletRequest request) {
        return new ArrayList<GoodsDetailBean>();
    }

    /**
     * 根据分类ID查询最终购买的列表
     *
     * @param catId
     *            分类ID {@link Long}
     * @param rowCount
     *            查询的行数 {@link Long}
     * @return 查询到的辅助Bena的集合
     */
    public List<ListFinalBuyVo> browCatFinalBuyAndPrecent(Long catId, Long rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put(CATID, catId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.browCatFinalBuyAndPrecent(map);
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
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds) {
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

    /**
     * 进行对比,传递请求
     *
     * @param productIds
     *            需要对比的商品列表 {@link List}
     * @return 需要对比的货品详细信息的集合 {@link List}
     */
    public List<GoodsDetailBean> execCompProduct(List<Long> productIds, HttpServletRequest request) {
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
            /* 取出session中的 */
            // 如果不是第三方商品就去查分仓库存
            Long distinctId = Long.parseLong(request.getSession().getAttribute("distinctId").toString());
            GoodsDetailBean detailBean;
            ProductWare productWare;
            GoodsProductVo productVo;
            for (int i = 0; i < lists.size(); i++) {
                detailBean = lists.get(i);
                if ("0".equals(detailBean.getProductVo().getIsThird()) && null != distinctId && distinctId > 0) {
                    /* 根据选择的地区查询库存及价格信息 */
                        productWare = this.productWareService.queryProductWareByProductIdAndDistinctId(detailBean.getProductVo().getGoodsInfoId(), distinctId);
                        /* 如果查询到的关联信息不能为空,就替换bean的价格和库存为查询到的关联记录 */
                        if (null != productWare) {
                            productVo = detailBean.getProductVo();
                            productVo.setGoodsInfoPreferPrice(productWare.getWarePrice());
                            productVo.setGoodsInfoVipPrice(productWare.getWareVipPrice());
                            productVo.setGoodsInfoStock(productWare.getWareStock());
                            detailBean.setProductVo(productVo);
                        } else {
                            productVo = detailBean.getProductVo();
                            productVo.setGoodsInfoStock(0L);
                            detailBean.setProductVo(productVo);
                        }
                }
            }
            return lists;
        } finally {
            lists = null;
        }
    }

    /**
     * 根据货品ID和查询行数查询销售最高的几条记录
     *
     * @param productId
     *            货品ID
     * @param rowCount
     *            查询的行数
     * @return 查询到的货品集合
     */
    public List<GoodsProduct> queryTopSalesByProductId(Long productId, Integer rowCount) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("productId", productId);
            map.put(ROWCOUNT, rowCount);
            return this.goodsProductMapper.queryTopSalesInfoByProductId(map);
        } finally {
            map = null;
        }
    }

    /**
     * 新增兑换积分
     *
     * @param map
     * @return
     */
    @Override
    public int insertExchangeCusmomer(Map<String, Object> map) {
        return this.goodsProductMapper.insertExchangeCusmomer(map);
    }

    /**
     * 根据地区ID查询关联的仓库ID
     *
     * @param distinctId
     *            地区ID
     * @return 仓库ID
     */
    @Override
    public Long selectWareIdByDistinctId(Long distinctId) {
        WareHouse wareHouse = productWareService.findWare(distinctId);
        return wareHouse == null ? null : wareHouse.getWareId();
    }

    public GoodsCateService getCateService() {
        return cateService;
    }

    @Resource(name = "HsiteGoodsCateService")
    public void setCateService(GoodsCateService cateService) {
        this.cateService = cateService;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "HsiteGoodsProductMapper")
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
