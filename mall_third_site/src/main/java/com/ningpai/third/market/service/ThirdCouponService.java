package com.ningpai.third.market.service;

import java.util.List;

/**
 * 第三方优惠券service接口
 * 
 * @author qiyuanyuan
 *
 */
public interface ThirdCouponService {

    /**
     * 查询范围信息
     * 
     * @param type
     *            {@link java.lang.String}
     * @return Object
     */
    List<Object> selectThirdMarketingScope(Long couponId, String type);

}
