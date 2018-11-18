/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.util;

import java.math.BigDecimal;

/**
 * 查询特定日期的订单的数量和总价格的辅助Bean
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年6月18日 上午11:49:59
 * @version 1.0
 */
public class DaysOrderUtil {
    /**
     * 订单数量
     */
    private Integer orderCount;
    /**
     * 订单总价格
     */
    private BigDecimal orderTotal;

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public BigDecimal getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(BigDecimal orderTotal) {
        this.orderTotal = orderTotal;
    }

}
