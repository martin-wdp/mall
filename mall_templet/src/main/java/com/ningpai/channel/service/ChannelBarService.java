/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.ChannelBar;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月9日下午6:27:51
 */
public interface ChannelBarService {
    /**
     * 根据主键删除频道导航
     * 
     * @param barId
     * @return
     */
    int deleteChannelBar(Long barId);

    /**
     * 添加频道导航
     * 
     * @param record
     * @return
     */
    int saveChannelBar(ChannelBar record);

    /**
     * 修改频道导航
     * 
     * @param record
     * @return
     */
    int updateChannelBar(ChannelBar record);

    /**
     * 根据主键查询频道导航
     * 
     * @param barId
     * @return
     */
    ChannelBar getChannelBarById(Long barId);

    /**
     * 根据分页参数和频道ID、模板ID查询频道导航
     * 
     * @param map
     * @return
     */
    PageBean selectChannelBarByParam(PageBean pb, Long channelId, Long tempId, String third);

    /**
     * 根据频道ID、模板ID查询频道导航
     * 
     * @param map
     * @return
     */
    List<ChannelBar> selectAllChannelBarByParam(Long channelId, Long tempId, String third);
}
