package com.ningpai.third.auth.bean;

import java.util.Date;

/**
 * <p>
 * 第三方管理员
 * </p>
 * 
 * @author zhanghl
 * @since20150730
 * @version 2.0
 */
public class ThirdManager {
    /**
     * 管理员编号
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String userkey;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date modTime;
    /**
     * 登录时间
     */
    private Date loginTime;
    /**
     * 删除标记
     */
    private String flag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserkey() {
        return userkey;
    }

    public void setUserkey(String userkey) {
        this.userkey = userkey;
    }

    /**
     * 获取创建时间
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
     * 设置创建日期
     * @param createTime
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
     * 获取修改日期
     * @return
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置修改日期
     * @param modTime
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    /**
     * 获取登陆日期
     * @return
     */
    public Date getLoginTime() {
        if (this.loginTime != null) {
            return new Date(this.loginTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置登陆日期
     * @param loginTime
     */
    public void setLoginTime(Date loginTime) {
        if (loginTime != null) {
            Date tEmp = (Date) loginTime.clone();
            if (tEmp != null) {
                this.loginTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
