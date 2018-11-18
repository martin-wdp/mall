package com.ningpai.site.order.service.impl;

import com.ningpai.common.util.alipay.config.AlipayConfig;
import com.ningpai.common.util.alipay.util.AlipaySubmit;
import com.ningpai.order.bean.Order;
import com.ningpai.site.order.service.IPayService;
import com.ningpai.site.order.service.SiteOrderService;
import com.ningpai.system.bean.Pay;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付实现类 修改方法时请注意（重要）qaq
 *
 * @author lih
 * @version 2.0
 * @since 2015/8/10 15:26
 */
@Service("IPayService")
public class PayServiceImpl implements IPayService {

    // site订单service
    @Resource(name = "SiteOrderService")
    private SiteOrderService siteOrderService;

    private Order checkOrder(Order order) {
        // 用来存储订单的总价格
        BigDecimal orderSumPrice = new BigDecimal(0.00);
        // 飞快验证主订单号
        if (null != order.getOrderOldCode()) {
            // 根据订单那的主订单号 查询主订单号下面所有的小订单信息
            List<Order> orders = siteOrderService.getPayOrderByOldCode(order.getOrderOldCode());
            // 如果需要支付的订单数大于1
            if (null != orders && orders.size() > 1) {
                for (int i = 0; i < orders.size(); i++) {
                    // 获取单个的子订单信息
                    Order childOrder = orders.get(i);
                    // 如果订单的支付方式是货到付款 就不计算价格
                    if (!"0".equals(childOrder.getOrderLinePay())) {
                        // 把子订单的信息进行相加
                        orderSumPrice = orderSumPrice.add(childOrder.getOrderPrice());
                    }
                }
                // 把子订单的价格相加重新赋值给order对象 用于支付宝支付 不需持久化到数据库
                order.setOrderPrice(orderSumPrice);
                // 把主订单号赋值给订单单号 也是用于支付宝支付 不需要持久化到数据库
                order.setOrderCode(order.getOrderOldCode());
            }
        }
        return order;
    }

    /**
     * 支付宝支付 1、 获取订单信息 2、 根据订单信息以及支付信息生成签名 3、 返回支付地址
     *
     * @param p
     *            支付宝配置信息
     * @param order
     *            订单信息（单笔订单支付是需要用到）
     * @author lih @since 2015年8月10日15:58:11
     * @return 支付地址
     */
    @Override
    public String getAlipay(String ip,Pay p, Order order, String goodsName, Long orderCount) {
        AlipayConfig.partner = p.getApiKey();
        AlipayConfig.key = p.getSecretKey();
        AlipayConfig.seller_email = p.getPayAccount();
        Order order1 = order;

        // 支付类型
        String paymentType = "1";

        // 单次支付的订单大于在一笔以上
        String notifyUrl ="";
        // 单次支付的订单大于在一笔以上
        if (null != orderCount&&orderCount!=1) {
            order1 = this.checkOrder(order);
            notifyUrl = p.getPayUrl() + "paysucccessybmulti.htm";
        }else{
            notifyUrl = p.getPayUrl() + "paysucccessyb.htm";
        }


        // 必填，不能修改
        // 服务器异步通知页面路径

        // 需http://格式的完整路径，不能加?id=123这类自定义参数

        // 页面跳转同步通知页面路径
        String returnUrl = p.getBackUrl();
        // 需http://格式的完整路径，不能加?id=123这类自定义参数，不能写成http://localhost/

        // 商户订单号
        String outTradeNo = new String(order1.getOrderCode());
        // 商户网站订单系统中唯一订单号，必填

        // 订单名称
        String subject = new String(goodsName);
        // 必填

        // 付款金额
        String totalFee = new String(order1.getOrderPrice().toString());
        // 必填

        // 订单描述

        String body = new String("网购订单");
        // 商品展示地址
        String showUrl = new String("");
        // 需以http://开头的完整路径，例如：http://www.商户网址.com/myorder.html

        // 防钓鱼时间戳
        String antiPhishingKey = "";
        // 若要使用请调用类文件submit中的query_timestamp函数

        // 客户端的IP地址
        String exterInvokeIp = ip;

        // 非局域网的外网IP地址，如：221.0.0.1

        // ////////////////////////////////////////////////////////////////////////////////

        // 把请求参数打包成数组
        Map<String, String> sParaTemp = new HashMap<String, String>();
        sParaTemp.put("service", "create_direct_pay_by_user");
        sParaTemp.put("partner", AlipayConfig.partner);
        sParaTemp.put("seller_email", AlipayConfig.seller_email);
        sParaTemp.put("_input_charset", AlipayConfig.input_charset);
        sParaTemp.put("payment_type", paymentType);
        sParaTemp.put("notify_url", notifyUrl);
        sParaTemp.put("return_url", returnUrl);
        sParaTemp.put("out_trade_no", outTradeNo);
        sParaTemp.put("subject", subject);
        sParaTemp.put("total_fee", totalFee);
        sParaTemp.put("body", body);
        sParaTemp.put("show_url", showUrl);
        sParaTemp.put("anti_phishing_key", antiPhishingKey);
        sParaTemp.put("exter_invoke_ip", exterInvokeIp);

        // 建立请求
        return AlipaySubmit.buildRequest(sParaTemp, "get", "确认");

    }

}
