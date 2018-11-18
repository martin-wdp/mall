package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelStoreyTag;

/**
 * DAO-频道楼层标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月12日下午4:55:27
 */
public interface ChannelStoreyTagMapper {
    /**
     * 根据主键删除
     * 
     * @param channelStoreyTagId
     * @return
     */
    int deleteByPrimaryKey(Long channelStoreyTagId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(ChannelStoreyTag record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ChannelStoreyTag record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChannelStoreyTag record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ChannelStoreyTag record);

    /**
     * 根据主键查询
     * 
     * @param channelStoreyTagId
     * @return
     */
    ChannelStoreyTag selectByPrimaryKey(Long channelStoreyTagId);

    /**
     * 根据分页参数和楼层ID查询频道楼层标签总行数
     * 
     * @param map
     * @return
     */
    Integer selectchannelStoreyTagCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和楼层ID查询频道楼层标签
     * 
     * @param map
     * @return
     */
    List<Object> selectchannelStoreyTagByParam(Map<String, Object> map);

    /**
     * 根据分页参数和楼层ID查询频道楼层标签列表-前台展示用
     * 
     * @param map
     * @return
     */
    List<ChannelStoreyTag> selectchannelStoreyTagByParamForSite(Map<String, Object> map);

    /**
     * 调用存储过程级联删除楼层标签
     * 
     * @param channelStoreyTagId
     * @return
     */
    int deleteByPrimaryKeyCallPro(Long channelStoreyTagId);
}
