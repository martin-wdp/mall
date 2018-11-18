package com.qpmall.bean;

import com.qpmall.vo.DetitleCarStyleBean;

import java.util.Date;
import java.util.Set;

/**
 * Created by pl on 2015/10/9.
 * Desc:
 */
public class QpAutoStyleBean implements Comparable<QpAutoStyleBean>{
    private String autoStyleIdLiyangId;
    private String autoStyleIdLiyangyasuoId;

    private String autoStyleMake;
    private String autoStyleBrandMake;

    private Integer goodsBrandId;

    private String goodsBrandName;

    private String autoStyleSystem;

    private String autoStyleType;

    private String autoStyleUpgrade;

    private String autoStyleChassis;

    private String autoStyleYear;

    private String autoStyleStopProduction;

    private String autoStyleEngine;

    private String autoStyleSweptVolume;

    private String autoStyleFuel;

    private String autoStylePower;

    private String autoStyleGearbox;

    private String autoStyleDrive;

    private Date autoStyleCreatetime;

    private Integer autoStyleIsdel;
    private String autoStyleProductiveYear;
    private String autoStyleSalesName;

    public void setAutoStyleBrandMake(String autoStyleBrandMake) {
        this.autoStyleBrandMake = autoStyleBrandMake;
    }

    public String getAutoStyleBrandMake() {
        return autoStyleBrandMake;
    }

    public String getAutoStyleSalesName() {
        return autoStyleSalesName;
    }

    public void setAutoStyleSalesName(String autoStyleSalesName) {
        this.autoStyleSalesName = autoStyleSalesName;
    }

    public String getAutoStyleProductiveYear() {
        return autoStyleProductiveYear;
    }

    public void setAutoStyleProductiveYear(String autoStyleProductiveYear) {
        this.autoStyleProductiveYear = autoStyleProductiveYear;
    }

    public String getAutoStyleIdLiyangId() {
        return autoStyleIdLiyangId;
    }

    public void setAutoStyleIdLiyangId(String autoStyleIdLiyangId) {
        this.autoStyleIdLiyangId = autoStyleIdLiyangId;
    }

    public String getAutoStyleIdLiyangyasuoId() {
        return autoStyleIdLiyangyasuoId;
    }

    public void setAutoStyleIdLiyangyasuoId(String autoStyleIdLiyangyasuoId) {
        this.autoStyleIdLiyangyasuoId = autoStyleIdLiyangyasuoId == null ? null : autoStyleIdLiyangyasuoId.trim();
    }

    public String getAutoStyleMake() {
        return autoStyleMake;
    }

    public void setAutoStyleMake(String autoStyleMake) {
        this.autoStyleMake = autoStyleMake == null ? null : autoStyleMake.trim();
    }

    public String getGoodsBrandName() {
        return goodsBrandName;
    }

    public void setGoodsBrandName(String goodsBrandName) {
        this.goodsBrandName = goodsBrandName == null ? null : goodsBrandName.trim();
    }

    public String getAutoStyleSystem() {
        return autoStyleSystem;
    }

    public void setAutoStyleSystem(String autoStyleSystem) {
        this.autoStyleSystem = autoStyleSystem == null ? null : autoStyleSystem.trim();
    }

    public String getAutoStyleType() {
        return autoStyleType;
    }

    public void setAutoStyleType(String autoStyleType) {
        this.autoStyleType = autoStyleType == null ? null : autoStyleType.trim();
    }

    public String getAutoStyleUpgrade() {
        return autoStyleUpgrade;
    }

    public void setAutoStyleUpgrade(String autoStyleUpgrade) {
        this.autoStyleUpgrade = autoStyleUpgrade == null ? null : autoStyleUpgrade.trim();
    }

    public String getAutoStyleChassis() {
        return autoStyleChassis;
    }

    public void setAutoStyleChassis(String autoStyleChassis) {
        this.autoStyleChassis = autoStyleChassis == null ? null : autoStyleChassis.trim();
    }

    public String getAutoStyleYear() {
        return autoStyleYear;
    }

    public void setAutoStyleYear(String autoStyleYear) {
        this.autoStyleYear = autoStyleYear == null ? null : autoStyleYear.trim();
    }

    public String getAutoStyleStopProduction() {
        return autoStyleStopProduction;
    }

    public void setAutoStyleStopProduction(String autoStyleStopProduction) {
        this.autoStyleStopProduction = autoStyleStopProduction == null ? null : autoStyleStopProduction.trim();
    }

    public String getAutoStyleEngine() {
        return autoStyleEngine;
    }

    public void setAutoStyleEngine(String autoStyleEngine) {
        this.autoStyleEngine = autoStyleEngine == null ? null : autoStyleEngine.trim();
    }

    public String getAutoStyleSweptVolume() {
        return autoStyleSweptVolume;
    }

    public void setAutoStyleSweptVolume(String autoStyleSweptVolume) {
        this.autoStyleSweptVolume = autoStyleSweptVolume == null ? null : autoStyleSweptVolume.trim();
    }

    public String getAutoStyleFuel() {
        return autoStyleFuel;
    }

    public void setAutoStyleFuel(String autoStyleFuel) {
        this.autoStyleFuel = autoStyleFuel == null ? null : autoStyleFuel.trim();
    }

    public String getAutoStylePower() {
        return autoStylePower;
    }

    public void setAutoStylePower(String autoStylePower) {
        this.autoStylePower = autoStylePower == null ? null : autoStylePower.trim();
    }

    public String getAutoStyleGearbox() {
        return autoStyleGearbox;
    }

    public void setAutoStyleGearbox(String autoStyleGearbox) {
        this.autoStyleGearbox = autoStyleGearbox == null ? null : autoStyleGearbox.trim();
    }

    public String getAutoStyleDrive() {
        return autoStyleDrive;
    }

    public void setAutoStyleDrive(String autoStyleDrive) {
        this.autoStyleDrive = autoStyleDrive == null ? null : autoStyleDrive.trim();
    }

    public Date getAutoStyleCreatetime() {
        return autoStyleCreatetime;
    }

    public void setAutoStyleCreatetime(Date autoStyleCreatetime) {
        this.autoStyleCreatetime = autoStyleCreatetime;
    }

    public Integer getAutoStyleIsdel() {
        return autoStyleIsdel;
    }

    public void setAutoStyleIsdel(Integer autoStyleIsdel) {
        this.autoStyleIsdel = autoStyleIsdel;
    }

    public Integer getGoodsBrandId() {
        return goodsBrandId;
    }

    public void setGoodsBrandId(Integer goodsBrandId) {
        this.goodsBrandId = goodsBrandId;
    }

    @Override
    public int compareTo(QpAutoStyleBean o) {
        return this.getAutoStyleProductiveYear().compareTo(o.getAutoStyleProductiveYear());
    }
}
