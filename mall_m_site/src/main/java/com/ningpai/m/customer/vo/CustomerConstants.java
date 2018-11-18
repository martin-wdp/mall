/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.vo;

import com.ningpai.common.util.ConstantUtil;

/**
 * 会员模块常量类
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2013年12月25日 下午8:02:59
 */
public final class CustomerConstants {

    /**
     * 会员中心页面
     */
    public static final String CUSTOMERCENTER = "customer/customercenter";
    /**
     * 我的订单页面
     */
    public static final String MYORDER = "customer/order";
    /**
     * 个人信息页面
     */
    public static final String OWNERINFO = "customer/info";
    /**
     * 订单状态显示页面
     */
    public static final String ORDERDETAIL = "customer/orderdetail";
    /**
     * Cookie 会员编号键
     */
    public static final String NPSTORE_CUSTOMERID = "npstore_customerId";
    /**
     * 收货地址页面
     */
    public static final String CUSTOMERADDRESS = "customer/address";
    /**
     * customer string
     */
    public static final String CUSTOMER = "customer";
    /**
     * 订单成功页面
     */
    public static final String SUCCESSORDER = "customer/successorder";
    /**
     * order String
     */
    public static final String ORDER = "order";
    /**
     * 跳转重置密码页面
     */
    public static final String RESETPASS = "customer/resetPass";
    /**
     * 提示页面
     */
    public static final String TIP = "customer/tip";

    /**
     * 跳转邮件验证页面
     */
    public static final String CHECKIDENTITY = "customer/checkEmail";
    /**
     *
     */
    public static final String FINDPASS = "customer/findPass";
    /**
     * 邮件发送成功
     */
    public static final String EMAILSUCCESS = "customer/emailSuccess";
    /**
     * 密码修改成功
     */
    public static final String MODIFYSUCCESS = "customer/modifysuccess";
    /**
     * 跳转登录页面
     */
    public static final String GOLOGIN = "gologin.htm";
    /**
     * queryCustomerAddress.htm
     */
    public static final String CUSTOMERADDRESSHTM = "queryCustomerAddress.htm";
    /**
     * String "customerId"
     */
    public static final String CUSTOMERID = "customerId";
    /**
     * 积分页面
     */
    public static final String CUSTOMERPOINT = "customer/customerPoint";
    /**
     * startRowNum
     */
    public static final String STARTNUM = "startRowNum";
    /**
     * "endRowNum"
     */
    public static final String ENDNUM = "endRowNum";
    /**
     * "pb"
     */
    public static final String PB = "pb";
    /**
     * 会员首页 customer/index
     */
    public static final String CUSTOMERINDEX = "customer/index";
    /**
     * NEW会员首页 customer/index
     */
    public static final String NEWCUSTOMERINDEX = "customer/newindex";
    /**
     * 会员积分页面 customer/integral
     */
    public static final String MYINTEGRAL = "customer/integral";
    /**
     * 浏览记录页面 customer/browserecord.ftl
     */
    public static final String BROWSERECORD = "customer/browserecord";
    /**
     * 我的收藏页面
     */
    public static final String MYFOLLOW = "customer/follow";

    public static final String REFUNDLIST = "customer/cancelorder";
    /**
     * 投诉页面
     */
    public static final String ORDERCOMPLAIN = "customer/ordercomplain";
    /**
     * 填写投诉内容
     */
    public static final String TOCOMPLAIN = "customer/tocomplain";
    /**
     * 会员安全中心
     */
    public static final String SECURITYCENTER = "customer/accountsecurity";
    /**
     * 验证身份
     */
    public static final String VALIDATEIDENTITY = "customer/validateidentity";
    /**
     * 修改密码 邮箱 手机
     */
    public static final String MODIFYPEM = "customer/reirectpem";
    /**
     * 登录跳转
     */
    public static final String LOGINREDIRECT = "/login/redirect";
    /**
     * URL String
     */
    public static final String URL = "url";
    /**
     * /customer
     */
    public static final String CUSTOMERS = "/" + CUSTOMER;
    /**
     * ISO-8859-1
     */
    public static final String ISO = "ISO-8859-1";
    /**
     * utf-8
     */
    public static final String UTF = ConstantUtil.UTF;
    /**
     * "/securitycenter.html"
     */
    public static final String CENTERHTML = "/securitycenter.html";

    public static final String TYPE = "type";
    public static final String DATE = "date";
    /**
     * customer/consult
     */
    public static final String CONSULT = "customer/consult";
    /**
     * consults
     */
    public static final String CONSULTS = "consults";
    /**
     * customer/comment
     */
    public static final String COMMENT = "customer/comment";

    /**
     * 账户管理页面
     */
    public static final String ACCOUNTMANAGE = "customer/accountManage";
    /**
     * 账户管理页面
     */
    public static final String BROWSERECORDLIST = "customer/accountManage";
    /**
     * 账户管理页面
     */
    public static final String BROWSERECORDS = "customer/browseRecords";
    /**
     * 修改密码页面
     */
    public static final String RESETPASSWORD = "customer/resetPassword";
    /**
     * 收货地址页面
     */
    public static final String RECEIPTADDR = "customer/receiptAddr";
    /**
     * 添加新收货地址的编辑页面
     */
    public static final String REVISEADDR = "customer/reviseAddr";
    /**
     * 验证方式 ut
     */
    public static final String UT = "ut";
    public static final String REDIRECTLOGINTOINDEX = "redirect:/login.html?url=/customer/index.html?tag=4";
    public static final String TOADDRESSLISTHTML = "redirect:/customer/showOrderAddressList.htm";
    public static final String FILLADDRESS = "customer/filladdress";
    //public static final String REGOSTER = "register/register";
    public static final String REGOSTER = "customer/register";
    public static final String TOPWSSUCCESS = "customer/success";
    public static final String REDIRECTTOINDEX = "redirect:/customer/index.html?tag=4";
    public static final String REDIRECTTOFINDPWD = "customer/findpwd1";
    public static final String REDIRECTTORESETPWD = "customer/findpwd2";
    public static final String REDIRECTTORESETPWDSUCC = "customer/findpwd3";

    private CustomerConstants() {

    }

}
