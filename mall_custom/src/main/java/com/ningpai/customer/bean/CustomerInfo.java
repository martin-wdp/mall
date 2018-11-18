package com.ningpai.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 会员详细信息
 * 
 * @author NINGPAI-张强强
 * @since 2013年12月16日 下午4:55:24
 * @version 1.0
 */
@Component("customerInfo")
public class CustomerInfo {
    // 会员信息编号
    private Long infoId;
    // 会员编号
    private Long customerId;
    // 真实姓名
    private String infoRealname;
    // 身份证号码
    private String infoCardid;
    // 性别
    private String infoGender;
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
    // 地址
    private String infoAddress;
    // 婚姻状况
    private String infoMarital;
    // 月收入
    private String infoSalary;
    // 兴趣爱好
    private String infoInterest;
    // 电子邮件
    private String infoEmail;
    // 手机电话
    private String infoMobile;
    // 余额
    private BigDecimal balanceSum;
    // 积分总额
    private Integer infoPointSum;
    // 会员类型
    private Integer infoType;
    // 注册时间
    private Date infoRegisterTime;
    // 修改时间
    private Date modifiedTime;
    // 删除标记
    private String delFlag;
    // 手机
    private String infoPhone;
    // 邮编
    private String infoZip;
    // 删除时间
    private Date delTime;

    public String getInfoStreet() {
        return infoStreet;
    }

    public void setInfoStreet(String infoStreet) {
        this.infoStreet = infoStreet;
    }

    public Long getInfoId() {
        return infoId;
    }

    public void setInfoId(Long infoId) {
        this.infoId = infoId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
    }

    public String getInfoCardid() {
        return infoCardid;
    }

    public void setInfoCardid(String infoCardid) {
        this.infoCardid = infoCardid;
    }

    public String getInfoGender() {
        return infoGender;
    }

    public void setInfoGender(String infoGender) {
        this.infoGender = infoGender;
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

    public String getInfoAddress() {
        return infoAddress;
    }

    public void setInfoAddress(String infoAddress) {
        this.infoAddress = infoAddress;
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
     *获取创建时间
     * */
    public Date getInfoRegisterTime() {
        if (this.infoRegisterTime != null) {
            return new Date(this.infoRegisterTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置创建时间
     * */
    public void setInfoRegisterTime(Date infoRegisterTime) {
        if (infoRegisterTime != null) {
            Date timeEmp = (Date) infoRegisterTime.clone();
            if (timeEmp != null) {
                this.infoRegisterTime = timeEmp;
            }
        }
    }
    /**
     *获取修改时间
     * */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置修改时间
     * */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date timeEmp = (Date) modifiedTime.clone();
            if (timeEmp != null) {
                this.modifiedTime = timeEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
    /**
     *获取删除时间
     * */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置删除时间
     * */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date timeEmp = (Date) delTime.clone();
            if (timeEmp != null) {
                this.delTime = timeEmp;
            }
        }
    }
}
