package com.ningpai.thirdaudit.bean;

import java.util.Date;
/**
 * 品牌实体类
 *
 * */
public class GrandBrand {
    private Long id;

    private Long thirdId;

    private Long brandId;

    private String delFlag;

    private Date modifyTime;

    private String rateStatus;

    private Date rateTime;

    private String reason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRateStatus() {
        return rateStatus;
    }

    public void setRateStatus(String rateStatus) {
        this.rateStatus = rateStatus;
    }

    public Date getRateTime() {
        return rateTime;
    }

    public void setRateTime(Date rateTime) {
        this.rateTime = rateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
