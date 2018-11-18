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

import com.ningpai.channel.bean.Channel;
import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.dao.ChannelBarMapper;
import com.ningpai.channel.dao.ChannelMapper;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.util.MyLogger;
import com.ningpai.util.PageBean;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日上午9:20:54
 */
@Service("ChannelBarServiceNew")
public class ChannelBarServiceNewImpl implements ChannelBarService {

    /** 记录日志对象 */
    private static final MyLogger LOGGER = new MyLogger(ChannelBarServiceNewImpl.class);

    @Resource(name = "ChannelBarMapper")
    private ChannelBarMapper channelBarMapper;

    @Resource(name = "ChannelMapper")
    private ChannelMapper channelMapper;

    /**
     * 
     *
     *
     */
    @Override
    public int deleteChannelBar(Long barId) {
        ChannelBar cb = channelBarMapper.selectByPrimaryKey(barId);
        cb.setDeleteStatus(1);
        cb.setModifyDate(new Date());
        return channelBarMapper.updateByPrimaryKeySelective(cb);
    }

    /**
     * 
     *
     */
    @Override
    public int saveChannelBar(ChannelBar record) {
        int c = -1;
        try {
            // 添加页面导航
            record.setDeleteStatus(0);
            Date date = new Date();
            record.setInsertDate(date);
            record.setModifyDate(date);
            c = channelBarMapper.insertSelective(record);
            // 添加导航频道
            Channel channel = new Channel();
            channel.setTempId(record.getTempId());
            channel.setChannelName(record.getBarName());
            channel.setUsedStart("1");
            channel.setDelflag("0");
            channel.setTemp4(record.getBarId().toString());
            channelMapper.insertSelective(channel);
        } catch (Exception e) {
            LOGGER.error("添加页面导航失败，请查看原因：", e);
        }
        return c;
    }

    /**
     * 
     *
     */
    @Override
    public int updateChannelBar(ChannelBar record) {
        record.setModifyDate(new Date());
        return channelBarMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     *
     */
    @Override
    public ChannelBar getChannelBarById(Long barId) {

        return channelBarMapper.selectByPrimaryKey(barId);
    }

    /**
     * 
     *
     */
    @Override
    public PageBean selectChannelBarByParam(PageBean pb, Long channelId, Long tempId, String third) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put("channelId", channelId);
            map.put("tempId", tempId);
            map.put("expFleid1", third);
            pb.setRows(this.channelBarMapper.selectChannelBarCountByParam(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelBarMapper.selectChannelBarByParam(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 
     *
     */
    @Override
    public List<ChannelBar> selectAllChannelBarByParam(Long channelId, Long tempId, String third) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", channelId);
        map.put("tempId", tempId);
        map.put("expFleid1", third);
        return channelBarMapper.selectAllChannelBarByParam(map);
    }

}
