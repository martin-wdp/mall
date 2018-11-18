/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.dao.ChannelBarMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月9日下午6:21:56
 */
@Repository("ChannelBarMapper")
public class ChannelBarMapperImpl extends BasicSqlSupport implements ChannelBarMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long barId) {

        return this.delete("com.ningpai.channel.dao.ChannelBarMapper.deleteByPrimaryKey", barId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#insert(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int insert(ChannelBar record) {

        return this.insert("com.ningpai.channel.dao.ChannelBarMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#insertSelective(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int insertSelective(ChannelBar record) {

        return this.insert("com.ningpai.channel.dao.ChannelBarMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelBar record) {

        return this.update("com.ningpai.channel.dao.ChannelBarMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int updateByPrimaryKey(ChannelBar record) {

        return this.update("com.ningpai.channel.dao.ChannelBarMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelBar selectByPrimaryKey(Long barId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelBarMapper.selectByPrimaryKey", barId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#selectchannelBarCountByParam(java.util.Map)
     */
    @Override
    public Integer selectChannelBarCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelBarMapper.selectchannelBarCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#selectchannelBarByParam(java.util.Map)
     */
    @Override
    public List<Object> selectChannelBarByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelBarMapper.selectchannelBarByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelBarMapper#selectAllchannelBarByParam(java.util.Map)
     */
    @Override
    public List<ChannelBar> selectAllChannelBarByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelBarMapper.selectAllchannelBarByParam", map);
    }

}
