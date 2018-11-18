/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.service.impl;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.Customer;
import com.ningpai.customer.bean.CustomerAddress;
import com.ningpai.customer.dao.CustomerAddressMapper;
import com.ningpai.customer.service.CustomerServiceMapper;
import com.ningpai.freighttemplate.bean.Express;
import com.ningpai.freighttemplate.bean.FreightTemplate;
import com.ningpai.freighttemplate.dao.ExpressInfoMapper;
import com.ningpai.freighttemplate.dao.FreightTemplateMapper;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.bean.ProductWare;
import com.ningpai.goods.bean.WareHouse;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.dao.GoodsProductReleSpecMapper;
import com.ningpai.goods.dao.ProductWareMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.service.ProductWareService;
import com.ningpai.goods.util.CalcStockUtil;
import com.ningpai.goods.vo.GoodsProductDetailViewVo;
import com.ningpai.goods.vo.GoodsProductReleSpecVo;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.*;
import com.ningpai.order.dao.*;
import com.ningpai.order.service.OrderCouponService;
import com.ningpai.order.service.OrderService;
import com.ningpai.order.util.TimeListUtil;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.IsBackOrderMapper;
import com.ningpai.system.service.ILogisticsCompanyBiz;
import com.ningpai.system.service.IsBackOrderService;
import com.ningpai.system.service.PaymentService;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.util.UtilDate;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单service实现类
 *
 * @author NINGPAI-LIH
 */
@Service("OrderService")
public class OrderServiceImpl implements OrderService {

    /**
     * 记录日志对象
     */
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    private static final String ORDERSTATUS = "orderStatus";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";
    private static final String START = "start";
    private static final String NUMBER = "number";
    private static final String ORDERLINEPAY = "orderLinePay";
    private static final String ORDERMTYPE = "orderMType";
    private static final String ENDTIME = "endTime";
    private static final String STARTTIME = "startTime";
    private static final String KUAIDI_URL = "http://www.kuaidi100.com/applyurl?key=e4300e2c86783e80&com=";
    private static final String ORDERID = "orderId";

    /**
     * 订单优惠券
     */
    @Resource(name = "OrderCouponService")
    private OrderCouponService orderCouponService;

    @Resource(name = "FreightTemplateMapper")
    private FreightTemplateMapper freightTemplateMapper;

    @Resource(name = "ProductWareService")
    private ProductWareService productWareService;

    @Resource(name = "BackOrderMapper")
    private BackOrderMapper backOrderMapper;

    @Resource(name = "OrderGoodsInfoCouponMapper")
    private OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper;

    @Resource(name = "CouponService")
    private CouponService couponService;
    @Resource(name = "customerAddressMapper")
    private CustomerAddressMapper customerAddressMapper;

    @Resource(name = "OrderGoodsInfoGiftMapper")
    private OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper;

    @Resource(name = "ProductWareMapper")
    private ProductWareMapper productWareMapper;

    @Resource(name = "GiftService")
    private GiftService giftService;

    @Resource(name = "OrderGoodsMapper")
    private OrderGoodsMapper orderGoodsMapper;

    @Resource(name = "OrderCouponMapper")
    private OrderCouponMapper orderCouponMapper;

    @Resource(name = "OrderGiftMapper")
    private OrderGiftMapper orderGiftMapper;

    @Resource(name = "OrderExpressMapper")
    private OrderExpressMapper orderExpressMapper;

    // 查询货品总数量和货品id
    @Resource(name = "OrderGoodsInfoMapper")
    private OrderGoodsInfoMapper orderGoodsInfoMapper;

    @Resource(name = "OrderContainerRelationMapper")
    private OrderContainerRelationMapper relationMapper;

    @Resource(name = "OrderContainerMapper")
    private OrderContainerMapper containerMapper;

    @Resource(name = "logisticsCompanyBizImpl")
    private ILogisticsCompanyBiz iLogisticsCompanyBiz;

    @Resource(name = "OrderMapper")
    private OrderMapper orderMapper;

    @Resource(name = "OrderMarketingMapper")
    private OrderMarketingMapper orderMarketingMapper;

    @Resource(name = "GoodsProductMapper")
    private GoodsProductMapper goodsProductMapper;

    @Resource(name = "MarketingService")
    private MarketingService marketingService;

    @Resource(name = "GoodsProductService")
    private GoodsProductService goodsProductService;

    @Resource(name = "expressInfoMapperThird")
    private ExpressInfoMapper expressInfoMapper;

    @Resource(name = "IsBackOrderMapper")
    private IsBackOrderMapper isBackOrderMapper;

    @Resource(name = "IsBackOrderService")
    private IsBackOrderService isbackOrderService;

    @Resource(name = "customerServiceMapper")
    private CustomerServiceMapper customerServiceMapper;

    @Resource(name = "GoodsProductReleSpecMapper")
    private GoodsProductReleSpecMapper goodsProductReleSpecMapper;

    @Resource(name = "PaymentService")
    PaymentService paymentService;

    /**
     * 根据状态查询订单总数
     *
     * @param buinessId
     *            商家ID
     * @param orderStatus
     *            订单状态 0未付款 1已付款未发货 2已发货未确认收获 3已经收货 4作废
     * @return int
     */
    public int businessOrderCount(Long buinessId, String orderStatus) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("businessId", buinessId);
        paramMap.put(ORDERSTATUS, orderStatus);
        paramMap.put("delFlag", "0");
        return orderMapper.searchThirdOrderCount(paramMap);
    }

    /**
     * 更具订单id数组查询订单列表
     *
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    public PageBean searchOrderList(Order order, PageBean pageBean, Long[] orderIds) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
        List<Long> list = null;
        if (orderIds == null) {
            // 查询总数
            pageBean.setRows(orderMapper.searchOrderCount(paramMap));
        } else {
            list = new ArrayList<Long>();
            for (int i = 0; i < orderIds.length; i++) {
                list.add(orderIds[i]);
            }
            paramMap.put("list", list);
            int number = orderMapper.searchOrderCountByOrderIdsList(paramMap);
            pageBean.setRows(number);
        }
        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        try {
            // 查询列表页
            if (orderIds == null) {
                // 如果订单id为空，则查询全部订单
                pageBean.setList(orderMapper.searchOrderList(paramMap));
            } else {
                // 如果订单id集合不为空，则按照集合进行查询
                pageBean.setList(orderMapper.searchOrderListByOrderIdList(paramMap));
            }
        } finally {
            paramMap = null;
        }
        return pageBean;
    }
    /**
     * 更具订单id数组查询订单列表
     *
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    public PageBean searchSimpleOrderList(Order order, PageBean pageBean, Long[] orderIds) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
        List<Long> list = null;
        if (orderIds == null) {
            // 查询总数
            pageBean.setRows(orderMapper.searchOrderCount(paramMap));
        } else {
            list = new ArrayList<Long>();
            for (int i = 0; i < orderIds.length; i++) {
                list.add(orderIds[i]);
            }
            paramMap.put("list", list);
            int number = orderMapper.searchOrderCountByOrderIdsList(paramMap);
            pageBean.setRows(number);
        }
        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        try {
            // 查询列表页
            if (orderIds == null) {
                // 如果订单id为空，则查询全部订单
                pageBean.setList(orderMapper.searchOrderList(paramMap));
            } else {
                // 如果订单id集合不为空，则按照集合进行查询
                pageBean.setList(orderMapper.searchSimpleOrderListByOrderIdList(paramMap));
            }
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 查询新订单列表
     *
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    public Map<String, Object> newsearchOrderList(String status, Order order, PageBean pageBean) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        Map<String, Object> map = new HashMap<>();

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        // 待发货
        if ("2".equals(status)) {
            paramMap.put(ORDERSTATUS, "1");

        } else if ("3".equals(status)) { // 已发货
            paramMap.put(ORDERSTATUS, "2");
        } else if ("4".equals(status)) { // 待支付
            paramMap.put(ORDERSTATUS, "0");
            paramMap.put(ORDERLINEPAY, "1");
        } else if ("5".equals(status)) { // 货到付款
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERLINEPAY, "0");
        } else if ("6".equals(status)) { // 已完成
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "3");
        } else if ("7".equals(status)) { // 已取消
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "4");
        } else if ("8".equals(status)) { // 手机订单
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERMTYPE, "2");
        } else {
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERMTYPE, "");
        }
        List<Object> obj = orderMapper.searchOrderList(paramMap);
        // 公用list集合
        pageBean.setRows(orderMapper.searchOrderCount(paramMap));
        // 查询列表页
        pageBean.setList(obj);
        map.put("pageBean", pageBean);
        return map;
    }

    /**
     * 新订单列表页面分页
     *
     * @param status
     * @param order
     * @param pageBean
     * @return PageBean
     */
    @Override
    public PageBean newajaxgetpagefoot(String status, Order order, PageBean pageBean) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        // 待发货
        if ("2".equals(status)) {
            paramMap.put(ORDERSTATUS, "1");
        } else if ("3".equals(status)) { // 已发货
            paramMap.put(ORDERSTATUS, "2");
        } else if ("4".equals(status)) { // 待付款
            paramMap.put(ORDERSTATUS, "0");
            paramMap.put(ORDERLINEPAY, "1");
        } else if ("5".equals(status)) { // 货到付款
            paramMap.put("payId", "2");
        } else if ("6".equals(status)) { // 已完成
            paramMap.put(ORDERSTATUS, "3");
        } else if ("7".equals(status)) { // 已取消
            paramMap.put(ORDERSTATUS, "2");
        } else if ("8".equals(status)) { // 手机订单
            paramMap.put(ORDERMTYPE, "2");
        }
        pageBean.setRows(orderMapper.searchOrderCount(paramMap));
        return pageBean;
    }

    /**
     * 查询订单列表
     *
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @param pageBean
     *            {@link com.ningpai.util.PageBean}
     * @return PageBean
     */
    public PageBean searchOrderList(Order order, PageBean pageBean) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);

        // 查询总数
        pageBean.setRows(orderMapper.searchOrderCount(paramMap));

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());

        try {
            // 查询列表页
            pageBean.setList(orderMapper.searchOrderList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 修改订单信息状态根据Id
     *
     * @return int
     */
    @Transactional
    public int modifyOrderByKey(Long orderId, String status) {
        String statusNew = status;
        List<Long> list = new ArrayList<Long>();
        list.add(orderId);
        orderMapper.selectOrderList(list);
        Order order = orderMapper.selectOrderList(list).get(0);
        order.setOrderStatus(statusNew);
        // 后台修改订单状态为已付款
        order.setPayId(3L);
        if ("1".equals(statusNew)) {
            order.setPayTime(new Date());
        }
        // 判断是否是要完成订单
        if ("3".equals(statusNew)) {
            // 赠送优惠券
            orderCouponService.modifyCouponByOrderId(order.getOrderId(), order.getCustomerId());
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 修改订单信息状态根据Id
     *
     * @return int
     */
    @Override
    public int modifyOrderByKey(Long orderId, Long thirdId, String status) {
        // 创建修改订单对象
        Order order = new Order();
        // 订单编号
        order.setOrderId(orderId);
        // 商家编号
        order.setBusinessId(thirdId);
        // 订单状态
        order.setOrderStatus(status);
        return orderMapper.updateOrderStatusByorderId(order);
    }

    /**
     * 中断订单
     *
     * @param orderId
     *            订单id
     * @param status
     *            订单状态
     * @param reson
     *            订单中断原因
     * @return int
     */
    @Transactional
    public int modifyOrderByKey(Long orderId, String status, String reson) {
        String statusNew = status;
        Order order = new Order();
        if (statusNew == null || "".equals(statusNew)) {
            statusNew = "4";
        }
        order.setOrderId(orderId);
        order.setOrderStatus(statusNew);
        order.setOrderCancelRemark(reson);
        order.setOrderCancelTime(new Date());
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    @Override
    public int queryOrderCountBygoodsIds(Long[] goodsIds) {

        return orderGoodsMapper.queryOrderCountBygoodsIds(goodsIds);
    }

    @Override
    public int queryOrderCountBygoodsInfoIds(Long[] goodsInfoIds) {
        return orderGoodsMapper.queryOrderCountBygoodsInfoIds(goodsInfoIds);
    }

    /**
     * 查询订单详细
     *
     * @param orderId
     *            {@link Long}
     * @return Order
     */
    public Order orderDetail(Long orderId) {
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
                GoodsProductDetailViewVo goodsProductDetailViewVo = goodsProductService.queryViewVoByProductId(orderGoods.getGoodsInfoId());
                orderGoods.setGoodsProductVo(goodsProductDetailViewVo);
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
                    // 判断是否赠送赠品
                    if (orderGoods.getHaveGiftStatus() != null && "1".equals(orderGoods.getHaveGiftStatus())) {
                        // 有送赠品
                        // 查询赠送的赠品
                        List<OrderGoodsInfoGift> orderGoodsInfoGiftList = orderGoodsInfoGiftMapper.selectOrderGoodsInfoGift(orderGoods.getOrderGoodsId());
                        // 查询赠品详细信息
                        if (orderGoodsInfoGiftList != null && !orderGoodsInfoGiftList.isEmpty()) {
                            for (int j = 0; j < orderGoodsInfoGiftList.size(); j++) {
                                if (orderGoodsInfoGiftList.get(j).getGiftId() != null) {
                                    orderGoodsInfoGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGoodsInfoGiftList.get(j).getGiftId()));
                                }
                            }
                            orderGoods.setOrderGoodsInfoGiftList(orderGoodsInfoGiftList);
                        }

                    }

                }
            }
            order.setOrderGoodsList(orderGoodsList);
        }
        // 查询订单促销信息
        List<OrderMarketing> orderMarketingList = orderMarketingMapper.selectOrderMarketingList(orderId);
        // 查询订单商品促销的是否有赠品和优惠券
        if (orderMarketingList != null && !orderMarketingList.isEmpty()) {
            for (int i = 0; i < orderMarketingList.size(); i++) {
                OrderMarketing orderMarketing = orderMarketingList.get(i);
                // 查询订单促销详细信息
                orderMarketing.setMarketing(marketingService.marketingDetail(orderMarketing.getMarketingId()));
                // 判断订单促销是否送优惠券
                if (orderMarketing.getHaveCouponStatus() != null && "1".equals(orderMarketing.getHaveCouponStatus())) {
                    // 有 查询赠送优惠券
                    List<OrderCoupon> orderCouponList = orderCouponMapper.selectOrderCoupon(orderMarketing.getOrderMarketingId());
                    // 查询优惠券详细
                    if (orderCouponList != null && !orderCouponList.isEmpty()) {
                        for (int j = 0; j < orderCouponList.size(); j++) {
                            orderCouponList.get(j).setCoupon(couponService.searchCouponById(orderCouponList.get(j).getCouponId()));
                        }
                        orderMarketing.setOrderCouponList(orderCouponList);
                    }

                }
                // 判断是否有赠品
                if (orderMarketing.getHaveGiftStatus() != null && "1".equals(orderMarketing.getHaveGiftStatus())) {
                    // 有 查询赠送的赠品信息
                    List<OrderGift> orderGiftList = orderGiftMapper.selectOrderGiftList(orderMarketing.getOrderMarketingId());
                    // 查询赠品详细信息
                    if (orderGiftList != null && !orderGiftList.isEmpty()) {
                        for (int j = 0; j < orderGiftList.size(); j++) {
                            orderGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGiftList.get(j).getGiftId()));
                        }
                        orderMarketing.setOrderGiftList(orderGiftList);
                    }
                }
            }
            order.setOrderMarketingList(orderMarketingList);
        }

        // 判断是否有订单优惠券和赠品

        return order;
    }

    /**
     * 查询订单详细
     *
     * @param orderId
     *            {@link Long}
     * @return Order
     */
    public Order orderSimpleDetail(Long orderId) {
        // 更新订单状态为已查看
        orderMapper.updateOrdreNewStauts(orderId);
        // 查询订单详细信息
        Order order = orderMapper.orderSimpleDetail(orderId);
        // 查询订单商品信息
        List<OrderGoods> orderGoodsList = orderGoodsMapper.selectSimpleOrderGoodsList(orderId);
        // 查询订单物流信息
        order.setOrderExpress(orderExpressMapper.selectOrderExpress(orderId));
        // 查询订单商品参与的商品促销信息
        if (orderGoodsList != null && !orderGoodsList.isEmpty()) {
            for (int i = 0; i < orderGoodsList.size(); i++) {
                OrderGoods orderGoods = orderGoodsList.get(i);
                // 查询货品详细信息
                GoodsProductDetailViewVo goodsProductDetailViewVo = goodsProductService.simpleQueryViewVoByProductId(orderGoods.getGoodsInfoId());
                orderGoods.setGoodsProductReleSpecVoList(goodsProductReleSpecMapper.queryAllSimpleByProductId(orderGoods.getGoodsInfoId()));
                orderGoods.setGoodsProductVo(goodsProductDetailViewVo);
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
                    // 判断是否赠送赠品
                    if (orderGoods.getHaveGiftStatus() != null && "1".equals(orderGoods.getHaveGiftStatus())) {
                        // 有送赠品
                        // 查询赠送的赠品
                        List<OrderGoodsInfoGift> orderGoodsInfoGiftList = orderGoodsInfoGiftMapper.selectOrderGoodsInfoGift(orderGoods.getOrderGoodsId());
                        // 查询赠品详细信息
                        if (orderGoodsInfoGiftList != null && !orderGoodsInfoGiftList.isEmpty()) {
                            for (int j = 0; j < orderGoodsInfoGiftList.size(); j++) {
                                if (orderGoodsInfoGiftList.get(j).getGiftId() != null) {
                                    orderGoodsInfoGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGoodsInfoGiftList.get(j).getGiftId()));
                                }
                            }
                            orderGoods.setOrderGoodsInfoGiftList(orderGoodsInfoGiftList);
                        }

                    }

                }
            }
            order.setOrderGoodsList(orderGoodsList);
        }
        // 查询订单促销信息
        List<OrderMarketing> orderMarketingList = orderMarketingMapper.selectOrderMarketingList(orderId);
        // 查询订单商品促销的是否有赠品和优惠券
        if (orderMarketingList != null && !orderMarketingList.isEmpty()) {
            for (int i = 0; i < orderMarketingList.size(); i++) {
                OrderMarketing orderMarketing = orderMarketingList.get(i);
                // 查询订单促销详细信息
                orderMarketing.setMarketing(marketingService.marketingDetail(orderMarketing.getMarketingId()));
                // 判断订单促销是否送优惠券
                if (orderMarketing.getHaveCouponStatus() != null && "1".equals(orderMarketing.getHaveCouponStatus())) {
                    // 有 查询赠送优惠券
                    List<OrderCoupon> orderCouponList = orderCouponMapper.selectOrderCoupon(orderMarketing.getOrderMarketingId());
                    // 查询优惠券详细
                    if (orderCouponList != null && !orderCouponList.isEmpty()) {
                        for (int j = 0; j < orderCouponList.size(); j++) {
                            orderCouponList.get(j).setCoupon(couponService.searchCouponById(orderCouponList.get(j).getCouponId()));
                        }
                        orderMarketing.setOrderCouponList(orderCouponList);
                    }

                }
                // 判断是否有赠品
                if (orderMarketing.getHaveGiftStatus() != null && "1".equals(orderMarketing.getHaveGiftStatus())) {
                    // 有 查询赠送的赠品信息
                    List<OrderGift> orderGiftList = orderGiftMapper.selectOrderGiftList(orderMarketing.getOrderMarketingId());
                    // 查询赠品详细信息
                    if (orderGiftList != null && !orderGiftList.isEmpty()) {
                        for (int j = 0; j < orderGiftList.size(); j++) {
                            orderGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGiftList.get(j).getGiftId()));
                        }
                        orderMarketing.setOrderGiftList(orderGiftList);
                    }
                }
            }
            order.setOrderMarketingList(orderMarketingList);
        }

        // 判断是否有订单优惠券和赠品

        return order;
    }

    /**
     * 查询物流信息
     *
     * @param orderId
     *            {@link Long}
     * @return OrderExpress
     */
    public OrderExpress expressDetail(Long orderId) {
        return orderExpressMapper.selectOrderExpress(orderId);
    }

    /**
     * 发货
     *
     * @param orderExpress
     *            {@link com.ningpai.order.bean.OrderExpress}
     * @return int
     */
    @Transactional
    public int sendOrder(OrderExpress orderExpress) {
        orderExpressMapper.updateExpress(orderExpress);
        return orderMapper.sendOrder(orderExpress.getOrderId());
    }

    /**
     * 插入订单
     *
     * @param order
     * @return int
     */
    public int insertOrder(Order order) {
        return orderMapper.insertOrder(order);
    }

    /**
     * 查询刚刚插入的订单ID
     *
     * @return Long
     */
    public Long selectLastId() {
        return orderMapper.selectLastId();
    }

    /**
     * 确认支付
     *
     * @param orderId
     * @return int
     */
    public int payOrder(Long orderId) {
        return orderMapper.payOrder(orderId);
    }

    /**
     * 已提交至银行等待处理
     *
     * @param orderId
     * @return orderId
     */
    public int subBankPayOrder(Long orderId){
        return orderMapper.subBankPayOrder(orderId);
    }

    /**
     * 查询订单
     *
     * @param orderId
     * @return Order
     */
    public Order getPayOrder(Long orderId) {
        Order order = orderMapper.orderDetail(orderId);
        if (order != null) {
            // 订单商品
            order.setOrderGoodsList(orderGoodsMapper.selectOrderGoodsList(orderId));
        }

        return order;
    }
    /**
     * 查询订单
     *
     * @param orderId
     * @return Order
     */
    public Order getSimplePayOrder(Long orderId) {
        Order order = orderMapper.orderSimpleDetail(orderId);
        if (order != null) {
            // 订单商品
            order.setOrderGoodsList(orderGoodsMapper.selectSimpleOrderGoodsList(orderId));
        }

        return order;
    }


    /**
     * 查询订单根据COde
     *
     * @param orderCode
     * @return Order
     */
    public Order getPayOrderByCode(String orderCode) {
        return orderMapper.getPayOrderByCode(orderCode);
    }

    /**
     * 添加订单促销信息
     *
     * @param orderMarketing
     *            参数
     * @author NINGPAI-LIH
     */
    public void insertSelective(OrderMarketing orderMarketing) {
        orderMarketingMapper.insertSelective(orderMarketing);
    }

    /**
     * 查看最后加入的订单促销id
     *
     * @return Long
     */
    public Long selectOrderMarketLastId() {
        return orderMarketingMapper.selectOrderMarketLastId();
    }

    /**
     * 插入所有订单赠品
     *
     * @param gift
     */
    public void insertOrderInfoGift(List<OrderGift> gift) {
        orderGiftMapper.insertOrderInfoGift(gift);
    }

    /**
     * 插入所有的订单优惠劵
     */
    public void insertCouponInfoGift(List<OrderCoupon> coupons) {
        orderCouponMapper.insertCouponInfoGift(coupons);

    }

    /**
     * 拣货
     *
     * @param orderId
     * @param thirdId
     * @return gifts:赠品信息和赠品数量 orderPicking：拣货单信息 orderGoodsInfos商品信息和商品数量
     *         goodsGifts:赠品信息和赠品数量
     */
    public Map<String, Object> queryByPincking(Long[] orderId, Long thirdId, String pinckingName, String status) {
        List<Long> list = new ArrayList<Long>();
        List<Order> orders = new ArrayList<Order>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
            orders.add(orderMapper.orderDetail(orderId[i]));
        }
        int count = 0;
        // 根据订单id查询所属的货品id和货品数量
        List<OrderGoodsInfo> orderGoodsInfos = orderGoodsInfoMapper.queryByGoodsInfosCount(list);
        if (orderGoodsInfos != null) {
            for (int i = 0; i < orderGoodsInfos.size(); i++) {
                count += orderGoodsInfos.get(i).getGoodsInfoNum();
                GoodsProductVo goodsProductVo = goodsProductService.queryByPrimaryId(orderGoodsInfos.get(i).getGoodsInfoId());
                orderGoodsInfos.get(i).setGoodsProductVo(goodsProductVo);
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orders", orders);
        map.put("count", count);
        // 订单id所属的所有赠品id和赠品数量
        List<OrderGoodsInfo> gifts = orderGoodsInfoMapper.queryGiftCountByOrderIds(list);
        // 设置赠品实体
        for (int i = 0; i < gifts.size(); i++) {
            gifts.get(i).setGift(giftService.selectGiftDetailById(gifts.get(i).getGiftId()));
        }

        List<OrderGoodsInfo> goodsGifts = orderGoodsInfoMapper.queryGiftCountByGoodsIds(list);
        // 设置赠品实体
        for (int i = 0; i < goodsGifts.size(); i++) {
            goodsGifts.get(i).setGift(giftService.selectGiftDetailById(goodsGifts.get(i).getGiftId()));
        }

        List<OrderGoodsInfo> orderGoodsInfo = giftExtract(gifts, goodsGifts);

        // 订单id所属的货品id和货品数量以及货品的详细信息
        map.put("orderGoodsInfos", orderGoodsInfos);
        // 设置订单所属的赠品id和赠品数量
        map.put("gifts", orderGoodsInfo);
        // 添加拣货集合
        // List<OrderPicking> orderPickings=new ArrayList<OrderPicking>();
        OrderPicking orderPicking = new OrderPicking();
        orderPicking.setPickingStatus("0");
        // 拣货人编号
        orderPicking.setPickingNo(UtilDate.mathString(new Date()));
        // 拣货人姓名
        orderPicking.setPickingName(pinckingName);
        // 拣货时间
        orderPicking.setPickingTime(UtilDate.stringToDate(UtilDate.dataFormat(new Date())));

        // 设置拣货人员，拣货时间
        map.put("orderPicking", orderPicking);
        try {
            return map;
        } finally {
            list = null;
            map = null;
        }
    }

    /**
     * 拣货
     *
     * @param orderId
     * @param thirdId
     * @return gifts:赠品信息和赠品数量 orderPicking：拣货单信息 orderGoodsInfos商品信息和商品数量
     *         goodsGifts:赠品信息和赠品数量
     */
    public Map<String, Object> querySimpleByPincking(Long[] orderId, Long thirdId, String pinckingName, String status) {
        List<Long> list = new ArrayList<Long>();
        List<Order> orders = new ArrayList<Order>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
           /* orders.add(orderMapper.orderSimpleDetail(orderId[i]));*/
        }
        int count = 0;
        // 根据订单id查询所属的货品id和货品数量
        List<OrderGoodsInfo> orderGoodsInfos = orderGoodsInfoMapper.queryByGoodsInfosCount(list);
        if (orderGoodsInfos != null) {
            for (int i = 0; i < orderGoodsInfos.size(); i++) {
                count += orderGoodsInfos.get(i).getGoodsInfoNum();
                GoodsProductVo goodsProductVo = goodsProductService.queryByPrimaryId(orderGoodsInfos.get(i).getGoodsInfoId());
                orderGoodsInfos.get(i).setGoodsProductVo(goodsProductVo);
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("orders", orders);
        map.put("count", count);
        // 订单id所属的所有赠品id和赠品数量
        List<OrderGoodsInfo> gifts = orderGoodsInfoMapper.queryGiftCountByOrderIds(list);
        // 设置赠品实体
        for (int i = 0; i < gifts.size(); i++) {
            gifts.get(i).setGift(giftService.selectGiftDetailById(gifts.get(i).getGiftId()));
        }

        List<OrderGoodsInfo> goodsGifts = orderGoodsInfoMapper.queryGiftCountByGoodsIds(list);
        // 设置赠品实体
        for (int i = 0; i < goodsGifts.size(); i++) {
            goodsGifts.get(i).setGift(giftService.selectGiftDetailById(goodsGifts.get(i).getGiftId()));
        }

        List<OrderGoodsInfo> orderGoodsInfo = giftExtract(gifts, goodsGifts);

        // 订单id所属的货品id和货品数量以及货品的详细信息
        map.put("orderGoodsInfos", orderGoodsInfos);
        // 设置订单所属的赠品id和赠品数量
        map.put("gifts", orderGoodsInfo);
        // 添加拣货集合
        // List<OrderPicking> orderPickings=new ArrayList<OrderPicking>();
        OrderPicking orderPicking = new OrderPicking();
        orderPicking.setPickingStatus("0");
        // 拣货人编号
        orderPicking.setPickingNo(UtilDate.mathString(new Date()));
        // 拣货人姓名
        orderPicking.setPickingName(pinckingName);
        // 拣货时间
        orderPicking.setPickingTime(UtilDate.stringToDate(UtilDate.dataFormat(new Date())));

        // 设置拣货人员，拣货时间
        map.put("orderPicking", orderPicking);
        try {
            return map;
        } finally {
            list = null;
            map = null;
        }
    }

    /**
     * 订单促销赠品和商品促销赠品合并
     *
     * @param gifts
     *            订单促销赠品
     * @param goodsGifts
     *            商品促销赠品
     * @return
     */
    private List<OrderGoodsInfo> giftExtract(List<OrderGoodsInfo> gifts, List<OrderGoodsInfo> goodsGifts) {
        List<OrderGoodsInfo> orderGoodsInfo = new ArrayList<OrderGoodsInfo>();

        for (int i = 0; i < gifts.size(); i++) {
            orderGoodsInfo.add(gifts.get(i));
        }
        // 循环商品
        for (int i = 0; i < goodsGifts.size(); i++) {
            boolean isT = true;
            for (int j = 0; j < orderGoodsInfo.size(); j++) {
                // 判断商品促销赠品和订单促销赠品是否相同，相同叠加数量，不同插入集合
                if (goodsGifts.get(i).getGift().getGiftCode().equals(orderGoodsInfo.get(j).getGift().getGiftCode())) {
                    OrderGoodsInfo order1 = goodsGifts.get(i);
                    OrderGoodsInfo order2 = orderGoodsInfo.get(j);
                    order1.setGiftNum(order1.getGiftNum() + order2.getGiftNum());
                    orderGoodsInfo.set(j, order1);
                    isT = false;
                }
            }
            if (isT) {
                orderGoodsInfo.add(goodsGifts.get(i));
            }
        }
        return orderGoodsInfo;
    }

    /**
     * 出库
     *
     * @param orderId
     *            订单编号
     * @param thirdId
     *            第三方标示
     * @return
     */
    public List<Order> selectOrderListsByOrderIds(Long[] orderId, Long thirdId) {
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
        }
        List<Order> orderLists = orderMapper.selectOrderList(list);

        for (int i = 0; i < orderLists.size(); i++) {
            orderLists.get(i).setOrderGoodsList(orderGoodsMapper.selectOrderGoodsList(orderLists.get(i).getOrderId()));
            for (int j = 0; j < orderLists.get(i).getOrderGoodsList().size(); j++) {
                orderLists.get(i).getOrderGoodsList().get(j)
                        .setGoodsProductVo(goodsProductService.queryViewVoByProductId(orderLists.get(i).getOrderGoodsList().get(j).getGoodsInfoId()));
            }
            List<OrderGoodsInfo> gifts = orderGoodsInfoMapper.selectGiftByOrderIdInGoods(orderLists.get(i).getOrderId());
            // 设置赠品实体
            for (int j = 0; j < gifts.size(); j++) {
                gifts.get(j).setGift(giftService.selectGiftDetailById(gifts.get(j).getGiftId()));
            }
            List<OrderGoodsInfo> orderGifts = orderGoodsInfoMapper.selectGiftByOrderIdInOrder(orderLists.get(i).getOrderId());
            // 设置赠品实体
            for (int j = 0; j < orderGifts.size(); j++) {
                orderGifts.get(j).setGift(giftService.selectGiftDetailById(orderGifts.get(j).getGiftId()));
            }
            List<OrderGoodsInfo> orderGoodsInfo = giftExtract(orderGifts, gifts);
            orderLists.get(i).setOrderGoodsInfos(orderGoodsInfo);

        }

        // 修改状态
        Map<String, Object> mapStatus = new HashMap<String, Object>();
        mapStatus.put("list", list);
        mapStatus.put(ORDERSTATUS, '1');
        // 修改状态
        orderMapper.updateOrderStatusByOrderIds(mapStatus);
        return orderLists;
    }

    /**
     * 为订单添加默认包裹
     *
     * @param orderId
     *            订单id
     * @return
     */
    @Transactional
    public void initContainerRelation(Long orderId) {
        List<OrderContainerRelation> relations = selectListByOrderIds(orderId);
        if (relations.isEmpty()) {
            // 新增一个装箱单
            addContainerRalation(orderId);
            // 查询订单详情
            Order order = orderMapper.orderDetail(orderId);
            // 定义装箱集合
            List<OrderContainer> containers = new ArrayList<OrderContainer>();
            // 设置货品实体
            order.setOrderGoodsList(orderGoodsMapper.selectOrderGoodsList(order.getOrderId()));
            for (int j = 0; j < order.getOrderGoodsList().size(); j++) {
                order.getOrderGoodsList().get(j).setGoodsProductVo(goodsProductService.queryViewVoByProductId(order.getOrderGoodsList().get(j).getGoodsInfoId()));
                OrderContainer container = new OrderContainer();
                container.setContainerStatus("0");
                container.setGoodsInfoId(order.getOrderGoodsList().get(j).getGoodsInfoId());
                container.setGoodsNum(order.getOrderGoodsList().get(j).getGoodsInfoNum());
                container.setRelationId(relationMapper.selectLastId());
                containers.add(container);
            }

            List<OrderGoodsInfo> gifts = orderGoodsInfoMapper.selectGiftByOrderIdInGoods(order.getOrderId());
            // 设置赠品实体
            for (int j = 0; j < gifts.size(); j++) {
                gifts.get(j).setGift(giftService.selectGiftDetailById(gifts.get(j).getGiftId()));
            }
            List<OrderGoodsInfo> orderGifts = orderGoodsInfoMapper.selectGiftByOrderIdInOrder(order.getOrderId());
            // 设置赠品实体
            for (int j = 0; j < orderGifts.size(); j++) {
                orderGifts.get(j).setGift(giftService.selectGiftDetailById(orderGifts.get(j).getGiftId()));
            }
            // 订单赠品和商品促销赠品合并
            List<OrderGoodsInfo> orderGoodsInfo = giftExtract(orderGifts, gifts);
            order.setOrderGoodsInfos(orderGoodsInfo);
            // 循环设置赠品
            for (int i = 0; i < order.getOrderGoodsInfos().size(); i++) {
                OrderContainer container = new OrderContainer();
                container.setContainerStatus("1");
                container.setRelationId(relationMapper.selectLastId());
                container.setGoodsInfoId(order.getOrderGoodsInfos().get(i).getGift().getGiftId());
                container.setGoodsNum(order.getOrderGoodsInfos().get(i).getGiftNum());
                containers.add(container);
            }
            // 插入装箱单数据
            containerMapper.insertContainers(containers);
        }
    }

    /**
     * 查询订单包裹
     *
     * @param orderId
     * @return List
     */
    public List<OrderContainerRelation> queryContainerRalation(Long orderId) {
        // 查询所有的订单包裹
        List<OrderContainerRelation> relations = selectListByOrderIds(orderId);

        // 循环订单并为订单添加商品和赠品
        for (int i = 0; i < relations.size(); i++) {
            List<OrderContainer> containers = containerMapper.queryContainerByRelationId(relations.get(i).getRelationId());
            // 如果该包裹内没有商品和赠品
            if (containers != null) {
                // 循环包裹
                for (int j = 0; j < containers.size(); j++) {
                    if ("0".equals(containers.get(j).getContainerStatus())) {
                        relations.get(i).setIsGoodsInfos("1");
                        // 设置商品实体
                        GoodsProductDetailViewVo gd = goodsProductService.queryViewVoByProductId(containers.get(j).getGoodsInfoId());
                        gd.getGoods().setGoodsDetailDesc("");
                        containers.get(j).setGoodsProductDetailViewVo(gd);
                    } else {
                        relations.get(i).setIsGifts("1");
                        // 设置赠品实体
                        containers.get(j).setGift(giftService.selectGiftDetailById(containers.get(j).getGoodsInfoId()));
                    }
                }
            }
            relations.get(i).setContainers(containers);
        }

        return relations;
    }
    /**
     * 查询订单包裹
     *
     * @param orderId
     * @return List
     */
    public List<OrderContainerRelation> querySimpleContainerRalation(Long orderId) {
        // 查询所有的订单包裹
        List<OrderContainerRelation> relations = selectListByOrderIds(orderId);

        // 循环订单并为订单添加商品和赠品
        for (int i = 0; i < relations.size(); i++) {
            List<OrderContainer> containers = containerMapper.queryContainerByRelationId(relations.get(i).getRelationId());
            // 如果该包裹内没有商品和赠品
            if (containers != null) {
                // 循环包裹
                for (int j = 0; j < containers.size(); j++) {
                    if ("0".equals(containers.get(j).getContainerStatus())) {
                        relations.get(i).setIsGoodsInfos("1");
                        // 设置商品实体
                        GoodsProductDetailViewVo gd = goodsProductService.simpleQueryViewVoByProductId(containers.get(j).getGoodsInfoId());
                        gd.getGoods().setGoodsDetailDesc("");
                        containers.get(j).setGoodsProductDetailViewVo(gd);
                    } else {
                        relations.get(i).setIsGifts("1");
                        // 设置赠品实体
                        containers.get(j).setGift(giftService.selectGiftDetailById(containers.get(j).getGoodsInfoId()));
                    }
                }
            }
            relations.get(i).setContainers(containers);
        }

        return relations;
    }

    /**
     * 查询所有包裹
     *
     * @param orderId
     * @return List
     */
    public List<OrderContainerRelation> selectListByOrderIds(Long orderId) {
        return relationMapper.selectListByOrderIds(orderId);
    }

    /**
     * 新增装箱单
     *
     * @param orderId
     */
    @Transactional
    public void addContainerRalation(Long orderId) {
        OrderContainerRelation relation = new OrderContainerRelation();
        relation.setOrderId(orderId);
        OrderExpress express = orderExpressMapper.selectOrderExpress(orderId);
        relation.setOrderExpressId(express.getOrderExpressId());
        // 插入装箱关系表
        relationMapper.insertSelective(relation);
    }

    /**
     * 发货
     *
     * @param relationIds
     * @param expressNo
     * @param expressId
     */
    @Transactional
    public void updateSendOrderGoods(Long[] relationIds, String[] expressNo, int[] expressId) {
        // 更新运货单
        LogisticsCompany il = null;
        for (int i = 0; i < relationIds.length; i++) {
            OrderContainerRelation relation = new OrderContainerRelation();
            il = iLogisticsCompanyBiz.getLogisticsCompanyById(expressId[i]);
            // il =
            // iLogisticsCompanyBiz.getThirdLogisticsCompanyById(expressId[i]);
            // 设置包裹id
            relation.setRelationId(relationIds[i]);
            relation.setExpressName(il.getName());
            relation.setOrderExpressId((long) il.getLogComId());
            // 设置运货单
            relation.setExpressNo(expressNo[i]);
            relationMapper.updateRelation(relation);
        }
    }

    /**
     * 第三方发货
     *
     * @param relationIds
     * @param expressNo
     * @param expressId
     */
    @Transactional
    public void updateThirdSendOrderGoods(Long[] relationIds, String[] expressNo, int[] expressId) {
        // 更新运货单
        LogisticsCompany il = null;
        for (int i = 0; i < relationIds.length; i++) {
            OrderContainerRelation relation = new OrderContainerRelation();
            il = iLogisticsCompanyBiz.getThirdLogisticsCompanyById(expressId[i]);
            // il = iLogisticsCompanyBiz.getLogisticsCompanyById(expressId[i]);
            // 设置包裹id
            relation.setRelationId(relationIds[i]);
            relation.setExpressName(il.getName());
            relation.setOrderExpressId((long) il.getLogComId());
            // 设置运货单
            relation.setExpressNo(expressNo[i]);
            relationMapper.updateRelation(relation);
        }
    }

    /**
     * 更改包裹
     *
     * @param container
     */
    public void updateRelation(OrderContainer container) {
        containerMapper.updateRelation(container);
    }

    /**
     * 添加包裹商品
     *
     * @param container
     */
    @Override
    public void addRelation(OrderContainer container) {
        // 插入内容
        containerMapper.insertSelective(container);
    }

    /**
     * 根据Id 获得包裹中商品的信息
     *
     * @param cId
     * @return OrderContainer
     */
    @Override
    public OrderContainer queryOrderContainerById(Long cId) {
        return containerMapper.queryContainerByParam(cId);
    }
    /**
     * 根据货品ID查询关联的规格值列表
     *
     * @param productId
     *            货品ID {@link java.lang.Long}
     * @return 查询到的关联列表 {@link java.util.List}
     *         {@link com.ningpai.goods.bean.GoodsProductReleSpec}
     */
    @Override
    public List<GoodsProductReleSpecVo> queryAllSimpleByProductId(Long productId){
        return goodsProductReleSpecMapper.queryAllSimpleByProductId(productId);
    }

    /**
     * 根据rId 和货品Id获得包裹中商品的信息
     *
     * @param rId
     * @return OrderContainer
     */
    @Override
    public OrderContainer queryOrderContainerByGoodInfoId(Long rId, Long goodInfoId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 关联Id
        map.put("rId", rId);
        // 存储货品Id
        map.put("goodInfoId", goodInfoId);
        return containerMapper.queryOrderContainerByGoodInfoId(map);
    }

    /**
     * 验证包裹下是否有商品
     *
     * @param relationId
     *            包裹id
     * @return Long
     */
    public Long verifyCount(Long relationId) {
        return containerMapper.verifyCount(relationId);
    }

    /**
     * 删除包裹
     *
     * @param relationId
     */
    public void delRelationById(Long relationId) {
        relationMapper.delRelationById(relationId);
    }

    /**
     * 删除包裹商品
     *
     * @param cId
     *            包裹id
     */
    @Override
    public void delContainerByCId(Long cId) {
        relationMapper.delContainerByCId(cId);
    }

    /**
     * 分解包裹下的商品
     *
     * @param cId
     *            包裹id
     * @param goodsSum
     */
    @Transactional
    public void splitOrderGoods(Long cId, Long goodsSum) {
        // 先根据id查询出所属包裹内商品的详细内容
        OrderContainer container = containerMapper.queryContainerByParam(cId);
        // 修改包裹内商品的数量
        // 修改数量
        container.setGoodsNum(container.getGoodsNum() - goodsSum);
        containerMapper.updateGoodsNum(container);
        // 为包裹插入新的商品
        // 创建新的包裹商品
        OrderContainer newCon = container;
        // 设置数量
        newCon.setGoodsNum(goodsSum);
        // 插入内容
        containerMapper.insertSelective(newCon);

    }

    /**
     * 查询近一个月新增订单
     *
     * @return Map
     */
    public Map<String, Object> selectNewOrderByParam() {
        // 存放数据map list：前十条近一个月新增订单 count：近一个新增订单总数
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置前十条近一个新增订单
        map.put("list", orderMapper.selectNewOrderByParam());
        // 设置近一个新增订单总数量
        map.put("count", orderMapper.selectNewOrderCountByParam());
        return map;
    }

    /**
     * 返回提交订单时的第一个商品名称
     *
     * @param orderId
     * @return String
     */
    public String queryGoodsInfoName(Long orderId) {
        List<OrderGoods> lists = orderGoodsMapper.selectOrderGoodsList(orderId);
        if (lists == null || lists.isEmpty()) {
            return "暂无商品";
        }
        // 获取当前第一个商品
        OrderGoods orderGoods = lists.get(0);
        try {
            // 返回商品名称
            return goodsProductService.queryByPrimaryId(orderGoods.getGoodsInfoId()).getGoodsInfoName();
        } catch (Exception e) {
            LOGGER.error("返回提交订单时的第一个商品名称报错，请查看原因：", e);
            return "暂无商品";
        }
    }

    /**
     * 发货
     *
     * @param orderId
     * @param status
     * @param pickName
     *            发货人名称
     * @param pickingStatus
     * @param thirdId
     *            第三方标示
     * @return
     */
    public int sendOrderByP(Long orderId, String status, String pickName, String pickingStatus, Long thirdId) {
        Order order = new Order();
        // 设置订单id
        order.setOrderId(orderId);
        // 设置订单状态
        order.setOrderStatus(status);
        // 设置发货时间
        if ("2".equals(status)) {
            order.setSendExpressTime(new Date());
        }
        if (null != thirdId && 0 != thirdId) {
            order.setBusinessId(thirdId);
        }
        return orderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 查询订单
     *
     * @param request
     * @param order
     * @param pageBean
     * @param list
     * @return
     * @throws ParseException
     */
    public PageBean queryYOrder(HttpServletRequest request, Order order, PageBean pageBean, List<String> list) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
        paramMap.put("list", list);
        Object beginTime = request.getParameter("beginTime");
        Object endTime = request.getParameter(ENDTIME);
        paramMap.put("endPrice", request.getParameter("endPrice"));
        paramMap.put("beginPrice", request.getParameter("beginPrice"));
        if (beginTime != null && !"".equals(beginTime)) {
            paramMap.put("beginTime", formatDate.parse(beginTime.toString()));
        }
        if (endTime != null && !"".equals(endTime)) {
            paramMap.put(ENDTIME, formatDate.parse(endTime.toString()));
        }
        // 查询总数
        pageBean.setRows(orderMapper.searchOrderCountByOrderIds(paramMap));

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());

        // 查询列表页
        pageBean.setList(orderMapper.searchOrderListByOrderIds(paramMap));
        return pageBean;
    }

    /**
     * 查询订单
     *
     * @param request
     * @param order
     * @param pageBean
     * @param list
     * @return
     * @throws ParseException
     */
    public PageBean queryYSimpleOrder(HttpServletRequest request, Order order, PageBean pageBean, List<String> list) throws ParseException {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(ENDROWNUM, pageBean.getEndRowNum());
        paramMap.put("list", list);
        Object beginTime = request.getParameter("beginTime");
        Object endTime = request.getParameter(ENDTIME);
        paramMap.put("endPrice", request.getParameter("endPrice"));
        paramMap.put("beginPrice", request.getParameter("beginPrice"));
        if (beginTime != null && !"".equals(beginTime)) {
            paramMap.put("beginTime", formatDate.parse(beginTime.toString()));
        }
        if (endTime != null && !"".equals(endTime)) {
            paramMap.put(ENDTIME, formatDate.parse(endTime.toString()));
        }
        // 查询总数
        pageBean.setRows(orderMapper.searchOrderCountByOrderIds(paramMap));

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());

        // 查询列表页
        pageBean.setList(orderMapper.searchSimpleOrderListByOrderIds(paramMap));
        return pageBean;
    }

    /**
     * 查询某个订单下的所有商品
     *
     * @param orderId
     *            订单id
     * @return
     */
    public List<OrderGoods> queryOrderGoods(Long orderId) {
        return orderGoodsMapper.selectOrderGoodsList(orderId);
    }

    /**
     * 更改订单状态
     *
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @return
     */
    public int changeOrderIds(Long[] orderId, String status) {
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
        }
        // 修改状态
        Map<String, Object> mapStatus = new HashMap<String, Object>();
        mapStatus.put("list", list);
        mapStatus.put(ORDERSTATUS, status);
        if (status != null) {
            // 修改状态
            orderMapper.updateOrderStatusByOrderIds(mapStatus);
            // 修改成功
            return 0;
        } else {
            // 修改失败
            return 1;
        }
    }

    /**
     * 修改订单金额
     *
     * @param price
     *            要修改的订单金额
     * @param orderId
     */
    public void modifyOrderPrice(BigDecimal price, Long orderId) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置订单交易金额
        map.put("price", price);
        // 设置订单id
        map.put(ConstantUtil.ORDERID, orderId);
        orderMapper.modifyOrderPrice(map);
    }

    /**
     * 修改订单的出库状态
     *
     * @param orderId
     * @param status
     * @return
     */
    @Override
    public int updateSetCargoStatusByOrderId(Long orderId, String status) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderCargoStatus(status);
        orderMapper.updateSetCargoStatusByOrderId(order);
        return 0;
    }

    /**
     * 修改第三方订单的出库状态
     *
     * @param orderId
     * @param status
     * @param businessId
     * @return
     */
    @Override
    public int updateSetCargoStatusByThirdOrderId(Long orderId, String status, Long businessId) {
        Order order = new Order();
        order.setOrderId(orderId);
        order.setOrderCargoStatus(status);
        order.setBusinessId(businessId);
        orderMapper.updateSetCargoStatusByThirdOrderId(order);
        return 0;
    }

    /**
     * 批量更改第三方订单状态
     *
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @param businessId
     *            商家Id
     * @return
     */
    @Override
    public int updateOrderCargoStatusByThirdOrderIds(Long[] orderId, String status, Long businessId) {
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("orderCargoStatus", status);
        map.put("businessId", businessId);
        return orderMapper.updateSetCargoStatusByThirdOrderIds(map);
    }

    /**
     * 批量更改订单状态
     *
     * @param orderId
     *            订单id数组
     * @param status
     *            要修改的订单状态
     * @return
     */
    @Override
    public int updateOrderCargoStatusByOrderIds(Long[] orderId, String status) {
        List<Long> list = new ArrayList<Long>();
        for (int i = 0; i < orderId.length; i++) {
            list.add(orderId[i]);
        }
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("list", list);
        map.put("orderCargoStatus", status);

        return orderMapper.updateSetCargoStatusByOrderIds(map);
    }

    /**
     * 判断订单的出库状态是否符合
     *
     * @param status
     *            出库状态
     * @param orderId
     *            订单id
     * @return
     */
    @Override
    public int judgeStatus(String status, Long orderId) {
        // 订单详情
        Order order = orderMapper.orderDetail(orderId);
        // 判断是否符合规则，如果符合，则正确，返回1
        if (order.getOrderCargoStatus().equals(status)) {
            return 1;
        }
        return 0;
    }

    /**
     * 删除单个退单信息
     *
     * @param backOrderId
     *            退单Id
     * @param customerId
     *            当前会员ID
     * @return
     */
    @Override
    public int deleteBackOrderById(Long backOrderId, Long customerId) {
        return orderMapper.deleteBackOrderById(backOrderId, customerId);
    }

    /**
     * 根据时间查询订单信息
     *
     * @param starTime
     * @param endTime
     * @return
     */
    @Override
    public List<Order> selectOrderListByTime(Date starTime, Date endTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 设置开始时间
        map.put(STARTTIME, starTime);
        // 设置结束时间
        map.put(ENDTIME, endTime);
        // 返回查询结果
        return orderMapper.selectOrderListByTime(map);
    }

    /**
     * 查询每天前十条数量
     *
     * @return
     */
    @Override
    public List<OrderGoods> selectTopOrderGoods() {
        return orderGoodsMapper.selectTopOrderGoods();
    }

    /**
     * 根据时间查询订单总数量
     *
     * @return
     */
    @Override
    public int selectOrderCountByCurdate() {
        return orderMapper.selectOrderCountByCurdate();
    }

    /**
     * 根据商品id查询购买商品记录
     *
     * @param goodsInfoId
     * @return
     */
    @Override
    public List<GoodsProductVo> queryGoodsProductVoByOrderGoods(Long goodsInfoId) {

        // 购买过该商品的客户id
        List<Long> list = null;
        List<OrderGoods> goodsInfoIds = null;
        List<OrderGoods> orderGoods = null;
        orderGoods = orderGoodsMapper.queryProGoodsInfoCustomer(goodsInfoId);
        GoodsProductVo goodsProductVo = null;
        List<GoodsProductVo> goodsProductVos = new ArrayList<GoodsProductVo>();
        if (orderGoods != null && !orderGoods.isEmpty()) {
            list = new ArrayList<Long>();
            if (orderGoods != null && !orderGoods.isEmpty()) {
                for (int i = 0; i < orderGoods.size(); i++) {
                    list.add(orderGoods.get(i).getCustomerId());
                }
            }
        }

        goodsInfoIds = orderGoodsMapper.queryProGoodsInfoByCustomerId(list);
        for (OrderGoods gId : goodsInfoIds) {
            goodsProductVo = goodsProductService.queryByPrimaryId(gId.getGoodsInfoId());
            if (goodsProductVo != null) {
                goodsProductVos.add(goodsProductVo);
            }
        }

        try {
            return goodsProductVos;
        } finally {
            list = null;
            goodsInfoIds = null;
            goodsProductVos = null;
        }
    }

    /**
     * 根据商品id和客户id查询最近一段时间的订单
     *
     * @param goodsInfoId
     *            商品id
     * @param custId
     *            客户id
     * @param starTime
     *            查询开始时间
     * @return
     */
    @Override
    public Long selectGoodsInfoCount(Long goodsInfoId, Long custId, Date starTime) {
        Map<String, Object> map = new HashMap<String, Object>();
        // 商品id
        map.put("goodsInfoId", goodsInfoId);
        // 开始时间
        map.put("starTime", starTime);
        // 客户id
        map.put("customerId", custId);
        return orderGoodsMapper.selectGoodsInfoCount(map);
    }

    /**
     * 根据参数查询订单列表
     *
     * @param paramMap
     *            可以放各种参数，只要在xml里添加相应的参数
     * @return
     */
    @Override
    public List<Order> selectByParam(Map<String, Object> paramMap) {
        return orderMapper.selectByParam(paramMap);
    }

    /**
     * 从快递100接口获取物流信息html链接
     *
     * @param relation
     *            包裹信息
     * @return
     */
    @Override
    public String queryExpressInfoUrl(OrderContainerRelation relation) {
        String resultUrl = "";
        try {
            Order order = orderMapper.orderDetail(relation.getOrderId());
            LogisticsCompany expressCompany = null;
            Express express = null;
            URL url = null;
            if (order.getBusinessId() != null && order.getBusinessId().intValue() == 0) {
                expressCompany = iLogisticsCompanyBiz.getLogisticsCompanyById(relation.getOrderExpressId().intValue());
                url = new URL(KUAIDI_URL + expressCompany.getKuaidi100Code() + "&nu=" + relation.getExpressNo());
            } else {
                express = this.expressInfoMapper.selectByshoreExpId(relation.getOrderExpressId());
                url = new URL(KUAIDI_URL + express.getKudi100code() + "&nu=" + relation.getExpressNo());
            }
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte[] b = new byte[10000];
            int numRead = urlStream.read(b);
            resultUrl = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, ConstantUtil.UTF);
                    resultUrl += newContent;
                }
            }
            urlStream.close();
        } catch (MalformedURLException e) {
            LOGGER.error("快递100URL格式不正确！", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("转码错误！", e);
        } catch (IOException e) {
            LOGGER.error("快递100URL失效！", e);
        }
        return resultUrl;
    }

    /**
     * 插入物流信息(E店宝专用)
     *
     * @param expressNo
     * @param orderId
     * @return
     */
    @Override
    @Transactional
    public int addExpress(String expressNo, Long orderId) {
        OrderContainerRelation relation = new OrderContainerRelation();
        relation.setOrderId(orderId);
        OrderExpress express = orderExpressMapper.selectOrderExpress(orderId);
        relation.setExpressNo(expressNo);
        relation.setExpressName(express.getExpressName());
        relation.setOrderExpressId(express.getExpressId());
        // 插入装箱关系表
        relationMapper.insertSelective(relation);

        // 查询订单详情
        Order order = orderMapper.orderDetail(orderId);
        // 定义装箱集合
        List<OrderContainer> containers = new ArrayList<OrderContainer>();
        // 设置货品实体
        order.setOrderGoodsList(orderGoodsMapper.selectOrderGoodsList(order.getOrderId()));
        for (int j = 0; j < order.getOrderGoodsList().size(); j++) {
            order.getOrderGoodsList().get(j).setGoodsProductVo(goodsProductService.queryViewVoByProductId(order.getOrderGoodsList().get(j).getGoodsInfoId()));
            OrderContainer container = new OrderContainer();
            container.setContainerStatus("0");
            container.setGoodsInfoId(order.getOrderGoodsList().get(j).getGoodsInfoId());
            container.setGoodsNum(order.getOrderGoodsList().get(j).getGoodsInfoNum());
            container.setRelationId(relationMapper.selectLastId());
            containers.add(container);
        }

        List<OrderGoodsInfo> gifts = orderGoodsInfoMapper.selectGiftByOrderIdInGoods(order.getOrderId());
        // 设置赠品实体
        for (int j = 0; j < gifts.size(); j++) {
            gifts.get(j).setGift(giftService.selectGiftDetailById(gifts.get(j).getGiftId()));
        }
        List<OrderGoodsInfo> orderGifts = orderGoodsInfoMapper.selectGiftByOrderIdInOrder(order.getOrderId());
        // 设置赠品实体
        for (int j = 0; j < orderGifts.size(); j++) {
            orderGifts.get(j).setGift(giftService.selectGiftDetailById(orderGifts.get(j).getGiftId()));
        }
        // 订单赠品和商品促销赠品合并
        List<OrderGoodsInfo> orderGoodsInfo = giftExtract(orderGifts, gifts);
        order.setOrderGoodsInfos(orderGoodsInfo);
        // 循环设置赠品
        for (int i = 0; i < order.getOrderGoodsInfos().size(); i++) {
            OrderContainer container = new OrderContainer();
            container.setContainerStatus("1");
            container.setRelationId(relationMapper.selectLastId());
            container.setGoodsInfoId(order.getOrderGoodsInfos().get(i).getGift().getGiftId());
            container.setGoodsNum(order.getOrderGoodsInfos().get(i).getGiftNum());
            containers.add(container);
        }
        // 插入装箱单数据
        containerMapper.insertContainers(containers);
        return 0;
    }

    /**
     * 根据时间分组查询每天销售量
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Order> querySaleCountByDay(String startTime, String endTime) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(STARTTIME, startTime);
        /*
         * 日期条件加时分秒 可以用于 sql BETWEEN区间查询 可查区间后者条件当天的数据 例：日期为2015-06-05 23:59:59
         * 可查询5号数据
         */
        paramMap.put(ENDTIME, endTime + " 23:59:59");
        return orderMapper.querySaleCountByDay(paramMap);
    }

    /**
     * 根据时间分组查询每天销售额
     *
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<Order> querySaleMoneyByDay(String startTime, String endTime) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(STARTTIME, startTime);
        /*
         * 日期条件加时分秒 可以用于 sql BETWEEN区间查询 可查区间后者条件当天的数据 例：日期为2015-06-05 23:59:59
         * 可查询5号数据
         */
        paramMap.put(ENDTIME, endTime + " 23:59:59");
        return orderMapper.querySaleMoneyByDay(paramMap);
    }

    /**
     * 从快递100接口获取物流信息html链接
     *
     * @param logComId
     * @param expressNo
     * @return
     */
    @Override
    public String queryExpressInfoUrl(int logComId, String expressNo) {
        String resultUrl = "";
        try {
            LogisticsCompany expressCompany = iLogisticsCompanyBiz.getLogisticsCompanyById(logComId);
            URL url = new URL(KUAIDI_URL + expressCompany.getKuaidi100Code() + "&nu=" + expressNo);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte[] b = new byte[10000];
            int numRead = urlStream.read(b);
            resultUrl = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, ConstantUtil.UTF);
                    resultUrl += newContent;
                }
            }
            urlStream.close();
        } catch (MalformedURLException e) {
            LOGGER.error("快递100URL格式不正确！", e);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("转码错误！", e);
        } catch (IOException e) {
            LOGGER.error("快递100URL失效！", e);
        }
        return resultUrl;
    }

    /**
     * 返回订单列表总数量 积分订单列表 退单列表总数量
     *
     * @return
     */
    @Override
    public Map<String, Object> getIndexOrderCount() {
        Order order = new Order();
        order.setDelFlag("0");
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderCount", orderMapper.searchOrderCount(paramMap));
        map.put("barterOrderCount", backOrderMapper.searchBackOrderCount(null));
        // 设置订单状态
        order.setOrderStatus("1");
        // 设置商品为boss商品
        order.setBusinessId((long) 0);
        // 设置出库状态(拣货)
        order.setOrderCargoStatus("0");
        paramMap = MapUtil.getParamsMap(order);
        map.put("pickCount", orderMapper.searchOrderCount(paramMap));

        // 设置出库状态(装箱)
        order.setOrderCargoStatus("1");
        paramMap = MapUtil.getParamsMap(order);
        map.put("zxCount", orderMapper.searchOrderCount(paramMap));
        // 设置出库状态(出库)
        order.setOrderCargoStatus("2");
        paramMap = MapUtil.getParamsMap(order);
        map.put("ckCount", orderMapper.searchOrderCount(paramMap));
        return map;
    }

    /**
     * 删除订单
     *
     * @param orderId
     * @return
     */
    @Override
    public int deleteOrderById(Long orderId) {
        return orderMapper.deleteOrderById(orderId);
    }

    /**
     * 查询第三方订单
     *
     * @param order
     * @param pageBean
     * @return
     */
    @Override
    public PageBean searchThirdOrderList(Order order, PageBean pageBean) {

        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        // 查询总数
        pageBean.setRows(orderMapper.searchThirdOrderCount(paramMap));
        paramMap.put(STARTROWNUM, pageBean.getStartRowNum());
        paramMap.put(ENDROWNUM, pageBean.getEndRowNum());

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        try {
            // 查询列表页
            pageBean.setList(orderMapper.searchThirdOrderList(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 自动更新未付款订单七天后作废
     */
    public void cancelOrderByTime() {
        Long time = isbackOrderService.getTimeFromNpset();
        Map<String, Object> map = new HashMap<>();
        map.put("time", time * 60 * 60);
        map.put("orderCancelRemark", "订单一定时间未付款系统自动取消订单!");
        orderMapper.updateStatusByCancleOrder(map);
    }

    /**
     * 根据总订单号查询订单信息
     *
     * @param orderOldCode
     * @return
     */
    public List<Order> getPayOrderByOldCode(String orderOldCode) {
        return orderMapper.getPayOrderByOldCode(orderOldCode);
    }

    /**
     * 更新订单货品表 标记为退单货品
     *
     * @param goodsInfoId
     * @param orderId
     * @return
     */
    @Override
    public int updateOrderGoodsBack(Long goodsInfoId, Long orderId, String backOrderCode) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("goodsInfoId", goodsInfoId);
        param.put(ORDERID, orderId);
        param.put("backOrderCode", backOrderCode);
        return orderGoodsMapper.updateOrderGoodsBack(param);
    }

    /**
     * 根据订单id更改订单状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    @Override
    public int updateStatusBackById(Long orderId, String orderStatus, BigDecimal backPrice) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put(ORDERID, orderId);
        param.put(ORDERSTATUS, orderStatus);
        param.put("backPrice", backPrice);
        return orderMapper.updateStatusBackById(param);
    }

    /**
     * 查询退货商品信息（根据orderId和退单编号查询）
     *
     * @param orderId
     * @param backOrderCode
     * @return
     */
    @Override
    public List<OrderGoods> queryOrderGoodsByOrderIdAndBackCode(Long orderId, String backOrderCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(ORDERID, orderId);
        map.put("backOrderCode", backOrderCode);
        return orderGoodsMapper.queryOrderGoodsByOrderIdAndBackCode(map);
    }

    /**
     * 查询首页统计
     *
     * @return 统计数据
     * @auth lih
     */
    @Override
    public Map<String, Object> queryStatistics() {
        // 时间类型
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 获取七天前的时间
        Calendar c = Calendar.getInstance();
        // 将当天时间减去6天
        c.add(Calendar.DATE, -6);
        // 获取时间
        Date monday = c.getTime();
        // 进行转换
        String preMonday = sdf.format(monday);
        // 当前时间
        String neDate = sdf.format(new Date());
        // 设置结果
        Map<String, Object> map = new HashMap<String, Object>();
        // 根据时间分组查询每天销售量(七天)
        map.put("orderList", querySaleCountByDay(preMonday, neDate));
        // 根据时间分组查询每天销额（七天）
        map.put("orderPriceList", querySaleMoneyByDay(preMonday, neDate));
        // 设置时间集合
        map.put("timeList", TimeListUtil.getTimeList());
        return map;
    }

    /**
     * 定时任务
     */
    @Override
    public void receiptConfirmation() {
        // 得到所有已发货的订单信息
        List<Order> orderList = orderMapper.receiptConfirmation();
        if (CollectionUtils.isNotEmpty(orderList)) {
            // 后台设置自动收货订单时间
            Long day = isBackOrderMapper.getIsBackOrder().getReceiptTime();
            for (Order order : orderList) {
                if (new Date().getTime() - order.getSendExpressTime().getTime() > (day * 60 * 60 * 24 * 1000)) {
                    // 自动收货后状态改变
                    order.setOrderStatus("3");
                    order.setGetGoodsTime(new Date());
                    orderMapper.updateOrderStatusByorderIdFortask(order);
                }
            }
        }
    }

    /**
     * 获取订单详细
     *
     * .lang.Long[], java.lang.Long[], java.lang.String)
     */
    @Override
    public Map<String, Object> ajaxGetorderDetail(HttpServletRequest request, Long[] goodsNum, Long[] goodsIdP, String customerName) {
        Customer cus = customerServiceMapper.getCustomerByUsername(customerName);
        Map<String, Object> map = new HashMap<>();
        if (null == cus) {
            // 填写的会员账号不存在
            map.put("isPassed", "0");
            return map;
        } else {
            // 支付方式 id 1:在线支付 2:货到付款 其中isOpen 1:开启 0:关闭
            map.put("payList", paymentService.selectAllForSite());
            map.put("customerId", cus.getCustomerId());
            map.put("vip", cus.getIsEnterprise());
            // CustomerAddress ca = customerServiceMapper.queryDefaultAddr(Long
            // customerId)(addressId);
            return map;
        }

    }

    /**
     * 后台保存添加的订单
     *
     * @return int 0:货品不能为空 1:货品库存不足 2:货品的总价格不能为空 3:物流公司不能为空 4:支付方式不能为空 5:订单添加成功
     *         6:订单添加失败
     */
    @Override
    public int saveAddOrder(BigDecimal goodsAllPrice, BigDecimal freightPrice, String companyInfo, String customerRemark, Long[] goodsIdP, Long[] goodsNum, Long distinctId,
            Long payType, String invoiceTitle, String invoiceType, String invoiceContent, CustomerAddress customerAddress,String isvip) {

        // 货品id map查询货品集合
        Map<String, Object> goodsIds = new HashMap<>();
        goodsIds.put("productIds", goodsIdP);
        // 添加订单货品的集合
        List<GoodsProductVo> goodsProductVos = goodsProductMapper.queryGoodsProductVoByProductIds(goodsIds);
        // 仓库信息
        List<CalcStockUtil> calcStockUtils = new ArrayList<CalcStockUtil>();
        // 查询购买的商品
        List<OrderGoods> oglist = new ArrayList<OrderGoods>();
        // 封装物流信息
        OrderExpress oe = new OrderExpress();
        Order order = new Order();
        // 查询物流模板信息 根据thirdId 查询默认的模板
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("freightIsDefault", "1");
        paramMap.put("freightThirdId", 0);
        // 获取默认模板
        FreightTemplate ft = freightTemplateMapper.selectFreightTemplateSubOrder(paramMap);
        if (ft != null) {
            // 配送方式名称
            oe.setExpressTypeName(ft.getFreightTemplateName());
        }
        // 配送方式 0 快递配送 1 上门自提
        oe.setExpressTypeId(0L);

        if (null == goodsAllPrice) {
            return 2;
        }
        if (null == payType) {
            return 4;
        }
        customerAddress.setInfoCounty(distinctId.toString());
        if (CollectionUtils.isNotEmpty(goodsProductVos)) {

            for (int i = 0; i < goodsProductVos.size(); i++) {
                // 查询库存
                ProductWare productWare = productWareService.queryProductWareByProductIdAndDistinctId(goodsProductVos.get(i).getGoodsInfoId(), distinctId);
                if (productWare != null && productWare.getWareStock() - goodsNum[i] <= 0) {
                    // 设置商品库存
                        // 库存不足
                        return 1;
                }
                //add by luyong 添加企业价格
                BigDecimal oldprice = goodsProductVos.get(i).getGoodsInfoPreferPrice();
                //如果是企业会员
                if("1".endsWith(isvip)){
                    oldprice = goodsProductVos.get(i).getGoodsInfoVipPrice();
                }
                // 初始化订单商品信息
                OrderGoods og = new OrderGoods();
                // 设置数量
                og.setGoodsInfoNum(goodsNum[i]);
                // 设置删除标记
                og.setDelFlag("0");
                // 设置购买时间
                og.setBuyTime(new Date());
                // 设置收货地区
                og.setDistinctId(distinctId);
                og.setEvaluateFlag("0");
                // 设置商品原价
                //og.setGoodsInfoOldPrice(oldprice); 重复了
                // 设置货品Id
                og.setGoodsInfoId(goodsProductVos.get(i).getGoodsInfoId());
                // 设置商品ID
                og.setGoodsId(goodsProductVos.get(i).getGoodsId());
                // 货品价格
                og.setGoodsInfoOldPrice(oldprice);
                // 订单价格由于有优惠不好判断只保存销售价格
                og.setGoodsInfoPrice(og.getGoodsInfoOldPrice());
                // 设置货品总价格（数量*价格）
                og.setGoodsInfoSumPrice(og.getGoodsInfoOldPrice().multiply(BigDecimal.valueOf(goodsNum[i])));
                CalcStockUtil calcStockUtil = new CalcStockUtil();
                calcStockUtil.setIsThird(goodsProductVos.get(i).getIsThird());
                calcStockUtil.setDistinctId(distinctId);
                // 商品id
                calcStockUtil.setProductId(goodsProductVos.get(i).getGoodsInfoId());
                // 减去库存
                calcStockUtil.setStock(Integer.parseInt(og.getGoodsInfoNum().toString()));
                calcStockUtils.add(calcStockUtil);
                oglist.add(og);

            }

            if (StringUtils.isNotEmpty(companyInfo)) {
                // 物流公司id
                Long comId = Long.valueOf(companyInfo.split("_")[0]);
                // 物流公司名称
                String comName = companyInfo.split("_")[1];
                oe.setExpressId(comId);
                oe.setExpressName(comName);
            } else {
                // 物流公司不能为空
                return 3;
            }
            // 保存收货地址信息
            Long currentId
                  = customerAddressMapper.insertSelectiveNew(customerAddress);
            // 重新查询收货地址信息
            CustomerAddress ca = customerAddressMapper.selectByaddressId(currentId);
            // 设置配送方式
            order.setOrderExpressType("0");
            // 用户收货地址ID
            order.setShoppingAddrId(ca.getAddressId());
            // 省
            order.setShippingProvince(ca.getProvince().getProvinceName());
            // 市
            order.setShippingCity(ca.getCity().getCityName());
            // 区
            order.setShippingCounty(ca.getDistrict().getDistrictName());
            // 详细地址
            order.setShippingAddress(ca.getAddressDetail());
            // 区ID
            order.setShippingCountyId(ca.getDistrict().getDistrictId());

            // 收货人
            order.setShippingPerson(ca.getAddressName());
            // 电话
            order.setShippingPhone(ca.getAddressPhone());
            // 手机
            order.setShippingMobile(ca.getAddressMoblie());
            // 备注
            order.setCustomerRemark(customerRemark);
            // 邮编
            order.setShippingPostcode(ca.getAddressZip());
            // 主订单号
            String orderOldCode = UtilDate.mathString(new Date());
            // 子订单号
            // 随机数
            int randomNum = (int) (Math.random() * 100);
            String orderCode = UtilDate.mathString(new Date()) + randomNum;
            order.setOrderCode(orderCode);
            order.setOrderOldCode(orderOldCode);
            // 发票信息
            order.setInvoiceType(invoiceType);
            // 如果为1，普通发票，设置发票抬头和内容
            if ("1".equals(invoiceType)) {
                // 发票抬头
                order.setInvoiceTitle(invoiceTitle);
                // 发票内容
                order.setInvoiceContent(invoiceContent);
            }
            order.setDelFlag("0");
            // 订单状态
            order.setOrderStatus("0");
            // 订单总金额 加上运费
            order.setOrderPrice(goodsAllPrice.add(freightPrice));
            // 原始总额
            order.setOrderOldPrice(goodsAllPrice);
            // 运费
            order.setExpressPrice(freightPrice);
            // 总优惠金额
            order.setOrderPrePrice(BigDecimal.ZERO);
            // 支付方式 payId 1:在线支付,2:货到付款
            order.setPayId(payType);
            if (payType == 2) {
                // 货到付款
                order.setOrderLinePay("0");
            } else {
                // 在线支付
                order.setOrderLinePay("1");
            }
            order.setBusinessId(0L);
            order.setCustomerId(customerAddress.getCustomerId());
            order.setCreateTime(new Date());
            WareHouse ware = productWareMapper.findWare(distinctId);
            if (ware != null) {
                // 收获地址信息
                order.setWareName(ware.getWareName());
                // 库存ID
                order.setWareId(ware.getWareId());
            }
            // 插入订单主表
            int f = orderMapper.insertOrder(order);
            // 后台不需要添加到收货地址
            customerAddressMapper.deleteByPrimaryKey(customerAddress.getAddressId());
            // customerAddressMapper.
            if (f == 1) {
                // 获取刚刚插入的订单ID
                Long orderId = orderMapper.selectLastId();
                // 插入物流信息
                oe.setOrderId(orderId);
                orderExpressMapper.insertOrderExpress(oe);
                // 循环设置货品级联ID信息
                if (oglist != null && !oglist.isEmpty()) {
                    for (int i = 0; i < oglist.size(); i++) {
                        oglist.get(i).setOrderId(orderId);
                        // 插入货品
                        orderGoodsMapper.insertOrderGoodsInfo(oglist.get(i));
                    }
                }
                return 5;
            } else {
                return 6;
            }
        } else {
            // 货品不能为空
            return 0;
        }
    }

    /**
     * 查询所有订单信息 此方法供后台订单列表中的导出订单列表功能使用
     *
     * @return
     * @author houyichang 2015/8/14
     */
    @Override
    public List<Order> queryAllOrderList() {
        return this.orderMapper.queryAllOrderList();
    }

    /**
     * 新查询第三方订单
     *
     * @param status
     *            订单状态
     * @param order
     *            订单
     * @param pageBean
     *            分页
     * @return
     */
    @Override
    public Map<String, Object> newsearchThirdOrderList(String status, Order order, PageBean pageBean) {
        order.setDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        Map<String, Object> map = new HashMap<>();

        // 查询条件封装
        paramMap.put(START, pageBean.getStartRowNum());
        paramMap.put(NUMBER, pageBean.getEndRowNum());
        // 待发货
        if ("2".equals(status)) {
            paramMap.put(ORDERSTATUS, "1");

        } else if ("3".equals(status)) {
            // 已发货
            paramMap.put(ORDERSTATUS, "2");
        } else if ("4".equals(status)) {
            // 待支付
            paramMap.put(ORDERSTATUS, "0");
            paramMap.put(ORDERLINEPAY, "1");
        } else if ("5".equals(status)) {
            // 货到付款
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERLINEPAY, "0");
        } else if ("6".equals(status)) {
            // 已完成
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "3");
        } else if ("7".equals(status)) {
            // 已取消
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "4");
        } else if ("8".equals(status)) {
            // 手机订单
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERMTYPE, "2");
        } else {
            paramMap.put(ORDERLINEPAY, "");
            paramMap.put(ORDERSTATUS, "");
            paramMap.put(ORDERMTYPE, "");
        }
        List<Object> obj = orderMapper.searchThirdOrderList(paramMap);
        // 公用list集合
        pageBean.setRows(orderMapper.searchThirdOrderCount(paramMap));
        // 查询列表页
        pageBean.setList(obj);
        map.put("pageBean", pageBean);
        return map;
    }
}
