package com.ningpai.system.bean;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * 在线客服
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:40:56
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("onLineService")
public class OnLineService implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getOnLineServiceId()
     * @see #setOnLineServiceId(int)
     */
    private int onLineServiceId;
    /**
     * 客服显示
     * 
     * @see #getOnlineIndex()
     * @see #setOnlineIndex(String)
     */
    private String onlineIndex;
    /**
     * 标题说明
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    @NotNull
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String title;
    /**
     * 客服类型
     * 
     * @see #getType()
     * @see #setType(String)
     */
    private String type;
    /**
     * 第三方商家ID
     * 
     * @see #getThirdStoreId()
     * @see #setThirdStoreId(int)
     */
    private int thirdStoreId;
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
    /** 客服项列表 */
    private List<OnLineServiceItem> itemList;

    public List<OnLineServiceItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<OnLineServiceItem> itemList) {
        this.itemList = itemList;
    }

    /**
     * 取得：记录ID
     * 
     * @return int 记录ID {@link OnLineService#onLineServiceId}
     */
    public final int getOnLineServiceId() {
        return this.onLineServiceId;
    }

    /**
     * 设置：记录ID
     * 
     * @param onLineServiceId
     *            记录ID
     */
    public final void setOnLineServiceId(final int onLineServiceId) {
        this.onLineServiceId = onLineServiceId;
    }

    /**
     * 取得：客服显示
     * 
     * @return String 客服显示 {@link OnLineService#onlineIndex}
     */
    public final String getOnlineIndex() {
        return this.onlineIndex;
    }

    /**
     * 设置：客服显示
     * 
     * @param onlineIndex
     *            客服显示
     */
    public final void setOnlineIndex(final String onlineIndex) {
        this.onlineIndex = onlineIndex;
    }

    /**
     * 取得：标题说明
     * 
     * @return String 标题说明 {@link OnLineService#title}
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * 设置：标题说明
     * 
     * @param title
     *            标题说明
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 取得：客服类型
     * 
     * @return String 客服类型 {@link OnLineService#type}
     */
    public final String getType() {
        return this.type;
    }

    /**
     * 设置：客服类型
     * 
     * @param type
     *            客服类型
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * 取得：第三方商家ID
     * 
     * @return int 第三方商家ID {@link OnLineService#thirdStoreId}
     */
    public final int getThirdStoreId() {
        return this.thirdStoreId;
    }

    /**
     * 设置：第三方商家ID
     * 
     * @param thirdStoreId
     *            第三方商家ID
     */
    public final void setThirdStoreId(final int thirdStoreId) {
        this.thirdStoreId = thirdStoreId;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link OnLineService#expFleid1}
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
     * @return String 扩展字段2 {@link OnLineService#expFleid2}
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
     * @return int 添加人 {@link OnLineService#insertId}
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
     * @return Date 添加时间 {@link OnLineService#insertDate}
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
     * @return int 修改人 {@link OnLineService#modifyId}
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
     * @return Date 修改时间 {@link OnLineService#modifyDate}
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
     * @return int 删除标识 {@link OnLineService#deleteStatus}
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
     * 在线客服对象简明
     * 
     * @return 在线客服对象简明
     */
    public final String toString() {
        return "在线客服[" + this.onLineServiceId + "_" + this.title + "]";
    }
}
