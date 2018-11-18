package com.ningpai.system.bean;

import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * 购物设置
 * 
 * @Title: Entity
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 17:58:48
 * @version V1.0
 */
@SuppressWarnings("serial")
@Component("shopConf")
public class ShopConf implements java.io.Serializable {
    /**
     * 记录ID
     * 
     * @see #getShopId()
     * @see #setShopId(int)
     */
    private int shopId;
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
     * 登录购买
     * 
     * @see #getLoginShop()
     * @see #setLoginShop(String)
     */
    private String loginShop;
    /**
     * 离线购物车
     * 
     * @see #getOffLineShop()
     * @see #setOffLineShop(String)
     */
    private String offLineShop;
    /**
     * 非会员购物
     * 
     * @see #getNoMemberShop()
     * @see #setNoMemberShop(String)
     */
    private String noMemberShop;
    /**
     * 含税价格
     * 
     * @see #getContainTallagePrice()
     * @see #setContainTallagePrice(String)
     */
    private String containTallagePrice;
    /**
     * 税率
     * 
     * @see #getTaxRate()
     * @see #setTaxRate(double)
     */
    private double taxRate;
    /**
     * 邮编填写项
     * 
     * @see #getPostcodeInput()
     * @see #setPostcodeInput(String)
     */
    private String postcodeInput;
    /**
     * 配送时间
     * 
     * @see #getDeliveryTime()
     * @see #setDeliveryTime(String)
     */
    private String deliveryTime;
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
     * @return int 记录ID {@link ShopConf#shopId}
     */
    public final int getShopId() {
        return this.shopId;
    }

    /**
     * 设置：记录ID
     * 
     * @param shopId
     *            记录ID
     */
    public final void setShopId(final int shopId) {
        this.shopId = shopId;
    }

    /**
     * 取得：设置方案标题
     * 
     * @return String 设置方案标题 {@link ShopConf#title}
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
     * @return String 设置方案代码 {@link ShopConf#setCode}
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
     * 取得：登录购买
     * 
     * @return String 登录购买 {@link ShopConf#loginShop}
     */
    public final String getLoginShop() {
        return this.loginShop;
    }

    /**
     * 设置：登录购买
     * 
     * @param loginShop
     *            登录购买
     */
    public final void setLoginShop(final String loginShop) {
        this.loginShop = loginShop;
    }

    /**
     * 取得：离线购物车
     * 
     * @return String 离线购物车 {@link ShopConf#offLineShop}
     */
    public final String getOffLineShop() {
        return this.offLineShop;
    }

    /**
     * 设置：离线购物车
     * 
     * @param offLineShop
     *            离线购物车
     */
    public final void setOffLineShop(final String offLineShop) {
        this.offLineShop = offLineShop;
    }

    /**
     * 取得：非会员购物
     * 
     * @return String 非会员购物 {@link ShopConf#noMemberShop}
     */
    public final String getNoMemberShop() {
        return this.noMemberShop;
    }

    /**
     * 设置：非会员购物
     * 
     * @param noMemberShop
     *            非会员购物
     */
    public final void setNoMemberShop(final String noMemberShop) {
        this.noMemberShop = noMemberShop;
    }

    /**
     * 取得：含税价格
     * 
     * @return String 含税价格 {@link ShopConf#containTallagePrice}
     */
    public final String getContainTallagePrice() {
        return this.containTallagePrice;
    }

    /**
     * 设置：含税价格
     * 
     * @param containTallagePrice
     *            含税价格
     */
    public final void setContainTallagePrice(final String containTallagePrice) {
        this.containTallagePrice = containTallagePrice;
    }

    /**
     * 取得：税率
     * 
     * @return double 税率 {@link ShopConf#taxRate}
     */
    public final double getTaxRate() {
        return this.taxRate;
    }

    /**
     * 设置：税率
     * 
     * @param taxRate
     *            税率
     */
    public final void setTaxRate(final double taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * 取得：邮编填写项
     * 
     * @return String 邮编填写项 {@link ShopConf#postcodeInput}
     */
    public final String getPostcodeInput() {
        return this.postcodeInput;
    }

    /**
     * 设置：邮编填写项
     * 
     * @param postcodeInput
     *            邮编填写项
     */
    public final void setPostcodeInput(final String postcodeInput) {
        this.postcodeInput = postcodeInput;
    }

    /**
     * 取得：配送时间
     * 
     * @return String 配送时间 {@link ShopConf#deliveryTime}
     */
    public final String getDeliveryTime() {
        return this.deliveryTime;
    }

    /**
     * 设置：配送时间
     * 
     * @param deliveryTime
     *            配送时间
     */
    public final void setDeliveryTime(final String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    /**
     * 取得：启用
     * 
     * @return String 启用 {@link ShopConf#usedStatus}
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
     * @return String 扩展字段1 {@link ShopConf#expFleid1}
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
     * @return String 扩展字段2 {@link ShopConf#expFleid2}
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
     * @return int 添加人 {@link ShopConf#insertId}
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
     * @return Date 添加时间 {@link ShopConf#insertDate}
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
     * @return int 修改人 {@link ShopConf#modifyId}
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
     * @return Date 修改时间 {@link ShopConf#modifyDate}
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
     * @return int 删除标识 {@link ShopConf#deleteStatus}
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
     * 购物设置对象简明
     * 
     * @return 购物设置对象简明
     */
    public final String toString() {
        return "购物设置[" + this.shopId + "_" + this.title + "]";
    }
}
