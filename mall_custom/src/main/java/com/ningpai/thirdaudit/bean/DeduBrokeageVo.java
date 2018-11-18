/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.thirdaudit.bean;

/**
 * 商家平台扣点佣金Bean
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年7月25日 下午3:00:14
 * @version 0.0.1
 */
public class DeduBrokeageVo {
    // 编号
    private Long dbId;
    // 支付方式ID
    private Long payId;
    // 第三方编号
    private Long thirdId;
    // 平台扣点
    private String deduction;
    // 佣金
    private String brokerage;
    // 删除标记
    private String delFlag;

    // 支付名称
    private String payName;

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public Long getDbId() {
        return dbId;
    }

    public void setDbId(Long dbId) {
        this.dbId = dbId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public String getDeduction() {
        return deduction;
    }

    public void setDeduction(String deduction) {
        this.deduction = deduction;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
