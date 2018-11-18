package com.ningpai.topic.dao;

import java.util.Map;

import com.ningpai.topic.bean.TopicRecommend;

/**
 * 话题推荐接口
 * 
 * @author qiyuanyuan
 * 
 */
public interface TopicRecommendMapper {
    /**
     * 根据主键删除 参数:主键 返回:删除个数
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    int deleteByPrimaryKey(Long recommendId);

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    int insert(TopicRecommend record);

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    int insertSelective(TopicRecommend record);

    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    TopicRecommend selectByPrimaryKey(Long recommendId);

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    int updateByPrimaryKeySelective(TopicRecommend record);

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     * 
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    int updateByPrimaryKey(TopicRecommend record);

    /**
     * 用户推荐话题次数查询
     * 
     * @param paramMap
     *            参数
     * @return int
     */
    int topicRecommendCount(Map<String, Object> paramMap);

}
