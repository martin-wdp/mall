/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.group.dao;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.Mood;

import java.util.List;
import java.util.Map;

/**
 * 个人中心--个人心情接口
 *
 */
public  interface MoodMapper {

    /**
     * 发布心情
     * @param mood
     * @return int
     */
    int sendMood(Mood mood);
    /**
     * 获取插入的心情Id
     * @return Long
     */
    Long lastId();
    /**
     * 查询心情列表
     * @param paramMap
     * @return List
     */
    List<Object> selectMoodList(Map<String, Object> paramMap);
    /**
     * 查询心情总数
     * @param paramMap
     * @return int
     */
    int selectMoodCount(Map<String, Object> paramMap);
    /**
     * 查询心情评论
     * @param paramMap
     * @return List
     */ 
    List<CustomerReply> moodReply(Map<String, Object> paramMap);
    /**
     * 删除心情
     * @param moodId
     * @return int
     */
    int delMood(Long moodId);
    /**
     * 查询某个心情的评论
     * @param memberId
     * @return List
     */
    List<CustomerReply> selectReplyList(Long memberId);
    /**
     * 评论心情
     * @param reply
     * @return int
     */
    int sendReply(CustomerReply reply);
    /**
     * 查询心情
     * @param relationshipId
     * @return Mood
     */
    Mood selectMoodDetail(Long relationshipId);


    /**
     * 查询最新一条心情
     * @param memberId
     * @return Mood
     */
    Mood selectOneMood(Long memberId);
}
