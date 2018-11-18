/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 *
 */
package com.ningpai.m.customer.controller;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.bean.Coupon;
import com.ningpai.coupon.bean.CouponNo;
import com.ningpai.coupon.service.CouponNoService;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;
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
public class CouponSiteControllerM {

    /**
     * 记录日志对象
     */
    private static final MyLogger LOGGER = new MyLogger(CouponSiteControllerM.class);

    /**
     * 站点设置服务层接口
     */
    @Resource(name = "basicSetService")
    private BasicSetService basicSetService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 优惠券接口
     */
    @Resource(name = "CouponService")
    private CouponService couponService;

    /**
     * 优惠券券码接口
     */
    @Resource(name = "CouponNoService")
    private CouponNoService couponNoService;


    /**
     * 进入(满减)优惠卷列表页
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("coupon/queryCouponList2")
    public ModelAndView queryCouponList2(HttpServletRequest request,
                                         String couponRulesType) {
        ModelAndView mav = new ModelAndView();
        Map<String, Object> param = new HashMap<String, Object>();
        // 检查用户是否登录
        if (LoginUtil.checkLoginStatus(request)) {
            mav = new ModelAndView();
            param.put("couponRulesType", couponRulesType);
            param.put("customerId", (Long) request.getSession().getAttribute("customerId"));
            mav.setViewName("customer/couponlist");
            List<Coupon> coupons = couponService.selectCouponListBySite(param);
            mav.addObject("couponList", coupons);
            mav.addObject(ConstantUtil.PAGENAME, "优惠券领取列表");
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
        }
        // 跳转到我的优惠券
        return seoService.getCurrSeo(mav);
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
        if (LoginUtil.checkLoginStatus(request)) {
            Coupon coupon = couponService.searchCouponById(couponId);
            mav = new ModelAndView();
            Long cId = (Long) request.getSession().getAttribute("customerId");
            // 查询该用户此优惠券领取的张数
            int counts = couponNoService.selectReadyGet(couponId, cId);
            mav.setViewName("customer/couponresult");
            // 查询优惠券总数
            int countAll = couponNoService.selectCountAllByCouponId(couponId);
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
                        // 领取优惠券
                        int count = couponNoService.updateCouponCustomer(couponNo.getCodeId(), cId);
                        // 领取成功或者失败的结果页面
                        mav.addObject("count", count);
                    }
                }

            }
            mav.addObject(ConstantUtil.PAGENAME, "优惠券领取结果");
        } else {
            // 没登录的用户跳转到登录页面
            mav = new ModelAndView("redirect:/login.html?url=/customer/index.html?tag=4");
        }
        // 跳转到我的优惠券
        return seoService.getCurrSeo(mav);
    }


    /**
     * ADD BY LY
     * 领取优惠券AJAX
     * int -1 未登录 0 失败 1 成功
     * 1 成功
     * 2 已领完
     * 3 用户已经领取过了自己可以领取的最大张数
     *
     * @param request
     * @param
     * @return
     */
    @RequestMapping("qpgetOffCouponAjax")
    @ResponseBody
    public int qpgetOffCouponAjax(HttpServletRequest request, Long couponId) {
        // 插叙优惠券详细
        if (!LoginUtil.checkLoginStatus(request)) {
            return -1;
        }
        Coupon coupon = couponService.searchCouponById(couponId);
        Long cId = (Long) request.getSession().getAttribute("customerId");
        // 查询该用户此优惠券领取的张数
        int counts = couponNoService.selectReadyGet(couponId, cId);
        // 查询优惠券总数
        int countAll = couponNoService.selectCountAllByCouponId(couponId);
        if (counts >= coupon.getCouponGetNo().intValue()) {
            return 0;
        }
        // 判断可领取张数减去已经被领取张数是否大于0
        // 如果大于0，可以进行领取
        // 否则提示已领取完
        if ((countAll - couponNoService.queryUsedCountByCouponId(couponId).intValue()) <= 0) {
            return 0;
        }
        Date d = new Date();
        // 如果开始时间 大于当前时间 并且 结束世界晚与当前时间 为有效
        if (!(coupon.getCouponStartTime().before(d) && coupon.getCouponEndTime().after(d))) {
            return 0;
        }
        // 返回劵码
        CouponNo couponNo = couponNoService.selectNoByCouponIdByStatus(couponId);
        // 领取优惠券
        int count = couponNoService.updateCouponCustomer(couponNo.getCodeId(), cId);
        // 领取成功或者失败的结果页面
        return count;

    }
}
