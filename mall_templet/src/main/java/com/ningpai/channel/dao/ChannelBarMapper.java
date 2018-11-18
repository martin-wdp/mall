package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.ChannelBar;

/**
 * DAO-频道导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月9日下午6:12:05
 */
public interface ChannelBarMapper {
    /**
     * 根据主键删除频道导航
     * 
     * @param barId
     * @return
     */
    int deleteByPrimaryKey(Long barId);

    /**
     * 添加频道导航
     * 
     * @param record
     * @return
     */
    int insert(ChannelBar record);

    /**
     * 添加频道导航-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ChannelBar record);

    /**
     * 修改频道导航-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ChannelBar record);

    /**
     * 修改频道导航
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ChannelBar record);

    /**
     * 根据主键查询频道导航
     * 
     * @param barId
     * @return
     */
    ChannelBar selectByPrimaryKey(Long barId);

    /**
     * 根据分页参数和频道ID、模板ID查询频道导航总行数
     * 
     * @param map
     * @return
     */
    Integer selectChannelBarCountByParam(Map<String, Object> map);

    /**
     * 根据分页参数和频道ID、模板ID查询频道导航
     * 
     * @param map
     * @return
     */
    List<Object> selectChannelBarByParam(Map<String, Object> map);

    /**
     * 根据频道ID、模板ID查询频道导航
     * 
     * @param map
     * @return
     */
    List<ChannelBar> selectAllChannelBarByParam(Map<String, Object> map);
}
