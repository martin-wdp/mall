package com.ningpai.businesscircle.bean;

import java.util.Date;

/**
 * 商圈实体类
 * 
 * @author ggn
 *
 */
public class BusinessCircle {
    /*
     * 商圈ID
     */
    private Long businessCircleId;
    /*
     * 商圈名称 --
     */
    private String businessCircleName;
    /*
     * 商圈所在省 --
     */
    private Long businessCircleProvinceId;
    /*
     * 商圈所在市 --
     */
    private Long businessCircleCityId;
    /*
     * 商圈所属商家
     */
    private Long businessCircleThirdId;
    /*
     * 商圈备注 --
     */
    private String businessCircleRemark;
    /*
     * 创建时间
     */
    private Date businessCircleCreateTime;
    /*
     * 修改时间
     */
    private Date businessCircleModifyTime;
    /*
     * 是否删除 0 正常 1删除
     */
    private String businessCircleDelFlag;
    /*
     * 是否开启 0不开启 1开启 --
     */
    private String businessCircleIsOpen;
    /*
     * 商圈所属商家
     */
    private String storeName;
    /*
     * 商圈所在的省
     */
    private String provinceName;
    /*
     * 商圈所在的市
     */
    private String cityName;

    /*
     * 在商家审核弹窗中，显示所属商圈下拉：内容是： businessCircleThirdId 为null businessCircleDelFlag
     * 不删除 businessCircleIsOpen 开启
     */

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getBusinessCircleId() {
        return businessCircleId;
    }

    public void setBusinessCircleId(Long businessCircleId) {
        this.businessCircleId = businessCircleId;
    }

    public String getBusinessCircleName() {
        return businessCircleName;
    }

    public void setBusinessCircleName(String businessCircleName) {
        this.businessCircleName = businessCircleName;
    }

    public Long getBusinessCircleProvinceId() {
        return businessCircleProvinceId;
    }

    public void setBusinessCircleProvinceId(Long businessCircleProvinceId) {
        this.businessCircleProvinceId = businessCircleProvinceId;
    }

    public Long getBusinessCircleCityId() {
        return businessCircleCityId;
    }

    public void setBusinessCircleCityId(Long businessCircleCityId) {
        this.businessCircleCityId = businessCircleCityId;
    }

    public Long getBusinessCircleThirdId() {
        return businessCircleThirdId;
    }

    public void setBusinessCircleThirdId(Long businessCircleThirdId) {
        this.businessCircleThirdId = businessCircleThirdId;
    }

    public String getBusinessCircleRemark() {
        return businessCircleRemark;
    }

    public void setBusinessCircleRemark(String businessCircleRemark) {
        this.businessCircleRemark = businessCircleRemark;
    }
    /**
     * 获取BusinessCircleCreateTime
     * */
    public Date getBusinessCircleCreateTime() {
        return (Date) businessCircleCreateTime.clone();
    }
    /**
     * 设置BusinessCircleCreateTime
     * */
    public void setBusinessCircleCreateTime(Date businessCircleCreateTime) {
        this.businessCircleCreateTime = businessCircleCreateTime == null ? null : (Date) businessCircleCreateTime.clone();
    }
    /**
     * 获取BusinessCircleModifyTime
     * */
    public Date getBusinessCircleModifyTime() {
        return (Date) businessCircleModifyTime.clone();
    }
    /**
     * 设置BusinessCircleModifyTime
     * */
    public void setBusinessCircleModifyTime(Date businessCircleModifyTime) {
        this.businessCircleModifyTime = businessCircleModifyTime == null ? null : (Date) businessCircleModifyTime.clone();
    }

    public String getBusinessCircleDelFlag() {
        return businessCircleDelFlag;
    }

    public void setBusinessCircleDelFlag(String businessCircleDelFlag) {
        this.businessCircleDelFlag = businessCircleDelFlag;
    }

    public String getBusinessCircleIsOpen() {
        return businessCircleIsOpen;
    }

    public void setBusinessCircleIsOpen(String businessCircleIsOpen) {
        this.businessCircleIsOpen = businessCircleIsOpen;
    }
}
