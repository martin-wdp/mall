/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.marketing.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.marketing.bean.MarketingRushTime;

/**
 * 抢购时间段
 * 
 * @author NINGPAI-LIH
 * 
 */
public interface MarketingRushTimeMapper {

    /**
     * 查询总数
     * 
     * @return 总数
     */
    int queryRushTimeCount();

    /**
     * 查询抢购时间段集合
     * 
     * @param map
     *            分页参数
     * @return 集合
     */
    List<Object> queryRustTimeList(Map<String, Object> map);

    /**
     * 添加抢购时间
     * 
     * @param rushTime
     *            添加参数
     * @return
     */
    int insertSelective(MarketingRushTime rushTime);

    /**
     * 修改抢购时间
     * 
     * @param rushTime
     *            抢购
     * @return
     */
    int updateByPrimaryKeySelective(MarketingRushTime rushTime);

    /**
     * 删除抢购时间
     * 
     * @param tId
     *            抢购时间id
     * @return
     */
    int deleteByPrimaryKey(Long tId);

    /**
     * 批量删除抢购时间数组
     * 
     * @param list要删除的id数组
     * @return
     */
    int delRushTimes(List<Long> list);

    /**
     * 查询可以的抢购时间列表
     * 
     * @return
     */
    List<MarketingRushTime> queryRustTimeListByFlag();
}
