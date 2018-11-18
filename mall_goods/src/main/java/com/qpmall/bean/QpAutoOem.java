package com.qpmall.bean;

import java.util.Date;

public class QpAutoOem {
    private Long autoOemId;

    private String autoOemCode;

    private String autoStyleIdLiyangId;

    private String autoStyleId;

    private String autoOemBrand;

    private Date autoOemCreateTime;

    private String autoOemModifiedName;

    private Date autoOemModifiedTime;

    private String autoOemDelName;

    private Date autoOemDelTime;

    private String autoOemDelflag;

    private Long goodsTypeId;

    private String autoOemName;

    private String autoOemCreateName;

    public Long getAutoOemId() {
        return autoOemId;
    }

    public void setAutoOemId(Long autoOemId) {
        this.autoOemId = autoOemId;
    }

    public String getAutoOemCode() {
        return autoOemCode;
    }

    public void setAutoOemCode(String autoOemCode) {
        this.autoOemCode = autoOemCode == null ? null : autoOemCode.trim();
    }

    public String getAutoStyleIdLiyangId() {
        return autoStyleIdLiyangId;
    }

    public void setAutoStyleIdLiyangId(String autoStyleIdLiyangId) {
        this.autoStyleIdLiyangId = autoStyleIdLiyangId == null ? null : autoStyleIdLiyangId.trim();
    }

    public String getAutoStyleId() {
        return autoStyleId;
    }

    public void setAutoStyleId(String autoStyleId) {
        this.autoStyleId = autoStyleId == null ? null : autoStyleId.trim();
    }

    public String getAutoOemBrand() {
        return autoOemBrand;
    }

    public void setAutoOemBrand(String autoOemBrand) {
        this.autoOemBrand = autoOemBrand == null ? null : autoOemBrand.trim();
    }

    public Date getAutoOemCreateTime() {
        return autoOemCreateTime;
    }

    public void setAutoOemCreateTime(Date autoOemCreateTime) {
        this.autoOemCreateTime = autoOemCreateTime;
    }

    public String getAutoOemModifiedName() {
        return autoOemModifiedName;
    }

    public void setAutoOemModifiedName(String autoOemModifiedName) {
        this.autoOemModifiedName = autoOemModifiedName == null ? null : autoOemModifiedName.trim();
    }

    public Date getAutoOemModifiedTime() {
        return autoOemModifiedTime;
    }

    public void setAutoOemModifiedTime(Date autoOemModifiedTime) {
        this.autoOemModifiedTime = autoOemModifiedTime;
    }

    public String getAutoOemDelName() {
        return autoOemDelName;
    }

    public void setAutoOemDelName(String autoOemDelName) {
        this.autoOemDelName = autoOemDelName == null ? null : autoOemDelName.trim();
    }

    public Date getAutoOemDelTime() {
        return autoOemDelTime;
    }

    public void setAutoOemDelTime(Date autoOemDelTime) {
        this.autoOemDelTime = autoOemDelTime;
    }

    public String getAutoOemDelflag() {
        return autoOemDelflag;
    }

    public void setAutoOemDelflag(String autoOemDelflag) {
        this.autoOemDelflag = autoOemDelflag == null ? null : autoOemDelflag.trim();
    }

    public Long getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(Long goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getAutoOemName() {
        return autoOemName;
    }

    public void setAutoOemName(String autoOemName) {
        this.autoOemName = autoOemName == null ? null : autoOemName.trim();
    }

    public String getAutoOemCreateName() {
        return autoOemCreateName;
    }

    public void setAutoOemCreateName(String autoOemCreateName) {
        this.autoOemCreateName = autoOemCreateName == null ? null : autoOemCreateName.trim();
    }
}