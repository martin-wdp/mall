/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ningpai.coupon.service.CouponService;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;
import com.ningpai.util.PageBean;

/**
 * 会员优惠券Controller
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年8月21日 上午11:30:40
 * @version 0.0.1
 */
@Controller
public class CouponCustomerM {

    // 优惠券Service
    @Resource(name = "CouponService")
    private CouponService couponService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 获取优惠券
     * 
     * @param request
     * @param type
     *            类型
     * @return {@link ModelAndView}
     */
    @RequestMapping("/customer/coupons")
    public ModelAndView queryMyConpons(HttpServletRequest request, PageBean pageBean, String type) {
        String typeNew = type;
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 检查用户是否登录
            if (LoginUtil.checkLoginStatus(request)) {
                mav = new ModelAndView();
                if (typeNew == null || ("").equals(typeNew)) {
                    typeNew = "1";
                }
                resultMap.put("type", typeNew);
                resultMap.put("pb", couponService.myCouponList(pageBean, (Long) request.getSession().getAttribute("customerId"), typeNew));
                resultMap.put("status1", couponService.countByCodeStatus((Long) request.getSession().getAttribute("customerId"), "1"));
                resultMap.put("status2", couponService.countByCodeStatus((Long) request.getSession().getAttribute("customerId"), "2"));
                resultMap.put("status3", couponService.countByCodeStatus((Long) request.getSession().getAttribute("customerId"), "3"));
                mav.setViewName("member/mycoupons");
                mav.addAllObjects(resultMap);
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            // 跳转到我的优惠券
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }

}
