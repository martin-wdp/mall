package com.ningpai.third.auth.bean;

import java.util.Date;

/**
 * <p>
 * 会员实体
 * </p>
 * 
 * @author zhanghl
 * @since 2015.07.30
 * @version 2.0
 */
public class Customer {

    /**
     * 会员编号
     * 
     * @see #getCustomerId()
     * @see Customer#setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 用户名
     * 
     * @see #getCustomerUsername()
     * @see #setCustomerUsername(String)
     */
    private String customerUsername;
    /**
     * 会员密码
     * 
     * @see #getCustomerPassword()
     * @see #setCustomerPassword(String)
     */
    private String customerPassword;
    /**
     * 会员昵称
     * 
     * @see #getCustomerNickname()
     * @see #setCustomerNickname(String)
     */
    private String customerNickname;
    /**
     * 手机是否验证
     * 
     * @see #getIsMobile()
     * @see #setIsMobile(String)
     */
    private String isMobile;
    /**
     * 邮箱是否验证
     * 
     * @see #getIsEmail()
     * @see #setIsEmail(String)
     */
    private String isEmail;
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
     * 会员头像
     * 
     * @see #getCustomerImg()
     * @see #setCustomerImg(String)
     */
    private String customerImg;
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
     * 第三方编号
     * 
     * @see #getThirdId()
     * @see #setThirdId(Long)
     */
    private Long thirdId;
    /**
     * 商家标记 0普通会员 1商家 2店铺员工
     * 
     * @see #getIsSeller()
     * @see #setIsSeller(String)
     */
    private String isSeller;

    private ThirdAuthority thirdAuthority;

    public ThirdAuthority getThirdAuthority() {
        return thirdAuthority;
    }

    public void setThirdAuthority(ThirdAuthority thirdAuthority) {
        this.thirdAuthority = thirdAuthority;
    }

    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 获取验证码失效时间
     * @return
     */
    public Date getAeadTime() {
        if (this.aeadTime != null) {
            return new Date(this.aeadTime.getTime());
        }
        return null;
    }

    /**
     * 设置验证码失效时间
     * @param aeadTime
     */
    public void setAeadTime(Date aeadTime) {
        if (aeadTime != null) {
            Date tEmp = (Date) aeadTime.clone();
            if (tEmp != null) {
                this.aeadTime = tEmp;
            }
        }
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getCustomerImg() {
        return customerImg;
    }

    public void setCustomerImg(String customerImg) {
        this.customerImg = customerImg;
    }

    /**
     * 获取CustomerId的值
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置CustomerId的值
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 设置customerUsername的值
     * 
     * @return customerUsername
     *         {@link com.ningpai.customer.bean.Customer#customerUsername}
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    /**
     * 获取customerUsername的值
     * 
     * @param customerUsername
     *            {@link com.ningpai.customer.bean.Customer#customerUsername}
     */
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 获取customerPassword的值
     * {@link com.ningpai.customer.bean.Customer#customerPassword}
     */
    public String getCustomerPassword() {
        return customerPassword;
    }

    /**
     * 设置customerPassword的值
     * 
     * @return customerPassword
     *         {@link com.ningpai.customer.bean.Customer#customerPassword}
     */
    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    /**
     * 获取customerNickname的值
     * 
     * {@link com.ningpai.customer.bean.Customer#customerNickname}
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    /**
     * 设置customerNickname的值
     * 
     * @return customerNickname
     *         {@link com.ningpai.customer.bean.Customer#customerNickname}
     */
    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    /**
     * 获取isMobile的值
     * 
     * {@link com.ningpai.customer.bean.Customer#isMobile}
     */
    public String getIsMobile() {
        return isMobile;
    }

    /**
     * 设置isMobile的值
     * 
     * @return isMobile {@link com.ningpai.customer.bean.Customer#isMobile}
     */
    public void setIsMobile(String isMobile) {
        this.isMobile = isMobile;
    }

    /**
     * 获取isEmail的值
     * 
     * {@link com.ningpai.customer.bean.Customer#isEmail}
     */
    public String getIsEmail() {
        return isEmail;
    }

    /**
     * 设置isEmail的值
     * 
     * @return isEmail {@link com.ningpai.customer.bean.Customer#isEmail}
     */
    public void setIsEmail(String isEmail) {
        this.isEmail = isEmail;
    }

    /**
     * 获取isFlag的值
     * 
     * {@link com.ningpai.customer.bean.Customer#isFlag}
     */
    public String getIsFlag() {
        return isFlag;
    }

    /**
     * 设置isFlag的值
     * 
     * @return isFlag {@link com.ningpai.customer.bean.Customer#isFlag}
     */
    public void setIsFlag(String isFlag) {
        this.isFlag = isFlag;
    }

    /**
     * 获取loginIp的值
     * 
     * {@link com.ningpai.customer.bean.Customer#loginIp}
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 设置loginIp的值
     * 
     * @return loginIp {@link com.ningpai.customer.bean.Customer#loginIp}
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 获取loginTime的值
     * 
     * {@link com.ningpai.customer.bean.Customer#loginTime}
     */
    public Date getLoginTime() {
        if (this.loginTime != null) {
            return new Date(this.loginTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置loginTime的值
     * 
     * @return loginTime {@link com.ningpai.customer.bean.Customer#loginTime}
     */
    public void setLoginTime(Date loginTime) {
        if (loginTime != null) {
            Date tEmp = (Date) loginTime.clone();
            if (tEmp != null) {
                this.loginTime = tEmp;
            }
        }
    }

    /**
     * 获取createTime的值
     * 
     * {@link com.ningpai.customer.bean.Customer#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置createTime的值
     * 
     * @return createTime {@link com.ningpai.customer.bean.Customer#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取modifiedTime的值
     * 
     * {@link com.ningpai.customer.bean.Customer#modifiedTime}
     */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置modifiedTime的值
     * 
     * @return modifiedTime
     *         {@link com.ningpai.customer.bean.Customer#modifiedTime}
     */
    public void setModifiedTime(Date modifiedTime) {
        if (modifiedTime != null) {
            Date tEmp = (Date) modifiedTime.clone();
            if (tEmp != null) {
                this.modifiedTime = tEmp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * {@link com.ningpai.customer.bean.Customer#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @return delFlag {@link com.ningpai.customer.bean.Customer#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * 获取delTime的值
     * 
     * {@link com.ningpai.customer.bean.Customer#delTime}
     */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置delTime的值
     * 
     * @return delTime {@link com.ningpai.customer.bean.Customer#delTime}
     */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date tEmp = (Date) delTime.clone();
            if (tEmp != null) {
                this.delTime = tEmp;
            }
        }
    }

}
