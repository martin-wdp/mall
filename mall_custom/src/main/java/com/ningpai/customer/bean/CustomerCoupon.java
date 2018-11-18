package com.ningpai.customer.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠劵信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午4:22:55
 * @version 0.0.1
 */
public class CustomerCoupon implements Serializable {
    /**
     * 序列化
     */
    private static final long serialVersionUID = -4055488745533476586L;
    // 优惠劵编号
    private Long couponId;
    // 会员编号
    private Long customerId;
    // 优惠劵类型
    private Integer couponType;
    // 优惠劵编号
    private String couponNo;
    // 是否使用
    private String isUsed;
    // 创建时间
    private Date createTime;
    // 删除标记
    private String delFlag;
    // 订单编号
    private String orderNo;
    // 满减额
    private BigDecimal reductionPrice;
    // 满额
    private BigDecimal fullPrice;
    // 直降额
    private BigDecimal downPrice;
    // 类型
    private String ruleType;
    // 优惠券状态
    private String codeStatus;

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public BigDecimal getReductionPrice() {
        return reductionPrice;
    }

    public void setReductionPrice(BigDecimal reductionPrice) {
        this.reductionPrice = reductionPrice;
    }

    public BigDecimal getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }

    public BigDecimal getDownPrice() {
        return downPrice;
    }

    public void setDownPrice(BigDecimal downPrice) {
        this.downPrice = downPrice;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer couponType) {
        this.couponType = couponType;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getIsUsed() {
        return isUsed;
    }

    public void setIsUsed(String isUsed) {
        this.isUsed = isUsed;
    }
    /**
     *获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     *设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }

    public String getDelFlag() {

        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
