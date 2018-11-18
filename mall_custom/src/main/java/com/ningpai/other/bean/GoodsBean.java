/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.other.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ningpai.comment.bean.ShareImg;

/**
 * 商品Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月23日 下午7:34:04
 * @version 0.0.1
 */
public class GoodsBean {

    // 商品编号
    private Long goodsId;
    // 商品名称
    private String goodsName;
    // 商品价格
    private BigDecimal goodsPrice;
    // 销售价格
    private BigDecimal goodsPreferPrice;
    // vip价格
    private BigDecimal goodsVipPrice;
    // 商品图片
    private String goodsImg;
    // 商品数量
    private Long goodsNum;
    // 购买时间
    private Date buyTime;
    // 是否已经评价
    private String evaluateFlag;
    // 货品编号
    private String goodsNo;
    // 晒单标记
    private String shareFlag;
    // 晒单图片
    private List<ShareImg> imageList;

    // 货品总评分
    private Long goodsScore;

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
    // 分类编号
    private Long cateId;

    // 晒单编号
    private Long shareId;

    public BigDecimal getGoodsPreferPrice() {
        return goodsPreferPrice;
    }

    public void setGoodsPreferPrice(BigDecimal goodsPreferPrice) {
        this.goodsPreferPrice = goodsPreferPrice;
    }

    public BigDecimal getGoodsVipPrice() {
        return goodsVipPrice;
    }

    public void setGoodsVipPrice(BigDecimal goodsVipPrice) {
        this.goodsVipPrice = goodsVipPrice;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public Long getGoodsScore() {
        return goodsScore;
    }

    public void setGoodsScore(Long goodsScore) {
        this.goodsScore = goodsScore;
    }

    public BigDecimal getGoodsMarketPrice() {
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(BigDecimal goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public Long getGoodStock() {
        return goodStock;
    }

    public void setGoodStock(Long goodStock) {
        this.goodStock = goodStock;
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

    public List<ShareImg> getImageList() {
        return imageList;
    }

    public void setImageList(List<ShareImg> imageList) {
        this.imageList = imageList;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * 获取购买时间
     * */
    public Date getBuyTime() {
        if (this.buyTime != null) {
            return new Date(this.buyTime.getTime());
        } else {
            return null;
        }
    }

    /**
     * 设置购买时间
     * */
    public void setBuyTime(Date buyTime) {
        if (buyTime != null) {
            Date tEmp = (Date) buyTime.clone();
            if (tEmp != null) {
                this.buyTime = tEmp;
            }
        }
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
