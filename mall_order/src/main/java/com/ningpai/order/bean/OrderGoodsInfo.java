/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.bean;

import com.ningpai.gift.bean.Gift;
import com.ningpai.goods.vo.GoodsProductVo;

import java.math.BigDecimal;

/**
 * 查询货品id和货品数量
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日14:17:12
 * 
 */
public class OrderGoodsInfo {
    /**
     * 货品id
     */
    private Long goodsInfoId;
    /**
     * 货品数量
     */
    private Long goodsInfoNum;
    /**
     * 货品销售价格
     */
    private BigDecimal goodsInfoPrice;
    /**
     * 货品
     */
    private GoodsProductVo goodsProductVo;

    /**
     * 赠品id
     */
    private Long giftId;

    /**
     * 赠品数量
     */
    private Long giftNum;

    /**
     * 赠品的详细信息
     */
    private Gift gift;

    public BigDecimal getGoodsInfoPrice() {
        return goodsInfoPrice;
    }

    public void setGoodsInfoPrice(BigDecimal goodsInfoPrice) {
        this.goodsInfoPrice = goodsInfoPrice;
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public Long getGiftId() {
        return giftId;
    }

    public void setGiftId(Long giftId) {
        this.giftId = giftId;
    }

    public Long getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Long giftNum) {
        this.giftNum = giftNum;
    }

    public GoodsProductVo getGoodsProductVo() {
        return goodsProductVo;
    }

    public void setGoodsProductVo(GoodsProductVo goodsProductVo) {
        this.goodsProductVo = goodsProductVo;
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

}
