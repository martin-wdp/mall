/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.m.customer.mapper.impl;

import com.ningpai.m.customer.mapper.BrowserecordMapper;
import com.ningpai.m.customer.vo.Browserecord;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author NINGPAI-zhangqiang
 * @since 2014年4月12日 下午4:57:39
 * @version 0.0.1
 */
@Repository("browserecordMapperM")
public class BrowserecordMapperImpl extends BasicSqlSupport implements
        BrowserecordMapper {
    /**
     * 根据主键删除
     * @param map
     *            浏览编号
     * @return
     */
    @Override
    public int deleteByPrimaryKey(Map<String, Object> map) {
        return this
                .update("com.ningpai.m.customer.mapper.BrowserecordMapper.deleteByPrimaryKey",
                        map);
    }
    /**
     * 根据用户ID删除
     * @param customerId
     *            浏览编号
     * @return
     */
    @Override
    public int deleteByCustomerId(Long customerId) {
        return this
                .update("com.ningpai.m.customer.mapper.BrowserecordMapper.deleteByCustomerId",
                        customerId);
    }

    /**
     * 插入数据
     * @param record
     * @return
     */
    @Override
    public int insert(Browserecord record) {
        return 0;
    }

    /**
     * 按条件插入数据
     * @param record
     * @return
     */
    @Override
    public int insertSelective(Browserecord record) {
        return 0;
    }

    /**
     * 按照主键编号查找
     * @param likeId
     *            浏览编号
     * @return
     */
    @Override
    public Browserecord selectByPrimaryKey(Long likeId) {
        return null;
    }

    /**
     * 按条件修改信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKeySelective(Browserecord record) {
        return 0;
    }

    /**
     * 根据主键编号修改信息
     * @param record
     * @return
     */
    @Override
    public int updateByPrimaryKey(Browserecord record) {
        return 0;
    }

    /**
     * 查询浏览记录
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public List<Browserecord> selectBrowserecord(Long customerId) {
        return this
                .selectList(
                        "com.ningpai.m.customer.mapper.BrowserecordMapper.selectBrowserecord",
                        customerId);
    }
    /**
     * 查询浏览记录
     * @param customerId
     *            会员编号
     * @return
     */
    @Override
    public int selectBrowserecordCount(Long customerId) {
        return this
                .selectOne(
                        "com.ningpai.m.customer.mapper.BrowserecordMapper.selectBrowserecordCount",
                        customerId);
    }

}
