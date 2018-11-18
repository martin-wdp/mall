/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.common.util.wxap.RequestHandler;
import com.ningpai.common.util.wxap.ResponseHandler;
import com.ningpai.common.util.wxap.util.TenpayUtil;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.logger.util.OperaLogUtil;
import com.ningpai.m.common.service.SeoService;
import com.ningpai.m.order.service.OrderOtherMPayService;
import com.ningpai.m.order.util.OrderURL;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderOtherPayService;
import com.ningpai.order.service.OrderService;
import com.ningpai.other.util.CustomerConstantStr;
import com.ningpai.system.service.PayService;
import com.ningpai.util.MyLogger;

/**
 * 多人代付
 * 
 * @author NINGPAI-LIH
 * @since 2014年10月9日10:39:12
 * 
 */
@Controller
public class OrderOtherController {

    private static final MyLogger LOGGER = new MyLogger(OrderOtherController.class);

    private static final String LOGGERINFO1 = "订单已支付";
    private static final String MESSAGE = "message";
    private static final String ORDER = "order";
    private static final String ISO_8859_1 = "ISO-8859-1";

    @Resource(name = "OrderOtherPayService")
    private OrderOtherPayService orderOtherPayService;

    @Resource(name = "OrderOtherPayScheduleService")
    private OrderOtherPayScheduleService orderOtherPayScheduleService;

    @Resource(name = "OrderService")
    private OrderService orderService;

    @Resource(name = "payService")
    PayService payService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "SeoService")
    private SeoService seoService;

    @Resource(name = "OrderOtherMPayService")
    private OrderOtherMPayService orderOtherMPayService;

    /**
     * 代付页面
     * 
     * @param otherPay
     *            代付信息
     * @param orderId
     *            代付订单id
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/queryother")
    public ModelAndView queryOther(OrderOtherPay otherPay, Long orderId, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(ConstantUtil.UTF);
        Order order = orderService.orderDetail(orderId);
        if (!"0".equals(order.getOrderStatus())) {
            return new ModelAndView(new RedirectView(OrderURL.ERRORPAGE)).addObject(MESSAGE, LOGGERINFO1);
        }
        if (!order.getCustomerId().equals((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID))) {
            // 用户id不同
            return new ModelAndView(OrderURL.ERRORPAGE).addObject(MESSAGE, "用户不匹配");
        }
        ModelAndView mav = new ModelAndView(OrderURL.OTHERPAY).addObject(ORDER, order).addObject("customer", customerServiceMapper.selectByPrimaryKey(order.getCustomerId()));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 代付页面
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/otherPay")
    public ModelAndView otherPay(OrderOtherPay otherPay, Long orderId, HttpServletRequest request, String status) throws UnsupportedEncodingException {
        request.setCharacterEncoding(ConstantUtil.UTF);
        Long custId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (custId == null) {
            request.getSession().setAttribute("otherPayUrl", "otherpay-" + orderId + ".html");
            return new ModelAndView("redirect:/getwxcode2.htm");
        }
        // 获取订单信息
        Order order = orderService.getPayOrder(orderId);
        // 若果订单已经支付，则跳到支付已完成页面
        if (!"0".equals(order.getOrderStatus())) {
            return new ModelAndView(new RedirectView(OrderURL.ERRORPAGE)).addObject(MESSAGE, LOGGERINFO1);
        }
        if (otherPay.getOrderCode() != null) {
            orderOtherMPayService.updateOrderOtherSingle(otherPay, custId, request);
        }
        ModelAndView mav = null;
        if (status != null && "1".equals(status)) {
            // 跳到用户页面
            mav = new ModelAndView("otherpay/single_pay_step1");
        } else {
            // 跳到代付页面
            mav = new ModelAndView("otherpay/information2");
        }
        mav.addObject("map", orderOtherMPayService.selectOtherPaySingle(orderId));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 代付页面
     * 
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("/otherpaypromptly")
    public ModelAndView otherPayPromptly(OrderOtherPaySchedule otherPay, Long orderId, HttpServletRequest request, String status) throws UnsupportedEncodingException {
        request.setCharacterEncoding(ConstantUtil.UTF);
        Order order = orderService.getPayOrder(orderId);
        // 若果订单已经支付，则跳到支付已完成页面
        if (!"0".equals(order.getOrderStatus())) {
            return new ModelAndView(new RedirectView(OrderURL.ERRORPAGE)).addObject(MESSAGE, LOGGERINFO1);
        }
        Long custId = (Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID);
        if (custId == null) {
            // 微信登陆
            request.getSession().setAttribute("otherPayUrl", "otherpay-" + orderId + ".html");
            return new ModelAndView("redirect:/getwxcode2.htm");
        }
        if (otherPay.getOrderCode() != null) {
            // 修改或者新增代付信息
            orderOtherMPayService.updateOrderOtherPay(otherPay, request);
        }
        ModelAndView mav = null;
        if (status != null && "1".equals(status)) {
            // 跳到用户页面
            mav = new ModelAndView("otherpay/multi_pay_step");
        } else {
            // 跳到代付页面
            mav = new ModelAndView("otherpay/multi_pay_step2");
        }
        mav.addObject("map", orderOtherMPayService.selectOtherPay(orderId));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 去支付
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/gomultipay")
    public ModelAndView goMultiPay(Long orderId) {
        ModelAndView mav = null;
        mav = new ModelAndView("otherpay/information");
        mav.addObject("map", orderOtherMPayService.selectOtherPay(orderId));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 单人代付
     * 
     * @param orderOtherPay
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("inertotherpaysingle")
    public ModelAndView payOrderSingle(OrderOtherPay orderOtherPay, Long payId, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(ConstantUtil.UTF);
        try {
            orderOtherPay.setOrderPayCustid((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            orderOtherPay.setOrderPayName(new String(orderOtherPay.getOrderPayName().getBytes(ISO_8859_1), ConstantUtil.UTF));
            orderOtherPay.setOrderPayRemark(new String(orderOtherPay.getOrderPayRemark().getBytes(ISO_8859_1), ConstantUtil.UTF));
        } catch (UnsupportedEncodingException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }

        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        String s = orderOtherPay.getOrderCode() + strReq;
        orderOtherPay.setOrderPayCode(s);
        orderOtherPay.setOrderCreateTime(new Date());
        orderOtherPay.setOrderPayStatus("1");
        ModelAndView mav = new ModelAndView();
        Order order = orderService.getPayOrderByCode(orderOtherPay.getOrderCode());
        orderOtherPay.setOrderPayCode(order.getOrderCode());
        OrderOtherPaySchedule orderOtherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(orderOtherPay.getOrderCode());
        if (orderOtherPaySchedule != null) {
            order.setOrderPrice(orderOtherPaySchedule.getOrderResiduePrice());
        }
        if (!"0".equals(order.getOrderStatus())) {
            return new ModelAndView(new RedirectView(OrderURL.ERRORPAGE)).addObject(MESSAGE, LOGGERINFO1);
        }
        // 获取openid
        String ip = request.getRemoteAddr();
        // 订单信息
        mav.addObject(ORDER, order);
        // ip地址
        mav.addObject("ip", ip);
        // 订单id
        mav.addObject(ConstantUtil.ORDERID, order.getOrderId());
        // 商品名称
        mav.addObject("goodsInfoName", "测试");
        // 订单价格
        mav.addObject("payPrice", order.getOrderPrice().toString().replace(".", ""));
        // 回调函数
        mav.addObject("callBackUrl", "http://shop.ningpai.com/mobile/wxpaysuc.htm");
        mav.setViewName("pay/pay2");
        orderOtherPayService.updateOtherPay(orderOtherPay);
        return mav;
    }

    /**
     * 多人代付支付
     * 
     * @param orderOtherPay
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping("inertotherpay")
    public ModelAndView payOrder(OrderOtherPay orderOtherPay, Long payId, HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding(ConstantUtil.UTF);
        try {
            orderOtherPay.setOrderPayCustid((Long) request.getSession().getAttribute(CustomerConstantStr.CUSTOMERID));
            orderOtherPay.setOrderPayName(new String(orderOtherPay.getOrderPayName().getBytes(ISO_8859_1), ConstantUtil.UTF));
            orderOtherPay.setOrderPayRemark(new String(orderOtherPay.getOrderPayRemark().getBytes(ISO_8859_1), ConstantUtil.UTF));
        } catch (UnsupportedEncodingException e) {
            Customer cust = (Customer) request.getSession().getAttribute("cust");
            OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
        }
        Boolean bool = orderOtherPayService.isNoPay(orderOtherPay);
        if (bool) {
            return new ModelAndView(new RedirectView(OrderURL.ERRORPAGE)).addObject(MESSAGE, "当前正在支付过多，请稍等");
        }
        String currTime = TenpayUtil.getCurrTime();
        // 8位日期
        String strTime = currTime.substring(8, currTime.length());
        // 四位随机数
        String strRandom = TenpayUtil.buildRandom(4) + "";
        // 10位序列号,可以自行调整。
        String strReq = strTime + strRandom;
        String s = orderOtherPay.getOrderCode() + strReq;
        orderOtherPay.setOrderPayCode(s);
        orderOtherPay.setOrderCreateTime(new Date());
        orderOtherPay.setOrderPayStatus("1");
        ModelAndView mav = new ModelAndView();
        Order order = orderService.getPayOrderByCode(orderOtherPay.getOrderCode());
        // 获取openid
        String ip = request.getRemoteAddr();
        // 订单信息
        mav.addObject(ORDER, order);
        // ip地址
        mav.addObject("ip", ip);
        mav.addObject("orderPayCode", orderOtherPay.getOrderPayCode());
        // 订单id
        mav.addObject(ConstantUtil.ORDERID, order.getOrderId());
        // 商品名称
        mav.addObject("goodsInfoName", "测试");
        // 订单价格
        mav.addObject("payPrice", orderOtherPay.getOrderPayPrice().toString().replace(".", ""));
        // 回调函数
        mav.addObject("callBackUrl", "http://shop.ningpai.com/mobile/otherpaywxsucess.htm");
        mav.setViewName("pay/pay3");
        orderOtherPayService.insertOtherPay(orderOtherPay);
        return mav;
    }

    /**
     * 微信回调
     * 
     * @param request
     */
    @RequestMapping("/otherpaywxsucess")
    public void otherPayWxSucess(HttpServletRequest request, HttpServletResponse response) {
        ResponseHandler resHandler = new ResponseHandler(request, response);
        resHandler.setKey("9b65423e573b0baa90e882e7158270ce");
        resHandler.setKey("LTD5WYXxE8xbFAghepFKezy04NaqXDojDEeFFV5ZgFfhVXX8zNF98yS4kvsTGhlTx2wGRU3JJsEIdnawrAX0AG06dRQ5VaFMyfndnO6ZcremCphgFlJhKwg0dvCWru2e");
        // 创建请求对象
        RequestHandler queryReq = new RequestHandler(null, null);
        queryReq.init();
        if (resHandler.isValidSign()) {

            // 商户订单号
            String outTradeNo = resHandler.getParameter("out_trade_no");
            // 财付通订单号
            // String transaction_id = resHandler
            // .getParameter("transaction_id");
            // 金额,以分为单位
            // String total_fee = resHandler.getParameter("total_fee");
            // 支付结果
            // 查询代付信息
            OrderOtherPay otherPay = orderOtherPayService.selectOthertByOrderPayCode(outTradeNo);
            otherPay.setOrderPayTime(new Date());
            otherPay.setOrderPayStatus("2");
            orderOtherPayService.updateOtherPay(otherPay);
            // 查询代付信息 结束

            // 查询代付进度
            OrderOtherPaySchedule otherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(otherPay.getOrderCode());
            otherPaySchedule.setOrderPayStatus("1");
            orderOtherMPayService.updateOrderOtherPay(otherPaySchedule, request);

            // 判断订单是否完成
            orderOtherPayScheduleService.payOther(otherPaySchedule, otherPay);

            try {
                resHandler.sendToCFT("success");
            } catch (IOException e) {
                Customer cust = (Customer) request.getSession().getAttribute("cust");
                OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            }
        } else {// sha1签名失败
            try {
                resHandler.sendToCFT("fail");
            } catch (IOException e) {
                Customer cust = (Customer) request.getSession().getAttribute("cust");
                OperaLogUtil.addOperaException(cust.getCustomerUsername(), e, request);
            }
        }

    }

    /**
     * 支付宝支付成功
     * 
     * @param request
     * @param agr1
     * @return ModelAndView
     */
    @RequestMapping("/otherpaysuccess")
    public ModelAndView otherPaySuccess(HttpServletRequest request, HttpServletResponse agr1, HttpServletResponse response) {
        Map<String, String> params = new HashMap<String, String>();
        // 获取返回的Map
        Map<?, ?> requestParams = request.getParameterMap();
        // 循环取出
        for (@SuppressWarnings("rawtypes")
        Map.Entry entrySet : requestParams.entrySet()) {
            String[] values = (String[]) entrySet.getValue();
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr.concat(values[i]) : valueStr.concat(values[i]).concat(",");
            }
            try {
                valueStr = new String(valueStr.getBytes(ISO_8859_1), ConstantUtil.UTF);
                // 根据名字 和value 存入Map
                params.put(entrySet.getKey().toString(), valueStr);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("类型转换错误" + e);
            }
        }

        // 订单号
        String orderCode = request.getParameter("out_trade_no");
        OrderOtherPay otherPay = orderOtherPayService.selectOthertByOrderPayCode(orderCode);
        otherPay.setOrderPayTime(new Date());
        otherPay.setOrderPayStatus("2");
        orderOtherPayService.updateOtherPay(otherPay);
        // 查询代付信息 结束

        // 查询代付进度
        OrderOtherPaySchedule otherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(otherPay.getOrderCode());
        orderOtherPayScheduleService.payOther(otherPaySchedule, otherPay);
        return new ModelAndView(new RedirectView("otherpaypromptly.htm")).addObject(ConstantUtil.ORDERID, 2387);
    }

    /**
     * 测试
     *
     * */
    @RequestMapping("/test")
    public void test(String orderCode) {
        // 订单号
        OrderOtherPay otherPay = orderOtherPayService.selectOthertByOrderPayCode(orderCode);
        otherPay.setOrderPayTime(new Date());
        otherPay.setOrderPayStatus("2");
        orderOtherPayService.updateOtherPay(otherPay);
        // 查询代付信息 结束

        // 查询代付进度
        OrderOtherPaySchedule otherPaySchedule = orderOtherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(otherPay.getOrderCode());
        orderOtherPayScheduleService.payOther(otherPaySchedule, otherPay);
    }

    /**
     * 单人代付成功
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/otherpaysinglesuccess")
    public ModelAndView otherPaySingleSuccess(Long orderId) {
        ModelAndView mav = new ModelAndView("otherpay/single_pay_success").addObject("map", orderOtherMPayService.selectOtherPaySingle(orderId));
        return seoService.getCurrSeo(mav);
    }

    /**
     * 多人代付成功
     * 
     * @param orderId
     * @return
     */
    @RequestMapping("/orderpaysuccess")
    public ModelAndView orderPaySuccess(Long orderId) {
        ModelAndView mav = new ModelAndView("otherpay/multi_pay_success").addObject("map", orderOtherMPayService.selectOtherPay(orderId));
        return seoService.getCurrSeo(mav);
    }

}
