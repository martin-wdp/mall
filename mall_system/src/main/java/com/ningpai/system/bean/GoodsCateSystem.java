package com.ningpai.system.bean;

import java.util.Date;

/**
 * 商品分类实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午10:13:31
 * @version 1.0
 */
public class GoodsCateSystem {
    /* 
     * 分类ID
     */
    private Long catId;
    /*
     *  分类名称
     */
    private String catName;
    /*
     *  父级分类ID
     */
    private Long catParentId;
    /*
     *  商品类型ID
     */
    private Long typeId;
    /*
     *  类型排序
     */
    private Integer catSort;
    /* 
     * 层级
     */
    private Integer catGrade;
    /*
     *  Seo标题
     */
    private String catSeoTitle;
    /*
     *  seo关键字
     */
    private String catSeoKeyword;
    /*
     *  seo介绍
     */
    private String catSeoDesc;
    /* 
     * 是否显示
     */
    private String catIsShow;
    /* 
     * 删除标记
     */
    private String catDelflag;
    /*
     *  创建人名称
     */
    private String catCreateName;
    /* 
     * 创建时间
     */
    private Date catCreateTime;
    /* 
     * 修改人名称
     */
    private String catModifiedName;
    /* 
     * 修改时间
     */
    private Date catModifiedTime;
    /*
     *  删除人名称
     */
    private String catDelName;
    /* 
     * 删除时间
     */
    private Date catDelTime;

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
     * 时间
     * @return
     */
    public Date getCatCreateTime() {
        if (this.catCreateTime != null) {
            return new Date(this.catCreateTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCatCreateTime(Date catCreateTime) {
        if (catCreateTime != null) {
            Date tEmp = (Date) catCreateTime.clone();
            if (tEmp != null) {
                this.catCreateTime = tEmp;
            }
        }
    }

    public String getCatModifiedName() {
        return catModifiedName;
    }

    public void setCatModifiedName(String catModifiedName) {
        this.catModifiedName = catModifiedName;
    }
    /**
     * 时间
     * @return
     */
    public Date getCatModifiedTime() {
        if (this.catModifiedTime != null) {
            return new Date(this.catModifiedTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCatModifiedTime(Date catModifiedTime) {
        if (catModifiedTime != null) {
            Date tEmp = (Date) catModifiedTime.clone();
            if (tEmp != null) {
                this.catModifiedTime = tEmp;
            }
        }
    }

    public String getCatDelName() {
        return catDelName;
    }

    public void setCatDelName(String catDelName) {
        this.catDelName = catDelName;
    }
    /**
     * 时间
     * @return
     */
    public Date getCatDelTime() {
        if (this.catDelTime != null) {
            return new Date(this.catDelTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCatDelTime(Date catDelTime) {
        if (this.catDelTime != null) {
            Date tEmp = (Date) catDelTime.clone();
            if (tEmp != null) {
                this.catDelTime = tEmp;
            }
        }
    }

}
