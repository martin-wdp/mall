package com.qpmall.vo;

/**
 * Created by pl on 2016/1/9.
 * Desc:
 */
public class AutoCarTypeVo {
    private String goodsBrandName;
    private String autoStyleSystem;
    private String autoStyleType;
    private String autoStyleEngine;
    private String autoStyleGearbox;
    private String autoStyleProductiveYear;

    public void setAutoStyleEngine(String autoStyleEngine) {
        this.autoStyleEngine = autoStyleEngine;
    }

    public String getAutoStyleEngine() {
        return autoStyleEngine;
    }

    public void setAutoStyleGearbox(String autoStyleGearbox) {
        this.autoStyleGearbox = autoStyleGearbox;
    }

    public String getAutoStyleGearbox() {
        return autoStyleGearbox;
    }

    public void setAutoStyleProductiveYear(String autoStyleProductiveYear) {
        this.autoStyleProductiveYear = autoStyleProductiveYear;
    }

    public String getAutoStyleProductiveYear() {
        return autoStyleProductiveYear;
    }

    public void setAutoStyleSystem(String autoStyleSystem) {
        this.autoStyleSystem = autoStyleSystem;
    }

    public String getAutoStyleSystem() {
        return autoStyleSystem;
    }

    public void setAutoStyleType(String autoStyleType) {
        this.autoStyleType = autoStyleType;
    }

    public String getAutoStyleType() {
        return autoStyleType;
    }

    public void setGoodsBrandName(String goodsBrandName) {
        this.goodsBrandName = goodsBrandName;
    }

    public String getGoodsBrandName() {
        return goodsBrandName;
    }
}
