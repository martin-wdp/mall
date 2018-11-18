package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 货币设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 13:17:21
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("currencyConf")
public class CurrencyConf implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getCurrencyId()
     * @see #setCurrencyId(int)
     */
    private int currencyId;
    /**
     * 货币名称
     * 
     * @see #getCurrencyName()
     * @see #setCurrencyName(String)
     */
    private String currencyName;
    /**
     * 货币符号
     * 
     * @see #getSymbol()
     * @see #setSymbol(String)
     */
    private String symbol;
    /**
     * 汇率
     * 
     * @see #getExchangeRate()
     * @see #setExchangeRate(double)
     */
    private double exchangeRate;
    /**
     * 货币代码
     * 
     * @see #getCurrencyCode()
     * @see #setCurrencyCode(String)
     */
    private String currencyCode;
    /**
     * 默认货币
     * 
     * @see #getDefaultCurrency()
     * @see #setDefaultCurrency(String)
     */
    private String defaultCurrency;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 扩展字段1
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 扩展字段2
     * 
     * @see #getExpFleid2()
     * @see #setExpFleid2(String)
     */
    private String expFleid2;
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
     * 删除标识
     * 
     * @see #getDeleteStatus()
     * @see #setDeleteStatus(int)
     */
    private int deleteStatus;

    /**
     * 取得：记录ID
     * 
     * @return int 记录ID {@link CurrencyConf#currencyId}
     */
    public final int getCurrencyId() {
        return this.currencyId;
    }

    /**
     * 设置：记录ID
     * 
     * @param currencyId
     *            记录ID
     */
    public final void setCurrencyId(final int currencyId) {
        this.currencyId = currencyId;
    }

    /**
     * 取得：货币名称
     * 
     * @return String 货币名称 {@link CurrencyConf#currencyName}
     */
    public final String getCurrencyName() {
        return this.currencyName;
    }

    /**
     * 设置：货币名称
     * 
     * @param currencyName
     *            货币名称
     */
    public final void setCurrencyName(final String currencyName) {
        this.currencyName = currencyName;
    }

    /**
     * 取得：货币符号
     * 
     * @return String 货币符号 {@link CurrencyConf#symbol}
     */
    public final String getSymbol() {
        return this.symbol;
    }

    /**
     * 设置：货币符号
     * 
     * @param symbol
     *            货币符号
     */
    public final void setSymbol(final String symbol) {
        this.symbol = symbol;
    }

    /**
     * 取得：汇率
     * 
     * @return double 汇率 {@link CurrencyConf#exchangeRate}
     */
    public final double getExchangeRate() {
        return this.exchangeRate;
    }

    /**
     * 设置：汇率
     * 
     * @param exchangeRate
     *            汇率
     */
    public final void setExchangeRate(final double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    /**
     * 取得：货币代码
     * 
     * @return String 货币代码 {@link CurrencyConf#currencyCode}
     */
    public final String getCurrencyCode() {
        return this.currencyCode;
    }

    /**
     * 设置：货币代码
     * 
     * @param currencyCode
     *            货币代码
     */
    public final void setCurrencyCode(final String currencyCode) {
        this.currencyCode = currencyCode;
    }

    /**
     * 取得：默认货币
     * 
     * @return String 默认货币 {@link CurrencyConf#defaultCurrency}
     */
    public final String getDefaultCurrency() {
        return this.defaultCurrency;
    }

    /**
     * 设置：默认货币
     * 
     * @param defaultCurrency
     *            默认货币
     */
    public final void setDefaultCurrency(final String defaultCurrency) {
        this.defaultCurrency = defaultCurrency;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link CurrencyConf#usedStatus}
     */
    public final String getUsedStatus() {
        return this.usedStatus;
    }

    /**
     * 设置：启用
     * 
     * @param usedStatus
     *            启用
     */
    public final void setUsedStatus(final String usedStatus) {
        this.usedStatus = usedStatus;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link CurrencyConf#expFleid1}
     */
    public final String getExpFleid1() {
        return this.expFleid1;
    }

    /**
     * 设置：扩展字段1
     * 
     * @param expFleid1
     *            扩展字段1
     */
    public final void setExpFleid1(final String expFleid1) {
        this.expFleid1 = expFleid1;
    }

    /**
     * 取得：扩展字段2
     * 
     * @return String 扩展字段2 {@link CurrencyConf#expFleid2}
     */
    public final String getExpFleid2() {
        return this.expFleid2;
    }

    /**
     * 设置：扩展字段2
     * 
     * @param expFleid2
     *            扩展字段2
     */
    public final void setExpFleid2(final String expFleid2) {
        this.expFleid2 = expFleid2;
    }

    /**
     * 取得：添加人
     * 
     * @return int 添加人 {@link CurrencyConf#insertId}
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
     * @return Date 添加时间 {@link CurrencyConf#insertDate}
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
     * @return int 修改人 {@link CurrencyConf#modifyId}
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
     * @return Date 修改时间 {@link CurrencyConf#modifyDate}
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
     * 取得：删除标识
     * 
     * @return int 删除标识 {@link CurrencyConf#deleteStatus}
     */
    public final int getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置：删除标识
     * 
     * @param deleteStatus
     *            删除标识
     */
    public final void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 货币设置对象简明
     * 
     * @return 货币设置对象简明
     */
    public final String toString() {
        return "货币设置[" + this.currencyId + "_" + this.currencyName + "]";
    }
}
