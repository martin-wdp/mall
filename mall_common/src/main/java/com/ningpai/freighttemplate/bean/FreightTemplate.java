package com.ningpai.freighttemplate.bean;


import java.util.Date;
import java.util.List;

/**
 * 运费模板实体
 * @author ggn
 *
 */
public class FreightTemplate {
    /*
     *模板ID
     */
    private Long freightTemplateId;
    /*
     *模板名称
     */
    private String freightTemplateName;
    /*
     *城市ID
     */
    private Long freightCityId;
    /*
     *省ID
     */
    private Long freightProvinceId;
    /*
     *区县ID
     */
    private Long freightCountyId;
    /*
     *是否包邮  0 否 1是
     */
    private String freightPackageMail;
    /*
     *计价方式  0 按件  1按重量
     */
    private String freightMethods;
    /*
     *区域限售 0不支持 1支持
     */
    private String freightRestriArea;
    /*
     *是否默认  0不默认 1默认
     */
    private String freightIsDefault;
    /*
     *是否删除  0正常 1删除
     */
    private String freightDelFlag;
    /*
     *是否是第三方
     */
    private Long freightThirdId;
    /*
     *创建时间
     */
    private Date freightCreateTime;
    /*
     *修改时间
     */
    private Date freightModifyTime;
    /*
     *模板备注
     */
    private String freightRemark;
    /*
     *是否允许删除
     */
    private String freightNoDelete;
    /*
     *运费运送方式实体类
     */
    private List<FreightExpress> freightExpressList;

    
    public List<FreightExpress> getFreightExpressList() {
        return freightExpressList;
    }

    public void setFreightExpressList(List<FreightExpress> freightExpressList) {
        this.freightExpressList = freightExpressList;
    }

    public Long getFreightTemplateId() {
        return freightTemplateId;
    }

    public void setFreightTemplateId(Long freightTemplateId) {
        this.freightTemplateId = freightTemplateId;
    }

    public String getFreightTemplateName() {
        return freightTemplateName;
    }

    public void setFreightTemplateName(String freightTemplateName) {
        this.freightTemplateName = freightTemplateName;
    }

    public Long getFreightCityId() {
        return freightCityId;
    }

    public void setFreightCityId(Long freightCityId) {
        this.freightCityId = freightCityId;
    }

    public Long getFreightProvinceId() {
        return freightProvinceId;
    }

    public void setFreightProvinceId(Long freightProvinceId) {
        this.freightProvinceId = freightProvinceId;
    }

    public Long getFreightCountyId() {
        return freightCountyId;
    }

    public void setFreightCountyId(Long freightCountyId) {
        this.freightCountyId = freightCountyId;
    }

    public String getFreightPackageMail() {
        return freightPackageMail;
    }

    public void setFreightPackageMail(String freightPackageMail) {
        this.freightPackageMail = freightPackageMail;
    }

    public String getFreightMethods() {
        return freightMethods;
    }

    public void setFreightMethods(String freightMethods) {
        this.freightMethods = freightMethods;
    }

    public String getFreightRestriArea() {
        return freightRestriArea;
    }

    public void setFreightRestriArea(String freightRestriArea) {
        this.freightRestriArea = freightRestriArea;
    }

    public String getFreightIsDefault() {
        return freightIsDefault;
    }

    public void setFreightIsDefault(String freightIsDefault) {
        this.freightIsDefault = freightIsDefault;
    }

    public String getFreightDelFlag() {
        return freightDelFlag;
    }

    public void setFreightDelFlag(String freightDelFlag) {
        this.freightDelFlag = freightDelFlag;
    }

    public Long getFreightThirdId() {
        return freightThirdId;
    }

    public void setFreightThirdId(Long freightThirdId) {
        this.freightThirdId = freightThirdId;
    }
    /**
     * 获取创建时间
     * */
    public Date getFreightCreateTime() {
        return  this.freightCreateTime ==null?null:(Date) freightCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setFreightCreateTime(Date freightCreateTime) {
        this.freightCreateTime = freightCreateTime==null?null: (Date) freightCreateTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getFreightModifyTime() {
        return this.freightModifyTime == null ? null : (Date) freightModifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setFreightModifyTime(Date freightModifyTime) {
        this.freightModifyTime = freightModifyTime==null?null: (Date) freightModifyTime.clone();
    }

    public String getFreightRemark() {
        return freightRemark;
    }

    public void setFreightRemark(String freightRemark) {
        this.freightRemark = freightRemark;
    }

    public String getFreightNoDelete() {
        return freightNoDelete;
    }

    public void setFreightNoDelete(String freightNoDelete) {
        this.freightNoDelete = freightNoDelete;
    }
}
