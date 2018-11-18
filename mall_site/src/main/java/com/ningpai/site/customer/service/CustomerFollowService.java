/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.site.customer.service;

import com.ningpai.util.PageBean;

import java.util.List;
import java.util.Map;

/**
 * 收藏商品Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月14日 下午2:05:49
 * @version 0.0.1
 */
public interface CustomerFollowService {
    /**
     * 查询收藏记录
     * 
     * @param paramMap
     *            查询条件
     * @return List<Browserecord> {@link java.util.List}
     */
    PageBean selectCustomerFollow(Map<String, Object> paramMap, PageBean pb);

    /**
     * 商品列表专用 查询当前会员是否
     * 
     * @param paramMap
     * @return
     */
    List<String> selectCustomerFollowForList(Map<String, Object> paramMap);

    /**
     * 取消关注
     * 
     * @param followId
     *            关注编号
     * @return 0 失败 1成功
     */
    int deleteFollow(Long followId, Long customerId);
}
