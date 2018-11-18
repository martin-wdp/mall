/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.coupon.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.coupon.bean.CouponRange;
import com.ningpai.coupon.service.CouponRangeService;

/**
 * @author ggn 2014-03-20 优惠券范围controller
 */
@Controller
public class CouponRangeController {

    /*
     * 优惠券范围service
     */
    private CouponRangeService couponRangeService;

    /**
     * 查询优惠券分类范围
     * 
     * @return ModelAndView
     */
    @RequestMapping("/selectcouponrangecatelist")
    @ResponseBody
    public List<CouponRange> selectCouponRangeCateList() {
        // 创建bean
        CouponRange cr = new CouponRange();
        // 设置范围类型
        cr.setCouponRangeType("0");
        // 设置是否删除
        cr.setDelFlag("0");
        // 查询
        return couponRangeService.selectCouponRangeList(cr);
    }

    /**
     * 查询优惠券品牌范围
     * 
     * @return ModelAndView
     */
    @RequestMapping("/selectcouponrangebrandlist")
    @ResponseBody
    public List<CouponRange> selectCouponRangeBrandList() {
        // 创建bean
        CouponRange cr = new CouponRange();
        // 设置范围类型
        cr.setCouponRangeType("1");
        // 设置是否删除
        cr.setDelFlag("0");
        // 查询
        return couponRangeService.selectCouponRangeList(cr);
    }

    /**
     * 查询优惠券SKU范围
     * 
     * @return ModelAndView
     */
    @RequestMapping("/selectcouponrangeskulist")
    @ResponseBody
    public List<CouponRange> selectCouponRangeSkuList() {
        // 创建bean
        CouponRange cr = new CouponRange();
        // 设置范围类型
        cr.setCouponRangeType("2");
        // 设置是否删除
        cr.setDelFlag("0");
        // 查询
        return couponRangeService.selectCouponRangeList(cr);
    }

    public CouponRangeService getCouponRangeService() {
        return couponRangeService;
    }

    @Resource(name = "CouponRangeService")
    public void setCouponRangeService(CouponRangeService couponRangeService) {
        this.couponRangeService = couponRangeService;
    }

}
