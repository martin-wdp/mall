package com.qpmall.bean;

import java.util.Date;

/**
 * Created by pl on 2015/10/12.
 * Desc:
 */
public class QpCustomerAutoStyleSearchBean {
    private Long customerAutoStyleSearchId;

    private Long customerId;

    private String autoStyleId;

    private String customerAutoStyleSearchCreateName;

    private Date customerAutoStyleSearchCreateTime;

    private String customerAutoStyleSearchModifiedName;

    private Date customerAutoStyleSearchModifiedTime;

    private String customerAutoStyleSearchDelName;

    private Date customerAutoStyleSearchDelTime;

    private String customerAutoStyleSearchDelflag;

    public Long getCustomerAutoStyleSearchId() {
        return customerAutoStyleSearchId;
    }

    public void setCustomerAutoStyleSearchId(Long customerAutoStyleSearchId) {
        this.customerAutoStyleSearchId = customerAutoStyleSearchId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getAutoStyleId() {
        return autoStyleId;
    }

    public void setAutoStyleId(String autoStyleId) {
        this.autoStyleId = autoStyleId == null ? null : autoStyleId.trim();
    }

    public String getCustomerAutoStyleSearchCreateName() {
        return customerAutoStyleSearchCreateName;
    }

    public void setCustomerAutoStyleSearchCreateName(String customerAutoStyleSearchCreateName) {
        this.customerAutoStyleSearchCreateName = customerAutoStyleSearchCreateName == null ? null : customerAutoStyleSearchCreateName.trim();
    }

    public Date getCustomerAutoStyleSearchCreateTime() {
        return customerAutoStyleSearchCreateTime;
    }

    public void setCustomerAutoStyleSearchCreateTime(Date customerAutoStyleSearchCreateTime) {
        this.customerAutoStyleSearchCreateTime = customerAutoStyleSearchCreateTime;
    }

    public String getCustomerAutoStyleSearchModifiedName() {
        return customerAutoStyleSearchModifiedName;
    }

    public void setCustomerAutoStyleSearchModifiedName(String customerAutoStyleSearchModifiedName) {
        this.customerAutoStyleSearchModifiedName = customerAutoStyleSearchModifiedName == null ? null : customerAutoStyleSearchModifiedName.trim();
    }

    public Date getCustomerAutoStyleSearchModifiedTime() {
        return customerAutoStyleSearchModifiedTime;
    }

    public void setCustomerAutoStyleSearchModifiedTime(Date customerAutoStyleSearchModifiedTime) {
        this.customerAutoStyleSearchModifiedTime = customerAutoStyleSearchModifiedTime;
    }

    public String getCustomerAutoStyleSearchDelName() {
        return customerAutoStyleSearchDelName;
    }

    public void setCustomerAutoStyleSearchDelName(String customerAutoStyleSearchDelName) {
        this.customerAutoStyleSearchDelName = customerAutoStyleSearchDelName == null ? null : customerAutoStyleSearchDelName.trim();
    }

    public Date getCustomerAutoStyleSearchDelTime() {
        return customerAutoStyleSearchDelTime;
    }

    public void setCustomerAutoStyleSearchDelTime(Date customerAutoStyleSearchDelTime) {
        this.customerAutoStyleSearchDelTime = customerAutoStyleSearchDelTime;
    }

    public String getCustomerAutoStyleSearchDelflag() {
        return customerAutoStyleSearchDelflag;
    }

    public void setCustomerAutoStyleSearchDelflag(String customerAutoStyleSearchDelflag) {
        this.customerAutoStyleSearchDelflag = customerAutoStyleSearchDelflag == null ? null : customerAutoStyleSearchDelflag.trim();
    }
}
