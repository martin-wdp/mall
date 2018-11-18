/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.marketing.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.marketing.bean.MarketingGroup;
import com.ningpai.marketing.dao.MarketingGroupMapper;

/**
 * 优惠分组daoimpl
 * 
 * @author NingPai-lih
 * @since 2014年6月6日09:44:44
 */
@Repository("MarketingGroupMapper")
public class MarketingGroupMapperImpl extends BasicSqlSupport implements
        MarketingGroupMapper {

    /*
     * 按条件查询所有的分组信息
     * 
     * @return 所有的分组信息
     * 
     * @see com.ningpai.marketing.dao.MarketingGroupMapper#selectAll()
     */
    @Override
    public List<MarketingGroup> selectAll() {
        return this
                .selectList("com.ningpai.marketing.dao.MarketingGroupMapper.selectAll");
    }

    /*
     * 按条件查询所有的分组信息
     * 
     * @return 所有的分组信息
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#selectByPrimary(java.util
     * .Map)
     */
    @Override
    public List<Object> selectByPrimary(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.marketing.dao.MarketingGroupMapper.selectByPrimary",
                        map);
    }

    /*
     * 查询列表总数
     * 
     * @param map
     * 
     * @return
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#selectGourpListCount(java
     * .util.Map)
     */
    @Override
    public int selectGourpListCount(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingGroupMapper.selectGourpListCount",
                        map);
    }

    /*
     * 插入促销分组
     * 
     * @param group 要插入的分组
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#insertMarketingGroup(com
     * .ningpai.marketing.bean.MarketingGroup)
     */
    @Override
    public void insertMarketingGroup(MarketingGroup group) {
        this.insert(
                "com.ningpai.marketing.dao.MarketingGroupMapper.insertMarketingGroup",
                group);
    }

    /*
     * 修改促销分组
     * 
     * @param group 修改参数
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#updateByPrimaryKeySelective
     * (com.ningpai.marketing.bean.MarketingGroup)
     */
    @Override
    public void updateByPrimaryKeySelective(MarketingGroup group) {
        this.update(
                "com.ningpai.marketing.dao.MarketingGroupMapper.updateByPrimaryKeySelective",
                group);
    }

    /*
     * 查询分组是否能被删除
     * 
     * @param groupId 要删除id
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#delGroupByCodexIs(int)
     */
    @Override
    public int delGroupByCodexIs(Long groupId) {
        return this
                .selectOne(
                        "com.ningpai.marketing.dao.MarketingGroupMapper.delGroupByCodexIs",
                        groupId);
    }

    /*
     * 根据id删除优惠分组
     * 
     * @param groupId
     * 
     * @see
     * com.ningpai.marketing.dao.MarketingGroupMapper#deleteByPrimaryKey(java
     * .lang.Long)
     */
    @Override
    public void deleteByPrimaryKey(Long groupId) {
        this.delete(
                "com.ningpai.marketing.dao.MarketingGroupMapper.deleteByPrimaryKey",
                groupId);
    }

}
