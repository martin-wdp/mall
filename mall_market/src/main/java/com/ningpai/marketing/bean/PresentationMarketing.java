package com.ningpai.marketing.bean;

/**
 * 买赠信息 2014-03-25
 * 
 * @author ggn
 * 
 */
public class PresentationMarketing {
    /**
     * 促销类型2 为买赠活动 买赠ID
     */
    private Long presentationId;
    /**
     * 促销ID
     */
    private Long marketingId;
    /**
     * 赠品ID
     */
    private Long productId;
    /**
     * 是否删除
     */
    private String delFlag;

    public Long getPresentationId() {
        return presentationId;
    }

    public void setPresentationId(Long presentationId) {
        this.presentationId = presentationId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
