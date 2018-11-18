package com.ningpai.comment.bean;

import java.util.Date;

/**
 * 邮箱服务器设置
 * 
 * @author jiping
 * @since 2014年7月18日 上午10:34:21
 * @version 0.0.1
 */
public class EmailServerSite {
    // 邮箱服务器ID
    private Long serverid;
    // 发信邮箱
    private String sendmail;
    // 发信人
    private String sendname;
    // SMTP账号
    private String smtpaccount;
    // SMTP密码
    private String smtppass;
    // SMTP服务器
    private String smtpserver;
    // SMTP端口号
    private String smtpport;
    // 是否开启
    private String isOpen;
    // 是否验证
    private String isCheck;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modifyTime;
    // 是否删除
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
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }
    /**
     * 获取修改时间
     * */
    public Date getModifyTime() {
        if (this.modifyTime != null) {
            return new Date(this.modifyTime.getTime());
        } else {
            return null;
        }
    }
    /**
     * 设置修改时间
     * */
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
