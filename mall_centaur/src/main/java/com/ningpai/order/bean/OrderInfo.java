/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import java.util.Date;

/**
 * 订单信息实体
 * 
 * @author qiyuanyuan
 * 
 */
public class OrderInfo {
    /*
     * 订单编号
     */
    private String tid;
    /*
     * 外部平台单号
     */
    private String out_tid;
    /*
     * 店铺ID
     */
    private String shop_id;
    /*
     * 仓库编码
     */
    private Integer storage_id;
    /*
     * 买家ID
     */
    private String buyer_id;
    /*
     * 买家留言
     */
    private String buyer_msg;
    /*
     * 买家邮件地址
     */
    private String buyer_email;
    /*
     * 买家支付宝账号
     */
    private String buyer_alipay;
    /*
     * 客服备注
     */
    private String seller_remark;
    /*
     * 收货人姓名
     */
    private String consignee;
    /*
     * 收货地址
     */
    private String address;
    /*
     * 收货人邮编
     */
    private String postcode;
    /*
     * 联系电话
     */
    private String telephone;
    /*
     * 联系手机
     */
    private String mobilPhone;
    /*
     * 收货人省份
     */
    private String privince;
    /*
     * 收货人城市
     */
    private String city;
    /*
     * 收货人地区
     */
    private String area;
    /*
     * 实收运费
     */
    private Double actual_freight_get;
    /*
     * 实收参考价订单实收金额
     */
    private Double actual_RP;
    /*
     * 配送方式
     */
    private String ship_method;
    /*
     * 快递公司名
     */
    private String express;
    /*
     * 开票情况(是否已开发票0：无1：有)默认0
     */
    private int is_invoiceOpened;
    /*
     * 发票类型
     */
    private String invoice_type;
    /*
     * 开票金额
     */
    private String invoice_money;
    /*
     * 发票抬头
     */
    private String invoice_title;
    /*
     * 发票内容
     */
    private String invoice_msg;
    /*
     * 订单类型
     */
    private String order_type;
    /*
     * 处理状态
     */
    private String process_status;
    /*
     * 付款状态（待退款部分退款待退款全部退款待退款所有交易关闭未付款已付款已退款部分退款已退款全部退款已退款所有）默认：未付款
     */
    private String pay_status;
    /*
     * 发货状态（待退货部分退货待退货全部退货待退货所有退货到货部分退货退货到货全部退货退货到货所有未发货已发货）默认：未发货
     */
    private String deliver_status;
    /*
     * 是否货到付款
     */
    private Integer is_COD;
    /*
     * 货到付款服务费
     */
    private Double serverCost_COD;
    /*
     * 订单总金额
     */
    private Double order_totalMoney;
    /*
     * 产品总金额
     */
    private Double product_totalMoney;
    /*
     * 支付方式
     */
    private String pay_method;
    /*
     * 支付佣金
     */
    private Double pay_commission;
    /*
     * 支付积分
     */
    private Integer pay_score;
    /*
     * 返点积分
     */
    private Integer return_score;
    /*
     * 优惠金额:订单总优惠金额
     */
    private Double favorable_money;
    /*
     * 支付宝交易号
     */
    private String alipay_transaction_no;
    /*
     * 外部平台付款单号
     */
    private String out_payNo;
    /*
     * 外部平台快递方式
     */
    private String out_express_method;
    /*
     * 外部平台快递订单状态
     */
    private String out_order_status;
    /*
     * 订货日期
     */
    private Date order_date;
    /*
     * 付款日期
     */
    private String pay_date;
    /*
     * 完成日期
     */
    private Date finish_date;
    /*
     * 平台类型
     */
    private String plat_type;
    /*
     * 分销商编号
     */
    private String distributor_no;
    /*
     * 物流公司
     */
    private String WuLiu;
    /*
     * 物流单号
     */
    private String WuLiu_no;
    /*
     * 终端类型(电脑-手机-电话)
     */
    private String terminal_type;
    /*
     * 内部便签
     */
    private String in_memo;
    /*
     * 其他备注
     */
    private String other_remark;
    /*
     * 实付运费
     */
    private Double actual_freight_pay;
    /*
     * 预配货日期
     */
    private Date ship_date_plan;
    /*
     * 预计发货日期
     */
    private Date deliver_date_plan;
    /*
     * 是否积分换购（0：不是1：是）默认：0
     */
    private Integer is_scorePay;
    /*
     * 是否需要开发票（0：不需要1：需要）默认0
     */
    private Integer is_needInvoice;

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getOut_tid() {
        return out_tid;
    }

    public void setOut_tid(String outTid) {
        this.out_tid = outTid;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shopId) {
        this.shop_id = shopId;
    }

    public Integer getStorage_id() {
        return storage_id;
    }

    public void setStorage_id(Integer storageId) {
        this.storage_id = storageId;
    }

    public String getBuyer_id() {
        return buyer_id;
    }

    public void setBuyer_id(String buyerId) {
        this.buyer_id = buyerId;
    }

    public String getBuyer_msg() {
        return buyer_msg;
    }

    public void setBuyer_msg(String buyerMsg) {
        this.buyer_msg = buyerMsg;
    }

    public String getBuyer_email() {
        return buyer_email;
    }

    public void setBuyer_email(String buyerEmail) {
        this.buyer_email = buyerEmail;
    }

    public String getBuyer_alipay() {
        return buyer_alipay;
    }

    public void setBuyer_alipay(String buyerAlipay) {
        this.buyer_alipay = buyerAlipay;
    }

    public String getSeller_remark() {
        return seller_remark;
    }

    public void setSeller_remark(String sellerRemark) {
        this.seller_remark = sellerRemark;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMobilPhone() {
        return mobilPhone;
    }

    public void setMobilPhone(String mobilPhone) {
        this.mobilPhone = mobilPhone;
    }

    public String getPrivince() {
        return privince;
    }

    public void setPrivince(String privince) {
        this.privince = privince;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public Double getActual_freight_get() {
        return actual_freight_get;
    }

    public void setActual_freight_get(Double actualFreightGet) {
        this.actual_freight_get = actualFreightGet;
    }

    public Double getActual_RP() {
        return actual_RP;
    }

    public void setActual_RP(Double actualRP) {
        this.actual_RP = actualRP;
    }

    public String getShip_method() {
        return ship_method;
    }

    public void setShip_method(String shipMethod) {
        this.ship_method = shipMethod;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
        this.express = express;
    }

    public int getIs_invoiceOpened() {
        return is_invoiceOpened;
    }

    public void setIs_invoiceOpened(int isInvoiceOpened) {
        this.is_invoiceOpened = isInvoiceOpened;
    }

    public String getInvoice_type() {
        return invoice_type;
    }

    public void setInvoice_type(String invoiceType) {
        this.invoice_type = invoiceType;
    }

    public String getInvoice_money() {
        return invoice_money;
    }

    public void setInvoice_money(String invoiceMoney) {
        this.invoice_money = invoiceMoney;
    }

    public String getInvoice_title() {
        return invoice_title;
    }

    public void setInvoice_title(String invoiceTitle) {
        this.invoice_title = invoiceTitle;
    }

    public String getInvoice_msg() {
        return invoice_msg;
    }

    public void setInvoice_msg(String invoiceMsg) {
        this.invoice_msg = invoiceMsg;
    }

    public String getOrder_type() {
        return order_type;
    }

    public void setOrder_type(String orderType) {
        this.order_type = orderType;
    }

    public String getProcess_status() {
        return process_status;
    }

    public void setProcess_status(String processStatus) {
        this.process_status = processStatus;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String payStatus) {
        this.pay_status = payStatus;
    }

    public String getDeliver_status() {
        return deliver_status;
    }

    public void setDeliver_status(String deliverStatus) {
        this.deliver_status = deliverStatus;
    }

    public Integer getIs_COD() {
        return is_COD;
    }

    public void setIs_COD(Integer isCOD) {
        this.is_COD = isCOD;
    }

    public Double getServerCost_COD() {
        return serverCost_COD;
    }

    public void setServerCost_COD(Double serverCostCOD) {
        this.serverCost_COD = serverCostCOD;
    }

    public Double getOrder_totalMoney() {
        return order_totalMoney;
    }

    public void setOrder_totalMoney(Double orderTotalMoney) {
        this.order_totalMoney = orderTotalMoney;
    }

    public Double getProduct_totalMoney() {
        return product_totalMoney;
    }

    public void setProduct_totalMoney(Double productTotalMoney) {
        this.product_totalMoney = productTotalMoney;
    }

    public String getPay_method() {
        return pay_method;
    }

    public void setPay_method(String payMethod) {
        this.pay_method = payMethod;
    }

    public Double getPay_commission() {
        return pay_commission;
    }

    public void setPay_commission(Double payCommission) {
        this.pay_commission = payCommission;
    }

    public Integer getPay_score() {
        return pay_score;
    }

    public void setPay_score(Integer payScore) {
        this.pay_score = payScore;
    }

    public Integer getReturn_score() {
        return return_score;
    }

    public void setReturn_score(Integer returnScore) {
        this.return_score = returnScore;
    }

    public Double getFavorable_money() {
        return favorable_money;
    }

    public void setFavorable_money(Double favorableMoney) {
        this.favorable_money = favorableMoney;
    }

    public String getAlipay_transaction_no() {
        return alipay_transaction_no;
    }

    public void setAlipay_transaction_no(String alipayTransactionNo) {
        this.alipay_transaction_no = alipayTransactionNo;
    }

    public String getOut_payNo() {
        return out_payNo;
    }

    public void setOut_payNo(String outPayNo) {
        this.out_payNo = outPayNo;
    }

    public String getOut_express_method() {
        return out_express_method;
    }

    public void setOut_express_method(String outExpressMethod) {
        this.out_express_method = outExpressMethod;
    }

    public String getOut_order_status() {
        return out_order_status;
    }

    public void setOut_order_status(String outOrderStatus) {
        this.out_order_status = outOrderStatus;
    }
    /**
     * 获取OrderDate
     * */
    public Date getOrder_date() {
        return order_date==null?null:(Date) order_date.clone();
    }
    /**
     * 设置OrderDate
     * */
    public void setOrder_date(Date orderDate) {
        this.order_date = orderDate == null ? null : (Date) orderDate.clone();
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String payDate) {
        this.pay_date = payDate;
    }
    /**
     * 获取finishDate
     * */
    public Date getFinish_date() {
        return finish_date==null?null:(Date) finish_date.clone();
    }
    /**
     * 设置FinishDate
     * */
    public void setFinish_date(Date finishDate) {
        this.finish_date = finishDate == null ? null : (Date) finishDate.clone();
    }

    public String getPlat_type() {
        return plat_type;
    }

    public void setPlat_type(String platType) {
        this.plat_type = platType;
    }

    public String getDistributor_no() {
        return distributor_no;
    }

    public void setDistributor_no(String distributorNo) {
        this.distributor_no = distributorNo;
    }

    public String getWuLiu() {
        return WuLiu;
    }

    public void setWuLiu(String wuLiu) {
        WuLiu = wuLiu;
    }

    public String getWuLiu_no() {
        return WuLiu_no;
    }

    public void setWuLiu_no(String wuLiuNo) {
        WuLiu_no = wuLiuNo;
    }

    public String getTerminal_type() {
        return terminal_type;
    }

    public void setTerminal_type(String terminalType) {
        this.terminal_type = terminalType;
    }

    public String getIn_memo() {
        return in_memo;
    }

    public void setIn_memo(String inMemo) {
        this.in_memo = inMemo;
    }

    public String getOther_remark() {
        return other_remark;
    }

    public void setOther_remark(String otherRemark) {
        this.other_remark = otherRemark;
    }

    public Double getActual_freight_pay() {
        return actual_freight_pay;
    }

    public void setActual_freight_pay(Double actualFreightPay) {
        this.actual_freight_pay = actualFreightPay;
    }
    /**
     * 获取SHioDatePlan
     * */
    public Date getShip_date_plan() {
        return (Date) ship_date_plan.clone();
    }
    /**
     * 设置ShipDatePlan
     * */
    public void setShip_date_plan(Date shipDatePlan) {
        this.ship_date_plan = shipDatePlan == null ? null : (Date) shipDatePlan.clone();
    }
    /**
     * 获取DeliverDatePlan
     * */
    public Date getDeliver_date_plan() {
        return (Date) deliver_date_plan.clone();
    }
    /**
     * 设置DeliverDatePlan
     * */
    public void setDeliver_date_plan(Date deliverDatePlan) {
        this.deliver_date_plan = deliverDatePlan == null ? null : (Date) deliverDatePlan.clone();
    }

    public Integer getIs_scorePay() {
        return is_scorePay;
    }

    public void setIs_scorePay(Integer isScorePay) {
        this.is_scorePay = isScorePay;
    }

    public Integer getIs_needInvoice() {
        return is_needInvoice;
    }

    public void setIs_needInvoice(Integer isNeedInvoice) {
        this.is_needInvoice = isNeedInvoice;
    }

}
