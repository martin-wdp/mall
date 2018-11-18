package com.ningpai.site.order.bean;

import java.math.BigDecimal;
import java.util.Date;

/***
 * 会员积分兑换
 * 
 * @author zhanghailong
 *
 */
public class ExchangeCusmomerPoint {

    private Long exchangeId; // 积分兑换ID
    private String orderCode;// 订单单号
    private Long customerId; // 会员ID
    private Long exchangePoint; // 所兑换的积分数量
    private Date exchangeTime; // 兑换的时间
    private Long flag; // 是否删除
    private BigDecimal disparityPrice; // 兑换的金额
    private BigDecimal consumption; // 兑换的规则

    public BigDecimal getDisparityPrice() {
        return disparityPrice;
    }

    public void setDisparityPrice(BigDecimal disparityPrice) {
        this.disparityPrice = disparityPrice;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public Long getExchangeId() {
        return exchangeId;
    }

    public void setExchangeId(Long exchangeId) {
        this.exchangeId = exchangeId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getExchangePoint() {
        return exchangePoint;
    }

    public void setExchangePoint(Long exchangePoint) {
        this.exchangePoint = exchangePoint;
    }


    /**
     * 得到时间
     * @return
     */
    public Date getExchangeTime() {
        return (Date) exchangeTime.clone();
    }


    /**
     * 设置时间
     * @param exchangeTime
     */
    public void setExchangeTime(Date exchangeTime) {
        this.exchangeTime = exchangeTime == null ? null : (Date) exchangeTime.clone();
    }

    public Long getFlag() {
        return flag;
    }

    public void setFlag(Long flag) {
        this.flag = flag;
    }

}
