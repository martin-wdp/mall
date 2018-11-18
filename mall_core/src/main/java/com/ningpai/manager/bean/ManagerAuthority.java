package com.ningpai.manager.bean;

import java.util.Date;

/**
 * 管理员权限关系BEAN
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午3:11:47
 */
public class ManagerAuthority {
    /**
     * 管理员权限ID
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;
    /**
     * 管理员ID
     * 
     * @see #getManagerId()
     * @see #setManagerId(Long)
     */
    private Long managerId;
    /**
     * 权限ID
     * 
     * @see #getAuthorityId()
     * @see #setAuthorityId(Long)
     */
    private Long authorityId;
    /**
     * 创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 修改时间
     * 
     * @see #getModTime()
     * @see #setModTime(Date)
     */
    private Date modTime;
    /**
     * 状态标记
     * 
     * @see #getFlag()
     * @see #setFlag(String)
     */
    private String flag;

    /**
     * 获取<code>ManagerAuthority</code>ID值
     * 
     * @return id{@link com.ningpai.manager.bean.ManagerAuthority#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>ManagerAuthority</code>ID值
     * 
     * @param id
     *            {@link com.ningpai.manager.bean.ManagerAuthority#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>ManagerAuthority</code>managerId
     * 
     * @return managerId
     *         {@link com.ningpai.manager.bean.ManagerAuthority#managerId}
     */
    public Long getManagerId() {
        return managerId;
    }

    /**
     * 设置<code>ManagerAuthority</code>managerId
     * 
     * @param managerId
     *            {@link com.ningpai.manager.bean.ManagerAuthority#managerId}
     */
    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    /**
     * 获取<code>ManagerAuthority</code>authorityId
     * 
     * @return authorityId
     *         {@link com.ningpai.manager.bean.ManagerAuthority#authorityId}
     */
    public Long getAuthorityId() {
        return authorityId;
    }

    /**
     * 设置<code>ManagerAuthority</code>authorityId
     * 
     * @param authorityId
     *            {@link com.ningpai.manager.bean.ManagerAuthority#authorityId}
     */
    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * 获取<code>ManagerAuthority</code>createTime
     * 
     * @return createTime
     *         {@link com.ningpai.manager.bean.ManagerAuthority#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>ManagerAuthority</code>createTime
     * 
     * @param createTime
     *            {@link com.ningpai.manager.bean.ManagerAuthority#createTime}
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
     * 获取<code>ManagerAuthority</code>modTime
     * 
     * @return modTime{@link com.ningpai.manager.bean.ManagerAuthority#modTime}
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>ManagerAuthority</code>modTime
     * 
     * @param modTime
     *            {@link com.ningpai.manager.bean.ManagerAuthority#modTime}
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
     * 获取<code>ManagerAuthority</code>flag
     * 
     * @return flag{@link com.ningpai.manager.bean.ManagerAuthority#flag}
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置<code>ManagerAuthority</code>flag
     * 
     * @param flag
     *            {@link com.ningpai.manager.bean.ManagerAuthority#flag}
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
