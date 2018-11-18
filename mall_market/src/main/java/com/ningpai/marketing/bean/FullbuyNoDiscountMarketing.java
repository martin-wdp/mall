package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**
 * 满x件打y折
 * 
 * @author zhouxu
 *
 */
public class FullbuyNoDiscountMarketing {
    /*
     * 满x件打y折id
     */
    private Long fullbuyNoDiscountMarketingId;
    /*
     * 促销Id
     */
    private Long marketingId;
    /*
     * 满多少件
     */
    private Long packagesNo;
    /*
     * 打多少折
     */
    private BigDecimal packagebuyDiscount;
    /*
     * 是否删除
     */
    private String delFlag;
    /*
     * 计算“满购件数打折”的件数
     */
    private Long countCondition;
    /**
     * isMeetCondition
     */
    private String isMeetCondition;

    public Long getFullbuyNoDiscountMarketingId() {
        return fullbuyNoDiscountMarketingId;
    }

    public void setFullbuyNoDiscountMarketingId(
            Long fullbuyNoDiscountMarketingId) {
        this.fullbuyNoDiscountMarketingId = fullbuyNoDiscountMarketingId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getPackagesNo() {
        return packagesNo;
    }

    public void setPackagesNo(Long packagesNo) {
        this.packagesNo = packagesNo;
    }

    public BigDecimal getPackagebuyDiscount() {
        return packagebuyDiscount;
    }

    public void setPackagebuyDiscount(BigDecimal packagebuyDiscount) {
        this.packagebuyDiscount = packagebuyDiscount;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getCountCondition() {
        return countCondition;
    }

    public void setCountCondition(Long countCondition) {
        this.countCondition = countCondition;
    }

    public String getIsMeetCondition() {
        return isMeetCondition;
    }

    public void setIsMeetCondition(String isMeetCondition) {
        this.isMeetCondition = isMeetCondition;
    }

}
