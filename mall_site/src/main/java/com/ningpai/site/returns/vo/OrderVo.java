package com.ningpai.site.returns.vo;

import java.util.Date;

/***
 * 订单明细
 * 
 * @author zhanghailong
 *
 */
public class OrderVo {
    /**
     * 明细ID
     */
    private Long orderId;
    /**
     * 订单编号
     */
    private Long orderNo;
    /**
     * 会员ID
     */
    private Long customerId;
    /**
     * 商家ID
     */
    private Long businessId;
    /**
     * 订单总金额
     */
    private Double orderPrice;
    /**
     * 购买时间
     */
    private Date payTime;
    /**
     * 退货人
     */
    private String returnUser;
    /**
     * 联系电话
     */
    private String returnPhone;
    /**
     * 审核状态
     */
    private Long checkstatus;
    /**
     * 读取状态
     */
    private Long weidustatus;
    /**
     * 创建时间
     */
    private Date createtime;
    /**
     * 退货原因
     */
    private String returnYuanyin;
    /**
     * 是否允许退货
     */
    private Long whethereturn;

    /**
     * 无参构造方法
     */
    public OrderVo() {
        super();

    }

    /**
     * 构造方法
     * 
     * @param orderId
     * @param orderNo
     * @param customerId
     * @param businessId
     * @param orderPrice
     * @param payTime
     * @param returnUser
     * @param returnPhone
     * @param checkstatus
     * @param weidustatus
     * @param createtime
     * @param returnYuanyin
     * @param whethereturn
     */
    public OrderVo(Long orderId, Long orderNo, Long customerId, Long businessId, Double orderPrice, Date payTime, String returnUser, String returnPhone, Long checkstatus,
            Long weidustatus, Date createtime, String returnYuanyin, Long whethereturn) {
        super();
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.customerId = customerId;
        this.businessId = businessId;
        this.orderPrice = orderPrice;
        this.payTime = payTime;
        this.returnUser = returnUser;
        this.returnPhone = returnPhone;
        this.checkstatus = checkstatus;
        this.weidustatus = weidustatus;
        this.createtime = createtime;
        this.returnYuanyin = returnYuanyin;
        this.whethereturn = whethereturn;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Long orderNo) {
        this.orderNo = orderNo;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }

    public Date getPayTime() {
        return (Date) payTime.clone();
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime==null?null: (Date) payTime.clone();
    }

    public String getReturnUser() {
        return returnUser;
    }

    public void setReturnUser(String returnUser) {
        this.returnUser = returnUser;
    }

    public String getReturnPhone() {
        return returnPhone;
    }

    public void setReturnPhone(String returnPhone) {
        this.returnPhone = returnPhone;
    }

    public Long getCheckstatus() {
        return checkstatus;
    }

    public void setCheckstatus(Long checkstatus) {
        this.checkstatus = checkstatus;
    }

    public Long getWeidustatus() {
        return weidustatus;
    }

    public void setWeidustatus(Long weidustatus) {
        this.weidustatus = weidustatus;
    }

    public Date getCreatetime() {
        return (Date) createtime.clone();
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime == null?null: (Date) createtime.clone();
    }

    public String getReturnYuanyin() {
        return returnYuanyin;
    }

    public void setReturnYuanyin(String returnYuanyin) {
        this.returnYuanyin = returnYuanyin;
    }

    public Long getWhethereturn() {
        return whethereturn;
    }

    public void setWhethereturn(Long whethereturn) {
        this.whethereturn = whethereturn;
    }

}
