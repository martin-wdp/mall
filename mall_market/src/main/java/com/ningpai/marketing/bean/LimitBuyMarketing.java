package com.ningpai.marketing.bean;

/**
 * 限购
 * 
 * @author ggn
 * 
 */
public class LimitBuyMarketing {
    /*
     * 限购ID
     */
    private Long limitBuyId;
    /*
     * 促销ID
     */
    private Long marketingId;
    /*
     * 限购数量
     */
    private Long limitCount;
    /*
     * 是否删除
     */
    private String delFlag;

    public Long getLimitBuyId() {
        return limitBuyId;
    }

    public void setLimitBuyId(Long limitBuyId) {
        this.limitBuyId = limitBuyId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getLimitCount() {
        return limitCount;
    }

    public void setLimitCount(Long limitCount) {
        this.limitCount = limitCount;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
