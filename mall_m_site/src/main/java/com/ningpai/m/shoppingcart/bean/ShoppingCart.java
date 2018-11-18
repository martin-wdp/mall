/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.m.shoppingcart.bean;
import com.ningpai.goods.vo.GoodsGroupVo;
import com.ningpai.m.goods.bean.GoodsDetailBean;
import com.ningpai.marketing.bean.Marketing;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 购物车主表 2014-04-14
 * @author ggn
 *
 */
public class ShoppingCart {
    /**
    *购物车ID
     */
    private Long shoppingCartId;
    /**
     *货品ID
     */
    private Long goodsInfoId;
    /**
    *优惠价格
     */
    private Long goodsPrePrice;
    /**
     *商品价格
     */
    private BigDecimal goodsPrice;
    /**
     *数量
     */
    private Long goodsNum;
    /**
     *套包ＩＤ
     */
    private Long fitId;
    /**
     *客户Id
     */
    private Long customerId;
    /**
     *促销ID
     */
    private Long marketingId;
    /**
     *时间
     */
    private Date shoppingCartTime;
    /**
     *是否删除
     */
    private String delFlag;
    /**
     *促销
     */
    private Marketing marketing;
    /**
     *货品明细
     */
    private GoodsDetailBean goodsDetailBean;
    /**
     *货品参加的促销
     */
    private List<Marketing> marketingList;
    
    /**
     *仓库Id
     */
    private Long distinctId;
    
    /**
     *活动优惠id
     */
    private Long marketingActivityId;
    
    /**
     *参加的订单促销
     */
    private List<Marketing> orderMarketing;
    
    /**
     *订单优惠id
     */
    private Long orderMarketingId;
    
    /**
    *订单促销
     */
    private Marketing orderMarket;
    
    /**
     *优惠分组
     */
    private GoodsGroupVo goodsGroupVo;
    /**
     *运费模板id
     */
    private Long freightId;

    /**
    *商品重量
     */
    private BigDecimal goodsInfoWeight;

    /**
     *商品所属第三方
     */
    private Long thirdId;

    /**
     *促销类型
     */
    private String codeType;
    /**
     *商品选中状态
     */
    private String shoppingStatus;
    /**
     *是否包邮
     */
    private String isBaoyou;
    /**
     *促销后价格
     */
    private BigDecimal marketgoodsPrice;

    /**
     *判断有效时间内是否参与折扣
     */
    /**
     *0:折扣促销有效,1:折扣促销无效
     */
    private  Integer isMarketing;

    /**
     * 产品团购促销id
     */
    private Long goodsGroupId;

    public Long getGoodsGroupId() {
        return goodsGroupId;
    }

    public void setGoodsGroupId(Long goodsGroupId) {
        this.goodsGroupId = goodsGroupId;
    }

    public Integer getIsMarketing() {
        return isMarketing;
    }

    public void setIsMarketing(Integer isMarketing) {
        this.isMarketing = isMarketing;
    }

    public String getIsBaoyou(){
        return isBaoyou;
    }
    public void setIsBaoyou(String isBaoyou){
        this.isBaoyou=isBaoyou;
    }
    public String getShoppingStatus() {
        return shoppingStatus;
    }

    public BigDecimal getMarketgoodsPrice() {
        return marketgoodsPrice;
    }

    public void setMarketgoodsPrice(BigDecimal marketgoodsPrice) {
        this.marketgoodsPrice = marketgoodsPrice;
    }

    public void setShoppingStatus(String shoppingStatus) {
        this.shoppingStatus = shoppingStatus;
    }

    public GoodsGroupVo getGoodsGroupVo() {
        return goodsGroupVo;
    }

    public void setGoodsGroupVo(GoodsGroupVo goodsGroupVo) {
        this.goodsGroupVo = goodsGroupVo;
    }

    public Long getOrderMarketingId() {
        return orderMarketingId;
    }

    public void setOrderMarketingId(Long orderMarketingId) {
        this.orderMarketingId = orderMarketingId;
    }

    public Marketing getOrderMarket() {
        return orderMarket;
    }

    public void setOrderMarket(Marketing orderMarket) {
        this.orderMarket = orderMarket;
    }

    public List<Marketing> getOrderMarketing() {
        return orderMarketing;
    }

    public void setOrderMarketing(List<Marketing> orderMarketing) {
        this.orderMarketing = orderMarketing;
    }

    public Long getMarketingActivityId() {
        return marketingActivityId;
    }

    public void setMarketingActivityId(Long marketingActivityId) {
        this.marketingActivityId = marketingActivityId;
    }

    public Long getDistinctId() {
        return distinctId;
    }

    public void setDistinctId(Long distinctId) {
        this.distinctId = distinctId;
    }

    public List<Marketing> getMarketingList() {
        return marketingList;
    }

    public void setMarketingList(List<Marketing> marketingList) {
        this.marketingList = marketingList;
    }

    public GoodsDetailBean getGoodsDetailBean() {
        return goodsDetailBean;
    }

    public void setGoodsDetailBean(GoodsDetailBean goodsDetailBean) {
        this.goodsDetailBean = goodsDetailBean;
    }

    public Marketing getMarketing() {
        return marketing;
    }

    public void setMarketing(Marketing marketing) {
        this.marketing = marketing;
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

    public Long getGoodsPrePrice() {
        return goodsPrePrice;
    }

    public void setGoodsPrePrice(Long goodsPrePrice) {
        this.goodsPrePrice = goodsPrePrice;
    }

    public BigDecimal getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(BigDecimal goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public Long getFreightId() {
        return freightId;
    }

    public void setFreightId(Long freightId) {
        this.freightId = freightId;
    }

    public BigDecimal getGoodsInfoWeight() {
        return goodsInfoWeight;
    }

    public void setGoodsInfoWeight(BigDecimal goodsInfoWeight) {
        this.goodsInfoWeight = goodsInfoWeight;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    public Long getFitId() {
        return fitId;
    }

    public void setFitId(Long fitId) {
        this.fitId = fitId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    /**
     * 获取时间
     * */
    public Date getShoppingCartTime() {
        if (this.shoppingCartTime!=null) {
            return new Date(this.shoppingCartTime.getTime());
        }
        return null;
    }
    /**
     * 设置时间
     * */
    public void setShoppingCartTime(Date shoppingCartTime) {
        if (shoppingCartTime != null) {
            Date tEmp = (Date) shoppingCartTime.clone();
            if (tEmp != null) {
                this.shoppingCartTime = tEmp;
            }
        }
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
