/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.CouponMarketing;

/**
 * 优惠券促销信息 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface CouponMarketingMapper {

    /**
     * 查询优惠券促销信息列表
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return List
     */
    List<CouponMarketing> selectCouponMarketingListByMarketingId(
            Long marketingId);

    /**
     * 插入买送优惠券
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int insertCouponMarketing(List<CouponMarketing> list);

    /**
     * 删除优惠券促销信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteCouponMarketing(Long marketingId);

}
