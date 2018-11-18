package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 验证设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 09:58:51
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("authCheckConf")
public class AuthCheckConf implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getCheckId()
     * @see #setCheckId(int)
     */
    private int checkId;
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
     * 邮箱验证
     * 
     * @see #getEmailCheck()
     * @see #setEmailCheck(String)
     */
    private String emailCheck;
    /**
     * 手机验证
     * 
     * @see #getPhoneCheck()
     * @see #setPhoneCheck(String)
     */
    private String phoneCheck;
    /**
     * 姓名验证
     * 
     * @see #getNameCheck()
     * @see #setNameCheck(String)
     */
    private String nameCheck;
    /**
     * 身份证验证
     * 
     * @see #getIdCardCheck()
     * @see #setIdCardCheck(String)
     */
    private String idCardCheck;
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
     * @return int 记录ID {@link AuthCheckConf#checkId}
     */
    public final int getCheckId() {
        return this.checkId;
    }

    /**
     * 设置：记录ID
     * 
     * @param checkId
     *            记录ID
     */
    public final void setCheckId(final int checkId) {
        this.checkId = checkId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link AuthCheckConf#title}
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
     * @return String 设置方案代码 {@link AuthCheckConf#setCode}
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
     * 取得：邮箱验证
     * 
     * @return String 邮箱验证 {@link AuthCheckConf#emailCheck}
     */
    public final String getEmailCheck() {
        return this.emailCheck;
    }

    /**
     * 设置：邮箱验证
     * 
     * @param emailCheck
     *            邮箱验证
     */
    public final void setEmailCheck(final String emailCheck) {
        this.emailCheck = emailCheck;
    }

    /**
     * 取得：手机验证
     * 
     * @return String 手机验证 {@link AuthCheckConf#phoneCheck}
     */
    public final String getPhoneCheck() {
        return this.phoneCheck;
    }

    /**
     * 设置：手机验证
     * 
     * @param phoneCheck
     *            手机验证
     */
    public final void setPhoneCheck(final String phoneCheck) {
        this.phoneCheck = phoneCheck;
    }

    /**
     * 取得：姓名验证
     * 
     * @return String 姓名验证 {@link AuthCheckConf#nameCheck}
     */
    public final String getNameCheck() {
        return this.nameCheck;
    }

    /**
     * 设置：姓名验证
     * 
     * @param nameCheck
     *            姓名验证
     */
    public final void setNameCheck(final String nameCheck) {
        this.nameCheck = nameCheck;
    }

    /**
     * 取得：身份证验证
     * 
     * @return String 身份证验证 {@link AuthCheckConf#idCardCheck}
     */
    public final String getIdCardCheck() {
        return this.idCardCheck;
    }

    /**
     * 设置：身份证验证
     * 
     * @param idCardCheck
     *            身份证验证
     */
    public final void setIdCardCheck(final String idCardCheck) {
        this.idCardCheck = idCardCheck;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link AuthCheckConf#usedStatus}
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
     * @return String 扩展字段1 {@link AuthCheckConf#expFleid1}
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
     * @return String 扩展字段2 {@link AuthCheckConf#expFleid2}
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
     * @return int 添加人 {@link AuthCheckConf#insertId}
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
     * @return Date 添加时间 {@link AuthCheckConf#insertDate}
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
     * @return int 修改人 {@link AuthCheckConf#modifyId}
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
     * @return Date 修改时间 {@link AuthCheckConf#modifyDate}
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
     * @return int 删除标识 {@link AuthCheckConf#deleteStatus}
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
     * 验证设置对象简明
     * 
     * @return 验证设置对象简明
     */
    public final String toString() {
        return "验证设置[" + this.checkId + "_" + this.title + "]";
    }
}
