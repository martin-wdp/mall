/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.other.bean;

import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.bean.CustomerCoupon;
import org.apache.commons.lang.StringUtils;

import javax.validation.constraints.Pattern;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员所有信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月16日 下午4:46:23
 * @version 1.0
 */
public class CustomerAllInfo extends Customer {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 7221989306837681709L;
    // 会员信息编号
    private Long infoId;
    // 电子邮件
    // @Pattern(regexp =
    // "([0-9A-Za-z][a-zA-Z0-9\\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\\.[a-zA-Z\\.]{1,}[A-Za-z])|()")
    private String infoEmail;
    // 手机号码
    @Pattern(regexp = "(0?(13|15|17|18|14)[0-9]{9})|()")
    private String infoMobile;
    // 地址
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoAddress;
    // 身份证
    @Pattern(regexp = "[1-9]([0-9]{14}|[0-9]{17}|[0-9]{16}[xyXY])|()")
    private String infoCardid;
    // 真实姓名
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoRealname;
    // 性别
    private String infoGender;
    // 会员等级编号
    private Long pointLevelId;
    // 会员等级
    private String pointLevelName;
    // 生日
    private String infoBirthday;
    // 邮编
    private String infoRegip;
    // 省份
    private String infoProvince;
    // 城市
    private String infoCity;
    // 区县
    private String infoCounty;
    // 街道
    private String infoStreet;
    // 婚姻状况
    @Pattern(regexp = "[012]")
    private String infoMarital;
    // 月收入
    private String infoSalary;
    // 兴趣爱好
    @Pattern(regexp = "[^\\<\\>]*")
    private String infoInterest;
    // 余额
    private BigDecimal balanceSum;
    // 总积分
    private Integer infoPointSum;
    // 会员类型
    private Integer infoType;
    // 注册时间
    private Date infoRegisterTime;
    // 固定电话
    private String infoPhone;
    // 邮编
    private String infoZip;
    // 订单列表
    private List<OrderInfoBean> orders = new ArrayList<OrderInfoBean>(0);
    // 优惠劵列表
    private List<CustomerCoupon> coupons = new ArrayList<CustomerCoupon>(0);
    // 分页开始的条数
    private int startRowNum;
    // 分页结束的条数
    private int endRowNum;
    // 查询条件 登录时间
    private Date loginTimeTo;
    // 收货地址
    private CustomerAddress customerAddress;
    // 省
    private ProvinceBean province;
    // 市
    private CityBean city;
    // 区
    private DistrictBean district;
    // 头像图片
    @Pattern(regexp = "([_A-Za-z0-9:\\/\\.]+[^\\<\\>]*)|()")
    private String customerImg;

    // 注册资金
    private BigDecimal companyCapital;

    private String bussLegalName;
    private String bussLegalCardId;
    private String bussRange;
    private String companyType;
    private String companyName;
    private Date auditTime;

    //认证企业地址 省市区
    private String companyAddress;
    //详细地址
    private String companyContactAddr;
    //企业联系人
    private String companyContactName;

    //企业联系人手机

    private String companyContactMoble;

    public String getCompanyContactMoble() {
        return companyContactMoble;
    }

    public void setCompanyContactMoble(String companyContactMoble) {
        this.companyContactMoble = companyContactMoble;
    }

    public String getCompanyContactName() {
        return companyContactName;
    }

    public void setCompanyContactName(String companyContactName) {
        this.companyContactName = companyContactName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContactAddr() {
        return companyContactAddr;
    }

    public void setCompanyContactAddr(String companyContactAddr) {
        this.companyContactAddr = companyContactAddr;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public BigDecimal getCompanyCapital() {
        return companyCapital;
    }

    public void setCompanyCapital(BigDecimal companyCapital) {
        this.companyCapital = companyCapital;
    }

    public String getBussLegalName() {
        return bussLegalName;
    }

    public void setBussLegalName(String bussLegalName) {
        this.bussLegalName = bussLegalName;
    }

    public String getBussLegalCardId() {
        return bussLegalCardId;
    }

    public void setBussLegalCardId(String bussLegalCardId) {
        this.bussLegalCardId = bussLegalCardId;
    }

    public String getBussRange() {
        return bussRange;
    }

    public void setBussRange(String bussRange) {
        this.bussRange = bussRange;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    public ProvinceBean getProvince() {
        return province;
    }

    public void setProvince(ProvinceBean province) {
        this.province = province;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public DistrictBean getDistrict() {
        return district;
    }

    public void setDistrict(DistrictBean district) {
        this.district = district;
    }

    public CustomerAddress getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(CustomerAddress customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getInfoStreet() {
        return infoStreet;
    }

    public void setInfoStreet(String infoStreet) {
        this.infoStreet = infoStreet;
    }

    /**
     * 获取登录时间
     * */
    public Date getLoginTimeTo() {
        if (this.loginTimeTo != null) {
            return new Date(this.loginTimeTo.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置登录时间
     * */
    public void setLoginTimeTo(Date loginTimeTo) {
        if (loginTimeTo != null) {
            Date timeTemp = (Date) loginTimeTo.clone();
            if (timeTemp != null) {
                this.loginTimeTo = timeTemp;
            }
        }
    }

    /**
     * 用户名和密码 是否为空 如果用户名或者密码 有一个为空 就返回true 否则返回false
     * 
     * @return
     */
    public boolean isNameAndPasswordEmpty() {
        return StringUtils.isEmpty(super.getCustomerUsername()) || StringUtils.isEmpty(super.getCustomerPassword());
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    public List<CustomerCoupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<CustomerCoupon> coupons) {
        this.coupons = coupons;
    }

    /**
     * 获取注册时间
     * */
    public Date getInfoRegisterTime() {
        if (this.infoRegisterTime != null) {
            return new Date(this.infoRegisterTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置注册时间
     * */
    public void setInfoRegisterTime(Date infoRegisterTime) {
        if (infoRegisterTime != null) {
            Date timeTemp = (Date) infoRegisterTime.clone();
            if (timeTemp != null) {
                this.infoRegisterTime = timeTemp;
            }
        }
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

    public Integer getInfoType() {
        return infoType;
    }

    public void setInfoType(Integer infoType) {
        this.infoType = infoType;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public List<OrderInfoBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderInfoBean> orders) {
        this.orders = orders;
    }

    public Integer getInfoPointSum() {
        return infoPointSum;
    }

    public void setInfoPointSum(Integer infoPointSum) {
        this.infoPointSum = infoPointSum;
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

    public Long getPointLevelId() {
        return pointLevelId;
    }

    public void setPointLevelId(Long pointLevelId) {
        this.pointLevelId = pointLevelId;
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

    public String getInfoGender() {
        return infoGender;
    }

    public void setInfoGender(String infoGender) {
        this.infoGender = infoGender;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
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

}
