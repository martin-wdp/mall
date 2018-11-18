/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.info.bean;
/**
 * 邮件、短信bean
 * @author huangyi
 *
 */
public class MessageBean {
    /**id编号*/
    private int informId;
    /**信件名称*/
    private String informSubject;
    /**发送方式：1.邮件 2.短信*/
    private int informType;
    /**模板模式1.注册 2.找回密码 3.下单 4.发货 5.营销*/
    private int informStatus;
    /**发送内容*/
    private String informText;
    /**修改时间*/
    private String informUpdateTime;
    public int getInformId() {
        return informId;
    }
    public void setInformId(int informId) {
        this.informId = informId;
    }
    public String getInformSubject() {
        return informSubject;
    }
    public void setInformSubject(String informSubject) {
        this.informSubject = informSubject;
    }
    public int getInformType() {
        return informType;
    }
    public void setInform_type(int informType) {
        this.informType = informType;
    }
    public int getInformStatus() {
        return informStatus;
    }
    public void setInformStatus(int informStatus) {
        this.informStatus = informStatus;
    }
    public String getInformText() {
        return informText;
    }
    public void setInformText(String informText) {
        this.informText = informText;
    }
    public String getInformUpdateTime() {
        return informUpdateTime;
    }
    public void setInformUpdateTime(String informUpdateTime) {
        this.informUpdateTime = informUpdateTime;
    }
    
    
}
