package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 异常页面设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 10:10:44
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("sysErrorPage")
public class SysErrorPage implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getErrorPageId()
     * @see #setErrorPageId(int)
     */
    private int errorPageId;
    /**
     * 设置方案标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    private String title;
    /**
     * 设置方案代码
     * 
     * @see #getSetCode()
     * @see #setSetCode(String)
     */
    private String setCode;
    /**
     * 页面名称
     * 
     * @see #getPageName()
     * @see #setPageName(String)
     */
    private String pageName;
    /**
     * 页面标题
     * 
     * @see #getPageTitle()
     * @see #setPageTitle(String)
     */
    private String pageTitle;
    /**
     * 页面描述
     * 
     * @see #getPageDes()
     * @see #setPageDes(String)
     */
    private String pageDes;
    /**
     * 异常页面模板URL
     * 
     * @see #getPage()
     * @see #setPage(String)
     */
    private String page;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 扩展字段1
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 扩展字段2
     * 
     * @see #getExpFleid2()
     * @see #setExpFleid2(String)
     */
    private String expFleid2;
    /**
     * 添加人
     * 
     * @see #getInsertId()
     * @see #setInsertId(int)
     */
    private int insertId;
    /**
     * 添加时间
     * 
     * @see #getInsertDate()
     * @see #setInsertDate(java.util.Date)
     */
    private Date insertDate;
    /**
     * 修改人
     * 
     * @see #getModifyId()
     * @see #setModifyId(int)
     */
    private int modifyId;
    /**
     * 修改时间
     * 
     * @see #getModifyDate()
     * @see #setModifyDate(java.util.Date)
     */
    private Date modifyDate;
    /**
     * 删除标识
     * 
     * @see #getDeleteStatus()
     * @see #setDeleteStatus(int)
     */
    private int deleteStatus;

    /**
     * 取得：记录ID
     * 
     * @return int 记录ID {@link SysErrorPage#errorPageId}
     */
    public final int getErrorPageId() {
        return this.errorPageId;
    }

    /**
     * 设置：记录ID
     * 
     * @param errorPageId
     *            记录ID
     */
    public final void setErrorPageId(final int errorPageId) {
        this.errorPageId = errorPageId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link SysErrorPage#title}
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * 设置：设置方案标题
     * 
     * @param title
     *            设置方案标题
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 取得：设置方案代码
     * 
     * @return String 设置方案代码 {@link SysErrorPage#setCode}
     */
    public final String getSetCode() {
        return this.setCode;
    }

    /**
     * 设置：设置方案代码
     * 
     * @param setCode
     *            设置方案代码
     */
    public final void setSetCode(final String setCode) {
        this.setCode = setCode;
    }

    /**
     * 取得：页面名称
     * 
     * @return String 页面名称 {@link SysErrorPage#pageName}
     */
    public final String getPageName() {
        return this.pageName;
    }

    /**
     * 设置：页面名称
     * 
     * @param pageName
     *            页面名称
     */
    public final void setPageName(final String pageName) {
        this.pageName = pageName;
    }

    /**
     * 取得：页面标题
     * 
     * @return String 页面标题 {@link SysErrorPage#pageTitle}
     */
    public final String getPageTitle() {
        return this.pageTitle;
    }

    /**
     * 设置：页面标题
     * 
     * @param pageTitle
     *            页面标题
     */
    public final void setPageTitle(final String pageTitle) {
        this.pageTitle = pageTitle;
    }

    /**
     * 取得：页面描述
     * 
     * @return String 页面描述 {@link SysErrorPage#pageDes}
     */
    public final String getPageDes() {
        return this.pageDes;
    }

    /**
     * 设置：页面描述
     * 
     * @param pageDes
     *            页面描述
     */
    public final void setPageDes(final String pageDes) {
        this.pageDes = pageDes;
    }

    /**
     * 取得：异常页面模板URL
     * 
     * @return String 异常页面模板URL {@link SysErrorPage#page}
     */
    public final String getPage() {
        return this.page;
    }

    /**
     * 设置：异常页面模板URL
     * 
     * @param page
     *            异常页面模板URL
     */
    public final void setPage(final String page) {
        this.page = page;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link SysErrorPage#usedStatus}
     */
    public final String getUsedStatus() {
        return this.usedStatus;
    }

    /**
     * 设置：启用
     * 
     * @param usedStatus
     *            启用
     */
    public final void setUsedStatus(final String usedStatus) {
        this.usedStatus = usedStatus;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link SysErrorPage#expFleid1}
     */
    public final String getExpFleid1() {
        return this.expFleid1;
    }

    /**
     * 设置：扩展字段1
     * 
     * @param expFleid1
     *            扩展字段1
     */
    public final void setExpFleid1(final String expFleid1) {
        this.expFleid1 = expFleid1;
    }

    /**
     * 取得：扩展字段2
     * 
     * @return String 扩展字段2 {@link SysErrorPage#expFleid2}
     */
    public final String getExpFleid2() {
        return this.expFleid2;
    }

    /**
     * 设置：扩展字段2
     * 
     * @param expFleid2
     *            扩展字段2
     */
    public final void setExpFleid2(final String expFleid2) {
        this.expFleid2 = expFleid2;
    }

    /**
     * 取得：添加人
     * 
     * @return int 添加人 {@link SysErrorPage#insertId}
     */
    public final int getInsertId() {
        return this.insertId;
    }

    /**
     * 设置：添加人
     * 
     * @param insertId
     *            添加人
     */
    public final void setInsertId(final int insertId) {
        this.insertId = insertId;
    }

    /**
     * 取得：添加时间
     * 
     * @return Date 添加时间 {@link SysErrorPage#insertDate}
     */
    public final Date getInsertDate() {
        if (this.insertDate != null) {
            return new Date(this.insertDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：添加时间
     * 
     * @param insertDate
     *            添加时间
     */
    public final void setInsertDate(final Date insertDate) {
        if (insertDate != null) {
            Date tEmp = (Date) insertDate.clone();
            if (tEmp != null) {
                this.insertDate = tEmp;
            }
        }
    }

    /**
     * 取得：修改人
     * 
     * @return int 修改人 {@link SysErrorPage#modifyId}
     */
    public final int getModifyId() {
        return this.modifyId;
    }

    /**
     * 设置：修改人
     * 
     * @param modifyId
     *            修改人
     */
    public final void setModifyId(final int modifyId) {
        this.modifyId = modifyId;
    }

    /**
     * 取得：修改时间
     * 
     * @return Date 修改时间 {@link SysErrorPage#modifyDate}
     */
    public final Date getModifyDate() {
        if (this.modifyDate != null) {
            return new Date(this.modifyDate.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置：修改时间
     * 
     * @param modifyDate
     *            修改时间
     */
    public final void setModifyDate(final Date modifyDate) {
        if (modifyDate != null) {
            Date tEmp = (Date) modifyDate.clone();
            if (tEmp != null) {
                this.modifyDate = tEmp;
            }
        }
    }

    /**
     * 取得：删除标识
     * 
     * @return int 删除标识 {@link SysErrorPage#deleteStatus}
     */
    public final int getDeleteStatus() {
        return this.deleteStatus;
    }

    /**
     * 设置：删除标识
     * 
     * @param deleteStatus
     *            删除标识
     */
    public final void setDeleteStatus(final int deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    /**
     * 异常页面设置对象简明
     * 
     * @return 异常页面设置对象简明
     */
    public final String toString() {
        return "异常页面设置[" + this.errorPageId + "_" + this.title + "]";
    }
}
