/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.util;

import com.ningpai.order.bean.OrderContainerRelation;

/**
 * 订单物流追踪
 * 
 * @author NINGPAI-LIH
 * 
 */
public class OrderContainerUtil {

    /**
     * 订单下商品信息
     */
    private OrderContainerRelation containerRelations;

    /**
     * 订单物流
     */
    private String Express;

    /**
     * 物流名称
     */
    private String expressName;

    public String getExpressName() {
        return expressName;
    }

    public void setExpressName(String expressName) {
        this.expressName = expressName;
    }

    public OrderContainerRelation getContainerRelations() {
        return containerRelations;
    }

    public void setContainerRelations(OrderContainerRelation containerRelations) {
        this.containerRelations = containerRelations;
    }

    public String getExpress() {
        return Express;
    }

    public void setExpress(String express) {
        Express = express;
    }

}
