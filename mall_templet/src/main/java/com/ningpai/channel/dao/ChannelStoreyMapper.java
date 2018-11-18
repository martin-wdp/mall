package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelStorey;

/**
 * DAO-频道楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午4:17:29
 */
public interface ChannelStoreyMapper {
    /**
     * 根据主键删除
     * 
     * @param channelStoreyId
     * @return
     */
    int deleteByPrimaryKey(Long channelStoreyId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(ChannelStorey record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ChannelStorey record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChannelStorey record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ChannelStorey record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyId
     * @return
     */
    ChannelStorey selectByPrimaryKey(Long channelStoreyId);

    /**
     * 根据分页参数和频道ID、模板ID查询频道楼层总行数
     * 
     * @param map
     * @return
     */
    Integer selectchannelStoreyCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询频道楼层
     * 
     * @param map
     * @return
     */
    List<Object> selectchannelStoreyByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询频道楼层列表-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelStorey> selectchannelStoreyByParamForSite(Map<String, Object> map);

    /**
     * 调用存储过程级联删除楼层
     * 
     * @param channelStoreyId
     * @return
     */
    int deleteByPrimaryKeyCallPro(Long channelStoreyId);

}
