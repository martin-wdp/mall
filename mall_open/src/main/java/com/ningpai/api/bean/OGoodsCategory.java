package com.ningpai.api.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品分类
 *
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月16日 下午7:28:54
 * @version 1.0
 */
public class OGoodsCategory {
    /**
     * 分类id
     */
    private Long catId;
    /**
     * 分类名称
     */
    private String catName;
    /**
     * 父类ID
     */
    private Long catParentId;
    /**
     * 类型ID
     */
    private Long typeId;
    /**
     * 排序
     */
    private Integer catSort;
    /**
     * 层级
     */
    private Integer catGrade;
    /**
     * SEOtitile
     */
    private String catSeoTitle;
    /**
     * SEO关键字
     */
    private String catSeoKeyword;
    /**
     * SEO描述
     */
    private String catSeoDesc;
    /**
     * 是否显示
     */
    private String catIsShow;
    /**
     * 是否删除
     */
    private String catDelflag;
    /**
     * 创建人
     */
    private String catCreateName;
    /**
     * 创建时间
     */
    private Date catCreateTime;
    /**
     * 修改人
     */
    private String catModifiedName;
    /**
     * 修改时间
     */
    private Date catModifiedTime;
    /**
     * 删除人
     */
    private String catDelName;
    /**
     * 删除时间
     */
    private Date catDelTime;
    /**
     * 扣率
     */
    private BigDecimal catRate;

    /**
     * 分类id
     */
    public Long getCatId() {
        return catId;
    }

    public void setCatId(Long catId) {
        this.catId = catId;
    }

    /**
     * 分类名称
     */
    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * 父类ID
     */
    public Long getCatParentId() {
        return catParentId;
    }

    public void setCatParentId(Long catParentId) {
        this.catParentId = catParentId;
    }

    /**
     * 类型ID
     */
    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    /**
     * 排序
     */
    public Integer getCatSort() {
        return catSort;
    }

    public void setCatSort(Integer catSort) {
        this.catSort = catSort;
    }

    /**
     * 层级
     */
    public Integer getCatGrade() {
        return catGrade;
    }

    public void setCatGrade(Integer catGrade) {
        this.catGrade = catGrade;
    }

    /**
     * SEOtitile
     */
    public String getCatSeoTitle() {
        return catSeoTitle;
    }

    public void setCatSeoTitle(String catSeoTitle) {
        this.catSeoTitle = catSeoTitle;
    }

    /**
     * SEO关键字
     */
    public String getCatSeoKeyword() {
        return catSeoKeyword;
    }

    public void setCatSeoKeyword(String catSeoKeyword) {
        this.catSeoKeyword = catSeoKeyword;
    }

    /**
     * SEO描述
     */
    public String getCatSeoDesc() {
        return catSeoDesc;
    }

    public void setCatSeoDesc(String catSeoDesc) {
        this.catSeoDesc = catSeoDesc;
    }

    /**
     * 是否显示
     */
    public String getCatIsShow() {
        return catIsShow;
    }

    public void setCatIsShow(String catIsShow) {
        this.catIsShow = catIsShow;
    }

    /**
     * 是否删除
     */
    public String getCatDelflag() {
        return catDelflag;
    }

    public void setCatDelflag(String catDelflag) {
        this.catDelflag = catDelflag;
    }

    /**
     * 创建人
     */
    public String getCatCreateName() {
        return catCreateName;
    }

    public void setCatCreateName(String catCreateName) {
        this.catCreateName = catCreateName;
    }

    /**
     * 创建时间
     */
    public Date getCatCreateTime() {

        if(catCreateTime != null){
            return (Date) catCreateTime.clone();
        }
        return null;

    }

    public void setCatCreateTime(Date catCreateTime) {
        this.catCreateTime = catCreateTime == null ? null : (Date) catCreateTime.clone();
    }

    /**
     * 修改人
     */
    public String getCatModifiedName() {
        return catModifiedName;
    }

    public void setCatModifiedName(String catModifiedName) {
        this.catModifiedName = catModifiedName;
    }

    /**
     * 修改时间
     */
    public Date getCatModifiedTime() {
        if(catModifiedTime != null){
            return (Date) catModifiedTime.clone();
        }
        return null;
    }

    public void setCatModifiedTime(Date catModifiedTime) {
        this.catModifiedTime = catModifiedTime == null ? null : (Date) catModifiedTime.clone();
    }

    /**
     * 删除人
     */
    public String getCatDelName() {
        return catDelName;
    }

    public void setCatDelName(String catDelName) {
        this.catDelName = catDelName;
    }

    /**
     * 修改时间
     */
    public Date getCatDelTime() {
        if(catDelTime != null){
            return (Date) catDelTime.clone();
        }
        return null;
    }

    public void setCatDelTime(Date catDelTime) {
        this.catDelTime = catDelTime == null ? null : (Date) catDelTime.clone();
    }

    /**
     * 扣率
     */
    public BigDecimal getCatRate() {
        return catRate;
    }

    public void setCatRate(BigDecimal catRate) {
        this.catRate = catRate;
    }
}
