/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.ningpai.comment.bean.CommentReplay;

/**
 * 评论回复接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月27日 下午5:24:57
 * @version 1.0
 */
public interface CommentReplayServiceMapper {

    /**
     * 添加回复
     * 
     * @param record
     * @return 0 失败 1成功
     */
    int insertSelective(CommentReplay record);

    /**
     * 根据评论编号获取所有回复
     * 
     * @see List {@link java.util.List}
     * @see CommentReplay {@link com.ningpai.comment.bean.CommentReplay}
     * @param commentId
     *            评论编号
     * @return
     */
    List<CommentReplay> selectByCommentId(Long commentId);

    /**
     * 修改回复
     * 
     * @param replay
     *            品论回复 {@link CommentReplay}
     * @return 0失败 1成功
     */
    int updateCommentRep(CommentReplay replay);

    /**
     * 添加回复 -- 前台回复
     * 
     * @param request
     * @param commentId
     *            评论编号 {@link Long}
     * @param commentContent
     *            回复内容 {@link String}
     * @return 0 失败 1成功
     */
    int addCommentRepaly(HttpServletRequest request, Long commentId, String commentContent);
}
