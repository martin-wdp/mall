package com.ningpai.site.giftshop.bean;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分兑换赠品订单实体
 * 
 * @author qiyuanyuan
 *
 */
public class GiftOrder {

    // 订单Id
    private Long giftOrderId;

    // 订单编号
    private String giftOrderCode;

    // 订单状态(0未发货 1已发货 2已经收货)
    private String giftOrderStatus;

    // 会员ID
    private Long customerId;

    // 支付时间
    private Date payTime;

    // 发货时间
    private Date sendExpressTime;

    // 收货时间
    private Date getGoodsTime;

    // 收货地址ID
    private Long shoppingAddrId;

    // 收货省
    private Long shoppingProvince;

    // 收货市
    private Long shoppingCity;

    // 收货区县
    private Long shoppingCounty;

    // 收货详细地址
    private String shoppingAddress;

    // 收货人
    private String shoppingPerson;

    // 收货电话
    private String shoppingPhone;

    // 收货手机
    private String shoppingMobile;

    // 邮编
    private String shoppingPostcode;

    // 删除标记
    private String delFlag;

    // 应扣积分
    private Long orderIntegral;

    // 运费
    private BigDecimal expressPrice;

    // 临时字段1（赠品Id）
    private String temp1;

    // 临时字段2(物流公司Id)
    private String temp2;

    // 临时字段3(物流公司编号)
    private String temp3;

    // 创建时间
    private Date createTime;

    // 赠品
    private Gift gift;

    // 物流公司名称
    private String temp4;

    // 兑换数量
    private int orderNum;

    public Long getGiftOrderId() {
        return giftOrderId;
    }

    public void setGiftOrderId(Long giftOrderId) {
        this.giftOrderId = giftOrderId;
    }

    public String getGiftOrderCode() {
        return giftOrderCode;
    }

    public void setGiftOrderCode(String giftOrderCode) {
        this.giftOrderCode = giftOrderCode;
    }

    public String getGiftOrderStatus() {
        return giftOrderStatus;
    }

    public void setGiftOrderStatus(String giftOrderStatus) {
        this.giftOrderStatus = giftOrderStatus;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    /**
     * 得到支付时间
     * @return
     */
    public Date getPayTime() {
        return payTime== null?null:(Date) payTime.clone();
    }

    /**
     * 设置支付时间
     * @param payTime
     */
    public void setPayTime(Date payTime) {
        this.payTime = payTime == null ? null : (Date) payTime.clone();
    }


    /**
     * 得到快递时间
     * @return
     */
    public Date getSendExpressTime() {
        return  sendExpressTime== null?null:(Date) sendExpressTime.clone();
    }


    /**
     * 设置快递时间
     * @param sendExpressTime
     */
    public void setSendExpressTime(Date sendExpressTime) {
        this.sendExpressTime = sendExpressTime == null ? null : (Date) sendExpressTime.clone();
    }


    /**
     * 得到发货时间
     * @return
     */
    public Date getGetGoodsTime() {
        return getGoodsTime == null?null:(Date)getGoodsTime.clone();
    }


    /**
     * 设置发货时间
     * @param getGoodsTime
     */
    public void setGetGoodsTime(Date getGoodsTime) {
        this.getGoodsTime = getGoodsTime == null ? null : (Date) getGoodsTime.clone();
    }

    public Long getShoppingAddrId() {
        return shoppingAddrId;
    }

    public void setShoppingAddrId(Long shoppingAddrId) {
        this.shoppingAddrId = shoppingAddrId;
    }

    public Long getShoppingProvince() {
        return shoppingProvince;
    }

    public void setShoppingProvince(Long shoppingProvince) {
        this.shoppingProvince = shoppingProvince;
    }

    public Long getShoppingCity() {
        return shoppingCity;
    }

    public void setShoppingCity(Long shoppingCity) {
        this.shoppingCity = shoppingCity;
    }

    public Long getShoppingCounty() {
        return shoppingCounty;
    }

    public void setShoppingCounty(Long shoppingCounty) {
        this.shoppingCounty = shoppingCounty;
    }

    public String getShoppingAddress() {
        return shoppingAddress;
    }

    public void setShoppingAddress(String shoppingAddress) {
        this.shoppingAddress = shoppingAddress;
    }

    public String getShoppingPerson() {
        return shoppingPerson;
    }

    public void setShoppingPerson(String shoppingPerson) {
        this.shoppingPerson = shoppingPerson;
    }

    public String getShoppingPhone() {
        return shoppingPhone;
    }

    public void setShoppingPhone(String shoppingPhone) {
        this.shoppingPhone = shoppingPhone;
    }

    public String getShoppingMobile() {
        return shoppingMobile;
    }

    public void setShoppingMobile(String shoppingMobile) {
        this.shoppingMobile = shoppingMobile;
    }

    public String getShoppingPostcode() {
        return shoppingPostcode;
    }

    public void setShoppingPostcode(String shoppingPostcode) {
        this.shoppingPostcode = shoppingPostcode;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getOrderIntegral() {
        return orderIntegral;
    }

    public void setOrderIntegral(Long orderIntegral) {
        this.orderIntegral = orderIntegral;
    }

    public BigDecimal getExpressPrice() {
        return expressPrice;
    }

    public void setExpressPrice(BigDecimal expressPrice) {
        this.expressPrice = expressPrice;
    }

    public String getTemp1() {
        return temp1;
    }

    public void setTemp1(String temp1) {
        this.temp1 = temp1;
    }

    public String getTemp2() {
        return temp2;
    }

    public void setTemp2(String temp2) {
        this.temp2 = temp2;
    }

    public String getTemp3() {
        return temp3;
    }

    public void setTemp3(String temp3) {
        this.temp3 = temp3;
    }
    /**
     * 获取创建时间
     * */
    public Date getCreateTime() {
        return  this.createTime == null ? null : (Date) createTime.clone();
    }
    /**
     * 设置创建时间
     * */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime == null ? null : (Date) createTime.clone();
    }

    public Gift getGift() {
        return gift;
    }

    public void setGift(Gift gift) {
        this.gift = gift;
    }

    public String getTemp4() {
        return temp4;
    }

    public void setTemp4(String temp4) {
        this.temp4 = temp4;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

}
