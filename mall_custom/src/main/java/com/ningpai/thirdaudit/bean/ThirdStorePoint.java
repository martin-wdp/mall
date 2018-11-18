package com.ningpai.thirdaudit.bean;

import java.util.Date;

/**
 * 第三方商家point实体类
 * */
public class ThirdStorePoint {
    /*
     * 商家pointId
     */
    private Long storePointId;

    /*
     * 商家id
     */
    private Long thirdId;
    /*
     * 详情
     */
    private String pointDetail;
    /*
     * 类型
     */
    private String pointType;
    /*
     * point
     */
    private Integer point;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifiedTime;
    /*
     * 删除时间
     */
    private Date delTime;
    /*
     * 删除标记
     */
    private String delFlag;

    public Long getStorePointId() {
        return storePointId;
    }

    public void setStorePointId(Long storePointId) {
        this.storePointId = storePointId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getPointDetail() {
        return pointDetail;
    }

    public void setPointDetail(String pointDetail) {
        this.pointDetail = pointDetail;
    }

    public String getPointType() {
        return pointType;
    }

    public void setPointType(String pointType) {
        this.pointType = pointType;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }

    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getModifiedTime() {
        return (Date) modifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : (Date) modifiedTime.clone();
    }
    /**
     * 获取删除时间
     * */
    public Date getDelTime() {
        return (Date) delTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setDelTime(Date delTime) {
        this.delTime = delTime == null ? null : (Date) delTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
