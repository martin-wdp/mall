/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.order.bean.OrderOtherPay;
import com.ningpai.order.bean.OrderOtherPaySchedule;
import com.ningpai.order.dao.OrderOtherPayMapper;
import com.ningpai.order.service.OrderOtherPayScheduleService;
import com.ningpai.order.service.OrderOtherPayService;

/**
 * 代付
 * 
 * @author ggn
 *
 */
@Service("OrderOtherPayService")
public class OrderOtherOtherPayServiceImpl implements OrderOtherPayService {

    @Resource(name = "OrderOtherPayMapper")
    private OrderOtherPayMapper mapper;

    @Resource(name = "OrderOtherPayScheduleService")
    private OrderOtherPayScheduleService otherPayScheduleService;

    /**
     * 插入代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    public int insertOtherPay(OrderOtherPay orderOtherPay) {
        return mapper.insertSelective(orderOtherPay);
    }

    /**
     * 更新代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    public int updateOtherPay(OrderOtherPay orderOtherPay) {
        return mapper.updateByOrderCodex(orderOtherPay);
    }

    /**
     * 查询代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    public OrderOtherPay queryOtherPayByOrderCode(OrderOtherPay orderOtherPay) {
        return mapper.queryOrderPayByOrderCode(orderOtherPay);
    }

    /**
     * 根据代付编号查询代付信息
     * 
     * @param orderPayCode
     *            代付编号
     * @return
     */
    public OrderOtherPay selectOthertByOrderPayCode(String orderPayCode) {
        return mapper.queryOrderByCode(orderPayCode);
    }

    /**
     * 判断是否可以支付
     * 
     * @param otherPay
     * @return
     */
    public Boolean isNoPay(OrderOtherPay otherPay) {
        Boolean bool = false;
        List<OrderOtherPay> lists = mapper.queryOrderPayBylately(otherPay.getOrderCode());
        // 总金额
        BigDecimal sum = new BigDecimal(0);
        OrderOtherPaySchedule otherPaySchedule = otherPayScheduleService.selectOrderOtherPayScheduleByOrderCode(otherPay.getOrderCode());
        for (int i = 0; i < lists.size(); i++) {
            // 累加当前正在支付的金额
            sum = sum.add(lists.get(i).getOrderPayPrice());
        }
        sum = sum.add(otherPay.getOrderPayPrice());
        // 判断金额是否超出
        if (otherPaySchedule.getOrderResiduePrice().subtract(sum).signum() < 0) {
            bool = true;
        }
        return bool;
    }

    /**
     * 根据订单编号查询成功的代付信息
     * 
     * @param orderCode
     *            订单编号
     * @return
     */
    @Override
    public List<OrderOtherPay> queryOrderPayBySuccess(String orderCode) {
        return mapper.queryOrderPayBySuccess(orderCode);
    }

    /**
     * 查询需要退款的付款信息
     * 
     * @param orderCode
     * @return
     */
    @Override
    public List<OrderOtherPay> queryOrderPayRefund(String orderCode) {
        return mapper.queryOrderPayRefund(orderCode);
    }
}
