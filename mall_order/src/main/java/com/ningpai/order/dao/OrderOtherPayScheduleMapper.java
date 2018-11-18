package com.ningpai.order.dao;

import java.util.List;

import com.ningpai.order.bean.OrderOtherPaySchedule;

/**
 * 订单支付信息
 * @author ggn
 *
 */
public interface OrderOtherPayScheduleMapper {

    /**
     * 根据参数插入信息
     * 
     * @param record
     * @return
     */
    int insertSelective(OrderOtherPaySchedule record);

    /**
     * 根据订单编号查询信息
     * 
     * @param orderManyId
     * @return
     */
    OrderOtherPaySchedule selectByPrimaryKey(String orderCode);

    /**
     * 根据订单编号修改信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(OrderOtherPaySchedule record);

    /**
     * 代付付款
     * 
     * @param otherPaySchedule
     * @return
     */
    int payOther(OrderOtherPaySchedule otherPaySchedule);

    /**
     * 查询需要退款的多人代付
     * 
     * @return 需要退款的多人代付信息
     */
    List<OrderOtherPaySchedule> queryOrderOtherPayRefund();

}
