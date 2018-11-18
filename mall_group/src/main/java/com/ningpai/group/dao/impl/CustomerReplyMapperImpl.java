/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.dao.CustomerReplyMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/***
 * 客户回复实现
 * 
 * @version 2014年5月27日 下午2:14:15
 * @author qiyuanyuan
 */

@Repository("CustomerReplyMapper")
public class CustomerReplyMapperImpl extends BasicSqlSupport implements CustomerReplyMapper {

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int deleteByPrimaryKey(Long replyId) {
        return this.delete("com.ningpai.group.mapper.CustomerReplyMapper.deleteByPrimaryKey", replyId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper#
     */
    public int insert(CustomerReply customerReply) {
        return this.insert("com.ningpai.group.mapper.CustomerReplyMapper.insert", customerReply);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int insertSelective(CustomerReply customerReply) {
        return this.insert("com.ningpai.group.mapper.CustomerReplyMapper.insertSelective", customerReply);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public CustomerReply selectByPrimaryKey(Long replyId) {
        return this.selectOne("com.ningpai.group.mapper.CustomerReplyMapper.selectByPrimaryKey", replyId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper#updateByPrimaryKeySelective (com.ningpai.group.bean.CustomerReply)
     */
    public int updateByPrimaryKeySelective(CustomerReply customerReply) {
        return this.update("com.ningpai.group.mapper.CustomerReplyMapper.updateByPrimaryKeySelective", customerReply);

    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper#updateByPrimaryKeyWithBLOBs (com.ningpai.group.bean.CustomerReply)
     */
    public int updateByPrimaryKeyWithBLOBs(CustomerReply customerReply) {
        return this.update("com.ningpai.group.mapper.CustomerReplyMapper.updateByPrimaryKeyWithBLOBs", customerReply);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int updateByPrimaryKey(CustomerReply customerReply) {
        return this.update("com.ningpai.group.mapper.CustomerReplyMapper.updateByPrimaryKey", customerReply);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public List<Object> groupImgReplyList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.CustomerReplyMapper.groupImgReplyList", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int groupImgReplyCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.CustomerReplyMapper.groupImgReplyCount", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public CustomerReply selectReplyById(Long replyId) {
        return this.selectOne("com.ningpai.group.mapper.CustomerReplyMapper.selectReplyById", replyId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper#delReply(java.lang.Long)
     */
    public int delReply(Long replyId) {
        return this.update("com.ningpai.group.mapper.CustomerReplyMapper.delreply", replyId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public List<CustomerReply> groupImgLastestReply(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.CustomerReplyMapper.groupImgLastestReply", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public List<Object> groupTopicReplyList(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.group.mapper.CustomerReplyMapper.groupTopicReplyList", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int groupTopicReplyCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.CustomerReplyMapper.groupTopicReplyCount", paramMap);
    }

    /**
     * 删除图片下的所有评论
     *
     * @param paramMap replyShipId  关联id{@link java.lang.Long}
     *                replyType 回复类型{@link java.lang.String}
     * @return int
     */
    public int delallReplyByShipId(Map<String, Object> paramMap) {
        return this.update("com.ningpai.group.mapper.CustomerReplyMapper.delallReplyByShipId", paramMap);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.CustomerReplyMapper
     */
    public int customerReplyCount(Map<String, Object> paramMap) {
        //
        return this.selectOne("com.ningpai.group.mapper.CustomerReplyMapper.customerReplyCount", paramMap);
    }

}
