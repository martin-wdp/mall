/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.thirdaudit.bean.StoreInfo;

/**
 * 退单信息
 *
 * @author ggn
 */
public class BackOrder {
    /**
     * 退单ID
     */
    private Long backOrderId;
    /**
     * 退单编号
     */
    private String backOrderCode;
    /**
     * 订单标号
     */
    private String orderCode;
    /**
     * 退单时间
     */
    private Date backTime;
    /**
     * 退单原因
     */
    private String backRemark;
    /**
     * 退单总价
     */
    private BigDecimal backPrice;
    /**
     * 退单审核
     */
    private String backCheck;
    /**
     * 是否删除
     */
    private String backDelFlag;
    /**
     * 真实姓名
     */
    private String backRealName;
    /**
     * 银行名称
     */
    private String backBankName;
    /**
     * 银行账户
     */
    private String backBankAccount;
    /**
     * 退单商品
     */
    private GoodsProductDetailViewVo goodsProductVo;
    /**
     * 类型 会员0 经销商1
     */
    private String dealerType;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 退单商品
     */
    private OrderGoods orderGoods;
    /**
     * 退单来源订单 以及订单信息
     */
    private Order order;
    /**
     * 操作员
     */
    private String authorName;
    /**
     * 操作时间
     */
    private Date authorTime;
    /**
     * 操作日志
     */
    private String authorLog;
    /**
     * 第三方商铺
     */
    private StoreInfo storeInfo;
    /**
     * 区分是退款还是退单
     */
    private Long orderstatus;
    /**
     * 结束价格 （用于第三方价格区域查询）
     */
    private BigDecimal startprice;
    /**
     * 结束价格 （用于第三方价格区域查询）
     */
    private BigDecimal endprice;
    /**
     * 订单开始时间，商家查询使用
     */
    private String startTime;
    /**
     * 订单结束时间，商家查询使用
     */
    private String endTime;
    /**
     * 是否退货(1 退货 2 退款)
     */
    private String isBack;
    /**
     * 退单原因(1 不想买了 2 收货人信息有误 3 商品损坏 4 其他)
     */
    private String backReason;
    /**
     * 申请凭据(1 有发票 2 有质检报告 3无任何凭据)
     */
    private String applyCredentials;
    /**
     * 上传凭证
     */
    private String uploadDocuments;
    /**
     * 商品返回方式(0 快递)
     */
    private String backWay;
    /**
     * 图片
     */
    private List imgs;

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
     * 需要退货的商品ID
     */
    private String backGoodsIdAndSum;

    /**
     * 保存需要退单的商品信息
     */
    private List<GoodsProductVo> orderGoodslistVo = new ArrayList<GoodsProductVo>();


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
     * 退单物流信息
     */
    private Long ogisticsId;
    /**
     * 退单物流单号
     */
    private String ogisticsNo;
    /**
     * 退单物流名称
     */
    private String ogisticsName;
    /**
     * 创建时间
     */
    private Date creatTime;
    /**
     * 退单Id
     */
    private Long wuliubackOrderId;

    /**
     * 备注
     */
    private String temp;

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
     * 企业名称
     */
    private String companyName;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Long getSalesmanId() {
        return salesmanId;
    }

    public void setSalesmanId(Long salesmanId) {
        this.salesmanId = salesmanId;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
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

    public String getIsEnterprise() {
        return isEnterprise;
    }

    public void setIsEnterprise(String isEnterprise) {
        this.isEnterprise = isEnterprise;
    }

    public List getImgs() {
        return imgs;
    }

    public void setImgs(List imgs) {
        this.imgs = imgs;
    }

    public String getIsBack() {
        return isBack;
    }

    public void setIsBack(String isBack) {
        this.isBack = isBack;
    }

    public String getBackReason() {
        return backReason;
    }

    public void setBackReason(String backReason) {
        this.backReason = backReason;
    }

    public String getApplyCredentials() {
        return applyCredentials;
    }

    public void setApplyCredentials(String applyCredentials) {
        this.applyCredentials = applyCredentials;
    }

    public String getUploadDocuments() {
        return uploadDocuments;
    }

    public void setUploadDocuments(String uploadDocuments) {
        this.uploadDocuments = uploadDocuments;
    }

    public String getBackWay() {
        return backWay;
    }

    public void setBackWay(String backWay) {
        this.backWay = backWay;
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

    public Long getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Long orderstatus) {
        this.orderstatus = orderstatus;
    }

    public StoreInfo getStoreInfo() {
        return storeInfo;
    }

    public void setStoreInfo(StoreInfo storeInfo) {
        this.storeInfo = storeInfo;
    }

    public String getAuthorLog() {
        return authorLog;
    }

    public void setAuthorLog(String authorLog) {
        this.authorLog = authorLog;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 获取作者时间
     */
    public Date getAuthorTime() {
        if (this.authorTime != null) {
            return new Date(this.authorTime.getTime());
        }
        return null;
    }

    /**
     * 设置作者时间
     */
    public void setAuthorTime(Date authorTime) {
        if (authorTime != null) {
            Date tEmp = (Date) authorTime.clone();
            if (tEmp != null) {
                this.authorTime = tEmp;
            }
        }
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public OrderGoods getOrderGoods() {
        return orderGoods;
    }

    public void setOrderGoods(OrderGoods orderGoods) {
        this.orderGoods = orderGoods;
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

    public GoodsProductDetailViewVo getGoodsProductVo() {
        return goodsProductVo;
    }

    public void setGoodsProductVo(GoodsProductDetailViewVo goodsProductVo) {
        this.goodsProductVo = goodsProductVo;
    }

    public String getBackRealName() {
        return backRealName;
    }

    public void setBackRealName(String backRealName) {
        this.backRealName = backRealName;
    }

    public String getBackBankName() {
        return backBankName;
    }

    public void setBackBankName(String backBankName) {
        this.backBankName = backBankName;
    }

    public String getBackBankAccount() {
        return backBankAccount;
    }

    public void setBackBankAccount(String backBankAccount) {
        this.backBankAccount = backBankAccount;
    }

    public List<GoodsProductVo> getOrderGoodslistVo() {
        return orderGoodslistVo;
    }

    public void setOrderGoodslistVo(List<GoodsProductVo> orderGoodslistVo) {
        this.orderGoodslistVo = orderGoodslistVo;
    }

    public String getBackGoodsIdAndSum() {
        return backGoodsIdAndSum;
    }

    public void setBackGoodsIdAndSum(String backGoodsIdAndSum) {
        this.backGoodsIdAndSum = backGoodsIdAndSum;
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

    /**
     * getBuyTime
     *
     * @return
     */
    public Date getBuyTime() {
        if (this.buyTime != null) {
            return new Date(this.buyTime.getTime());
        }
        return null;
    }

    /**
     * setBuyTime
     *
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

    /**
     * getPayTime
     *
     * @return
     */
    public Date getPayTime() {
        if (this.payTime != null) {
            return new Date(this.payTime.getTime());
        }
        return null;
    }

    /**
     * setPayTime
     *
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

    /**
     * getSendExpressTime
     *
     * @return
     */
    public Date getSendExpressTime() {
        if (this.sendExpressTime != null) {
            return new Date(this.sendExpressTime.getTime());
        }
        return null;
    }

    /**
     * setSendExpressTime
     *
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

    /**
     * getGetGoodsTime
     *
     * @return
     */
    public Date getGetGoodsTime() {
        if (this.getGoodsTime != null) {
            return new Date(this.getGoodsTime.getTime());
        }
        return null;
    }

    /**
     * setGetGoodsTime
     *
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

    public Long getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(Long backOrderId) {
        this.backOrderId = backOrderId;
    }

    public String getBackOrderCode() {
        return backOrderCode;
    }

    public void setBackOrderCode(String backOrderCode) {
        this.backOrderCode = backOrderCode;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    /**
     * getBackTime
     *
     * @return
     */
    public Date getBackTime() {
        if (this.backTime != null) {
            return new Date(this.backTime.getTime());
        }
        return null;
    }

    /**
     * setBackTime
     *
     * @param backTime
     */
    public void setBackTime(Date backTime) {
        if (backTime != null) {
            Date tEmp = (Date) backTime.clone();
            if (tEmp != null) {
                this.backTime = tEmp;
            }
        }
    }

    public String getBackRemark() {
        return backRemark;
    }

    public void setBackRemark(String backRemark) {
        this.backRemark = backRemark;
    }

    public BigDecimal getBackPrice() {
        return backPrice;
    }

    public void setBackPrice(BigDecimal backPrice) {
        this.backPrice = backPrice;
    }

    public String getBackCheck() {
        return backCheck;
    }

    public void setBackCheck(String backCheck) {
        this.backCheck = backCheck;
    }

    public String getBackDelFlag() {
        return backDelFlag;
    }

    public void setBackDelFlag(String backDelFlag) {
        this.backDelFlag = backDelFlag;
    }

    public Long getOgisticsId() {
        return ogisticsId;
    }

    public void setOgisticsId(Long ogisticsId) {
        this.ogisticsId = ogisticsId;
    }

    public String getOgisticsNo() {
        return ogisticsNo;
    }

    public void setOgisticsNo(String ogisticsNo) {
        this.ogisticsNo = ogisticsNo;
    }

    public String getOgisticsName() {
        return ogisticsName;
    }

    public void setOgisticsName(String ogisticsName) {
        this.ogisticsName = ogisticsName;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Long getWuliubackOrderId() {
        return wuliubackOrderId;
    }

    public void setWuliubackOrderId(Long wuliubackOrderId) {
        this.wuliubackOrderId = wuliubackOrderId;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

}
