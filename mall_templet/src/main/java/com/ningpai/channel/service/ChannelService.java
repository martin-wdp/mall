/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import com.ningpai.channel.bean.Channel;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午2:21:10
 */
public interface ChannelService {
    /**
     * 根据ID删除频道
     * 
     * @param channelId
     * @return
     */
    int deleteChannel(Long channelId, Long userID);

    /**
     * 添加频道
     * 
     * @param record
     * @return
     */
    int createChannel(Channel record);

    /**
     * 修改频道
     * 
     * @param record
     * @return
     */
    int updateChannel(Channel record);

    /**
     * 根据主键查询频道
     * 
     * @param channelId
     * @return
     */
    Channel findChannelByID(Long channelId);

    /**
     * 根据商品分类ID查询频道
     * 
     * @param cateId
     * @return
     */
    Channel selectByCateId(Long cateId);

    /**
     * 根据分页参数查询频道列表
     * 
     * @param map
     * @return
     */
    PageBean findChannelByPageBean(PageBean pb, String searchText, Long tempId);

    /**
     * 开启全部频道
     */
    void openAllChannel();

    /**
     * 关闭全部频道
     */
    void closeAllChannel();

    /**
     * 根据频道ID开启频道
     * 
     * @param channelId
     */
    void openChannelByID(Long channelId, Long userId);

    /**
     * 根据频道ID关闭频道
     * 
     * @param channelId
     */
    void closeChannelByID(Long channelId, Long userId);

    /**
     * 根据页面导航ID查询频道
     * 
     * @param barId
     * @return
     */
    Channel selectByBarId(String barId);
}
