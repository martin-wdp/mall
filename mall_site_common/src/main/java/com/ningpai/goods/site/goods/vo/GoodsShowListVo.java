/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.vo;

import java.math.BigDecimal;
import java.util.List;

import com.ningpai.goods.bean.Goods;
import com.ningpai.goods.bean.GoodsImage;
import com.ningpai.goods.site.goods.util.ProductCommentUtilBean;

/**
 * 列表页显示的货品信息VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月19日 下午6:23:49
 * @version
 */
public class GoodsShowListVo {
    /*
     *主键ID
     */
    private Long goodsInfoId;
    /*
     *商品ID
     */
    private Long goodsId;
    /*
     *货号
     */
    private String goodsInfoItemNo;
    /*
     *货品名称
     */
    private String productName;
    /*
     *货品副标题
     */
    private String subtitle;
    /*
     *是否上架 默认1上架
     */
    private String goodsInfoAdded;
    /*
     *货品库存
     */
    private Long goodsInfoStock;
    /*
     *销售价
     */
    private BigDecimal goodsInfoPreferPrice;
    /*
     *成本价
     */
    private BigDecimal goodsInfoCostPrice;
    /*
     *市场价
     */
    private BigDecimal goodsInfoMarketPrice;
    /*
     *重量
     */
    private BigDecimal goodsInfoWeight;
    /*
     *货品初始图片
     */
    private String goodsInfoImgId;
    /*
     *删除标记
     */
    private String goodsInfoDelflag;
    /*
     *关联的规格值的集合
     */
    private List<GoodsProductReleSpecVo> specVo;
    /*
     *货品图片的集合
     */
    private List<GoodsImage> imageList;
    /*
     *货品所属的商品
     */
    private Goods goods;
    /*
     *评分帮助
     */
    private ProductCommentUtilBean commentUtilBean;

    public ProductCommentUtilBean getCommentUtilBean() {
        return commentUtilBean;
    }

    public void setCommentUtilBean(ProductCommentUtilBean commentUtilBean) {
        this.commentUtilBean = commentUtilBean;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public List<GoodsImage> getImageList() {
        return imageList;
    }

    public void setImageList(List<GoodsImage> imageList) {
        this.imageList = imageList;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    public String getGoodsInfoAdded() {
        return goodsInfoAdded;
    }

    public void setGoodsInfoAdded(String goodsInfoAdded) {
        this.goodsInfoAdded = goodsInfoAdded;
    }

    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    public BigDecimal getGoodsInfoPreferPrice() {
        return goodsInfoPreferPrice;
    }

    public void setGoodsInfoPreferPrice(BigDecimal goodsInfoPreferPrice) {
        this.goodsInfoPreferPrice = goodsInfoPreferPrice;
    }

    public BigDecimal getGoodsInfoCostPrice() {
        return goodsInfoCostPrice;
    }

    public void setGoodsInfoCostPrice(BigDecimal goodsInfoCostPrice) {
        this.goodsInfoCostPrice = goodsInfoCostPrice;
    }

    public BigDecimal getGoodsInfoMarketPrice() {
        return goodsInfoMarketPrice;
    }

    public void setGoodsInfoMarketPrice(BigDecimal goodsInfoMarketPrice) {
        this.goodsInfoMarketPrice = goodsInfoMarketPrice;
    }

    public BigDecimal getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    public void setGoodsInfoWeight(BigDecimal goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }

    public String getGoodsInfoImgId() {
        return goodsInfoImgId;
    }

    public void setGoodsInfoImgId(String goodsInfoImgId) {
        this.goodsInfoImgId = goodsInfoImgId;
    }

    public String getGoodsInfoDelflag() {
        return goodsInfoDelflag;
    }

    public void setGoodsInfoDelflag(String goodsInfoDelflag) {
        this.goodsInfoDelflag = goodsInfoDelflag;
    }

    public List<GoodsProductReleSpecVo> getSpecVo() {
        return specVo;
    }

    public void setSpecVo(List<GoodsProductReleSpecVo> specVo) {
        this.specVo = specVo;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}
