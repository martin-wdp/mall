/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.dao;

import com.ningpai.order.bean.OrderPicking;

/**
 * 拣货or出库dao
 * 
 * @author NINGPAI-LIH
 * @since 2014年6月25日10:06:15
 * 
 */
public interface OrderPickingMapper {

    /**
     * 插入拣货or出库单信息
     * 
     * @param orPicking
     *            参数
     * @param orPicking
     *            .picking_status 0：拣货 1：出库
     * @return
     */
    void insertSelective(OrderPicking orPicking);

}
