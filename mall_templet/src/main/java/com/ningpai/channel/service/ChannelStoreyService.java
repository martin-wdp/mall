/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.ChannelStorey;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午4:36:27
 */
public interface ChannelStoreyService {
    /**
     * 删除频道楼层
     * 
     * @param channelStoreyId
     * @return
     */
    int deleteChannelStorey(Long channelStoreyId, Long userId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveChannelStorey(ChannelStorey record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateChannelStorey(ChannelStorey record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyId
     * @return
     */
    ChannelStorey getChannelStoreyById(Long channelStoreyId);

    /**
     * 根据分页参数和频道ID、模板ID查询频道楼层
     * 
     * @param map
     * @return
     */
    PageBean selectchannelStoreyByParam(PageBean pb, Long channelId, Long tempId, String temp1);

    /**
     * 根据分页参数和频道ID、模板ID查询频道楼层列表-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelStorey> selectchannelStoreyByParamForSite(Long channelId, Long tempId, String temp1);

    /**
     * 调用存储过程级联删除楼层
     * 
     * @param channelStoreyId
     * @return
     */
    int deleteByPrimaryKeyCallPro(Long channelStoreyId);

}
