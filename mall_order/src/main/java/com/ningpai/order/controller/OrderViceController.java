/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.ningpai.util.MenuSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.order.bean.OrderVice;
import com.ningpai.order.service.OrderViceService;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.util.PageBean;

/**
 * 团购抢购订单
 * 
 * @author NINGPAI-LIH
 * @since 2014年12月4日10:11:27
 *
 */
@Controller
public class OrderViceController {

    private static final String PAGEBEAN = "pageBean";
    private static final String EXPRESSLIST = "expressList";
    private static final String ORDER = "order";
    private static final String JSP_ORDER_ORDERVICELIST = "jsp/order/ordervicelist";

    @Resource(name = "OrderViceService")
    private OrderViceService service;

    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz iLogisticsCompanyBiz;

    /**
     * 抢购订单列表
     * 
     * @param pageBean
     *            分页参数
     * @param orderVice
     *            查询参数
     * @return
     */
    @RequestMapping("ordervicelistbyrush")
    public ModelAndView orderViceListByRush(PageBean pageBean, OrderVice orderVice, HttpServletRequest request) {
        // 设为抢购订单
        orderVice.setOrderType("1");
        orderVice.setBusinessId(0L);
        pageBean.setUrl("ordervicelistbyrush.htm");
        return new ModelAndView(JSP_ORDER_ORDERVICELIST).addObject(PAGEBEAN, service.searchOrderList(orderVice, pageBean))
                .addObject(EXPRESSLIST, iLogisticsCompanyBiz.queryAllLogisticsCompany()).addObject(ORDER, orderVice)
                .addObject(ConstantUtil.CSRFTOKEN, request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 抢购订单列表
     * 
     * @param pageBean
     *            分页参数
     * @param orderVice
     *            查询参数
     * @return
     */
    @RequestMapping("ordervicelistbyrushisthird")
    public ModelAndView orderViceListByRushisthird(PageBean pageBean, OrderVice orderVice, HttpServletRequest request) {
        // 设为抢购订单
        orderVice.setOrderType("1");
        orderVice.setBusinessId(1L);
        pageBean.setUrl("ordervicelistbyrushisthird.htm");
        return new ModelAndView(JSP_ORDER_ORDERVICELIST).addObject(PAGEBEAN, service.searchOrderList(orderVice, pageBean))
                .addObject(EXPRESSLIST, iLogisticsCompanyBiz.queryAllLogisticsCompany()).addObject(ORDER, orderVice)
                .addObject(ConstantUtil.CSRFTOKEN, request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 团购列表
     * 
     * @param pageBean
     *            分页参数
     * @param orderVice
     *            查询参数
     * @return
     */
    @RequestMapping("ordervicelistbygroupon")
    public ModelAndView orderViceListByGroupon(PageBean pageBean, OrderVice orderVice, HttpServletRequest request) {
        MenuSession.sessionMenu(request);
        pageBean.setUrl("ordervicelistbygroupon.htm");
        orderVice.setBusinessId(0L);
        // 设为团购订单
        orderVice.setOrderType("0");
        return new ModelAndView(JSP_ORDER_ORDERVICELIST).addObject(PAGEBEAN, service.searchOrderList(orderVice, pageBean)).addObject(ORDER, orderVice)
                .addObject(EXPRESSLIST, iLogisticsCompanyBiz.queryAllLogisticsCompany())
                .addObject(ConstantUtil.CSRFTOKEN, request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 团购列表
     * 
     * @param pageBean
     *            分页参数
     * @param orderVice
     *            查询参数
     * @return
     */
    @RequestMapping("ordervicelistbygrouponisthird")
    public ModelAndView orderViceListByGrouponisthird(PageBean pageBean, OrderVice orderVice, HttpServletRequest request) {
        pageBean.setUrl("ordervicelistbygrouponisthird.htm");
        orderVice.setBusinessId(1L);
        // 设为团购订单
        orderVice.setOrderType("0");
        return new ModelAndView(JSP_ORDER_ORDERVICELIST).addObject(PAGEBEAN, service.searchOrderList(orderVice, pageBean)).addObject(ORDER, orderVice)
                .addObject(EXPRESSLIST, iLogisticsCompanyBiz.queryAllLogisticsCompany())
                .addObject(ConstantUtil.CSRFTOKEN, request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString());
    }

    /**
     * 修改订单信息
     * 
     * @param orderVice
     *            要修改的订单信息
     * @param url
     *            跳转地址
     * @return
     */
    @RequestMapping("sendgrouporder")
    public ModelAndView sendGroupOrder(OrderVice orderVice, String url) {
        orderVice.setOrderStatus("2");
        service.updateOrderVice(orderVice);
        return new ModelAndView(new RedirectView(url));
    }

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return
     */
    @RequestMapping("selectdetails")
    public ModelAndView selectDetails(Long orderId) {
        return new ModelAndView("jsp/order/ordervicedetail").addObject(ORDER, service.selectDetails(orderId));
    }

    /**
     * 订单详情
     * 
     * @param orderId
     *            主键id
     * @return
     */
    @RequestMapping("selectdetailsajax")
    @ResponseBody
    public OrderVice selectDetailsajax(Long orderId) {
        return service.selectDetails(orderId);
    }

}
