/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.channel.dao.ChannelSysDictionaryMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.channel.bean.SysDictionary;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日上午10:59:31
 */
@Repository("ChannelSysDictionaryMapper")
public class ChannelSysDictionaryMapperImpl extends BasicSqlSupport implements ChannelSysDictionaryMapper {

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelSysDictionaryMapper#selectByPrimaryKey(int)
     */
    @Override
    public SysDictionary selectByPrimaryKey(int dicId) {
        return this.selectOne("com.ningpai.channel.dao.ChannelSysDictionaryMapper.selectByPrimaryKey", dicId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.dao.ChannelSysDictionaryMapper#getSysDictionaryByField(java.lang.Long)
     */
    @Override
    public List<SysDictionary> getSysDictionaryByField(Long parentId) {
        return this.selectList("com.ningpai.channel.dao.ChannelSysDictionaryMapper.getSysDictionaryByField", parentId);
    }

}
