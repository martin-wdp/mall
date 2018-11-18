package com.ningpai.group.bean;

/**
 * 小组用户
 *@author qiyuanyuan
 */
public class CommonCustomer {

    /**
     * 用户昵称
     */
    private String customerNickname;

    /**
     * 用户ID
     */
    private Long customerId;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 安全
     */
    private String isMobile;
    /**
     * 邮箱
     */
    private String isEmail;
    /**
     * 用户头像
     */
    private String customerImg;
    /**
     * 积分
     */
    private Integer infoCount;

    /**
     * 是否商家 0否 1是 2商家员工 3装修公司
     */
    private String isSeller;

    /**
     * 1 共同小组， 3共同城市 4共同好友
     */
    private Integer focusFlag;
    
    public String getCustomerNickname() {
        return customerNickname;
    }
    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }
    public Long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getIsMobile() {
        return isMobile;
    }
    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }
    public String getIsEmail() {
        return isEmail;
    }
    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }
    public String getCustomerImg() {
        return customerImg;
    }
    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }
    public Integer getInfoCount() {
        return infoCount;
    }
    public void setInfoCount(Integer infoCount) {
        this.infoCount = infoCount;
    }
    public String getIsSeller() {
        return isSeller;
    }
    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }
    public Integer getFocusFlag() {
        return focusFlag;
    }
    public void setFocusFlag(Integer focusFlag) {
        this.focusFlag = focusFlag;
    }
}
