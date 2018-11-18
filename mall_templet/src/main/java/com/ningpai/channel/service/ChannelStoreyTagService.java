/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import java.util.List;

import com.ningpai.channel.bean.ChannelStoreyTag;
import com.ningpai.util.PageBean;

/**
 * SERVICE-频道楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午5:05:46
 */
public interface ChannelStoreyTagService {
    /**
     * 根据主键删除
     * 
     * @param channelStoreyTagId
     * @return
     */
    int deleteChannelStoreyTag(Long channelStoreyTagId, Long userId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveChannelStoreyTag(ChannelStoreyTag record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateChannelStoreyTag(ChannelStoreyTag record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyTagId
     * @return
     */
    ChannelStoreyTag getChannelStoreyTagById(Long channelStoreyTagId);

    /**
     * 根据分页参数和楼层ID查询频道楼层标签
     * 
     * @param pb
     *            分页参数
     * @param storeyId
     *            楼层ID
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return
     */
    PageBean selectchannelStoreyTagByParam(PageBean pb, Long storeyId, Long tempId, Long channelId);

    /**
     * 根据分页参数和楼层ID查询频道楼层标签列表-前台展示用
     * 
     * @param storeyId
     *            楼层ID
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @return
     */
    List<ChannelStoreyTag> selectchannelStoreyTagByParamForSite(Long storeyId, Long tempId, Long channelId);

    /**
     * 调用存储过程级联删除楼层标签
     * 
     * @param channelStoreyTagId
     * @return
     */
    int deleteByPrimaryKeyCallPro(Long channelStoreyTagId);
}
