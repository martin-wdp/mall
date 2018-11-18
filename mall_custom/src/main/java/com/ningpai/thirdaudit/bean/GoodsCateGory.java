package com.ningpai.thirdaudit.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品类型
 *
 * */
public class GoodsCateGory {
    private String parentName;

    private Long catId;

    private String catName;

    private Long catParentId;

    private Long typeId;

    private Integer catSort;

    private Integer catGrade;

    private String catSeoTitle;

    private String catSeoKeyword;

    private String catSeoDesc;

    private String catIsShow;

    private String catDelflag;

    private String catCreateName;

    private Date catCreateTime;

    private String catModifiedName;

    private Date catModifiedTime;

    private String catDelName;

    private Date catDelTime;

    private BigDecimal catRate;

    private String catModel;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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

    public String getCatIsShow() {
        return catIsShow;
    }

    public void setCatIsShow(String catIsShow) {
        this.catIsShow = catIsShow;
    }

    public String getCatDelflag() {
        return catDelflag;
    }

    public void setCatDelflag(String catDelflag) {
        this.catDelflag = catDelflag;
    }

    public String getCatCreateName() {
        return catCreateName;
    }

    public void setCatCreateName(String catCreateName) {
        this.catCreateName = catCreateName;
    }

    /**
     * 获取创建时间
     * */
    public Date getCatCreateTime() {
        return (Date) catCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCatCreateTime(Date catCreateTime) {
        this.catCreateTime = catCreateTime == null ? null : (Date) catCreateTime.clone();
    }

    public String getCatModifiedName() {
        return catModifiedName;
    }

    public void setCatModifiedName(String catModifiedName) {
        this.catModifiedName = catModifiedName;
    }
    /**
     * 获取修改时间
     * */
    public Date getCatModifiedTime() {
        return (Date) catModifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setCatModifiedTime(Date catModifiedTime) {
        this.catModifiedTime = catModifiedTime == null ? null : (Date) catModifiedTime.clone();
    }

    public String getCatDelName() {
        return catDelName;
    }

    public void setCatDelName(String catDelName) {
        this.catDelName = catDelName;
    }
    /**
     * 获取删除时间
     * */
    public Date getCatDelTime() {
        return (Date) catDelTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setCatDelTime(Date catDelTime) {
        this.catDelTime = catDelTime == null ? null : (Date) catDelTime.clone();
    }

    public BigDecimal getCatRate() {
        return catRate;
    }

    public void setCatRate(BigDecimal catRate) {
        this.catRate = catRate;
    }

    public String getCatModel() {
        return catModel;
    }

    public void setCatModel(String catModel) {
        this.catModel = catModel;
    }
}
