package com.ningpai.topic.bean;

import java.util.Date;

/**
 * 话题推荐
 * 
 * @author ggn
 *
 */
public class TopicRecommend {
    // 主键
    private Long recommendId;

    // 被推荐的对象ID
    private Long recommendShipId;

    // 推荐用户Id
    private Long customerId;

    // 被推荐的对象类型
    private String recommendType;

    // 用户ip
    private String customerIp;

    // 推荐时间
    private Date recommendCreateTime;

    // 修改时间
    private Date recommendModifyTime;

    // 删除标记(0:正常 1：删除)
    private String delFlag;

    public Long getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Long recommendId) {
        this.recommendId = recommendId;
    }

    public Long getRecommendShipId() {
        return recommendShipId;
    }

    public void setRecommendShipId(Long recommendShipId) {
        this.recommendShipId = recommendShipId;
    }

    public String getRecommendType() {
        return recommendType;
    }

    public void setRecommendType(String recommendType) {
        this.recommendType = recommendType;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerIp() {
        return customerIp;
    }

    public void setCustomerIp(String customerIp) {
        this.customerIp = customerIp;
    }

    /**
     * 获取创建时间
     * */
    public Date getRecommendCreateTime() {
        return (Date) recommendCreateTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setRecommendCreateTime(Date recommendCreateTime) {
        this.recommendCreateTime = recommendCreateTime == null ? null : (Date) recommendCreateTime.clone();
    }
    /**
     * 获取修改时间
     * */
    public Date getRecommendModifyTime() {
        return (Date) recommendModifyTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setRecommendModifyTime(Date recommendModifyTime) {
        this.recommendModifyTime = recommendModifyTime == null ? null : (Date) recommendModifyTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
