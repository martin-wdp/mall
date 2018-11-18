/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao.impl;

import com.ningpai.comment.bean.Comment;
import com.ningpai.comment.bean.ShareImg;
import com.ningpai.comment.dao.CommentMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 商品评论接口实现类
 * 
 * @author NINGPAI-zhangqiang
 * @since 2013年12月24日 下午6:36:23
 * @version 0.0.1
 */
@Repository("commentMapper")
public class CommentMapperImpl extends BasicSqlSupport implements CommentMapper {

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectAllComment()
     */
    @Override
    public Long selectAllCommentCount() {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectAllCommentCount");
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectCommentByLimit(java.util.Map)
     */
    @Override
    public List<Object> selectCommentByLimit(Map<String, Integer> paramMap) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectCommentByLimit", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectCommentByComment(com.ningpai.comment.bean.Comment)
     */
    @Override
    public List<Object> selectCommentByComment(Comment comment) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectCommentByComment", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectCommentCount(com.ningpai.comment.bean.Comment)
     */
    @Override
    public Long selectCommentCount(Comment comment) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectCommentCount", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#deleteCommentById(java.lang.Long)
     */
    @Override
    public int deleteCommentById(Long parseLong) {
        return this.delete("com.ningpai.comment.dao.CommentMapper.deleteCommentById", parseLong);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectByCommentId(java.lang.Long)
     */
    @Override
    public Comment selectByCommentId(Long commentId) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectByCommentId", commentId);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectAllConsult(java.util.Map)
     */
    @Override
    public List<Object> selectAllConsult(Map<String, Integer> paramMap) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectAllConsult", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectAllConsultCount()
     */
    @Override
    public Long selectAllConsultCount() {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectAllConsultCount");
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectConsultByConsult(com.ningpai.comment.bean.Comment)
     */
    @Override
    public List<Object> selectConsultByConsult(Comment comment) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectConsultByConsult", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectConsultCount(com.ningpai.comment.bean.Comment)
     */
    @Override
    public Long selectConsultCount(Comment comment) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectConsultCount", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectGoodAllCommCount(java.util.Map)
     */
    @Override
    public Long selectGoodAllCommCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectAllGoodsCommentCount", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectAllCommentByGoodsid(java.util.Map)
     */
    @Override
    public List<Object> selectAllCommentByGoodsId(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectAllCommentByGoodsId", paramMap);
    }
    /*
     *
     *
     * @see com.ningpai.comment.dao.CommentMapper#selectAllCommentByGoodsid(java.util.Map)
     */
    @Override
    public List<Object> selectAllCommentByGoodsIdNopage(Map<String, Object> paramMap) {
        paramMap.put("noPage","1");
        return this.selectList("com.ningpai.comment.dao.CommentMapper.selectAllCommentByGoodsId", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#updateComment(com.ningpai.comment.bean.Comment)
     */
    @Override
    public int updateComment(Comment comment) {
        return this.update("com.ningpai.comment.dao.CommentMapper.updateByPrimaryKeySelective", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#addGoodsComment(com.ningpai.comment.bean.Comment)
     */
    @Override
    public int addGoodsComment(Comment comment) {
        return this.insert("com.ningpai.comment.dao.CommentMapper.addGoodsComment", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#queryCustConsultCount(com.ningpai.comment.bean.Comment)
     */
    @Override
    public Long queryCustConsultCount(Comment comment) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.queryCustConsultCount", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#queryCustConsult(java.util.Map)
     */
    @Override
    public List<Object> queryCustConsult(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.queryCustConsult", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#queryCustCommentCount(com.ningpai.comment.bean.Comment)
     */
    @Override
    public Object queryCustCommentCount(Comment comment) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.queryCustCommentCount", comment);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.CommentMapper#queryCustComment(java.util.Map)
     */
    @Override
    public List<Object> queryCustComment(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.queryCustComment", paramMap);
    }

    /*
     * 
     * @see com.ningpai.comment.dao.CommentMapper#queryAllComment()
     */
    @Override
    public List<Comment> queryAllComment() {
        return this.selectList("com.ningpai.comment.dao.CommentMapper.queryAllComment");
    }

    /*
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectGoodsInfoIdByNo(java.lang.String)
     */
    @Override
    public Long selectGoodsInfoIdByNo(String goodsNo) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectGoodsInfoIdByNo",goodsNo);
    }

    /*
     * 
     * @see com.ningpai.comment.dao.CommentMapper#selectSellerAvg(java.lang.Long)
     */
    @Override
    public Comment selectSellerAvg(Long thirdId) {
        
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.goodscommentavg", thirdId);
    }
    /**
     * 会员评论 数量
     * @param paramMap customerId orderId
     * @return
     */
    @Override
    public Long queryCommentCountByCust(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.web.dao.OrderGoodsMapper.queryCommentCountByCust", paramMap);
    }
    /**
     * 会员评论列表
     * @param paramMap
     * @return  List<Object>
     */
    @Override
    public List<Object> queryCommentByCust(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.web.dao.OrderGoodsMapper.queryCommentByCust", paramMap);
    }

    @Override
    public Comment queryCommentByOrderGoodsId(Map<String, Object> map) {
        return this.selectOne("com.ningpai.site.customer.mapper.GoodsCommentMapper.queryCommentByOrderGoodsId", map);
    }

    /**
     * 查询商品评论的用户数量
     * @param goodsId
     * @return
     */
    @Override
    public int selectAllCommentUserCount(Long goodsId) {
        return this.selectOne("com.ningpai.comment.dao.CommentMapper.selectAllCommentUserCount", goodsId);
    }
    /**
     * 查询晒单的图片
     * @param shareId
     * @return
     */
    @Override
    public List<ShareImg> queryImgsByShareId(Long shareId){
         return this.selectList("com.ningpai.comment.dao.ShareImgMapper.queryImgsByShareId", shareId);

    }

    /**
     * 判断是订单商品是否是会员的
     * @param map orderGoodsId 订单货品编号 customerId 用户编号
     * @return
     */
    @Override
    public Long checkNewCommGoodIsUser(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.comment.dao.ShareImgMapper.checkCommGoodIsUser",
                        map);
    }

}
