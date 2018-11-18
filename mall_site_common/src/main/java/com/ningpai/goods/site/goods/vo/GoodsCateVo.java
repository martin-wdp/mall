/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.site.goods.vo;

import java.util.List;

import com.ningpai.goods.bean.GoodsCate;

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
     *父类分类ID
     */
    private Long catParentId;

    /*
     *商品分类实体类
     */
    private GoodsCate parentCat;

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
     *子级的CateVo集合
     */
    private List<GoodsCateVo> cateVos;

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

    public GoodsCate getParentCat() {
        return parentCat;
    }

    public void setParentCat(GoodsCate parentCat) {
        this.parentCat = parentCat;
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

    public List<GoodsCateVo> getCateVos() {
        return cateVos;
    }

    public void setCateVos(List<GoodsCateVo> cateVos) {
        this.cateVos = cateVos;
    }

    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

}
