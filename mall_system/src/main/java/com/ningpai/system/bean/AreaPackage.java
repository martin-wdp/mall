package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 地区设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("areaPackage")
public class AreaPackage implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getAreaPackageId()
     * @see #setAreaPackageId(int)
     */
    private int areaPackageId;
    /**
     * 地区包
     * 
     * @see #getAreaPackage()
     * @see #setAreaPackage(String)
     */
    private String areaPackage;
    /**
     * 地区设置
     * 
     * @see #getArea()
     * @see #setArea(String)
     */
    private String area;
    /**
     * 默认
     * 
     * @see #getDefaultPackage()
     * @see #setDefaultPackage(String)
     */
    private String defaultPackage;
    /**
     * 描述信息
     * 
     * @see #getDes()
     * @see #setDes(String)
     */
    private String des;
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
     * @return int 记录ID {@link AreaPackage#areaPackageId}
     */
    public final int getAreaPackageId() {
        return this.areaPackageId;
    }

    /**
     * 设置：记录ID
     * 
     * @param areaPackageId
     *            记录ID
     */
    public final void setAreaPackageId(final int areaPackageId) {
        this.areaPackageId = areaPackageId;
    }

    /**
     * 取得：地区包
     * 
     * @return String 地区包 {@link AreaPackage#areaPackage}
     */
    public final String getAreaPackage() {
        return this.areaPackage;
    }

    /**
     * 设置：地区包
     * 
     * @param areaPackage
     *            地区包
     */
    public final void setAreaPackage(final String areaPackage) {
        this.areaPackage = areaPackage;
    }

    /**
     * 取得：地区设置
     * 
     * @return String 地区设置 {@link AreaPackage#area}
     */
    public final String getArea() {
        return this.area;
    }

    /**
     * 设置：地区设置
     * 
     * @param area
     *            地区设置
     */
    public final void setArea(final String area) {
        this.area = area;
    }

    /**
     * 取得：默认
     * 
     * @return String 默认 {@link AreaPackage#defaultPackage}
     */
    public final String getDefaultPackage() {
        return this.defaultPackage;
    }

    /**
     * 设置：默认
     * 
     * @param defaultPackage
     *            默认
     */
    public final void setDefaultPackage(final String defaultPackage) {
        this.defaultPackage = defaultPackage;
    }

    /**
     * 取得：描述信息
     * 
     * @return String 描述信息 {@link AreaPackage#des}
     */
    public final String getDes() {
        return this.des;
    }

    /**
     * 设置：描述信息
     * 
     * @param des
     *            描述信息
     */
    public final void setDes(final String des) {
        this.des = des;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link AreaPackage#usedStatus}
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
     * @return String 扩展字段1 {@link AreaPackage#expFleid1}
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
     * @return String 扩展字段2 {@link AreaPackage#expFleid2}
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
     * @return int 添加人 {@link AreaPackage#insertId}
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
     * @return Date 添加时间 {@link AreaPackage#insertDate}
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
     * @return int 修改人 {@link AreaPackage#modifyId}
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
     * @return Date 修改时间 {@link AreaPackage#modifyDate}
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
     * @return int 删除标识 {@link AreaPackage#deleteStatus}
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
     * 地区设置对象简明
     * 
     * @return 地区设置对象简明
     */
    public final String toString() {
        return "地区设置[" + this.areaPackageId + "_" + this.area + "]";
    }
}
