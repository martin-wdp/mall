package com.ningpai.api.bean;

import java.util.List;

/**
 * 订单详细信息
 * @author lih
 * @version 2.0
 * @since 15/8/31
 */
public class OOrderAllInfo extends OOrder{
    /**
     * 订单商品
     */
    private List<OOrderGoods> orderGoodses;
    /**
     * 订单商品
     */
    public List<OOrderGoods> getOrderGoodses() {
        return orderGoodses;
    }

    public void setOrderGoodses(List<OOrderGoods> orderGoodses) {
        this.orderGoodses = orderGoodses;
    }
}
