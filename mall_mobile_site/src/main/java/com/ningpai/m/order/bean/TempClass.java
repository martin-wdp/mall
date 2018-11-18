/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.order.bean;

/**
 * 测试类
 * @author ggn
 * 
 */
public class TempClass {

    private Long thirdId;

    private Long[] shoppingCartId;

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    /**
     * 购物车id
     * */
    public Long[] getShoppingCartId() {
        return shoppingCartId.clone();
    }
    /**
     * 购物车Id
     * */
    public void setShoppingCartId(Long[] shoppingCartId) {
        this.shoppingCartId = shoppingCartId.clone();
    }

}
