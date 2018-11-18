/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

/**
 * 登录接口实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月21日 下午1:36:14
 * @version 1.0
 */
public class InterLogin {
    /* 
     * 登录接口ID
     */
    private Long loginId;
    /*
     *  登录接口地址
     */
    private String loginUrl;
    /*
     *  第三方UID
     */
    private String threeId;
    /*
     *  第三方TOKEN
     */
    private String threeToken;
    /*
     *  回调地址
     */
    private String backurl;
    /*
     *  是否开始
     */
    private String isOpen;
    /*
     *  创建时间
     */
    private Date createTime;
    /*
     *  修改时间
     */
    private Date modifyTime;
    /*
     *  是否删除
     */
    private String delFlag;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public String getThreeId() {
        return threeId;
    }

    public void setThreeId(String threeId) {
        this.threeId = threeId;
    }

    public String getThreeToken() {
        return threeToken;
    }

    public void setThreeToken(String threeToken) {
        this.threeToken = threeToken;
    }

    public String getBackurl() {
        return backurl;
    }

    public void setBackurl(String backurl) {
        this.backurl = backurl;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }
    /**
     * 时间
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 时间
     * @return
     */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 时间
     * @return
     */
    public void setModifyTime(Date modifyTime) {
        if (modifyTime != null) {
            Date tEmp = (Date) modifyTime.clone();
            if (tEmp != null) {
                this.modifyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
