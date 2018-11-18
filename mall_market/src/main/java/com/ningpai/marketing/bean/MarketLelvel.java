package com.ningpai.marketing.bean;

/**
 * 促销活动对应的会员等级实体
 * 
 * @author qiyuanyuan
 *
 */

public class MarketLelvel {
    // 促销会员等级Id
    private Long promotionLelvelId;

    // 会员等级ID
    private Long lelvelId;

    // 促销ID
    private Long marketingId;

    // 删除标记:0:正常 1:删除
    private String lelvelDelFlag;

    // 等级名称
    private String levelName;

    public Long getPromotionLelvelId() {
        return promotionLelvelId;
    }

    public void setPromotionLelvelId(Long promotionLelvelId) {
        this.promotionLelvelId = promotionLelvelId;
    }

    public Long getLelvelId() {
        return lelvelId;
    }

    public void setLelvelId(Long lelvelId) {
        this.lelvelId = lelvelId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public String getLelvelDelFlag() {
        return lelvelDelFlag;
    }

    public void setLelvelDelFlag(String lelvelDelFlag) {
        this.lelvelDelFlag = lelvelDelFlag;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}