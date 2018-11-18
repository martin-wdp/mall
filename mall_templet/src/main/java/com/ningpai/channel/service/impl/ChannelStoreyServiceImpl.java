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

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.channel.dao.ChannelStoreyMapper;
import com.ningpai.channel.service.ChannelStoreyService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午4:42:11
 */
@Service("ChannelStoreyService")
public class ChannelStoreyServiceImpl implements ChannelStoreyService {

    /** 频道楼层数据访问接口 */
    private ChannelStoreyMapper channelStoreyMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#deleteChannelStorey(
     * java.lang.Long, java.lang.Long)
     */
    @Override
    public int deleteChannelStorey(Long channelStoreyId, Long userId) {
        ChannelStorey channelStorey = this.channelStoreyMapper.selectByPrimaryKey(channelStoreyId);
        channelStorey.setDelflag("1");
        channelStorey.setUpdateUserId(userId);
        channelStorey.setUpdateDate(new Date());
        return this.channelStoreyMapper.updateByPrimaryKeySelective(channelStorey);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#saveChannelStorey(com
     * .ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int saveChannelStorey(ChannelStorey record) {
        Date date = new Date();
        record.setDelflag("0");
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return this.channelStoreyMapper.insertSelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#updateChannelStorey(
     * com.ningpai.channel.bean.ChannelStorey)
     */
    @Override
    public int updateChannelStorey(ChannelStorey record) {
        record.setUpdateDate(new Date());
        return this.channelStoreyMapper.updateByPrimaryKeySelective(record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#getChannelStoreyById
     * (java.lang.Long)
     */
    @Override
    public ChannelStorey getChannelStoreyById(Long channelStoreyId) {

        return this.channelStoreyMapper.selectByPrimaryKey(channelStoreyId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#selectchannelStoreyByParam
     * (com.ningpai.util.PageBean, java.lang.Long, java.lang.Long)
     */
    @Override
    public PageBean selectchannelStoreyByParam(PageBean pb, Long channelId, Long tempId, String temp1) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put("channelId", channelId);
            map.put("tempId", tempId);
            map.put("temp1", temp1);
            pb.setRows(channelStoreyMapper.selectchannelStoreyCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelStoreyMapper.selectchannelStoreyByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelStoreyService#
     * selectchannelStoreyByParamForSite(java.lang.Long, java.lang.Long)
     */
    @Override
    public List<ChannelStorey> selectchannelStoreyByParamForSite(Long channelId, Long tempId, String temp1) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", channelId);
        map.put("tempId", tempId);
        map.put("temp1", temp1);
        return this.channelStoreyMapper.selectchannelStoreyByParamForSite(map);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelStoreyService#deleteByPrimaryKeyCallPro
     * (java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyCallPro(Long channelStoreyId) {
        return channelStoreyMapper.deleteByPrimaryKeyCallPro(channelStoreyId);
    }

    public ChannelStoreyMapper getChannelStoreyMapper() {
        return channelStoreyMapper;
    }

    @Resource(name = "ChannelStoreyMapper")
    public void setChannelStoreyMapper(ChannelStoreyMapper channelStoreyMapper) {
        this.channelStoreyMapper = channelStoreyMapper;
    }

}
