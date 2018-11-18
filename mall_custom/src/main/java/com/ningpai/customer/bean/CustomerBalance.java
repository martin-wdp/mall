package com.ningpai.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 会员余额记录
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午4:19:28
 * @version
 */
public class CustomerBalance {
    // 余额编号
    private Long balanceId;
    // 会员编号
    private Long customerId;
    // 余额值
    private BigDecimal balanceNum;
    // 备注
    private String balanceRemark;
    // 类型
    private String balanceType;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifiedTime;
    // 删除时间
    private Date delTime;
    // 删除标记
    private String delFlag;

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
     *设置取创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
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
            Date timeTemp = (Date) modifiedTime.clone();
            if (timeTemp != null) {
                this.modifiedTime = timeTemp;
            }
        }
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
            Date timeTemp = (Date) delTime.clone();
            if (timeTemp != null) {
                this.delTime = timeTemp;
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
