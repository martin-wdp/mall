package com.ningpai.site.order.service;

import com.ningpai.order.bean.Order;
import com.ningpai.system.bean.Pay;

/**
 * 支付用service
 * @author lih
 * @version 2.0
 * @since 2015/8/10 15:04
 */
public interface IPayService {

    /**
     * 支付宝支付
     * 1、 获取订单信息
     * 2、 根据订单信息以及支付信息生成签名
     * 3、 返回支付地址
     * @param  orderCount 单次需要支付的订单数量
     * @param p 支付宝配置信息
     * @param order 订单信息（单笔订单支付是需要用到）
     * @author lih @since 2015年8月10日15:58:11
     * @return 支付地址
     */
    String  getAlipay(String ip,Pay p, Order order,String goodsName,Long orderCount);
}
