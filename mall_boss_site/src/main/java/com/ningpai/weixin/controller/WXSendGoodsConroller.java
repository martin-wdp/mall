/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.weixin.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.weixin.util.WXSendMSG;

/**
 * 发货通知
 * 
 * @author NINGPAI-LIH
 * @since 2014年9月11日16:22:12
 * 
 */
@Controller
public class WXSendGoodsConroller {

    @Resource(name = "OrderService")
    private OrderService orderService;
    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    /**
     * 微信发货通知
     * 
     * @param orderId
     *            订单id
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/sendgoodsnotify")
    @ResponseBody
    public int sendGoodsNotify(Long orderId, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        // 获取用户编号
        Order order = orderService.getPayOrder(orderId);

        // 获取用户信息
        CustomerAllInfo cust = customerServiceMapper.selectByPrimaryKey(order.getCustomerId());
        // 订单编号
        paraMap.put("orderNo", order.getOrderCode());
        // 用户openId
        paraMap.put(ConstantUtil.OPENID, cust.getCustomerUsername());
        // 用户昵称
        paraMap.put("customerNickName", cust.getCustomerNickname());
        // 订单编号
        paraMap.put(ConstantUtil.ORDERID, order.getOrderId());
        // 用户状态
        paraMap.put("orderStatus", "已发货");
        WXSendMSG.sendWxMsgForChanOrderStatus(paraMap, request, response);
        return 0;
    }
}
