/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.FullbuyCouponMarketing;

/**
 * 满赠优惠券接口 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface FullbuyCouponMarketingMapper {

    /**
     * 查询满赠优惠信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return List
     */
    List<FullbuyCouponMarketing> selectFullbuyCouponMarketingListByMarketingId(
            Long marketingId);

    /**
     * 批量插入
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int insertFullbuyCouponMarketing(List<FullbuyCouponMarketing> list);

    /**
     * 删除满送优惠券
     * 
     * @param marketingId
     * @return int
     */
    int deleteFullbuyCouponMarketing(Long marketingId);

}
