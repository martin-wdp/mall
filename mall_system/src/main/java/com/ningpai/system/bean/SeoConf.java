package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * SEO设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 13:35:13
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("seoConf")
public class SeoConf implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getSeoId()
     * @see #setSeoId(int)
     */
    private int seoId;
    /**
     * 设置方案标题
     * 
     * @see #getSeoTitle()
     * @see #setSeoTitle(String)
     */
    
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String seoTitle;
    /**
     * 设置方案代码
     * 
     * @see #getSeoCode()
     * @see #setSeoCode(String)
     */
    
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String seoCode;
    /**
     * 页面标题
     * 
     * @see #getMete()
     * @see #setMete(String)
     */  
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String mete;
    /**
     * 关键字
     * 
     * @see #getMeteKey()
     * @see #setMeteKey(String)
     */

    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\]+")
    private String meteKey;
    /**
     * 启用
     * 
     * @see #getUsedStatus()
     * @see #setUsedStatus(String)
     */
    private String usedStatus;
    /**
     * 0 PC版
     * 1 电脑版
     */
    private String belongPlate;
    /**
     * 1 SEO设置
     * 2 网站地图设置
     * 3 Robots文件设置
     */
    private String type;
    /**
     * 描述信息
     * 
     * @see #getMeteDes()
     * @see #setMeteDes(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\]+|()")
    private String meteDes;
    /**
     * 扩展字段1
     * 
     * @see #getExpFleid1()
     * @see #setExpFleid1(String)
     */
    private String expFleid1;
    /**
     * 网站地图url
     */
    private String url;
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
     * @return int 记录ID {@link SeoConf#seoId}
     */
    public final int getSeoId() {
        return this.seoId;
    }

    /**
     * 设置：记录ID
     * 
     * @param seoId
     *            记录ID
     */
    public final void setSeoId(final int seoId) {
        this.seoId = seoId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link SeoConf#seoTitle}
     */
    public final String getSeoTitle() {
        return this.seoTitle;
    }

    /**
     * 设置：设置方案标题
     * 
     * @param seoTitle
     *            设置方案标题
     */
    public final void setSeoTitle(final String seoTitle) {
        this.seoTitle = seoTitle;
    }

    /**
     * 取得：设置方案代码
     * 
     * @return String 设置方案代码 {@link SeoConf#seoCode}
     */
    public final String getSeoCode() {
        return this.seoCode;
    }

    /**
     * 设置：设置方案代码
     * 
     * @param seoCode
     *            设置方案代码
     */
    public final void setSeoCode(final String seoCode) {
        this.seoCode = seoCode;
    }

    /**
     * 取得：页面标题
     * 
     * @return String 页面标题 {@link SeoConf#mete}
     */
    public final String getMete() {
        return this.mete;
    }

    /**
     * 设置：页面标题
     * 
     * @param mete
     *            页面标题
     */
    public final void setMete(final String mete) {
        this.mete = mete;
    }

    /**
     * 取得：关键字
     * 
     * @return String 关键字 {@link SeoConf#meteKey}
     */
    public final String getMeteKey() {
        return this.meteKey;
    }

    /**
     * 设置：关键字
     * 
     * @param meteKey
     *            关键字
     */
    public final void setMeteKey(final String meteKey) {
        this.meteKey = meteKey;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link SeoConf#usedStatus}
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
     * @return String 描述信息 {@link SeoConf#meteDes}
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
     * @return String 扩展字段1 {@link SeoConf#expFleid1}
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
     * @return String 扩展字段2 {@link SeoConf#expFleid2}
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
     * @return int 添加人 {@link SeoConf#insertId}
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

    public String getBelongPlate() {
        return belongPlate;
    }

    public void setBelongPlate(String belongPlate) {
        this.belongPlate = belongPlate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    /**
     * 取得：添加时间
     * 
     * @return Date 添加时间 {@link SeoConf#insertDate}
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
     * @return int 修改人 {@link SeoConf#modifyId}
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
     * @return Date 修改时间 {@link SeoConf#modifyDate}
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
     * @return int 删除标识 {@link SeoConf#deleteStatus}
     */
    public final int getDeleteStatus() {
        return this.deleteStatus;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
     * SEO设置对象简明
     * 
     * @return SEO设置对象简明
     */
    public final String toString() {
        return "SEO设置[" + this.seoId + "_" + this.seoTitle + "]";
    }
}
