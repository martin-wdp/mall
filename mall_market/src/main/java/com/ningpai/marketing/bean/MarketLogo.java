package com.ningpai.marketing.bean;

/**
 * 促销活动对应的促销Logo对象
 * 
 * @author qiyuanyuan
 *
 */
public class MarketLogo {
    /**
     * 促销活动logoId
     */
    private Long marketLogoId;

    /**
     * 促销ID
     */
    private Long marketingId;

    /**
     * 促销logoID
     */
    private Long promotionLogoId;

    /**
     * 删除标记：0：正常 1：删除
     */
    private String delFlag;

    /**
     * 促销logo名称
     */
    private String promotionLogoName;

    /**
     * 促销logo图片
     */
    private String promotionLogoUrl;

    public Long getMarketLogoId() {
        return marketLogoId;
    }

    public void setMarketLogoId(Long marketLogoId) {
        this.marketLogoId = marketLogoId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getPromotionLogoId() {
        return promotionLogoId;
    }

    public void setPromotionLogoId(Long promotionLogoId) {
        this.promotionLogoId = promotionLogoId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getPromotionLogoName() {
        return promotionLogoName;
    }

    public void setPromotionLogoName(String promotionLogoName) {
        this.promotionLogoName = promotionLogoName;
    }

    public String getPromotionLogoUrl() {
        return promotionLogoUrl;
    }

    public void setPromotionLogoUrl(String promotionLogoUrl) {
        this.promotionLogoUrl = promotionLogoUrl;
    }

}