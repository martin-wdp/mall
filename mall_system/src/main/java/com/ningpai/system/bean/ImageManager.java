package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 图片管理
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 14:33:31
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("imageManager")
public class ImageManager implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getImageId()
     * @see #setImageId(int)
     */
    private int imageId;
    /**
     * 图片标题
     * 
     * @see #getTitle()
     * @see #setTitle(String)
     */
    private String title;
    /**
     * 图片尺寸
     * 
     * @see #getSize()
     * @see #setSize(String)
     */
    private String size;
    /**
     * 原图
     * 
     * @see #getOrginalUrl()
     * @see #setOrginalUrl(String)
     */
    private String orginalUrl;
    /**
     * 大图
     * 
     * @see #getBigUrl()
     * @see #setBigUrl(String)
     */
    private String bigUrl;
    /**
     * 中图
     * 
     * @see #getMiddleUrl()
     * @see #setMiddleUrl(String)
     */
    private String middleUrl;
    /**
     * 小图
     * 
     * @see #getSmallUrl()
     * @see #setSmallUrl(String)
     */
    private String smallUrl;
    /**
     * 存储引擎
     * 
     * @see #getStorageEngine()
     * @see #setStorageEngine(String)
     */
    private String storageEngine;
    /**
     * 水印
     * 
     * @see #getWatermark()
     * @see #setWatermark(String)
     */
    private String watermark;
    /**
     * 标签
     * 
     * @see #getTag()
     * @see #setTag(int)
     */
    private int tag;
    /** 标签显示值 */
    private String tagValue;
    /**
     * 图标类型
     * 
     * @see #getType()
     * @see #setType(String)
     */
    private String type;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 描述信息
     * 
     * @see #getMeteDes()
     * @see #setMeteDes(String)
     */
    private String meteDes;
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
     * @return int 记录ID {@link ImageManager#imageId}
     */
    public final int getImageId() {
        return this.imageId;
    }

    /**
     * 设置：记录ID
     * 
     * @param imageId
     *            记录ID
     */
    public final void setImageId(final int imageId) {
        this.imageId = imageId;
    }

    /**
     * 取得：图片标题
     * 
     * @return String 图片标题 {@link ImageManager#title}
     */
    public final String getTitle() {
        return this.title;
    }

    /**
     * 设置：图片标题
     * 
     * @param title
     *            图片标题
     */
    public final void setTitle(final String title) {
        this.title = title;
    }

    /**
     * 取得：图片尺寸
     * 
     * @return String 图片尺寸 {@link ImageManager#size}
     */
    public final String getSize() {
        return this.size;
    }

    /**
     * 设置：图片尺寸
     * 
     * @param size
     *            图片尺寸
     */
    public final void setSize(final String size) {
        this.size = size;
    }

    /**
     * 取得：原图
     * 
     * @return String 原图 {@link ImageManager#orginalUrl}
     */
    public final String getOrginalUrl() {
        return this.orginalUrl;
    }

    /**
     * 设置：原图
     * 
     * @param orginalUrl
     *            原图
     */
    public final void setOrginalUrl(final String orginalUrl) {
        this.orginalUrl = orginalUrl;
    }

    /**
     * 取得：大图
     * 
     * @return String 大图 {@link ImageManager#bigUrl}
     */
    public final String getBigUrl() {
        return this.bigUrl;
    }

    /**
     * 设置：大图
     * 
     * @param bigUrl
     *            大图
     */
    public final void setBigUrl(final String bigUrl) {
        this.bigUrl = bigUrl;
    }

    /**
     * 取得：中图
     * 
     * @return String 中图 {@link ImageManager#middleUrl}
     */
    public final String getMiddleUrl() {
        return this.middleUrl;
    }

    /**
     * 设置：中图
     * 
     * @param middleUrl
     *            中图
     */
    public final void setMiddleUrl(final String middleUrl) {
        this.middleUrl = middleUrl;
    }

    /**
     * 取得：小图
     * 
     * @return String 小图 {@link ImageManager#smallUrl}
     */
    public final String getSmallUrl() {
        return this.smallUrl;
    }

    /**
     * 设置：小图
     * 
     * @param smallUrl
     *            小图
     */
    public final void setSmallUrl(final String smallUrl) {
        this.smallUrl = smallUrl;
    }

    /**
     * 取得：存储引擎
     * 
     * @return String 存储引擎 {@link ImageManager#storageEngine}
     */
    public final String getStorageEngine() {
        return this.storageEngine;
    }

    /**
     * 设置：存储引擎
     * 
     * @param storageEngine
     *            存储引擎
     */
    public final void setStorageEngine(final String storageEngine) {
        this.storageEngine = storageEngine;
    }

    /**
     * 取得：水印
     * 
     * @return String 水印 {@link ImageManager#watermark}
     */
    public final String getWatermark() {
        return this.watermark;
    }

    /**
     * 设置：水印
     * 
     * @param watermark
     *            水印
     */
    public final void setWatermark(final String watermark) {
        this.watermark = watermark;
    }

    /**
     * 取得：标签
     * 
     * @return int 标签 {@link ImageManager#tag}
     */
    public final int getTag() {
        return this.tag;
    }

    /**
     * 设置：标签
     * 
     * @param tag
     *            标签
     */
    public final void setTag(final int tag) {
        this.tag = tag;
    }

    /**
     * 取得：标签显示值
     */
    public final String getTagValue() {
        return this.tagValue;
    }

    /**
     * 设置：标签显示值
     */
    public final void setTagValue(String tagValue) {
        this.tagValue = tagValue;
    }

    /**
     * 取得：图标类型
     * 
     * @return String 图标类型 {@link ImageManager#type}
     */
    public final String getType() {
        return this.type;
    }

    /**
     * 设置：图标类型
     * 
     * @param type
     *            图标类型
     */
    public final void setType(final String type) {
        this.type = type;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link ImageManager#usedStatus}
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
     * 取得：描述信息
     * 
     * @return String 描述信息 {@link ImageManager#meteDes}
     */
    public final String getMeteDes() {
        return this.meteDes;
    }

    /**
     * 设置：描述信息
     * 
     * @param meteDes
     *            描述信息
     */
    public final void setMeteDes(final String meteDes) {
        this.meteDes = meteDes;
    }

    /**
     * 取得：扩展字段1
     * 
     * @return String 扩展字段1 {@link ImageManager#expFleid1}
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
     * @return String 扩展字段2 {@link ImageManager#expFleid2}
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
     * @return int 添加人 {@link ImageManager#insertId}
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
     * @return Date 添加时间 {@link ImageManager#insertDate}
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
     * @return int 修改人 {@link ImageManager#modifyId}
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
     * @return Date 修改时间 {@link ImageManager#modifyDate}
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
     * @return int 删除标识 {@link ImageManager#deleteStatus}
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
     * 图片管理对象简明
     * 
     * @return 图片管理对象简明
     */
    public final String toString() {
        return "图片管理[" + this.imageId + "_" + this.imageId + "]";
    }
}
