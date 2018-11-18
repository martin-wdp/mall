package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 价格精度设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("pricePrecisionCof")
public class PricePrecisionCof implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getPricePreConfId()
     * @see #setPricePreConfId(int)
     */
    private int pricePreConfId;
    /**
     * 设置方案标题
     * 
     * @see #getConfTitle()
     * @see #setConfTitle(String)
     */
    private String confTitle;
    /**
     * 设置方案代码
     * 
     * @see #getConfCode()
     * @see #setConfCode(String)
     */
    private String confCode;
    /**
     * 保留位数
     * 
     * @see #getDecimalDigits()
     * @see #setDecimalDigits(String)
     */
    private String decimalDigits;
    /** 保留位数显示值 */
    private String decimalDigitsValue;
    /**
     * 取整方式
     * 
     * @see #getGetIntegerStyle()
     * @see #setGetIntegerStyle(String)
     */
    private String getIntegerStyle;
    /** 取整方式显示值 */
    private String getIntegerStyleValue;
    /**
     * 默认方式
     * 
     * @see #getDefalutStatus()
     * @see #setDefalutStatus(String)
     */
    private String defalutStatus;
    /**
     * 添加人
     * 
     * @see #getInsertId()
     * @see #setInsertId(int)
     */
    private int insertId;
    /**
     * 添加时间
     * 
     * @see #getInsertDate()
     * @see #setInsertDate(java.util.Date)
     */
    private Date insertDate;
    /**
     * 修改人
     * 
     * @see #getModifyId()
     * @see #setModifyId(int)
     */
    private int modifyId;
    /**
     * 修改时间
     * 
     * @see #getModifyDate()
     * @see #setModifyDate(java.util.Date)
     */
    private Date modifyDate;

    /**
     * 取得：记录ID
     * 
     * @return int 记录ID {@link PricePrecisionCof#pricePreConfId}
     */
    public final int getPricePreConfId() {
        return this.pricePreConfId;
    }

    /**
     * 设置：记录ID
     * 
     * @param pricePreConfId
     *            记录ID
     */
    public final void setPricePreConfId(final int pricePreConfId) {
        this.pricePreConfId = pricePreConfId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link PricePrecisionCof#confTitle}
     */
    public final String getConfTitle() {
        return this.confTitle;
    }

    /**
     * 设置：设置方案标题
     * 
     * @param confTitle
     *            设置方案标题
     */
    public final void setConfTitle(final String confTitle) {
        this.confTitle = confTitle;
    }

    /**
     * 取得：设置方案代码
     * 
     * @return String 设置方案代码 {@link PricePrecisionCof#confCode}
     */
    public final String getConfCode() {
        return this.confCode;
    }

    /**
     * 设置：设置方案代码
     * 
     * @param confCode
     *            设置方案代码
     */
    public final void setConfCode(final String confCode) {
        this.confCode = confCode;
    }

    /**
     * 取得：保留位数
     * 
     * @return String 保留位数 {@link PricePrecisionCof#decimalDigits}
     */
    public final String getDecimalDigits() {
        return this.decimalDigits;
    }

    /**
     * 设置：保留位数
     * 
     * @param decimalDigits
     *            保留位数
     */
    public final void setDecimalDigits(final String decimalDigits) {
        this.decimalDigits = decimalDigits;
    }

    /**
     * 取得：保留位数显示值
     */
    public final String getDecimalDigitsValue() {
        return this.decimalDigitsValue;
    }

    /**
     * 设置：保留位数显示值
     */
    public final void setDecimalDigitsValue(String decimalDigitsValue) {
        this.decimalDigitsValue = decimalDigitsValue;
    }

    /**
     * 取得：取整方式
     * 
     * @return String 取整方式 {@link PricePrecisionCof#getIntegerStyle}
     */
    public final String getGetIntegerStyle() {
        return this.getIntegerStyle;
    }

    /**
     * 设置：取整方式
     * 
     * @param getIntegerStyle
     *            取整方式
     */
    public final void setGetIntegerStyle(final String getIntegerStyle) {
        this.getIntegerStyle = getIntegerStyle;
    }

    /**
     * 取得：取整方式显示值
     */
    public final String getGetIntegerStyleValue() {
        return this.getIntegerStyleValue;
    }

    /**
     * 设置：取整方式显示值
     */
    public final void setGetIntegerStyleValue(String getIntegerStyleValue) {
        this.getIntegerStyleValue = getIntegerStyleValue;
    }

    /**
     * 取得：默认方式
     * 
     * @return String 默认方式 {@link PricePrecisionCof#defalutStatus}
     */
    public final String getDefalutStatus() {
        return this.defalutStatus;
    }

    /**
     * 设置：默认方式
     * 
     * @param defalutStatus
     *            默认方式
     */
    public final void setDefalutStatus(final String defalutStatus) {
        this.defalutStatus = defalutStatus;
    }

    /**
     * 取得：添加人
     * 
     * @return int 添加人 {@link PricePrecisionCof#insertId}
     */
    public final int getInsertId() {
        return this.insertId;
    }

    /**
     * 设置：添加人
     * 
     * @param insertId
     *            添加人
     */
    public final void setInsertId(final int insertId) {
        this.insertId = insertId;
    }

    /**
     * 取得：添加时间
     * 
     * @return Date 添加时间 {@link PricePrecisionCof#insertDate}
     */
    public final Date getInsertDate() {
        if (this.insertDate != null) {
            return new Date(this.insertDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：添加时间
     * 
     * @param insertDate
     *            添加时间
     */
    public final void setInsertDate(final Date insertDate) {
        if (insertDate != null) {
            Date tEmp = (Date) insertDate.clone();
            if (tEmp != null) {
                this.insertDate = tEmp;
            }
        }
    }

    /**
     * 取得：修改人
     * 
     * @return int 修改人 {@link PricePrecisionCof#modifyId}
     */
    public final int getModifyId() {
        return this.modifyId;
    }

    /**
     * 设置：修改人
     * 
     * @param modifyId
     *            修改人
     */
    public final void setModifyId(final int modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 取得：修改时间
     * 
     * @return Date 修改时间 {@link PricePrecisionCof#modifyDate}
     */
    public final Date getModifyDate() {
        if (this.modifyDate != null) {
            return new Date(this.modifyDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：修改时间
     * 
     * @param modifyDate
     *            修改时间
     */
    public final void setModifyDate(final Date modifyDate) {
        if (modifyDate != null) {
            Date tEmp = (Date) modifyDate.clone();
            if (tEmp != null) {
                this.modifyDate = tEmp;
            }
        }
    }

    /**
     * 价格精度设置对象简明
     * 
     * @return 价格精度设置对象简明
     */
    public final String toString() {
        return "价格精度设置[" + this.pricePreConfId + "_" + this.confTitle + "]";
    }
}
