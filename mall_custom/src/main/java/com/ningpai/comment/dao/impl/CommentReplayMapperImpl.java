/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ningpai.comment.bean.CommentReplay;
import com.ningpai.comment.dao.CommentReplayMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.comment.dao.CommentReplayMapper
 * @author NINGPAI-zhangqiang
 * @since 2013年12月27日 下午5:17:06
 * @version 0.0.1
 */
@Repository("commentReplayMapper")
public class CommentReplayMapperImpl extends BasicSqlSupport implements CommentReplayMapper {

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentReplayMapper#insertSelective(com.ningpai .comment.bean.CommentReplay)
     */
    @Override
    @Transactional
    public int insertSelective(CommentReplay record) {
        return this.insert("com.ningpai.comment.dao.CommentReplayMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentReplayMapper#selectByCommentId(java.lang .Long)
     */
    @Override
    public List<CommentReplay> selectByCommentId(Long commentId) {
        return this.selectList("com.ningpai.comment.dao.CommentReplayMapper.selectByCommentId", commentId);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentReplayMapper#updateCommentRep(com.ningpai .comment.bean.CommentReplay)
     */
    @Override
    @Transactional
    public int updateCommentRep(CommentReplay replay) {
        return this.update("com.ningpai.comment.dao.CommentReplayMapper.updateByPrimaryKeySelective", replay);
    }

}
