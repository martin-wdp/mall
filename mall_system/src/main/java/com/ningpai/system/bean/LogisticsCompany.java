package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * 物流公司设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:55:21
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("logisticsCompany")
public class LogisticsCompany implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getLogComId()
     * @see #setLogComId(int)
     */
    private int logComId;
    /**
     * 物流公司名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /**
     * 物流公司代码
     * 
     * @see #getCode()
     * @see #setCode(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String code;
    /**
     * 快递100物流公司代码
     * 
     * @see #getKuaidi100Code()
     * @see #setKuaidi100Code(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String kuaidi100Code;
    /** 物流公司代码显示值 */
    private String codeValue;
    /**
     * 网址
     * 
     * @see #getComUrl()
     * @see #setComUrl(String)
     */
    //@NotNull
    //@Pattern(regexp = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$")
    private String comUrl;
    /**
     * 询件网址
     * 
     * @see #getQueryUrl()
     * @see #setQueryUrl(String)
     */
    //@NotNull
   // @Pattern(regexp = "^http[s]?:\\/\\/([\\w-]+\\.)+[\\w-]+([\\w-./?%&=]*)?$")
    private String queryUrl;
    /**
     * 排序
     * 
     * @see #getSort()
     * @see #setSort(int)
     */
    private int sort;
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
     * @return int 记录ID {@link LogisticsCompany#logComId}
     */
    public final int getLogComId() {
        return this.logComId;
    }

    /**
     * 设置：记录ID
     * 
     * @param logComId
     *            记录ID
     */
    public final void setLogComId(final int logComId) {
        this.logComId = logComId;
    }

    /**
     * 取得：物流公司名称
     * 
     * @return String 物流公司名称 {@link LogisticsCompany#name}
     */
    public final String getName() {
        return this.name;
    }

    /**
     * 设置：物流公司名称
     * 
     * @param name
     *            物流公司名称
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * 取得：物流公司代码
     * 
     * @return String 物流公司代码 {@link LogisticsCompany#code}
     */
    public final String getCode() {
        return this.code;
    }

    /**
     * 设置：物流公司代码
     * 
     * @param code
     *            物流公司代码
     */
    public final void setCode(final String code) {
        this.code = code;
    }

    /**
     * 取得：物流公司代码显示值
     */
    public final String getCodeValue() {
        return this.codeValue;
    }

    /**
     * 设置：物流公司代码显示值
     */
    public final void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }

    /**
     * 取得：网址
     * 
     * @return String 网址 {@link LogisticsCompany#comUrl}
     */
    public final String getComUrl() {
        return this.comUrl;
    }

    /**
     * 设置：网址
     * 
     * @param comUrl
     *            网址
     */
    public final void setComUrl(final String comUrl) {
        this.comUrl = comUrl;
    }

    /**
     * 取得：询件网址
     * 
     * @return String 询件网址 {@link LogisticsCompany#queryUrl}
     */
    public final String getQueryUrl() {
        return this.queryUrl;
    }

    /**
     * 设置：询件网址
     * 
     * @param queryUrl
     *            询件网址
     */
    public final void setQueryUrl(final String queryUrl) {
        this.queryUrl = queryUrl;
    }

    /**
     * 取得：排序
     * 
     * @return int 排序 {@link LogisticsCompany#sort}
     */
    public final int getSort() {
        return this.sort;
    }

    /**
     * 设置：排序
     * 
     * @param sort
     *            排序
     */
    public final void setSort(final int sort) {
        this.sort = sort;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link LogisticsCompany#usedStatus}
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
     * @return int 添加人 {@link LogisticsCompany#insertId}
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
     * @return Date 添加时间 {@link LogisticsCompany#insertDate}
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
     * @return int 修改人 {@link LogisticsCompany#modifyId}
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
     * @return Date 修改时间 {@link LogisticsCompany#modifyDate}
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
     * @return int 删除标识 {@link LogisticsCompany#deleteStatus}
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
     * 物流公司设置对象简明
     * 
     * @return 物流公司设置对象简明
     */
    public final String toString() {
        return "物流公司设置[" + this.logComId + "_" + this.name + "]";
    }

    public String getKuaidi100Code() {
        return kuaidi100Code;
    }

    public void setKuaidi100Code(String kuaidi100Code) {
        this.kuaidi100Code = kuaidi100Code;
    }

}
