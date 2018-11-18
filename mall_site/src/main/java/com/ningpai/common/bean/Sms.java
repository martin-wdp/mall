/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.common.bean;

/**
 * 发送短信
 * 
 * @author Songhl
 */
public class Sms {

    /**
     * 登录名
     */
    private String loginName;
    /**
     * 密码
     */
    private String password;
    /**
     * 短信类型
     */
    private String smsKind;
    /**
     * sendSim
     */
    private String sendSim;
    /**
     * expSmsId
     */
    private String expSmsId;
    /**
     * 信息文本
     */
    private String msgContext;
    /**
     * 网站地址
     */
    private String httpUrl;

    public Sms() {

    }

    /**
     * 初始化
     * 
     * @param loginName
     * @param password
     * @param smsKind
     * @param sendSim
     * @param expSmsId
     * @param msgContext
     */
    public Sms(String loginName, String password, String httpUrl, String smsKind, String expSmsId, String sendSim, String msgContext) {
        this.loginName = loginName;
        this.password = password;
        this.httpUrl = httpUrl;
        this.smsKind = smsKind;
        this.sendSim = sendSim;
        this.expSmsId = expSmsId;
        this.msgContext = msgContext;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmsKind() {
        return smsKind;
    }

    public void setSmsKind(String smsKind) {
        this.smsKind = smsKind;
    }

    public String getSendSim() {
        return sendSim;
    }

    public void setSendSim(String sendSim) {
        this.sendSim = sendSim;
    }

    public String getExpSmsId() {
        return expSmsId;
    }

    public void setExpSmsId(String expSmsId) {
        this.expSmsId = expSmsId;
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext;
    }

    public String getHttpUrl() {
        return httpUrl;
    }

    public void setHttpUrl(String httpUrl) {
        this.httpUrl = httpUrl;
    }

}
