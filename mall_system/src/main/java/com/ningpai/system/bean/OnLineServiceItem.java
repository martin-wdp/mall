package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * 在线客服项
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:44:30
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("onLineServiceItem")
public class OnLineServiceItem implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getOnLineServiceItemId()
     * @see #setOnLineServiceItemId(int)
     */
    private int onLineServiceItemId;
    /**
     * 在线客服记录ID
     * 
     * @see #getOnLineServiceId()
     * @see #setOnLineServiceId(int)
     */
    private int onLineServiceId;
    /**
     * 客服名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String name;
    /**
     * 联系号码
     * 
     * @see #getContactNumber()
     * @see #setContactNumber(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String contactNumber;
    /**
     * 联系类型
     * 
     * @see #getContactType()
     * @see #setContactType(int)
     */
    private int contactType;
    /** 联系类型显示值 */
    private String contactTypeValue;
    /**
     * 员工ID
     * 
     * @see #getEmpId()
     * @see #setEmpId(int)
     */
    private int empId;
    /**
     * 排序
     * 
     * @see #getOnlineSort()
     * @see #setOnlineSort(int)
     */
    private int onlineSort;
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
     * 取得：记录ID
     * 
     * @return int 记录ID {@link OnLineServiceItem#onLineServiceItemId}
     */
    public final int getOnLineServiceItemId() {
        return this.onLineServiceItemId;
    }

    /**
     * 设置：记录ID
     * 
     * @param onLineServiceItemId
     *            记录ID
     */
    public final void setOnLineServiceItemId(final int onLineServiceItemId) {
        this.onLineServiceItemId = onLineServiceItemId;
    }

    /**
     * 取得：在线客服记录ID
     * 
     * @return int 在线客服记录ID {@link OnLineServiceItem#onLineServiceId}
     */
    public final int getOnLineServiceId() {
        return this.onLineServiceId;
    }

    /**
     * 设置：在线客服记录ID
     * 
     * @param onLineServiceId
     *            在线客服记录ID
     */
    public final void setOnLineServiceId(final int onLineServiceId) {
        this.onLineServiceId = onLineServiceId;
    }

    /**
     * 取得：客服名称
     * 
     * @return String 客服名称 {@link OnLineServiceItem#name}
     */
    public final String getName() {
        return this.name;
    }

    /**
     * 设置：客服名称
     * 
     * @param name
     *            客服名称
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * 取得：联系号码
     * 
     * @return String 联系号码 {@link OnLineServiceItem#contactNumber}
     */
    public final String getContactNumber() {
        return this.contactNumber;
    }

    /**
     * 设置：联系号码
     * 
     * @param contactNumber
     *            联系号码
     */
    public final void setContactNumber(final String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * 取得：联系类型
     * 
     * @return int 联系类型 {@link OnLineServiceItem#contactType}
     */
    public final int getContactType() {
        return this.contactType;
    }

    /**
     * 设置：联系类型
     * 
     * @param contactType
     *            联系类型
     */
    public final void setContactType(final int contactType) {
        this.contactType = contactType;
    }

    /**
     * 取得：联系类型显示值
     */
    public final String getContactTypeValue() {
        return this.contactTypeValue;
    }

    /**
     * 设置：联系类型显示值
     */
    public final void setContactTypeValue(String contactTypeValue) {
        this.contactTypeValue = contactTypeValue;
    }

    /**
     * 取得：员工ID
     * 
     * @return int 员工ID {@link OnLineServiceItem#empId}
     */
    public final int getEmpId() {
        return this.empId;
    }

    /**
     * 设置：员工ID
     * 
     * @param empId
     *            员工ID
     */
    public final void setEmpId(final int empId) {
        this.empId = empId;
    }

    /**
     * 取得：排序
     * 
     * @return int 排序 {@link OnLineServiceItem#onlineSort}
     */
    public final int getOnlineSort() {
        return this.onlineSort;
    }

    /**
     * 设置：排序
     * 
     * @param onlineSort
     *            排序
     */
    public final void setOnlineSort(final int onlineSort) {
        this.onlineSort = onlineSort;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link OnLineServiceItem#expFleid1}
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
     * @return String 扩展字段2 {@link OnLineServiceItem#expFleid2}
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
     * @return int 添加人 {@link OnLineServiceItem#insertId}
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
     * @return Date 添加时间 {@link OnLineServiceItem#insertDate}
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
     * 在线客服项对象简明
     * 
     * @return 在线客服项对象简明
     */
    public final String toString() {
        return "在线客服项[" + this.onLineServiceItemId + "_" + this.name + "]";
    }
}
