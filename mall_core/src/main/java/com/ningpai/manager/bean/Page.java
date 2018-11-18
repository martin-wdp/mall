package com.ningpai.manager.bean;

import java.util.Date;

/**
 * 页面Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年3月29日 下午2:31:26
 * @version 0.0.1
 */
public class Page {
    /**
     * ID
     * 
     * @see #getId()
     * @see #setId(Long)
     */
    private Long id;
    /**
     * 父菜单ID
     * 
     * @see #getParentId()
     * @see #setCharacterization(String)
     */
    private Long parentId;
    // 层级数
    private Long grade;
    // 排序
    private Long sort;
    /**
     * 页面名称
     * 
     * @see #getDesignation()
     * @see #setDesignation(String)
     */
    private String designation;
    /**
     * URL
     * 
     * @see #getUrl()
     * @see #setUrl(String)
     */
    private String url;
    /**
     * 页面类型
     * 
     * @see #getType()
     * @see #setType(String)
     */
    private String type;
    /**
     * 页面描述
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
    /**
     * 状态标记
     * 
     * @see #getFlag()
     * @see #setFlag(String)
     */
    private String flag;
    /**
     * 页面未选中图片
     * 
     * @see #getImgUrl()
     * @see #setImgUrl(String)
     */
    private String imgUrl;
    /**
     * 页面选中图片
     * 
     * @see #getImgUrlSeleted()
     * @see #setImgUrlSeleted(String)
     */
    private String imgUrlSelected;

    /**
     * 获取<code>imgUrlSelected</code>ID值
     * 
     * @return imgUrlSelected
     *         {@link com.ningpai.manager.bean.Page#imgUrlSelected}
     */
    public String getImgUrlSelected() {
        return imgUrlSelected;
    }

    /**
     * 设置<code>imgUrlSelected</code>ID值
     * 
     * @param imgUrlSelected
     *            {@link com.ningpai.manager.bean.Page#imgUrlSelected}
     */
    public void setImgUrlSelected(String imgUrlSelected) {
        this.imgUrlSelected = imgUrlSelected;
    }

    /**
     * 获取<code>imgUrl</code>ID值
     * 
     * @return imgUrl{@link com.ningpai.manager.bean.Page#imgUrl}
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置<code>imgUrl</code>ID值
     * 
     * @param imgUrl
     *            {@link com.ningpai.manager.bean.Page#imgUrl}
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取<code>Page</code>ID值
     * 
     * @return id{@link com.ningpai.manager.bean.Page#id}
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置<code>Page</code>ID值
     * 
     * @param id
     *            {@link com.ningpai.manager.bean.Page#id}
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取<code>Page</code>designation
     * 
     * @return designation{@link com.ningpai.manager.bean.Page#designation}
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * 设置<code>Page</code>designation
     * 
     * @param designation
     *            {@link com.ningpai.manager.bean.Page#designation}
     */
    public void setDesignation(String designation) {
        this.designation = designation;
    }

    /**
     * 获取<code>Page</code>url
     * 
     * @return url{@link com.ningpai.manager.bean.Page#url}
     */
    public String getUrl() {
        return url;
    }

    /**
     * 设置<code>Page</code>url
     * 
     * @param url
     *            {@link com.ningpai.manager.bean.Page#url}
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取<code>Page</code>characterization
     * 
     * @return characterization
     *         {@link com.ningpai.manager.bean.Page#characterization}
     */
    public String getCharacterization() {
        return characterization;
    }

    /**
     * 设置<code>Page</code>characterization
     * 
     * @param characterization
     *            {@link com.ningpai.manager.bean.Page#characterization}
     */
    public void setCharacterization(String characterization) {
        this.characterization = characterization;
    }

    /**
     * 获取<code>Page</code>createTime
     * 
     * @return createTime{@link com.ningpai.manager.bean.Page#createTime}
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Page</code>createTime
     * 
     * @param createTime
     *            {@link com.ningpai.manager.bean.Page#createTime}
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
     * 获取<code>Page</code>modTime
     * 
     * @return modTime{@link com.ningpai.manager.bean.Page#modTime}
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime());
        }
        return null;
    }

    /**
     * 设置<code>Page</code>modTime
     * 
     * @param modTime
     *            {@link com.ningpai.manager.bean.Page#modTime}
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
     * 获取<code>Page</code>flag
     * 
     * @return flag{@link com.ningpai.manager.bean.Page#flag}
     */
    public String getFlag() {
        return flag;
    }

    /**
     * 设置<code>Page</code>flag
     * 
     * @param flag
     *            {@link com.ningpai.manager.bean.Page#flag}
     */
    public void setFlag(String flag) {
        this.flag = flag;
    }

    /**
     * 获取<code>Page</code>parentId
     * 
     * @return parentId{@link com.ningpai.manager.bean.Page#parentId}
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置<code>Page</code>parentId
     * 
     * @param parentId
     *            {@link com.ningpai.manager.bean.Page#parentId}
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取<code>Page</code>type
     * 
     * @return type{@link com.ningpai.manager.bean.Page#type}
     */
    public String getType() {
        return type;
    }

    /**
     * 设置<code>Page</code>type
     * 
     * @param type
     *            {@link com.ningpai.manager.bean.Page#type}
     */
    public void setType(String type) {
        this.type = type;
    }

    public Long getGrade() {
        return grade;
    }

    public void setGrade(Long grade) {
        this.grade = grade;
    }

    public Long getSort() {
        return sort;
    }

    public void setSort(Long sort) {
        this.sort = sort;
    }

}
