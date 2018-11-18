package com.ningpai.system.bean;

/**
 * 货币基础信息实体
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:11:25
 * @version 1.0
 */
public class CurrencyBase {
    /**
     * 编号
     */
    private Long id;
    /**
     * 货币名称
     */
    private String name;
    /**
     * 国家或地区
     */
    private String country;
    /**
     * 货币符号
     */
    private String symbol;
    /**
     * 货币代码
     */
    private String currencyCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }
}
