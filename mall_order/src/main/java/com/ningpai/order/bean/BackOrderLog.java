package com.ningpai.order.bean;

import java.util.Date;

/**
 * Created by Zhoux on 2015/6/10.
 */
public class BackOrderLog {
    /**
     * 退单日志id
     */
    private Long logId;
    /**
     * 退单日志操作者
     */
    private String backLogPerson;
    /**
     * 退单日志操作时间
     */
    private Date backLogTime;
    /**
     * 退单操作日志类型(1 顾客 申请退货 2 admin 审核通过 3admin 审核不通过 4 客户 填写快递单号 5 admin 确认收货 6
     * admin 收货失败 7 admin 退款 8 admin 同意退款 9 admin 拒绝退单)
     */
    private String backLogStatus;
    /**
     * 退单id
     */
    private Long backOrderId;
    /**
     * 退单备注
     */
    private String backRemark;
    /**
     * 给客户留言
     */
    private String customerRemark;

    public Long getLogId() {
        return logId;
    }

    public void setLogId(Long logId) {
        this.logId = logId;
    }

    public String getBackLogPerson() {
        return backLogPerson;
    }

    public void setBackLogPerson(String backLogPerson) {
        this.backLogPerson = backLogPerson;
    }

    public Date getBackLogTime() {
        return backLogTime;
    }

    public void setBackLogTime(Date backLogTime) {
        this.backLogTime = backLogTime;
    }

    public String getBackLogStatus() {
        return backLogStatus;
    }

    public void setBackLogStatus(String backLogStatus) {
        this.backLogStatus = backLogStatus;
    }

    public Long getBackOrderId() {
        return backOrderId;
    }

    public void setBackOrderId(Long backOrderId) {
        this.backOrderId = backOrderId;
    }

    public String getBackRemark() {
        return backRemark;
    }

    public void setBackRemark(String backRemark) {
        this.backRemark = backRemark;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }
}
