package com.ningpai.common.bean;

import java.util.Date;

/**
 * 邮箱服务器设置
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 上午11:51:59
 * @version 1.0
 */
public class EmailServer {
    /**
     * 邮箱服务器ID
     */
    private Long serverid;
    /**
     * 发信邮箱
     */
    private String sendmail;
    /**
     * 发信人
     */
    private String sendname;
    /**
     * SMTP账号
     */
    private String smtpaccount;
    /**
     * SMTP密码
     */
    private String smtppass;
    /**
     * SMTP服务器
     */
    private String smtpserver;
    /**
     * SMTP端口号
     */
    private String smtpport;
    /**
     * 是否开启
     */
    private String isOpen;
    /**
     * 是否验证
     */
    private String isCheck;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
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
     * 获取创建时间
     * @return 创建时间
     * @author Songhl
     */
    public Date getCreateTime() {
        //如果创建时间不为空
        if (this.createTime != null) {
            //返回创建时间
            return new Date(this.createTime.getTime());
        } else {
            //返回空
            return null;
        }
    }

    /**
     * 设置创建时间
     * @param createTime 创建时间
     * @author Songhl
     */
    public void setCreateTime(Date createTime) {
        //如果创建时间不为空
        if (createTime != null) {
            //获取创建时间值
            Date tEmp = (Date) createTime.clone();
            //如果不为空
            if (tEmp != null) {
                //赋值给创建时间
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 获取修改时间
     * @return 修改时间
     * @author Songhl
     */
    public Date getModifyTime() {
        //如果修改时间不为空
        if (this.modifyTime != null) {
            //返回修改时间
            return new Date(this.modifyTime.getTime());
        } else {
            //返回空
            return null;
        }
    }

    /**
     * 设置修改时间
     * @param modifyTime 修改时间
     * @author Songhl
     */
    public void setModifyTime(Date modifyTime) {
        //如果修改时间不为空
        if (modifyTime != null) {
            //获取修改时间
            Date tEmp = (Date) modifyTime.clone();
            //如果修改时间不为空
            if (tEmp != null) {
                //设置为修改时间
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
