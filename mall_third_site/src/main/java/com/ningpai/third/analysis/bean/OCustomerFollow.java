package com.ningpai.third.analysis.bean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 会员关注
 * </p>
 *
 * @author zhanghl
 * @versuin 2.0
 * @since 2015.07.30
 */
public class OCustomerFollow {
    /**
     * 关注ID
     */
    private Long followId;

    /**
     * 会员ID
     */
    private Long customerId;
    /**
     * 商品ID
     */

    private Long goodsId;

    /**
     * 关注时的价格
     */
    private BigDecimal followPrice;

    /**
     * 关注标签
     */
    private String followTag;

    /**
     * 创建日期
     */
    private Date createTime;

    /**
     * 修改日期
     */
    private Date modifiedTime;

    /**
     * 删除日期
     */

    private Date delTime;

    /**
     * 是否删除
     */
    private String delFlag;

    /**
     * 关注的商品名称
     */

    private String goodsInfoName;

    /**
     * 保存时间集合
     */
    private Map<String, Long> timeMap;

    /**
     * 商品下载时间
     */
    private String goodsDownTime;

    /**
     * 刷量
     */
    private Long countSum;

    public Long getCountSum() {
        return countSum;
    }

    public void setCountSum(Long countSum) {
        this.countSum = countSum;
    }

    public String getGoodsDownTime() {
        return goodsDownTime;
    }

    public void setGoodsDownTime(String goodsDownTime) {
        this.goodsDownTime = goodsDownTime;
    }

    public String getGoodsInfoName() {
        return goodsInfoName;
    }

    public void setGoodsInfoName(String goodsInfoName) {
        this.goodsInfoName = goodsInfoName;
    }

    public Map<String, Long> getTimeMap() {
        return timeMap;
    }

    public void setTimeMap(Map<String, Long> timeMap) {
        this.timeMap = timeMap;
    }

    public Long getFollowId() {
        return followId;
    }

    public void setFollowId(Long followId) {
        this.followId = followId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public BigDecimal getFollowPrice() {
        return followPrice;
    }

    public void setFollowPrice(BigDecimal followPrice) {
        this.followPrice = followPrice;
    }

    public String getFollowTag() {
        return followTag;
    }

    public void setFollowTag(String followTag) {
        this.followTag = followTag;
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
    /**
     * 获取删除时间
     * */
    public Date getDelTime() {
        return (Date) delTime.clone();
    }
    /**
     * 设置删除时间
     * */
    public void setDelTime(Date delTime) {
        this.delTime = delTime == null ? null : (Date) delTime.clone();
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
