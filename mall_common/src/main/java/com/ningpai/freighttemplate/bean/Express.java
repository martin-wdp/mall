package com.ningpai.freighttemplate.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家物流信息
 */
public class Express {
    /**
     *商家物流编号
     */
    private Long shoreExpId;
    /**
     *商家编号
     */
    private Long storeId;
    /**
     *物流名称
     */
    private String expName;
    /**
     *物流价格
     */
    private BigDecimal expPrice;
    /**
     *物流公司
     */
    private String expCompany;
    /**
     *是否默认 0否 1是
     */
    private String isDefault;
    /**
     *创建时间
     */
    private Date seCreateTime;
    /**
     *修改时间
     */
    private Date seModTime;
    /**
     *删除时间
     */
    private Date seDelTime;
    /**
     *删除标记 0否 1是
     */
    private String delFlag;
    /**
     * 快递100
     */
    private String kudi100code;

    public String getKudi100code() {
        return kudi100code;
    }

    public void setKudi100code(String kudi100code) {
        this.kudi100code = kudi100code;
    }

    public Long getShoreExpId() {
        return shoreExpId;
    }

    public void setShoreExpId(Long shoreExpId) {
        this.shoreExpId = shoreExpId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public String getExpName() {
        return expName;
    }

    public void setExpName(String expName) {
        this.expName = expName;
    }

    public BigDecimal getExpPrice() {
        return expPrice;
    }

    public void setExpPrice(BigDecimal expPrice) {
        this.expPrice = expPrice;
    }

    public String getExpCompany() {
        return expCompany;
    }

    public void setExpCompany(String expCompany) {
        this.expCompany = expCompany;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    /**
     * 获取创建时间
     * @return
     */
    public Date getSeCreateTime() {
        if (this.seCreateTime != null) {
            return new Date(this.seCreateTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置创建时间
     * @param seCreateTime
     */
    public void setSeCreateTime(Date seCreateTime) {
        if (seCreateTime != null) {
            Date tEmp = (Date) seCreateTime.clone();
            if (tEmp != null) {
                this.seCreateTime = tEmp;
            }
        }
    }

    /**
     * 获取修改时间
     * @return
     */
    public Date getSeModTime() {
        if (this.seModTime != null) {
            return new Date(this.seModTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改时间
     * @param seModTime
     */
    public void setSeModTime(Date seModTime) {
        if (seModTime != null) {
            Date tEmp = (Date) seModTime.clone();
            if (tEmp != null) {
                this.seModTime = tEmp;
            }
        }
    }

    /**
     * 获取删除时间
     * @return
     */
    public Date getSeDelTime() {
        if (this.seDelTime != null) {
            return new Date(this.seDelTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置删除时间
     * @param seDelTime
     */
    public void setSeDelTime(Date seDelTime) {
        if (seDelTime != null) {
            Date tEmp = (Date) seDelTime.clone();
            if (tEmp != null) {
                this.seDelTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 设置删除时间
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
