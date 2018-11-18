package com.ningpai.system.bean;

import java.util.Date;

/**
 * 权限显示
 * 
 * @author lih
 * 
 */
public class Empower {
    /**
     * 权限ID
     */
    private Integer appId;
    /**
     *  权限码
     */
    private String appKey;

    /**
     * 权限用户
     */
    private String appUserName;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 状态 0：开启 1：禁用
     */
    private String status;

    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 权限用户
     * @return String
     */
    public String getAppUserName() {
        return appUserName;
    }
    /**
     * 权限用户
     * @return Empower
     */
    public Empower setAppUserName(String appUserName) {
        this.appUserName = appUserName;
        return this;
    }

    /**
     * 时间
     * @return
     */
    public Date getBeginTime() {
        if (this.beginTime != null) {
            return new Date(this.beginTime.getTime());
        }
        return null;
    }
    /**
     * 时间
     * @return
     */
    public Empower setBeginTime(Date beginTime) {
        if (beginTime != null) {
            Date tEmp = (Date) beginTime.clone();
            if (tEmp != null) {
                this.beginTime = tEmp;
            }
        }
        return this;
    }
    /**
     * 时间
     * @return
     */
    public Date getEndTime() {
        if (this.endTime != null) {
            return new Date(this.endTime.getTime());
        }
        return null;
    }
    /**
     * 时间
     * @return
     */
    public Empower setEndTime(Date endTime) {
        if (endTime != null) {
            Date tEmp = (Date) endTime.clone();
            if (tEmp != null) {
                this.endTime = tEmp;
            }
        }
        return this;
    }

    /**
     * 状态 0：开启 1：禁用
     * @return String
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状态 0：开启 1：禁用
     * @param status
     * @return Empower
     */
    public Empower setStatus(String status) {
        this.status = status;
        return this;
    }

    /**
     * 删除标记
     * @return String
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * 删除标记
     * @param delFlag
     * @return Empower
     */
    public Empower setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    /**
     * 权限ID
     * @return Integer
     */
    public Integer getAppId() {
        return appId;
    }

    /**
     * 权限ID
     * @param appId
     */
    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    /**
     * 权限码
     * @return String
     */
    public String getAppKey() {
        return appKey;
    }

    /**
     * 权限码
     * @param appKey
     */
    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
