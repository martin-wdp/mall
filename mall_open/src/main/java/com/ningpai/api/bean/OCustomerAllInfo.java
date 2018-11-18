package com.ningpai.api.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
public class OCustomerAllInfo {
    /**
     * 会员信息编号
     */
    private Long infoId;
    /**
     * 真实姓名
     */
    private String infoRealname;
    /**
     * 身份证号码
     */
    private String infoCardid;
    /**
     * 性别
     */
    private String infoGender;
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
     * 地址
     */
    private String infoAddress;
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
    private String infoInterest;
    /**
     * 余额
     */
    private BigDecimal balanceSum;
    /**
     * 积分总额
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
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 手机
     */
    private String infoPhone;
    /**
     * 邮编
     */
    private String infoZip;
    /**
     * 删除时间
     */
    private Date delTime;

    /**
     * 会员编号
     */
    private Long customerId;

    /**
     * 会员名称
     */
    private String customerUsername;

    /**
     * 会员别名
     */
    private String customerNickname;

    /**
     * 会员图片
     */
    private String customerImg;

    /**
     * 会员等级名称
     */
    private String pointLvelName;

    /**
     * 手机是否验证
     */
    private String isMobile;

    /**
     * 邮箱是否验证
     */
    private String isEmail;

    /**
     * 商家标记 0普通会员 1商家 2店铺员工
     */
    private String isSeller;

    /**
     * 第三方商家id
     */
    private String thirdId;

    /**
     * 用户手机号
     */
    private String infoMobile;

    /**
     * 用户邮箱号
     */
    private String infoEmail;

    /**
     * 省
     */
    private String infoProvince;

    /**
     * 市
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
     * 用户密码
     */
    private String customerPassword;

    /**
     * 是否启用
     */
    private String isFlag;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 登录时间
     */
    private Date loginTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 等级id
     */
    private Long pointLevelId;

    /**
     * 时间
     */
    private Date aeadTime;

    public Long getPointLevelId() {
        return pointLevelId;
    }


    /**
     * 会员等级
     * @param pointLevelId 等级
     * @return
     */
    public OCustomerAllInfo setPointLevelId(Long pointLevelId) {
        this.pointLevelId = pointLevelId;
        return this;
    }

    /**
     * 获取时间
     * @return
     */
    public Date getAeadTime() {

        if(aeadTime!=null){
            return (Date) aeadTime.clone();
        }
        return null;
    }

    /**
     * 设置时间
     * @param aeadTime
     * @return
     */
    public OCustomerAllInfo setAeadTime(Date aeadTime) {
        this.aeadTime = aeadTime == null ? null : (Date) aeadTime.clone();
        return this;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        if(createTime!=null){
            return (Date) createTime.clone();
        }
        return null;
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     * @return
     */
    public OCustomerAllInfo setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
        return this;
    }

    /**
     * 设置登录时间
     * @return 登录时间
     */
    public Date getLoginTime() {
        if(loginTime==null){
            return null;
        }else{
            return (Date) loginTime.clone();
        }
    }

    /**
     * 设置登录时间
     * @param loginTime 登录时间
     * @return
     */
    public OCustomerAllInfo setLoginTime(Date loginTime) {
        this.loginTime = loginTime == null ? null : (Date) loginTime.clone();
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置
     * @param delFlag
     * @return
     */
    public OCustomerAllInfo setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public String getIsFlag() {
        return isFlag;
    }

    /**
     * 设置删除标记
     * @param isFlag
     * @return
     */
    public OCustomerAllInfo setIsFlag(String isFlag) {
        this.isFlag = isFlag;
        return this;
    }

    /**
     * 用户密码
     *
     * @param customerPassword
     *            用户密码
     * @return
     */
    public OCustomerAllInfo setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
        return this;
    }

    /**
     * 用户手机号
     */
    public String getInfoMobile() {
        return infoMobile;
    }
    /**
     * 设置INfoMobile
     * */
    public OCustomerAllInfo setInfoMobile(String infoMobile) {
        this.infoMobile = infoMobile;
        return this;
    }

    /**
     * 用户邮箱号
     */
    public String getInfoEmail() {
        return infoEmail;
    }
    /**
     * 设置邮箱
     * */
    public OCustomerAllInfo setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail;
        return this;
    }

    /**
     * 省
     */
    public String getInfoProvince() {
        return infoProvince;
    }

    /**
     * 省
     * @param infoProvince
     * @return
     */
    public OCustomerAllInfo setInfoProvince(String infoProvince) {
        this.infoProvince = infoProvince;
        return this;
    }

    /**
     * 市
     */
    public String getInfoCity() {
        return infoCity;
    }

    /**
     * 市
     * @param infoCity
     * @return
     */
    public OCustomerAllInfo setInfoCity(String infoCity) {
        this.infoCity = infoCity;
        return this;
    }

    /**
     * 区
     */
    public String getInfoCounty() {
        return infoCounty;
    }
    /**
     * 设置InfoCounty
     * */
    public OCustomerAllInfo setInfoCounty(String infoCounty) {
        this.infoCounty = infoCounty;
        return this;
    }

    /**
     * 街道
     */
    public String getInfoStreet() {
        return infoStreet;
    }

    /**
     * 街道
     */
    public OCustomerAllInfo setInfoStreet(String infoStreet) {
        this.infoStreet = infoStreet;
        return this;
    }

    /**
     * 会员编号
     */
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 会员名称
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 会员别名
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    /**
     * 会员图片
     */
    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * 会员等级名称
     */
    public String getPointLvelName() {
        return pointLvelName;
    }

    public void setPointLvelName(String pointLvelName) {
        this.pointLvelName = pointLvelName;
    }

    /**
     * 手机是否验证
     */
    public String getIsMobile() {
        return isMobile;
    }

    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 邮箱是否验证
     */
    public String getIsEmail() {
        return isEmail;
    }

    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    /**
     * 商家标记 0普通会员 1商家 2店铺员工
     */
    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }

    /**
     * 第三方商家id
     */
    public String getThirdId() {
        return thirdId;
    }

    public void setThirdId(String thirdId) {
        this.thirdId = thirdId;
    }

    public Long getInfoId() {
        return infoId;
    }

    /**
     * 设置id
     * @param infoId
     * @return
     */
    public OCustomerAllInfo setInfoId(Long infoId) {
        this.infoId = infoId;
        return this;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    /**
     * 设置别名
     * @param infoRealname
     * @return
     */
    public OCustomerAllInfo setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
        return this;
    }

    public String getInfoCardid() {
        return infoCardid;
    }

    /**
     * 身份证
     * @param infoCardid
     * @return
     */
    public OCustomerAllInfo setInfoCardid(String infoCardid) {
        this.infoCardid = infoCardid;
        return this;
    }

    public String getInfoGender() {
        return infoGender;
    }

    /**
     * 性别
     * @param infoGender
     * @return
     */
    public OCustomerAllInfo setInfoGender(String infoGender) {
        this.infoGender = infoGender;
        return this;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    /**
     * 等级名称
     * @param pointLevelName
     * @return
     */
    public OCustomerAllInfo setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
        return this;
    }

    public String getInfoBirthday() {
        return infoBirthday;
    }


    /**
     * 生日
     * @param infoBirthday
     * @return
     */
    public OCustomerAllInfo setInfoBirthday(String infoBirthday) {
        this.infoBirthday = infoBirthday;
        return this;
    }

    public String getInfoRegip() {
        return infoRegip;
    }

    /**
     * 邮编
     * @param infoRegip
     * @return
     */
    public OCustomerAllInfo setInfoRegip(String infoRegip) {
        this.infoRegip = infoRegip;
        return this;
    }

    public String getInfoAddress() {
        return infoAddress;
    }

    /**
     * 地址
     * @param infoAddress
     * @return
     */
    public OCustomerAllInfo setInfoAddress(String infoAddress) {
        this.infoAddress = infoAddress;
        return this;
    }

    public String getInfoMarital() {
        return infoMarital;
    }

    /**
     *
     * @param infoMarital
     * @return
     */
    public OCustomerAllInfo setInfoMarital(String infoMarital) {
        this.infoMarital = infoMarital;
        return this;
    }

    public String getInfoSalary() {
        return infoSalary;
    }

    /**
     * 月收入
     * @param infoSalary
     * @return
     */
    public OCustomerAllInfo setInfoSalary(String infoSalary) {
        this.infoSalary = infoSalary;
        return this;
    }

    public String getInfoInterest() {
        return infoInterest;
    }

    /**
     * 兴趣爱好
     * @param infoInterest
     * @return
     */
    public OCustomerAllInfo setInfoInterest(String infoInterest) {
        this.infoInterest = infoInterest;
        return this;
    }

    public BigDecimal getBalanceSum() {
        return balanceSum;
    }

    /**
     * 余额
     * @param balanceSum
     * @return
     */
    public OCustomerAllInfo setBalanceSum(BigDecimal balanceSum) {
        this.balanceSum = balanceSum;
        return this;
    }

    public Integer getInfoPointSum() {
        return infoPointSum;
    }

    /**
     * 积分总额
     * @param infoPointSum
     * @return
     */
    public OCustomerAllInfo setInfoPointSum(Integer infoPointSum) {
        this.infoPointSum = infoPointSum;
        return this;
    }

    public Integer getInfoType() {
        return infoType;
    }

    /**
     * 会员类型
     * @param infoType
     * @return
     */
    public OCustomerAllInfo setInfoType(Integer infoType) {
        this.infoType = infoType;
        return this;
    }

    /**
     * 注册时间
     * @return
     */
    public Date getInfoRegisterTime() {
        if(infoRegisterTime==null){
            return null;
        }else{
            return (Date) infoRegisterTime.clone();
        }
    }
    /**
     * 注册时间
     * @return
     */
    public OCustomerAllInfo setInfoRegisterTime(Date infoRegisterTime) {
        this.infoRegisterTime = infoRegisterTime == null ? null : (Date) infoRegisterTime.clone();
        return this;
    }

    /**
     * 修改时间
     * @return
     */
    public Date getModifiedTime() {
        if(modifiedTime==null){
            return null;
        }else{
            return (Date) modifiedTime.clone();
        }
    }

    /**
     * 修改时间
     * @return
     */
    public OCustomerAllInfo setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : (Date) modifiedTime.clone();
        return this;
    }

    public String getInfoPhone() {
        return infoPhone;
    }

    /**
     * 手机
     * @param infoPhone 手机
     * @return
     */
    public OCustomerAllInfo setInfoPhone(String infoPhone) {
        this.infoPhone = infoPhone;
        return this;
    }

    public String getInfoZip() {
        return infoZip;
    }

    /**
     * 邮编
     * @param infoZip 邮编
     * @return
     */
    public OCustomerAllInfo setInfoZip(String infoZip) {
        this.infoZip = infoZip;
        return this;
    }

    /**
     * 删除时间
     * @return
     */
    public Date getDelTime() {
        if(delTime==null){
            return null;
        }else{
            return (Date) delTime.clone();
        }
    }

    /**
     * 删除时间
     * @return
     */
    public OCustomerAllInfo setDelTime(Date delTime) {
        this.delTime = delTime == null ? null : (Date) delTime.clone();
        return this;
    }
}
