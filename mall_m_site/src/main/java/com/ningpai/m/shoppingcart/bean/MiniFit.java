/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.shoppingcart.bean;

import java.math.BigDecimal;
import java.util.List;

/**
 * 迷你购物车套装
 * @author NingPai-Pro
 *
 */
public class MiniFit {
    
    //套装内商品
    private List<MiniGoods> miniGoods;
    
    //套装价格
    private BigDecimal fitPrice;
    
    //套装名称
    private String fitName;


    public List<MiniGoods> getMiniGoods() {
        return miniGoods;
    }

    public void setMiniGoods(List<MiniGoods> miniGoods) {
        this.miniGoods = miniGoods;
    }

    public BigDecimal getFitPrice() {
        return fitPrice;
    }

    public void setFitPrice(BigDecimal fitPrice) {
        this.fitPrice = fitPrice;
    }

    public String getFitName() {
        return fitName;
    }

    public void setFitName(String fitName) {
        this.fitName = fitName;
    } 
    
    
}
