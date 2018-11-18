package com.ningpai.manager.bean;

import java.math.BigDecimal;

/**
 * 首页统计数量实体类
 * 
 * @author ggn
 *
 */
public class IndexCountBean {
    // 订单---------------------
    // 昨日下单数
    private int subOrderCount;
    // 昨日付款订单数
    private int payOrderCount;
    // 昨日发货订单数量
    private int sendOrderCount;
    // 昨日交易额
    private BigDecimal yesterdayTurnover;
    // 总交易额
    private BigDecimal sumTunover;

    // 商品--------------------
    // 库存预警数量
    private int warningCount;
    // 缺货
    private int outOfStock;
    // 下架数量
    private int shelvesCount;
    // 总数量
    private int sumProduct;

    // 会员
    // 新增会员（昨日）
    private int newCustomerCount;
    // 会员总数
    private int sumCustomerCount;
    // 新咨询
    private int newConsulting;
    // 新评论
    private int newComments;

    public int getSubOrderCount() {
        return subOrderCount;
    }

    public void setSubOrderCount(int subOrderCount) {
        this.subOrderCount = subOrderCount;
    }

    public int getPayOrderCount() {
        return payOrderCount;
    }

    public void setPayOrderCount(int payOrderCount) {
        this.payOrderCount = payOrderCount;
    }

    public int getSendOrderCount() {
        return sendOrderCount;
    }

    public void setSendOrderCount(int sendOrderCount) {
        this.sendOrderCount = sendOrderCount;
    }

    public BigDecimal getYesterdayTurnover() {
        return yesterdayTurnover;
    }

    public void setYesterdayTurnover(BigDecimal yesterdayTurnover) {
        this.yesterdayTurnover = yesterdayTurnover;
    }

    public BigDecimal getSumTunover() {
        return sumTunover;
    }

    public void setSumTunover(BigDecimal sumTunover) {
        this.sumTunover = sumTunover;
    }

    public int getWarningCount() {
        return warningCount;
    }

    public void setWarningCount(int warningCount) {
        this.warningCount = warningCount;
    }

    public int getOutOfStock() {
        return outOfStock;
    }

    public void setOutOfStock(int outOfStock) {
        this.outOfStock = outOfStock;
    }

    public int getShelvesCount() {
        return shelvesCount;
    }

    public void setShelvesCount(int shelvesCount) {
        this.shelvesCount = shelvesCount;
    }

    public int getSumProduct() {
        return sumProduct;
    }

    public void setSumProduct(int sumProduct) {
        this.sumProduct = sumProduct;
    }

    public int getNewCustomerCount() {
        return newCustomerCount;
    }

    public void setNewCustomerCount(int newCustomerCount) {
        this.newCustomerCount = newCustomerCount;
    }

    public int getSumCustomerCount() {
        return sumCustomerCount;
    }

    public void setSumCustomerCount(int sumCustomerCount) {
        this.sumCustomerCount = sumCustomerCount;
    }

    public int getNewConsulting() {
        return newConsulting;
    }

    public void setNewConsulting(int newConsulting) {
        this.newConsulting = newConsulting;
    }

    public int getNewComments() {
        return newComments;
    }

    public void setNewComments(int newComments) {
        this.newComments = newComments;
    }

}
