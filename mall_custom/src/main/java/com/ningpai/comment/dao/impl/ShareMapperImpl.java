/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao.impl;

import java.util.List;
import java.util.Map;

import com.ningpai.comment.vo.ShareReplyVo;
import org.springframework.stereotype.Repository;

import com.ningpai.comment.bean.Share;
import com.ningpai.comment.bean.ShareImg;
import com.ningpai.comment.bean.ShareReply;
import com.ningpai.comment.dao.ShareMapper;
import com.ningpai.comment.vo.ShareVo;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.transaction.annotation.Transactional;

/**
 * @see com.ningpai.comment.dao.ShareMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年7月1日 下午4:34:23
 * @version 0.0.1
 */
@Repository("shareMapper")
public class ShareMapperImpl extends BasicSqlSupport implements ShareMapper {

    /*
     * 
     * 
     * @see com.ningpai.site.comment.dao.ShareMapper#queryTopShare(int)
     */
    @Override
    public List<Object> queryTopShare(int size) {
        return this.selectList("com.ningpai.comment.dao.ShareMapper.selectTopShare", size);
    }

    @Override
    public void saveShare(Share share) {
        this.insert("com.ningpai.comment.dao.ShareMapper.insertShare", share);
    }

    @Override
    public void saveShareImg(ShareImg shareImg) {
        this.insert("com.ningpai.comment.dao.ShareMapper.insertShareImg", shareImg);
    }

    @Override
    public Object selectShareById(Long shareId) {
        return this.selectOne("com.ningpai.comment.dao.ShareMapper.selectShareById", shareId);
    }

    @Override
    public int saveShareReply(ShareReply reply) {
       return this.insert("com.ningpai.comment.dao.ShareReplyMapper.insertShareReply", reply);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#updateShareIdByOrderGoodsId(java.util.Map)
     */
    @Override
    public int updateShareIdByOrderGoodsId(Map<String, Object> paramMap) {
        return this.update("com.ningpai.comment.dao.ShareMapper.updateShareIdByOrderGoodsId", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#selectAllShareCount(com.ningpai.comment.bean.Share)
     */
    @Override
    public Long selectAllShareCount(ShareVo share) {
        return this.selectOne("com.ningpai.comment.dao.ShareMapper.selectAllShareCount", share);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#selectAllShare(java.util.Map)
     */
    @Override
    public List<Object> selectAllShare(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.comment.dao.ShareMapper.selectAllShare", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#selectShareDetail(java.lang.Long)
     */
    @Override
    public ShareVo selectShareDetail(Long shareId) {
        return this.selectOne("com.ningpai.comment.dao.ShareMapper.selectShareByShareIdboss", shareId);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#updateShare(com.ningpai.comment.bean.Share)
     */
    @Override
    public int updateShare(Share share) {
        return this.update("com.ningpai.comment.dao.ShareMapper.updateShare", share);
    }

    @Override
    @Transactional
    public int updateShareReplay(ShareReply replay) {
        return this.update("com.ningpai.comment.dao.ShareReplyMapper.updateShareReply", replay);
    }

    /*
         * 
         *
         * @see com.ningpai.comment.dao.ShareMapper#deleteShareByBids(java.util.Map)
         */
    @Override
    public int deleteShareByBids(Map<String, Object> paramMap) {
        return this.update("com.ningpai.comment.dao.ShareMapper.deleteShareByBids", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#updateShareToIndex(java.util.Map)
     */
    @Override
    public int updateShareToIndex(Map<String, Object> paramMap) {
        return this.update("com.ningpai.comment.dao.ShareMapper.updateShareToIndex", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.comment.dao.ShareMapper#queryIndexShareCount()
     */
    @Override
    public Long queryIndexShareCount() {
        return this.selectOne("com.ningpai.comment.dao.ShareMapper.queryIndexShareCount");
    }

    @Override
    public List<ShareReplyVo> queryShareReplyByShareId(Long shareId) {
        return this.selectList("com.ningpai.comment.dao.ShareReplyMapper.queryShareReplyByShareId",shareId);
    }
}
