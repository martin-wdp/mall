/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.report.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description: np_report表的实体bean
 * @author Ningpai-HEHU
 * @date 2014-12-17 14:06:51
 * @version V1.0
 */
public class Report implements Serializable {

    /**
     * 序列化
     */
    private static final long serialVersionUID = 1L;

    /**
     * 报表id
     */
    private Long reportId;

    /**
     * 商家id
     */
    private Long storeId;

    /**
     * 类目扣率
     */
    private BigDecimal cateRate;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 报表生成时间
     */
    private Date createTime;

    /**
     * 当前周期内，产生的订单总支付金额
     */
    private BigDecimal totalOrderMoney;

    /**
     * 类目
     */
    private Long cateId;

    /**
     * 结算状态：0未结算，1已结算
     */
    private String settleStatus;

    /**
     * 删除状态0未删除，1已删除
     */
    private String delFlag;

    /**
     * 结算人ID
     */
    private Long settleUserId;

    /**
     * 删除人ID
     */
    private Long delUserId;

    /**
     * 应扣金额
     */
    private BigDecimal shouldBuckle;

    /**
     * 开始时间，查询使用
     */
    private String startDate;
    /**
     * 结束时间，查询使用
     */
    private String endDate;

    /** 总订单促销优惠 */
    private BigDecimal totalOrderPrePrice;

    /** 总商品促销优惠 */
    private BigDecimal totalGoodsPrePrice;

    /** 商家名称 */
    private String storeName;
    /** 分类名称 */
    private String cateName;
    /** 公司名称 */
    private String companyName;
    /** 时间类型，查询使用，0按开始时间/1结束时间 */
    private String timeType;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public BigDecimal getCateRate() {
        return cateRate;
    }

    public void setCateRate(BigDecimal cateRate) {
        this.cateRate = cateRate;
    }
    /**
     * 获取开始时间
     * */
    public Date getStartTime() {
        return (Date) startTime.clone();
    }
    /**
     * 设置开始时间
     * */
    public void setStartTime(Date startTime) {
        this.startTime = startTime == null ? null : (Date) startTime.clone();
    }
    /**
     * 获取结束时间
     * */
    public Date getEndTime() {
        return (Date) endTime.clone();
    }
    /**
     * 设置结束时间
     * */
    public void setEndTime(Date endTime) {
        this.endTime = endTime == null ? null : (Date) endTime.clone();
    }
    /**
     *
     * 获取创建时间
     * */
    public Date getCreateTime() {
        return (Date) createTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public BigDecimal getTotalOrderMoney() {
        return totalOrderMoney;
    }

    public void setTotalOrderMoney(BigDecimal totalOrderMoney) {
        this.totalOrderMoney = totalOrderMoney;
    }

    public Long getCateId() {
        return cateId;
    }

    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    public String getSettleStatus() {
        return settleStatus;
    }

    public void setSettleStatus(String settleStatus) {
        this.settleStatus = settleStatus;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getSettleUserId() {
        return settleUserId;
    }

    public void setSettleUserId(Long settleUserId) {
        this.settleUserId = settleUserId;
    }

    public Long getDelUserId() {
        return delUserId;
    }

    public void setDelUserId(Long delUserId) {
        this.delUserId = delUserId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getTotalOrderPrePrice() {
        return totalOrderPrePrice;
    }

    public void setTotalOrderPrePrice(BigDecimal totalOrderPrePrice) {
        this.totalOrderPrePrice = totalOrderPrePrice;
    }

    public BigDecimal getTotalGoodsPrePrice() {
        return totalGoodsPrePrice;
    }

    public void setTotalGoodsPrePrice(BigDecimal totalGoodsPrePrice) {
        this.totalGoodsPrePrice = totalGoodsPrePrice;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }
    /**
     * 获取ShouldBuckle
     * */
    public BigDecimal getShouldBuckle() {
        return shouldBuckle == null ? null : shouldBuckle.setScale(3, BigDecimal.ROUND_HALF_UP);
    }

    public void setShouldBuckle(BigDecimal shouldBuckle) {
        this.shouldBuckle = shouldBuckle;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }
}
