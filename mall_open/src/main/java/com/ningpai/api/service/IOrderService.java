package com.ningpai.api.service;

/**
 * 开放接口--订单service
 * @author lih
 * @version 2.0
 * @since 15/8/31
 */
public interface IOrderService {

    /**
     * 订单列表
     * @param pageNo 页数
     * @param pageSize 每页大小
     * @return 查询集合
     */
     String list(Integer pageNo, Integer pageSize);

    /**
     * 订单详情
     * @param orderCode 订单编号
     * @return
     */
     String get(String orderCode);

}
