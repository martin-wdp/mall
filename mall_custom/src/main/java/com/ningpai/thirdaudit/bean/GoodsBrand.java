package com.ningpai.thirdaudit.bean;

import java.util.Date;

/**
 * 商品品牌
 * */
public class GoodsBrand {
    private Long brandId;

    private String brandNickname;

    private String brandName;

    private String brandUrl;

    private String brandLogo;

    private String brandPromIndex;

    private Integer brandSort;

    private String brandSeoTitle;

    private String brandSeoKeyword;

    private String brandSeoDesc;

    private String brandDelflag;

    private String brandCreateName;

    private Date brandCreateTime;

    private String brandModifiedName;

    private Date brandModifiedTime;

    private String brandDelName;

    private Date brandDelTime;

    private String brandDesc;

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandNickname() {
        return brandNickname;
    }

    public void setBrandNickname(String brandNickname) {
        this.brandNickname = brandNickname;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl;
    }

    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    public String getBrandPromIndex() {
        return brandPromIndex;
    }

    public void setBrandPromIndex(String brandPromIndex) {
        this.brandPromIndex = brandPromIndex;
    }

    public Integer getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Integer brandSort) {
        this.brandSort = brandSort;
    }

    public String getBrandSeoTitle() {
        return brandSeoTitle;
    }

    public void setBrandSeoTitle(String brandSeoTitle) {
        this.brandSeoTitle = brandSeoTitle;
    }

    public String getBrandSeoKeyword() {
        return brandSeoKeyword;
    }

    public void setBrandSeoKeyword(String brandSeoKeyword) {
        this.brandSeoKeyword = brandSeoKeyword;
    }

    public String getBrandSeoDesc() {
        return brandSeoDesc;
    }

    public void setBrandSeoDesc(String brandSeoDesc) {
        this.brandSeoDesc = brandSeoDesc;
    }

    public String getBrandDelflag() {
        return brandDelflag;
    }

    public void setBrandDelflag(String brandDelflag) {
        this.brandDelflag = brandDelflag;
    }

    public String getBrandCreateName() {
        return brandCreateName;
    }

    public void setBrandCreateName(String brandCreateName) {
        this.brandCreateName = brandCreateName;
    }

    /**
     * 获取创建时间
     * */
    public Date getBrandCreateTime() {
        if (this.brandCreateTime != null) {
            return new Date(this.brandCreateTime.getTime());
        }
        return null;
    }

    /**
     * 设置创建时间
     * */
    public void setBrandCreateTime(Date brandCreateTime) {
        this.brandCreateTime = brandCreateTime == null ? null : (Date) brandCreateTime.clone();
    }

    public String getBrandModifiedName() {
        return brandModifiedName;
    }

    public void setBrandModifiedName(String brandModifiedName) {
        this.brandModifiedName = brandModifiedName;
    }

    /**
     * 获取修改时间
     * */
    public Date getBrandModifiedTime() {
        if (this.brandModifiedTime != null) {
            return new Date(this.brandModifiedTime.getTime());
        }
        return null;
    }

    /**
     * 设置修改时间
     * */
    public void setBrandModifiedTime(Date brandModifiedTime) {
        this.brandModifiedTime = brandModifiedTime == null ? null : (Date) brandModifiedTime.clone();
    }

    public String getBrandDelName() {
        return brandDelName;
    }

    public void setBrandDelName(String brandDelName) {
        this.brandDelName = brandDelName;
    }

    /**
     * 获取删除时间
     * */
    public Date getBrandDelTime() {
        return (Date) brandDelTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setBrandDelTime(Date brandDelTime) {
        this.brandDelTime = brandDelTime == null ? null : (Date) brandDelTime.clone();
    }

    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }
}
