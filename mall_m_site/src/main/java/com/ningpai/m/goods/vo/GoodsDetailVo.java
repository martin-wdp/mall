/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.m.goods.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 修改商品是用到的商品Vo
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月25日 下午6:04:36
 * @version 1.0
 */
public class GoodsDetailVo {
    // 商品ID
    private Long goodsId;
    // 商品名称
    private String goodsName;
    // 商品副标题
    private String goodsSubtitle;
    // 商品关键字
    private String goodsKeywords;
    // 商品编号
    private String goodsNo;
    // 默认的商品图片
    private String goodsImg;
    // 是否上架0不上架 1上架
    private String goodsAdded;
    // 销售价格
    private BigDecimal goodsPrice;
    // 销售价格(企业会员价)
    private BigDecimal goodsVipPrice;
    // 商品分类
    private GoodsCateVo goodsCate;
    // 商品简单介绍
    private String goodsBrief;
    // 商品推荐0不推荐 1推荐
    private String goodsRecom;
    // 商品计价单位
    private String goodsDeno;
    // SEOtitle
    private String goodsSeoTitle;
    // SEO keyword
    private String goodsSeoKeyword;
    // SEO desc
    private String goodsSeoDesc;
    // 是否进行促销 0：不促销 1促销
    private String goodsProm;
    // 无库存是否也可销售
    private String goodsInfoInstocksell;
    // 是否可以使用优惠劵
    private String goodsUsecoupon;
    // 商品详细介绍
    private String goodsDetailDesc;
    // 手机版详细介绍
    private String mobileDesc;
    // 关联的规格的列表
    private List<GoodsSpecVo> specVoList;
    // 关联的类型扩展属性VO
    private List<GoodsReleExpandParamVo> expandParamVoList;
    // 关联的类型详细参数Vo
    private List<GoodsReleParamVo> paramVoList;
    // 下属的货品列表
    private List<GoodsProductVo> productVos;

    public List<GoodsProductVo> getProductVos() {
        return productVos;
    }

    public void setProductVos(List<GoodsProductVo> productVos) {
        this.productVos = productVos;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSubtitle() {
        return goodsSubtitle;
    }

    public void setGoodsSubtitle(String goodsSubtitle) {
        this.goodsSubtitle = goodsSubtitle;
    }

    public String getGoodsKeywords() {
        return goodsKeywords;
    }

    public void setGoodsKeywords(String goodsKeywords) {
        this.goodsKeywords = goodsKeywords;
    }

    public String getGoodsAdded() {
        return goodsAdded;
    }

    public void setGoodsAdded(String goodsAdded) {
        this.goodsAdded = goodsAdded;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public GoodsCateVo getGoodsCate() {
        return goodsCate;
    }

    public void setGoodsCate(GoodsCateVo goodsCate) {
        this.goodsCate = goodsCate;
    }

    public String getGoodsBrief() {
        return goodsBrief;
    }

    public void setGoodsBrief(String goodsBrief) {
        this.goodsBrief = goodsBrief;
    }

    public String getGoodsRecom() {
        return goodsRecom;
    }

    public void setGoodsRecom(String goodsRecom) {
        this.goodsRecom = goodsRecom;
    }

    public String getGoodsDeno() {
        return goodsDeno;
    }

    public void setGoodsDeno(String goodsDeno) {
        this.goodsDeno = goodsDeno;
    }

    public String getGoodsSeoTitle() {
        return goodsSeoTitle;
    }

    public void setGoodsSeoTitle(String goodsSeoTitle) {
        this.goodsSeoTitle = goodsSeoTitle;
    }

    public String getGoodsSeoKeyword() {
        return goodsSeoKeyword;
    }

    public void setGoodsSeoKeyword(String goodsSeoKeyword) {
        this.goodsSeoKeyword = goodsSeoKeyword;
    }

    public String getGoodsSeoDesc() {
        return goodsSeoDesc;
    }

    public void setGoodsSeoDesc(String goodsSeoDesc) {
        this.goodsSeoDesc = goodsSeoDesc;
    }

    public String getGoodsProm() {
        return goodsProm;
    }

    public void setGoodsProm(String goodsProm) {
        this.goodsProm = goodsProm;
    }

    public String getGoodsInfoInstocksell() {
        return goodsInfoInstocksell;
    }

    public void setGoodsInfoInstocksell(String goodsInfoInstocksell) {
        this.goodsInfoInstocksell = goodsInfoInstocksell;
    }

    public String getGoodsUsecoupon() {
        return goodsUsecoupon;
    }

    public void setGoodsUsecoupon(String goodsUsecoupon) {
        this.goodsUsecoupon = goodsUsecoupon;
    }

    public String getGoodsDetailDesc() {
        return goodsDetailDesc;
    }

    public void setGoodsDetailDesc(String goodsDetailDesc) {
        this.goodsDetailDesc = goodsDetailDesc;
    }

    public List<GoodsReleExpandParamVo> getExpandParamVoList() {
        return expandParamVoList;
    }

    public void setExpandParamVoList(List<GoodsReleExpandParamVo> expandParamVoList) {
        this.expandParamVoList = expandParamVoList;
    }

    public List<GoodsReleParamVo> getParamVoList() {
        return paramVoList;
    }

    public void setParamVoList(List<GoodsReleParamVo> paramVoList) {
        this.paramVoList = paramVoList;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public void setSpecVoList(List<GoodsSpecVo> specVoList) {
        this.specVoList = specVoList;
    }

    public List<GoodsSpecVo> getSpecVoList() {
        return specVoList;
    }

    public String getMobileDesc() {
        return mobileDesc;
    }

    public void setMobileDesc(String mobileDesc) {
        this.mobileDesc = mobileDesc;
    }

}
