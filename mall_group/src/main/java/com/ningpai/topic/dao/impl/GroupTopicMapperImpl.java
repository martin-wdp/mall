/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.dao.GroupTopicMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 小组话题实现
 * 
 * @version 2014年5月26日 下午2:19:28
 * @author qiyuanyuan
 */

@Repository("GroupTopicMapper")
public class GroupTopicMapperImpl extends BasicSqlSupport implements GroupTopicMapper {

    /**
     * 根据主键删除
     *
     * @param topicId 主键{@link java.lang.Long}
     */
    public int deleteByPrimaryKey(Long topicId) {
        return this.delete("com.ningpai.topic.mapper.GroupTopicMapper.deleteByPrimaryKey", topicId);
    }

    /**
     * 插入，空属性也会插入
     *
     * @param groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return 删除个数
     */
    public int insert(GroupTopic groupTopic) {
        return this.insert("com.ningpai.topic.mapper.GroupTopicMapper.insert", groupTopic);
    }

    /**
     * 插入，空属性不会插入
     *
     * @param groupTopic 小组话题实体
     * @return 删除个数
     */
    public int insertSelective(GroupTopic groupTopic) {
        return this.insert("com.ningpai.topic.mapper.GroupTopicMapper.insertSelective", groupTopic);
    }

    /**
     * 根据主键查询
     *
     * @param topicId 查询条件,主键值{@link java.lang.Long}
     * @return 对象
     */
    public GroupTopic selectByPrimaryKey(Long topicId) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.selectByPrimaryKey", topicId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     *
     * @param groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return 成功修改个数
     */
    public int updateByPrimaryKeySelective(GroupTopic groupTopic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateByPrimaryKeySelective", groupTopic);
    }
    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     *
     * @param groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return  成功修改个数
     */
    public int updateByPrimaryKeyWithBLOBs(GroupTopic groupTopic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateByPrimaryKeyWithBLOBs", groupTopic);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     *
     * @param groupTopic 对象
     */
    public int updateByPrimaryKey(GroupTopic groupTopic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateByPrimaryKey", groupTopic);
    }

    /**
     * 获取新增主键的查询
     *
     */
    public Long selectLastId() {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.selectLastId");
    }

    /**
     * 根据查询条件查询小组话题数目
     *
     * @param paramMap
     *            查询参数
     * @return int
     */
    public int topicCountByParam(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.topicCountByParam", paramMap);
    }

    /**
     * 根据小组话题ID查询小组话题详情
     *
     * @param topicId
     *            话题ID {@link java.lang.Long}
     * @return 对象
     */
    public GroupTopic topicDetail(Long topicId) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.groupTopicDetail", topicId);
    }

    /**
     * 小组其他话题
     *
     * @param paramMap
     *            查询条件
     * @return List
     */
    public List<GroupTopic> otherGroupTopic(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.otherGroupTopic", paramMap);
    }

    /**
     * 特别置顶小组话题
     *
     * @param paramMap
     *            查询参数
     * @return 对象
     */
    public GroupTopic specGroupTopic(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.groupTopicSpecHot", paramMap);
    }
    /**
     * 一般置顶小组话题
     *
     * @param paramMap
     *            查询参数
     * @return List
     */
    public List<GroupTopic> commGroupTopic(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.groupTopicCommHot", paramMap);
    }

    /**
     * 小组话题列表(分页)
     *
     * @param paramMap 条件
     */
    public List<Object> groupTopicList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.groupTopicList", paramMap);
    }
    /**
     * 小组话题总数
     *
     * @param paramMap
     *            查询参数
     * @return int
     */
    public int groupTopicCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.groupTopicCount", paramMap);
    }

    /**
     * 设置特别置顶为不置顶
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    public int noTopTopic(GroupTopic topic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.noTopTopic", topic);
    }

    /**
     * 计算上传话题数目
     *
     * @param paramMap
     *            参数
     * @return int
     */
    public int pubTopicCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.topicCountBySave", paramMap);
    }

    /**
     * 小组话题列表
     *
     * @param paramMap 条件
     * @return List<GroupTopic>
     */
    public List<GroupTopic> topicList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.topicList", paramMap);
    }
    /**
     * 更新推荐次数
     *
     * @param groupTopic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     */
    public int updateTopicRecommend(GroupTopic groupTopic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateTopicRecommend", groupTopic);
    }

    /**
     * 我最新的话题
     *
     * @param paramMap
     *            参数
     */
    public List<GroupTopic> myLatestTopics(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.myLatestGroupTopic", paramMap);
    }

    /**
     * 查看精选热门话题
     *
     * @param paramMap
     *            查询参数
     * @return List
     */
    public List<Object> topicEssenceList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.topicEssenceList", paramMap);
    }

    /**
     * 精选热门话题数量
     *
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     */
    public int topicEssenceListCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.topicEssenceListCount", paramMap);
    }

    /**
     * 根据话题Id删除话题
     *
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return int
     */
    public int deleteByTopicId(Long topicId) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.deleteByTopicId", topicId);
    }

    /**
     * 申请到首页
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    public int updateToIndex(GroupTopic topic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateToIndex", topic);
    }

    /**
     * 首页显示话题
     *
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     * @return list
     */
    public List<GroupTopic> topicIndexList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.selectIndexTopic", paramMap);
    }

    /**
     * 根据话题Id查询申请详情
     *
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return 对象
     */
    public GroupTopic applyTopic(Long topicId) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.applyTopicDetail", topicId);
    }

    /**
     * 话题数目
     *
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     * @return int
     */
    public int topicCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.topicCount", paramMap);
    }

    /**
     * 设置使用心得
     *
     * @param groupTopic
     */
    public int useException(GroupTopic groupTopic) {
        return this.update("com.ningpai.topic.mapper.GroupTopicMapper.updateToUse", groupTopic);
    }
}
