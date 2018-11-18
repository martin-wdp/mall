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

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.channel.dao.ChannelAdverMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:22:09
 */
@Repository("ChannelAdverMapper")
public class ChannelAdverMapperImpl extends BasicSqlSupport implements ChannelAdverMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelAdverId) {

        return this.delete("com.ningpai.channel.dao.ChannelAdverMapper.deleteByPrimaryKey", channelAdverId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#insert(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int insert(ChannelAdver record) {

        return this.insert("com.ningpai.channel.dao.ChannelAdverMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#insertSelective(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int insertSelective(ChannelAdver record) {

        return this.insert("com.ningpai.channel.dao.ChannelAdverMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelAdver record) {

        return this.update("com.ningpai.channel.dao.ChannelAdverMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelAdver)
     */
    @Override
    public int updateByPrimaryKey(ChannelAdver record) {

        return this.update("com.ningpai.channel.dao.ChannelAdverMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelAdver selectByPrimaryKey(Long channelAdverId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelAdverMapper.selectByPrimaryKey", channelAdverId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#selectchannelAdverCountByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelAdverCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelAdverMapper.selectchannelAdverCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#selectchannelAdverByParam(java.util.Map)
     */
    @Override
    public List<Object> selectchannelAdverByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelAdverMapper.selectchannelAdverByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelAdverMapper#selectchannelAdverByParamForSite(java.util.Map)
     */
    @Override
    public List<ChannelAdver> selectchannelAdverByParamForSite(Map<String, Object> map) {
        return this.selectList("com.ningpai.channel.dao.ChannelAdverMapper.selectchannelAdverByParamForSite", map);
    }
}
