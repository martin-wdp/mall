package com.ningpai.category.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品分类实体
 * 
 * @author qiyuanyuan
 *
 */
public class GoodsCategory {
    /*
     * 分类ID
     */
    private Long catId;
    /*
     * 分类名称
     */
    private String catName;
    /*
     * 父亲ID
     */
    private Long catParentId;
    /*
     * 类型ID
     */
    private Long typeId;
    /*
     * 排序
     */
    private Integer catSort;
    /*
     * 层级
     */
    private Integer catGrade;
    /*
     * 标题
     */
    private String catSeoTitle;
    /*
     * 分类SEO关键字
     */
    private String catSeoKeyword;

    /*
     * 分类SEO描述
     */
    private String catSeoDesc;

    /*
     * 分类是否显示
     */
    private String catIsShow;

    /*
     * 分类是否删除
     */
    private String catDelflag;

    /*
     * 分类创建名称
     */
    private String catCreateName;

    /*
     * 分类创建时间
     */
    private Date catCreateTime;

    /*
     * 分类修改名字
     */
    private String catModifiedName;

    /*
     * 分类修改时间
     */
    private Date catModifiedTime;

    /*
     * 分类删除名字
     */
    private String catDelName;

    /*
     * 分类删除时间
     */
    private Date catDelTime;

    /*
     * 分类层级
     */
    private BigDecimal catRate;

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
}
