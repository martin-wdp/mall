package com.ningpai.marketing.bean;

import java.math.BigDecimal;

/**满件促销
 * @author ggn
 *
 */
public class FullbuyNoCountMarketing {
    /**
     * 满件多少钱id
     */
    private Long fullbuyNoCountMarketingId;
    /**
     * 促销id
     */
    private Long marketingId;
    /**
     * 满多少件
     */
    private Long countNo;
    /**
     * 多少钱
     */
    private BigDecimal countMoney;
    /**
     * 删除标记
     */
    private String delFlag;
    /**
     * 计算“满购件数多少钱”的件数
     */
    private Long countCondition;
    /**
     * isMeetCondition
     */
    private String isMeetCondition;

    public Long getFullbuyNoCountMarketingId() {
        return fullbuyNoCountMarketingId;
    }

    public void setFullbuyNoCountMarketingId(Long fullbuyNoCountMarketingId) {
        this.fullbuyNoCountMarketingId = fullbuyNoCountMarketingId;
    }

    public Long getMarketingId() {
        return marketingId;
    }

    public void setMarketingId(Long marketingId) {
        this.marketingId = marketingId;
    }

    public Long getCountNo() {
        return countNo;
    }

    public void setCountNo(Long countNo) {
        this.countNo = countNo;
    }

    public BigDecimal getCountMoney() {
        return countMoney;
    }

    public void setCountMoney(BigDecimal countMoney) {
        this.countMoney = countMoney;
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
