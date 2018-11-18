/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;

/**
 * 邮箱服务器实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 上午11:23:25
 * @version 1.0
 */
public class EmailServer {
    /*
     * 邮箱服务器ID
     */
    private Long serverid;
    /*
     * 发信邮箱
     */
    // @NotNull
    @Email
    private String sendmail;
    /*
     * 发信人
     */
    // @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String sendname;
    /*
     * SMTP账号
     */
    // @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smtpaccount;
    /*
     * SMTP密码
     */
    // @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smtppass;
    /*
     * SMTP服务器
     */
    // @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String smtpserver;
    /*
     * SMTP端口号
     */
    // @NotNull
    @Pattern(regexp = "^\\d+$")
    private String smtpport;
    /*
     * 是否开启
     */
    private String isOpen;
    /*
     * 是否验证
     */
    private String isCheck;
    /*
     * 创建时间
     */
    private Date createTime;
    /*
     * 修改时间
     */
    private Date modifyTime;
    /*
     * 是否删除
     */
    private String delFlag;

    public Long getServerid() {
        return serverid;
    }

    public void setServerid(Long serverid) {
        this.serverid = serverid;
    }

    public String getSendmail() {
        return sendmail;
    }

    public void setSendmail(String sendmail) {
        this.sendmail = sendmail;
    }

    public String getSendname() {
        return sendname;
    }

    public void setSendname(String sendname) {
        this.sendname = sendname;
    }

    public String getSmtpaccount() {
        return smtpaccount;
    }

    public void setSmtpaccount(String smtpaccount) {
        this.smtpaccount = smtpaccount;
    }

    public String getSmtppass() {
        return smtppass;
    }

    public void setSmtppass(String smtppass) {
        this.smtppass = smtppass;
    }

    public String getSmtpserver() {
        return smtpserver;
    }

    public void setSmtpserver(String smtpserver) {
        this.smtpserver = smtpserver;
    }

    public String getSmtpport() {
        return smtpport;
    }

    public void setSmtpport(String smtpport) {
        this.smtpport = smtpport;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
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
