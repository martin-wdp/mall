/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import java.math.BigDecimal;

/**
 * 积分设置实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午3:26:39
 * @version 1.0
 */
public class PointSet {
    /* 
     * 积分设置ID
     */
    private Long psetId;
    /*
     *  注册积分
     */
    private Integer psetRegister;
    /* 
     * 每日登陆积分
     */
    private Integer psetLogin;
    /* 
     * 邮箱验证积分
     */
    private Integer psetEmail;
    /*
     *  手机验证积分
     */
    private Integer psetPhone;
    /* 
     * 发表评论积分
     */
    private Integer psetComment;
    /*
     *  推荐用户积分
     */
    private Integer psetUser;
    /*
     *  每日上线积分
     */
    private Integer psetOnline;
    /*
     * 发表话题积分
     */
    private Integer psetPubtopic;
    /*
     * 热门话题积分
     */
    private Integer psetHottopic;
    /*
     * 精选话题积分
     */
    private Integer psetEssencetopic;
    /*
     * 推荐到首页话题积分
     */
    private Integer psetIndextopic;
    /* 
     * 是否开启 1:开启;0:关闭
     */
    private String isOpen;
    /*
     *  积分兑换规则
     */
    private Integer exchange;
    /* 
     * 积分消费规则
     */
    private BigDecimal consumption;

    public Long getPsetId() {
        return psetId;
    }

    public void setPsetId(Long psetId) {
        this.psetId = psetId;
    }

    public Integer getPsetRegister() {
        return psetRegister;
    }

    public void setPsetRegister(Integer psetRegister) {
        this.psetRegister = psetRegister;
    }

    public Integer getPsetLogin() {
        return psetLogin;
    }

    public void setPsetLogin(Integer psetLogin) {
        this.psetLogin = psetLogin;
    }

    public Integer getPsetEmail() {
        return psetEmail;
    }

    public void setPsetEmail(Integer psetEmail) {
        this.psetEmail = psetEmail;
    }

    public Integer getPsetPhone() {
        return psetPhone;
    }

    public void setPsetPhone(Integer psetPhone) {
        this.psetPhone = psetPhone;
    }

    public Integer getPsetComment() {
        return psetComment;
    }

    public void setPsetComment(Integer psetComment) {
        this.psetComment = psetComment;
    }

    public Integer getPsetUser() {
        return psetUser;
    }

    public void setPsetUser(Integer psetUser) {
        this.psetUser = psetUser;
    }

    public Integer getPsetOnline() {
        return psetOnline;
    }

    public void setPsetOnline(Integer psetOnline) {
        this.psetOnline = psetOnline;
    }

    public String getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(String isOpen) {
        this.isOpen = isOpen;
    }

    public Integer getExchange() {
        return exchange;
    }

    public void setExchange(Integer exchange) {
        this.exchange = exchange;
    }

    public BigDecimal getConsumption() {
        return consumption;
    }

    public void setConsumption(BigDecimal consumption) {
        this.consumption = consumption;
    }

    public Integer getPsetPubtopic() {
        return psetPubtopic;
    }

    public void setPsetPubtopic(Integer psetPubtopic) {
        this.psetPubtopic = psetPubtopic;
    }

    public Integer getPsetHottopic() {
        return psetHottopic;
    }

    public void setPsetHottopic(Integer psetHottopic) {
        this.psetHottopic = psetHottopic;
    }

    public Integer getPsetEssencetopic() {
        return psetEssencetopic;
    }

    public void setPsetEssencetopic(Integer psetEssencetopic) {
        this.psetEssencetopic = psetEssencetopic;
    }

    public Integer getPsetIndextopic() {
        return psetIndextopic;
    }

    public void setPsetIndextopic(Integer psetIndextopic) {
        this.psetIndextopic = psetIndextopic;
    }
    
}
