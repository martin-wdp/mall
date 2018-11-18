/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.third.order.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import com.ningpai.customer.bean.Customer;
import com.ningpai.order.bean.Order;
import com.ningpai.order.service.OrderService;
import com.ningpai.third.order.mapper.ThirdOrderMapper;
import com.ningpai.third.order.service.ThirdOrderService;
import com.ningpai.third.order.util.OrderValueUtil;
import com.ningpai.util.DaysOrderUtil;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;

/**
 * 第三方订单servcie
 *
 * @author NINGPAI-LIH
 * @version 2.0
 * @since 2014年5月19日17:41:01
 */
@Service("ThirdOrderService")
public class ThirdOrderServiceImpl implements ThirdOrderService {

    private static final String THIRDID = "thirdId";

    /**
     * 第三方订单Dao
     */
    private ThirdOrderMapper mapper;

    /**
     * 订单dao
     */
    @Resource(name = "OrderService")
    private OrderService bossOrderService;

    /**
     * 根据条件查询订单信息
     * 
     * @param pb
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @return
     */
    public PageBean searchOrderList(PageBean pb, Order order) {
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        try {
            // 查询第三方订单行数
            pb.setRows(mapper.searchThridOrderCountRow(paramMap));
            // 查询条件封装
            paramMap.put("start", pb.getStartRowNum());
            paramMap.put("number", pb.getEndRowNum());
            // 获得第三方订单列表
            // pb.setList(mapper.searchThridOrderList(paramMap));

            // 保存遍历出来的新的订单集合
            // List<Object> orderlist = new ArrayList<Object>();
            // 获取数据库的订单集合
            List<Object> orders = mapper.searchThridOrderList(paramMap);
            pb.setList(orders);
            return pb;
        } finally {
            paramMap = null;
        }
    }

    /**
     * 根据条件查询订单信息
     * 
     * @param pb
     *            分页参数
     * @param order
     *            订单查询参数
     * @return
     */

    public PageBean searchOrderListByOrderCargo(PageBean pb, Order order) {
        // 默认查询未拣货
        if (order.getOrderCargoStatus() == null) {
            order.setOrderCargoStatus("0");
        }
        // 查询订单为已付款的订单
        order.setOrderStatus("1");
        return searchOrderList(pb, order);
    }

    /**
     * 修改订单信息
     * 
     * @param order
     */
    public void updateThirdOrderByParam(Order order) {
        mapper.updateThirdOrderByParam(order);
    }

    /**
     * 修改第三方商家后台订单情况
     * 
     * @param order
     */
    public void updateThirdOrder(Order order) {
        mapper.updateThirdOrder(order);
    }

    /**
     * 根据主键查询单个的订单对象信息
     * 
     * @param orderId
     *            要查询的订单id
     * @return
     */
    public Order searcharOrderByParam(Long orderId) {
        return mapper.searcharOrderByParam(orderId);
    }

    /**
     * 批量更新订单信息
     * 
     * @param orderId
     */
    public void updateThirdOrderByParams(Long[] orderId) {
        for (int i = 0; i < orderId.length; i++) {
            Order order = new Order();
            // 设置订单ID
            order.setOrderId(orderId[i]);
            // 设置订单的状态
            order.setOrderStatus(OrderValueUtil.ORDERSTATUS);
            // 更新订单信息
            mapper.updateThirdOrderByParam(order);
        }
    }

    public ThirdOrderMapper getMapper() {
        return mapper;
    }

    @Resource(name = "ThridOrderMapper")
    public void setMapper(ThirdOrderMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * 查询商家的订单数量
     * 
     * @param status
     *            订单状态
     * @param thirdId
     *            第三方ID
     * @return
     */
    public int queryOrderCountByStaAndThirdId(String status, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 订单状态
            map.put("status", status);
            // 商家ID
            map.put(THIRDID, thirdId);
            // 根据参数查询订单个数
            return this.mapper.queryOrderCountBySta(map);
        } finally {
            map = null;
        }
    }

    /**
     * 查询指定商铺的查询特定日期的订单的数量和总价格
     * 
     * @param flag
     *            标记 0:前一天 1:当前天 {@link Integer}
     * @param thirdId
     *            第三方ID {@link Long}
     * @return
     */
    public DaysOrderUtil querySalesOrderCountByFlag(int flag, Long thirdId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (0 == flag) {
                // 查询前一天的订单数量和订单总金额
                return this.mapper.queryYestSalesOrderCount(map);
            } else {
                return this.mapper.queryTodaySalesOrderCount(map);
            }
        } finally {
            map = null;
        }
    }

    /**
     * 查询订单信息
     * 
     * @param orderId
     *            订单Id
     * @param request
     * @return
     */
    public Map<String, Object> orderPicking(Long[] orderId, HttpServletRequest request) {
        Customer customerInfo;
        String pickingName;
        Map<String, Object> map;
        try {
            // 当前登录者
            customerInfo = (Customer) request.getSession().getAttribute("cust");
            // 当前登录者信息
            pickingName = customerInfo.getCustomerUsername();
            // 拣货
            map = bossOrderService.queryByPincking(orderId, (Long) request.getSession().getAttribute(THIRDID), pickingName, null);
            return map;
        } finally {
            customerInfo = null;
            pickingName = null;
            map = null;
        }
    }

    /**
     * 批量更改订单状态
     * 
     * @param orderId
     *            需要拣货的订单ID
     * @param request
     * @return
     */
    @Override
    public int goOrderPicking(Long[] orderId, HttpServletRequest request) {

        // 批量更改订单状态
        return bossOrderService.updateOrderCargoStatusByThirdOrderIds(orderId, "1", (Long) request.getSession().getAttribute(THIRDID));
    }

    /**
     * 根据参数查询已买订单个数
     * 
     * @param status
     *            订单状态
     * @param customerId
     *            第三方ID
     * @return
     */
    @Override
    public int queryOrderCountByStaAndCustomerId(String status, Long customerId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 状态
            map.put("status", status);
            // 会员ID
            map.put("customerId", customerId);
            // 根据参数查询已买订单个数
            return this.mapper.queryCustomerOrderCountBySta(map);
        } finally {
            map = null;
        }
    }

    /**
     * 获得第三方订单列表
     * 
     * @param pb
     * @param order
     *            {@link com.ningpai.order.bean.Order}
     * @return
     */
    @Override
    public PageBean searchBuyOrderList(PageBean pb, Order order) {
        Map<String, Object> paramMap = MapUtil.getParamsMap(order);
        try {
            // 查询第三方已购买订单行数
            pb.setRows(mapper.searchPuyThridOrderCountRow(paramMap));
            // 查询条件封装 分页开始行数 分页终止行数
            paramMap.put("start", pb.getStartRowNum());
            paramMap.put("number", pb.getEndRowNum());
            // 获得第三方订单列表
            pb.setList(mapper.searchPuyThridOrderList(paramMap));
            return pb;
        } finally {
            paramMap = null;
        }
    }
}
