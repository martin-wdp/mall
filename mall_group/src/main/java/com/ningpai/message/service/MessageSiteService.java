package com.ningpai.message.service;

import com.ningpai.message.bean.Message;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;

/**
 * 前台消息Servcie接口
 * @author qiyuanyuan
 *
 */
public interface MessageSiteService {

    /***
     * 根据ID获取获取单个的消息对象
     * @param messageCustomerId 消息ID
     * @return
     */
    Message selectMessageById(Long messageCustomerId);

    /**
     * 添加消息并且发送
     * @param message 消息VO{@link com.ningpai.group.vo}
     * @param customerId 用户ID{@link java.lang.Long]
     * @return int
     */
    int addMessage(MessageVo message,Long customerId);
    
    /**
     * 根据用户Id和消息类型查询消息列表
     * @param customerId 用户Id{@link java.lang.Long}
     * @param messageType 消息类型{@link java.lang.String}
     * @param createAuthorId 消息的创建者{@link java.lang.Long}
     * @param flag 标记发送还是接收
     * @param pb 分页{@link java.lang.String}
     * @return pb
     */
    PageBean messagePb(Long customerId,String messageType,PageBean pb,Long createAuthorId,String flag);
    
    /**
     * 单个删除
     * @param messageCustomerId 消息接收Id{@link java.lang.Long}
     * @return
     */
    int  deleteMessage(Long messageCustomerId); 
    
    /**
     * 根据用户Id删除消息
     * @param messageCustomerIds 消息接收Id{@link java.lang.Long}
     * @return int
     */
    int deleteBatchMessage(Long[] messageCustomerIds);
    
    /**
     * 单个标志已读
     * @param messageCustomerId 消息接收Id{@link java.lang.Long}
     * @return
     */
    int readMessage(Long messageCustomerId);
    
    /**
     * 根据用户Id标记已读
     * @param messageCustomerIds 消息接收Id{@link java.lang.Long}
     * @return int
     */
    int readBatchMessage(Long[] messageCustomerIds);
    
    /**
     * 批量标记已发私信为删除状态
     * @param messageIds 消息Id{@link java.lang.Long}
     * @return
     */
    int deleteBatchByMessageId(Long[] messageIds);
    
    /**
     * 根据消息id删除消息
     * @param messageId 消息Id{@link java.lang.Long}
     * @return
     */
    int deleteByMessageId(Long messageId);
    
    /**
     * 统计各类消息未读数目
     * @param customerId 用户ID {@link java.lang.Long}
     * @param messageType 消息类型 {@link java.lang.String}
     * @return int
     */
    int messageCount(Long customerId, String messageType);
}
