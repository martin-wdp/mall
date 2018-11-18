/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.gift.bean.Gift;

/**
 * 营销信息
 * 
 * @author NINGPAI-LIH
 * @since 2014年3月19日上午10:56:02
 */
public class Marketing {
    /**
     * 营销ID
     */
    private Long marketingId;
    /**
     * 营销名称
     */
    @Length(min = 1, max = 225, message = "促销名称在1-20之间")
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String marketingName;
    /**
     * 营销描述
     */
    private String marketingDes;
    /**
     * 营销类型
     */
    private String marketingType;
    /**
     * 开始时间
     */
    private Date marketingBegin;
    /**
     * 结束时间
     */
    private Date marketingEnd;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间按
     */
    private Date modTime;
    /**
     * 标记
     */
    private String flag;
    /**
     * 促销类型名称
     */
    private String codexName;
    /**
     * 促销ID
     */
    private Long codexId;
    /**
     * 促销类型
     */
    private String codexType;

    /**
     * 是否叠加
     */
    private String isRepeat;

    /**
     * 第三方标示
     */
    private Long businessId;

    /**
     * 是否全场促销
     */
    private String isAll;
    /**
     * 直降信息
     */
    private PriceOffMarketing priceOffMarketing;

    /**
     * 买赠品信息
     */
    private List<Gift> giftList;

    /**
     * 买送优惠券信息
     */
    private List<Coupon> couponList;

    /**
     * 买折信息
     */
    private DiscountMarketing discountMarketing;
    /**
     * 满减
     */
    private FullbuyReduceMarketing fullbuyReduceMarketing;
    /**
     * 满赠赠品
     */
    private FullbuyPresentMarketing fullbuyPresentMarketing;
    /**
     * 满送优惠券
     */
    private FullbuyCouponMarketing fullbuyCouponMarketing;

    /**
     * 店铺标示
     */
    private Long thirId;
    /**
     * 满折
     */
    private FullbuyDiscountMarketing fullbuyDiscountMarketing;
    /**
     * 限购
     */
    private LimitBuyMarketing limitBuyMarketing;
    /**
     * 满x件打y折
     */
    private FullbuyNoDiscountMarketing fullbuyNoDiscountMarketing;
    /**
     * 满购件数多少钱
     */
    private FullbuyNoCountMarketing fullbuyNoCountMarketing;
    /**
     * 折扣促销
     */
    private PreDiscountMarketing preDiscountMarketing;

    /**
     * 团购
     */
    private Groupon groupon;

    /**
     * 抢购秒杀
     */
    private MarketingRush rush;
    /**
     * 优惠分组id
     */
    private Long groupId;

    /**
     * 优惠分组名称
     */
    private String preferentialName;

    /**
     * 所属商家名称
     */
    private String infoRealname;

    /**
     * 促销标示 0：分类促销 1：品牌促销 3：sku促销
     */
    private String scopeType;

    /**
     * 抢购集合
     */
    List<MarketingRush> rushs;

    /**
     * 附加赠送类型 (0:积分 1：优惠券)
     */
    private String addGiveType;

    /**
     * 积分
     */
    private Integer giveIntegral;

    /**
     * 优惠券
     */
    private Long couponId;

    /**
     * 活动站点设置(0：平台 1：移动端 2：全部)
     */
    private String activeSiteType;

    /**
     * 促销等级
     */
    private List<MarketLelvel> marketLelvels;

    /**
     * 促销活动对应的促销LOGO
     */
    private List<MarketLogo> marketLogos;

    /**
     * 优惠券名称
     */
    private String couponName;

    /**
     * 满减list
     */
    private List<FullbuyReduceMarketing> fullbuyReduceMarketings;

    /**
     * 满折list
     */
    private List<FullbuyDiscountMarketing> fullbuyDiscountMarketings;

    /**
     * 满x件打y折list
     */
    private List<FullbuyNoDiscountMarketing> fullbuyNoDiscountMarketings;
    /**
     * 满购件数多少钱list
     */
    private List<FullbuyNoCountMarketing> fullbuyNoCountMarketings;

    /**
     * 折扣list
     */
    private List<PreDiscountMarketing> preDiscountMarketings;

    /**
     * 满多少钱包邮
     */
    private BigDecimal shippingMoney;
    /**
     * 会员满多少钱包邮
     */
    private BigDecimal vipShippingMoney;

    /**
     * 优惠券对象
     */
    private Coupon coupon;

    /**
     * 分组
     */
    private String marketGroupId;

    /**
     * 该会员下可以购买该抢购的货品数量
     */
    private Integer customerbuynum;

    public Integer getCustomerbuynum() {
        return customerbuynum;
    }

    public void setCustomerbuynum(Integer customerbuynum) {
        this.customerbuynum = customerbuynum;
    }

    public BigDecimal getVipShippingMoney() {
        return vipShippingMoney;
    }

    public void setVipShippingMoney(BigDecimal vipShippingMoney) {
        this.vipShippingMoney = vipShippingMoney;
    }

    public List<MarketingRush> getRushs() {
        return rushs;
    }

    public void setRushs(List<MarketingRush> rushs) {
        this.rushs = rushs;
    }

    public MarketingRush getRush() {
        return rush;
    }

    public void setRush(MarketingRush rush) {
        this.rush = rush;
    }

    public Groupon getGroupon() {
        return groupon;
    }

    public void setGroupon(Groupon groupon) {
        this.groupon = groupon;
    }

    public String getMarketGroupId() {
        return marketGroupId;
    }

    public void setMarketGroupId(String marketGroupId) {
        this.marketGroupId = marketGroupId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getInfoRealname() {
        return infoRealname;
    }

    public void setInfoRealname(String infoRealname) {
        this.infoRealname = infoRealname;
    }

    public Long getThirId() {
        return thirId;
    }

    public void setThirId(Long thirId) {
        this.thirId = thirId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getPreferentialName() {
        return preferentialName;
    }

    public void setPreferentialName(String preferentialName) {
        this.preferentialName = preferentialName;
    }

    public LimitBuyMarketing getLimitBuyMarketing() {
        return limitBuyMarketing;
    }

    public void setLimitBuyMarketing(LimitBuyMarketing limitBuyMarketing) {
        this.limitBuyMarketing = limitBuyMarketing;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getIsRepeat() {
        return isRepeat;
    }

    public void setIsRepeat(String isRepeat) {
        this.isRepeat = isRepeat;
    }

    public FullbuyDiscountMarketing getFullbuyDiscountMarketing() {
        return fullbuyDiscountMarketing;
    }

    public void setFullbuyDiscountMarketing(FullbuyDiscountMarketing fullbuyDiscountMarketing) {
        this.fullbuyDiscountMarketing = fullbuyDiscountMarketing;
    }

    public FullbuyCouponMarketing getFullbuyCouponMarketing() {
        return fullbuyCouponMarketing;
    }

    public void setFullbuyCouponMarketing(FullbuyCouponMarketing fullbuyCouponMarketing) {
        this.fullbuyCouponMarketing = fullbuyCouponMarketing;
    }

    public FullbuyPresentMarketing getFullbuyPresentMarketing() {
        return fullbuyPresentMarketing;
    }

    public void setFullbuyPresentMarketing(FullbuyPresentMarketing fullbuyPresentMarketing) {
        this.fullbuyPresentMarketing = fullbuyPresentMarketing;
    }

    public FullbuyReduceMarketing getFullbuyReduceMarketing() {
        return fullbuyReduceMarketing;
    }

    public void setFullbuyReduceMarketing(FullbuyReduceMarketing fullbuyReduceMarketing) {
        this.fullbuyReduceMarketing = fullbuyReduceMarketing;
    }

    public DiscountMarketing getDiscountMarketing() {
        return discountMarketing;
    }

    public void setDiscountMarketing(DiscountMarketing discountMarketing) {
        this.discountMarketing = discountMarketing;
    }

    public List<Coupon> getCouponList() {
        return couponList;
    }

    public void setCouponList(List<Coupon> couponList) {
        this.couponList = couponList;
    }

    public List<Gift> getGiftList() {
        return giftList;
    }

    public void setGiftList(List<Gift> giftList) {
        this.giftList = giftList;
    }

    public PriceOffMarketing getPriceOffMarketing() {
        return priceOffMarketing;
    }

    public void setPriceOffMarketing(PriceOffMarketing priceOffMarketing) {
        this.priceOffMarketing = priceOffMarketing;
    }

    public Long getCodexId() {
        return codexId;
    }

    public void setCodexId(Long codexId) {
        this.codexId = codexId;
    }

    public String getCodexType() {
        return codexType;
    }

    public void setCodexType(String codexType) {
        this.codexType = codexType;
    }

    public String getCodexName() {
        return codexName;
    }

    public void setCodexName(String codexName) {
        this.codexName = codexName;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getMarketingName() {
        return marketingName;
    }

    public void setMarketingName(String marketingName) {
        this.marketingName = marketingName;
    }

    public String getMarketingDes() {
        return marketingDes;
    }

    public void setMarketingDes(String marketingDes) {
        this.marketingDes = marketingDes;
    }

    public String getMarketingType() {
        return marketingType;
    }

    public void setMarketingType(String marketingType) {
        this.marketingType = marketingType;
    }

    /**
     * 开始时间
     * 
     * @return Date
     */
    public Date getMarketingBegin() {
        if (this.marketingBegin != null) {
            return new Date(this.marketingBegin.getTime());
        }
        return null;
    }

    /**
     * 开始时间
     * 
     * @param marketingBegin
     */
    public void setMarketingBegin(Date marketingBegin) {
        if (marketingBegin != null) {
            Date tEmp = (Date) marketingBegin.clone();
            if (tEmp != null) {
                this.marketingBegin = tEmp;
            }
        }
    }

    /**
     * 结束时间
     * 
     * @return
     */
    public Date getMarketingEnd() {
        if (this.marketingEnd != null) {
            return new Date(this.marketingEnd.getTime());
        }
        return null;
    }

    /**
     * 结束时间
     * 
     * @param marketingEnd
     */
    public void setMarketingEnd(Date marketingEnd) {
        if (marketingEnd != null) {
            Date tEmp = (Date) marketingEnd.clone();
            if (tEmp != null) {
                this.marketingEnd = tEmp;
            }
        }
    }

    /**
     * 创建时间
     * 
     * @return
     */
    public Date getCreateTime() {
        if (this.createTime != null) {
            return new Date(this.createTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 创建时间
     * 
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        if (createTime != null) {
            Date tEmp = (Date) createTime.clone();
            if (tEmp != null) {
                this.createTime = tEmp;
            }
        }
    }

    /**
     * 修改时间
     * 
     * @return
     */
    public Date getModTime() {
        if (this.modTime != null) {
            return new Date(this.modTime.getTime()); // 正确值
        } else {
            return null;
        }
    }

    /**
     * 修改时间
     * 
     * @param modTime
     */
    public void setModTime(Date modTime) {
        if (modTime != null) {
            Date tEmp = (Date) modTime.clone();
            if (tEmp != null) {
                this.modTime = tEmp;
            }
        }
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getIsAll() {
        return isAll;
    }

    public void setIsAll(String isAll) {
        this.isAll = isAll;
    }

    public FullbuyNoDiscountMarketing getFullbuyNoDiscountMarketing() {
        return fullbuyNoDiscountMarketing;
    }

    public void setFullbuyNoDiscountMarketing(FullbuyNoDiscountMarketing fullbuyNoDiscountMarketing) {
        this.fullbuyNoDiscountMarketing = fullbuyNoDiscountMarketing;
    }

    public FullbuyNoCountMarketing getFullbuyNoCountMarketing() {
        return fullbuyNoCountMarketing;
    }

    public void setFullbuyNoCountMarketing(FullbuyNoCountMarketing fullbuyNoCountMarketing) {
        this.fullbuyNoCountMarketing = fullbuyNoCountMarketing;
    }

    public PreDiscountMarketing getPreDiscountMarketing() {
        return preDiscountMarketing;
    }

    public void setPreDiscountMarketing(PreDiscountMarketing preDiscountMarketing) {
        this.preDiscountMarketing = preDiscountMarketing;
    }

    public String getAddGiveType() {
        return addGiveType;
    }

    public void setAddGiveType(String addGiveType) {
        this.addGiveType = addGiveType;
    }

    public Integer getGiveIntegral() {
        return giveIntegral;
    }

    public void setGiveIntegral(Integer giveIntegral) {
        this.giveIntegral = giveIntegral;
    }

    public Long getCouponId() {
        return couponId;
    }

    public void setCouponId(Long couponId) {
        this.couponId = couponId;
    }

    public String getActiveSiteType() {
        return activeSiteType;
    }

    public void setActiveSiteType(String activeSiteType) {
        this.activeSiteType = activeSiteType;
    }

    /**
     * 促销等级
     * 
     * @return
     */
    public List<MarketLelvel> getMarketLelvels() {
        return marketLelvels;
    }

    /**
     * 促销等级
     * 
     * @param marketLelvels
     */
    public void setMarketLelvels(List<MarketLelvel> marketLelvels) {
        this.marketLelvels = marketLelvels;
    }

    public List<MarketLogo> getMarketLogos() {
        return marketLogos;
    }

    public void setMarketLogos(List<MarketLogo> marketLogos) {
        this.marketLogos = marketLogos;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    /**
     * 满减list
     * 
     * @return
     */
    public List<FullbuyReduceMarketing> getFullbuyReduceMarketings() {
        return fullbuyReduceMarketings;
    }

    /**
     * 满减list
     * 
     * @param fullbuyReduceMarketings
     */
    public void setFullbuyReduceMarketings(List<FullbuyReduceMarketing> fullbuyReduceMarketings) {
        this.fullbuyReduceMarketings = fullbuyReduceMarketings;
    }

    /**
     * 满折list
     * 
     * @return
     */
    public List<FullbuyDiscountMarketing> getFullbuyDiscountMarketings() {
        return fullbuyDiscountMarketings;
    }

    /**
     * 满折list
     * 
     * @param fullbuyDiscountMarketings
     */
    public void setFullbuyDiscountMarketings(List<FullbuyDiscountMarketing> fullbuyDiscountMarketings) {
        this.fullbuyDiscountMarketings = fullbuyDiscountMarketings;
    }

    /**
     * 满x件打y折list
     * 
     * @return
     */
    public List<FullbuyNoDiscountMarketing> getFullbuyNoDiscountMarketings() {
        return fullbuyNoDiscountMarketings;
    }

    /**
     * 满x件打y折list
     * 
     * @param fullbuyNoDiscountMarketings
     */
    public void setFullbuyNoDiscountMarketings(List<FullbuyNoDiscountMarketing> fullbuyNoDiscountMarketings) {
        this.fullbuyNoDiscountMarketings = fullbuyNoDiscountMarketings;
    }

    /**
     * 满购件数多少钱lis
     * 
     * @return
     */
    public List<FullbuyNoCountMarketing> getFullbuyNoCountMarketings() {
        return fullbuyNoCountMarketings;
    }

    /**
     * 满购件数多少钱lis
     * 
     * @param fullbuyNoCountMarketings
     */
    public void setFullbuyNoCountMarketings(List<FullbuyNoCountMarketing> fullbuyNoCountMarketings) {
        this.fullbuyNoCountMarketings = fullbuyNoCountMarketings;
    }

    public BigDecimal getShippingMoney() {
        return shippingMoney;
    }

    public void setShippingMoney(BigDecimal shippingMoney) {
        this.shippingMoney = shippingMoney;
    }

    /**
     * 折扣list
     * 
     * @return
     */
    public List<PreDiscountMarketing> getPreDiscountMarketings() {
        return preDiscountMarketings;
    }

    /**
     * 折扣list
     * 
     * @param preDiscountMarketings
     */
    public void setPreDiscountMarketings(List<PreDiscountMarketing> preDiscountMarketings) {
        this.preDiscountMarketings = preDiscountMarketings;
    }

    public Coupon getCoupon() {
        return coupon;
    }

    public void setCoupon(Coupon coupon) {
        this.coupon = coupon;
    }

}
