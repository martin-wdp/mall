package com.ningpai.api.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

/**
 * 商品品牌类
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月16日 下午7:28:54
 * @version 1.0
 */
public class OGoodsBrand {
    /**
     * 品牌ID
     */
    private Long brandId;
    /**
     * 品牌别名
     */
    private String brandNickname;
    /**
     * 品牌名称
     */
    @Length(min = 2, max = 16, message = "品牌名称 长度必须在 2字符 ~ 16字符之间.")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?!]+")
    private String brandName;
    /**
     * 品牌网址
     */
    @NotNull(message = "网站格式输入不正确！")
    private String brandUrl;
    /**
     * 品牌LOGO
     */
    private String brandLogo;
    /**
     * 品牌排序
     */
    @NotNull
    private Integer brandSort;
    /**
     * 推荐到首页
     */
    @NotNull
    private String promIndex;
    /**
     * SEO优化标题
     */
    private String brandSeoTitle;
    /**
     * SEO优化关键字
     */
    private String brandSeoKeyword;
    /**
     * SEO优化介绍
     */
    private String brandSeoDesc;
    /**
     * 删除标记 默认0 不删除
     */
    private String brandDelflag;
    /**
     * 品牌创建人名称
     */
    private String brandCreateName;
    /**
     * 品牌创建时间
     */
    private Date brandCreateTime;
    /**
     * 品牌修改人名称
     */
    private String brandModifiedName;
    /**
     * 品牌修改时间
     */
    private Date brandModifiedTime;
    /**
     * 品牌删除人名称
     */
    private String brandDelName;
    /**
     * 品牌删除时间
     */
    private Date brandDelTime;
    /**
     * 品牌详细介绍
     */
    private String brandDesc;

    /**
     * 申请品牌Id
     */
    private Long grandBrandId;

    /**
     * 申请店铺名称
     */
    private String storeName;

    /**
     * 品牌ID
     */
    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    /**
     * 品牌别名
     */
    public String getBrandNickname() {
        return brandNickname;
    }

    public void setBrandNickname(String brandNickname) {
        this.brandNickname = brandNickname.trim();
    }

    /**
     * 品牌名称
     */
    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName.trim();
    }

    /**
     * 品牌网址
     */
    public String getBrandUrl() {
        return brandUrl;
    }

    public void setBrandUrl(String brandUrl) {
        this.brandUrl = brandUrl.trim();
    }

    /**
     * 品牌LOGO
     */
    public String getBrandLogo() {
        return brandLogo;
    }

    public void setBrandLogo(String brandLogo) {
        this.brandLogo = brandLogo;
    }

    /**
     * 品牌排序
     */
    public Integer getBrandSort() {
        return brandSort;
    }

    public void setBrandSort(Integer brandSort) {
        this.brandSort = brandSort;
    }

    /**
     * SEO优化标题
     */
    public String getBrandSeoTitle() {
        return brandSeoTitle;
    }

    public void setBrandSeoTitle(String brandSeoTitle) {
        this.brandSeoTitle = brandSeoTitle.trim();
    }

    /**
     * SEO优化关键字
     */
    public String getBrandSeoKeyword() {
        return brandSeoKeyword;
    }

    public void setBrandSeoKeyword(String brandSeoKeyword) {
        this.brandSeoKeyword = brandSeoKeyword.trim();
    }

    /**
     * SEO优化介绍
     */
    public String getBrandSeoDesc() {
        return brandSeoDesc;
    }

    public void setBrandSeoDesc(String brandSeoDesc) {
        this.brandSeoDesc = brandSeoDesc.trim();
    }

    /**
     * 删除标记 默认0 不删除
     */
    public String getBrandDelflag() {
        return brandDelflag;
    }

    public void setBrandDelflag(String brandDelflag) {
        this.brandDelflag = brandDelflag;
    }

    /**
     * 品牌创建人名称
     */
    public String getBrandCreateName() {
        return brandCreateName;
    }

    public void setBrandCreateName(String brandCreateName) {
        this.brandCreateName = brandCreateName;
    }

    /**
     * 品牌删除时间
     */
    public Date getBrandCreateTime() {
        if (this.brandCreateTime != null) {
            return new Date(this.brandCreateTime.getTime());
        }
        return null;
    }

    /**
     * 品牌删除时间
     * @param brandCreateTime
     */
    public void setBrandCreateTime(Date brandCreateTime) {
        if (brandCreateTime != null) {
            Date tEmp = (Date) brandCreateTime.clone();
            if (tEmp != null) {
                this.brandCreateTime = tEmp;
            }
        }
    }

    /**
     * 品牌修改人名称
     */
    public String getBrandModifiedName() {
        return brandModifiedName;
    }

    public void setBrandModifiedName(String brandModifiedName) {
        this.brandModifiedName = brandModifiedName;
    }

    /**
     * 品牌修改时间
     */
    public Date getBrandModifiedTime() {
        if (this.brandModifiedTime != null) {
            return new Date(this.brandModifiedTime.getTime());
        }
        return null;
    }

    /**
     * 品牌修改时间
     */
    public void setBrandModifiedTime(Date brandModifiedTime) {
        if (brandModifiedTime != null) {
            Date tEmp = (Date) brandModifiedTime.clone();
            if (tEmp != null) {
                this.brandModifiedTime = tEmp;
            }
        }
    }

    /**
     * 品牌删除人名称
     */
    public String getBrandDelName() {
        return brandDelName;
    }

    public void setBrandDelName(String brandDelName) {
        this.brandDelName = brandDelName;
    }

    /**
     * 品牌删除时间
     */
    public Date getBrandDelTime() {
        if (this.brandDelTime != null) {
            return new Date(this.brandDelTime.getTime());
        }
        return null;
    }

    /**
     * 品牌删除时间
     */
    public void setBrandDelTime(Date brandDelTime) {
        if (brandDelTime != null) {
            Date tEmp = (Date) brandDelTime.clone();
            if (tEmp != null) {
                this.brandDelTime = tEmp;
            }
        }
    }

    /**
     * 品牌详细介绍
     */
    public String getBrandDesc() {
        return brandDesc;
    }

    public void setBrandDesc(String brandDesc) {
        this.brandDesc = brandDesc;
    }

    /**
     * 推荐到首页
     */
    public String getPromIndex() {
        return promIndex;
    }

    public void setPromIndex(String promIndex) {
        this.promIndex = promIndex;
    }


    /**
     * 申请品牌Id
     */
    public Long getGrandBrandId() {
        return grandBrandId;
    }

    public void setGrandBrandId(Long grandBrandId) {
        this.grandBrandId = grandBrandId;
    }

    /**
     * 申请店铺名称
     */
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }
}
