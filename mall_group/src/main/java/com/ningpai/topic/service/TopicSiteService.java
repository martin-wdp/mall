package com.ningpai.topic.service;

import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.bean.TopicRecommend;
import com.ningpai.topic.bean.TopicVo;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 话题前台Service
 * @author qiyuanyuan
 *
 */
public interface TopicSiteService {
    
    /**
     * 添加话题
     * @param topicVo
     * @return String
     */
    String saveTopic(TopicVo topicVo);
    
    
    /**
     * 特别置顶话题
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    GroupTopic specialHotTopic(GroupTopic topic);
    
    
    /**
     * 一般置顶话题
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    List<GroupTopic> commHotTopic(GroupTopic topic);
    
    
    /**
     * 普通话题(分页)
     * @param pb
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param sort 排序 {@link java.lang.String}
     * @return
     */
    PageBean groupTopics(PageBean pb,GroupTopic topic,String sort,String screening);
    
    /**
     * 普通话题
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return list
     */
    List<GroupTopic> commtopicList(GroupTopic topic);
    
    /**
     * 根据话题ID查询话题详情
      * @param topicId 话题ID{@link java.lang.Long}
     * @return 对象
     */
    GroupTopic selectTopicById(Long topicId);
    
    /**
     * 小组其他话题
     * @param topic
     * @return
     */
    List<GroupTopic> otherTopic(GroupTopic topic);
    
    /**
     * 修改小组话题
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    int editTopic(GroupTopic topic);
    
    
    /**
     * 删除话题
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param  customerId 操作用户Id{@link java.lang.Long}
     * @return
     */
    int delTopic(GroupTopic topic,Long customerId);
    
    /**
     * 批量删除话题
     * @param topicId 小组话题ID {@link java.lang.Long}
     * @param  customerId 操作用户Id{@link java.lang.Long}
     * @return int
     */
    int delTopicBatch(Long[] topicId,Long customerId);
    
    /**
     * 批量回应话题
     * @param topicId 小组话题ID {@link java.lang.Long}
     * @return int
     */
    int replyTopicBatch(Long[] topicId);
    
    
    /**
     * 批量禁止回应话题
     * @param topicId  小组话题ID {@link java.lang.Long}
     * @return int
     */
    int unreplyTopicBatch(Long[] topicId);
    
    /**
     * 申请到首页
     * @param topicId 小组话题ID {@link java.lang.Long}
     * @return int
     */
    int recommendTopicBatch(Long[] topicId); 
    
    /**
     * 设精华
     * @param topicId 小组话题ID {@link java.lang.Long}
     * @param essenceTopic 精华标志
     * @return int
     */
    int essenceTopicBatch(Long[] topicId,String essenceTopic);
    
    
    /**
     * 设精华
     * @param topicId 小组话题ID {@link java.lang.Long}
     * @param fever 热帖标志
     * @return int
     */
    int feverTopicBatch(Long[] topicId,String fever);
    
    /**
     * 话题置顶设置
     * @param topicId
     * @param top
     * @return
     */
    int topTopicBatch(Long[] topicId,String top);
    
    
    /**
     * 禁止 回应的话题 
     * @param pb pageBean
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    PageBean noResponseTopic(PageBean pb,GroupTopic topic);
    
    /**
     * 回收站(删除的话题)
     * @param pb pageBean
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    PageBean topicRecycle(PageBean pb,GroupTopic topic);
    
    /**
     * 彻底删除回收站的话题
     * @param topicId  话题Id{@link java.lang.Long}
     * @return
     */
    int delTopic(Long[] topicId);
    
    /**
     * 还原话题
     * @param topicId 话题Id{@link java.lang.Long}
     * @return
     */
    int restore(Long[] topicId);

    
    /**
     * 话题列表
     * @param pb
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param flag 搜索标记{@link java.lang.String}
     * @param keyword 查询条件{@link java.lang.String}
     * @return
     */
    PageBean topicList(PageBean pb,GroupTopic topic,String flag,String keyword);
    
    /**
     * 根据话题Id和用户Id查询用户推荐次数
     * @param shipId 被推荐的对象Id {@link java.lang.Long}
     * @param customerId 用户Id {@link java.lang.Long}
     * @param recommendType 被推荐的对象类型 {@link java.lang.String}
     * @return int
     */
    int topicRecommendCount(Long shipId,Long customerId,String recommendType);
    
    /**
     * 添加推荐数据
     * @param shipId 被推荐的对象Id {@link java.lang.Long}
     * @param customerId 用户Id {@link java.lang.Long}
     * @param recommendType 被推荐的对象类型 {@link java.lang.String}
     * @return int
     */
    int insertTopicRecommend(Long shipId,Long customerId,String recommendType);
    
    /**
     * 更新推荐次数
     * @param topic 小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param customerId 登陆用户Id{@link java.lang.Long}
     * @return
     */
    int updateTopicRecommend(GroupTopic topic,Long customerId);

    
    /**
     * 查看精选热门话题
     * @param pageNo 当前页码 
     * @param pb 分页 
     * @return
     */
    PageBean topicEssence(PageBean pb, String pageNo);
    
    /**
     * 添加赞
     * @param recommend 推荐{@link com.ningpai.topic.bean.TopicRecommend}
     * @return
     */
    int insertRecommen(TopicRecommend recommend);
    
    /**
     * 查询赞的次数
     * @param recommend 推荐{@link com.ningpai.topic.bean.TopicRecommend}
     * @return
     */
    int recommendCount(TopicRecommend recommend);
    
    /**
     * 首页显示话题
     * @param flag 标记 0：热帖 1：精华 {@link java.lang.String}
     * @param number 显示数目
     * @return List
     */
    List<GroupTopic> topicIndexList(String flag,int number);
    
    /**
     * 申请话题恢复
     * @param groupTopic 话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    int applyTopic(GroupTopic groupTopic);
    
    /**
     * 根据话题Id查询申请话题详情
     * @param topicId 话题ID{@link java.lang,Long}
     * @return 对象
     */
    GroupTopic applyTopicDetail(Long topicId);
    
    /**
     * 处理申诉详情
     * @param groupTopic 话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param customerId 用户ID{@link java.lang.Long}
     * @return int
     */
    int applyDetail(GroupTopic groupTopic,Long customerId);
    
    /**
     * 话题总数
     * @return
     */
    int topicCount();
}
