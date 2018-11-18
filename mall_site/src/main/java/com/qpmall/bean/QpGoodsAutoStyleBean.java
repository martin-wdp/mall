package com.qpmall.bean;

import java.util.Date;

/**
 * Created by pl on 2015/10/21.
 * Desc:
 */
public class QpGoodsAutoStyleBean {
    private Long goodsAutoStyleId;

    private Long goodsId;

    private String autoStyleId;

    private String goodsAutoStyleDelflag;

    private String goodsAutoStyleCreateName;

    private Date goodsAutoStyleCreateTime;

    private String goodsAutoStyleModifiedName;

    private Date goodsAutoStyleModifiedTime;

    private String goodsAutoStyleDelName;

    private Date goodsAutoStyleDelTime;

    public Long getGoodsAutoStyleId() {
        return goodsAutoStyleId;
    }

    public void setGoodsAutoStyleId(Long goodsAutoStyleId) {
        this.goodsAutoStyleId = goodsAutoStyleId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getAutoStyleId() {
        return autoStyleId;
    }

    public void setAutoStyleId(String autoStyleId) {
        this.autoStyleId = autoStyleId;
    }

    public String getGoodsAutoStyleDelflag() {
        return goodsAutoStyleDelflag;
    }

    public void setGoodsAutoStyleDelflag(String goodsAutoStyleDelflag) {
        this.goodsAutoStyleDelflag = goodsAutoStyleDelflag == null ? null : goodsAutoStyleDelflag.trim();
    }

    public String getGoodsAutoStyleCreateName() {
        return goodsAutoStyleCreateName;
    }

    public void setGoodsAutoStyleCreateName(String goodsAutoStyleCreateName) {
        this.goodsAutoStyleCreateName = goodsAutoStyleCreateName == null ? null : goodsAutoStyleCreateName.trim();
    }

    public Date getGoodsAutoStyleCreateTime() {
        return goodsAutoStyleCreateTime;
    }

    public void setGoodsAutoStyleCreateTime(Date goodsAutoStyleCreateTime) {
        this.goodsAutoStyleCreateTime = goodsAutoStyleCreateTime;
    }

    public String getGoodsAutoStyleModifiedName() {
        return goodsAutoStyleModifiedName;
    }

    public void setGoodsAutoStyleModifiedName(String goodsAutoStyleModifiedName) {
        this.goodsAutoStyleModifiedName = goodsAutoStyleModifiedName == null ? null : goodsAutoStyleModifiedName.trim();
    }

    public Date getGoodsAutoStyleModifiedTime() {
        return goodsAutoStyleModifiedTime;
    }

    public void setGoodsAutoStyleModifiedTime(Date goodsAutoStyleModifiedTime) {
        this.goodsAutoStyleModifiedTime = goodsAutoStyleModifiedTime;
    }

    public String getGoodsAutoStyleDelName() {
        return goodsAutoStyleDelName;
    }

    public void setGoodsAutoStyleDelName(String goodsAutoStyleDelName) {
        this.goodsAutoStyleDelName = goodsAutoStyleDelName == null ? null : goodsAutoStyleDelName.trim();
    }

    public Date getGoodsAutoStyleDelTime() {
        return goodsAutoStyleDelTime;
    }

    public void setGoodsAutoStyleDelTime(Date goodsAutoStyleDelTime) {
        this.goodsAutoStyleDelTime = goodsAutoStyleDelTime;
    }
}
