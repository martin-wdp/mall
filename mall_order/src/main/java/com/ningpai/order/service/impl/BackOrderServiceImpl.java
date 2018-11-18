/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.order.service.impl;

import com.ningpai.common.util.DateUtil;
import com.ningpai.coupon.service.CouponService;
import com.ningpai.customer.bean.CustomerConsume;
import com.ningpai.customer.bean.CustomerInfo;
import com.ningpai.customer.bean.CustomerPoint;
import com.ningpai.customer.bean.CustomerPointLevel;
import com.ningpai.customer.dao.CustomerConsumeMapper;
import com.ningpai.customer.dao.CustomerInfoMapper;
import com.ningpai.customer.dao.CustomerPointMapper;
import com.ningpai.customer.dao.IntegralSetMapper;
import com.ningpai.customer.service.PointLevelServiceMapper;
import com.ningpai.gift.service.GiftService;
import com.ningpai.goods.bean.GoodsProduct;
import com.ningpai.goods.dao.GoodsProductMapper;
import com.ningpai.goods.service.GoodsProductService;
import com.ningpai.goods.vo.GoodsProductVo;
import com.ningpai.marketing.service.MarketingService;
import com.ningpai.order.bean.*;
import com.ningpai.order.dao.*;
import com.ningpai.order.service.BackOrderService;
import com.ningpai.other.bean.IntegralSet;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.*;

/**
 * 退单信息列表
 * 
 * @author ggn
 */
@Service("BackOrderService")
public class BackOrderServiceImpl implements BackOrderService {

    // 静态变量
    private static final Logger LOGGER = Logger.getLogger(BackOrderServiceImpl.class);
    // spring注解
    private BackOrderMapper backOrderMapper;
    // 货品信息Mapper
    private GoodsProductMapper goodsProductMapper;
    // spring注解
    private CustomerPointMapper customerPointMapper;
    // spring注解
    private IntegralSetMapper integralSetmapper;
    // spring注解
    private CustomerInfoMapper customerInfoMapper;
    // 消费记录
    private CustomerConsumeMapper customerConsumeMapper;

    private GoodsProductService goodsProductService;

    private OrderMapper orderMapper;

    private OrderGoodsMapper orderGoodsMapper;

    private OrderMarketingMapper orderMarketingMapper;

    private MarketingService marketingService;

    private OrderGoodsInfoCouponMapper orderGoodsInfoCouponMapper;

    private CouponService couponService;

    private OrderGoodsInfoGiftMapper orderGoodsInfoGiftMapper;

    private GiftService giftService;

    private OrderCouponMapper orderCouponMapper;

    private OrderGiftMapper orderGiftMapper;

    private OrderExpressMapper orderExpressMapper;

    private PointLevelServiceMapper pointLevelServiceMapper;

    /**
     * 退单信息列表
     *
     * @param pageBean
     * @param bkOrder
     * @param startTime
     * @param endTime
     * @return PageBean
     */
    public PageBean backOrderList(PageBean pageBean, BackOrder bkOrder, String startTime, String endTime) {
        bkOrder.setBackDelFlag("0");
        // 分装实体类属性
        Map<String, Object> paramMap = MapUtil.getParamsMap(bkOrder);
        if (startTime != null && !"".equals(startTime)) {
            bkOrder.setBackTime(DateUtil.stringToDate(startTime, null));
            paramMap.put("startTime", startTime);
        }
        if (endTime != null && !"".equals(endTime)) {
            paramMap.put("endTime", endTime);
        }
        // 查询总数
        pageBean.setRows(backOrderMapper.searchBackOrderThirdCount(paramMap));
        // pageBean.setRows(backOrderMapper.searchBackOrderCountnew(paramMap));
        // 计算分页
        Integer no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
        no = no == 0 ? 1 : no;
        if (pageBean.getPageNo() >= no) {
            pageBean.setPageNo(no);
            pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
        }
        pageBean.setObjectBean(bkOrder);
        // 查询条件封装
        paramMap.put("start", pageBean.getStartRowNum());
        paramMap.put("number", pageBean.getEndRowNum());
        try {
            // 获取数据库的退单集合
            List<Object> backorders = backOrderMapper.searchBackOrderLisThird(paramMap);
            // 优化退单流程时候重新创建的方法 之前的查询太浪费资源
            // List<Object> backorders =
            // backOrderMapper.searchBackOrderListnew(paramMap);
            if (null != backorders && !backorders.isEmpty()) {
                for (int i = 0; i < backorders.size(); i++) {
                    // 获取单个的退单对象
                    BackOrder backOrders = (BackOrder) backorders.get(i);
                    if (!"".equals(backOrders.getBackGoodsIdAndSum())) {
                        // 获取退单对象 下面的退单的 商品Id
                        String[] strs = backOrders.getBackGoodsIdAndSum().split("-");
                        // 遍历退单对象下面的商品Id
                        for (int j = 0; j < strs.length; j++) {
                            String strss = strs[j];
                            // 获取第J个商品的Id
                            Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                            // 根据ID获取单个商品的详细信息
                            GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                            // 退货的商品数量 随意找的货品字段装的数据 只用于前台显示
                            String counts = strss.substring(strss.indexOf(",") + 1, strss.length());
                            // 装载商品数量数据
                            orderProductVo.setGoodsCount(Long.valueOf(counts));
                            // 退货的单个商品价格 随意找的货品字段装的数据 只用于前台显示
                            BigDecimal price = orderProductVo.getGoodsInfoPreferPrice();
                            BigDecimal count = new BigDecimal(counts);
                            // 计算总价格
                            BigDecimal sum = price.multiply(count);
                            // 装载单类形 退单货品的总价 随意找的货品字段装的数据 只用于前台显示
                            orderProductVo.setGoodsInfoPreferPrice(sum);
                            // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                            backOrders.getOrderGoodslistVo().add(orderProductVo);
                        }
                    }
                }
            }
            pageBean.setList(backorders);
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 当用户退单,后台同意后将把订单完成的积分奖励扣除掉 会员积分列表将增加一条扣除积分信息 会员消费列表将增加一条订单退掉的信息.
     *
     * @param orderId
     * @return int
     */
    @Override
    public int reducePointOrderBack(Long orderId) {
        Order order = orderMapper.orderDetail(orderId);
        Long customerId = order.getCustomerId();
        BigDecimal orderPrice = order.getOrderPrice();
        CustomerPoint customerPoint = new CustomerPoint();
        IntegralSet inte = integralSetmapper.findPointSet();
        // 每100元兑换积分
        double ex = 100;
        customerPoint.setPointDetail("订单退订扣除");
        // 订单退订应该扣除积分(即:订单完成的奖励积分)
        int reduce = ((Double) (orderPrice.doubleValue() * (inte.getExchange() / ex))).intValue();
        customerPoint.setPoint(reduce);
        customerPoint.setPointType("0");
        customerPoint.setDelFlag("0");
        customerPoint.setCreateTime(new Date());
        customerPoint.setCustomerId(customerId);
        // 扣除后的积分保存到数据库
        customerPointMapper.insertSelective(customerPoint);
        CustomerInfo info = customerInfoMapper.selectCustInfoById(customerId);
        // 当前会员总积分
        int allpoint = info.getInfoPointSum();
        allpoint = allpoint - reduce < 0 ? 0 : allpoint - reduce;
        // 扣除后的积分重新判断会员级别
        for (CustomerPointLevel level : pointLevelServiceMapper.selectAllPointLevel()) {
            String[] points = level.getPointNeed().split("~");
            if (Integer.valueOf(points[0]) <= allpoint && allpoint <= Integer.valueOf(points[1])) {
                info.setPointLevelName(level.getPointLevelName());
            }
        }
        allpoint+=order.getOrderIntegral().intValue();
        info.setInfoPointSum(allpoint);
        info.setCustomerId(order.getCustomerId());
        customerInfoMapper.updateInfoByCustId(info);
        // 添加取消订单后的消费记录
        CustomerConsume cc;
        try {
            cc = new CustomerConsume();
            // 从订单中取出付款方式1.在线支付2.货到付款
            if (order.getPayId() == 1) {
                cc.setPayType("1");
            }
            if (order.getPayId() == 2) {
                cc.setPayType("2");
            }
            cc.setCustomerId(customerId);
            cc.setBalanceNum(orderPrice);
            cc.setBalanceRemark("订单取消消费减少");
            cc.setBalanceType("3");
            cc.setCreateTime(new Date());
            cc.setDelFlag("0");
            cc.setOrderNo(order.getOrderCode());
            customerConsumeMapper.insertSelective(cc);
        } finally {
            cc = null;
        }
        return 0;
    }

    /**
     * 从订单里把退货成功的信息货品删除
     *
     * @param backId
     *            退单Id
     * @return int
     */
    public int deleteBackGoods(Long backId) {
        // 保存更新后的状态
        int result = 0;
        // 获取单个的退单对象
        BackOrder backOrders = backOrderMapper.selectbackOrderOne(backId);
        // 保存退单的总金额
        BigDecimal backOrderSumPrice = new BigDecimal("0.00");
        // 根据获取当前退单的订单信息
        Order orders = backOrderMapper.selectOrderOne(backOrders.getOrderCode());
        // 如果没有指定要退款的金额 就把商品的总金额加进去
        if (null == orders.getBackPrice() && !"".equals(backOrders.getBackGoodsIdAndSum())) {
                // 获取退单对象 下面的退单的 商品Id
                String[] strs = backOrders.getBackGoodsIdAndSum().split("-");
                // 遍历退单对象下面的商品Id
                for (int j = 0; j < strs.length; j++) {
                    String strss = strs[j];
                    // 获取第J个商品的Id
                    Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                    // 根据ID获取单个商品的详细信息
                    GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                    // 退货商品的价格
                    backOrderSumPrice = backOrderSumPrice.add(orderProductVo.getGoodsInfoPreferPrice());
                    // 退货的商品数量 随意找的货品字段装的数据 只用于前台显示
                    String counts = strss.substring(strss.indexOf(",") + 1, strss.length());
                    // 装载商品数量数据
                    orderProductVo.setGoodsCount(Long.valueOf(counts));
                    // 退货的单个商品价格 随意找的货品字段装的数据 只用于前台显示
                    BigDecimal price = orderProductVo.getGoodsInfoPreferPrice();
                    BigDecimal count = new BigDecimal(counts);
                    // 计算总价格
                    BigDecimal sum = price.multiply(count);
                    // 装载单类形 退单货品的总价 随意找的货品字段装的数据 只用于前台显示
                    orderProductVo.setGoodsInfoPreferPrice(sum);
                    // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                    backOrders.getOrderGoodslistVo().add(orderProductVo);
                }

                orders.setBackPrice(backOrderSumPrice);
                result = backOrderMapper.updateOrder(orders);
        }
        return result;
    }

    /**
     * 平台同意退单了 就把退单的商品状态更改为支持退单
     *
     * @param backId
     *            退单Id zhanghl
     * @return 修改np_order_goods 表中的商品为退货商品
     */
    public int setBackGoodsStatus(Long backId) {
        // 记录返回的结果
        int result = 0;
        // 获取单个的退单对象
        BackOrder backOrders = backOrderMapper.selectbackOrderOne(backId);
        // 根据获取当前退单的订单信息
        Order orders = backOrderMapper.selectOrderOne(backOrders.getOrderCode());
        if (!"".equals(backOrders.getBackGoodsIdAndSum())) {
            // 获取退单对象 下面的退单的 商品Id
            String[] strs = backOrders.getBackGoodsIdAndSum().split("-");
            // 遍历退单对象下面的商品Id
            for (int j = 0; j < strs.length; j++) {
                String strss = strs[j];
                // 获取第J个商品的Id
                Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("goodsInfoId", goodsId);
                param.put("orderId", orders.getOrderId());
                param.put("backOrderCode", backOrders.getBackOrderCode());
                result = orderGoodsMapper.updateOrderGoodsBack(param);
            }
        }
        return result;
    }

    /**
     * 修改退单审核状态
     *
     * @param backId
     * @param backCheck
     * @return int
     */
    public int modifyBackOrderByCheck(Long backId, String backCheck) {
        // 如果是退单的确认收货
        int count = 0;
        BackOrder backOrder;

        if ("4".equals(backCheck)) {
            // 设置退货的价格
            this.deleteBackGoods(backId);
            // 删除订单中 退货成功的商品
            this.setBackGoodsStatus(backId);
        }
        if (backId != null) {
            backOrder = new BackOrder();
            backOrder.setBackOrderId(backId);
            backOrder.setBackCheck(backCheck);
            count = backOrderMapper.updateByPrimaryKeySelective(backOrder);
        }

        return count;
    }

    /**
     * 修改退单信息
     *
     * @param backOrder
     * @return int
     */
    public int modifyBackBeanCheck(BackOrder backOrder) {
        // 保存更新数据返回的状态
        int result = 0;
        if (null != backOrder) {
            if ("1".equals(backOrder.getBackDelFlag())) {
                result = backOrderMapper.updateByPrimaryKeySelective(backOrder);
                return result;
            } else if (this.updateOrderStatus(backOrder) == 1 && backOrderMapper.updateByPrimaryKeySelective(backOrder) == 1) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 修改第三方后台退单信息
     *
     * @param backOrder
     * @return int
     */
    public int modifyThirdBackBeanCheck(BackOrder backOrder) {
        // 保存更新数据返回的状态
        int result = 0;
        if (null != backOrder) {
            if ("1".equals(backOrder.getBackDelFlag())) {
                result = backOrderMapper.updateByThirdId(backOrder);
                return result;
            } else if (this.updateOrderStatus(backOrder) == 1 && backOrderMapper.updateByThirdId(backOrder) == 1) {

                return 1;
            }
        }
        return 0;
    }

    /**
     * 修改交易表的交易状态 0=7:未审核 1=8：审核通过 2=9：审核未通过 4=11:退单成功
     *
     * @return int
     */
    public int updateOrderStatus(BackOrder backOrder) {
        int result;
        Order order;
        BackOrder backDer;
        // 表链接获取交易表ID
        try {
            backDer = backOrderMapper.selectbackOrderOne(backOrder.getBackOrderId());
            order = backOrderMapper.selectOrderOne(backDer.getOrderCode());
            if ("0".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("7"); // 7退货审核中8已退货9退货审核未通过)
            } else if ("1".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("8"); // 7退货审核中8已退货9退货审核未通过)
            } else if ("2".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("9"); // 7退货审核中8已退货9退货审核未通过)
            } else if ("4".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("11"); // 7退货审核中8已退货9退货审核未通过)
            } else if ("7".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("13"); // 拒绝退款
            }else if ("10".equals(backOrder.getBackCheck())) {
                order.setOrderStatus("18"); // 退款成功
            }

            result = backOrderMapper.updateOrder(order);
        } catch (Exception e) {
            result = 0;
            LOGGER.error("修改退单状态！", e);
        }
        return result;
    }

    /**
     * 查询BackOrderdetail
     *
     * @param backOrderId
     * @return BackOrder
     */
    public BackOrder detail(Long backOrderId) {
        // 根据Id获取单个退单详细信息
        BackOrder bo = backOrderMapper.selectBackOrderDetail_new(backOrderId);
        if (!"".equals(bo.getBackGoodsIdAndSum())) {
            // 获取退单对象 下面的退单的 商品Id
            String[] strs = bo.getBackGoodsIdAndSum().split("-");
            // 遍历退单对象下面的商品Id
            for (int j = 0; j < strs.length; j++) {
                String strss = strs[j];
                // 获取第J个商品的Id
                Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                // 根据ID获取单个商品的详细信息
                GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                // 退货的商品数量 随意找的货品字段装的数据 只用于前台显示
                String counts = strss.substring(strss.indexOf(",") + 1, strss.length());
                // 装载商品数量数据
                orderProductVo.setGoodsCount(Long.valueOf(counts));
                // 退货的单个商品价格 随意找的货品字段装的数据 只用于前台显示
                BigDecimal price = orderProductVo.getGoodsInfoPreferPrice();
                BigDecimal count = new BigDecimal(counts);
                // 计算总价格
                BigDecimal sum = price.multiply(count);
                // 装载单类形 退单货品的总价 随意找的货品字段装的数据 只用于前台显示
                orderProductVo.setGoodsInfoPreferPrice(sum);
                // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                bo.getOrderGoodslistVo().add(orderProductVo);
            }
        }
        if (bo != null) {
            Long orderId = bo.getOrderId();
            // 查询订单详细信息
            Order order = orderMapper.getPayOrderByCode(bo.getOrderCode());
            // 查询退货商品信息
            // List<OrderGoods> orderGoodsList =
            // orderGoodsMapper.selectBackGoodsList(bo.getBackOrderCode());
            List<OrderGoods> orderGoodsList = orderGoodsMapper.selectBackGoodsList(bo.getOrderId());
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
                        // 判断是否赠送赠品
                        if (orderGoods.getHaveGiftStatus() != null && "1".equals(orderGoods.getHaveGiftStatus())) {
                            // 有送赠品
                            // 查询赠送的赠品
                            List<OrderGoodsInfoGift> orderGoodsInfoGiftList = orderGoodsInfoGiftMapper.selectOrderGoodsInfoGift(orderGoods.getOrderGoodsId());
                            // 查询赠品详细信息
                            if (orderGoodsInfoGiftList != null && !orderGoodsInfoGiftList.isEmpty()) {
                                for (int j = 0; j < orderGoodsInfoGiftList.size(); j++) {
                                    orderGoodsInfoGiftList.get(j).setGift(giftService.selectGiftDetailById(orderGoodsInfoGiftList.get(j).getGiftId()));
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

            bo.setOrder(order);
            // 装在退单对应的物流信息 //根据退单ID查询物流信息
            BackOrderGeneral general = backOrderMapper.selectGeneralByBackOrderId(bo.getBackOrderId());
            if (null != general) {
                bo.setOgisticsName(general.getOgisticsName());// 物流名称
                bo.setCreatTime(general.getCreatTime());// 发货时间
                bo.setOgisticsNo(general.getOgisticsNo());// 物流单号
            }

        }

        return bo;
    }
    /**
     * 查询退单信息根据退单编号（包括订单信息）
     *
     * @param backOrderId
     * @return BackOrder
     */
    @Override
    public BackOrder selectBackOrderByBackOrderId(Long backOrderId) {
       return backOrderMapper.selectBackOrderDetail(backOrderId);
    }

    /**
     * 获取退单详细信息
     *
     * @param backOrderId
     * @return BackOrder
     */
    public BackOrder backdetail(Long backOrderId, Order order) {
        BackOrder bo = backOrderMapper.selectBackOrderByBackOrderId(backOrderId);

        // 处理数据
        if (!"".equals(bo.getBackGoodsIdAndSum())) {
            // 获取退单对象 下面的退单的 商品Id
            String[] strs = bo.getBackGoodsIdAndSum().split("-");
            /***
             * 获取订单商品列表
             * add  by 付陈林 2015年12月6日
             * */
            List<OrderGoods> ogiList=new ArrayList<OrderGoods>();
             if(order!=null&&order.getOrderGoodsList()!=null&&order.getOrderGoodsList().size()>0){
                 ogiList=  order.getOrderGoodsList();
            }
            //edit end
            // 遍历退单对象下面的商品Id
            for (int j = 0; j < strs.length; j++) {
                String strss = strs[j];
                // 获取第J个商品的Id
                Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                // 根据ID获取单个商品的详细信息
                GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                // 退货的商品数量 随意找的货品字段装的数据 只用于前台显示
                String counts = strss.substring(strss.indexOf(",") + 1, strss.length());
                /**
                 * 获取商品的实际价格
                 * add by 付陈林
                 * */

                for(OrderGoods ori:ogiList){
                    if(goodsId.equals(ori.getGoodsInfoId())){
                        orderProductVo.setGoodsInfoPreferPrice(ori.getGoodsInfoPrice());
                    }
                }
                /**end edit*/
                // 装载商品数量数据
                orderProductVo.setGoodsCount(Long.valueOf(counts));
                // 退货的单个商品价格 随意找的货品字段装的数据 只用于前台显示
                BigDecimal price = orderProductVo.getGoodsInfoPreferPrice();
                BigDecimal count = new BigDecimal(counts);
                // 计算总价格
                BigDecimal sum = price.multiply(count);
                // 装载单类形 退单货品的总价 随意找的货品字段装的数据 只用于前台显示
//                orderProductVo.setGoodsInfoPreferPrice(sum);
                orderProductVo.setGoodsInfoSumPrice(sum);
                // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                bo.getOrderGoodslistVo().add(orderProductVo);
            }
        }

        List imglist = new ArrayList();
        if (bo.getUploadDocuments() != null) {
            String[] imgs = bo.getUploadDocuments().split(",");
            for (int i = 0; i < imgs.length; i++) {
                imglist.add(imgs[i]);
            }
            bo.setImgs(imglist);
        }
        // 查询退货商品详细信息
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("orderId", order.getOrderId());
        map.put("backOrderCode", bo.getBackOrderCode());
        List<OrderGoods> goodslist = orderGoodsMapper.queryOrderGoodsByOrderIdAndBackCode(map);
        for (int j = 0; j < goodslist.size(); j++) {
            GoodsProduct goodsProduct = goodsProductMapper.queryProductByGoodsId(goodslist.get(j).getGoodsInfoId());
            goodslist.get(j).setGoodsImg(goodsProduct.getGoodsInfoImgId());
            goodslist.get(j).setGoodsName(goodsProduct.getGoodsInfoName());
            goodslist.get(j).setGoodsCode(goodsProduct.getGoodsInfoItemNo());
        }
        bo.setOrderGoodsList(goodslist);
        return bo;
    }

    /**
     * 查询第三方退单数量(已买)
     *
     * @param thirdId
     * @return int
     */
    public int queryBackOrderCount(Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("thirdId", thirdId);
            return this.backOrderMapper.queryBackOrderCount(map);
        } finally {
            map = null;
        }
    }

    /**
     * 插入退单信息
     *
     * @param backOrder
     * @return int
     */
    public int insertBackOrderInfo(BackOrder backOrder) {
        return this.backOrderMapper.insertBackOrderInfo(backOrder);
    }

    /**
     * 根据订单编号查找退单信息
     *
     * @param backorder
     * @return
     */
    public BackOrder queryBackOrderByOrderCode(Order backorder,HttpServletRequest request) {
        BackOrder backOrders = this.backOrderMapper.queryBackOrderByOrderCode(backorder.getOrderCode());
        if (!"".equals(backOrders.getBackGoodsIdAndSum())) {
            // 保存退单的总金额
            BigDecimal backOrderSumPrice = new BigDecimal("0.00");
            // 获取退单对象 下面的退单的 商品Id
            String[] strs = backOrders.getBackGoodsIdAndSum().split("-");

            /***
             * 获取订单商品列表
             * add  by 付陈林 2015年12月6日
             * */
            List<OrderGoods> ogiList=new ArrayList<OrderGoods>();
            if(backorder!=null&&backorder.getOrderGoodsList()!=null&&backorder.getOrderGoodsList().size()>0){
                ogiList=  backorder.getOrderGoodsList();
            }
            //edit end
            // 遍历退单对象下面的商品Id
            for (int j = 0; j < strs.length; j++) {
                String strss = strs[j];
                // 获取第J个商品的Id
                Long goodsId = Long.valueOf(strss.substring(0, strss.indexOf(",")));
                // 根据ID获取单个商品的详细信息
                GoodsProductVo orderProductVo = backOrderMapper.selectGoodsById(goodsId);
                // 退货商品的价格
                /**
                 * 获取商品的实际价格
                 * add by 付陈林
                 * */

                for(OrderGoods ori:ogiList){
                    if(goodsId.equals(ori.getGoodsInfoId())){
                        orderProductVo.setGoodsInfoPreferPrice(ori.getGoodsInfoPrice());
                    }
                }
                /**end edit*/

                backOrderSumPrice = backOrderSumPrice.add(orderProductVo.getGoodsInfoPreferPrice());
                // 退货的商品数量 随意找的货品字段装的数据 只用于前台显示
                String counts = strss.substring(strss.indexOf(",") + 1, strss.length());
                // 装载商品数量数据
                orderProductVo.setGoodsCount(Long.valueOf(counts));
                // 退货的单个商品价格 随意找的货品字段装的数据 只用于前台显示
                BigDecimal price = orderProductVo.getGoodsInfoPreferPrice();
                BigDecimal count = new BigDecimal(counts);
                // 计算总价格
                BigDecimal sum = price.multiply(count);
                // 装载单类形 退单货品的总价 随意找的货品字段装的数据 只用于前台显示
//                orderProductVo.setGoodsInfoPreferPrice(sum);
                orderProductVo.setGoodsInfoSumPrice(sum);
                // 循环把查询获取到的商品信息放入到退单对象的商品集合中
                backOrders.getOrderGoodslistVo().add(orderProductVo);
            }
        }
        return backOrders;
    }

    /**
     * 根据退单号查询物流
     *
     * @param backOrderId
     * @return BackOrderGeneral
     */
    public BackOrderGeneral queryBackOrderGeneral(Long backOrderId) {
        return backOrderMapper.queryBackOrderGeneral(backOrderId);
    }

    /**
     * 退款成功后退回库存
     *
     * @param orderId
     * @param backOrderId
     * @return Integer
     */
    public Integer addStockOrderBack(Long orderId, Long backOrderId) {
        // 根据订单id查询仓库id
        Order order = orderMapper.orderDetail(orderId);
        // 根据订单id查询货品个数
        Integer count = 0;
        List<OrderGoods> orderGoods = orderGoodsMapper.selectBackGoodsList(orderId);
        for (int i = 0; i < orderGoods.size(); i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("productId", orderGoods.get(i).getGoodsInfoId());
            map.put("stock", orderGoods.get(i).getGoodsInfoNum());
            map.put("distinctId", order.getWareId());
            count += goodsProductMapper.addBaseStock(map);
        }
        return count;
    }

    /**
     * 查询退单的数量（已买）
     *
     * @param customerId
     * @return int
     */
    @Override
    public int queryBackOrderCountBuy(Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("customerId", customerId);
            return this.backOrderMapper.queryBackOrderCountBuy(map);
        } finally {
            map = null;
        }
    }

    /**
     * 按主键修改订单审核
     * 
     * @param backOrder
     * @return int
     */
    public int modifyBackOrderByCheck(BackOrder backOrder) {
        return backOrderMapper.updateByPrimaryKeySelective(backOrder);
    }

    public GoodsProductService getGoodsProductService() {
        return goodsProductService;
    }

    @Resource(name = "GoodsProductService")
    public void setGoodsProductService(GoodsProductService goodsProductService) {
        this.goodsProductService = goodsProductService;
    }

    public BackOrderMapper getBackOrderMapper() {
        return backOrderMapper;
    }

    @Resource(name = "BackOrderMapper")
    public void setBackOrderMapper(BackOrderMapper backOrderMapper) {
        this.backOrderMapper = backOrderMapper;
    }

    public OrderExpressMapper getOrderExpressMapper() {
        return orderExpressMapper;
    }

    @Resource(name = "OrderExpressMapper")
    public void setOrderExpressMapper(OrderExpressMapper orderExpressMapper) {
        this.orderExpressMapper = orderExpressMapper;
    }

    public OrderGiftMapper getOrderGiftMapper() {
        return orderGiftMapper;
    }

    @Resource(name = "OrderGiftMapper")
    public void setOrderGiftMapper(OrderGiftMapper orderGiftMapper) {
        this.orderGiftMapper = orderGiftMapper;
    }

    public OrderCouponMapper getOrderCouponMapper() {
        return orderCouponMapper;
    }

    @Resource(name = "OrderCouponMapper")
    public void setOrderCouponMapper(OrderCouponMapper orderCouponMapper) {
        this.orderCouponMapper = orderCouponMapper;
    }

    public OrderGoodsMapper getOrderGoodsMapper() {
        return orderGoodsMapper;
    }

    @Resource(name = "OrderGoodsMapper")
    public void setOrderGoodsMapper(OrderGoodsMapper orderGoodsMapper) {
        this.orderGoodsMapper = orderGoodsMapper;
    }

    public OrderMapper getOrderMapper() {
        return orderMapper;
    }

    @Resource(name = "OrderMapper")
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
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

    public CustomerConsumeMapper getCustomerConsumeMapper() {
        return customerConsumeMapper;
    }

    // Spring注入
    @Resource(name = "customerConsumeMapper")
    public void setCustomerConsumeMapper(CustomerConsumeMapper customerConsumeMapper) {
        this.customerConsumeMapper = customerConsumeMapper;
    }

    public GoodsProductMapper getGoodsProductMapper() {
        return goodsProductMapper;
    }

    @Resource(name = "GoodsProductMapper")
    public void setGoodsProductMapper(GoodsProductMapper goodsProductMapper) {
        this.goodsProductMapper = goodsProductMapper;
    }

    public CustomerPointMapper getCustomerPointMapper() {
        return customerPointMapper;
    }

    // Spring注入
    @Resource(name = "customerPointMapper")
    public void setCustomerPointMapper(CustomerPointMapper customerPointMapper) {
        this.customerPointMapper = customerPointMapper;
    }

    public PointLevelServiceMapper getPointLevelServiceMapper() {
        return pointLevelServiceMapper;
    }

    // Spring注入
    @Resource(name = "pointLevelServiceMapper")
    public void setPointLevelServiceMapper(PointLevelServiceMapper pointLevelServiceMapper) {
        this.pointLevelServiceMapper = pointLevelServiceMapper;
    }

    public IntegralSetMapper getIntegralSetmapper() {
        return integralSetmapper;
    }

    // Spring注入
    @Resource(name = "integralSetMapper")
    public void setIntegralSetmapper(IntegralSetMapper integralSetmapper) {
        this.integralSetmapper = integralSetmapper;
    }

    public CustomerInfoMapper getCustomerInfoMapper() {
        return customerInfoMapper;
    }

    // Spring注入
    @Resource(name = "customerInfoMapper")
    public void setCustomerInfoMapper(CustomerInfoMapper customerInfoMapper) {
        this.customerInfoMapper = customerInfoMapper;
    }

}
