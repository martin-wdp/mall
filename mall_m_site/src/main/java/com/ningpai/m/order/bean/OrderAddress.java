/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.bean;

/**
 * 微信收货地址辅助类
 *
 * 
 */
public class OrderAddress {
    /**
     *id
     */
    private Long addressId;
    /**
     *收货人名称
     */
    private String addressName;

    /**
     *收货人电话
     */
    private String addressPhone;

    /**
     *收货街道信息
     */
    private String addressDetail;

    /**
     *详细地址
     */
    private String addressDetailInfo;

    /**
     *省
     */
    private String proviceFirstStageName;

    /**
     *市
     */
    private String addressCitySecondStageName;

    /**
     *区县
     */
    private String addressCountiesThirdStageName;
    /**
     * 获取ProviceFirstStageName
     * */
    public String getProviceFirstStageName() {
        return proviceFirstStageName;
    }
    /**
     * 设置ProviceFirstStageName
     * */
    public void setProviceFirstStageName(String proviceFirstStageName) {
        this.proviceFirstStageName = proviceFirstStageName;
    }
    /**
     * 获取AddressCitySecondSrageName
     * */
    public String getAddressCitySecondStageName() {
        return addressCitySecondStageName;
    }
    /**
     * 设置AddressCitySecondStageName
     * */
    public void setAddressCitySecondStageName(String addressCitySecondStageName) {
        this.addressCitySecondStageName = addressCitySecondStageName;
    }
    /**
     * 获取AddressCountiesThirdStageName
     * */
    public String getAddressCountiesThirdStageName() {
        return addressCountiesThirdStageName;
    }
    /**
     * 设置AddressCountiesThirdStageName
     * */
    public void setAddressCountiesThirdStageName(String addressCountiesThirdStageName) {
        this.addressCountiesThirdStageName = addressCountiesThirdStageName;
    }
    /**
     * 获取AddressName
     * */
    public String getAddressName() {
        return addressName;
    }
    /**
     * 设置AddressName
     * */
    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }
    /**
     * 获取AddressPhone
     * */
    public String getAddressPhone() {
        return addressPhone;
    }
    /**
     * 设置AddressPhone
     * */
    public void setAddressPhone(String addressPhone) {
        this.addressPhone = addressPhone;
    }
    /**
     * 获取AddressDetail
     * */
    public String getAddressDetail() {
        return addressDetail;
    }
    /**
     * 设置AddressDetail
     * */
    public void setAddressDetail(String addressDetail) {
        this.addressDetail = addressDetail;
    }
    /**
     * 获取AddressDetailInfo
     * */
    public String getAddressDetailInfo() {
        return addressDetailInfo;
    }
    /**
     * 设置AddressDetailInfo
     * */
    public void setAddressDetailInfo(String addressDetailInfo) {
        this.addressDetailInfo = addressDetailInfo;
    }
    /**
     * 设置AddressId
     * */
    public  void setAddressId(Long addressId){
        this.addressId=addressId;
    }
    /**
     * 获取AddressId
     * */
    public Long getAddressId(){
        return this.addressId;
    }

}
