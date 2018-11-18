/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean.vo;

import java.math.BigDecimal;

/**
 * 商品Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午5:20:59
 * @version 0.0.1
 */
public class GoodsBean {
    /**
     * 商品编号
     */
    private Long goodsId;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品价格
     */
    private BigDecimal goodsPrice;
    /**
     * 商品图片
     */
    private String goodsImg;
    /**
     * 商品数量
     */
    private Long goodsNum;
    /**
     * 商品积分
     * 
     * @see #getIntegral()
     * @see #setIntegral(Long)
     */
    private Long integral;
    /**
     * 市场价
     * 
     * @see #goodsMarketPrice
     * @see #setGoodsMarketPrice(java.math.BigDecimal)
     */
    private BigDecimal goodsMarketPrice;
    /**
     * 库存
     * 
     * @see #getGoodStock()
     * @see #setGoodStock(Long)
     */
    private Long goodStock;
    // 评价数量
    private Long commentCount;
    // 咨询数量
    private Long consultCount;
    // 是否已经评价
    private String evaluateFlag;
    // 评价编号
    private Long commentId;
    // 订单商品编号
    private Long orderGoodsId;
    // 货品编号
    private String goodsNo;
    // 评论标签
    private String commentTag;
    // 货品总评分
    private Long goodsScore;

    // 订单Id
    private Long orderId;

    private String shareFlag;

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(Long goodsScore) {
        this.goodsScore = goodsScore;
    }

    public String getCommentTag() {
        return commentTag;
    }

    public void setCommentTag(String commentTag) {
        this.commentTag = commentTag;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public Long getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }

    public Long getConsultCount() {
        return consultCount;
    }

    public void setConsultCount(Long consultCount) {
        this.consultCount = consultCount;
    }

    public Long getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(Long goodStock) {
        this.goodStock = goodStock;
    }

    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public Long getIntegral() {
        return integral;
    }

    public void setIntegral(Long integral) {
        this.integral = integral;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

}
