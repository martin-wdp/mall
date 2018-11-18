/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.service.CommentServiceMapper;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.customer.service.CustomerOrderService;
import com.ningpai.m.customer.vo.CustomerConstants;
import com.ningpai.m.util.LoginUtil;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.util.PageBean;

/**
 * 手机端会员订单控制器
 * 
 * @author jiping
 * @since 2014年8月20日 下午2:06:35
 * @version 0.0.1
 */
@Controller
public class CustomerOrderControllerM {

    // spring注解 会员订单service
    private CustomerOrderService customerOrderService;

    private CommentServiceMapper commentServiceMapper;

    private OrderCouponService orderCouponService;

    @Resource(name = "SeoService")
    private SeoService seoService;

    /**
     * 跳转我的订单页面
     * 
     * @param request
     * @return ModelAndview
     */
    @RequestMapping("/myorder")
    public ModelAndView queryAllOrders(HttpServletRequest request, PageBean pb, String date, String type, String paramString) {
        Map<String, Object> paramMap = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        Long customerId = null;
        try {
            // 检查用户是否登录
            if (LoginUtil.checkLoginStatus(request)) {
                paramMap = new HashMap<String, Object>();
                customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
                paramMap.put(CustomerConstants.CUSTOMERID, customerId);
                paramMap.put(CustomerConstants.DATE, date);
                paramMap.put(CustomerConstants.TYPE, type);
                paramMap.put("paramString", paramString);
                pb.setUrl("/customer/myorder");
                resultMap.put(CustomerConstants.TYPE, type);
                resultMap.put("paramString", paramString);
                resultMap.put(CustomerConstants.DATE, date);
                resultMap.put(CustomerConstants.PB, customerOrderService.queryAllMyOrders(paramMap, pb));
                mav = new ModelAndView(CustomerConstants.MYORDER).addAllObjects(resultMap);
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            paramMap = null;
        }
    }

    /**
     * 根据订单编号查询订单信息
     * 
     * @param orderId
     *            订单编号
     * @return ModelAndView
     */
    @RequestMapping("/orderdetails")
    public ModelAndView queryOrderByOrderId(HttpServletRequest request, Long orderId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        try {
            if (LoginUtil.checkLoginStatus(request)) {
                resultMap.put("order", customerOrderService.queryOrderByCustIdAndOrderId(orderId, (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID)));
                mav = new ModelAndView(CustomerConstants.ORDERDETAIL).addAllObjects(resultMap);
            } else {
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 跳转会员中心--评论页
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/tocomment")
    public ModelAndView toShare(HttpServletRequest request, Long orderId) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        ModelAndView mav = null;
        Long customerId = null;
        try {
            if (LoginUtil.checkLoginStatus(request)) {
                customerId = (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID);
                resultMap.put("order", customerOrderService.queryOrderByCustIdAndOrderId(orderId, customerId));

                mav = new ModelAndView("/customer/comment").addAllObjects(resultMap);
            } else {
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
            resultMap = null;
        }
    }

    /**
     * 商品评论
     * 
     * @param request
     * @return {@link ModelAndView}
     */
    @RequestMapping("/comment")
    public ModelAndView toViewComment(HttpServletRequest request, PageBean pageBean) {
        pageBean.setUrl("customer/comment");
        ModelAndView mav = null;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            resultMap.put(CustomerConstants.PB, commentServiceMapper.queryCustComment((Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID), pageBean));
            if (LoginUtil.checkLoginStatus(request)) {
                mav = new ModelAndView(CustomerConstants.COMMENT);
                mav.addAllObjects(resultMap);
            } else {
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            return seoService.getCurrSeo(mav);
        } finally {
            resultMap = null;
        }
    }

    /**
     * 添加商品评论
     * 
     * @param request
     * @param comment
     * @return
     */
    @RequestMapping("/addgoodscomment")
    public ModelAndView addGoodsComment(HttpServletRequest request, @Valid Comment comment, Long orderId) {
        ModelAndView mav = null;
        if (LoginUtil.checkLoginStatus(request)) {
            customerOrderService.addGoodsComment(request, comment, orderId);
            mav = new ModelAndView(new RedirectView(request.getContextPath() + "/comment-" + orderId + ".html"));
        } else {
            mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
        }
        return seoService.getCurrSeo(mav);
    }

    /**
     * 取消订单
     * 
     * @param request
     * @param pb
     *            页面数据
     * @param orderId
     *            订单编号
     * @return ModelAndView
     */
    @RequestMapping("/cancelorder")
    public ModelAndView cancelOrder(HttpServletRequest request, PageBean pb, Long orderId, String reason) {
        ModelAndView mav = null;
        if (!Pattern.compile("[^\\<\\>]+$").matcher(reason).find()) {
            throw new IllegalArgumentException();
        }
        try {
            // 检查用户是否登录
            if (LoginUtil.checkLoginStatus(request)) {
                customerOrderService.cancelOrder(orderId, reason);
                orderCouponService.modifyCouponStatus(orderId);
                // 控制跳转
                mav = new ModelAndView("redirect:/customer/myorder.html");
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            // 跳转订单页面
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }

    /**
     * 确认订单
     * 
     * @param request
     * @param pb
     *            页面数据
     * @param orderId
     *            订单编号
     * @return ModelAndView
     */
    @RequestMapping("/comfirmofgooods")
    public ModelAndView comfirmofGoods(HttpServletRequest request, PageBean pb, Long orderId) {
        ModelAndView mav = null;
        try {
            // 检查用户是否登录
            if (LoginUtil.checkLoginStatus(request)) {
                customerOrderService.comfirmofGoods(orderId);
                orderCouponService.modifyCouponByOrderId(orderId, (Long) request.getSession().getAttribute(CustomerConstants.CUSTOMERID));
                mav = new ModelAndView("redirect:/customer/myorder.html");
            } else {
                // 没登录的用户跳转到登录页面
                mav = new ModelAndView(CustomerConstants.REDIRECTLOGINTOINDEX);
            }
            // 跳转订单页面
            return seoService.getCurrSeo(mav);
        } finally {
            mav = null;
        }
    }

    public CustomerOrderService getCustomerOrderService() {
        return customerOrderService;
    }

    @Resource(name = "customerOrderServiceM")
    public void setCustomerOrderService(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    public CommentServiceMapper getCommentServiceMapper() {
        return commentServiceMapper;
    }

    @Resource(name = "commentServiceMapper")
    public void setCommentServiceMapper(CommentServiceMapper commentServiceMapper) {
        this.commentServiceMapper = commentServiceMapper;
    }

    public OrderCouponService getOrderCouponService() {
        return orderCouponService;
    }

    @Resource(name = "OrderCouponService")
    public void setOrderCouponService(OrderCouponService orderCouponService) {
        this.orderCouponService = orderCouponService;
    }

    public SeoService getSeoService() {
        return seoService;
    }

    public void setSeoService(SeoService seoService) {
        this.seoService = seoService;
    }

}
