package com.ningpai.freighttemplate.bean;


import java.math.BigDecimal;
/**
 * 运送地区配置
 * @author ggn
 *
 */
public class FreightExpressAll {
    /*
     *运送地区ID
     */
    private Long expressAreaId;
    /*
     *运送地区城市ID 字符串  逗号隔开
     */
    private String expressArea;
    /*
     *运送外键ID
     */
    private Long distributionId;
    /*
     *开始数
     */
    private Long expressStart;
    /*
     *价格
     */
    private BigDecimal expressPostage;
    /*
     *续数
     */
    private Long expressPlusN1;
    /*
     *价格
     */
    private BigDecimal expressPostageplus;
    /*
     *是否删除
     */
    private String expressDelflag;
    /*
     *所有城市名称
     */
    private String allCityName;
    
    public String getAllCityName() {
        return allCityName;
    }

    public void setAllCityName(String allCityName) {
        this.allCityName = allCityName;
    }

    public Long getExpressAreaId() {
        return expressAreaId;
    }

    public void setExpressAreaId(Long expressAreaId) {
        this.expressAreaId = expressAreaId;
    }

    public String getExpressArea() {
        return expressArea;
    }

    public void setExpressArea(String expressArea) {
        this.expressArea = expressArea;
    }

    public Long getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(Long distributionId) {
        this.distributionId = distributionId;
    }

    public Long getExpressStart() {
        return expressStart;
    }

    public void setExpressStart(Long expressStart) {
        this.expressStart = expressStart;
    }

    public BigDecimal getExpressPostage() {
        return expressPostage;
    }

    public void setExpressPostage(BigDecimal expressPostage) {
        this.expressPostage = expressPostage;
    }

    public Long getExpressPlusN1() {
        return expressPlusN1;
    }

    public void setExpressPlusN1(Long expressPlusN1) {
        this.expressPlusN1 = expressPlusN1;
    }

    public BigDecimal getExpressPostageplus() {
        return expressPostageplus;
    }

    public void setExpressPostageplus(BigDecimal expressPostageplus) {
        this.expressPostageplus = expressPostageplus;
    }

    public String getExpressDelflag() {
        return expressDelflag;
    }

    public void setExpressDelflag(String expressDelflag) {
        this.expressDelflag = expressDelflag;
    }
}
