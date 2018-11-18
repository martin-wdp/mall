package com.ningpai.third.auth.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 会员消费记录
 * </p>
 * 
 * @author zhanghl
 * @since 2015.07.30
 * @version 2.0
 */
public class CustomerConsume {
    /**
     * 余额编号
     */
    private Long balanceId;
    /**
     * 会员编号
     */
    private Long customerId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 金额
     */
    private BigDecimal balanceNum;
    /**
     * 备注
     */
    private String balanceRemark;
    /**
     * 类型
     */
    private String balanceType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 删除时间
     */
    private Date delTime;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 支付类型
     */
    private String payType;
    /**
     * 充值人类型 0会员 ，1第三方商家，3供应商
     */
    private String isSeller;

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public Long getBalanceId() {
        return balanceId;
    }

    public void setBalanceId(Long balanceId) {
        this.balanceId = balanceId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getBalanceNum() {
        return balanceNum;
    }

    public void setBalanceNum(BigDecimal balanceNum) {
        this.balanceNum = balanceNum;
    }

    public String getBalanceRemark() {
        return balanceRemark;
    }

    public void setBalanceRemark(String balanceRemark) {
        this.balanceRemark = balanceRemark;
    }

    public String getBalanceType() {
        return balanceType;
    }

    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    /**
     * 获取创建日期
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建日期
     * @param createTime
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
     * 获取修改日期
     * @return
     */
    public Date getModifiedTime() {
        if (this.modifiedTime != null) {
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改日期
     * @param modifiedTime
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
     * 获取登陆日期
     * @return
     */
    public Date getDelTime() {
        if (this.delTime != null) {
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置删除日期
     * @param delTime
     */
    public void setDelTime(Date delTime) {
        if (delTime != null) {
            Date tEmp = (Date) delTime.clone();
            if (tEmp != null) {
                this.delTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(String isSeller) {
        this.isSeller = isSeller;
    }

}
