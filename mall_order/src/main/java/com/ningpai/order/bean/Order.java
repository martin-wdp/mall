/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单主表 2014-04-09
 *
 * @author NINGPAI-LIH
 */
public class Order {
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单编号
     */
    private String orderCode;
    /**
     * 订单价格
     */
    private BigDecimal orderPrice;
    /**
     * 优惠价格
     */
    private BigDecimal orderPrePrice;
    /**
     * 订单优惠价格
     */
    private BigDecimal orderPrePriceOrder;
    /**
     * 原始价格
     */
    private BigDecimal orderOldPrice;
    /**
     * 订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
     */
    private String orderStatus;
    /**
     * 用户ID
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
     * 收货时间
     */
    private Date getGoodsTime;
    /**
     * 收货地址ID
     */
    private Long shoppingAddrId;
    /**
     * 收货省
     */
    private String shippingProvince;
    /**
     * 收货城市
     */
    private String shippingCity;
    /**
     * 收货区县
     */
    private String shippingCounty;
    /**
     * 收货区县id
     */
    private Long shippingCountyId;
    /**
     * 详细地址
     */
    private String shippingAddress;
    /**
     * 收货人
     */
    private String shippingPerson;
    /**
     * 电话
     */
    private String shippingPhone;
    /**
     * 手机
     */
    private String shippingMobile;
    /**
     * 发票抬头
     */
    private String invoiceTitle;
    /**
     * 发票内容
     */
    private String invoiceContent;
    /**
     * 是否删除
     */
    private String delFlag;
    /**
     * 发票类型
     */
    private String invoiceType;
    /**
     * 客户留言
     */
    private String customerRemark;
    /**
     * 支付ID
     */
    private Long payId;
    /**
     * 订单积分
     */
    private Long orderIntegral;
    /**
     * 优惠卷码
     */
    private String couponNo;
    /**
     * 运费
     */
    private BigDecimal expressPrice;
    /**
     * 订单促销
     */
    private List<OrderMarketing> orderMarketingList;
    /**
     * 订单商品
     */
    private List<OrderGoods> orderGoodsList;
    /**
     * 物流信息
     */
    private OrderExpress orderExpress;
    /**
     * 出库物流信息（用于显示在订单详情页 一个订单的会有N个商品 出库的时候也回有N个物流单）
     */
    private List<OrderContainerRelation> orderContainerRelations = new ArrayList<OrderContainerRelation>();
    /**
     * 退单金额
     */
    private String shippingPostcode;
    /**
     * 退单金额
     */
    private BigDecimal backPrice;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 商家名称
     */
    private String storeName;
    /**
     * 仓库名称
     */
    private String wareName;
    /**
     * 仓库ID
     */
    private Long wareId;
    /**
     * 类型 会员0 经销商1 第三方2
     */
    private String dealerType;
    /**
     * 主单Code
     */
    private String orderOldCode;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 订单开始时间，商家查询使用
     */
    private String startTime;
    /**
     * 订单结束时间，商家查询使用
     */
    private String endTime;
    /**
     * 订单取消时间
     */
    private Date orderCancelTime;
    /**
     * 配送方式 0：快递配送 1：上门自提
     */
    private String orderExpressType;
    /**
     * 付款开始时间，查询使用
     */
    private String payTimeStart;
    /**
     * 付款结束时间，查询使用
     */
    private String payTimeEnd;
    /**
     * 订单状态，查询时使用，不包括这些状态
     */
    private String alreadyPay;
    /**
     * 订单付款方式 1，线上支付 0，货到付款
     */
    private String orderLinePay;
    /**
     * 新增订单
     */
    private String orderNewStatus;
    /**
     * 用户名
     */
    private String customerUsername;
    /**
     * 用户昵称
     */
    private String customerNickname;
    /**
     * 用户等级
     */
    private String pointLevelName;
    /**
     * 日销售量
     */
    private Long dayCount;
    /**
     * 日销售额
     */
    private BigDecimal dayMoney;
    /**
     * 结束价格 （用于第三方价格区域查询）
     */
    private BigDecimal startprice;
    /**
     * 结束价格 （用于第三方价格区域查询）
     */
    private BigDecimal endprice;
    /**
     * 订单赠品
     */
    List<OrderGoodsInfo> orderGoodsInfos;
    /**
     * 退单原因
     */
    private String orderCancelRemark;
    /**
     * 订单出库状态 0:未拣货 1：已装箱 2：未出库
     */
    private String orderCargoStatus;
    /**
     * 判断是否是手机订单 0：PC订单 1：手机订单 2：微信订单
     */
    private String orderMType;
    /**
     * 业务员ID
     */
    private Long salesmanId;
    /**
     * 业务员姓名
     */
    private String salesmanName;
    /**
     * 业务员电话
     */
    private String salesmanPhone;
    /**
     * 业务员所属部门
     */
    private String salesmanDepartment;
    /**
     * 用户是否企业用户
     */
    private String isEnterprise;
    /**
     * 物流名称
     */
    private String expressName;
    /**
     * 物流单号
     */
    private String expressNo;
    /**
     * 0 商家订单 1直营店订单
     */
    private String directType;
    /**
     * 企业名称
     */
    private String companyName;

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public String getExpressNo() {
        return expressNo;
    }

    public void setExpressNo(String expressNo) {
        this.expressNo = expressNo;
    }

    public String getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(String isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public String getSalesmanPhone() {
        return salesmanPhone;
    }

    public void setSalesmanPhone(String salesmanPhone) {
        this.salesmanPhone = salesmanPhone;
    }

    public String getSalesmanDepartment() {
        return salesmanDepartment;
    }

    public void setSalesmanDepartment(String salesmanDepartment) {
        this.salesmanDepartment = salesmanDepartment;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDirectType() {
        return directType;
    }

    /**
     * 设置DirectType
     */
    public Order setDirectType(String directType) {
        this.directType = directType;
        return this;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    /**
     * 获取OrderCancelTime
     */
    public Date getOrderCancelTime() {
        return orderCancelTime;
    }

    /**
     * 设置OrderCancelTime
     */
    public void setOrderCancelTime(Date orderCancelTime) {
        this.orderCancelTime = orderCancelTime;
    }

    public BigDecimal getStartprice() {
        return startprice;
    }

    public void setStartprice(BigDecimal startprice) {
        this.startprice = startprice;
    }

    public BigDecimal getEndprice() {
        return endprice;
    }

    public void setEndprice(BigDecimal endprice) {
        this.endprice = endprice;
    }

    /**
     * 出库物流信息（用于显示在订单详情页 一个订单的会有N个商品 出库的时候也回有N个物流单）
     */
    public List<OrderContainerRelation> getOrderContainerRelations() {
        return orderContainerRelations;
    }

    /**
     * 出库物流信息（用于显示在订单详情页 一个订单的会有N个商品 出库的时候也回有N个物流单）
     */
    public void setOrderContainerRelations(List<OrderContainerRelation> orderContainerRelations) {
        this.orderContainerRelations = orderContainerRelations;
    }


    public String getWareName() {
        return wareName;
    }

    public void setWareName(String wareName) {
        this.wareName = wareName;
    }

    public Long getWareId() {
        return wareId;
    }

    public void setWareId(Long wareId) {
        this.wareId = wareId;
    }

    public Long getShippingCountyId() {
        return shippingCountyId;
    }

    public void setShippingCountyId(Long shippingCountyId) {
        this.shippingCountyId = shippingCountyId;
    }

    public String getPointLevelName() {
        return pointLevelName;
    }

    public void setPointLevelName(String pointLevelName) {
        this.pointLevelName = pointLevelName;
    }

    public String getOrderNewStatus() {
        return orderNewStatus;
    }

    public void setOrderNewStatus(String orderNewStatus) {
        this.orderNewStatus = orderNewStatus;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public void setDayCount(Long dayCount) {
        this.dayCount = dayCount;
    }

    public BigDecimal getDayMoney() {
        return dayMoney;
    }

    public void setDayMoney(BigDecimal dayMoney) {
        this.dayMoney = dayMoney;
    }

    public String getOrderLinePay() {
        return orderLinePay;
    }

    public void setOrderLinePay(String orderLinePay) {
        this.orderLinePay = orderLinePay;
    }

    public String getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getOrderMType() {
        return orderMType;
    }

    public void setOrderMType(String orderMType) {
        this.orderMType = orderMType;
    }

    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark;
    }

    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<OrderGoodsInfo> getOrderGoodsInfos() {
        return orderGoodsInfos;
    }

    public void setOrderGoodsInfos(List<OrderGoodsInfo> orderGoodsInfos) {
        this.orderGoodsInfos = orderGoodsInfos;
    }

    public String getOrderOldCode() {
        return orderOldCode;
    }

    public void setOrderOldCode(String orderOldCode) {
        this.orderOldCode = orderOldCode;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
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

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public OrderExpress getOrderExpress() {
        return orderExpress;
    }

    public void setOrderExpress(OrderExpress orderExpress) {
        this.orderExpress = orderExpress;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public List<OrderMarketing> getOrderMarketingList() {
        return orderMarketingList;
    }

    public void setOrderMarketingList(List<OrderMarketing> orderMarketingList) {
        this.orderMarketingList = orderMarketingList;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
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

    public BigDecimal getOrderOldPrice() {
        return orderOldPrice;
    }

    public void setOrderOldPrice(BigDecimal orderOldPrice) {
        this.orderOldPrice = orderOldPrice;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 获取支付时间
     */
    public Date getPayTime() {
        if (this.payTime != null) {
            return new Date(this.payTime.getTime());
        }
        return null;
    }

    /**
     * 设置支付时间
     */
    public void setPayTime(Date payTime) {
        if (payTime != null) {
            Date tEmp = (Date) payTime.clone();
            if (tEmp != null) {
                this.payTime = tEmp;
            }
        }
    }

    /**
     * 获取SendExpressTIme
     */
    public Date getSendExpressTime() {
        if (this.sendExpressTime != null) {
            return new Date(this.sendExpressTime.getTime());
        }
        return null;
    }

    /**
     * 设置SendExpressTime
     */
    public void setSendExpressTime(Date sendExpressTime) {
        if (sendExpressTime != null) {
            Date tEmp = (Date) sendExpressTime.clone();
            if (tEmp != null) {
                this.sendExpressTime = tEmp;
            }
        }
    }

    /**
     * 获取GetGoodsTime
     */
    public Date getGetGoodsTime() {
        if (this.getGoodsTime != null) {
            return new Date(this.getGoodsTime.getTime());
        }
        return null;
    }

    /**
     * 设置GetGOodsTIme
     */
    public void setGetGoodsTime(Date getGoodsTime) {
        if (getGoodsTime != null) {
            Date tEmp = (Date) getGoodsTime.clone();
            if (tEmp != null) {
                this.getGoodsTime = tEmp;
            }
        }
    }

    public Long getShoppingAddrId() {
        return shoppingAddrId;
    }

    public void setShoppingAddrId(Long shoppingAddrId) {
        this.shoppingAddrId = shoppingAddrId;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public String getCouponNo() {
        return couponNo;
    }

    public void setCouponNo(String couponNo) {
        this.couponNo = couponNo;
    }

    public String getPayTimeStart() {
        return payTimeStart;
    }

    public void setPayTimeStart(String payTimeStart) {
        this.payTimeStart = payTimeStart;
    }

    public String getPayTimeEnd() {
        return payTimeEnd;
    }

    public void setPayTimeEnd(String payTimeEnd) {
        this.payTimeEnd = payTimeEnd;
    }

    public String getAlreadyPay() {
        return alreadyPay;
    }

    public void setAlreadyPay(String alreadyPay) {
        this.alreadyPay = alreadyPay;
    }

    public BigDecimal getOrderPrePriceOrder() {
        return orderPrePriceOrder;
    }

    public void setOrderPrePriceOrder(BigDecimal orderPrePriceOrder) {
        this.orderPrePriceOrder = orderPrePriceOrder;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    /**
     * 获取用户昵称
     *
     * @return
     */
    public String getCustomerNickname() {
        return customerNickname;
    }

    /**
     * 设置用户昵称
     *
     * @param customerNickname
     */
    public void setCustomerNickname(String customerNickname) {
        this.customerNickname = customerNickname;
    }
}
