package com.ningpai.goods.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商起批设置类
 * 
 * @author NINGPAI-qiaoy
 * @since 2015年3月24日 180:04
 * @version 1.0
 */
public class GoodsBulk {
    /*
     * 起批设置ID
     */
    private Long bulkId;

    /*
     * 货品ID
     */
    private Long goodsInfoId;

    /*
     * 供应商商家ID
     */
    private Long thirdId;

    /*
     * 最小起批量
     */
    private Long bulkStart;

    /*
     * 最大起批量
     */
    private Long bulkEnd;

    /*
     * 起批价格
     */
    private BigDecimal bulkPrice;

    /*
     * 是否删除 0：未删除 1：已删除
     */
    private String delFlag;

    /*
     * 创建时间
     */
    private Date createTime;

    /*
     * 修改时间
     */
    private Date modifiedTime;
    /**
     * 无参构造
     * */
    public GoodsBulk() {
    }

    /**
     * 构造函数
     * */
    public GoodsBulk(Long bulkId, Long goodsInfoId, Long thirdId, Long bulkStart, Long bulkEnd, BigDecimal bulkPrice, String delFlag, Date createTime, Date modifiedTime) {
        this.bulkId = bulkId;
        this.goodsInfoId = goodsInfoId;
        this.thirdId = thirdId;
        this.bulkStart = bulkStart;
        this.bulkEnd = bulkEnd;
        this.bulkPrice = bulkPrice;
        this.delFlag = delFlag;
        this.createTime = createTime;
        this.modifiedTime = modifiedTime;
    }

    public Long getBulkId() {
        return bulkId;
    }

    public void setBulkId(Long bulkId) {
        this.bulkId = bulkId;
    }

    public Long getThirdId() {
        return thirdId;
    }

    public void setThirdId(Long thirdId) {
        this.thirdId = thirdId;
    }

    public Long getBulkStart() {
        return bulkStart;
    }

    public void setBulkStart(Long bulkStart) {
        this.bulkStart = bulkStart;
    }

    public Long getBulkEnd() {
        return bulkEnd;
    }

    public void setBulkEnd(Long bulkEnd) {
        this.bulkEnd = bulkEnd;
    }

    public BigDecimal getBulkPrice() {
        return bulkPrice;
    }

    public void setBulkPrice(BigDecimal bulkPrice) {
        this.bulkPrice = bulkPrice;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
    /**
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
    /**
     * 获取修改时间
     * */
    public Date getModifiedTime() {
        return (Date) modifiedTime.clone();
    }
    /**
     * 设置修改时间
     * */
    public void setModifiedTime(Date modifiedTime) {
        this.modifiedTime = modifiedTime == null ? null : (Date) modifiedTime.clone();
    }

    public Long getGoodsInfoId() {
        return goodsInfoId;
    }

    public void setGoodsInfoId(Long goodsInfoId) {
        this.goodsInfoId = goodsInfoId;
    }
}
