/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.FullbuyPresentMarketing;

/**
 * 满赠促销 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface FullbuyPresentMarketingMapper {

    /**
     * 查询满赠促销信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return List
     */
    List<FullbuyPresentMarketing> selectFullbuyPresentMarketingListByMarketingId(
            Long marketingId);

    /**
     * 插入满赠
     * 
     * @param list
     *            {@link java.util.List}
     * @return int
     */
    int insertFullbuyPresentMarketing(List<FullbuyPresentMarketing> list);

    /**
     * 删除满赠
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @return int
     */
    int updateFullbuyPresentMarketing(Long marketingId);

}
