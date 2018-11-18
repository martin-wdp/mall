package com.ningpai.channel.service;

import com.ningpai.channel.bean.ChannelTrademark;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * SERVICE-频道品牌
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午9:42:57
 */
public interface ChannelTrademarkService {
    /**
     * 根据ID删除
     * 
     * @param channelTrademarkId
     * @return
     */
    int deleteChannelTrademark(Long channelTrademarkId, Long userId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int saveChannelTrademark(ChannelTrademark record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateChannelTrademark(ChannelTrademark record);

    /**
     * 根据ID查询
     * 
     * @param channelTrademarkId
     * @return
     */
    ChannelTrademark getChannelTrademarkById(Long channelTrademarkId);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、分类导航ID查询品牌
     * 
     * @param pb
     * @param channelId
     * @param tempId
     * @param storeyId
     * @param storeyTagId
     * @param temp1
     * @return
     */
    PageBean selectchannelTrademarkByParam(PageBean pb, Long channelId, Long tempId, Long storeyId, Long storeyTagId, String temp1, String temp3);

    /**
     * 根据分页参数和分类导航ID和是否分类导航父框查询品牌
     * 
     * @param pb
     * @param tempId
     *            模板ID
     * @param channelId
     *            频道ID
     * @param temp1
     *            分类导航ID
     * @param temp2
     *            是否分类导航父框 1：是
     * @return
     */
    PageBean selectClassifyTrademarkByParam(PageBean pb, Long tempId, Long channelId, String temp1, String temp2);

    /**
     * 根据频道ID、模板ID、楼层ID查询频道品牌--前台展示用
     * 
     * @param channelId
     * @param tempId
     * @param storeyId
     * @param storeyTagId
     * @param temp1
     * @param temp2
     *            是否分类导航父框 1：是
     */
    List<ChannelTrademark> selectChannelTrademarkByParamForSite(Long channelId, Long tempId, Long storeyId, Long storeyTagId, String temp1, String temp2, String temp3);

    /**
     * 根据模板ID查询品牌设置
     * @param tempId
     * @return
     */
    List<ChannelTrademark> selectTrademarkByTempId(Long tempId);
}
