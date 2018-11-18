/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import com.ningpai.marketing.bean.DiscountMarketing;

/**
 * 买折促销接口 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface DiscountMarketingMapper {
    /**
     * 查询买折信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return DiscountMarketing
     */
    DiscountMarketing selectDiscountMarketingByMarketingId(Long marketingId);

    /**
     * 插入买着信息
     * 
     * @param dm
     *            {@link com.ningpai.marketing.bean.DiscountMarketing}
     * @return int
     */
    int insertDiscountMarketing(DiscountMarketing dm);

    /**
     * 删除买折信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteDiscountMarketing(Long marketingId);

    /**
     * 修改买折信息
     * 
     * @param dm
     * @return
     */
    int modifyDiscountMarketing(DiscountMarketing dm);

}
