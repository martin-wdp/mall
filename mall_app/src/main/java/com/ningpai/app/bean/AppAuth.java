/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: np_app_auth表的实体bean
 * @author Ningpai-HEHU
 * @date 2015-07-17 11:01:10
 * @version V1.0
 */
public class AppAuth implements Serializable {

    /**
     * 序列化id
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    private Long appAuthId;

    /**
     * 授权秘钥
     */
    private String appAuthKey;

    /**
     * 最后访问时间
     */
    private Date appAuthAccessTime;

    /**
     * 创建时间
     */
    private Date appAuthCreateTime;

    /**
     * 有效期截止时间
     */
    private Date appAuthEndTime;

    /**
     * 访问次数
     */
    private Long appAuthAccessCount;

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站联系人
     */
    private String siteContactor;

    /**
     * 联系电话
     */
    private String siteContactphone;
    /**
     * 联系人邮箱
     */
    private String siteContactEmail;

    /**
     * 网站地址
     */
    private String siteWebAddr;

    /**
     * 网站logo
     */
    private String siteLogo;

    /**
     * 删除标记，0未删除，1已删除
     */
    private String delFlag;

    public Long getAppAuthId() {
        return appAuthId;
    }

    public void setAppAuthId(Long appAuthId) {
        this.appAuthId = appAuthId;
    }

    public String getAppAuthKey() {
        return appAuthKey;
    }

    public void setAppAuthKey(String appAuthKey) {
        this.appAuthKey = appAuthKey;
    }

    /**
     * 获取AppAuthAccessTIme
     * */
    public Date getAppAuthAccessTime() {
        return (Date) appAuthAccessTime.clone();
    }

    /**
     * 设置授权时间
     * @param appAuthAccessTime 授权时间
     */
    public void setAppAuthAccessTime(Date appAuthAccessTime) {
        this.appAuthAccessTime = appAuthAccessTime == null ? null : (Date) appAuthAccessTime.clone();
    }

    public Long getAppAuthAccessCount() {
        return appAuthAccessCount;
    }

    public void setAppAuthAccessCount(Long appAuthAccessCount) {
        this.appAuthAccessCount = appAuthAccessCount;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteContactor() {
        return siteContactor;
    }

    public void setSiteContactor(String siteContactor) {
        this.siteContactor = siteContactor;
    }

    public String getSiteContactphone() {
        return siteContactphone;
    }

    public void setSiteContactphone(String siteContactphone) {
        this.siteContactphone = siteContactphone;
    }

    public String getSiteWebAddr() {
        return siteWebAddr;
    }

    public void setSiteWebAddr(String siteWebAddr) {
        this.siteWebAddr = siteWebAddr;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSiteContactEmail() {
        return siteContactEmail;
    }

    public void setSiteContactEmail(String siteContactEmail) {
        this.siteContactEmail = siteContactEmail;
    }
    /**
     * 获取AppAuthCreateTime
     * */
    public Date getAppAuthCreateTime() {
        return (Date) appAuthCreateTime.clone();
    }

    /**
     * 设置授权信息创建时间
     * @param appAuthCreateTime 授权信息创建时间
     */
    public void setAppAuthCreateTime(Date appAuthCreateTime) {
        this.appAuthCreateTime = appAuthCreateTime == null ? null : (Date) appAuthCreateTime.clone();
    }
    /**
     * 获取AppAuthEndTime
     * */
    public Date getAppAuthEndTime() {
        return (Date) appAuthEndTime.clone();
    }

    /**
     * 设置授权秘钥有效期截止时间
     * @param appAuthEndTime 秘钥有效期截止时间
     */
    public void setAppAuthEndTime(Date appAuthEndTime) {
        this.appAuthEndTime = appAuthEndTime == null ? null : (Date) appAuthEndTime.clone();
    }

    public String getSiteLogo() {
        return siteLogo;
    }

    public void setSiteLogo(String siteLogo) {
        this.siteLogo = siteLogo;
    }
}