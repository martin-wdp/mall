/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 短信接口实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 下午5:12:08
 * @version 1.0
 */
public class MessageServer {
    /*
     *  短信ID
     */
    private Long smsId;
    /*
     *  接口地址
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smsUrl;
    /*
     *  申请接口地址
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smsAddress;
    /*
     *  接口提供商
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smsProvider;
    /*
     *  名称
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smsName;
    /* 
     * 密码
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smsPass;
    /* 
     * 网关
     */
    @NotNull
    private String smsGateway;
    /* 
     * 是否开启
     */
    private String isOpen;
    /*
     *  创建时间
     */
    private Date createTime;
    /* 
     * 修改时间
     */
    private Date modifyTime;

    public Long getSmsId() {
        return smsId;
    }

    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getSmsProvider() {
        return smsProvider;
    }

    public void setSmsProvider(String smsProvider) {
        this.smsProvider = smsProvider;
    }

    public String getSmsAddress() {
        return smsAddress;
    }

    public void setSmsAddress(String smsAddress) {
        this.smsAddress = smsAddress;
    }

    public String getSmsName() {
        return smsName;
    }

    public void setSmsName(String smsName) {
        this.smsName = smsName;
    }

    public String getSmsPass() {
        return smsPass;
    }

    public void setSmsPass(String smsPass) {
        this.smsPass = smsPass;
    }

    public String getSmsGateway() {
        return smsGateway;
    }

    public void setSmsGateway(String smsGateway) {
        this.smsGateway = smsGateway;
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
}
