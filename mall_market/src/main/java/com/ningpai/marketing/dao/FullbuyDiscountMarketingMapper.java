/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.FullbuyDiscountMarketing;

/**
 * 满折活动 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface FullbuyDiscountMarketingMapper {

    /**
     * 查询满折活动
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return FullbuyDiscountMarketing
     */
    FullbuyDiscountMarketing selectFullbuyDiscountMarketingByMarketingId(
            Long marketingId);

    /**
     * 插入满折信息
     * 
     * @param fdm
     *            {@link com.ningpai.marketing.bean.FullbuyDiscountMarketing}
     * @return int
     */
    int insertFullbuyDiscountMarketing(FullbuyDiscountMarketing fdm);

    /**
     * 删除满折信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteFullbuyDiscountMarketing(Long marketingId);

    /**
     * 修改满折信息
     * 
     * @param fdm
     * @return
     */
    int modifyFullbuyDiscountMarketing(FullbuyDiscountMarketing fdm);

    /**
     * 根据促销ID查询满折信息
     * 
     * @param marketingId
     *            促销id{@link java.lang.Long}
     * @return list
     */
    List<FullbuyDiscountMarketing> selectFullbuyDiscountMarketingsByMarketingId(
            Long marketingId);

}
