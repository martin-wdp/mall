/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 会员充值消费Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月21日 下午2:39:15
 * @version 0.0.1
 */
public class CustomerConsume {
    /**
     * 余额编号
     * 
     * @see #getBalanceId()
     * @see #setBalanceId(Long)
     */
    private Long balanceId;
    /**
     * 会与编号
     * 
     * @see #getCustomerId()
     * @see #setCustomerId(Long)
     */
    private Long customerId;
    /**
     * 金额
     * 
     * @see #getBalanceNum()
     * @see #setBalanceNum(BigDecimal)
     */
    private BigDecimal balanceNum;
    // 支付方式
    private String payType;

    /**
     * 备注
     * 
     * @see #getBalanceRemark()
     * @see #setBalanceRemark(String)
     */
    private String balanceRemark;
    /**
     * 类型
     * 
     * @see #getBalanceType()
     * @see #setBalanceType(String)
     */
    private String balanceType;
    /**
     * 创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 删除标记
     * 
     * @see #getDelFlag()
     * @see #setDelFlag(String)
     */
    private String delFlag;
    /**
     * 创建时间到
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTimeTo;

    /**
     * 会员名称
     * 
     * @see #getCustomerUsername()
     * @see #setCustomerUsername(String)
     */
    @Pattern(regexp = "[^\\<\\>]*")
    private String customerUsername;
    // 订单编号
    private String orderNo;

    /**
     * 企业公司名称
     * @return
     */
    private String companyName;

    /**
     * 用户昵称
     * @return
     */
    private  String customerNickname;

    public String getCustomerNickname() {
        return customerNickname;
    }

    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取customerUsername的值
     * 
     * @return customerUsername
     *         {@link com.ningpai.customer.bean.CustomerPoint#customerUsername}
     */
    public String getCustomerUsername() {
        return customerUsername;
    }

    /**
     * 设置customerUsername的值
     * 
     * @param customerUsername
     *            {@link java.lang.String}
     */
    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 获取balanceId的值
     * 
     * @param balanceId
     *            {@link com.ningpai.customer.bean.CustomerConsume#balanceId}
     */
    public Long getBalanceId() {
        return balanceId;
    }

    /**
     * 设置balanceId的值
     * 
     * @return balanceId
     *         {@link com.ningpai.customer.bean.CustomerConsume#balanceId}
     */
    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    /**
     * 获取customerId的值
     * 
     * @param customerId
     *            {@link com.ningpai.customer.bean.CustomerConsume#customerId}
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 设置customerId的值
     * 
     * @return customerId
     *         {@link com.ningpai.customer.bean.CustomerConsume#customerId}
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取balanceNum的值
     * 
     * @param balanceNum
     *            {@link com.ningpai.customer.bean.CustomerConsume#balanceNum}
     */
    public BigDecimal getBalanceNum() {
        return balanceNum;
    }

    /**
     * 设置balanceNum的值
     * 
     * @return balanceNum
     *         {@link com.ningpai.customer.bean.CustomerConsume#balanceNum}
     */
    public void setBalanceNum(BigDecimal balanceNum) {
        this.balanceNum = balanceNum;
    }

    /**
     * 获取balanceRemark的值
     * 
     * @param balanceRemark
     *            {@link com.ningpai.customer.bean.CustomerConsume#balanceRemark}
     */
    public String getBalanceRemark() {
        return balanceRemark;
    }

    /**
     * 设置balanceRemark的值
     * 
     * @return balanceRemark
     *         {@link com.ningpai.customer.bean.CustomerConsume#balanceRemark}
     */
    public void setBalanceRemark(String balanceRemark) {
        this.balanceRemark = balanceRemark;
    }

    /**
     * 获取balanceType的值
     * 
     * @param balanceType
     *            {@link com.ningpai.customer.bean.CustomerConsume#balanceType}
     */
    public String getBalanceType() {
        return balanceType;
    }

    /**
     * 设置balanceType的值
     * 
     * @return balanceType
     *         {@link com.ningpai.customer.bean.CustomerConsume#balanceType}
     */
    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * 获取createTime的值
     * 
     * @param createTime
     *            {@link com.ningpai.customer.bean.CustomerConsume#createTime}
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
     * @return createTime
     *         {@link com.ningpai.customer.bean.CustomerConsume#createTime}
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    /**
     * 获取createTimeTo的值
     * 
     * @param createTimeTo
     *            {@link com.ningpai.customer.bean.CustomerConsume#createTimeTo}
     */
    public Date getCreateTimeTo() {
        if (this.createTimeTo != null) {
            return new Date(this.createTimeTo.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置createTimeTo的值
     * 
     * @return createTimeTo
     *         {@link com.ningpai.customer.bean.CustomerConsume#createTimeTo}
     */
    public void setCreateTimeTo(Date createTimeTo) {
        if (createTimeTo != null) {
            Date timeTemp = (Date) createTimeTo.clone();
            if (timeTemp != null) {
                this.createTimeTo = timeTemp;
            }
        }
    }

    /**
     * 获取delFlag的值
     * 
     * @param delFlag
     *            {@link com.ningpai.customer.bean.CustomerConsume#delFlag}
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置delFlag的值
     * 
     * @return delFlag {@link com.ningpai.customer.bean.CustomerConsume#delFlag}
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }
}
