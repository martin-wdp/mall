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

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.dao.ChannelStoreyMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午4:31:02
 */
@Repository("ChannelStoreyMapper")
public class ChannelStoreyMapperImpl extends BasicSqlSupport implements ChannelStoreyMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelStoreyId) {

        return this.delete("com.ningpai.channel.dao.ChannelStoreyMapper.deleteByPrimaryKey", channelStoreyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#insert(com.ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int insert(ChannelStorey record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#insertSelective(com.ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int insertSelective(ChannelStorey record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelStorey record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int updateByPrimaryKey(ChannelStorey record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelStorey selectByPrimaryKey(Long channelStoreyId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyMapper.selectByPrimaryKey", channelStoreyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#selectchannelStoreyCountByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelStoreyCountByParam(Map<String, Object> map) {
        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyMapper.selectchannelStoreyCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#selectchannelStoreyByParam(java.util.Map)
     */
    @Override
    public List<Object> selectchannelStoreyByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyMapper.selectchannelStoreyByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#selectchannelStoreyByParamForSite(java.util.Map)
     */
    @Override
    public List<ChannelStorey> selectchannelStoreyByParamForSite(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyMapper.selectchannelStoreyByParamForSite", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyMapper#deleteByPrimaryKeyCallPro(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyCallPro(Long channelStoreyId) {
        return this.delete("com.ningpai.channel.dao.ChannelStoreyMapper.deleteByPrimaryKeyCallPro", channelStoreyId);
    }

}
