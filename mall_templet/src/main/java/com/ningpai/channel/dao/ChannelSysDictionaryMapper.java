package com.ningpai.channel.dao;

import java.util.List;

import com.ningpai.channel.bean.SysDictionary;

/**
 * DAO-频道
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月27日下午1:56:52
 */
public interface ChannelSysDictionaryMapper {
    /**
     * 根据主键查询系统字典
     * 
     * @param channelId
     * @return
     */
    SysDictionary selectByPrimaryKey(int dicId);

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息
     * 
     * @param map
     * @return
     */
    List<SysDictionary> getSysDictionaryByField(Long parentId);
}
