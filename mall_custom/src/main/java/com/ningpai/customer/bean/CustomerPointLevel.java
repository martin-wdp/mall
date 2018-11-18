package com.ningpai.customer.bean;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Pattern;

/**
 * 会员等级信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月17日 下午4:21:50
 * @version
 */
public class CustomerPointLevel {
    // 等级编号
    private Long pointLelvelId;
    // 等级名称
    @Pattern(regexp = "[^\\<\\>]*")
    private String pointLevelName;
    // 所需积分
    @Pattern(regexp = "[0-9\\,]+")
    private String pointNeed;
    // 折扣
    private BigDecimal pointDiscount;
    // 是否默认
    private String isDefault;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifiedTime;
    // 删除时间
    private Date delTime;
    // 删除标记
    private String delFlag;

    public Long getPointLelvelId() {
        return pointLelvelId;
    }

    public void setPointLelvelId(Long pointLelvelId) {
        this.pointLelvelId = pointLelvelId;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
    }

    public String getPointNeed() {
        return pointNeed;
    }

    public void setPointNeed(String pointNeed) {
        this.pointNeed = pointNeed;
    }

    public BigDecimal getPointDiscount() {
        return pointDiscount;
    }

    public void setPointDiscount(BigDecimal pointDiscount) {
        this.pointDiscount = pointDiscount;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
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
     *设置修改时间
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
