/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketingRushTime;
import com.ningpai.marketing.dao.MarketingRushTimeMapper;

/**
 * 抢购时间段
 * 
 * @author NINGPAI-LIH
 * @since 2014年11月24日14:46:39
 * 
 */
@Repository("MarketingRushTimeMapper")
public class MarketingRushTimeMapperImpl extends BasicSqlSupport implements
        MarketingRushTimeMapper {

    /*
     * 查询总数
     * 
     * @return 总数
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#queryRushTimeCount()
     */
    @Override
    public int queryRushTimeCount() {
        return this
                .selectOne("com.ningpai.marketing.dao.MarketingRushTimeMapper.queryRushTimeCount");
    }

    /*
     * 查询抢购时间段集合
     * 
     * @param map 分页参数
     * 
     * @return 集合
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#queryRustTimeList(java
     * .util.Map)
     */
    @Override
    public List<Object> queryRustTimeList(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingRushTimeMapper.queryRustTimeList",
                        map);
    }

    /*
     * 添加抢购时间
     * 
     * @param rushTime 添加参数
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#insertSelective(com
     * .ningpai.marketing.bean.MarketingRushTime)
     */
    @Override
    public int insertSelective(MarketingRushTime rushTime) {
        return this
                .insert("com.ningpai.marketing.dao.MarketingRushTimeMapper.insertSelective",
                        rushTime);
    }

    /*
     * 修改抢购时间
     * 
     * @param rushTime 抢购
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.MarketingRushTime)
     */
    @Override
    public int updateByPrimaryKeySelective(MarketingRushTime rushTime) {
        return this
                .update("com.ningpai.marketing.dao.MarketingRushTimeMapper.updateByPrimaryKeySelective",
                        rushTime);
    }

    /*
     * 删除抢购时间
     * 
     * @param tId 抢购时间id
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#deleteByPrimaryKey(int)
     */
    @Override
    public int deleteByPrimaryKey(Long tId) {
        return this
                .update("com.ningpai.marketing.dao.MarketingRushTimeMapper.deleteByPrimaryKey",
                        tId);

    }

    /*
     * 
     * 批量删除抢购时间数组
     * 
     * @param list要删除的id数组
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#delRushTimes(java.util
     * .List)
     */
    @Override
    public int delRushTimes(List<Long> list) {
        return this
                .update("com.ningpai.marketing.dao.MarketingRushTimeMapper.delRushTimes",
                        list);
    }

    /*
     * 查询可以的抢购时间列表
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingRushTimeMapper#queryRustTimeListByFlag
     * ()
     */
    @Override
    public List<MarketingRushTime> queryRustTimeListByFlag() {
        return this
                .selectList("com.ningpai.marketing.dao.MarketingRushTimeMapper.queryRustTimeListByFlag");
    }

}
