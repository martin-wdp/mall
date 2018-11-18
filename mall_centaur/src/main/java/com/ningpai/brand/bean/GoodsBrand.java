package com.ningpai.brand.bean;

import java.util.Date;

/***
 * 品牌信息
 * 
 * @author ggn
 *
 */
public class GoodsBrand {
    /*
     * 品牌ID
     */
    private Long brandId;
    /*
     * 品牌昵称
     */
    private String brandNickname;
    /*
     * 品牌名字
     */
    private String brandName;
    /*
     * 品牌地址
     */
    private String brandUrl;
    /*
     * 品牌LOGO
     */
    private String brandLogo;
    /*
     * 推荐到首页
     */
    private String brandPromIndex;
    /*
     * 品牌排序
     */
    private Integer brandSort;
    /*
     * SEO优化标题
     */
    private String brandSeoTitle;
    /*
     * SEO优化关键字
     */
    private String brandSeoKeyword;
    /*
     * SEO优化介绍
     */
    private String brandSeoDesc;
    /*
     * SEO优化介绍
     */
    private String brandDelflag;
    /*
     * 品牌创建人名称
     */
    private String brandCreateName;
    /*
     * 品牌创建时间
     */
    private Date brandCreateTime;
    /*
     * 品牌修改人名称
     */
    private String brandModifiedName;
    /*
     * 品牌修改时间
     */
    private Date brandModifiedTime;
    /*
     * 品牌删除人名称
     */
    private String brandDelName;
    /*
     * 品牌删除时间
     */
    private Date brandDelTime;
    /*
     * 品牌详细介绍
     */
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
        return (Date) brandCreateTime.clone();
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
        return (Date) brandModifiedTime.clone();
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
