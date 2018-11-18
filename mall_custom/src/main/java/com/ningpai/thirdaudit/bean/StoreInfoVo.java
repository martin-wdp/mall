/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.bean;
/**
 * 商家详情
 *
 * */
public class StoreInfoVo {
    /*
     *店铺编号
      */
    private Long storeId;
    /*
     *客户编号
      */
    private Long customerid;
    /*
     *会员头像
      */
    private String customerImg;
    /*
     *店铺名称
      */
    private String storeName;
    /*
     *店铺开通状态 1：已开通 0：未开通
      */
    private String storeStatus;
    /*
     *公司名称
      */
    private String companyName;
    /*
     *公司地域 省;市;区
      */
    private String companyAddr;
    /*
    * 公司地址id
    * */
    private String companyAddrId;
    /*
     *公司详细地址
      */
    private String companyAddrDel;
    /*
     *公司电话
      */
    private String companyTel;
    /*
     *电子邮箱
      */
    private String companyEmail;
    /*
     *企业类型 1:生产厂商 2:品牌商 3:代理商 4:经销商
      */
    private String companyType;
    /*
     *联系人姓名
      */
    private String companyContactName;
    /*
     *联系人电话
      */
    private String companyContactTel;
    /*
     *审核状态 0:审核中 1:已审核 2:未通过
      */
    private String checkStatus;
    /*
     *删除标志 1：删除 0：未删除
      */
    private String delFlag;
    /*
     *打回原因
      */
    private String refuseContent;
    /*
     *logo
      */
    private String storeLogo;
    /*
     *店铺实体图
      */
    private String storeImg;
    /*
     *是否显示商家首页 0否 1是
      */
    private String isStoreIndex;
    /**
     * 主打商品
     */
    private Object mainGoodsProduct;

    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    public String getStoreImg() {
        return storeImg;
    }

    public void setStoreImg(String storeImg) {
        this.storeImg = storeImg;
    }

    public Object getMainGoodsProduct() {
        return mainGoodsProduct;
    }

    public void setMainGoodsProduct(Object mainGoodsProduct) {
        this.mainGoodsProduct = mainGoodsProduct;
    }

    public String getIsStoreIndex() {
        return isStoreIndex;
    }

    public void setIsStoreIndex(String isStoreIndex) {
        this.isStoreIndex = isStoreIndex;
    }

    public String getRefuseContent() {
        return refuseContent;
    }

    public void setRefuseContent(String refuseContent) {
        this.refuseContent = refuseContent;
    }

    public String getCompanyAddrId() {
        return companyAddrId;
    }

    public void setCompanyAddrId(String companyAddrId) {
        this.companyAddrId = companyAddrId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Long customerid) {
        this.customerid = customerid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreStatus() {
        return storeStatus;
    }

    public void setStoreStatus(String storeStatus) {
        this.storeStatus = storeStatus;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr;
    }

    public String getCompanyAddrDel() {
        return companyAddrDel;
    }

    public void setCompanyAddrDel(String companyAddrDel) {
        this.companyAddrDel = companyAddrDel;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyContactTel() {
        return companyContactTel;
    }

    public void setCompanyContactTel(String companyContactTel) {
        this.companyContactTel = companyContactTel;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

}
