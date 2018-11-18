/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;
import com.ningpai.marketing.bean.Marketing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单商品信息表
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月27日14:43:11
 * 
 */
public class OrderGoods {
    /**
     * 订单商品ID
     */
    private Long orderGoodsId;
    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 货品ＩＤ
     */
    private Long goodsInfoId;
    /**
     * 货品数量
     */
    private Long goodsInfoNum;
    /**
     * 货品原价
     */
    private BigDecimal goodsInfoOldPrice;
    /**
     * 货品价格
     */
    private BigDecimal goodsInfoPrice;
    /**
     * 优惠金额
     */
    private BigDecimal goodsCouponPrice;
    /**
     * 总价格（小计）
     */
    private BigDecimal goodsInfoSumPrice;
    /**
     * 是否有赠品
     */
    private String haveGiftStatus;
    /**
     * 是否有优惠券
     */
    private String haveCouponStatus;
    /**
     * 促销ID
     */
    private Long goodsMarketingId;
    /**
     * 购买时间
     */
    private Date buyTime;
    /**
     * 是否删除
     */
    private String delFlag;
    /**
     * 是否评价
     */
    private String evaluateFlag;
    /**
     * 是否分享
     */
    private String shareFlag;
    /**
     * 是否退单
     */
    private String backFlag;
    /**
     * 退单单号
     */
    private String backOrderCode;
    /**
     * 换单编号
     */
    private String barterOrderCode;
    /**
     * 地区id
     */
    private Long distinctId;

    /**
     * 货品名称
     */
    private String goodsInfoName;

    /**
     * 用戶id
     */
    private Long customerId;
    /**
     * 货品编号
     */
    private String goodsInfoItemNo;

    /**
     * 货品信息
     */
    private GoodsProductDetailViewVo goodsProductVo;
    /**
     * 货品参加的促销信息
     */
    private Marketing marketing;
    /**
     * 货品赠送的优惠券
     */
    private List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList;

    /**
     * 货品赠送的赠品
     */
    private List<OrderGoodsInfoGift> orderGoodsInfoGiftList;

    /**
     * 活动订单优惠id
     */
    private Long goodsActiveMarketingId;
    /**
     * 货品图片
     */
    private String goodsImg;
    /**
     * 货品名称
     */
    private String goodsName;
    /**
     * 货品编号
     */
    private String goodsCode;

    /**
     * 货品参与团购促销的id
     */
    private  Long goodsGroupMarketingId;

    /**
     * 商品规格
     */
    private List<GoodsProductReleSpecVo> goodsProductReleSpecVoList;

    public String getGoodsImg() {
        return goodsImg;
    }

    public void setGoodsImg(String goodsImg) {
        this.goodsImg = goodsImg;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsInfoItemNo() {
        return goodsInfoItemNo;
    }

    public void setGoodsInfoItemNo(String goodsInfoItemNo) {
        this.goodsInfoItemNo = goodsInfoItemNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

    public Long getGoodsActiveMarketingId() {
        return goodsActiveMarketingId;
    }

    public void setGoodsActiveMarketingId(Long goodsActiveMarketingId) {
        this.goodsActiveMarketingId = goodsActiveMarketingId;
    }

    public String getBackFlag() {
        return backFlag;
    }

    public void setBackFlag(String backFlag) {
        this.backFlag = backFlag;
    }

    public String getBackOrderCode() {
        return backOrderCode;
    }

    public void setBackOrderCode(String backOrderCode) {
        this.backOrderCode = backOrderCode;
    }

    public List<OrderGoodsInfoGift> getOrderGoodsInfoGiftList() {
        return orderGoodsInfoGiftList;
    }

    public void setOrderGoodsInfoGiftList(
            List<OrderGoodsInfoGift> orderGoodsInfoGiftList) {
        this.orderGoodsInfoGiftList = orderGoodsInfoGiftList;
    }

    public List<OrderGoodsInfoCoupon> getOrderGoodsInfoCouponList() {
        return orderGoodsInfoCouponList;
    }

    public void setOrderGoodsInfoCouponList(
            List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList) {
        this.orderGoodsInfoCouponList = orderGoodsInfoCouponList;
    }

    public Marketing getMarketing() {
        return marketing;
    }

    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
    }

    public GoodsProductDetailViewVo getGoodsProductVo() {
        return goodsProductVo;
    }

    public void setGoodsProductVo(GoodsProductDetailViewVo goodsProductVo) {
        this.goodsProductVo = goodsProductVo;
    }

    public String getEvaluateFlag() {
        return evaluateFlag;
    }

    public void setEvaluateFlag(String evaluateFlag) {
        this.evaluateFlag = evaluateFlag;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public Long getOrderGoodsId() {
        return orderGoodsId;
    }

    public void setOrderGoodsId(Long orderGoodsId) {
        this.orderGoodsId = orderGoodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }

    public Long getGoodsInfoNum() {
        return goodsInfoNum;
    }

    public void setGoodsInfoNum(Long goodsInfoNum) {
        this.goodsInfoNum = goodsInfoNum;
    }

    public BigDecimal getGoodsInfoOldPrice() {
        return goodsInfoOldPrice;
    }

    public void setGoodsInfoOldPrice(BigDecimal goodsInfoOldPrice) {
        this.goodsInfoOldPrice = goodsInfoOldPrice;
    }

    public BigDecimal getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(BigDecimal goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }

    public BigDecimal getGoodsCouponPrice() {
        return goodsCouponPrice;
    }

    public void setGoodsCouponPrice(BigDecimal goodsCouponPrice) {
        this.goodsCouponPrice = goodsCouponPrice;
    }

    public BigDecimal getGoodsInfoSumPrice() {
        return goodsInfoSumPrice;
    }

    public void setGoodsInfoSumPrice(BigDecimal goodsInfoSumPrice) {
        this.goodsInfoSumPrice = goodsInfoSumPrice;
    }

    public String getHaveGiftStatus() {
        return haveGiftStatus;
    }

    public void setHaveGiftStatus(String haveGiftStatus) {
        this.haveGiftStatus = haveGiftStatus;
    }

    public String getHaveCouponStatus() {
        return haveCouponStatus;
    }

    public void setHaveCouponStatus(String haveCouponStatus) {
        this.haveCouponStatus = haveCouponStatus;
    }

    public Long getGoodsMarketingId() {
        return goodsMarketingId;
    }

    public void setGoodsMarketingId(Long goodsMarketingId) {
        this.goodsMarketingId = goodsMarketingId;
    }

    /**getBuyTime
     * @return
     */
    public Date getBuyTime() {
        if (this.buyTime != null) {
            return new Date(this.buyTime.getTime());
        }
        return null;
    }

    /**setBuyTime
     * @param buyTime
     */
    public void setBuyTime(Date buyTime) {
        if (buyTime != null) {
            Date tEmp = (Date) buyTime.clone();
            if (tEmp != null) {
                this.buyTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getBarterOrderCode() {
        return barterOrderCode;
    }

    public void setBarterOrderCode(String barterOrderCode) {
        this.barterOrderCode = barterOrderCode;
    }

    public List<GoodsProductReleSpecVo> getGoodsProductReleSpecVoList() {
        return goodsProductReleSpecVoList;
    }

    public void setGoodsProductReleSpecVoList(
            List<GoodsProductReleSpecVo> goodsProductReleSpecVoList) {
        this.goodsProductReleSpecVoList = goodsProductReleSpecVoList;
    }

    public Long getGoodsGroupMarketingId() {
        return goodsGroupMarketingId;
    }

    public void setGoodsGroupMarketingId(Long goodsGroupMarketingId) {
        this.goodsGroupMarketingId = goodsGroupMarketingId;
    }
}
