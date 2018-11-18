package com.ningpai.freighttemplate.bean;


import java.math.BigDecimal;
import java.util.List;

/**
 * 运费运送方式实体类
 * @author ggn
 *
 */
public class FreightExpress {
    /*
     *运送ID
     */
    private Long distributionId;
    /*
     * 模板ID
     */
    private Long freightTemplateId;
    /*
     *快递公司ＩＤ
     */
    private Long logComId;
    /*
     *初始数
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
     *运送地区配置
     */
    private List<FreightExpressAll> freightExpressAll;
    /*
     *物流公司
     */
    private SysLogisticsCompany sysLogisticsCompany;
    /*
     *商家物流信息
     */
    private Express express;
    /*
     *运费模板实体
     */
    private FreightTemplate freightTemplate;

    public Express getExpress() {
        return express;
    }

    public void setExpress(Express express) {
        this.express = express;
    }

    public SysLogisticsCompany getSysLogisticsCompany() {
        return sysLogisticsCompany;
    }

    public void setSysLogisticsCompany(SysLogisticsCompany sysLogisticsCompany) {
        this.sysLogisticsCompany = sysLogisticsCompany;
    }

    public List<FreightExpressAll> getFreightExpressAll() {
        return freightExpressAll;
    }

    public void setFreightExpressAll(List<FreightExpressAll> freightExpressAll) {
        this.freightExpressAll = freightExpressAll;
    }

    public Long getDistributionId() {
        return distributionId;
    }

    public void setDistributionId(Long distributionId) {
        this.distributionId = distributionId;
    }

    public Long getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Long freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public Long getLogComId() {
        return logComId;
    }

    public void setLogComId(Long logComId) {
        this.logComId = logComId;
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

    public FreightTemplate getFreightTemplate() {
        return freightTemplate;
    }

    public void setFreightTemplate(FreightTemplate freightTemplate) {
        this.freightTemplate = freightTemplate;
    }
}
