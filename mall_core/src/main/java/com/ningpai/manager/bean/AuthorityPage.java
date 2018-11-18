package com.ningpai.manager.bean;

import java.util.Date;

/**
 * 权限页面关系表
 * 
 * @author AthrunNatu
 * 
 * @since 2013年11月20日上午11:42:20
 */
public class AuthorityPage {
    /**
     * 权限页面ID
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;
    /**
     * 权限ID
     * 
     * @see #getAuthorityId()
     * @see #setAuthorityId(Long)
     */
    private Long authorityId;
    /**
     * 页面ID
     * 
     * @see #getAuthorityId()
     * @see #setAuthorityId(Long);
     */
    private Long pageId;
    /**
     * 权限页面关系创建时间
     * 
     * @see #getCreateTime()
     * @see #setCreateTime(Date)
     */
    private Date createTime;
    /**
     * 权限页面修改时间
     * 
     * @see #getModTime()
     * @see #setModTime(Date)
     */
    private Date modTime;
    /**
     * 权限页面标记状态
     * 
     * @see #getFlag()
     * @see #setFlag(String)
     */
    private String flag;

    /**
     * 获取<code>AuthorityPage</code> id
     * 
     * @return id{@link com.ningpai.manager.bean.AuthorityPage#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>AuthorityPage</code> id
     * 
     * @return id{@link com.ningpai.manager.bean.AuthorityPage#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>AuthorityPage</code> AuthorityId
     * 
     * @return authorityId
     *         {@link com.ningpai.manager.bean.AuthorityPage#authorityId}
     */
    public Long getAuthorityId() {
        return authorityId;
    }

    /**
     * 设置 <code>AuthorityPage</code> authorityId
     * 
     * @param authorityId
     *            {@link com.ningpai.manager.bean.AuthorityPage#authorityId}
     */
    public void setAuthorityId(Long authorityId) {
        this.authorityId = authorityId;
    }

    /**
     * 获取<code>AuthorityPage</code> pageId
     * 
     * @return pageId{@link com.ningpai.manager.bean.AuthorityPage#pageId}
     */
    public Long getPageId() {
        return pageId;
    }

    /**
     * 设置<code>AuthorityPage</code> pageId
     * 
     * @param pageId
     *            {@link com.ningpai.manager.bean.AuthorityPage#pageId}
     */
    public void setPageId(Long pageId) {
        this.pageId = pageId;
    }

    /**
     * 获取<code>AuthorityPage</code> createTime
     * 
     * @return createTime
     *         {@link com.ningpai.manager.bean.AuthorityPage#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>AuthorityPage</code> createTime
     * 
     * @param createTime
     *            {@link com.ningpai.manager.bean.AuthorityPage#createTime}
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
     * 获取<code>AuthorityPage</code> modTime
     * 
     * @return modTime {@link com.ningpai.manager.bean.AuthorityPage#modTime}
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>AuthorityPage</code> modTime
     * 
     * @param modTime
     *            {@link com.ningpai.manager.bean.AuthorityPage#modTime}
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
     * 获取<code>AuthorityPage</code> flag
     * 
     * @return flag {@link com.ningpai.manager.bean.AuthorityPage#flag}
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置<code>AuthorityPage</code> flag
     * 
     * @param flag
     *            {@link com.ningpai.manager.bean.AuthorityPage#flag}
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
