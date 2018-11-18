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
import com.ningpai.channel.dao.ChannelMapper;
import com.ningpai.channel.service.ChannelService;
import com.ningpai.util.PageBean;

/**
 * SERVICE实现类-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:26:30
 */
@Service("ChannelService")
public class ChannelServiceImpl implements ChannelService {

    private ChannelMapper channelMapper;

    public ChannelMapper getChannelMapper() {
        return channelMapper;
    }

    @Resource(name = "ChannelMapper")
    public void setChannelMapper(ChannelMapper channelMapper) {
        this.channelMapper = channelMapper;
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#deleteChannel(java.lang.Long)
     */
    @Override
    public int deleteChannel(Long channelId, Long userID) {
        Channel channel = channelMapper.selectByPrimaryKey(channelId);
        channel.setUpdateUserId(userID);
        channel.setDelflag("1");
        channel.setUpdateDate(new Date());
        return channelMapper.updateByPrimaryKeySelective(channel);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#createChannel(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int createChannel(Channel record) {
        record.setDelflag("0");
        Date date = new Date();
        record.setCreateDate(date);
        record.setUpdateDate(date);
        return channelMapper.insertSelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#updateChannel(com.ningpai.channel.bean.Channel)
     */
    @Override
    public int updateChannel(Channel record) {
        record.setUpdateDate(new Date());
        return channelMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * *
     * 
     * @see com.ningpai.channel.service.ChannelService#findChannelByID(java.lang.Long)
     */
    @Override
    public Channel findChannelByID(Long channelId) {

        return channelMapper.selectByPrimaryKey(channelId);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#findChannelByPageBean(com.ningpai.information.common.Pageable)
     */
    @Override
    public PageBean findChannelByPageBean(PageBean pb, String searchText,
            Long tempId) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("searchText", searchText);
            map.put("tempId", tempId);
            // 查询数据的总行数并设置到PageBean中
            pb.setRows(this.channelMapper.queryTotalCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.channelMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#closeAllChannel()
     */
    @Override
    public void closeAllChannel() {
        List<Channel> list = this.channelMapper.selectAll();
        for (Channel channel : list) {
            channel.setUsedStart("0");
            channel.setUpdateDate(new Date());
            this.channelMapper.updateByPrimaryKeySelective(channel);
        }

    }

    /*
     * 
     * 
     * @see com.ningpai.channel.service.ChannelService#OpenAllChannel()
     */
    @Override
    public void openAllChannel() {
        List<Channel> list = this.channelMapper.selectAll();
        for (Channel channel : list) {
            channel.setUsedStart("1");
            channel.setUpdateDate(new Date());
            this.channelMapper.updateByPrimaryKeySelective(channel);
        }

    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelService#OpenChannelByID(java.lang.
     * Long)
     */
    @Override
    public void openChannelByID(Long channelId, Long userId) {
        Channel channel = this.channelMapper.selectByPrimaryKey(channelId);
        channel.setUsedStart("1");
        channel.setUpdateUserId(userId);
        channel.setUpdateDate(new Date());
        this.channelMapper.updateByPrimaryKeySelective(channel);

    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelService#closeChannelID(java.lang.Long)
     */
    @Override
    public void closeChannelByID(Long channelId, Long userId) {
        Channel channel = this.channelMapper.selectByPrimaryKey(channelId);
        channel.setUsedStart("0");
        channel.setUpdateUserId(userId);
        channel.setUpdateDate(new Date());
        this.channelMapper.updateByPrimaryKeySelective(channel);

    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelService#selectByCateId(java.lang.Long)
     */
    @Override
    public Channel selectByCateId(Long cateId) {
        return channelMapper.selectByCateId(cateId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.channel.service.ChannelService#selectByBarId(java.lang.String
     * )
     */
    @Override
    public Channel selectByBarId(String barId) {
        return channelMapper.selectByBarId(barId);
    }
}
