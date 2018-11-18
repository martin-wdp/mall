/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.api.bean;

import java.util.Date;

/**
 * 第三方授权信息Bean
 * 
 * @author NINGPAI-ZHOUY
 * @since 2013年12月23日下午5:44:43
 * @version 1.0
 */
public class Auth {
    /*
     *主键
     */
    private Long authId;
    /*
     *第三方名称
     */
    private String authName;
    /*
     *AppKey
     */
    private String authClientId;
    /*
     *AppSecret
     */
    private String authClientSecret;
    /*
     *回调地址
     */
    private String authRedirectUri;
    /*
     *排序字段
     */
    private Short authSort;
    /*
     *是否启用
     */
    private String authShow;
    /*
     *创建人
     */
    private String authCreateName;
    /*
     *创建时间
     */
    private Date authCreated;
    /*
     *修改人
     */
    private String authUpdateName;
    /*
     *修改时间
     */
    private Date authUpdated;
    /*
     *删除人
     */
    private String authDeleteName;
    /*
     *删除时间
     */
    private Date authDeleted;
    /*
     *删除标记
     */
    private String authDeleteFlag;

    /**
     * @return the authId
     */
    public Long getAuthId() {
        return authId;
    }

    /**
     * @param authId
     *            the authId to set
     */
    public void setAuthId(Long authId) {
        this.authId = authId;
    }

    /**
     * @return the authName
     */
    public String getAuthName() {
        return authName;
    }

    /**
     * @param authName
     *            the authName to set
     */
    public void setAuthName(String authName) {
        this.authName = authName;
    }

    /**
     * @return the authClientId
     */
    public String getAuthClientId() {
        return authClientId;
    }

    /**
     * @param authClientId
     *            the authClientId to set
     */
    public void setAuthClientId(String authClientId) {
        this.authClientId = authClientId;
    }

    /**
     * @return the authClientSecret
     */
    public String getAuthClientSecret() {
        return authClientSecret;
    }

    /**
     * @param authClientSecret
     *            the authClientSecret to set
     */
    public void setAuthClientSecret(String authClientSecret) {
        this.authClientSecret = authClientSecret;
    }

    /**
     * @return the authRedirectUri
     */
    public String getAuthRedirectUri() {
        return authRedirectUri;
    }

    /**
     * @param authRedirectUri
     *            the authRedirectUri to set
     */
    public void setAuthRedirectUri(String authRedirectUri) {
        this.authRedirectUri = authRedirectUri;
    }

    /**
     * @return the authSort
     */
    public Short getAuthSort() {
        return authSort;
    }

    /**
     * @param authSort
     *            the authSort to set
     */
    public void setAuthSort(Short authSort) {
        this.authSort = authSort;
    }

    /**
     * @return the authShow
     */
    public String getAuthShow() {
        return authShow;
    }

    /**
     * @param authShow
     *            the authShow to set
     */
    public void setAuthShow(String authShow) {
        this.authShow = authShow;
    }

    /**
     * @return the authCreateName
     */
    public String getAuthCreateName() {
        return authCreateName;
    }

    /**
     * @param authCreateName
     *            the authCreateName to set
     */
    public void setAuthCreateName(String authCreateName) {
        this.authCreateName = authCreateName;
    }

    /**
     * @return the authCreated
     */
    public Date getAuthCreated() {
        if (this.authCreated != null) {
            return new Date(this.authCreated.getTime());
        }
        return null;
    }

    /**
     * @param authCreated
     *            the authCreated to set
     */
    public void setAuthCreated(Date authCreated) {
        if (authCreated != null) {
            Date tEmp = (Date) authCreated.clone();
            if (tEmp != null) {
                this.authCreated = tEmp;
            }
        }
    }

    /**
     * @return the authUpdateName
     */
    public String getAuthUpdateName() {
        return authUpdateName;
    }

    /**
     * @param authUpdateName
     *            the authUpdateName to set
     */
    public void setAuthUpdateName(String authUpdateName) {
        this.authUpdateName = authUpdateName;
    }

    /**
     * @return the authUpdated
     */
    public Date getAuthUpdated() {
        if (this.authUpdated != null) {
            return new Date(this.authUpdated.getTime());
        }
        return null;
    }

    /**
     * @param authUpdated
     *            the authUpdated to set
     */
    public void setAuthUpdated(Date authUpdated) {
        if (authUpdated != null) {
            Date tEmp = (Date) authUpdated.clone();
            if (tEmp != null) {
                this.authUpdated = tEmp;
            }
        }
    }

    /**
     * @return the authDeleteName
     */
    public String getAuthDeleteName() {
        return authDeleteName;
    }

    /**
     * @param authDeleteName
     *            the authDeleteName to set
     */
    public void setAuthDeleteName(String authDeleteName) {
        this.authDeleteName = authDeleteName;
    }

    /**
     * @return the authDeleted
     */
    public Date getAuthDeleted() {
        if (this.authDeleted != null) {
            return new Date(this.authDeleted.getTime());
        }
        return null;
    }

    /**
     * @param authDeleted
     *            the authDeleted to set
     */
    public void setAuthDeleted(Date authDeleted) {
        if (authDeleted != null) {
            Date tEmp = (Date) authDeleted.clone();
            if (tEmp != null) {
                this.authDeleted = tEmp;
            }
        }
    }

    /**
     * @return the authDeleteFlag
     */
    public String getAuthDeleteFlag() {
        return authDeleteFlag;
    }

    /**
     * @param authDeleteFlag
     *            the authDeleteFlag to set
     */
    public void setAuthDeleteFlag(String authDeleteFlag) {
        this.authDeleteFlag = authDeleteFlag;
    }

}
