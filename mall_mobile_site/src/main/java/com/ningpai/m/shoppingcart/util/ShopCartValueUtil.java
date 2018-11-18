/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.shoppingcart.util;

import com.ningpai.common.util.ConstantUtil;

/**
 * 购物车的ValueBean
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月12日 上午11:37:51
 * @version 1.0
 */
public final class ShopCartValueUtil {
    public static final  String CUSTOMERID="customerId";
    public static final  String NPSTORESHOPCAR="_npstore_shopcar";
    public static final  String UTF8=ConstantUtil.UTF;
    
    public static final Long WARECOUNT= (long) 0;

    /**
     * 构造
     */
    private ShopCartValueUtil() {
        super();
    }
}
