/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.site.customer.controller;

import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.index.service.TopAndBottomService;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.site.customer.service.CustomerServiceInterface;
import com.ningpai.site.customer.vo.CustomerConstantStr;
import com.ningpai.system.service.BasicSetService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 控制器-我的优惠劵
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月14日下午2:10:30
 */
@Controller
public class CouponSiteController {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(CouponSiteController.class);

    private static final String CUSTOMERID = "customerId";

    /**
     * 会员服务接口
     */
    private CustomerServiceInterface customerServiceInterface;

    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    /**
     * 优惠券接口
     */
    private CouponService couponService;

    /**
     * 优惠券券码接口
     */
    private CouponNoService couponNoService;

    /**
     * 获取头尾
     */
    private TopAndBottomService topAndBottomService;

    /**
     * 进入优惠卷列表页
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("coupon/queryCouponList")
    public ModelAndView queryMyCoupon(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView();
        // 设置要跳转的页面
        mav.setViewName("coupon/couponlist");
        // 查询所有的优惠卷信息
        mav.addObject("couponLists", couponService.selectCouponList_site());
        // 新加载头部和底部信息<br/>
        return topAndBottomService.getTopAndBottom(mav);
    }

    /**
     * 进入(满减)优惠卷列表页
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("coupon/queryCouponList2")
    public ModelAndView queryCouponList2(HttpServletRequest request ,
                                                String couponRulesType) {
        if (!checkLoginStatus(request)) {
            return topAndBottomService.getTopAndBottom(new ModelAndView(new RedirectView(request.getContextPath()+"/login.html")));
        }
        ModelAndView mav = new ModelAndView();
        Long customerId = (Long)request.getSession().getAttribute(CUSTOMERID);
        // 设置要跳转的页面
        mav.setViewName("coupon/newcouponlist");
        Map<String,Object> param=new HashMap<String,Object>();
        param.put("couponRulesType",couponRulesType);
        param.put("customerId",customerId);
//        Edit by 付陈林 Time 2015年12月2号，
//        修改要求：需要查询所有有效且没有参与未参与注册营销活动的优惠券
//        实现方式：不修改原有的servcie的请求方法，增加相应的请求方法。
//        源代码如下：
//        // 查询所有的优惠卷信
//        mav.addObject("couponLists", couponService.selectCouponListByType(param));
        List<Coupon> conponsList = couponService.selectCouponListBySite(param);
        mav.addObject("couponLists", conponsList);

        //Edit end
        // 新加载头部和底部信息<br/>
        return topAndBottomService.getTopAndBottom(mav);
    }



    /**
     * 查询我的优惠券
     * 
     * @Description: 查询我的优惠券
     * @param request
     *            请求
     * @param pageBean
     *            分页类
     * @param codeStatus
     *            状态
     * @return
     */
    @RequestMapping(value = "/queryMyCoupon")
    public ModelAndView queryMyCoupon(HttpServletRequest request, PageBean pageBean, String codeStatus) {
        ModelAndView mav = new ModelAndView();
        try {
            // 检查用户是否登录
            if (checkLoginStatus(request)) {
                Long cId = (Long) request.getSession().getAttribute(CUSTOMERID);
                // 根据主键 获取会员详细信息
                Customer customer = customerServiceInterface.queryCustomerById(cId);
                // 非空验证 用户名
                if (null != customer.getCustomerUsername()) {
                    LOGGER.info("查询会员【" + customer.getCustomerUsername() + "】的优惠券");
                }
                // 设置显示行数
                pageBean.setPageSize(12);
                mav.addObject("codeStatus", codeStatus);
                pageBean.setList(null);
                // 我的优惠券
                mav.addObject("pb", couponService.myCouponList(pageBean, cId, codeStatus));
                pageBean.setUrl("mycoupon/" + Integer.valueOf(codeStatus));
                // 查询站点信息
                mav.addObject("basicSet", basicSetService.findBasicSet());
                mav.setViewName("customer/newmycoupon");
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView("/login/redirect").addObject("url", "/mycoupon/1");
            }
            // 跳转到我的优惠券
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 领取优惠券
     * 
     * @param request
     * @param
     * @return
     */
    @RequestMapping("getOffCoupon")
    public ModelAndView getOffCoupon(HttpServletRequest request, Long couponId) {
        ModelAndView mav = new ModelAndView();
        // 插叙优惠券详细
        Coupon coupon = couponService.searchCouponById(couponId);
        try {

            Long cId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (cId == null) {
                mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT);
                // mav = new ModelAndView(new RedirectView("login.html"));
            } else {
                // 根据主键 获取会员详细信息
                Customer customer = customerServiceInterface.queryCustomerById(cId);
                // 非空验证 用户名
                if (null != customer.getCustomerUsername() && null != coupon.getCouponName()) {
                    // 操作日志
                    OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "领取优惠券", "优惠券名称【" + coupon.getCouponName() + "】");
                    // 记录日志
                    LOGGER.info("会员【" + customer.getCustomerUsername() + "】领取优惠券成功！");
                }
                // 查询该用户此优惠券领取的张数
                int counts = couponNoService.selectReadyGet(couponId, cId);
                // 查询优惠券总数
                int countAll = couponNoService.selectCountAllByCouponId(couponId);

                // 如果等于0代表没有领取，可以进行领取
                // 否则提示已经领取过
                if (counts < coupon.getCouponGetNo().intValue()) {
                    // 判断可领取张数减去已经被领取张数是否大于0
                    // 如果大于0，可以进行领取
                    // 否则提示已领取完
                    if ((countAll - couponNoService.queryUsedCountByCouponId(couponId).intValue()) > 0) {
                        Date d = new Date();
                        // 如果开始时间 大于当前时间 并且 结束世界晚与当前时间 为有效
                        if (coupon.getCouponStartTime().before(d) && coupon.getCouponEndTime().after(d)) {
                            // 返回劵码
                            CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(couponId);
                            if (couponNo != null) {
                                // 领取优惠券
                                int count = couponNoService.updateCouponCustomer(couponNo.getCodeId(), cId);
                                if (count > 0) {
                                    // 领取成功页面
                                    mav.setViewName("customer/couponpage");
                                } else {
                                    // 已领取完页面
                                    mav.setViewName("customer/couponwrongpage");
                                }
                            } else {

                                mav = new ModelAndView(new RedirectView("login.html"));
                            }

                        } else {

                            // 已领取完页面
                            mav.setViewName("customer/couponstart");
                        }

                    } else {
                        // 已领取完页面
                        mav.setViewName("customer/couponwrongpage");
                    }
                } else {

                    // 已领取过页面
                    mav.setViewName("customer/coupongotpage");
                }
            }
            // 跳转到我的优惠券
            return topAndBottomService.getTopAndBottom(mav);
        } finally {
            mav = null;
        }
    }


    /**
     * ADD BY LY
     * 领取优惠券AJAX
     * int 0 未登录
     *     1 成功
     *     2 已领完
     *     3 用户已经领取过了自己可以领取的最大张数
     * @param request
     * @param
     * @return
     */
    @RequestMapping("qpgetOffCouponAjax")
    @ResponseBody
    public int qpgetOffCouponAjax(HttpServletRequest request, Long couponId) {
        //ModelAndView mav = new ModelAndView();
        // 插叙优惠券详细
        Coupon coupon = couponService.searchCouponById(couponId);
        try {

            Long cId = (Long) request.getSession().getAttribute(CUSTOMERID);
            if (cId == null) {
                return 0;
                /*mav = new ModelAndView(CustomerConstantStr.LOGINREDIRECT);*/
                // mav = new ModelAndView(new RedirectView("login.html"));
            } else {
                // 根据主键 获取会员详细信息
                Customer customer = customerServiceInterface.queryCustomerById(cId);
                // 非空验证 用户名
                if (null != customer.getCustomerUsername() && null != coupon.getCouponName()) {
                    // 操作日志
                    OperaLogUtil.addOperaLog(request, customer.getCustomerUsername(), "领取优惠券", "优惠券名称【" + coupon.getCouponName() + "】");
                    // 记录日志
                    LOGGER.info("会员【" + customer.getCustomerUsername() + "】领取优惠券成功！");
                }
                // 查询该用户此优惠券领取的张数
                int counts = couponNoService.selectReadyGet(couponId, cId);
                // 查询优惠券总数
                int countAll = couponNoService.selectCountAllByCouponId(couponId);

                // 如果等于0代表没有领取，可以进行领取
                // 否则提示已经领取过
                if (counts < coupon.getCouponGetNo().intValue()) {
                    // 判断可领取张数减去已经被领取张数是否大于0
                    // 如果大于0，可以进行领取
                    // 否则提示已领取完
                    if ((countAll - couponNoService.queryUsedCountByCouponId(couponId).intValue()) > 0) {
                        Date d = new Date();
                        // 如果开始时间 大于当前时间 并且 结束世界晚与当前时间 为有效
                        if (coupon.getCouponStartTime().before(d) && coupon.getCouponEndTime().after(d)) {
                            // 返回劵码
                            CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(couponId);
                            if (couponNo != null) {
                                // 领取优惠券
                                int count = couponNoService.updateCouponCustomer(couponNo.getCodeId(), cId);
                                if (count > 0) {
                                    // 领取成功页面
                                   // mav.setViewName("customer/couponpage");
                                    return 1;
                                } else {
                                    // 已领取完页面
                                    //mav.setViewName("customer/couponwrongpage");
                                    return 2;
                                }
                            } else {
                                return 0;
                                //mav = new ModelAndView(new RedirectView("login.html"));
                            }

                        } else {
                            return 2;
                            // 已领取完页面
                            //mav.setViewName("customer/couponstart");
                        }

                    } else {
                        // 已领取完页面
                        return 2;
                        //mav.setViewName("customer/couponwrongpage");
                    }
                } else {

                    // 已领取过页面
                    return 3;
                    //mav.setViewName("customer/coupongotpage");
                }
            }
            // 跳转到我的优惠券
            //return topAndBottomService.getTopAndBottom(mav);
        } finally {
            //mav = null;
        }
    }

    // 验证用户是否登录
    private boolean checkLoginStatus(HttpServletRequest request) {
        if (request.getSession().getAttribute(CUSTOMERID) == null) {
            return false;
        }
        return true;
    }

    public CouponNoService getCouponNoService() {
        return couponNoService;
    }

    @Resource(name = "CouponNoService")
    public void setCouponNoService(CouponNoService couponNoService) {
        this.couponNoService = couponNoService;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    public TopAndBottomService getTopAndBottomService() {
        return topAndBottomService;
    }

    @Resource(name = "TopAndBottomService")
    public void setTopAndBottomService(TopAndBottomService topAndBottomService) {
        this.topAndBottomService = topAndBottomService;
    }

    public CustomerServiceInterface getCustomerServiceInterface() {
        return customerServiceInterface;
    }

    @Resource(name = "customerServiceSite")
    public void setCustomerServiceInterface(CustomerServiceInterface customerServiceInterface) {
        this.customerServiceInterface = customerServiceInterface;
    }
}
