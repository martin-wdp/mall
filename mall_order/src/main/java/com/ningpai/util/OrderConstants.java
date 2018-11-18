/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.util;

/**
 * 订单地址工具
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月8日10:24:53
 * 
 */
public final class OrderConstants {

    // 查询销售量
    public static final String SALECOUNT = "jsp/dataAnalysis/salecount";
    // 查询销售额
    public static final String SALEMONEY = "jsp/dataAnalysis/salemoney";

    public static final String INITORDER = "orderlist.htm";

    public static final String ORDERLIST = "jsp/order/orderlist";
    // 第三方列表
    public static final String ORDERLISTISTHIRD = "jsp/order/thirdorderlist";

    public static final String ORDERDETAIL = "jsp/order/orderdetail";

    public static final String INITBACKORDER = "backorderlist.htm";
    // 第三方订单列表
    public static final String INITBACKORDERISTHIRD = "backorderlististhird.htm";
    public static final String BACKORDERLIST = "jsp/order/backorderlist";
    // 拣货
    public static final String PRINTTAKEPRO = "jsp/order/printtakepro";
    // 出库
    public static final String PRINTOUTSTOCK = "jsp/order/printoutstock";
    public static final String ORDERCONTAINER = "ordercontainer.htm";
    // 发货
    public static final String PRINTSHIPMENTS = "jsp/order/printshipments";
    public static final String EDIT = "jsp/order/printshipments";
    public static final String SENDGOODSORDER = "sendgoodsorder.htm";
    // 拣货单列表
    public static final String ORDERPICKINGLIST = "jsp/order/orderpickinglist";

    public static final String INITORDERPICKING = "orderpickinglist.htm";
    public static final String PRINTORDERPICKINGLIST = "jsp/order/printorderpickinglist";
    /**
     * 退单详细页面
     */
    public static final String BACKORDERDETAIL = "jsp/order/backorderdetail";

    public static final String NEWBACKORDERDETAIL = "jsp/order/newbackorderdetail";

    /**
     * 换单详细页面
     */
    public static final String BARTERORDERDETAIL = "jsp/order/barterorderdetail";

    // 出库列表
    public static final String ORDERDELIVERYLIST = "orderdeliverylist.htm";
    // 出库列表end

    // 已发货列表
    public static final String QUERYYSENDGOODSORDERLIST = "queryysendgoodsorderlist.htm";
    public static final String PRINTOVERLIST = "jsp/order/printoverlist";
    public static final String QUERYYDEORDERLIST = "queryydeorderlist.htm";
    // 已发货列表end
    // 已拣货
    public static final String QUERYYORDERLIST = "queryyorderlist.htm";

    // 已拣货end
    private OrderConstants() {
        super();
    }
}
