package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 换单信息
 * 
 * @author YANhb
 */
public class BarterOrder {

    /**
     * 换单ID
     */
    private Long barterOrderId;
    /**
     * 换单编号
     */
    private String barterOrderCode;
    /**
     * 订单标号
     */
    private String orderCode;
    /**
     * 换单时间
     */
    private Date barterTime;
    /**
     * 换单原因
     */
    private String barterRemark;
    /**
     * 换单总价
     */
    private BigDecimal barterPrice;
    /**
     * 换单审核 0 未审核（换货申请中） 1 已审核（换货接受） 2 已拒绝（换货驳回） 3 已发货（换货已发货） 4 换货完成
     */
    private String barterCheck;
    /**
     * 是否删除 0不删除 1删除
     */
    private String barterDelFlag;
    /**
     * 真实姓名
     */
    private String barterRealName;
    /**
     * 银行名称
     */
    private String barterBankName;
    /**
     * 银行账号
     */
    private String barterBankAccount;
    /**
     * 类型 会员0 经销商1
     */
    private String dealerType;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 操作人姓名
     */
    private String authorName;
    /**
     * 操作人时间
     */
    private Date authorTime;
    /**
     * 操作人记录
     */
    private String authorLog;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 订单价格
     */
    private BigDecimal orderPrice;
    /**
     * 优惠价格
     */
    private BigDecimal orderPrePrice;
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
     * 邮编
     */
    private String shippingPostcode;

    /**
     * 订单商品ID
     */
    private Long orderGoodsId;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 货品ＩＤ
     */
    private Long goodsInfoId;
    /**
     * 货品数量
     */
    private Long goodsInfoNum;
    /**
     * 货品原价
     */
    private BigDecimal goodsInfoOldPrice;
    /**
     * 货品价格
     */
    private BigDecimal goodsInfoPrice;
    /**
     * 优惠金额
     */
    private BigDecimal goodsCouponPrice;
    /**
     * 总价格（小计）
     */
    private BigDecimal goodsInfoSumPrice;
    /**
     * 是否有赠品
     */
    private String haveGiftStatus;
    /**
     * 是否有优惠券
     */
    private String haveCouponStatus;
    /**
     * 促销ID
     */
    private Long goodsMarketingId;
    /**
     * 购买时间
     */
    private Date buyTime;
    /**
     * 是否评价
     */
    private String evaluateFlag;
    /**
     * 是否退单
     */
    private String backFlag;

    /**
     * 订单信息
     */
    private Order order;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**getAuthorTime
     * @return
     */
    public Date getAuthorTime() {
        if (this.authorTime != null) {
            return new Date(this.authorTime.getTime());
        }
        return null;
    }

    /**setAuthorTime
     * @param authorTime
     */
    public void setAuthorTime(Date authorTime) {
        if (authorTime != null) {
            Date tEmp = (Date) authorTime.clone();
            if (tEmp != null) {
                this.authorTime = tEmp;
            }
        }
    }

    public String getAuthorLog() {
        return authorLog;
    }

    public void setAuthorLog(String authorLog) {
        this.authorLog = authorLog;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

    /**getPayTime
     * @return
     */
    public Date getPayTime() {
        if (this.payTime != null) {
            return new Date(this.payTime.getTime());
        }
        return null;
    }

    /**setPayTime
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        if (payTime != null) {
            Date tEmp = (Date) payTime.clone();
            if (tEmp != null) {
                this.payTime = tEmp;
            }
        }
    }

    /**getSendExpressTime
     * @return
     */
    public Date getSendExpressTime() {
        if (this.sendExpressTime != null) {
            return new Date(this.sendExpressTime.getTime());
        }
        return null;
    }

    /**setSendExpressTime
     * @param sendExpressTime
     */
    public void setSendExpressTime(Date sendExpressTime) {
        if (sendExpressTime != null) {
            Date tEmp = (Date) sendExpressTime.clone();
            if (tEmp != null) {
                this.sendExpressTime = tEmp;
            }
        }
    }

    /**getGetGoodsTime
     * @return
     */
    public Date getGetGoodsTime() {
        if (this.getGoodsTime != null) {
            return new Date(this.getGoodsTime.getTime());
        }
        return null;
    }

    /**setGetGoodsTime
     * @param getGoodsTime
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

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public List<OrderMarketing> getOrderMarketingList() {
        return orderMarketingList;
    }

    public void setOrderMarketingList(List<OrderMarketing> orderMarketingList) {
        this.orderMarketingList = orderMarketingList;
    }

    public List<OrderGoods> getOrderGoodsList() {
        return orderGoodsList;
    }

    public void setOrderGoodsList(List<OrderGoods> orderGoodsList) {
        this.orderGoodsList = orderGoodsList;
    }

    public OrderExpress getOrderExpress() {
        return orderExpress;
    }

    public void setOrderExpress(OrderExpress orderExpress) {
        this.orderExpress = orderExpress;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsInfoNum() {
        return goodsInfoNum;
    }

    public void setGoodsInfoNum(Long goodsInfoNum) {
        this.goodsInfoNum = goodsInfoNum;
    }

    public BigDecimal getGoodsInfoOldPrice() {
        return goodsInfoOldPrice;
    }

    public void setGoodsInfoOldPrice(BigDecimal goodsInfoOldPrice) {
        this.goodsInfoOldPrice = goodsInfoOldPrice;
    }

    public BigDecimal getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(BigDecimal goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }

    public BigDecimal getGoodsCouponPrice() {
        return goodsCouponPrice;
    }

    public void setGoodsCouponPrice(BigDecimal goodsCouponPrice) {
        this.goodsCouponPrice = goodsCouponPrice;
    }

    public BigDecimal getGoodsInfoSumPrice() {
        return goodsInfoSumPrice;
    }

    public void setGoodsInfoSumPrice(BigDecimal goodsInfoSumPrice) {
        this.goodsInfoSumPrice = goodsInfoSumPrice;
    }

    public String getHaveGiftStatus() {
        return haveGiftStatus;
    }

    public void setHaveGiftStatus(String haveGiftStatus) {
        this.haveGiftStatus = haveGiftStatus;
    }

    public String getHaveCouponStatus() {
        return haveCouponStatus;
    }

    public void setHaveCouponStatus(String haveCouponStatus) {
        this.haveCouponStatus = haveCouponStatus;
    }

    public Long getGoodsMarketingId() {
        return goodsMarketingId;
    }

    public void setGoodsMarketingId(Long goodsMarketingId) {
        this.goodsMarketingId = goodsMarketingId;
    }

    /**getBuyTime
     * @return
     */
    public Date getBuyTime() {
        if (this.buyTime != null) {
            return new Date(this.buyTime.getTime());
        }
        return null;
    }

    /**setBuyTime
     * @param buyTime
     */
    public void setBuyTime(Date buyTime) {
        if (buyTime != null) {
            Date tEmp = (Date) buyTime.clone();
            if (tEmp != null) {
                this.buyTime = tEmp;
            }
        }
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public Long getBarterOrderId() {
        return barterOrderId;
    }

    public void setBarterOrderId(Long barterOrderId) {
        this.barterOrderId = barterOrderId;
    }

    public String getBarterOrderCode() {
        return barterOrderCode;
    }

    public void setBarterOrderCode(String barterOrderCode) {
        this.barterOrderCode = barterOrderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**getBarterTime
     * @return
     */
    public Date getBarterTime() {
        if (this.barterTime != null) {
            return new Date(this.barterTime.getTime());
        }
        return null;
    }

    /**setBarterTime
     * @param barterTime
     */
    public void setBarterTime(Date barterTime) {
        if (barterTime != null) {
            Date tEmp = (Date) barterTime.clone();
            if (tEmp != null) {
                this.barterTime = tEmp;
            }
        }
    }

    public String getBarterRemark() {
        return barterRemark;
    }

    public void setBarterRemark(String barterRemark) {
        this.barterRemark = barterRemark;
    }

    public BigDecimal getBarterPrice() {
        return barterPrice;
    }

    public void setBarterPrice(BigDecimal barterPrice) {
        this.barterPrice = barterPrice;
    }

    public String getBarterCheck() {
        return barterCheck;
    }

    public void setBarterCheck(String barterCheck) {
        this.barterCheck = barterCheck;
    }

    public String getBarterDelFlag() {
        return barterDelFlag;
    }

    public void setBarterDelFlag(String barterDelFlag) {
        this.barterDelFlag = barterDelFlag;
    }

    public String getBarterRealName() {
        return barterRealName;
    }

    public void setBarterRealName(String barterRealName) {
        this.barterRealName = barterRealName;
    }

    public String getBarterBankName() {
        return barterBankName;
    }

    public void setBarterBankName(String barterBankName) {
        this.barterBankName = barterBankName;
    }

    public String getBarterBankAccount() {
        return barterBankAccount;
    }

    public void setBarterBankAccount(String barterBankAccount) {
        this.barterBankAccount = barterBankAccount;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }
}
