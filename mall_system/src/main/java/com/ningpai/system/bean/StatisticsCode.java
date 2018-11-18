package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * 统计代码
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:03:45
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("statisticsCode")
public class StatisticsCode implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getStaCodeId()
     * @see #setStaCodeId(int)
     */
    private int staCodeId;
    /**
     * 统计方案标题
     * 
     * @see #getStaTitle()
     * @see #setStaTitle(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String staTitle;
    /**
     * 统计方案代码
     * 
     * @see #getStaStyle()
     * @see #setStaStyle(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String staStyle;
    /**
     * 模块名
     * 
     * @see #getModule()
     * @see #setModule(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String module;
    /**
     * 代码块
     * 
     * @see #getCode()
     * @see #setCode(String)
     */
    @NotNull
    private String code;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
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
     * @return int 记录ID {@link StatisticsCode#staCodeId}
     */
    public final int getStaCodeId() {
        return this.staCodeId;
    }

    /**
     * 设置：记录ID
     * 
     * @param staCodeId
     *            记录ID
     */
    public final void setStaCodeId(final int staCodeId) {
        this.staCodeId = staCodeId;
    }

    /**
     * 取得：统计方案标题
     * 
     * @return String 统计方案标题 {@link StatisticsCode#staTitle}
     */
    public final String getStaTitle() {
        return this.staTitle;
    }

    /**
     * 设置：统计方案标题
     * 
     * @param staTitle
     *            统计方案标题
     */
    public final void setStaTitle(final String staTitle) {
        this.staTitle = staTitle;
    }

    /**
     * 取得：统计方案代码
     * 
     * @return String 统计方案代码 {@link StatisticsCode#staStyle}
     */
    public final String getStaStyle() {
        return this.staStyle;
    }

    /**
     * 设置：统计方案代码
     * 
     * @param staStyle
     *            统计方案代码
     */
    public final void setStaStyle(final String staStyle) {
        this.staStyle = staStyle;
    }

    /**
     * 取得：模块名
     * 
     * @return String 模块名 {@link StatisticsCode#module}
     */
    public final String getModule() {
        return this.module;
    }

    /**
     * 设置：模块名
     * 
     * @param module
     *            模块名
     */
    public final void setModule(final String module) {
        this.module = module;
    }

    /**
     * 取得：代码块
     * 
     * @return String 代码块 {@link StatisticsCode#code}
     */
    public final String getCode() {
        return this.code;
    }

    /**
     * 设置：代码块
     * 
     * @param code
     *            代码块
     */
    public final void setCode(final String code) {
        this.code = code;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link StatisticsCode#usedStatus}
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
     * 取得：添加人
     * 
     * @return int 添加人 {@link StatisticsCode#insertId}
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
     * @return Date 添加时间 {@link StatisticsCode#insertDate}
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
     * @return int 修改人 {@link StatisticsCode#modifyId}
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
     * @return Date 修改时间 {@link StatisticsCode#modifyDate}
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
     * @return int 删除标识 {@link StatisticsCode#deleteStatus}
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
     * 统计代码对象简明
     * 
     * @return 统计代码对象简明
     */
    public final String toString() {
        return "统计代码[" + this.staCodeId + "_" + this.staTitle + "]";
    }
}
