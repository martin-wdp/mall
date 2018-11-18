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

import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.channel.dao.ChannelBarMapper;
import com.ningpai.channel.service.ChannelBarService;
import com.ningpai.util.PageBean;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日上午9:20:54
 */
@Service("ChannelBarService")
public class ChannelBarServiceImpl implements ChannelBarService {
    private ChannelBarMapper channelBarMapper;

    public ChannelBarMapper getChannelBarMapper() {
        return channelBarMapper;
    }

    @Resource(name = "ChannelBarMapper")
    public void setChannelBarMapper(ChannelBarMapper channelBarMapper) {
        this.channelBarMapper = channelBarMapper;
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelBarService#deleteChannelBar(java.lang.Long)
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
     * @see com.ningpai.channel.service.ChannelBarService#saveChannelBar(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int saveChannelBar(ChannelBar record) {
        record.setDeleteStatus(0);
        Date date = new Date();
        record.setInsertDate(date);
        record.setModifyDate(date);
        return channelBarMapper.insertSelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelBarService#updateChannelBar(com.ningpai.channel.bean.ChannelBar)
     */
    @Override
    public int updateChannelBar(ChannelBar record) {
        // 设置修改时间
        record.setModifyDate(new Date());
        // 修改内容
        return channelBarMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelBarService#getChannelBarById(java.lang.Long)
     */
    @Override
    public ChannelBar getChannelBarById(Long barId) {
        // 根据di查询页面导航信息
        return channelBarMapper.selectByPrimaryKey(barId);
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelBarService#selectChannelBarByParam(com.ningpai.util.PageBean,
     *      java.lang.Long, java.lang.Long)
     */
    @Override
    public PageBean selectChannelBarByParam(PageBean pb, Long channelId,
            Long tempId, String third) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            map.put("channelId", channelId);
            // 模板id
            map.put("tempId", tempId);
            map.put("expFleid1", third);
            // 设置总行数
            pb.setRows(this.channelBarMapper.selectChannelBarCountByParam(map));
            // 设置开始行数
            map.put("startRowNum", pb.getStartRowNum());
            // 设置结束行数
            map.put("endRowNum", pb.getEndRowNum());
            // 设置集合
            pb.setList(this.channelBarMapper.selectChannelBarByParam(map));
        } finally {
            // 至空参数
            map = null;
        }
        return pb;
    }

    /**
     * 
     * 
     * @see com.ningpai.channel.service.ChannelBarService#selectAllChannelBarByParam(java.lang.Long,
     *      java.lang.Long)
     */
    @Override
    public List<ChannelBar> selectAllChannelBarByParam(Long channelId,
            Long tempId, String third) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("channelId", channelId);
        map.put("tempId", tempId);
        map.put("expFleid1", third);
        return channelBarMapper.selectAllChannelBarByParam(map);
    }

}
