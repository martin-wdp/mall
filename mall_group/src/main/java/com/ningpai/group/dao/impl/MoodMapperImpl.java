/*
 * Copyright 2013 NingPai, Inc. All rights reserved.
 * NINGPAI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.Mood;
import com.ningpai.group.dao.MoodMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 个人中心-心情接口
 *
 */
@Repository("MoodMapper")
public class MoodMapperImpl extends BasicSqlSupport implements MoodMapper {

    /**
     * 发布心情
     * @param mood
     * @return int
     */
    public int sendMood(Mood mood) {
        
        return this.insert("com.ningpai.web.dao.MoodMapper.insert",mood);
    }

    /**
     * 获取插入的心情Id
     * @return Long
     */
    public Long lastId() {
        
        return this.selectOne("com.ningpai.web.dao.MoodMapper.selectLastId");
    }

    /**
     * 查询心情列表
     * @param paramMap
     * @return List
     */
    public List<Object> selectMoodList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.web.dao.MoodMapper.selectMoodList",paramMap);
    }

    /**
     * 查询心情总数
     * @param paramMap
     * @return int
     */
    public int selectMoodCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.web.dao.MoodMapper.selectMoodCount",paramMap);
    }

    /**
     * 查询心情评论
     * @param paramMap
     * @return List
     */
    public List<CustomerReply> moodReply(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.group.mapper.CustomerReplyMapper.moodReply", paramMap);
    }

    /**
     * 删除心情
     * @param moodId
     * @return int
     */
    public int delMood(Long moodId) {
        
        return this.update("com.ningpai.web.dao.MoodMapper.delMood",moodId);
    }

    /**
     * 查询某个心情的评论
     * @param moodId
     * @return List
     */
    public List<CustomerReply> selectReplyList(Long moodId) {
        
        return this.selectList("com.ningpai.web.dao.ReplyMapper.selectReplyList",moodId);
    }

    /**
     * 评论心情
     * @param reply
     * @return int
     */
    public int sendReply(CustomerReply reply) {
        
        return this.insert("com.ningpai.web.dao.ReplyMapper.insert",reply);
    }

    /**
     * 查询心情
     * @param moodId
     * @return Mood
     */
    public Mood selectMoodDetail(Long moodId) {
        
        return this.selectOne("com.ningpai.web.dao.MoodMapper.selectMoodDetail",moodId);
    }

    /**
     * 查询最新一条心情
     * @param memberId
     * @return Mood
     */
    public Mood selectOneMood(Long memberId) {
        
        return this.selectOne("com.ningpai.web.dao.MoodMapper.selectOneMood",memberId);
    }


}
