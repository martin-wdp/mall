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

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.dao.ChannelMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:16:14
 */
@Repository("ChannelMapper")
public class ChannelMapperImpl extends BasicSqlSupport implements ChannelMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long channelId) {

        return this.delete("com.ningpai.channel.dao.ChannelMapper.deleteByPrimaryKey", channelId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#insert(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int insert(Channel record) {

        return this.insert("com.ningpai.channel.dao.ChannelMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#insertSelective(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int insertSelective(Channel record) {

        return this.insert("com.ningpai.channel.dao.ChannelMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#updateByPrimaryKeySelective(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int updateByPrimaryKeySelective(Channel record) {

        return this.update("com.ningpai.channel.dao.ChannelMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#updateByPrimaryKey(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int updateByPrimaryKey(Channel record) {

        return this.update("com.ningpai.channel.dao.ChannelMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public Channel selectByPrimaryKey(Long channelId) {

        return this.selectOne("com.ningpai.channel.dao.ChannelMapper.selectByPrimaryKey", channelId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#queryTotalCount(java.util.Map)
     */
    @Override
    public Integer queryTotalCount(Map<String, Object> map) {

        return this.selectOne("com.ningpai.channel.dao.ChannelMapper.queryTotalCount", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#queryByPageBean(java.util.Map)
     */
    @Override
    public List<Object> queryByPageBean(Map<String, Object> map) {

        return this.selectList("com.ningpai.channel.dao.ChannelMapper.queryByPageBean", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#selectAll()
     */
    @Override
    public List<Channel> selectAll() {
        return this.selectList("com.ningpai.channel.dao.ChannelMapper.selectAll");
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#selectByCateId(java.lang.Long)
     */
    @Override
    public Channel selectByCateId(Long cateId) {
        return this.selectOne("com.ningpai.channel.dao.ChannelMapper.selectByCateId", cateId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelMapper#selectByBarId(java.lang.String)
     */
    @Override
    public Channel selectByBarId(String barId) {
        return this.selectOne("com.ningpai.channel.dao.ChannelMapper.selectByBarId", barId);
    }
}
