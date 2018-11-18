/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.third.market.service;

/**
 * 新第三方促销范围service接口
 * 
 * @author qiyuanyuan
 *
 */
public interface ThirdMarketingService {

    /**
     * 查询范围信息
     * 
     * @param marketingId
     *            {@link java.lang.Long}
     * @param type
     *            {@link java.lang.String}
     * @param thirdId
     *            {@link java.lang.Long}
     * @return Object
     */
    Object selectThirdMarketingScope(Long marketingId, String type, Long thirdId);

}
