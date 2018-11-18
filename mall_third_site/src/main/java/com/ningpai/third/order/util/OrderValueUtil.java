/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.util;

/**
 * <p>封装常用的String参数</p>
 * @since 20150803
 * @version 2.0
 * @author NINGPAI-LIH
 *
 */
public final class OrderValueUtil {

    // 设置订单的状态为作废
    public static final String ORDERSTATUS = "4";
    // 第三方订单列表Controller
    // 第三方出库页面
    public static final String ORDERDELIVERY = "order/orderdelivery";
    // 第三方发货页面
    public static final String ORDERSENDGOODS = "order/ordersendgoods";
    // 分页查询订单列表 "order/thirdorderlist"
    public static final String THIRDORDERLIST = "order/thirdorderlist";
    // 分页查询订单列表 "order/thirdorderlist"
    public static final String PUYTHIRDORDERLIST = "order/puythirdorderlist";
    // 跳转到订单首页
    public static final String QUERYTHIRDORDERLIST = "queryThirdOrderList.htm";
    // 订单详细页面
    public static final String THIRDORDERDETAIL = "order/thirdorderdetail";
    // 退单详情页面
    public static final String THIRDBACKORDERDETAIL = "order/thirdbackorderdetail";
    // 第三方订单列表Controller END

    // 第三方退单列表
    // 跳转到退单页面
    public static final String THIRDBACKORDERLISTS = "order/thirdBackorderlists";
    // 第三方退单列表end

    // 第三方换货单Controller
    public static final String QUERYBARTERLISTS = "order/thirdbarterorderlists";
    public static final String TOEXAMBARKERORDER = "order/exambarkerorder";
    public static final String QUERYTHIRDBARTERLISTS = "queryThirdBarterLists.htm";

    // 出库列表
    public static final String THIRDOUTSTOCK = "order/thirdoutstock";

    // 第三方换货单Controller END
    private OrderValueUtil() {
        super();
    }

}
