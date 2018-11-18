/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.channel.service;

import com.ningpai.channel.bean.ChannelAdver;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * SERVICE-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:26:25
 */
public interface ChannelAdverService {
    /**
     * 根据主键删除频道广告
     * 
     * @param channelAdverId
     * @return
     */
    int deleteChannelAdver(Long channelAdverId, Long userId);

    /**
     * 添加频道广告
     * 
     * @param record
     * @return
     */
    int saveChannelAdver(ChannelAdver record);

    /**
     * 修改频道广告
     * 
     * @param record
     * @return
     */
    int updateChannelAdver(ChannelAdver record);

    /**
     * 根据主键查询频道广告
     * 
     * @param channelAdverId
     * @return
     */
    ChannelAdver selectByPrimaryKey(Long channelAdverId);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID、分类导航ID查询频道广告
     * 
     * @param pb
     * @param channelId
     * @param tempId
     * @param floorId
     * @param floorTagId
     * @param atId
     * @param adverType
     * @param temp1
     * @return
     */
    PageBean selectchannelAdverByParam(PageBean pb, Long channelId, Long tempId, Long floorId, Long floorTagId, Long atId, Long adverType, String temp1, String temp4);

    /**
     * 根据分页参数、分类导航ID查询导航分类父框广告
     * 
     * @param pb
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @param temp1
     *            分类导航ID
     * @param temp3
     *            是否是导航分类父框 0：不是 1：是
     * @return
     */
    PageBean selectClassifyBarAdverByParam(PageBean pb, Long tempId, Long channelId, String temp1, String temp3);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告列表-前台展示用 channelId 频道ID tempId 模板ID floorId 楼层ID floorTagId 楼层标签ID atId 广告分类ID 157大广告；159小广告；161页面广告 adverType 广告类型ID 现用151 temp1 分类导航ID temp3 是否是导航分类父框 0：不是 1：是 temp5 广告显示位置
     * @param channelId
     * @param tempId
     * @param floorId
     * @param floorTagId
     * @param atId
     * @param adverType
     * @param temp1
     * @param temp3
     * @param temp4
     * @param temp5
     * @return
     */
    List<ChannelAdver> selectchannelAdverByParamForSite(Long channelId, Long tempId, Long floorId, Long floorTagId, Long atId, Long adverType, String temp1, String temp3, String temp4, String temp5);
}
