/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.service;

import com.ningpai.order.bean.BarterOrder;
import com.ningpai.util.PageBean;

/**
 * 换单业务层
 * 
 * @author YANhb
 * 
 */
public interface BarterService {

    /**
     * 换单分页
     * 
     * @param pageBean
     * @param barterOrder
     * @return PageBean
     */
    PageBean queryBarterPageSize(PageBean pageBean, BarterOrder barterOrder,
            String startTime, String endTime);

    /**
     * 批量删除
     * 
     * @param barterOrderIds
     * @return int
     */
    int batchDelBarterOrder(Long[] barterOrderIds);

    /**
     * 换单详细信息
     * 
     * @param barterOrderId
     * @return BarterOrder
     */
    BarterOrder queryBarterDetails(Long barterOrderId);

    /**
     * 修改换货单审核约定
     * 
     * @param barterOrder
     * @return int
     */
    int modifyBarterCheck(BarterOrder barterOrder);

    /**
     * 查询到的换单数量
     * 
     * @param thirdId
     *            第三方ID {@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBarterOrderCount(Long thirdId);

    /**
     * 查询到的换单数量(已买)
     * 
     * @param customerId
     *            customerID {@link Long}
     * @return 查询到的数量 {@link Integer}
     */
    int queryBarterOrderCountBuy(Long customerId);

}
