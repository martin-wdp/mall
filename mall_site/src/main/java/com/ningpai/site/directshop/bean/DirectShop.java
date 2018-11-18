package com.ningpai.site.directshop.bean;

import java.util.Date;

/**
 * 直营店实体
 * @author qiyuanyuan
 *
 */
public class DirectShop {

    /*
     *直营店ID 
     */
    private Long directShopId;
    
    /*
     * 直营店名称
     */
    private String directShopName;
    
    /*
     * 直营店地区
     */
    private String directShopAdd;
    
    /*
     * 直营店地区id
     */
    private String directShopAddId;
    
    /*
     * 详细地址
     */
    private String directShopAddDetail;
    
    /*
     *联系电话
     */
    private String directShopTel;
    
    /*
     * 是否启用 0：否  1：是
     */
    private String directShopStatus;

    /*
     * 创建时间
     */
    private Date directShopCreateDate;
    /*
     * 修改时间
     */
    private Date directShopModifyDate;
    
    /*
     * 是否删除 0：否  1：是
     */
    private String delFlag;
    
    /*
     * 店铺类型 0：商家 1：直营店
     */
    private String storeType;
    
    /*
     * 省id
     */
    private Long provinceId;
    
    /*
     * 市id
     */
    private Long cityId;
    
    /*
     * 区id
     */
    private Long countyId;

    public Long getDirectShopId() {
        return directShopId;
    }

    public void setDirectShopId(Long directShopId) {
        this.directShopId = directShopId;
    }

    public String getDirectShopName() {
        return directShopName;
    }

    public void setDirectShopName(String directShopName) {
        this.directShopName = directShopName;
    }

    public String getDirectShopAddId() {
        return directShopAddId;
    }

    public void setDirectShopAddId(String directShopAddId) {
        this.directShopAddId = directShopAddId;
    }

    public String getDirectShopAddDetail() {
        return directShopAddDetail;
    }

    public void setDirectShopAddDetail(String directShopAddDetail) {
        this.directShopAddDetail = directShopAddDetail;
    }

    public String getDirectShopTel() {
        return directShopTel;
    }

    public void setDirectShopTel(String directShopTel) {
        this.directShopTel = directShopTel;
    }

    public String getDirectShopStatus() {
        return directShopStatus;
    }

    public void setDirectShopStatus(String directShopStatus) {
        this.directShopStatus = directShopStatus;
    }

    public Date getDirectShopCreateDate() {
        return (Date) directShopCreateDate.clone();
    }

    public void setDirectShopCreateDate(Date directShopCreateDate) {
        this.directShopCreateDate = directShopCreateDate==null?null: (Date) directShopCreateDate.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getDirectShopModifyDate() {
        return (Date) directShopModifyDate.clone();
    }

    public void setDirectShopModifyDate(Date directShopModifyDate) {
        this.directShopModifyDate = directShopModifyDate==null?null: (Date) directShopModifyDate.clone();
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getDirectShopAdd() {
        return directShopAdd;
    }

    public void setDirectShopAdd(String directShopAdd) {
        this.directShopAdd = directShopAdd;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getCountyId() {
        return countyId;
    }

    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
}
