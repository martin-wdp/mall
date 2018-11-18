/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.util;

/**
 * 订单地址管理
 * 
 * @author NINGPAI-LIH
 * @since 2014年8月28日18:16:33
 * 
 */
public class OrderURL {

    // 收货地址添加
    public static final String FILLADDRESS = "order/filladdress";

    // 收货地址列表
    public static final String ADDRESSLIST = "order/address_list";

    // 订单用户页面
    public static final String OTHERPAYCONT = "otherpay/other_pay_cont";

    // 代付人页面
    public static final String OTHERPAYFRIEND = "otherpay/other_pay_friend";

    // 错误信息
    public static final String ERRORPAGE = "errorpage.html";

    // 代付页面
    public static final String OTHERPAY = "otherpay/pay";

    private OrderURL() {
    }
}
