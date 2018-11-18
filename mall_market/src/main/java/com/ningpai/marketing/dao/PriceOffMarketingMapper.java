/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.PriceOffMarketing;

/**
 * 直降信息表 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface PriceOffMarketingMapper {

    /**
     * 查询直降信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return PriceOffMarketing
     */
    PriceOffMarketing selectPriceOffMarketingByMarketingId(Long marketingId);

    /**
     * 插入直降信息
     * 
     * @param poff
     *            {@com.ningpai.marketing.bean.PriceOffMarketing}
     * @return int
     */
    int insertPriceOffMarketing(PriceOffMarketing poff);

    /**
     * 删除直降
     * 
     * @param marketingId
     * @return int
     */
    int deletePriceOffMarketing(Long marketingId);

    /**
     * 修改促销信息
     * 
     * @param poff
     * @return
     */
    int modifyPriceOffMarketing(PriceOffMarketing poff);

}
