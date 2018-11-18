/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 货品条目实体
 * 
 * @author qiyuanyuan
 * 
 */
@XStreamAlias("product_item")
public class ProductItem {
    /*
     * barCode;条形码
     */
    private String barCode;
    /*
     * product_title;商品名称
     */
    @XStreamAlias("product_title")
    private String product_title;
    /*
     * standard;规格
     */
    private String standard;
    /*
     * out_price;外部单价
     */
    private Double out_price;
    /*
     * favorite_money;优惠金额:单品的优惠金额
     */
    private Double favorite_money;
    /*
     * orderGoods_Num;订货数量
     */
    private Long orderGoods_Num;
    /*
     * gift_Num;赠品数量
     */
    private Double gift_Num;
    /*
     * cost_Price;成交单价
     */
    private Double cost_Price;
    /*
     * tid;订单编号
     */
    private String tid;
    /*
     * product_stockout;产品缺货情况
     */
    private String product_stockout;
    /*
     * is_Book;是否预订（0：否1：是）默认：0
     */
    private Integer is_Book;
    /*
     * is_presell;是否预售 false is_Gift 0 int 是否赠品（0：否1：是）默认：0
     */
    private Integer is_presell;
    /*
     * is_Gift;是否赠品（0：否1：是）默认：0
     */
    private Integer is_Gift;
    /*
     * avg_price;加权平均单价
     */
    private Double avg_price;
    /*
     * product_freight;产品运费
     */
    private Double product_freight;
    /*
     * shop_id;店铺编号
     */
    private String shop_id;
    /*
     * out_tid;外部平台单号
     */
    private String out_tid;
    /*
     * out_productId;外部平台产品Id
     */
    private String out_productId;
    /*
     * out_barCode;外部平台条形码
     */
    private String out_barCode;
    /*
     * product_intro;产品简介
     */
    private String product_intro;

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String productTitle) {
        this.product_title = productTitle;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public Double getOut_price() {
        return out_price;
    }

    public void setOut_price(Double outPrice) {
        this.out_price = outPrice;
    }

    public Double getFavorite_money() {
        return favorite_money;
    }

    public void setFavorite_money(Double favoriteMoney) {
        this.favorite_money = favoriteMoney;
    }

    public void setOrderGoods_Num(Long orderGoodsNum) {
        this.orderGoods_Num = orderGoodsNum;
    }

    public Long getOrderGoods_Num() {
        return orderGoods_Num;
    }

    public Double getGift_Num() {
        return gift_Num;
    }

    public void setGift_Num(Double giftNum) {
        this.gift_Num = giftNum;
    }

    public Double getCost_Price() {
        return cost_Price;
    }

    public void setCost_Price(Double costPrice) {
        this.cost_Price = costPrice;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getProduct_stockout() {
        return product_stockout;
    }

    public void setProduct_stockout(String productStockout) {
        this.product_stockout = productStockout;
    }

    public Integer getIs_Book() {
        return is_Book;
    }

    public void setIs_Book(Integer isBook) {
        this.is_Book = isBook;
    }

    public Integer getIs_presell() {
        return is_presell;
    }

    public void setIs_presell(Integer isPresell) {
        this.is_presell = isPresell;
    }

    public Integer getIs_Gift() {
        return is_Gift;
    }

    public void setIs_Gift(Integer isGift) {
        this.is_Gift = isGift;
    }

    public Double getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(Double avgPrice) {
        this.avg_price = avgPrice;
    }

    public Double getProduct_freight() {
        return product_freight;
    }

    public void setProduct_freight(Double productFreight) {
        this.product_freight = productFreight;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shopId) {
        this.shop_id = shopId;
    }

    public String getOut_tid() {
        return out_tid;
    }

    public void setOut_tid(String outTid) {
        this.out_tid = outTid;
    }

    public String getOut_productId() {
        return out_productId;
    }

    public void setOut_productId(String outProductId) {
        this.out_productId = outProductId;
    }

    public String getOut_barCode() {
        return out_barCode;
    }

    public void setOut_barCode(String outBarCode) {
        this.out_barCode = outBarCode;
    }

    public String getProduct_intro() {
        return product_intro;
    }

    public void setProduct_intro(String productIntro) {
        this.product_intro = productIntro;
    }

}
