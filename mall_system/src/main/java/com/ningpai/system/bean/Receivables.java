package com.ningpai.system.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 收款单实体bean
 * Created by houyichang on 2015/8/17.
 */
public class Receivables {
    /*
    *收款单id
     */
    private Long cashRegisterId;
    /*
    *收款单IP
     */
    private String payIp;
    /*
    *收款单编号
     */
    private String cashRegisterCode;
    /*
    *支付时间
     */
    private Date payTime;
    /*
    *操作人员
     */
    private String customerId;
    /*
    *支付方式
     */
    private String payMode;
    /*
    *支付金额
     */
    private BigDecimal payMoney;
    /*
    *支付类型
     */
    private String payType;
    /*
    *支付状态 ‘0’ 支付成功 ‘1’支付失败
     */
    private String payStatus;
    /*
    *订单编号
     */
    private String orderCode;
    /*
    *收款账号
     */
    private String payAccount;

    /*
    * 付款时间
    */
    private Date receivablesTime;

    public Long getCashRegisterId() {
        return cashRegisterId;
    }

    public void setCashRegisterId(Long cashRegisterId) {
        this.cashRegisterId = cashRegisterId;
    }

    public String getPayIp() {
        return payIp;
    }

    public void setPayIp(String payIp) {
        this.payIp = payIp;
    }

    public String getCashRegisterCode() {
        return cashRegisterCode;
    }

    public void setCashRegisterCode(String cashRegisterCode) {
        this.cashRegisterCode = cashRegisterCode;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPayMode() {
        return payMode;
    }

    public void setPayMode(String payMode) {
        this.payMode = payMode;
    }

    public BigDecimal getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(BigDecimal payMoney) {
        this.payMoney = payMoney;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }

    public Date getReceivablesTime() {
        return receivablesTime;
    }

    public void setReceivablesTime(Date receivablesTime) {
        this.receivablesTime = receivablesTime;
    }
}
