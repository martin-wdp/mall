package com.ningpai.comment.dao;

import java.util.List;

import com.ningpai.comment.bean.CommentReplay;

/**
 * 评论回复接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月20日 上午9:29:15
 * @version 0.0.1
 */
public interface CommentReplayMapper {

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

}
