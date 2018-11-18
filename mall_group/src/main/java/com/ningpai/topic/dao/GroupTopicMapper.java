package com.ningpai.topic.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.topic.bean.GroupTopic;

/**
 * 小组话题DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupTopicMapper {
    /**
     * 根据主键删除
     * 
     * @param:topicId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int deleteByPrimaryKey(Long topicId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int insert(GroupTopic groupTopic);

    /**
     * 插入，空属性不会插入
     * 
     * @param:pojo对象
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int insertSelective(GroupTopic groupTopic);

    /**
     * 根据主键查询
     * 
     * @param:topicId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    GroupTopic selectByPrimaryKey(Long topicId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int updateByPrimaryKeySelective(GroupTopic groupTopic);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 
     * @param:groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int updateByPrimaryKeyWithBLOBs(GroupTopic groupTopic);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupTopic 对象{@link com.ningpai.topic.bean.GroupTopic}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:38
     */
    int updateByPrimaryKey(GroupTopic groupTopic);

    /**
     * 获取新增主键的查询
     * 
     * @return
     */
    Long selectLastId();

    /**
     * 根据查询条件查询小组话题数目
     * 
     * @param paramMap
     *            查询参数
     * @return int
     */
    int topicCountByParam(Map<String, Object> paramMap);

    /**
     * 根据小组话题ID查询小组话题详情
     * 
     * @param topicId
     *            话题ID {@link java.lang.Long}
     * @return 对象
     */
    GroupTopic topicDetail(Long topicId);

    /**
     * 小组其他话题
     * 
     * @param paramMap
     *            查询条件
     * @return List
     */
    List<GroupTopic> otherGroupTopic(Map<String, Object> paramMap);

    /**
     * 特别置顶小组话题
     * 
     * @param paramMap
     *            查询参数
     * @return 对象
     */
    GroupTopic specGroupTopic(Map<String, Object> paramMap);

    /**
     * 一般置顶小组话题
     * 
     * @param paramMap
     *            查询参数
     * @return List
     */
    List<GroupTopic> commGroupTopic(Map<String, Object> paramMap);

    /**
     * 小组话题列表(分页)
     * 
     * @param paramMap
     * @return
     */
    List<Object> groupTopicList(Map<String, Object> paramMap);

    /**
     * 小组话题列表
     * 
     * @param paramMap
     * @return
     */
    List<GroupTopic> topicList(Map<String, Object> paramMap);

    /**
     * 小组话题总数
     * 
     * @param paramMap
     *            查询参数
     * @return int
     */
    int groupTopicCount(Map<String, Object> paramMap);

    /**
     * 设置特别置顶为不置顶
     * 
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    int noTopTopic(GroupTopic topic);

    /**
     * 计算上传话题数目
     * 
     * @param paramMap
     *            参数
     * @return int
     */
    int pubTopicCount(Map<String, Object> paramMap);

    /**
     * 更新推荐次数
     * 
     * @param groupTopic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    int updateTopicRecommend(GroupTopic groupTopic);

    /**
     * 我最新的话题
     * 
     * @param paramMap
     *            参数
     * @return
     */
    List<GroupTopic> myLatestTopics(Map<String, Object> paramMap);

    /**
     * 查看精选热门话题
     * 
     * @param paramMap
     *            查询参数
     * @return List
     */
    List<Object> topicEssenceList(Map<String, Object> paramMap);

    /**
     * 精选热门话题数量
     * 
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     * @return
     */
    int topicEssenceListCount(Map<String, Object> paramMap);

    /**
     * 根据话题Id删除话题
     * 
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return int
     */
    int deleteByTopicId(Long topicId);

    /**
     * 申请到首页
     * 
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    int updateToIndex(GroupTopic topic);

    /**
     * 首页显示话题
     * 
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     * @return list
     */
    List<GroupTopic> topicIndexList(Map<String, Object> paramMap);

    /**
     * 根据话题Id查询申请详情
     * 
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return 对象
     */
    GroupTopic applyTopic(Long topicId);

    /**
     * 话题数目
     * 
     * @param paramMap
     *            查询条件参数{@link java.util.Map}
     * @return int
     */
    int topicCount(Map<String, Object> paramMap);

    /**
     * 设置使用心得
     * 
     * @param groupTopic
     * @return
     */
    int useException(GroupTopic groupTopic);
}
