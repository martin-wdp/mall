/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service;

import com.ningpai.util.PageBean;

/**
 * E店宝同步订单Service接口
 * 
 * @author qiyuanyuan
 *
 */
public interface SynOrderService {

    /**
     * 同步订单到Ｅ点宝
     * 
     * @param orderId
     * @return　int
     */
    int SynOrder(Long orderId);

    /**
     * 获取E店宝订单信息并修改当前订单信息
     * 
     * @param orderId
     * @return　int
     */
    int getOrder(Long[] orderId);

    /**
     * 订单日志
     * 
     * @param pb
     * @return
     */
    PageBean selectOrderELogList(PageBean pb);

    /**
     * 重新导入e店宝订单
     * 
     * @param orderId
     * @return
     */
    int upLog(Long orderId, Long eId);
}
