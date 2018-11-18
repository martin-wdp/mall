/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.vo;

import com.ningpai.customer.bean.CustomerAddress;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单信息
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月15日17:51:03
 * @version 0.0.1
 */
public class OrderInfoBean {

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 会员编号
     */
    private Long customerId;
    /**
     * 商品总金额
     */
    private BigDecimal moneyPaid;

    /**
     * 订单出库状态
     */
    private String orderCargoStatus;
    /**
     * 买家留言
     */
    private String customerRemark;
    /**
     * 购买时间
     * 
     * @see #getPayTime()
     * @see #setPayTime(Date)
     */
    private Date payTime;
    /**
     * 是否使用优惠劵
     */
    private String couponsDelfag;
    /**
     * 优惠劵券码
     */
    private String couponNo;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品图片
     */
    private String goodsImg;
    /**
     * 退单金额
     */
    private BigDecimal backPrice;

    /**
     * 商品编号
     *
     * @see #getGoodsId()
     * @see #setGoodsId(Long)
     */
    private Long goodsId;
    /**
     * 商品列表
     */
    private List<GoodsBean> goods = new ArrayList<GoodsBean>(0);
    /**
     * 配送费用
     */
    private BigDecimal shippingFee;
    /**
     * 订单总额
     */
    private BigDecimal orderAmount;
    /**
     * 发货时间
     *
     * @see #getShippingTime()
     * @see #setShippingTime(Date)
     */
    private Date shippingTime;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 配送状态
     */
    private String shippingStatus;
    /**
     * 订单生成时间
     *
     */
    private Date addTime;
    /**
     * 配送方式编号
     *
     * @see #getDmId()
     * @see #setDmId(Long)
     */
    private Long dmId;
    /**
     * 支付编号
     *
     * @see #getPayId()
     * @see #setPayId(Long)
     */
    private Long payId;
    /**
     * 是否是上门自提 是 1， 否 0
     */
    private String orderExpressType;
    /**
     * 配送方式
     *
     * @see #getExpress()
     * @see #setExpress(ExpressBean)
     */
    private ExpressBean express;
    /**
     * 支付方式
     *
     * @see #getPay()
     * @see #setPay(PayBean)
     */
    private PayBean pay;

    /**
     * 支付方式 0 货到付款 1在线支付
     */
    private String orderLinePay;

    /**
     * 订单赠送积分
     *
     * @see #getAcquireIntegral()
     * @see #setAcquireIntegral(Long)
     */
    private Long acquireIntegral;
    /**
     * 订单完成时间
     *
     * @see #getSuccessTime()
     * @see #setSuccessTime(Date)
     */
    private Date successTime;

    /**
     * 收货地址编号
     *
     * @see #getAddressId()
     * @see #setAddressId(Long)
     */
    private Long addressId;
    /**
     * 收货地址
     *
     * @see #getAddress()
     * @see #setAddress(CustomerAddress)
     */
    private CustomerAddress address;
    /**
     * 发票类型
     */
    private String invoiceType;
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    /**
     * 发票内容
     *
     */
    private String invoiceContent;
    /**
     * 取消时间
     */
    private Date cancelTime;
    /**
     * 取消理由
     */
    private String cancelRemark;
    /**
     * 晒单状态 0代表没完成订单商品的晒单 1代表已完成订单商品的晒单
     */
    private Long shareFlag;
    /**
     * 原始金额
     */
    private BigDecimal oldPrice;
    /**
     * 优惠金额
     */
    private BigDecimal prePrice;

    /**
     * 收货省
     */
    private String shippingProvince;

    /**
     * 收货市
     */
    private String shippingCity;

    /**
     * 收货区县
     */
    private String shippingCounty;

    /**
     * 收货详细地址
     */
    private String shippingAddress;

    /**
     * 收货人
     */
    private String shippingPerson;

    /**
     * 收货电话
     */
    private String shippingPhone;

    /**
     * 收货手机
     */
    private String shippingMobile;

    /**
     * 物流订单
     */
    private List<ExpressNoVo> expressno;

    /**
     * 商家Id
     */
    private Long businessId;
    /**
     * 物流名称
     */
    private String expressName;
    /**
     * 订单积分
     */
    private Long orderIntegral;
    /**
     * 邮编
     */
    private String shippingPostcode;
    /**
     * 退单金额
     * 
     * @return
     */
    public BigDecimal getBackPrice() {
        return backPrice;
    }

    /**
     * 退单金额
     * 
     * @param backPrice
     */
    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public String getOrderLinePay() {
        return orderLinePay;
    }

    public void setOrderLinePay(String orderLinePay) {
        this.orderLinePay = orderLinePay;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType;
    }

    /**
     * 是否是上门自提 是 1， 否 0
     * 
     * @return
     */
    public String getOrderExpressType() {
        return this.orderExpressType;
    }

    /**
     * 优惠劵券码
     * 
     * @param couponNo
     */
    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    /**
     * 优惠劵券码
     * 
     * @return
     */
    public String getCouponNo() {
        return this.couponNo;
    }

    /**
     * 买家留言
     * 
     * @return
     */
    public String getCustomerRemark() {
        return customerRemark;
    }

    /**
     * 买家留言
     * 
     * @param customerRemark
     */
    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public List<ExpressNoVo> getExpressno() {
        return expressno;
    }

    public void setExpressno(List<ExpressNoVo> expressno) {
        this.expressno = expressno;
    }

    /**
     * 订单出库状态
     * 
     * @return
     */
    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    /**
     * 订单出库状态
     * 
     * @param orderCargoStatus
     */
    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

    public BigDecimal getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(BigDecimal oldPrice) {
        this.oldPrice = oldPrice;
    }

    public BigDecimal getPrePrice() {
        return prePrice;
    }

    public void setPrePrice(BigDecimal prePrice) {
        this.prePrice = prePrice;
    }

    public Long getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(Long shareFlag) {
        this.shareFlag = shareFlag;
    }

    public String getCancelRemark() {
        return cancelRemark;
    }

    public void setCancelRemark(String cancelRemark) {
        this.cancelRemark = cancelRemark;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getCancelTime() {
        // 如果取消时间不为空
        if (this.cancelTime != null) {
            // 返回取消时间
            return new Date(this.cancelTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     * 
     * @param cancelTime
     */
    public void setCancelTime(Date cancelTime) {
        // 如果取消时间不为空
        if (cancelTime != null) {
            // 获取取消时间
            Date tEmp = (Date) cancelTime.clone();
            if (tEmp != null) {
                // 设置
                this.cancelTime = tEmp;
            }
        }
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public String getInvoiceContent() {
        return invoiceContent;
    }

    public void setInvoiceContent(String invoiceContent) {
        this.invoiceContent = invoiceContent;
    }

    public CustomerAddress getAddress() {
        return address;
    }

    public void setAddress(CustomerAddress address) {
        this.address = address;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    /**
     * 商品编号
     * 
     * @return Long
     */
    public Long getGoodsId() {
        goodsId = getGoods().isEmpty() ? null : getGoods().get(0).getGoodsId();
        return goodsId;
    }

    /**
     * 商品编号
     * 
     * @param goodsId
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getSuccessTime() {
        // 如果成功时间不为空
        if (this.successTime != null) {
            // 返回成功时间
            return new Date(this.successTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     * 
     * @param successTime
     */
    public void setSuccessTime(Date successTime) {
        // 如果成功时间不为空
        if (successTime != null) {
            // 获取成功时间
            Date tEmp = (Date) successTime.clone();
            if (tEmp != null) {
                // 设置
                this.successTime = tEmp;
            }
        }
    }

    /**
     * 配送方式编号
     * 
     * @return
     */
    public Long getDmId() {
        return dmId;
    }

    /**
     * 配送方式编号
     * 
     * @param dmId
     */
    public void setDmId(Long dmId) {
        this.dmId = dmId;
    }

    /**
     * 支付编号
     * 
     * @return
     */
    public Long getPayId() {
        return payId;
    }

    /**
     * 支付编号
     * 
     * @param payId
     */
    public void setPayId(Long payId) {
        this.payId = payId;
    }

    /**
     * 配送方式
     * 
     * @return
     */
    public ExpressBean getExpress() {
        return express;
    }

    /**
     * 配送方式
     * 
     * @param express
     */
    public void setExpress(ExpressBean express) {
        this.express = express;
    }

    public PayBean getPay() {
        return pay;
    }

    public void setPay(PayBean pay) {
        this.pay = pay;
    }

    public Long getAcquireIntegral() {
        return acquireIntegral;
    }

    public void setAcquireIntegral(Long acquireIntegral) {
        this.acquireIntegral = acquireIntegral;
    }

    /**
     * 配送状态
     * 
     * @return
     */
    public String getShippingStatus() {
        return shippingStatus;
    }

    /**
     * 配送状态
     * 
     * @param shippingStatus
     */
    public void setShippingStatus(String shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    /**
     * 订单状态
     * 
     * @return
     */
    public String getOrderStatus() {
        return orderStatus;
    }

    /**
     * 会员编号
     * 
     * @return
     */
    public Long getCustomerId() {
        return customerId;
    }

    /**
     * 会员编号
     * 
     * @param customerId
     */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 订单状态
     * 
     * @param orderStatus
     */
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 配送费用
     * 
     * @return
     */
    public BigDecimal getShippingFee() {
        return shippingFee;
    }

    /**
     * 配送费用
     * 
     * @param shippingFee
     */
    public void setShippingFee(BigDecimal shippingFee) {
        this.shippingFee = shippingFee;
    }

    /**
     * 订单总额
     * 
     * @return
     */
    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    /**
     * 订单总额
     * 
     * @param orderAmount
     */
    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * 商品名称
     * 
     * @return
     */
    public String getGoodsName() {
        goodsName = getGoods().isEmpty() ? "" : getGoods().get(0).getGoodsName();
        return goodsName;
    }

    /**
     * 商品名称
     * 
     * @param goodsName
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 商品列表
     * 
     * @return
     */
    public List<GoodsBean> getGoods() {
        return goods;
    }

    /**
     * 商品列表
     * 
     * @param goods
     */
    public void setGoods(List<GoodsBean> goods) {
        this.goods = goods;
    }

    /**
     * 商品图片
     * 
     * @return String
     */
    public String getGoodsImg() {
        goodsImg = getGoods().isEmpty() ? "" : getGoods().get(0).getGoodsImg();
        return goodsImg;
    }

    /**
     * 商品图片
     * 
     * @param goodsImg
     */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    /**
     * 订单ID
     * 
     * @return
     */
    public Long getOrderId() {
        return orderId;
    }

    /**
     * 订单ID
     * 
     * @param orderId
     */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    /**
     * 订单编号
     * 
     * @return
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 订单编号
     * 
     * @param orderNo
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 商品总金额
     * 
     * @return
     */
    public BigDecimal getMoneyPaid() {
        return moneyPaid;
    }

    /**
     * 商品总金额
     * 
     * @param moneyPaid
     */
    public void setMoneyPaid(BigDecimal moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    /**
     * 是否使用优惠劵
     * 
     * @return
     */
    public String getCouponsDelfag() {
        return couponsDelfag;
    }

    /**
     * 是否使用优惠劵
     * 
     * @param couponsDelfag
     */
    public void setCouponsDelfag(String couponsDelfag) {
        this.couponsDelfag = couponsDelfag;
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getAddTime() {
        // 如果添加时间不为空
        if (this.addTime != null) {
            // 返回添加时间
            return new Date(this.addTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     * 
     * @param addTime
     */
    public void setAddTime(Date addTime) {
        // 如果添加时间不为空
        if (addTime != null) {
            // 获取添加时间
            Date tEmp = (Date) addTime.clone();
            if (tEmp != null) {
                // 设置
                this.addTime = tEmp;
            }
        }
    }

    /**
     * 发货时间
     * 
     * @return Date
     */
    public Date getShippingTime() {
        // 如果购买时间不为空
        if (this.shippingTime != null) {
            // 返回购买时间
            return new Date(this.shippingTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 发货时间
     * 
     * @param shippingTime
     */
    public void setShippingTime(Date shippingTime) {
        // 如果购买时间不为空
        if (shippingTime != null) {
            // 获取购买时间
            Date tEmp = (Date) shippingTime.clone();
            if (tEmp != null) {
                // 设置
                this.shippingTime = tEmp;
            }
        }
    }

    /**
     * 时间
     * 
     * @return Date
     */
    public Date getPayTime() {
        // 如果支付时间不为空
        if (this.payTime != null) {
            // 返回
            return new Date(this.payTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 时间
     * 
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        // 如果返回时间不为空
        if (payTime != null) {
            // 获取返回时间
            Date tEmp = (Date) payTime.clone();
            if (tEmp != null) {
                // 设置
                this.payTime = tEmp;
            }
        }
    }

    public String getShippingProvince() {
        return shippingProvince;
    }

    public void setShippingProvince(String shippingProvince) {
        this.shippingProvince = shippingProvince;
    }

    public String getShippingCity() {
        return shippingCity;
    }

    public void setShippingCity(String shippingCity) {
        this.shippingCity = shippingCity;
    }

    public String getShippingCounty() {
        return shippingCounty;
    }

    public void setShippingCounty(String shippingCounty) {
        this.shippingCounty = shippingCounty;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getShippingPerson() {
        return shippingPerson;
    }

    public void setShippingPerson(String shippingPerson) {
        this.shippingPerson = shippingPerson;
    }

    public String getShippingPhone() {
        return shippingPhone;
    }

    public void setShippingPhone(String shippingPhone) {
        this.shippingPhone = shippingPhone;
    }

    public String getShippingMobile() {
        return shippingMobile;
    }

    public void setShippingMobile(String shippingMobile) {
        this.shippingMobile = shippingMobile;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }
}
