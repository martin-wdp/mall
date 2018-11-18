/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service;

import java.util.List;

import com.ningpai.order.bean.OrderOtherPay;

/**
 * 代付
 * @author ggn
 *
 */
public interface OrderOtherPayService {
    /**
     * 插入代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    int insertOtherPay(OrderOtherPay orderOtherPay);

    /**
     * 更新代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    int updateOtherPay(OrderOtherPay orderOtherPay);

    /**
     * 查询代付信息
     * 
     * @return
     */
    public OrderOtherPay queryOtherPayByOrderCode(OrderOtherPay orderOtherPay);

    /**
     * 根据代付编号查询代付信息
     * 
     * @param orderPayCode
     *            代付编号
     * @return
     */
    public OrderOtherPay selectOthertByOrderPayCode(String orderPayCode);

    /**
     * 判断是否可以支付
     * 
     * @return
     */
    public Boolean isNoPay(OrderOtherPay otherPay);

    /**
     * 根据订单编号查询成功的代付信息
     * 
     * @param orderCode
     *            订单编号
     * @return 代付成功的结果集
     */
    List<OrderOtherPay> queryOrderPayBySuccess(String orderCode);

    /**
     * 查询需要退款的付款信息
     * 
     * @param orderCode
     * @return
     */
    List<OrderOtherPay> queryOrderPayRefund(String orderCode);
}
