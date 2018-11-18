/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.third.market.util;

/**
 * <p>优惠劵用到的常量类</p>
 * @author NINGPAI-ggn
 * @since 2014年4月15日 下午4:02:38
 * @version 2.0
 */
public final class ThirdMarketPath {

    /**
     * 商品促销列表方法
     */
    public static final String INITMARKETGOODSLIST = "thirdproductmarketing.htm";
    /**
     * 商品折扣促销列表
     */
    public static final String INITMARKETZKSLIST = "createzkmarketing.htm";
    /**
     * 商品包邮促销列表
     */
    public static final String INITMARKETBYSLIST = "createbymarketing.htm";
    /**
     * 商品满减促销列表
     */
    public static final String INITMARKETMJOSLIST = "createmjomarketing.htm";
    /**
     * 商品满件促销列表
     */
    public static final String INITMARKETMJPSLIST = "createmjpmarketing.htm";
    
    /**
     * 跳转商品促销
     */
    public static final String MARKETGOODSLIST = "/marketing/thirdproductmarket";
    /**
     * 跳转商品折扣促销列表
     */
    public static final String MARKETZKGOODSLIST = "/marketing/thirdzkproductmarket";
    /**
     * 跳转商品包邮促销列表
     */
    public static final String MARKETBYGOODSLIST = "/marketing/thirdbyproductmarket";
    /**
     * 跳转商品满减促销列表
     */
    public static final String MARKETMJOGOODSLIST = "/marketing/thirdmjoproductmarket";
    /**
     * 跳转商品满减促销列表
     */
    public static final String MARKETMJPGOODSLIST = "/marketing/thirdmjpproductmarket";
    /**
     * 跳转添加促销
     */
    public static final String CREATEMARKETING = "/marketing/thirdcreatemarket";
    /**
     * 跳转订单促销
     */
    public static final String INITMARKETORDERLIST = "thirdordermarketing.htm";
    /**
     * 修改促销
     */
    public static final String UPDATEMARKETING = "/marketing/thirdupdatemarket";
    /**
     * 订单促销列表
     */
    public static final String MARKETORDERSLIST = "/marketing/thirdordermarket";

    /**
     * 添加单品促销页面
     */
    public static final String DPMARKETING = "/marketing/createmarketing-dp";

    /**
     * 添加折扣促销
     */
    public static final String ZKMARKETING = "/marketing/createmarketing-zk";

    /**
     * 添加包邮促销
     */
    public static final String BYMARKETING = "/marketing/createmarketing-by";
    
    /**
     * 添加满减促销
     */
    public static final String MJOMARKETING = "/marketing/createmarketing-mjo";
    
    /**
     * 添加满件促销
     */
    public static final String MJPMARKETING = "/marketing/createmarketing-mjp";

    private ThirdMarketPath() {
        super();
    }

}
