package com.ningpai.group.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.group.bean.CustomerReply;

/**
 *客户回复DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface CustomerReplyMapper {
     /**
     * 根据主键删除
     * 
     * @param:replyId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int deleteByPrimaryKey(Long replyId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:customerReply 用户回复对象{@link com.ningpai.group.bean.CustomerReply}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int insert(CustomerReply customerReply);

    /**
     * 插入，空属性不会插入
     * 
     * @param:customerReply 用户回复对象{@link com.ningpai.group.bean.CustomerReply}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int insertSelective(CustomerReply customerReply);

    /**
     * 根据主键查询
     * 
     * @param:查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    CustomerReply selectByPrimaryKey(Long replyId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param::customerReply 用户回复对象 {@link com.ningpai.group.bean.CustomerReply}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int updateByPrimaryKeySelective(CustomerReply customerReply);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * 
     * @param::customerReply 用户回复对象 {@link com.ningpai.group.bean.CustomerReply}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int updateByPrimaryKeyWithBLOBs(CustomerReply customerReply);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param::customerReply 用户回复对象 {@link com.ningpai.group.bean.CustomerReply}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:18:17
     */
    int updateByPrimaryKey(CustomerReply customerReply);

    /**
     * 查询小组相片下的回复
     * 
     * @param paramMap 查询参数
     * @return list
     */
    List<Object> groupImgReplyList(Map<String, Object> paramMap);

    /**
     * 查询小组相片下的回复总数
     * 
     * @param paramMap 查询参数
     * @return int
     */
    int groupImgReplyCount(Map<String, Object> paramMap);

    /**
     * 
     * 根据评论Id查询评论
     * 
     * @param replyId 评论ID{@link java.lang.Long}
     * @return 对象
     */
    CustomerReply selectReplyById(Long replyId);

    /**
     * 根据评论ID删除评论
     * 
     * @param replyId 评论ID{@link java.lang.Long}
     * @return int
     */
    int delReply(Long replyId);

    /**
     * 查询小组相册的最新评论
     * 
     * @param Map 查询参数
     * @return list
     */
    List<CustomerReply> groupImgLastestReply(Map<String, Object> paramMap);

    /**
     * 查询小组话题下的回复
     * 
     * @param paramMap 查询参数
     * @return list
     */
    List<Object> groupTopicReplyList(Map<String, Object> paramMap);

    /**
     * 查询小组话题下的回复总数
     * 
     * @param paramMap 查询参数
     * @return int
     */
    int groupTopicReplyCount(Map<String, Object> paramMap);

    /**
     * 删除图片下的所有评论
     * 
     * @param paramMap replyShipId  关联id{@link java.lang.Long}
     *                replyType 回复类型{@link java.lang.String}
     * @return int
     */
    int delallReplyByShipId(Map<String, Object> paramMap);

    /**
     * 根据类型查询回复数目
     * 
     * @param paramMap 查询参数{@link java.util.Map}
     * @return int
     */
    int customerReplyCount(Map<String, Object> paramMap);
    
}
