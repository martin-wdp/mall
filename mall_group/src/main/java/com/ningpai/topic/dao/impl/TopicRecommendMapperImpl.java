/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.topic.bean.TopicRecommend;
import com.ningpai.topic.dao.TopicRecommendMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 话题推荐接口实现类
 * 
 * @author qiyuanyuan
 * 
 */
@Repository("TopicRecommendMapper")
public class TopicRecommendMapperImpl extends BasicSqlSupport implements TopicRecommendMapper {

    /**
     * 根据主键删除 参数:主键 返回:删除个数
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public int deleteByPrimaryKey(Long recommendId) {

        return this.delete("com.ningpai.dao.TopicRecommendMapper.deleteByPrimaryKey", recommendId);
    }

    /**
     * 插入，空属性也会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public int insert(TopicRecommend record) {

        return this.insert("com.ningpai.dao.TopicRecommendMapper.insert", record);
    }

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:删除个数
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public int insertSelective(TopicRecommend record) {

        return this.insert("com.ningpai.dao.TopicRecommendMapper.insertSelective", record);
    }


    /**
     * 根据主键查询 参数:查询条件,主键值 返回:对象
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public TopicRecommend selectByPrimaryKey(Long recommendId) {

        return this.selectOne("com.ningpai.dao.TopicRecommendMapper.selectByPrimaryKey", recommendId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public int updateByPrimaryKeySelective(TopicRecommend record) {
        return this.update("com.ningpai.dao.TopicRecommendMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 根据主键修改，空值条件会修改成null 参数:1.要修改成的值 返回:成功修改个数
     *
     * @ibatorgenerated 2014-07-31 16:59:29
     */
    public int updateByPrimaryKey(TopicRecommend record) {
        return this.update("com.ningpai.dao.TopicRecommendMapper.updateByPrimaryKey", record);
    }

    /**
     * 用户推荐话题次数查询
     *
     * @param paramMap
     *            参数
     * @return int
     */
    public int topicRecommendCount(Map<String, Object> paramMap) {

        return this.selectOne("com.ningpai.dao.TopicRecommendMapper.topicRecommendCount", paramMap);
    }

}
