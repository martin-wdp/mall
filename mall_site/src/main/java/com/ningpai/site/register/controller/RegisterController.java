/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.register.controller;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.RegisterPoint;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.marketing.bean.RegisterMarketing;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.other.util.IPAddress;
import com.ningpai.site.login.service.LoginService;
import com.ningpai.system.service.AuthService;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.qpmall.marketing.bean.RegisterCoupon;
import com.qpmall.site.register.dao.QpEnterpriseCertificationInfoMapper;
import com.qpmall.site.register.service.QpEnterpriseCertificationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 前台注册Controller
 *
 * @author NINGPAI-zhangqiang
 * @version 0.0.1
 * @since 2014年4月16日 下午5:11:01
 */
@Controller
public class RegisterController {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(RegisterController.class);

    private static final String CUSTOMERID = "customerId";

    private static final String REGISTER_SUCCESS = "/register/registersuc";

    // Date date = new Date();
    Date startTime = null;

    Date endTime = null;

    // 是否赠送优惠卷
    int count = 0;

    // 是否有赠送的积分
    int pointCount = 0;

    // spring 注解 会员服务Service
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 站点设置服务层接口接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    private LoginService loginService;

    private CustomerPointServiceMapper customerPointServiceMapper;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "CouponNoService")
    private CouponNoService couponNoService;

    @Resource(name = "CouponService")
    private CouponService couponService;

    // 获取头尾
    private TopAndBottomService topAndBottomService;

    // 获取已启用的第三方登录接口
    @Resource(name = "authService")
    private AuthService authService;

    @Resource(name = "customerInfoMapper")
    private CustomerInfoMapper customerInfoMapper;

    /**
     * 跳转注册页面
     *
     * @return ModelAndView
     */
    @RequestMapping("/customer/register")
    public ModelAndView jumpRegister() {
        // return topAndBottomService.getBottom(new
        // ModelAndView("/register/register").addObject("t",
        // authService.findByShow()));
        return topAndBottomService.getBottom(new ModelAndView("/register/newregister").addObject("t", authService.findByShow()));
    }

    /**
     * 获取当前客户端的IP 传到页面
     *
     * @return
     * @throws IOException
     */
    @RequestMapping("checkRegsiter")
    public ModelAndView checkRegsiter(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String getIp = IPAddress.getIpAddr(request);
        // 网页面写参数
        PrintWriter out = response.getWriter();
        // 把获取的IP传到页面
        out.print(getIp);

        return null;
    }

    /**
     * 注册
     *
     * @param request
     * @param allInfo
     * @return
     */
    @RequestMapping("/customer/newaddcustomer")
    @ResponseBody
    public ModelAndView newaddcustomer(HttpServletRequest request, @Valid CustomerAllInfo allInfo, Long cusId, String user, String psd, String varification) {
        if (allInfo.getCustomerUsername() == null || allInfo.getCustomerPassword() == null) {
            throw new NullPointerException();
        }
        allInfo.setLoginIp(IPAddress.getIpAddr(request));
        allInfo.setIsSeller("0");

        // 判断用户名是否存在
        Long isUsernameFlag = customerServiceMapper.selectCustomerByName(allInfo.getCustomerUsername());
        // 验证码
        String patchca = (String) request.getSession().getAttribute("PATCHCA");
        // 如果不存在则注册，验证码是否一致
        if (isUsernameFlag == 0 && patchca.equals(varification)) {
            // 注册成功返回值
            int register = customerServiceMapper.addCustomer(allInfo);
            // 非空验证 推荐用户 被推荐用户
            if (null != allInfo.getCustomerUsername() && register == 1) {
                LOGGER.info("会员【" + allInfo.getCustomerUsername() + "】注册成功。");
            }
            loginService.checkCustomerExists(request, allInfo.getCustomerUsername(), allInfo.getCustomerPassword());

            // 判断是否是会员推荐的注册链接
            if (null != cusId && register == 1) {
                customerPointServiceMapper.addIntegralByType(cusId, "5");
                // 保存推广注册成功的信息
                registPoint(request, cusId, allInfo.getCustomerUsername());
                customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");
            } else {
                customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");
            }

            // 判断注册送优惠券是否开启
            RegisterMarketing registerMarketing = marketingService.findRegisterMarketing();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                startTime = sdf.parse(registerMarketing.getStartTime());
                endTime = sdf.parse(registerMarketing.getEndTime());
            } catch (ParseException e) {
                // 日志
                // String operaCode = "注册送优惠券出错";
                // String operaContent =
                // request.getSession().getAttribute(CustomerConstantStr.OPERAPATH)
                // + "注册送优惠券出错";
            }
            if (registerMarketing != null && registerMarketing.getIsUsed() != null && Integer.parseInt(registerMarketing.getIsUsed()) == 1) {
                        Date date = new Date();
                        // 判断活动开启关闭时间
                        if (startTime.before(date) && endTime.after(date)) {
                            // 判断该优惠券是否还有
//                            edit by 付陈林，2015年12月4日，由于注册营销选择优惠劵可以选择多张，所此地需要对多优惠劵进行处理。
//                            count = couponNoService.selectCouponNoByStatus(registerMarketing.getRegisterCouponId());
//                            if (count > 0) {
//                                // 根据优惠券id随机获取一张未使用的优惠券码
//                                CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(registerMarketing.getRegisterCouponId());
//                                // 获取用户id 把优惠券给用户
//                                couponNoService.updateCouponCustomer(couponNo.getCodeId(), (Long) request.getSession().getAttribute(CUSTOMERID));
//                            }
//                            判读是否选择优惠劵
                            if(registerMarketing.getRcList()!=null&&registerMarketing.getRcList().size()>0){
                                for(int i=0;i<registerMarketing.getRcList().size();i++){
                                    RegisterCoupon rc =registerMarketing.getRcList().get(i);
                                    count = couponNoService.selectCouponNoByStatus(rc.getCouponId());
                                    if (count > 0) {
                                        // 根据优惠券id随机获取未使用的优惠券码 张数根据优惠劵发送的张数。
                                        Coupon coupon =couponService.searchCouponById(rc.getCouponId());
//                                        判断优惠劵是否有效。
                                        if (coupon.getCouponStartTime().before(date) && coupon.getCouponEndTime().after(date)) {
                                            for(int j=0;j<coupon.getCouponGetNo();j++) {
                                                CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(rc.getCouponId());
                                                // 获取用户id 把优惠券给用户
                                                couponNoService.updateCouponCustomer(couponNo.getCodeId(), (Long) request.getSession().getAttribute(CUSTOMERID));
                                            }
                                        }
                                    }
                                }

                            }
//                            edit end 付陈林

                        }
            }
            return new ModelAndView(new RedirectView(request.getContextPath() + "/success.html"));
        } else {
            // 非法操作，跳至首页
            return new ModelAndView(new RedirectView(request.getContextPath() + "/index.html"));
        }

    }

    /**
     * 注册
     *
     * @param request
     * @param allInfo
     * @return
     */
    @RequestMapping("/customer/addcustomer")
    public ModelAndView register(HttpServletRequest request, @Valid CustomerAllInfo allInfo, Long cusId, String user, String psd, String varification) {
        if (allInfo.getCustomerUsername() == null || allInfo.getCustomerPassword() == null) {
            throw new NullPointerException();
        }
        allInfo.setLoginIp(IPAddress.getIpAddr(request));
        allInfo.setIsSeller("0");

        // 判断用户名是否存在
        Long isUsernameFlag = customerServiceMapper.selectCustomerByName(allInfo.getCustomerUsername());
        // 验证码
        String patchca = (Integer) request.getSession().getAttribute(allInfo.getCustomerUsername() + "codeMobileNum")+"";

        // 如果不存在则注册，验证码是否一致
        // if (isUsernameFlag == 0 || patchca.equals(varification)){
        if (isUsernameFlag == 0 && patchca.equals(varification)) {
            allInfo.setIsMobile("1");
            allInfo.setIsEnterprise("0");
            // 注册成功返回值
            int register = customerServiceMapper.addCustomer(allInfo);
            // 非空验证 推荐用户 被推荐用户
            if (null != allInfo.getCustomerUsername() && register == 1) {
                LOGGER.info("会员【" + allInfo.getCustomerUsername() + "】注册成功。");
            }
            loginService.checkCustomerExists(request, allInfo.getCustomerUsername(), allInfo.getCustomerPassword());

            // 判断注册送优惠券是否开启
            RegisterMarketing registerMarketing = marketingService.findRegisterMarketing();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                startTime = sdf.parse(registerMarketing.getStartTime());
                endTime = sdf.parse(registerMarketing.getEndTime());
            } catch (ParseException e) {
                // 日志
                // String operaCode = "注册送优惠券出错";
                // String operaContent =
                // request.getSession().getAttribute(CustomerConstantStr.OPERAPATH)
                // + "注册送优惠券出错";
            }
            // 获取当前系统时间
            Date date = new Date();
            // 判断是否是会员推荐的注册链接
            if (null != cusId && register == 1) {
                customerPointServiceMapper.addIntegralByType(cusId, "5");
                // 保存推广注册成功的信息
                registPoint(request, cusId, allInfo.getCustomerUsername());

                // 注册营销是否开启
                if (registerMarketing.getIsUsed() != null && Integer.parseInt(registerMarketing.getIsUsed()) == 1 && startTime.before(date) && endTime.after(date)) {
                    customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");
                    // 如果注册成功赠送了积分 就＋1 用于页面跳转
                    pointCount++;
                } else {
                    // 没有开始设置注册积分为0
                    customerPointServiceMapper.updateIntegralById(0);
                    // 根据类型不同添加不同的积分数量
                    customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");

                }
            } else {
                // 注册营销是否开启
                if (registerMarketing.getIsUsed() != null && Integer.parseInt(registerMarketing.getIsUsed()) == 1 && startTime.before(date) && endTime.after(date)) {
                    customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");
                    // 如果注册成功赠送了积分 就＋1 用于页面跳转
                    pointCount++;
                } else {
                    // 没有开始设置注册积分为0
                    customerPointServiceMapper.updateIntegralById(0);
                    // 根据类型不同添加不同的积分数量
                    customerPointServiceMapper.addIntegralByType((Long) request.getSession().getAttribute(CUSTOMERID), "0");
                }

            }

            if (registerMarketing != null && registerMarketing.getIsUsed() != null && Integer.parseInt(registerMarketing.getIsUsed()) == 1) {
                        // 判断活动开启关闭时间
                        if (startTime.before(date) && endTime.after(date)) {
                            // 判断该优惠券是否还有
//                            edit by 付陈林，2015年12月4日，由于注册营销选择优惠劵可以选择多张，所此地需要对多优惠劵进行处理。
//                            count = couponNoService.selectCouponNoByStatus(registerMarketing.getRegisterCouponId());
//                            if (count > 0) {
//                                // 根据优惠券id随机获取一张未使用的优惠券码
//                                CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(registerMarketing.getRegisterCouponId());
//                                // 获取用户id 把优惠券给用户
//                                couponNoService.updateCouponCustomer(couponNo.getCodeId(), (Long) request.getSession().getAttribute(CUSTOMERID));
//                            }
//                            判读是否选择优惠劵
                            if(registerMarketing.getRcList()!=null&&registerMarketing.getRcList().size()>0){
                                for(int i=0;i<registerMarketing.getRcList().size();i++){
                                    RegisterCoupon rc =registerMarketing.getRcList().get(i);
                                    count = couponNoService.selectCouponNoByStatus(rc.getCouponId());
                                    if (count > 0) {
                                        // 根据优惠券id随机获取未使用的优惠券码 张数根据优惠劵发送的张数。
                                        Coupon coupon =couponService.searchCouponById(rc.getCouponId());
//                                        判断优惠劵是否有效。
                                        if (coupon.getCouponStartTime().before(date) && coupon.getCouponEndTime().after(date)) {
                                            for(int j=0;j<coupon.getCouponGetNo();j++) {
                                                CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(rc.getCouponId());
                                                // 获取用户id 把优惠券给用户
                                                couponNoService.updateCouponCustomer(couponNo.getCodeId(), (Long) request.getSession().getAttribute(CUSTOMERID));
                                            }
                                        }
                                    }
                                }

                            }
//                            edit end 付陈林
                        } else {
                            if (registerMarketing.getRegisterIntegral() != null && registerMarketing.getRegisterIntegral() > 0) {
                                // 设置注册积分为0
                                customerPointServiceMapper.updateIntegralById(0);
                                // 换行
                            }
                        }
            }
                //add by ly  增加注册成功标识，在注册成功页面进行非法访问拦截 15-11.04
                request.getSession().setAttribute("isRegistering","ok");
                return new ModelAndView(new RedirectView(request.getContextPath() + "/success.html"));

        } else {
            // 非法操作，跳至首页
            return new ModelAndView(new RedirectView(request.getContextPath() + "/index.html"));
        }

    }

    /**
     * 保存推广链接注册信息
     *
     * @return
     */
    protected int registPoint(HttpServletRequest request, Long customerId, String customerUsername) {
        int result = 0;
        IntegralSet inte = customerPointServiceMapper.findPointSet();
        // 根据ID获取会员对象
        Customer customer = customerPointServiceMapper.selectCusById(customerId);
        // 推广注册信息
        RegisterPoint point = new RegisterPoint();
        point.setRegPointReferee(customer.getCustomerUsername()); // 推荐人
        point.setRegPointRecom(customerUsername);// 被推荐人
        point.setRegPointNumber(inte.getPsetUser()); // 注册赠送的积分
        point.setRegPointTime(new Date()); // 推广注册时间
        result = customerPointServiceMapper.insertRegisterPoint(point);
        // 非空验证 推荐用户 被推荐用户
        if (null != customer.getCustomerUsername() && null != customerUsername && 1 == result) {
            LOGGER.info("会员【" + customer.getCustomerUsername() + "】推荐用户【" + customerUsername + "】注册成功，并且成功获取积分奖励！");
        }
        return result;
    }

    /**
     * 注册成功
     *
     * @return
     */
    @RequestMapping("/customer/success")
    public ModelAndView success(HttpServletRequest request) {
        //进行非法访问拦截 15-11.04 ADD BY LY
        if ((Long) request.getSession().getAttribute("customerId") == null || "".equals((Long) request.getSession().getAttribute("customerId"))) {
            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView("login.html")));
        }
        //进行非法访问拦截 15-11.04 ADD BY LY
        if(request.getSession().getAttribute("isRegistering")==null||"".equals(request.getSession().getAttribute("isRegistering"))){
            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView("index.html")));
        }
        request.getSession().setAttribute("isRegistering","");
        RegisterMarketing registerMarketing = marketingService.findRegisterMarketing();
        if (count > 0 || pointCount > 0) {
            if (Integer.parseInt(registerMarketing.getIsUsed()) == 1) {
                Date date = new Date();
                if (startTime.before(date) && endTime.after(date)) {
                    return topAndBottomService.getBottom(new ModelAndView(REGISTER_SUCCESS).addObject("registerIntegral", registerMarketing.getRegisterIntegral())
                            .addObject("basicSet", basicSetService.findBasicSet())
                            .addObject("couponId", registerMarketing.getRegisterCouponId()));
                } else {
                    return topAndBottomService.getBottom(new ModelAndView(REGISTER_SUCCESS));
                }
            } else {
                return topAndBottomService.getBottom(new ModelAndView(REGISTER_SUCCESS));
            }
        } else {
            return topAndBottomService.getBottom(new ModelAndView(REGISTER_SUCCESS));
        }
    }

    public CustomerServiceMapper getCustomerServiceMapper() {
        return customerServiceMapper;
    }

    @Resource(name = "customerServiceMapper")
    public void setCustomerServiceMapper(CustomerServiceMapper customerServiceMapper) {
        this.customerServiceMapper = customerServiceMapper;
    }

    public LoginService getLoginService() {
        return loginService;
    }

    @Resource(name = "loginServiceSite")
    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

}
