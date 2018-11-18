/*
 * Copyright 2013 NINGPAI, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.system.bean;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * 站点基本设置实体类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月16日 下午4:51:11
 * @version 1.0
 */
public class BasicSet  implements Serializable {

    /*
     * 基本ID
     */
    private Long bsetId;
    /*
     * 站点名称
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetName;
    /*
     * 站点描述
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+|()")
    private String bsetDesc;
    /*
     * 站点地址
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetAddress;
    /*
     * 联系电话
     */
    @Pattern(regexp = "^0?1[0-9]{10}$")
    private String bsetPhone;
    /*
     * 网站负责人
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetAdmin;
    /*
     * 邮件
     */
    @Email
    private String bsetEmail;
    /*
     * 网站logo
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetLogo;
    /*
     * 商家登陆页logo
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetThirdLogo;

    /*
     * 标签页图标
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String bsetHotline;
    /*
     * 版权信息
     */
    private String bsetCopyright;

    /*
     * 第三方地址
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetThirdAddress;

    /*
     * 主域名
     */
    @Pattern(regexp = "[^''\\[\\]\\<\\>?\\\\!]+")
    private String bsetDomain;

    /*
     * 前台登陆页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String siteLoginImg;
    /*
     * 前台注册页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String siteRegImg;
     /*
     * 前台注册成功后背景图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String siteRegSuccImg;
    /*
     * 第三方登陆页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String thirdLoginImg;
    /*
     * 第三方注册页面大图片
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String thirdRegImg;

    /*
     * 后台登陆是非要启用验证码
     */
    @Pattern(regexp = "[^\\<\\>]+|()")
    private String loginPatcha;

    /*
     * 用户协议
     */
    private String bsetUseragreement;

    /*
     * 商家注册协议
     */
    private String thirdUserment;
    /*
     * 会员注册协议
     */
    private String bsetUseragreementuser;

    /*
   * 第三方版权信息
   */
    private String thirdCopyright;

    private String bsetEnterpriseagreement;

    public String getBsetEnterpriseagreement() {
        return bsetEnterpriseagreement;
    }

    public void setBsetEnterpriseagreement(String bsetEnterpriseagreement) {
        this.bsetEnterpriseagreement = bsetEnterpriseagreement;
    }

    public String getThirdCopyright() {
        return thirdCopyright;
    }

    public void setThirdCopyright(String thirdCopyright) {
        this.thirdCopyright = thirdCopyright;
    }

    /**
     * 获取SiteRegSuccImg
     * */
    public String getSiteRegSuccImg() {
        return siteRegSuccImg;
    }
    /**
     * 设置SiteRegSuccImg
     * */
    public void setSiteRegSuccImg(String siteRegSuccImg) {
        this.siteRegSuccImg = siteRegSuccImg;
    }
    /**
     * 获取ThirdRegTmg
     * */
    public String getThirdRegImg() {
        return thirdRegImg;
    }
    /**
     * 设置ThirdRegImg
     * */
    public void setThirdRegImg(String thirdRegImg) {
        this.thirdRegImg = thirdRegImg;
    }
    /**
     * 获取THirdUserment
     * */
    public String getThirdUserment() {
        return thirdUserment;
    }
    /**
     * 设置THirdUserment
     * */
    public void setThirdUserment(String thirdUserment) {
        this.thirdUserment = thirdUserment;
    }
    /**
     * 获取LoginPatcha
     * */
    public String getLoginPatcha() {
        return loginPatcha;
    }
    /**
     * 设置LoginPatcha
     * */
    public void setLoginPatcha(String loginPatcha) {
        this.loginPatcha = loginPatcha;
    }
    /**
     * 获取BsetDomain
     * */
    public String getBsetDomain() {
        return bsetDomain;
    }
    /**
     * 设置BsetDomain
     * */
    public void setBsetDomain(String bsetDomain) {
        this.bsetDomain = bsetDomain;
    }
    /**
     * 获取BsetThirdAddress
     * */
    public String getBsetThirdAddress() {
        return bsetThirdAddress;
    }
    /**
     * 设置BsetTHirdAddress
     * */
    public void setBsetThirdAddress(String bsetThirdAddress) {
        this.bsetThirdAddress = bsetThirdAddress;
    }
    /**
     * 设置SiteRegImg
     * */
    public void setSiteRegImg(String siteRegImg) {
        this.siteRegImg = siteRegImg;
    }
    /**
     * 获取SiteRegImg
     * */
    public String getSiteRegImg() {
        return this.siteRegImg;
    }
    /**
     * 获取BsetUseragreementuser
     * */
    public String getBsetUseragreementuser() {
        return bsetUseragreementuser;
    }
    /**
     * 设置BsetUseragreementuser
     * */
    public void setBsetUseragreementuser(String bsetUseragreementuser) {
        this.bsetUseragreementuser = bsetUseragreementuser;
    }
    /**
     * 获取BsetHotline
     * */
    public String getBsetHotline() {
        return bsetHotline;
    }
    /**
     * 设置BsetHotline
     * */
    public void setBsetHotline(String bsetHotline) {
        this.bsetHotline = bsetHotline;
    }
    /**
     * 获取BsetId
     * */
    public Long getBsetId() {
        return bsetId;
    }
    /**
     * 设置BsetId
     * */
    public void setBsetId(Long bsetId) {
        this.bsetId = bsetId;
    }
    /**
     * 获取BsetName
     * */
    public String getBsetName() {
        return bsetName;
    }
    /**
     * 设置BsetName
     * */
    public void setBsetName(String bsetName) {
        this.bsetName = bsetName;
    }
    /**
     * 获取BsetDesc
     * */
    public String getBsetDesc() {
        return bsetDesc;
    }
    /**
     * 设置BsetDesc
     * */
    public void setBsetDesc(String bsetDesc) {
        this.bsetDesc = bsetDesc;
    }
    /**
     * 获取BsetAddress
     * */
    public String getBsetAddress() {
        return bsetAddress;
    }
    /**
     * 设置BsetAddress
     * */
    public void setBsetAddress(String bsetAddress) {
        this.bsetAddress = bsetAddress;
    }
    /**
     * 获取BsetPhone
     * */
    public String getBsetPhone() {
        return bsetPhone;
    }
    /**
     * 设置BsetPhone
     * */
    public void setBsetPhone(String bsetPhone) {
        this.bsetPhone = bsetPhone;
    }
    /**
     * 获取BsetAdmin
     * */
    public String getBsetAdmin() {
        return bsetAdmin;
    }
    /**
     * 设置BsetAdmin
     * */
    public void setBsetAdmin(String bsetAdmin) {
        this.bsetAdmin = bsetAdmin;
    }
    /**
     * 获取BsetEmail
     * */
    public String getBsetEmail() {
        return bsetEmail;
    }
    /**
     * 设置BsetEmail
     * */
    public void setBsetEmail(String bsetEmail) {
        this.bsetEmail = bsetEmail;
    }
    /**
     * 获取BsetLogo
     * */
    public String getBsetLogo() {
        return bsetLogo;
    }
    /**
     * 设置BsetLogo
     * */
    public void setBsetLogo(String bsetLogo) {
        this.bsetLogo = bsetLogo;
    }
    /**
     * 获取BsetCopyright
     * */
    public String getBsetCopyright() {
        return bsetCopyright;
    }
    /**
     *设置BsetCopyRight
     * */
    public void setBsetCopyright(String bsetCopyright) {
        this.bsetCopyright = bsetCopyright;
    }
    /**
     * 获取BsetUseragreement
     * */
    public String getBsetUseragreement() {
        return bsetUseragreement;
    }
    /**
     * 设置BsetUseragreement
     * */
    public void setBsetUseragreement(String bsetUseragreement) {
        this.bsetUseragreement = bsetUseragreement;
    }
    /**
     * 获取SiteLoginImg
     * */
    public String getSiteLoginImg() {
        return siteLoginImg;
    }
    /**
     * 设置SiteLoginImg
     * */
    public void setSiteLoginImg(String siteLoginImg) {
        this.siteLoginImg = siteLoginImg;
    }
    /**
     * 获取THirdLoginImg
     * */
    public String getThirdLoginImg() {
        return thirdLoginImg;
    }
    /**
     * 设置ThirdLoginImg
     * */
    public void setThirdLoginImg(String thirdLoginImg) {
        this.thirdLoginImg = thirdLoginImg;
    }
    /**
     * 获取BsetTHirdLogo
     * */
    public String getBsetThirdLogo() {
        return bsetThirdLogo;
    }
    /**
     * 设置BsetTHirdLogo
     * */
    public void setBsetThirdLogo(String bsetThirdLogo) {
        this.bsetThirdLogo = bsetThirdLogo;
    }
}
