/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.service;

import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;

/**
 * 话题后台Service
 * 
 * @version 2014年5月26日 下午2:20:03
 * @author qiyuanyuan
 */

public interface TopicService {

    /**
     * 后台查询话题列表
     * 
     * @param pb
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    PageBean topicList(PageBean pb, GroupTopic topic, SelectBean selectBean);

    /**
     * 根据话题ID查询话题详情
     * 
     * @param topicId
     *            话题ID{@link java.lang.Long}
     * @return 对象
     */
    GroupTopic selectTopicById(Long topicId);

    /**
     * 修改小组话题
     * 
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    int editTopic(GroupTopic topic);

    /**
     * 删除话题
     * 
     * @param topicIds
     *            话题Id{@link java.lang.String}
     * @return
     */
    int deleteTopic(String[] topicIds);

    /**
     * 申请到首页
     * 
     * @param topicIds
     *            话题id {@link java.lang.Long}
     * @param topicIndexView
     *            首页标记 {@link java.lang.String}
     * @return int
     */
    int updateToIndex(String[] topicIds, String topicIndexView);

    /**
     * 设置为使用心得
     * 
     * @param topicIds
     * @param topicIsUse
     * @return
     */
    int useException(String[] topicIds, String topicIsUse);

}
