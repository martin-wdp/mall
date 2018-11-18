package com.ningpai.site.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员消费记录
 * 
 * @author NINGPAI-wanghy
 * @since 2013年12月17日 下午4:19:28
 * @version
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
     * 获取创建时间
     * @return
     */
    public Date getCreateTime() {
        //创建时间不为空
        if (this.createTime != null) {
            //转码
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        //如果不为空
        if (createTime != null) {
            //获取创建时间
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                //赋值
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取修改时间
     * @return
     */
    public Date getModifiedTime() {
        //如果修改时间不为空
        if (this.modifiedTime != null) {
            //返回修改时间
            return new Date(this.modifiedTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * @param modifiedTime
     */
    public void setModifiedTime(Date modifiedTime) {
        //如果修改时间不为空
        if (modifiedTime != null) {
            //获取修改时间
            Date tEmp = (Date) modifiedTime.clone();
            if (tEmp != null) {
                this.modifiedTime = tEmp;
            }
        }
    }

    /**
     * 获取删除时间
     * @return
     */
    public Date getDelTime() {
        //如果删除时间不为空
        if (this.delTime != null) {
            //返回删除时间
            return new Date(this.delTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置删除世间
     * @param delTime
     */
    public void setDelTime(Date delTime) {
        //如果删除时间不为空
        if (delTime != null) {
            //获取删除时间
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

}
