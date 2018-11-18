/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.ningpai.goods.vo.GoodsProductVo;

/**
 * 抢购团购订单表
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月3日16:34:38
 * 
 */
public class OrderVice {
    /**
     * 主键id
     */
    private Long orderId;

    /**
     * 订单编号
     */
    private String orderCode;

    /**
     * 收货地址
     */
    private String shippingAddress;

    /**
     * 手机
     */
    private String shippingMobile;

    /**
     * 收货人
     */
    private String shippingPerson;

    /**
     * 订单状态
     */
    private String orderStatus;

    /**
     * 订单类型 0：团购订单 1：抢购订单
     */
    private String orderType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 运单编号
     */
    private String expressNo;

    /**
     * 用户id
     */
    private Long customerId;

    /**
     * 付款时间
     */
    private Date payTime;

    /**
     * 发货时间
     */
    private Date sendExpressTime;

    /**
     * 客户留言
     */
    private String customerRemark;

    /**
     * 订单价格
     */
    private BigDecimal orderPrice;

    /**
     * 订单优惠金额
     */
    private BigDecimal orderPrePrice;

    /**
     * 商品id
     */
    private Long goodsInfoId;

    /**
     * 购买数量
     */
    private Long goodsNum;

    /**
     * 快递公司名称
     */
    private String expressName;

    /**
     * 商家id
     */
    private Long businessId;

    /**
     * 删除标记
     */
    private String delFlag;

    /**
     * 备用字段1
     */
    private String temp1;

    /**
     * 备用字段2
     */
    private String temp2;

    /**
     * 备用字段3
     */
    private String temp3;

    /**
     * 商家名称
     */
    private String storeName;

    /**
     * 物流id
     */
    private Long expressId;

    /**
     * 物流详细地址
     */
    private String shippingAddressDetail;

    /**
     * 团购id
     */
    private Long marketingId;

    /**
     * 商品实体
     */
    private GoodsProductVo goodsProductVo;

    /**
     * 地区id
     */
    private Long districtId;

    private String customerUsername;
    private String pointLevelName;

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getExpressId() {
        return expressId;
    }

    public void setExpressId(Long expressId) {
        this.expressId = expressId;
    }

    public String getShippingAddressDetail() {
        return shippingAddressDetail;
    }

    public void setShippingAddressDetail(String shippingAddressDetail) {
        this.shippingAddressDetail = shippingAddressDetail;
    }

    public GoodsProductVo getGoodsProductVo() {
        return goodsProductVo;
    }

    public void setGoodsProductVo(GoodsProductVo goodsProductVo) {
        this.goodsProductVo = goodsProductVo;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取支付时间
     * */
    public Date getPayTime() {
        return (Date) payTime.clone();
    }
    /**
     * 设置支付时间
     * */
    public void setPayTime(Date payTime) {
        this.payTime = payTime == null ? null : (Date) payTime.clone();
    }
    /**
     * 获取SendExpressTime
     * */
    public Date getSendExpressTime() {
        return (Date) sendExpressTime.clone();
    }
    /**
     * 设置SendExpressTime
     * */
    public void setSendExpressTime(Date sendExpressTime) {
        this.sendExpressTime = sendExpressTime == null ? null : (Date) sendExpressTime.clone();
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public BigDecimal getOrderPrePrice() {
        return orderPrePrice;
    }

    public void setOrderPrePrice(BigDecimal orderPrePrice) {
        this.orderPrePrice = orderPrePrice;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }
}
