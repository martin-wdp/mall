/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;

import com.ningpai.util.MyLogger;
import org.springframework.stereotype.Service;

import com.ningpai.common.util.tenpay.RequestHandler;
import com.ningpai.common.util.tenpay.client.ClientResponseHandler;
import com.ningpai.common.util.tenpay.client.TenpayHttpClient;
import com.ningpai.common.util.tenpay.util.MD5Util;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.service.OrderLogService;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderOtherPayService;
import com.ningpai.order.service.OrderService;

/**
 * 财付通退款
 *
 * @author NINGPAI-LIH
 * @since 时间
 */
@Service("RefundmentUtil")
public class RefundmentUtil {

    /**
     * 日志
     * */
    public static final MyLogger LOGGER = new MyLogger(RefundmentUtil.class);

    // spring注入
    @Resource(name = "OrderOtherPayService")
    private OrderOtherPayService orderOtherPayService;
    // spring注入
    @Resource(name = "OrderOtherPayScheduleService")
    private OrderOtherPayScheduleService orderOtherPayScheduleService;
    // spring注入
    @Resource(name = "OrderService")
    private OrderService orderService;
    // spring注入
    @Resource(name = "OrderLogService")
    private OrderLogService orderLogService;

    /**
     * 退款
     */
    public void service() {
        List<OrderOtherPaySchedule> schedules = orderOtherPayScheduleService.queryOrderOtherPayRefund();
        for (OrderOtherPaySchedule orderOtherPaySchedule : schedules) {
            List<OrderOtherPay> orderOtherPays = orderOtherPayService.queryOrderPayRefund(orderOtherPaySchedule.getOrderCode());
            if (orderOtherPays == null || orderOtherPays.isEmpty()) {
                Order order = orderService.getPayOrderByCode(orderOtherPaySchedule.getOrderCode());
                if ("0".equals(order.getOrderStatus())) {
                    orderService.updateSetCargoStatusByOrderId(order.getOrderId(), "4");
                }
            } else {
                for (OrderOtherPay orderOtherPay : orderOtherPays) {
                    try {
                        refundment(orderOtherPay.getOrderPayCode(), orderOtherPay.getOrderPayPrice().toString().replace(".", ""), orderOtherPay.getOrderCode());
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.error("",e);
                    } catch (Exception e) {
                        LOGGER.error("",e);
                    }
                }
            }
        }
    }

    /**
     * @param orderCode
     * @param orderPrice
     * @param code
     * @throws Exception
     */
    public void refundment(String orderCode, String orderPrice, String code) throws Exception {
        Order order = orderService.getPayOrderByCode(code);
        // 商户号
        String partner = "1220343401";
        // 密钥
        String key = "9b65423e573b0baa90e882e7158270ce";
        // 创建查询请求对象
        RequestHandler reqHandler = new RequestHandler(null, null);
        // 通信对象
        TenpayHttpClient httpClient = new TenpayHttpClient();
        // 应答对象
        ClientResponseHandler resHandler = new ClientResponseHandler();

        // -----------------------------
        // 设置请求参数
        // -----------------------------
        reqHandler.init();
        reqHandler.setKey(key);
        reqHandler.setGateUrl("https://mch.tenpay.com/refundapi/gateway/refund.xml");

        // -----------------------------
        // 设置接口参数
        // -----------------------------
        reqHandler.setParameter("service_version", "1.1");
        reqHandler.setParameter("partner", partner);
        reqHandler.setParameter("out_trade_no", orderCode);
        reqHandler.setParameter("out_refund_no", orderCode);
        reqHandler.setParameter("total_fee", orderPrice);
        reqHandler.setParameter("refund_fee", orderPrice);
        // 操作员账号
        reqHandler.setParameter("op_user_id", "1220343401001");
        // 操作员密码,MD5处理
        reqHandler.setParameter("op_user_passwd", MD5Util.MD5Encode("oacat520", "GBK"));
        reqHandler.setParameter("recv_user_id", "");
        reqHandler.setParameter("reccv_user_name", "");
        // -----------------------------
        // 设置通信参数
        // -----------------------------
        // 设置请求返回的等待时间
        httpClient.setTimeOut(5);
        // 设置ca证书
        httpClient.setCaInfo(new File(RefundmentUtil.class.getClassLoader().getResource("").getPath() + "/com/ningpai/m/order/util/cacert.pem"));
        // 设置个人(商户)证书
        httpClient.setCertInfo(new File(RefundmentUtil.class.getClassLoader().getResource("").getPath() + "/com/ningpai/m/order/util/1220343401_20140804205129.pfx"), "1220343401");
        // 设置发送类型POST
        httpClient.setMethod("POST");

        // 设置请求内容
        String requestUrl = reqHandler.getRequestURL();
        httpClient.setReqContent(requestUrl);
        String rescontent = "null";

        // 后台调用
        if (httpClient.call()) {
            // 设置结果参数
            rescontent = httpClient.getResContent();
            resHandler.setContent(rescontent);
            resHandler.setKey(key);

            // 获取返回参数
            String retcode = resHandler.getParameter("retcode");

            // 判断签名及结果
            if (resHandler.isTenpaySign() && "0".equals(retcode)) {
                /*
                 * 退款状态 refund_status 4，10：退款成功。 3，5，6：退款失败。 8，9，11:退款处理中。 1，2:
                 * 未确定，需要商户原退款单号重新发起。
                 * 7：转入代发，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，资金回流到商户的现金帐号
                 * ，需要商户人工干预，通过线下或者财付通转账的方式进行退款。
                 */
                String refundStatus = resHandler.getParameter("refund_status");
                String outRefundNo = resHandler.getParameter("out_refund_no");
                if ("4".equals(refundStatus) || "10".equals(refundStatus)) {
                    // 退款完成
                    OrderOtherPay otherPay = orderOtherPayService.selectOthertByOrderPayCode(outRefundNo);
                    otherPay.setOrderPayStatus("3");
                    orderOtherPayService.updateOtherPay(otherPay);
                    orderLogService.insertSelective("用户：" + otherPay.getOrderPayName() + "的退款已完成", order.getOrderId(), "用户退款", "5");
                } else if ("8".equals(refundStatus) || "9".equals(refundStatus) || "11".equals(refundStatus)) {
                    // 退款处理中
                    OrderOtherPay otherPay = orderOtherPayService.selectOthertByOrderPayCode(outRefundNo);
                    if (otherPay.getOrderPayName() == null) {
                        otherPay.setOrderPayName("请叫我好心人");
                    }
                    if ("4".equals(otherPay.getOrderPayStatus())) {
                        otherPay.setOrderPayStatus("4");
                        orderOtherPayService.updateOtherPay(otherPay);
                        orderLogService.insertSelective("用户：" + otherPay.getOrderPayName() + "的退款正在处理中。", order.getOrderId(), "用户退款", "5");
                    }
                }
            } else {
                orderLogService.insertSelective("验证签名失败或业务错误", order.getOrderId(), "用户退款", "5");
                orderLogService.insertSelective("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo(), order.getOrderId(), "用户退款", "5");
            }
        } else {
            orderLogService.insertSelective("后台调用通信失败", order.getOrderId(), "用户退款", "5");
            orderLogService.insertSelective("http res:" + httpClient.getResponseCode() + "," + httpClient.getErrInfo(), order.getOrderId(), "用户退款", "5");
        }

    }

}
