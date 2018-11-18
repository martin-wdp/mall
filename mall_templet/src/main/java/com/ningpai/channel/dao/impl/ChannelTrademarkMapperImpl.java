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

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.channel.dao.ChannelTrademarkMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午10:01:59
 */
@Repository("ChannelTrademarkMapper")
public class ChannelTrademarkMapperImpl extends BasicSqlSupport implements ChannelTrademarkMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelTrademarkId) {

        return this.delete("com.ningpai.channel.dao.ChannelTrademarkMapper.deleteByPrimaryKey", channelTrademarkId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#insert(com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int insert(ChannelTrademark record) {

        return this.insert("com.ningpai.channel.dao.ChannelTrademarkMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#insertSelective(com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int insertSelective(ChannelTrademark record) {

        return this.insert("com.ningpai.channel.dao.ChannelTrademarkMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int updateByPrimaryKeySelective(ChannelTrademark record) {

        return this.update("com.ningpai.channel.dao.ChannelTrademarkMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#updateByPrimaryKey(com.ningpai.channel.bean.ChannelTrademark)
     */
    @Override
    public int updateByPrimaryKey(ChannelTrademark record) {

        return this.update("com.ningpai.channel.dao.ChannelTrademarkMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ChannelTrademark selectByPrimaryKey(Long channelTrademarkId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelTrademarkMapper.selectByPrimaryKey", channelTrademarkId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#selectchannelTrademarkCountByParam(java.util.Map)
     */
    @Override
    public Integer selectchannelTrademarkCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelTrademarkMapper.selectchannelTrademarkCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#selectchannelTrademarkByParam(java.util.Map)
     */
    @Override
    public List<Object> selectchannelTrademarkByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelTrademarkMapper.selectchannelTrademarkByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelTrademarkMapper#selectChannelTrademarkByParamForSite(java.util.Map)
     */
    @Override
    public List<ChannelTrademark> selectChannelTrademarkByParamForSite(Map<String, Object> map) {
        return this.selectList("com.ningpai.channel.dao.ChannelTrademarkMapper.selectChannelTrademarkByParamForSite", map);
    }

    @Override
    public List<ChannelTrademark> selectTrademarkByTempId(Long tempId) {
        
        return this.selectList("com.ningpai.channel.dao.ChannelTrademarkMapper.selectTrademarkByTempId", tempId);
    }
}
