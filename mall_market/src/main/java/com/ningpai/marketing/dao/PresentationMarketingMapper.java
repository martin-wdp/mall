/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;

import com.ningpai.marketing.bean.PresentationMarketing;

/**
 * 买赠接口 2014-03-25
 * 
 * @author ggn
 * 
 */
public interface PresentationMarketingMapper {

    /**
     * 查询促销赠品信息
     * 
     * @param marketingId
     * @return List
     */
    List<PresentationMarketing> selectPresentationMarketingListByMarketingId(
            Long marketingId);

    /**
     * 批量插入赠品信息
     * 
     * @param list
     * @return int
     */
    int insertPresentationMarketing(List<PresentationMarketing> list);

    /**
     * 删除买赠
     * 
     * @param marketingId
     * @return int
     */
    int deletePresentationMarketing(Long marketingId);

}
