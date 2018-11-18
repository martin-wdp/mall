package com.ningpai.api.bean;

import java.math.BigDecimal;

/**
 * 开放接口--订单商品
 * @author lih
 * @version 2.0
 * @since 15/8/31
 */
public class OOrderGoods {

    /**
     * 订单商品id
     */
    private Long  orderGoodsId;

    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 订单商品数量
     */
    private Long goodsInfoNum;

    /**
     * 订单总价格
     */
    private BigDecimal goodsInfoSumPrice;

    /**
     * 订单商品价格
     */
    private BigDecimal goodsInfoPrice;

    /**
     * 订单商品名称
     */
    private String goodsInfoName;

    /**
     * 商品id
     */
    private Long goodsInfod;

    /**
     * 订单商品id
     */
    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }
    /**
     * 订单id
     */
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    /**
     * 订单商品数量
     */
    public Long getGoodsInfoNum() {
        return goodsInfoNum;
    }

    public void setGoodsInfoNum(Long goodsInfoNum) {
        this.goodsInfoNum = goodsInfoNum;
    }
    /**
     * 订单总价格
     */
    public BigDecimal getGoodsInfoSumPrice() {
        return goodsInfoSumPrice;
    }

    public void setGoodsInfoSumPrice(BigDecimal goodsInfoSumPrice) {
        this.goodsInfoSumPrice = goodsInfoSumPrice;
    }
    /**
     * 订单商品价格
     */
    public BigDecimal getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(BigDecimal goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }
    /**
     * 订单商品名称
     */
    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }
    /**
     * 商品id
     */
    public Long getGoodsInfod() {
        return goodsInfod;
    }

    public void setGoodsInfod(Long goodsInfod) {
        this.goodsInfod = goodsInfod;
    }
}
