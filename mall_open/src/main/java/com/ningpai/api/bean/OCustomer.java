package com.ningpai.api.bean;

import java.util.Date;

/**
 * 会员基础信息
 * 
 * @author lih
 * @version 2.0
 * @since 15/8/26
 */
public class OCustomer {

    /**
     * 账户状态
     *
     * @see #getIsFlag()
     * @see #setIsFlag(String)
     */
    private String isFlag;
    /**
     * 登陆IP
     *
     * @see #getLoginIp()
     * @see #setLoginIp(String)
     */
    private String loginIp;
    /**
     * 登陆时间
     *
     * @see #getLoginTime()
     * @see #setLoginTime(Date)
     */
    private Date loginTime;
    /**
     * 创建时间
     *
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 修改时间
     *
     * @see #getModifiedTime()
     * @see #setModifiedTime(Date)
     */
    private Date modifiedTime;
    /**
     * 删除标记
     *
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 删除时间
     *
     * @see #getDelTime()
     * @see #setDelTime(Date)
     */
    private Date delTime;

    /**
     * 手机验证码
     *
     * @see #getCaptcha()
     * @see #setCaptcha(String)
     */
    private String captcha;
    /**
     * 验证码失效时间
     *
     * @see #getAeadTime()
     * @see #setAeadTime(Date)
     */
    private Date aeadTime;

    /**
     * 邮件验证码
     *
     * @see #getCaptcha()
     * @see #setCaptcha(String)
     */
    private String pwdCaptcha;
    /**
     * 邮件验证码失效时间
     *
     * @see #getAeadTime()
     * @see #setAeadTime(Date)
     */
    private Date pwdAeadTime;

    /**
     * 是否临时会员
     *
     * @see #getIsTempCust()
     * @see #setIsTempCust(String)
     */
    private String isTempCust;

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
     * 用户密码
     */
    private String customerPassword;

    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * 用户密码
     * 
     * @param customerPassword
     *            用户密码
     * @return
     */
    public OCustomer setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
        return this;
    }

    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 会员id
     * @param customerId
     * @return
     */
    public OCustomer setCustomerId(Long customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getIsFlag() {
        return isFlag;
    }

    /**
     * 删除标记
     * @param isFlag
     * @return
     */
    public OCustomer setIsFlag(String isFlag) {
        this.isFlag = isFlag;
        return this;
    }

    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 登录id
     * @param loginIp
     * @return
     */
    public OCustomer setLoginIp(String loginIp) {
        this.loginIp = loginIp;
        return this;
    }

    /**
     * 登录id
     * @return
     */
    public Date getLoginTime() {
        if(loginTime==null){
            return null;
        }else{
            return (Date) loginTime.clone();
        }

    }

    /**
     * 登录时间
     * @param loginTime
     * @return
     */
    public OCustomer setLoginTime(Date loginTime) {
        this.loginTime = loginTime == null ? null : (Date) loginTime.clone();
        return this;
    }

    /**
     * 创建时间
     * @return
     */
    public Date getCreateTime() {
        if(createTime==null){
            return null;
        }else{
            return (Date) createTime.clone();
        }
    }

    /**
     * 创建时间
     * @param createTime
     * @return
     */
    public OCustomer setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
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
     * @param modifiedTime
     * @return
     */
    public OCustomer setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : (Date) modifiedTime.clone();
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     * @param delFlag
     * @return
     */
    public OCustomer setDelFlag(String delFlag) {
        this.delFlag = delFlag;
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
     * @param delTime
     * @return
     */
    public OCustomer setDelTime(Date delTime) {

        this.delTime = delTime == null ? null : (Date) delTime.clone();
        return this;
    }

    public String getCaptcha() {
        return captcha;
    }
    /**
     * 设置Captcha
     * */
    public OCustomer setCaptcha(String captcha) {
        this.captcha = captcha;
        return this;
    }

    /**
     * 验证码失效时间
     * @return
     */
    public Date getAeadTime() {

        if(aeadTime==null){
            return null;
        }else{
            return (Date) aeadTime.clone();
        }
    }

    /**
     * 验证码失效时间
     * @return
     */
    public OCustomer setAeadTime(Date aeadTime) {
        this.aeadTime = aeadTime == null ? null : (Date) aeadTime.clone();
        return this;
    }

    public String getPwdCaptcha() {
        return pwdCaptcha;
    }

    /**
     * 邮件验证码
     * @param pwdCaptcha
     * @return
     */
    public OCustomer setPwdCaptcha(String pwdCaptcha) {
        this.pwdCaptcha = pwdCaptcha;
        return this;
    }

    /**
     * 邮件验证码失效时间
     * @return
     */
    public Date getPwdAeadTime() {
        if(pwdAeadTime==null){
            return null;
        }else{
            return (Date) pwdAeadTime.clone();
        }
    }

    /**
     * 邮件验证码失效时间
     * @return
     */
    public OCustomer setPwdAeadTime(Date pwdAeadTime) {
        this.pwdAeadTime = pwdAeadTime == null ? null : (Date) pwdAeadTime.clone();
        return this;
    }

    public String getIsTempCust() {
        return isTempCust;
    }


    /**
     * 临时会员
     * @param isTempCust
     * @return
     */
    public OCustomer setIsTempCust(String isTempCust) {
        this.isTempCust = isTempCust;
        return this;
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
}
