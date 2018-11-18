package com.ningpai.manager.bean;

import java.util.Date;

/**
 *
 * 管理员登录记录Bean
 *  @author hanxiaoying
 * @since 2016/1/20
 * @version 0.0.1
 */
public class ManagerLoginHistory {

    /**
     * 登录记录id
     */
    private Long id;

    /**
     * 登录人工号
     */
    private Long managerId;
    /**
     * 登录时间
     */
    private Date loginDate;
    /**
     * 登录人IP
     */
    private String loginIP;

    /**
     * 登录类型，1：web正常登录
     */
    private String loginType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Date getLoginDate() {
        if (this.loginDate != null) {
            return new Date(this.loginDate.getTime());
        }
        return null;
    }

    public void setLoginDate(Date loginDate) {
        if (loginDate != null) {
            Date tEmp = (Date) loginDate.clone();
            if (tEmp != null) {
                this.loginDate = tEmp;
            }
        }
    }

    public String getLoginIP() {
        return loginIP;
    }

    public void setLoginIP(String loginIP) {
        this.loginIP = loginIP;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
