package com.ningpai.customer.bean;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 会员实体
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月16日 下午4:47:01
 * @version 0.0.1
 */
public class Customer implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = 7118154901337020515L;

    /**
     * 会员登陆错误的次数
     */
    private Long loginErrorCount;

    /***
     * 会员达到登陆的次数 锁定登陆时间
     */
    private Date loginLockTime;

    private List<CustomerAddress> customerAddressList;

    /**
     * 登录Key
     *
     * @see #getLoginKey()
     * @see #setLoginKey(String)
     */
    private String loginKey;
    // 会员订单数
    private Long orderNumber;
    // 会员总订单额
    private BigDecimal orderMoney;
    // 订单开始时间，查询使用
    private String startTime;
    // 订单结束时间，查询使用
    private String endTime;

    // 会员等级名称
    private String pointLevelName;

    //会员等级Id
    private Long pointLevelId;

    // 每个等级的会员人数
    private Long levelCount;

    // 每个等级的会员占比
    private BigDecimal levelRate;

    // 会员的订单总数
    private Long countOrder;

    // 交易总额
    private BigDecimal sumPrice;

    // 最后交易成功时间
    private Date maxtime;
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
    @Pattern(regexp = "[0-9A-Za-z][a-zA-Z0-9\\._-]{1,}@[a-zA-Z0-9-]{1,}[a-zA-Z0-9]\\.[a-zA-Z\\.]{1,}[A-Za-z]|(0?(13|15|17|18|14)[0-9]{9})|(\\w+[^\\<\\>]*)|()")
    private String customerUsername;
    /**
     * 会员密码
     *
     * @see #getCustomerPassword()
     * @see #setCustomerPassword(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerPassword;
    /**
     * 会员昵称
     *
     * @see #getCustomerNickname()
     * @see #setCustomerNickname(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
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
    @Pattern(regexp = "([_A-Za-z0-9:\\/\\.]+)|()")
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
     * 商家标记 0普通会员 1商家 2店铺员工 3未通过审核在第三方注册的商家
     *
     * @see #getIsSeller()
     * @see #setIsSeller(String)
     */
    private String isSeller;

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
     * 推广的链接是否被注册过'0'，'1'
     */
    private Long isShare;

    // 是否是前端管理员 '0' 不是,'1' 是
    private String isSiteManager;

    private String isEnterprise;
    private String salesmanName;
    private Long salesmanId;

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(String isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public Long getIsShare() {
        return isShare;
    }

    public void setIsShare(Long isShare) {
        this.isShare = isShare;
    }

    public List<CustomerAddress> getCustomerAddressList() {
        return customerAddressList;
    }

    public void setCustomerAddressList(List<CustomerAddress> customerAddressList) {
        this.customerAddressList = customerAddressList;
    }

    public Long getLoginErrorCount() {
        return loginErrorCount;
    }

    public void setLoginErrorCount(Long loginErrorCount) {
        this.loginErrorCount = loginErrorCount;
    }

    public Date getLoginLockTime() {
        return loginLockTime;
    }

    public void setLoginLockTime(Date loginLockTime) {
        this.loginLockTime = loginLockTime;
    }

    public Long getCountOrder() {
        return countOrder;
    }

    public void setCountOrder(Long countOrder) {
        this.countOrder = countOrder;
    }

    public BigDecimal getSumPrice() {
        return sumPrice;
    }

    public void setSumPrice(BigDecimal sumPrice) {
        this.sumPrice = sumPrice;
    }

    public Date getMaxtime() {
        return maxtime;
    }

    public void setMaxtime(Date maxtime) {
        this.maxtime = maxtime;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
    }

    public Long getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(Long levelCount) {
        this.levelCount = levelCount;
    }

    public BigDecimal getLevelRate() {
        return levelRate;
    }

    public void setLevelRate(BigDecimal levelRate) {
        this.levelRate = levelRate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Long getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Long orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BigDecimal getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(BigDecimal orderMoney) {
        this.orderMoney = orderMoney;
    }

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getIsTempCust() {
        return isTempCust;
    }

    public void setIsTempCust(String isTempCust) {
        this.isTempCust = isTempCust;
    }

    public String getPwdCaptcha() {
        return pwdCaptcha;
    }

    public void setPwdCaptcha(String pwdCaptcha) {
        this.pwdCaptcha = pwdCaptcha;
    }

    /**
     * 获取邮件验证码失效时间
     * */
    public Date getPwdAeadTime() {
        if (this.pwdAeadTime != null) {
            return new Date(this.pwdAeadTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置邮件验证码失效时间
     * */
    public void setPwdAeadTime(Date pwdAeadTime) {
        if (pwdAeadTime != null) {
            Date tEmp = (Date) pwdAeadTime.clone();
            if (tEmp != null) {
                this.pwdAeadTime = tEmp;
            }
        }
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
     *
     * @param
     *
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置CustomerId的值
     *
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
     *
     * @param {@link com.ningpai.customer.bean.Customer#customerPassword}
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
     * @param {@link com.ningpai.customer.bean.Customer#customerNickname}
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
     * @param {@link com.ningpai.customer.bean.Customer#isMobile}
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
     * @param {@link com.ningpai.customer.bean.Customer#isEmail}
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
     * @param {@link com.ningpai.customer.bean.Customer#isFlag}
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
     * @param {@link com.ningpai.customer.bean.Customer#loginIp}
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
     * 获取delFlag的值
     *
     * @param {@link com.ningpai.customer.bean.Customer#delFlag}
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
     * 获取loginTime的值
     *
     * @param {@link com.ningpai.customer.bean.Customer#loginTime}
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
     * @param {@link com.ningpai.customer.bean.Customer#createTime}
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
     * @param {@link com.ningpai.customer.bean.Customer#modifiedTime}
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
     * 获取delTime的值
     *
     * @param {@link com.ningpai.customer.bean.Customer#delTime}
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

    /**
     * 获取验证码失效时间
     * */
    public Date getAeadTime() {
        if (this.aeadTime != null) {
            return new Date(this.aeadTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置验证码失效时间
     * */
    public void setAeadTime(Date aeadTime) {
        if (aeadTime != null) {
            Date tEmp = (Date) aeadTime.clone();
            if (tEmp != null) {
                this.aeadTime = tEmp;
            }
        }
    }

    public String getIsSiteManager() {
        return isSiteManager;
    }

    public void setIsSiteManager(String isSiteManager) {
        this.isSiteManager = isSiteManager;
    }

    public Long getPointLevelId() {
        return pointLevelId;
    }

    public void setPointLevelId(Long pointLevelId) {
        this.pointLevelId = pointLevelId;
    }
}
