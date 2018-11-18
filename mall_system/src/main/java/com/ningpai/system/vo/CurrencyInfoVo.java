package com.ningpai.system.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.ningpai.system.bean.CurrencyBase;

/**
 * 货币信息实体VO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:20:25
 * @version 1.0
 */
public class CurrencyInfoVo {
    /**
     * 编号
     */
    private Long id;
    /**
     * 货币基础信息
     */
    private CurrencyBase currencyBase;
    /**
     * 汇率
     */
    private BigDecimal exchangeRate;
    /**
     * 是否是默认货币<br>
     * 0 不是； 1 是
     */
    private String isDefault;
    /**
     * 是否删除<br>
     * 0 未删除； 1 已删除
     */
    private String delflag;
    /**
     * 创建人
     */
    private Long createUserId;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private Long updateUserId;
    /**
     * 更新时间
     */
    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CurrencyBase getCurrencyBase() {
        return currencyBase;
    }

    public void setCurrencyBase(CurrencyBase currencyBase) {
        this.currencyBase = currencyBase;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getDelflag() {
        return delflag;
    }

    public void setDelflag(String delflag) {
        this.delflag = delflag;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateDate() {
        if (this.createDate != null) {
            return new Date(this.createDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateDate(Date createDate) {
        if (createDate != null) {
            Date tEmp = (Date) createDate.clone();
            if (tEmp != null) {
                this.createDate = tEmp;
            }
        }
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    /**
     * 时间
     * @return
     */
    public Date getUpdateDate() {
        if (this.updateDate != null) {
            return new Date(this.updateDate.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setUpdateDate(Date updateDate) {
        if (updateDate != null) {
            Date tEmp = (Date) updateDate.clone();
            if (tEmp != null) {
                this.updateDate = tEmp;
            }
        }
    }
}
