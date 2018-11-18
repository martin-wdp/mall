package com.ningpai.third.analysis.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 订单类
 * </p>
 * 
 * @author zhangsl
 * @version 2.0
 * @since 2014年12月13日 上午11:08:44
 * @version
 */
public class    OOrder {
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
     * 评价
     */
    private String evaluateFlag;
    /**
     * 运费
     */
    private BigDecimal expressPrice;
    /**
     * 邮编
     */
    private String shippingPostcode;
    /**
     * 订单取消时间
     */
    private Date orderCancelTime;
    /**
     * 取消订单备注时间
     */
    private String orderCancelRemark;
    /**
     * 退单金额
     */
    private BigDecimal backPrice;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 类型 会员0 经销商2
     */
    private String dealerType;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 主单Code
     */
    private String orderOldCode;
    /**
     * 订单新状态
     */
    private String orderNewStatus;
    /**
     * 订单出库状态 0：拣货 1：装箱 2：出库
     */
    private String orderCargoStatus;
    /**
     * 订单状态
     */
    private String orderMType;
    /**
     * 物流类型
     */
    private String orderExpressType;
    /**
     * 成功订单量
     */
    private Long countSum;
    /**
     * 创建时间
     */
    private String ctime;
    /**
     * 不成功订单量
     */
    private Long allcount;

    public Long getAllcount() {
        return allcount;
    }

    public void setAllcount(Long allcount) {
        this.allcount = allcount;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public Long getCountSum() {
        return countSum;
    }

    public void setCountSum(Long countSum) {
        this.countSum = countSum;
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
    /**
     * 获取GetGoodsTime
     * */
    public Date getGetGoodsTime() {
        return (Date) getGoodsTime.clone();
    }
    /**
     * 设置GetGoodsTime
     * */
    public void setGetGoodsTime(Date getGoodsTime) {
        this.getGoodsTime = getGoodsTime == null ? null : (Date) getGoodsTime.clone();
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

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getShippingPostcode() {
        return shippingPostcode;
    }

    public void setShippingPostcode(String shippingPostcode) {
        this.shippingPostcode = shippingPostcode;
    }
    /**
     * 获取OrderCancelTIme
     * */
    public Date getOrderCancelTime() {
        return (Date) orderCancelTime.clone();
    }
    /**
     * 设置OrderCancelTime
     * */
    public void setOrderCancelTime(Date orderCancelTime) {
        this.orderCancelTime = orderCancelTime == null ? null : (Date) orderCancelTime.clone();
    }

    public String getOrderCancelRemark() {
        return orderCancelRemark;
    }

    public void setOrderCancelRemark(String orderCancelRemark) {
        this.orderCancelRemark = orderCancelRemark;
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getDealerType() {
        return dealerType;
    }

    public void setDealerType(String dealerType) {
        this.dealerType = dealerType;
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

    public String getOrderOldCode() {
        return orderOldCode;
    }

    public void setOrderOldCode(String orderOldCode) {
        this.orderOldCode = orderOldCode;
    }

    public String getOrderNewStatus() {
        return orderNewStatus;
    }

    public void setOrderNewStatus(String orderNewStatus) {
        this.orderNewStatus = orderNewStatus;
    }

    public String getOrderCargoStatus() {
        return orderCargoStatus;
    }

    public void setOrderCargoStatus(String orderCargoStatus) {
        this.orderCargoStatus = orderCargoStatus;
    }

    public String getOrderMType() {
        return orderMType;
    }

    public void setOrderMType(String orderMType) {
        this.orderMType = orderMType;
    }

    public String getOrderExpressType() {
        return orderExpressType;
    }

    public void setOrderExpressType(String orderExpressType) {
        this.orderExpressType = orderExpressType;
    }
}
