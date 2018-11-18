/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.safe.CSRFTokenManager;
import com.ningpai.common.util.ConstantUtil;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderLogService;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.service.SynOrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.util.OrderConstants;
import com.ningpai.util.PageBean;

/**
 * e店宝订单控制器
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月22日10:00:55
 */
@Controller
public class SynOrderController {

    /**
     * Spring注入OrderService
     */
    @Resource(name = "OrderService")
    private OrderService orderService;

    /**
     * Spring注入
     */
    @Resource(name = "OrderLogService")
    private OrderLogService orderLogService;

    /**
     * Spring注入
     */
    @Resource(name = "SynOrderService")
    private SynOrderService synOrderService;

    /**
     * Spring注入
     */
    @RequestMapping("testOrder")
    public void testOrder(Long orderId) {
        synOrderService.SynOrder(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param order
     *            订单信息
     * @param pageBean
     * @param request
     * @return ModelAndView
     */
    @RequestMapping("/ordeedbplist")
    public ModelAndView orderEList(Order order, PageBean pageBean, HttpServletRequest request) {
        // 设值url
        pageBean.setUrl("ordeedbplist.htm");
        // 设值订单状态
        order.setOrderStatus("1");
        String token = request.getSession().getAttribute(CSRFTokenManager.CSRF_TOKEN_FOR_SESSION_ATTR_NAME).toString();
        // 返回页面
        return new ModelAndView("jsp/edb/orderlist", "pageBean", orderService.searchOrderList(order, pageBean)).addObject("order", order).addObject("sx", token);
    }

    /**
     * 订单e店宝重新导入
     * 
     * @param orderId
     *            订单id
     * @param eId
     * @return
     */
    @RequestMapping("syssubmitorder")
    @ResponseBody
    public int sysSubmitOrder(Long orderId, Long eId) {
        return synOrderService.upLog(orderId, eId);
    }

    /**
     * 根据订单id和订单状态更新订单
     * 
     * @param orderId
     *            订单id{@link java.lang.Long}
     * @param orderStatus
     *            订单状态
     * @return ModelAndView
     */
    @RequestMapping("modifyOrderStatus")
    public ModelAndView modifyOrderByKey(Long orderId, String orderStatus, HttpServletRequest request) {
        // 修改订单信息状态根据Id
        int count = orderService.modifyOrderByKey(orderId, orderStatus);
        // 是否修改成功
        if (count > 0) {
            // 执行插入操作
            orderLogService.insertSelective(null, orderId, request.getSession().getAttribute(CustomerConstantStr.NAME).toString(), "6");
            // 执行同步订单操作
            synOrderService.SynOrder(orderId);
            return new ModelAndView(new RedirectView(OrderConstants.INITORDER)).addObject(ConstantUtil.ORDERID, orderId);
        }
        return null;
    }

    /**
     * 根据订单编号查询E店宝的订单状态并修改
     * 
     * @param orderId
     *            订单编号
     * @return int
     */
    @RequestMapping("getorderstatus")
    @ResponseBody
    public int getOrderStatus(Long[] orderId) {
        // 获取E店宝订单信息并修改当前订单信息
        return synOrderService.getOrder(orderId);
    }

    /**
     * 根据分页参数查询列表
     * 
     * @param pageBean
     * @return ModelAndView
     */
    @RequestMapping("selectordereloglist")
    public ModelAndView selectOrderELogList(PageBean pageBean) {
        // 设定url值
        pageBean.setUrl("selectordereloglist.htm");
        // 返回页面
        return new ModelAndView("jsp/edb/ordereloglist").addObject("pageBean", synOrderService.selectOrderELogList(pageBean));
    }
}
