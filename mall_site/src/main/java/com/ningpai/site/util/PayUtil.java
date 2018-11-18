package com.ningpai.site.util;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;

/**
 * Created by NingPai-Pro on 2015/6/2.
 */
public class PayUtil {
    // 订单编号
    private String orderNo;
    // 购买方编号
    private String buyerUserId;
    // 收款方编号
    private String sellerUserId;
    // 收款方应得金额
    private BigDecimal settleAmount;
    // 商品名称
    private String productName;
    // 上级编号
    private String adminUserId;
    // 支付成功回调地址
    private String callBackUrl;
    // ip地址
    private String fromIp;
    // 订单渠道 (先传YX,到时会给你们一个专用渠道为:SYY)
    private String tradeChannel;
    // 商品详情链接
    private String goodsUrl;
    // 结算类型 (取值为:1实时结算 0后结算)
    private String settleType;
    // 支付金额
    private BigDecimal amount;
    // 系统编号
    private String sysId;
    // css样式URL(即 使用哪个cssStyle样式)
    private String cssStyle;
    // 回调通知地址
    private String notifyUrl;

    public BigDecimal getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(BigDecimal settleAmount) {
        this.settleAmount = settleAmount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getSellerUserId() {
        return sellerUserId;
    }

    public void setSellerUserId(String sellerUserId) {
        this.sellerUserId = sellerUserId;
    }

    public String getProductName() throws UnsupportedEncodingException {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public String getFromIp() {
        return fromIp;
    }

    public void setFromIp(String fromIp) {
        this.fromIp = fromIp;
    }

    public String getTradeChannel() {
        return tradeChannel;
    }

    public void setTradeChannel(String tradeChannel) {
        this.tradeChannel = tradeChannel;
    }

    public String getGoodsUrl() {
        return goodsUrl;
    }

    public void setGoodsUrl(String goodsUrl) {
        this.goodsUrl = goodsUrl;
    }

    public String getSettleType() {
        return settleType;
    }

    public void setSettleType(String settleType) {
        this.settleType = settleType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSysId() {
        return sysId;
    }

    public void setSysId(String sysId) {
        this.sysId = sysId;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }
}
