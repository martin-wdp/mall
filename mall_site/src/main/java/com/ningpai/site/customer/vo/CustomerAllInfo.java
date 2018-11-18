/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.vo;

import com.ningpai.customer.bean.Customer;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员详细信息VO
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午5:16:54
 * @version 0.0.1
 */

public class CustomerAllInfo extends Customer {

    /**
     * 用户名
     * 
     * @see #getCustomerUsername()
     * @see #setCustomerUsername(String)
     */
    @Null
    @Pattern(regexp = "[0-9A-Za-z][a-zA-Z0-9\\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\\.[a-zA-Z\\.]{1,}[A-Za-z]|(0?(13|15|17|18|14)[0-9]{9})|(\\w+[^\\<\\>]+)")
    private String customerUsername;
    /**
     * 会员密码
     * 
     * @see #getCustomerPassword()
     * @see #setCustomerPassword(String)
     */
    @Null
    @Length(min = 6, max = 20)
    @Pattern(regexp = "[^\\<\\>]+")
    private String customerPassword;

    /**
     * 电子邮件
     */
    @Pattern(regexp = "[0-9A-Za-z][a-zA-Z0-9\\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\\.[a-zA-Z\\.]{1,}[A-Za-z]")
    private String infoEmail;
    /**
     * 手机号码
     */
    @Pattern(regexp = "(0?(13|15|17|18|14)[0-9]{9})")
    private String infoMobile;
    /**
     * 地址
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoAddress;
    /**
     * 身份证
     */
    @Pattern(regexp = "(0?(13|15|17|18|14)[0-9]{9})")
    private String infoCardid;
    /**
     * 真实姓名
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoRealname;
    /**
     * 性别
     */
    private String infoGender;
    /**
     * 会员等级编号
     */
    private Long pointLevelId;
    /**
     * 会员等级
     */
    private String pointLevelName;
    /**
     * 生日
     */
    private String infoBirthday;
    /**
     * 邮编
     */
    private String infoRegip;
    /**
     * 省份
     */
    private String infoProvince;
    /**
     * 城市
     */
    private String infoCity;
    /**
     * 区县
     */
    private String infoCounty;
    /**
     * 街道
     */
    private String infoStreet;
    /**
     * 婚姻状况
     */
    private String infoMarital;
    /**
     * 月收入
     */
    private String infoSalary;
    /**
     * 兴趣爱好
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoInterest;
    /**
     * 余额
     */
    private BigDecimal balanceSum;
    /**
     * 总积分
     */
    private Integer infoPointSum;
    /**
     * 会员类型
     */
    private Integer infoType;
    /**
     * 注册时间
     */
    private Date infoRegisterTime;
    /**
     * 固定电话
     */
    private String infoPhone;
    /**
     * 邮编
     */
    private String infoZip;
    /**
     * 订单列表
     */
    private List<OrderInfoBean> orders = new ArrayList<OrderInfoBean>(0);
    /**
     * 会员QQ号码
     * 
     * @see #getInfoQQ()
     * @see #setInfoQQ(String)
     */
    private String infoQQ;
    /**
     * 旺旺号
     * 
     * @see #getWangwangNo()
     * @see #setWangwangNo(String)
     */
    private String wangwangNo;
    /**
     * 会员收货地址集合
     * 
     * @see #getAddresses()
     * @see #setAddresses(List)
     */
    private List<OrderInfoBean> addresses = new ArrayList<OrderInfoBean>(0);

    /**
     * 根据收货地址结算运费
     */
    private BigDecimal freight;
    /**
     * 头像图片
     */
    @Pattern(regexp = "[_A-Za-z0-9:\\/\\.]+[^\\<\\>]*")
    private String customerImg;
    /**
     * 待处理订单数量
     * 
     * @see #getPendingNum()
     * @see #setPendingNum(Long)
     */
    private Long pendingNum;
    /**
     * 待评论订单数量
     * 
     * @see #getCommentNum()
     * @see #setCommentNum(Long)
     */
    private Long commentNum;

    public Long getPendingNum() {
        return pendingNum;
    }

    public void setPendingNum(Long pendingNum) {
        this.pendingNum = pendingNum;
    }

    public Long getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Long commentNum) {
        this.commentNum = commentNum;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public List<OrderInfoBean> getAddresses() {
        return addresses;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public void setAddresses(List<OrderInfoBean> addresses) {
        this.addresses = addresses;
    }

    public String getWangwangNo() {
        return wangwangNo;
    }

    public void setWangwangNo(String wangwangNo) {
        this.wangwangNo = wangwangNo;
    }

    public String getInfoQQ() {
        return infoQQ;
    }

    public void setInfoQQ(String infoQQ) {
        this.infoQQ = infoQQ;
    }

    public String getInfoEmail() {
        return infoEmail;
    }

    public void setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail;
    }

    public String getInfoMobile() {
        return infoMobile;
    }

    public void setInfoMobile(String infoMobile) {
        this.infoMobile = infoMobile;
    }

    public String getInfoAddress() {
        return infoAddress;
    }

    public void setInfoAddress(String infoAddress) {
        this.infoAddress = infoAddress;
    }

    public String getInfoCardid() {
        return infoCardid;
    }

    public void setInfoCardid(String infoCardid) {
        this.infoCardid = infoCardid;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
    }

    public String getInfoGender() {
        return infoGender;
    }

    public void setInfoGender(String infoGender) {
        this.infoGender = infoGender;
    }

    public Long getPointLevelId() {
        return pointLevelId;
    }

    public void setPointLevelId(Long pointLevelId) {
        this.pointLevelId = pointLevelId;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
    }

    public String getInfoBirthday() {
        return infoBirthday;
    }

    public void setInfoBirthday(String infoBirthday) {
        this.infoBirthday = infoBirthday;
    }

    public String getInfoRegip() {
        return infoRegip;
    }

    public void setInfoRegip(String infoRegip) {
        this.infoRegip = infoRegip;
    }

    public String getInfoProvince() {
        return infoProvince;
    }

    public void setInfoProvince(String infoProvince) {
        this.infoProvince = infoProvince;
    }

    public String getInfoCity() {
        return infoCity;
    }

    public void setInfoCity(String infoCity) {
        this.infoCity = infoCity;
    }

    public String getInfoCounty() {
        return infoCounty;
    }

    public void setInfoCounty(String infoCounty) {
        this.infoCounty = infoCounty;
    }

    public String getInfoStreet() {
        return infoStreet;
    }

    public void setInfoStreet(String infoStreet) {
        this.infoStreet = infoStreet;
    }

    public String getInfoMarital() {
        return infoMarital;
    }

    public void setInfoMarital(String infoMarital) {
        this.infoMarital = infoMarital;
    }

    public String getInfoSalary() {
        return infoSalary;
    }

    public void setInfoSalary(String infoSalary) {
        this.infoSalary = infoSalary;
    }

    public String getInfoInterest() {
        return infoInterest;
    }

    public void setInfoInterest(String infoInterest) {
        this.infoInterest = infoInterest;
    }

    public BigDecimal getBalanceSum() {
        return balanceSum;
    }

    public void setBalanceSum(BigDecimal balanceSum) {
        this.balanceSum = balanceSum;
    }

    public Integer getInfoPointSum() {
        return infoPointSum;
    }

    public void setInfoPointSum(Integer infoPointSum) {
        this.infoPointSum = infoPointSum;
    }

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    /**
     * 获取信息注册时间
     * 
     * @return
     */
    public Date getInfoRegisterTime() {
        // 如果注册时间不为空
        if (this.infoRegisterTime != null) {
            // 返回
            return new Date(this.infoRegisterTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置信息注册时间
     * 
     * @param infoRegisterTime
     */
    public void setInfoRegisterTime(Date infoRegisterTime) {
        // 如果注册时间不为空
        if (infoRegisterTime != null) {
            // 获取注册时间
            Date tEmp = (Date) infoRegisterTime.clone();
            if (tEmp != null) {
                // 设置
                this.infoRegisterTime = tEmp;
            }
        }
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getInfoPhone() {
        return infoPhone;
    }

    public void setInfoPhone(String infoPhone) {
        this.infoPhone = infoPhone;
    }

    public String getInfoZip() {
        return infoZip;
    }

    public void setInfoZip(String infoZip) {
        this.infoZip = infoZip;
    }

    public List<OrderInfoBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfoBean> orders) {
        this.orders = orders;
    }

}
