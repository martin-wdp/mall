package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderOtherPay;

/**
 * 代付dao
 * 
 * @author NINGPAI-LIH
 * 
 */
public interface OrderOtherPayMapper {

    /**
     * 根据订单编号查看其所有代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    OrderOtherPay queryOrderPayByOrderCode(OrderOtherPay orderOtherPay);

    /**
     * 根据订单编号修改代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    int updateByOrderCodex(OrderOtherPay orderOtherPay);

    /**
     * 根据代付编号查询单个代付信息
     * 
     * @param orderPayCode
     * @return
     */
    OrderOtherPay queryOrderByCode(String orderPayCode);

    /**
     * 根据代付id修改代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    int updateByOtherPayId(OrderOtherPay orderOtherPay);

    /**
     * 插入代付信息
     * 
     * @param orderOtherPay
     * @return
     */
    int insertSelective(OrderOtherPay orderOtherPay);

    /**
     * 查询5分钟内发起的未完成付款
     * 
     * @return
     */
    List<OrderOtherPay> queryOrderPayBylately(String orderCode);

    /**
     * 根据订单编号查询代付成功的信息
     * 
     * @param orderCode
     *            订单编号
     * @return 查询到的代付信息集合
     */
    List<OrderOtherPay> queryOrderPayBySuccess(String orderCode);

    /**
     * 根据 订单编号查询需要退款付款信息
     * 
     * @param orderCode
     *            订单编号
     * @return
     */
    List<OrderOtherPay> queryOrderPayRefund(String orderCode);
}
