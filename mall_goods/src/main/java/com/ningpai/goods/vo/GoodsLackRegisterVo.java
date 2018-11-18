/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.goods.vo;

import java.util.Date;

/**
 * 到货通知VO
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2013年12月18日 上午10:30:34
 * @version 1.0
 */
public class GoodsLackRegisterVo {
    /*
    *主键ID
     */
    private Long lackRegisterId;

    /*
    *货品ID
     */
    private Long goodsInfoId;

    /*
    *登记的会员ID
     */
    private Long customerId;

    /*
    *通知状态
     */
    private String lackRegisterNoticeStatus;

    /*
    *登记时间
     */
    private Date lackRegisterTime;

    /*
    *通知时间
     */
    private Date lackRegisterNoticeTime;

    /*
    *会员的用户名
     */
    private String customerUsername;

    /*
    *会员手机
     */
    private String infoMobile;

    /*
    *会员邮箱
     */
    private String infoEmail;

    /*
    *库存状态
     */
    private Long goodsInfoStock;

    /*
    *商品名称
     */
    private String goodsName;

    /*
    *货号
     */
    private String goodsInfoItemNo;

    public Long getLackRegisterId() {
        return lackRegisterId;
    }

    public void setLackRegisterId(Long lackRegisterId) {
        this.lackRegisterId = lackRegisterId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getLackRegisterNoticeStatus() {
        return lackRegisterNoticeStatus;
    }

    public void setLackRegisterNoticeStatus(String lackRegisterNoticeStatus) {
        this.lackRegisterNoticeStatus = lackRegisterNoticeStatus;
    }
    /**
     * 获取创建时间
     * */
    public Date getLackRegisterTime() {
        if (this.lackRegisterTime != null) {
            return new Date(this.lackRegisterTime.getTime());
        }
        return null;
    }
    /**
     * 设置创建时间
     * */
    public void setLackRegisterTime(Date lackRegisterTime) {
        if (lackRegisterTime != null) {
            Date tEmp = (Date) lackRegisterTime.clone();
            if (tEmp != null) {
                this.lackRegisterTime = tEmp;
            }
        }
    }
    /**
     * 获取通知时间
     * */
    public Date getLackRegisterNoticeTime() {
        if (this.lackRegisterNoticeTime != null) {
            return new Date(this.lackRegisterNoticeTime.getTime());
        }
        return null;
    }
    /**
     * 设置通知时间
     * */
    public void setLackRegisterNoticeTime(Date lackRegisterNoticeTime) {
        if (lackRegisterNoticeTime != null) {
            Date tEmp = (Date) lackRegisterNoticeTime.clone();
            if (tEmp != null) {
                this.lackRegisterNoticeTime = tEmp;
            }
        }
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getInfoMobile() {
        return infoMobile;
    }

    public void setInfoMobile(String infoMobile) {
        this.infoMobile = infoMobile;
    }

    public String getInfoEmail() {
        return infoEmail;
    }

    public void setInfoEmail(String infoEmail) {
        this.infoEmail = infoEmail;
    }

    public Long getGoodsInfoStock() {
        return goodsInfoStock;
    }

    public void setGoodsInfoStock(Long goodsInfoStock) {
        this.goodsInfoStock = goodsInfoStock;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }
}
