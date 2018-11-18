package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelAdver;

/**
 * DAO-频道广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月1日下午4:17:14
 */
public interface ChannelAdverMapper {
    /**
     * 根据主键删除频道广告
     * 
     * @param channelAdverId
     * @return
     */
    int deleteByPrimaryKey(Long channelAdverId);

    /**
     * 添加频道广告
     * 
     * @param record
     * @return
     */
    int insert(ChannelAdver record);

    /**
     * 添加频道广告-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ChannelAdver record);

    /**
     * 修改频道广告-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChannelAdver record);

    /**
     * 修改频道广告
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ChannelAdver record);

    /**
     * 根据主键查询频道广告
     * 
     * @param channelAdverId
     * @return
     */
    ChannelAdver selectByPrimaryKey(Long channelAdverId);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告总行数
     * 
     * @param map
     * @return
     */
    Integer selectchannelAdverCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告
     * 
     * @param map
     * @return
     */
    List<Object> selectchannelAdverByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID、楼层ID、楼层标签ID查询频道广告列表-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelAdver> selectchannelAdverByParamForSite(Map<String, Object> map);
}
