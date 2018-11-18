package com.ningpai.channel.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.channel.bean.Channel;

/**
 * DAO-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午1:56:52
 */
public interface ChannelMapper {
    /**
     * 根据ID删除频道
     * 
     * @param channelId
     * @return
     */
    int deleteByPrimaryKey(Long channelId);

    /**
     * 添加频道
     * 
     * @param record
     * @return
     */
    int insert(Channel record);

    /**
     * 添加频道-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(Channel record);

    /**
     * 修改频道-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Channel record);

    /**
     * 修改频道
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(Channel record);

    /**
     * 根据主键查询频道
     * 
     * @param channelId
     * @return
     */
    Channel selectByPrimaryKey(Long channelId);

    /**
     * 根据商品分类ID查询频道
     * 
     * @param cateId
     * @return
     */
    Channel selectByCateId(Long cateId);

    /**
     * 根据参数查询总行数-分页用
     * 
     * @param map
     * @return
     */
    Integer queryTotalCount(Map<String, Object> map);

    /**
     * 根据分页参数查询频道列表
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);

    /**
     * 查询所有频道
     * 
     * @param map
     * @return
     */
    List<Channel> selectAll();

    /**
     * 根据页面导航ID查询频道
     * 
     * @param barId
     * @return
     */
    Channel selectByBarId(String barId);
}
