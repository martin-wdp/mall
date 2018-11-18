package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.GroupImg;
import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.util.PageBean;

/**
 * 用户回复接口
 * @author qiyuanyuan
 *
 */
public interface CustomerReplySiteService {

    /**
     * 查询小组相片的评论
     * @param pb
     * @param groupImg
     * @param ta 评论用户Id
     * @return
     */
    PageBean groupImgReply(PageBean pb,GroupImg groupImg,Long ta);
    
    /**
     * 发表评论
     * @param reply 用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param customerId 用户Id {@link java.lang.Long}
     * @param flag 标志回复or评论 {@link java.lang.String}
     * @param reCustomerId 回复对象的id{@link java.lang.Long}
     * @return int
     */
    int pubGroupImgReply(CustomerReply reply,Long customerId,String flag,Long reCustomerId);
    
     /**
     * 根据评论ID删除评论
     * @param replyId 评论ID{@link java.lang.Long}
     * @return int
     */
    int delReply(Long replyId);
    
    /**
     * 根据评论Id查询用户评论
     * @param replyId 评论Id{@link java.lang.Long}
     * @return 对象
     */
    CustomerReply selectReply(Long replyId);
    
    /**
     * 查询小组相册的最新评论
     * @param groupId 小组ID{@link java.lang.Long}
     * @return list
     */
    List<CustomerReply> groupImgLastestReply(Long groupId);
    
    /**
     * 话题评论
     * @param pb 分页
     * @param topic 
     * @param ta 评论用户Id
     * @return
     */
    PageBean topicReply(PageBean pb,GroupTopic topic,Long ta);
    
    /**
     * 发表话题评论
     * @param reply 用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param flag 标志回复or评论 {@link java.lang.String}
     * @param reCustomerId 回复对象的id{@link java.lang.Long}  
     * @return int
     */
    int pubGroupTopicReply(CustomerReply reply,Long customerdId,String flag,Long reCustomerId);
    
    /**
     * 删除并拉黑
     * @param reply 用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param groupId 小组Id{@link java.lang.Long}
     * @return int
     */
    int delAndBlack(CustomerReply reply,Long groupId);
    
    /**
     * 评论心情
     * @param reply
     * @return
     */
    int sendmoodreply(CustomerReply reply,Long customerId,String flag,Long reCustomerId);
    
    
     /**
     * 根据类型查询回复数目
     * @return int
     */
    int customerReplyCount(String typeFlag);
    
}
