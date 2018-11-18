/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.m.order.service.OrderPayService;
import com.ningpai.m.weixin.util.WXSendMSG;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.dao.OrderExpressMapper;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.order.service.OrderService;

/**
 * 订单提交service
 *
 * @author NINGPAI-LIH
 * @since 2014年9月4日20:45:30
 */
@Service("OrderPayService")
public class OrderPayServiceImpl extends BasicSqlSupport implements OrderPayService {

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "OrderService")
    private OrderService orderService;

    @Resource(name = "OrderMapper")
    private OrderMapper orderMapper;

    @Resource(name = "OrderGoodsMapper")
    private OrderGoodsMapper orderGoodsMapper;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    @Resource(name = "OrderExpressMapper")
    private OrderExpressMapper orderExpressMapper;

    @Resource(name = "OrderGoodsInfoCouponMapper")
    private OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper;

    @Resource(name = "CouponService")
    private CouponService couponService;

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderPayService#queryGoodsProducts(java.lang
     * .Long)
     */
    @Override
    public Order queryGoodsProducts(Long orderId) {
        // 更新订单状态为已查看
        orderMapper.updateOrdreNewStauts(orderId);
        // 查询订单详细信息
        Order order = orderMapper.orderDetail(orderId);
        // 查询订单商品信息
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectOrderGoodsList(orderId);
        // 查询订单物流信息
        order.setOrderExpress(orderExpressMapper.selectOrderExpress(orderId));
        // 查询订单商品参与的商品促销信息
        if (orderGoodsList != null && !orderGoodsList.isEmpty()) {
            for (int i = 0; i < orderGoodsList.size(); i++) {
                OrderGoods orderGoods = orderGoodsList.get(i);
                // 查询货品详细信息
                orderGoods.setGoodsProductVo(goodsProductService.queryViewVoByProductId(orderGoods.getGoodsInfoId()));
                // 判断货品是否参加了商品促销
                if (orderGoods.getGoodsMarketingId() != null && !"".equals(orderGoods.getGoodsMarketingId().toString())) {
                    // 查询促销信息
                    orderGoods.setMarketing(marketingService.marketingDetail(orderGoods.getGoodsMarketingId()));
                    // 判断是否有赠送优惠券
                    if (orderGoods.getHaveCouponStatus() != null && "1".equals(orderGoods.getHaveCouponStatus())) {
                        // 有送优惠券
                        // 查询赠送的优惠券信息
                        List<OrderGoodsInfoCoupon> orderGoodsInfoCouponList = orderGoodsInfoCouponMapper.selectOrderGoodsInfoCoupon(orderGoods.getOrderGoodsId());
                        // 查询优惠券详细
                        if (orderGoodsInfoCouponList != null && !orderGoodsInfoCouponList.isEmpty()) {
                            for (int j = 0; j < orderGoodsInfoCouponList.size(); j++) {
                                orderGoodsInfoCouponList.get(j).setCoupon(couponService.searchCouponById(orderGoodsInfoCouponList.get(j).getCouponId()));
                            }
                            orderGoods.setOrderGoodsInfoCouponList(orderGoodsInfoCouponList);
                        }

                    }
                }
            }
            order.setOrderGoodsList(orderGoodsList);
        }

        return order;
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.m.order.service.OrderPayService#sendOrderRe(java.lang.Long)
     */
    @Override
    public void sendOrderRe(Order order, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paraMap = new HashMap<String, Object>();
        String goodsName = orderService.queryGoodsInfoName(order.getOrderId());
        Customer customer = customerServiceMapper.queryCustomerInfo(order.getCustomerId());
        // 订单编号
        paraMap.put("orderNo", order.getOrderCode());
        // 订单价格
        paraMap.put("orderPrice", order.getOrderPrice());
        // 商品名称
        paraMap.put("goodsName", goodsName);
        // 微信id
        paraMap.put(ConstantUtil.OPENID, customer.getCustomerUsername());
        // 订单编号
        paraMap.put(ConstantUtil.ORDERID, order.getOrderId());
        // 推送信息
        WXSendMSG.sendWxMsgForOrderCreate(paraMap, request, response);
    }
}
