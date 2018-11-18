/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.site.order.bean;

/**
 * @author ggn
 * 
 */
public class TempClass {

    /**
     * 第三方id
     */
    private Long thirdId;

    /**
     * 购物车id
     */
    private Long[] shoppingCartId;

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }
    /**
     * 获取购物车id
     * */
    public Long[] getShoppingCartId() {
        return shoppingCartId.clone();
    }
    /**
     * 设置购物车id
     * */
    public void setShoppingCartId(Long[] shoppingCartId) {
        this.shoppingCartId = shoppingCartId.clone();
    }

}
