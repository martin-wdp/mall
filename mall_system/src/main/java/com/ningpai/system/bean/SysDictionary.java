package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 系统字典
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("sysDictionary")
public class SysDictionary implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getDicId()
     * @see #setDicId(int)
     */
    private int dicId;
    /**
     * 父节点ID
     * 
     * @see #getParentId()
     * @see #setParentId(int)
     */
    private int parentId;
    /**
     * 字典名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    private String name;
    /**
     * 字典代码
     * 
     * @see #getCode()
     * @see #setCode(String)
     */
    private String code;
    /**
     * 启用状态
     * 
     * @see #getStatus()
     * @see #setStatus(String)
     */
    private String status;
    /**
     * 字典类型
     * 
     * @see #getType()
     * @see #setType(String)
     */
    private String type;
    /**
     * 描述信息
     * 
     * @see #getDes()
     * @see #setDes(String)
     */
    private String des;
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
     * 取得：记录ID
     * 
     * @return int 记录ID {@link SysDictionary#dicId}
     */
    public final int getDicId() {
        return this.dicId;
    }

    /**
     * 设置：记录ID
     * 
     * @param dicId
     *            记录ID
     */
    public final void setDicId(final int dicId) {
        this.dicId = dicId;
    }

    /**
     * 取得：父节点ID
     * 
     * @return int 父节点ID {@link SysDictionary#parentId}
     */
    public final int getParentId() {
        return this.parentId;
    }

    /**
     * 设置：父节点ID
     * 
     * @param parentId
     *            父节点ID
     */
    public final void setParentId(final int parentId) {
        this.parentId = parentId;
    }

    /**
     * 取得：字典名称
     * 
     * @return String 字典名称 {@link SysDictionary#name}
     */
    public final String getName() {
        return this.name;
    }

    /**
     * 设置：字典名称
     * 
     * @param name
     *            字典名称
     */
    public final void setName(final String name) {
        this.name = name;
    }

    /**
     * 取得：字典代码
     * 
     * @return String 字典代码 {@link SysDictionary#code}
     */
    public final String getCode() {
        return this.code;
    }

    /**
     * 设置：字典代码
     * 
     * @param code
     *            字典代码
     */
    public final void setCode(final String code) {
        this.code = code;
    }

    /**
     * 取得：启用状态
     * 
     * @return String 启用状态 {@link SysDictionary#status}
     */
    public final String getStatus() {
        return this.status;
    }

    /**
     * 设置：启用状态
     * 
     * @param status
     *            启用状态
     */
    public final void setStatus(final String status) {
        this.status = status;
    }

    /**
     * 取得：字典类型
     * 
     * @return String 字典类型 {@link SysDictionary#type}
     */
    public final String getType() {
        return this.type;
    }

    /**
     * 设置：字典类型
     * 
     * @param type
     *            字典类型
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * 取得：描述信息
     * 
     * @return String 描述信息 {@link SysDictionary#des}
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
     * 取得：添加人
     * 
     * @return int 添加人 {@link SysDictionary#insertId}
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
     * @return Date 添加时间 {@link SysDictionary#insertDate}
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
     * @return int 修改人 {@link SysDictionary#modifyId}
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
     * @return Date 修改时间 {@link SysDictionary#modifyDate}
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
     * 系统字典对象简明
     * 
     * @return 系统字典对象简明
     */
    public final String toString() {
        return "系统字典[" + this.dicId + "_" + this.name + "]";
    }
}
