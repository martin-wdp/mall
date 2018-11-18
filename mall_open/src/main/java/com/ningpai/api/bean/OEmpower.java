package com.ningpai.api.bean;

import java.util.Date;

/**
 * 开放接口--秘钥
 * @author lih
 * @version 2.0
 * @since 15/9/1
 */
public class OEmpower {

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
     * 用户token
     */
    private String token;

    /**
     * token获取时间
     */
    private Date tokenTime;


    public String getToken() {
        return token;
    }
    /**
     * 设置TOken
     * */
    public OEmpower setToken(String token) {
        this.token = token;
        return this;
    }
    /**
     *获取TokenTime
     * */
    public Date getTokenTime() {
        if (this.tokenTime != null) {
            return new Date(this.tokenTime.getTime());
        }
        return null;
    }
    /**
     * 设置TokenTIme
     * */
    public OEmpower setTokenTime(Date tokenTime) {
        this.tokenTime = tokenTime;
        return this;
    }

    public String getAppUserName() {
        return appUserName;
    }
    /**
     * 设置App名称
     * */
    public OEmpower setAppUserName(String appUserName) {
        this.appUserName = appUserName;
        return this;
    }
    /**
     * 获取开始时间
     * */
    public Date getBeginTime() {
        if (this.beginTime != null) {
            return new Date(this.beginTime.getTime());
        }
        return null;
    }
    /**
     * 设置开始时间
     * */
    public OEmpower setBeginTime(Date beginTime) {
        if (beginTime != null) {
            Date tEmp = (Date) beginTime.clone();
            if (tEmp != null) {
                this.beginTime = tEmp;
            }
        }
        return this;
    }
    /**
     * 获取结束时间
     * */
    public Date getEndTime() {
        if (this.endTime != null) {
            return new Date(this.endTime.getTime());
        }
        return null;
    }
    /**
     * 设置结束时间
     * */
    public OEmpower setEndTime(Date endTime) {
        if (endTime != null) {
            Date tEmp = (Date) endTime.clone();
            if (tEmp != null) {
                this.endTime = tEmp;
            }
        }
        return this;
    }


    public String getStatus() {
        return status;
    }
    /**
     * 设置状态
     * */
    public OEmpower setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getDelFlag() {
        return delFlag;
    }
    /**
     * 设置删除标记
     * */
    public OEmpower setDelFlag(String delFlag) {
        this.delFlag = delFlag;
        return this;
    }

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }
}
