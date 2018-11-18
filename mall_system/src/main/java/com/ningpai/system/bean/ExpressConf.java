package com.ningpai.system.bean;

import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.stereotype.Component;

/**
 * 配送方式设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-26 16:38:32
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("expressConf")
public class ExpressConf implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getExpressId()
     * @see #setExpressId(int)
     */
    private int expressId;
    /**
     * 配送方式名称
     * 
     * @see #getName()
     * @see #setName(String)
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String name;
    /**
     * 物流公司id
     * 
     * @see #getExpress()
     * @see #setExpress(int)
     */
    private int express;
    /** 配送公司显示值 */
    private String expressValue;
    /**
     * 运费
     * 
     * @see #getPrice()
     * @see #setPrice(double)
     */
    @NotNull
    @Digits(integer = 20, fraction = 2)
    private double price;
    /**
     * 承运公司id
     * 
     * @see #getSendExpress()
     * @see #setSendExpress(int)
     */
    private int sendExpress;
    /** 承运公司显示值 */
    private String sendExpressValue;
    /**
     * 备注
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
     * 自提标识
     * 
     * @see #getPickupFlag()
     * @see #setPickupFlag(String)
     */
    private String pickupFlag;

    /**
     * 取得：记录ID
     * 
     * @return int 记录ID {@link ExpressConf#expressId}
     */
    public final int getExpressId() {
        return this.expressId;
    }

    /**
     * 设置：记录ID
     * 
     * @param expressId
     *            记录ID
     */
    public final void setExpressId(final int expressId) {
        this.expressId = expressId;
    }

    /**
     * 取得：配送公司
     * 
     * @return int 配送公司 {@link ExpressConf#express}
     */
    public final int getExpress() {
        return this.express;
    }

    /**
     * 设置：配送公司
     * 
     * @param express
     *            配送公司
     */
    public final void setExpress(final int express) {
        this.express = express;
    }

    /**
     * 取得：配送公司显示值
     */
    public final String getExpressValue() {
        return this.expressValue;
    }

    /**
     * 设置：配送公司显示值
     */
    public final void setExpressValue(String expressValue) {
        this.expressValue = expressValue;
    }

    /**
     * 取得：运费
     * 
     * @return double 运费 {@link ExpressConf#price}
     */
    public final double getPrice() {
        return this.price;
    }

    /**
     * 设置：运费
     * 
     * @param price
     *            运费
     */
    public final void setPrice(final double price) {
        this.price = price;
    }

    /**
     * 取得：承运公司
     * 
     * @return int 承运公司 {@link ExpressConf#sendExpress}
     */
    public final int getSendExpress() {
        return this.sendExpress;
    }

    /**
     * 设置：承运公司
     * 
     * @param sendExpress
     *            承运公司
     */
    public final void setSendExpress(final int sendExpress) {
        this.sendExpress = sendExpress;
    }

    /**
     * 取得：承运公司显示值
     */
    public final String getSendExpressValue() {
        return this.sendExpressValue;
    }

    /**
     * 设置：承运公司显示值
     */
    public final void setSendExpressValue(String sendExpressValue) {
        this.sendExpressValue = sendExpressValue;
    }

    /**
     * 取得：备注
     * 
     * @return String 备注 {@link ExpressConf#des}
     */
    public final String getDes() {
        return this.des;
    }

    /**
     * 设置：备注
     * 
     * @param des
     *            备注
     */
    public final void setDes(final String des) {
        this.des = des;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link ExpressConf#usedStatus}
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
     * @return String 扩展字段1 {@link ExpressConf#expFleid1}
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
     * @return String 扩展字段2 {@link ExpressConf#expFleid2}
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
     * @return int 添加人 {@link ExpressConf#insertId}
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
     * @return Date 添加时间 {@link ExpressConf#insertDate}
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
     * @return int 修改人 {@link ExpressConf#modifyId}
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
     * @return Date 修改时间 {@link ExpressConf#modifyDate}
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
     * @return int 删除标识 {@link ExpressConf#deleteStatus}
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
     * 配送方式设置对象简明
     * 
     * @return 配送方式设置对象简明
     */
    public final String toString() {
        return "配送方式设置[" + this.expressId + "_" + this.express + "]";
    }

    public String getPickupFlag() {
        return pickupFlag;
    }

    public void setPickupFlag(String pickupFlag) {
        this.pickupFlag = pickupFlag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
