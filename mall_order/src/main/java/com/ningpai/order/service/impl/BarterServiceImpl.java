/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.common.util.DateUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.BarterOrder;
import com.ningpai.order.bean.Order;
import com.ningpai.order.bean.OrderCoupon;
import com.ningpai.order.bean.OrderGift;
import com.ningpai.order.bean.OrderGoods;
import com.ningpai.order.bean.OrderGoodsInfoCoupon;
import com.ningpai.order.bean.OrderGoodsInfoGift;
import com.ningpai.order.bean.OrderMarketing;
import com.ningpai.order.dao.BarterOrderMapper;
import com.ningpai.order.dao.OrderCouponMapper;
import com.ningpai.order.dao.OrderExpressMapper;
import com.ningpai.order.dao.OrderGiftMapper;
import com.ningpai.order.dao.OrderGoodsInfoCouponMapper;
import com.ningpai.order.dao.OrderGoodsInfoGiftMapper;
import com.ningpai.order.dao.OrderGoodsMapper;
import com.ningpai.order.dao.OrderMapper;
import com.ningpai.order.dao.OrderMarketingMapper;
import com.ningpai.order.service.BarterService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 换单业务实现层
 *
 * @author YANhb
 */

@Service("barterService")
public class BarterServiceImpl implements BarterService {

    // 货品信息业务层
    private GoodsProductService goodsProductService;
    // 订单接口
    private OrderMapper orderMapper;
    // 订单里的货品列表
    private OrderGoodsMapper orderGoodsMapper;
    // 查询订单的促销信息
    private OrderMarketingMapper orderMarketingMapper;
    // 促销信息的业务层
    private MarketingService marketingService;
    // 订单优惠劵信息
    private OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper;
    // 优惠劵接口
    private CouponService couponService;
    // 订单货品赠品信息
    private OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper;
    // 赠品接口
    private GiftService giftService;
    // 订单赠送优惠券接口
    private OrderCouponMapper orderCouponMapper;
    // 订单赠品接口
    private OrderGiftMapper orderGiftMapper;
    // 订单物流信息
    private OrderExpressMapper orderExpressMapper;

    /**
     * 注入换单数据层
     */
    @Resource
    private BarterOrderMapper barterOrderMapper;

    /**
     * 分页查询换单
     *
     * @param pageBean
     * @param barterOrder
     * @param startTime
     * @param endTime
     * @return
     */
    public PageBean queryBarterPageSize(PageBean pageBean, BarterOrder barterOrder, String startTime, String endTime) {
        barterOrder.setBackFlag("0");
        // 封装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(barterOrder);
        if (startTime != null && !"".equals(startTime)) {
            barterOrder.setBarterTime(DateUtil.stringToDate(startTime, null));
            paramMap.put("startTime", startTime);
        }
        // 结束时间
        if (endTime != null && !"".equals(endTime)) {
            paramMap.put("endTime", endTime);
        }
        // 计算总记录数
        pageBean.setRows(barterOrderMapper.selectBarterGetCount(paramMap));
        // 计算总页数
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : pageBean.getRows() / pageBean.getPageSize() + 1;
        // 如果总页数等于0，则设为1
        if (no == 0) {
            no = 1;
        }
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            pageBean.setObjectBean(barterOrder);
        }
        paramMap.put("startRowNum", pageBean.getStartRowNum());
        paramMap.put("endRowNum", pageBean.getEndRowNum());
        try {
            pageBean.setList(barterOrderMapper.selectBarterPageSize(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 批量删除换单
     *
     * @param barterOrderIds
     * @return
     */
    public int batchDelBarterOrder(Long[] barterOrderIds) {
        List<Long> list = new ArrayList<Long>();
        int count = 0;
        try {
            // 循环加入要删除的换单id
            for (int i = 0; i < barterOrderIds.length; i++) {
                list.add(barterOrderIds[i]);
            }
            count = barterOrderMapper.batchBarterOrder(list);
        } finally {
            list = null;
        }
        return count;
    }

    /**
     * 查询换单详情
     *
     * @param barterOrderId
     * @return
     */
    public BarterOrder queryBarterDetails(Long barterOrderId) {
        BarterOrder barterOrder = barterOrderMapper.selectBarterDetails(barterOrderId);
        if (barterOrder != null) {
            // 获取订单编号
            Long orderId = barterOrder.getOrderId();
            // 查询订单详细信息
            Order order = orderMapper.orderDetail(orderId);
            // 查询换单信息
            List<OrderGoods> orderGoods = orderGoodsMapper.selectBarterGoodList(barterOrder.getBarterOrderCode());
            // 查询订单物流信息
            order.setOrderExpress(orderExpressMapper.selectOrderExpress(orderId));
            // 查询订单商品参与的商品促销信息
            if (orderGoods != null && !orderGoods.isEmpty()) {
                for (int i = 0; i < orderGoods.size(); i++) {
                    OrderGoods goods = orderGoods.get(i);
                    // 查询货品详细信息
                    goods.setGoodsProductVo(goodsProductService.queryViewVoByProductId(goods.getGoodsInfoId()));
                    // 判断货品是否参加了商品促销
                    if (goods.getGoodsMarketingId() != null && goods.getGoodsMarketingId() > 0) {
                        // 查询促销信息
                        goods.setMarketing(marketingService.marketingDetail(goods.getGoodsMarketingId()));
                        // 判断是否有优惠券
                        if (goods.getHaveCouponStatus() != null && "1".equals(goods.getHaveCouponStatus())) {
                            // 有优惠券，查询赠送的优惠券信息
                            List<OrderGoodsInfoCoupon> orderGoodsInfoCouponsList = orderGoodsInfoCouponMapper.selectOrderGoodsInfoCoupon(goods.getOrderGoodsId());
                            if (orderGoodsInfoCouponsList != null && !orderGoodsInfoCouponsList.isEmpty()) {
                                for (int j = 0; j < orderGoodsInfoCouponsList.size(); j++) {

                                    orderGoodsInfoCouponsList.get(j).setCoupon(couponService.searchCouponById(orderGoodsInfoCouponsList.get(j).getCouponId()));
                                }
                                goods.setOrderGoodsInfoCouponList(orderGoodsInfoCouponsList);
                            }
                        }
                        // 判断是否赠送赠品
                        if (goods.getHaveGiftStatus() != null && "1".equals(goods.getHaveGiftStatus())) {
                            // 有送赠品
                            List<OrderGoodsInfoGift> orderGoodsInfoGiftList = orderGoodsInfoGiftMapper.selectOrderGoodsInfoGift(goods.getOrderGoodsId());
                            // 判断赠品不为空，查询详细赠品信息
                            if (orderGoodsInfoGiftList != null && !orderGoodsInfoGiftList.isEmpty()) {
                                for (int j = 0; j < orderGoodsInfoGiftList.size(); j++) {
                                    orderGoodsInfoGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGoodsInfoGiftList.get(j).getGiftId()));
                                }
                                goods.setOrderGoodsInfoGiftList(orderGoodsInfoGiftList);
                            }
                        }
                    }
                }
                order.setOrderGoodsList(orderGoods);
            }
            // 查询订单促销信息
            List<OrderMarketing> orderMarketingList = orderMarketingMapper.selectOrderMarketingList(orderId);
            // 查询订单商品促销是否有赠品与优惠券
            if (orderMarketingList != null && !orderMarketingList.isEmpty()) {
                for (int i = 0; i < orderMarketingList.size(); i++) {
                    OrderMarketing orderMarketing = orderMarketingList.get(i);
                    // 查询订单促销详细信息
                    orderMarketing.setMarketing(marketingService.marketingDetail(orderMarketing.getMarketingId()));
                    // 判断订单促销是否赠送优惠券
                    if (orderMarketing.getHaveCouponStatus() != null && "1".equals(orderMarketing.getHaveCouponStatus())) {
                        List<OrderCoupon> orderCouponsList = orderCouponMapper.selectOrderCoupon(orderMarketing.getMarketingId());
                        if (orderCouponsList != null && !orderCouponsList.isEmpty()) {
                            for (int j = 0; j < orderCouponsList.size(); j++) {
                                orderCouponsList.get(j).setCoupon(couponService.searchCouponById(orderCouponsList.get(j).getCouponId()));
                            }
                            orderMarketing.setOrderCouponList(orderCouponsList);
                        }
                    }

                    // 判断是否有赠品
                    if (orderMarketing.getHaveGiftStatus() != null && "1".equals(orderMarketing.getHaveGiftStatus())) {
                        List<OrderGift> orderGiftsList = orderGiftMapper.selectOrderGiftList(orderMarketing.getMarketingId());
                        if (orderGiftsList != null && !orderGiftsList.isEmpty()) {
                            for (int j = 0; j < orderGiftsList.size(); j++) {
                                orderGiftsList.get(j).setGift(giftService.selectGiftDetailById(orderGiftsList.get(j).getGiftId()));
                            }
                            orderMarketing.setOrderGiftList(orderGiftsList);
                        }
                    }
                }
                order.setOrderMarketingList(orderMarketingList);
            }
            barterOrder.setOrder(order);
        }
        return barterOrder;
    }

    /**
     * 修改换单任意字段，根据ID
     *
     * @param barterOrder
     * @return
     */
    public int modifyBarterCheck(BarterOrder barterOrder) {
        return barterOrderMapper.updateByPrimaryKeySelective(barterOrder);
    }

    /**
     * 查询换单的数量
     *
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public int queryBarterOrderCount(Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("thirdId", thirdId);
            return this.barterOrderMapper.queryBarterOrderCount(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询换单的数量(已买)
     *
     * @param customerId
     *            customerID {@link Long}
     * @return
     */
    @Override
    public int queryBarterOrderCountBuy(Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("customerId", customerId);
            return this.barterOrderMapper.queryBarterOrderCountBuy(map);
        } finally {
            map = null;
        }

    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    @Resource(name = "OrderMapper")
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    public OrderGoodsMapper getOrderGoodsMapper() {
        return orderGoodsMapper;
    }

    @Resource(name = "OrderGoodsMapper")
    public void setOrderGoodsMapper(OrderGoodsMapper orderGoodsMapper) {
        this.orderGoodsMapper = orderGoodsMapper;
    }

    public OrderMarketingMapper getOrderMarketingMapper() {
        return orderMarketingMapper;
    }

    @Resource(name = "OrderMarketingMapper")
    public void setOrderMarketingMapper(OrderMarketingMapper orderMarketingMapper) {
        this.orderMarketingMapper = orderMarketingMapper;
    }

    public MarketingService getMarketingService() {
        return marketingService;
    }

    @Resource(name = "MarketingService")
    public void setMarketingService(MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    public OrderGoodsInfoCouponMapper getOrderGoodsInfoCouponMapper() {
        return orderGoodsInfoCouponMapper;
    }

    @Resource(name = "OrderGoodsInfoCouponMapper")
    public void setOrderGoodsInfoCouponMapper(OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper) {
        this.orderGoodsInfoCouponMapper = orderGoodsInfoCouponMapper;
    }

    public CouponService getCouponService() {
        return couponService;
    }

    @Resource(name = "CouponService")
    public void setCouponService(CouponService couponService) {
        this.couponService = couponService;
    }

    public OrderGoodsInfoGiftMapper getOrderGoodsInfoGiftMapper() {
        return orderGoodsInfoGiftMapper;
    }

    @Resource(name = "OrderGoodsInfoGiftMapper")
    public void setOrderGoodsInfoGiftMapper(OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper) {
        this.orderGoodsInfoGiftMapper = orderGoodsInfoGiftMapper;
    }

    public GiftService getGiftService() {
        return giftService;
    }

    @Resource(name = "GiftService")
    public void setGiftService(GiftService giftService) {
        this.giftService = giftService;
    }

    public OrderCouponMapper getOrderCouponMapper() {
        return orderCouponMapper;
    }

    @Resource(name = "OrderCouponMapper")
    public void setOrderCouponMapper(OrderCouponMapper orderCouponMapper) {
        this.orderCouponMapper = orderCouponMapper;
    }

    public OrderGiftMapper getOrderGiftMapper() {
        return orderGiftMapper;
    }

    @Resource(name = "OrderGiftMapper")
    public void setOrderGiftMapper(OrderGiftMapper orderGiftMapper) {
        this.orderGiftMapper = orderGiftMapper;
    }

    public OrderExpressMapper getOrderExpressMapper() {
        return orderExpressMapper;
    }

    @Resource(name = "OrderExpressMapper")
    public void setOrderExpressMapper(OrderExpressMapper orderExpressMapper) {
        this.orderExpressMapper = orderExpressMapper;
    }

}
