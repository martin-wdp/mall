/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.channel.dao.ChannelStoreyTagMapper;
import com.ningpai.channel.service.ChannelStoreyTagService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-频道楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:11:30
 */
@Service("ChannelStoreyTagService")
public class ChannelStoreyTagServiceImpl implements ChannelStoreyTagService {

    /** 频道楼层标签数据访问接口 */
    private ChannelStoreyTagMapper channelStoreyTagMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyTagService#deleteChannelStoreyTag
     * (java.lang.Long)
     */
    @Override
    public int deleteChannelStoreyTag(Long channelStoreyTagId, Long userId) {
        ChannelStoreyTag storeyTag = this.channelStoreyTagMapper.selectByPrimaryKey(channelStoreyTagId);
        storeyTag.setDelflag("1");
        storeyTag.setUpdateUserId(userId);
        storeyTag.setUpdateDate(new Date());
        return this.channelStoreyTagMapper.updateByPrimaryKeySelective(storeyTag);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyTagService#saveChannelStoreyTag
     * (com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int saveChannelStoreyTag(ChannelStoreyTag record) {
        record.setDelflag("0");
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return this.channelStoreyTagMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyTagService#updateChannelStoreyTag
     * (com.ningpai.channel.bean.ChannelStoreyTag)
     */
    @Override
    public int updateChannelStoreyTag(ChannelStoreyTag record) {
        record.setUpdateDate(new Date());
        return this.channelStoreyTagMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyTagService#getChannelStoreyTagById
     * (java.lang.Long)
     */
    @Override
    public ChannelStoreyTag getChannelStoreyTagById(Long channelStoreyTagId) {

        return this.channelStoreyTagMapper.selectByPrimaryKey(channelStoreyTagId);
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyTagService#
     * selectchannelStoreyTagByParam(com.ningpai.util.PageBean, java.lang.Long)
     */
    @Override
    public PageBean selectchannelStoreyTagByParam(PageBean pb, Long storeyId, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put("storeyId", storeyId);
            map.put("temp1", tempId);
            map.put("temp2", channelId);
            pb.setRows(this.channelStoreyTagMapper.selectchannelStoreyTagCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelStoreyTagMapper.selectchannelStoreyTagByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyTagService#
     * selectchannelStoreyTagByParamForSite(java.lang.Long)
     */
    @Override
    public List<ChannelStoreyTag> selectchannelStoreyTagByParamForSite(Long storeyId, Long tempId, Long channelId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("storeyId", storeyId);
        map.put("temp1", tempId);
        map.put("temp2", channelId);
        return this.channelStoreyTagMapper.selectchannelStoreyTagByParamForSite(map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyTagService#deleteByPrimaryKeyCallPro
     * (java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyCallPro(Long channelStoreyTagId) {
        return channelStoreyTagMapper.deleteByPrimaryKeyCallPro(channelStoreyTagId);
    }

    public ChannelStoreyTagMapper getChannelStoreyTagMapper() {
        return channelStoreyTagMapper;
    }

    @Resource(name = "ChannelStoreyTagMapper")
    public void setChannelStoreyTagMapper(ChannelStoreyTagMapper channelStoreyTagMapper) {
        this.channelStoreyTagMapper = channelStoreyTagMapper;
    }
}
