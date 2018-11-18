package com.ningpai.comment.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.comment.bean.ShareImg;
import org.springframework.stereotype.Repository;

import com.ningpai.comment.bean.Comment;

/**
 * 会员评论接口
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月20日 上午9:28:55
 * @version 0.0.1
 */
@Repository
public interface CommentMapper {

    /**
     * 查询所有评论
     * 
     * @see java.util.List {@link java.util.List}
     * @return List 评论列表集合
     */
    Long selectAllCommentCount();

    /**
     * 分页查询 评论
     * 
     * @see java.util.List {@link java.util.List}
     * @return 评论列表集合
     */
    List<Object> selectCommentByLimit(Map<String, Integer> paramMap);

    /**
     * 按条件查询 评论
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @see java.util.List {@link java.util.List}
     * @return
     */
    List<Object> selectCommentByComment(Comment comment);

    /**
     * 按条件查询 评论数量
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return int 评论数量
     */
    Long selectCommentCount(Comment comment);

    /**
     * 删除评论
     * 
     * @see parseLong 评论编号
     * @see java.lang.Integer {@link java.lang.Integer}
     * @return 0 失败 1 成功
     */
    int deleteCommentById(Long parseLong);

    /**
     * 根据评论编号 查询评论
     * 
     * @see java.lang.Integer {@link java.lang.Integer}
     * @param commentId
     * @return Comment {@link com.ningpai.comment.bean.Comment}
     */
    Comment selectByCommentId(Long commentId);

    /**
     * 按条件查询 咨询
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @see java.util.List {@link java.util.List}
     * @return
     */
    List<Object> selectAllConsult(Map<String, Integer> paramMap);

    /**
     * 咨询总数量
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return int 咨询数量
     */
    Long selectAllConsultCount();

    /**
     * 按条件查询 咨询
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @see java.util.List {@link java.util.List}
     * @return
     */
    List<Object> selectConsultByConsult(Comment comment);

    /**
     * 按条件查询 咨询数量
     * 
     * @param comment
     *            {@link com.ningpai.comment.bean.Comment}
     * @return int 咨询数量
     */
    Long selectConsultCount(Comment comment);

    /**
     * 评论数量
     * 
     * @param paramMap
     * @return Long {@link java.lang.Long}
     */
    Long selectGoodAllCommCount(Map<String, Object> paramMap);

    /**
     * 查询所有评论 按商品编号
     * 
     * @param paramMap
     * @return List {@link java.util.List}
     */
    List<Object> selectAllCommentByGoodsId(Map<String, Object> paramMap);
    /**
     * 查询所有评论 按商品编号
     *
     * @param paramMap
     * @return List {@link java.util.List}
     */
    List<Object> selectAllCommentByGoodsIdNopage(Map<String, Object> paramMap);

    /**
     * 修改评论
     * 
     * @param comment
     *            评论对象{@link com.ningpai.comment.bean.Comment}
     * @return 0失败 1成功
     */
    int updateComment(Comment comment);

    /**
     * 添加商品评论
     * 
     * @param comment
     *            评论信息 {@link Comment}
     * @return 评论编号
     */
    int addGoodsComment(Comment comment);

    /**
     * 会员咨询数量
     * 
     * @param comment
     *            咨询实体 {@link Comment}
     * @return Long 数量 {@link Long}
     */
    Long queryCustConsultCount(Comment comment);

    /**
     * 查询所有咨询 按会员编号
     * 
     * @param paramMap
     * @return List {@link java.util.List}
     */
    List<Object> queryCustConsult(Map<String, Object> paramMap);

    /**
     * 会员评论数量
     * 
     * @param comment
     *            咨询实体 {@link Comment}
     * @return Long 数量 {@link Long}
     */
    Object queryCustCommentCount(Comment comment);

    /**
     * 查询所有评论 按会员编号
     * 
     * @param paramMap
     * @return List {@link java.util.List}
     */
    List<Object> queryCustComment(Map<String, Object> paramMap);
    
    /**
     * 查询所有评论列表
     * @return
     */
    List<Comment> queryAllComment();

    /**
     * 根据货品编号查询货品id
     * @param goodsNo
     * @return
     */
    Long selectGoodsInfoIdByNo(String goodsNo);
    
    /**
     * 查询店铺的评级
     * @param thirdId
     * @return
     */
    Comment selectSellerAvg(Long thirdId);

    /**
     * 会员评论 数量
     * @param paramMap customerId orderId
     * @return
     */
    Long queryCommentCountByCust(Map<String, Object> paramMap);

    /**
     * 会员评论列表
     * @param paramMap
     * @return  List<Object>
     */
    List<Object> queryCommentByCust(Map<String, Object> paramMap);

    /**
     * 根据订单商品编号和会员编号查询商品评论晒单信息
     * @param  map orderGoodsId 订单商品编号 customerId 会员编号
     * @return
     */
    Comment queryCommentByOrderGoodsId(Map<String, Object> map);

    /**
     * 查询商品评论的用户数量
     * @param goodsId
     * @return
     */
    int selectAllCommentUserCount(Long goodsId);

    /**
     * 查询晒单的图片
     * @param shareId
     * @return
     */
    List<ShareImg> queryImgsByShareId(Long shareId);

    /**
     * 判断是订单商品是否是会员的
     * @param map orderGoodsId 订单货品编号 customerId 用户编号
     * @return int 1是 0 否
     */
    Long checkNewCommGoodIsUser(Map<String, Object> map);

}
