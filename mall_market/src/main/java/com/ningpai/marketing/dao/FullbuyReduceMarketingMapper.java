/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.FullbuyReduceMarketing;

/**
 * 满减促销接口 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface FullbuyReduceMarketingMapper {

    /**
     * 查询满减活动信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return FullbuyReduceMarketing
     */
    FullbuyReduceMarketing selectFullbuyReduceMarketingByMarketingId(
            Long marketingId);

    /**
     * 插入满减
     * 
     * @param frm
     *            {@link com.ningpai.marketing.bean.FullbuyReduceMarketing}
     * @return int
     */
    int insertFullbuyReduceMarketing(FullbuyReduceMarketing frm);

    /**
     * 删除满减
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int deleteFullbuyReduceMarketing(Long marketingId);

    /**
     * 修改满减
     * 
     * @param frm
     * @return
     */
    int modifyFullbuyReduceMarketing(FullbuyReduceMarketing frm);

    /**
     * 根据促销ID查询促销满减信息
     * 
     * @param marketingId
     *            促销ID{@link java.lang.Long}
     * @return list
     */
    List<FullbuyReduceMarketing> selectFullbuyReduceMarketingsByMarketingId(
            Long marketingId);

}
