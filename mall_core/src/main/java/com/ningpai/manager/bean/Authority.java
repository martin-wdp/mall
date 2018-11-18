package com.ningpai.manager.bean;

import java.util.Date;

/**
 * 权限BEAN
 * 
 * @author AthrunNatu
 * 
 * @since 2013年11月20日上午10:49:13
 */
public class Authority {
    /**
     * 权限ID
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;
    /**
     * 权限名称
     * 
     * @see #getDesignation()
     * @see #setDesignation(String)
     */
    private String designation;
    /**
     * 权限
     * 
     * @see #getCharacterization()
     * @see #setCharacterization(String)
     */
    private String characterization;
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

    private Long createId;

    /**
     * 状态标记
     * 
     * @see #getFlag()
     * @see #setFlag(String)
     */
    private String flag;
    // 分页开始的条数
    private int startRowNum;
    // 分页结束的条数
    private int endRowNum;

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public int getStartRowNum() {
        return startRowNum;
    }

    public void setStartRowNum(int startRowNum) {
        this.startRowNum = startRowNum;
    }

    public int getEndRowNum() {
        return endRowNum;
    }

    public void setEndRowNum(int endRowNum) {
        this.endRowNum = endRowNum;
    }

    /**
     * 获取<code>Authority</code>ID值
     * 
     * @return id{@link com.ningpai.manager.bean.Authority#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>Authority</code>ID值
     * 
     * @param id
     *            {@link com.ningpai.manager.bean.Authority#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>Authority</code>权限名称
     * 
     * @return designation
     *         {@link com.ningpai.manager.bean.Authority#designation}
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 设置<code>Authority</code>权限名称
     * 
     * @param designation
     *            {@link com.ningpai.manager.bean.Authority#designation}
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 获取<code>Authority</code>权限描述
     * 
     * @return characterization
     *         {@link com.ningpai.manager.bean.Authority#characterization}
     */
    public String getCharacterization() {
        return characterization;
    }

    /**
     * 设置<code>Authority</code>权限描述
     * 
     * @param characterization
     *            {@link com.ningpai.manager.bean.Authority#characterization}
     */
    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }

    /**
     * 获取创建<code>Authority</code>创建时间
     * 
     * @return createTime {@link com.ningpai.manager.bean.Authority#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置创建<code>Authority</code>创建时间
     * 
     * @param createTime
     *            {@link com.ningpai.manager.bean.Authority#createTime}
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
     * 获取<code>Authority</code>修改时间
     * 
     * @return modTime {@link com.ningpai.manager.bean.Authority#modTime}
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Authority</code>修改时间
     * 
     * @param modTime
     *            {@link com.ningpai.manager.bean.Authority#modTime}
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
     * 获取<code>Authority</code>标记状态
     * 
     * @return flag {@link com.ningpai.manager.bean.Authority#flag}
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置<code>Authority</code>标记状态
     * 
     * @param flag
     *            {@link com.ningpai.manager.bean.Authority#flag}
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }
}
