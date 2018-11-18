/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.vo;

import com.ningpai.m.goods.vo.GoodsProductReleSpecVo;

import java.math.BigDecimal;
import java.util.List;

/**
 * 商品Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年1月13日 下午5:20:59
 * @version 0.0.1
 */
public class GoodsBean {
    // 商品编号
    private Long goodsId;
    // 商品名称
    private String goodsName;
    // 商品价格
    private BigDecimal goodsPrice;
    // 商品会员价格
    private BigDecimal goodsVipPrice;
    // 商品图片
    private String goodsImg;
    // 商品数量
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
     * @see #setGoodsMarketPrice(BigDecimal)
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


    private Long shareId;

    private String shareFlag;



    // 订单Id
    private Long orderId;


    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    // 关联的规格值的集合
    private List<GoodsProductReleSpecVo> specVo;
    /**
     * 获取SpecVO
     * */
    public List<GoodsProductReleSpecVo> getSpecVo() {
        return specVo;
    }
    /**
     * 设置SpecVo
     * */
    public void setSpecVo(List<GoodsProductReleSpecVo> specVo) {
        this.specVo = specVo;
    }
    /**
     * 获取OrderId
     * */
    public Long getOrderId() {
        return orderId;
    }
    /**
     * 设置OrderId
     * */
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    /**
     * 获取GoodsScore
     * */
    public Long getGoodsScore() {
        return goodsScore;
    }
    /**
     * 设置GOodsScore
     * */
    public void setGoodsScore(Long goodsScore) {
        this.goodsScore = goodsScore;
    }
    /**
     * 获取CommentTag
     * */
    public String getCommentTag() {
        return commentTag;
    }
    /**
     * 设置CommentTag
     * */
    public void setCommentTag(String commentTag) {
        this.commentTag = commentTag;
    }
    /**
     * 获取GOodsNo
     * */
    public String getGoodsNo() {
        return goodsNo;
    }
    /**
     * 设置GOoodsNo
     * */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }
    /**
     * 获取OrderGoodsId
     * */
    public Long getOrderGoodsId() {
        return orderGoodsId;
    }
    /**
     * 设置OrderGoodsId
     * */
    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }
    /**
     * 获取CommentId
     * */
    public Long getCommentId() {
        return commentId;
    }
    /**
     * 设置CommentId
     * */
    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }
    /**
     * 获取EvalusteFlag
     * */
    public String getEvaluateFlag() {
        return evaluateFlag;
    }
    /**
     * 设置EvsluateFlag
     * */
    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }
    /**
     * 获取CommentCount
     * */
    public Long getCommentCount() {
        return commentCount;
    }
    /**
     * 设置CommentCount
     * */
    public void setCommentCount(Long commentCount) {
        this.commentCount = commentCount;
    }
    /**
     * 获取ConsultCount
     * */
    public Long getConsultCount() {
        return consultCount;
    }
    /**
     * 设置CoonsultCount
     * */
    public void setConsultCount(Long consultCount) {
        this.consultCount = consultCount;
    }
    /**
     * 获取GoodsStock
     * */
    public Long getGoodStock() {
        return goodStock;
    }
    /**
     * 设置GoodStock
     * */
    public void setGoodStock(Long goodStock) {
        this.goodStock = goodStock;
    }
    /**
     * 获取GoodsMarketPrice
     * */
    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }
    /**
     * 设置GoodsMarketPrice
     * */
    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }
    /**
     * 获取Integral
     * */
    public Long getIntegral() {
        return integral;
    }
    /**
     * 设置Integral
     * */
    public void setIntegral(Long integral) {
        this.integral = integral;
    }
    /**
     * 获取GoodsNum
     * */
    public Long getGoodsNum() {
        return goodsNum;
    }
    /**
     * 设置GoodsNum
     * */
    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }
    /**
     * 获取GoodsId
     * */
    public Long getGoodsId() {
        return goodsId;
    }
    /**
     * 设置GoodsId
     * */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    /**
     * 获取GoodsName
     * */
    public String getGoodsName() {
        return goodsName;
    }
    /**
     * 设置GOodsName
     * */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    /**
     * 获取GoodsPrice
     * */
    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }
    /**
     * 设置GOoodsPrice
     * */
    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 获取GoodsVipPrice
     * */
    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }
    /**
     * 设置GoodsVipPrice
     * */
    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }
    /**
     * 获取GoodsImg
     * */
    public String getGoodsImg() {
        return goodsImg;
    }
    /**
     * 设置GoodsImg
     * */
    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

}
