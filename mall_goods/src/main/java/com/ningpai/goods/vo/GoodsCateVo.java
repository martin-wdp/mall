/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品分类VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月17日 下午6:07:15
 * @version 1.0
 */
public class GoodsCateVo {
    /*
    *分类ID
     */
    private Long catId;

    /*
    *分类名称
     */
    private String catName;

    /*
     *分类图标
     * 2015.11.05 ly add
      */
    private String catImage;

    /*
    *父级分类ID
     */
    private Long catParentId;
    /*
    *分类下的商品数量
     */
    private Integer goodsCount;

    /*
    *商品类型ID
     */
    private Long typeId;

    /*
    *类型排序
     */
    private Integer catSort;

    /*
    *层级
     */
    private Integer catGrade;

    /*
     *Seo标题
      */
    private String catSeoTitle;

    /*
    *seo关键字
     */
    private String catSeoKeyword;

    /*
    *seo介绍
     */
    private String catSeoDesc;

    /*
    *删除标记
     */
    private String catDelflag;

    /*
    *类型名称
     */
    private String typeName;

    /*
    *子级的CateVo集合
     */
    private List<GoodsCateVo> cateVos;

    /*
    *模板商品分类配置从属信息
     */
    private Object sysCat;

    /*
    *类目扣率
     */
    private BigDecimal catRate;

    public BigDecimal getCatRate() {
        return catRate;
    }

    public void setCatRate(BigDecimal catRate) {
        this.catRate = catRate;
    }

    public Object getSysCat() {
        return sysCat;
    }

    public void setSysCat(Object sysCat) {
        this.sysCat = sysCat;
    }

    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }

    public Integer getCatGrade() {
        return catGrade;
    }

    public void setCatGrade(Integer catGrade) {
        this.catGrade = catGrade;
    }

    public String getCatSeoTitle() {
        return catSeoTitle;
    }

    public void setCatSeoTitle(String catSeoTitle) {
        this.catSeoTitle = catSeoTitle;
    }

    public String getCatSeoKeyword() {
        return catSeoKeyword;
    }

    public void setCatSeoKeyword(String catSeoKeyword) {
        this.catSeoKeyword = catSeoKeyword;
    }

    public String getCatSeoDesc() {
        return catSeoDesc;
    }

    public void setCatSeoDesc(String catSeoDesc) {
        this.catSeoDesc = catSeoDesc;
    }

    public String getCatDelflag() {
        return catDelflag;
    }

    public void setCatDelflag(String catDelflag) {
        this.catDelflag = catDelflag;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<GoodsCateVo> getCateVos() {
        return cateVos;
    }

    public void setCateVos(List<GoodsCateVo> cateVos) {
        this.cateVos = cateVos;
    }

    public Integer getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(Integer goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }
}
