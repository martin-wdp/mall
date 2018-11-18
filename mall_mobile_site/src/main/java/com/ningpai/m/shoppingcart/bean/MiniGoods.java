/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.shoppingcart.bean;

import java.math.BigDecimal;

import com.ningpai.marketing.bean.Marketing;

/**
 * @author ggn
 *
 */
public class MiniGoods {
    private Long shoppingCartId; 
    //主键ID
    private Long goodsInfoId;
    //商品ID
    private Long goodsId;
    //数量
    private Long buNum;
    //名称
    private String productName;
    //价格
    private BigDecimal productPrice;
    //促销
    private  Marketing marketing;
    
    private String productPic;
    //套装id
    private Long fitId;
    //如果商品为套装
    private MiniFit miniFit;
    
    public MiniFit getMiniFit() {
        return miniFit;
    }

    public void setMiniFit(MiniFit miniFit) {
        this.miniFit = miniFit;
    }

    
    public Long getFitId() {
        return fitId;
    }

    public void setFitId(Long fitId) {
        this.fitId = fitId;
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic;
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }
    
    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }
    public Long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public Long getBuNum() {
        return buNum;
    }
    public void setBuNum(Long buNum) {
        this.buNum = buNum;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public BigDecimal getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }
    public Marketing getMarketing() {
        return marketing;
    }
    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }
    
}


