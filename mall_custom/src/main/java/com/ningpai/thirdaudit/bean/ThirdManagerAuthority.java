package com.ningpai.thirdaudit.bean;

import java.util.Date;

/**
 * 第三方管理员权限关系
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年5月12日 下午4:15:59
 * @version 0.0.1
 */
public class ThirdManagerAuthority {
    // 关系ID
    private Long id;
    // 管理员编号
    private Long managerId;
    // 权限编号
    private Long authorityId;
    // 创建时间
    private Date createTime;
    // 修改时间
    private Date modTime;
    // 删除标记
    private String flag;

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

    public Long getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date timeTemp = (Date) createTime.clone();
            if (timeTemp != null) {
                this.createTime = timeTemp;
            }
        }
    }
    /**
     * 获取修改时间
     * */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(modTime.getTime());
        }
        return null;
    }
    /**
     * 设置修改时间
     * */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date timeTemp = (Date) modTime.clone();
            if (timeTemp != null) {
                this.modTime = timeTemp;
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
