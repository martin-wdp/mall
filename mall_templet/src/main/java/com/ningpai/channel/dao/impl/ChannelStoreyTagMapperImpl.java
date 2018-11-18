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

import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.dao.ChannelStoreyTagMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:00:18
 */
@Repository("ChannelStoreyTagMapper")
public class ChannelStoreyTagMapperImpl extends BasicSqlSupport implements ChannelStoreyTagMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelStoreyTagId) {

        return this.delete("com.ningpai.channel.dao.ChannelStoreyTagMapper.deleteByPrimaryKey", channelStoreyTagId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#insert(com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int insert(ChannelStoreyTag record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyTagMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#insertSelective(com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int insertSelective(ChannelStoreyTag record) {

        return this.insert("com.ningpai.channel.dao.ChannelStoreyTagMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelStoreyTag record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyTagMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int updateByPrimaryKey(ChannelStoreyTag record) {

        return this.update("com.ningpai.channel.dao.ChannelStoreyTagMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelStoreyTag selectByPrimaryKey(Long channelStoreyTagId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyTagMapper.selectByPrimaryKey", channelStoreyTagId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#selectchannelStoreyTagCountByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelStoreyTagCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelStoreyTagMapper.selectchannelStoreyTagCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#selectchannelStoreyTagByParam(java.util.Map)
     */
    @Override
    public List<Object> selectchannelStoreyTagByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyTagMapper.selectchannelStoreyTagByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#selectchannelStoreyTagByParamForSite(java.util.Map)
     */
    @Override
    public List<ChannelStoreyTag> selectchannelStoreyTagByParamForSite(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelStoreyTagMapper.selectchannelStoreyTagByParamForSite", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelStoreyTagMapper#deleteByPrimaryKeyCallPro(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyCallPro(Long channelStoreyTagId) {
        return this.delete("com.ningpai.channel.dao.ChannelStoreyTagMapper.deleteByPrimaryKeyCallPro", channelStoreyTagId);
    }
}
