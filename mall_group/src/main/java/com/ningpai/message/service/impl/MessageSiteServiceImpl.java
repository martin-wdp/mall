package com.ningpai.message.service.impl;

import com.ningpai.message.bean.Message;
import com.ningpai.message.bean.MessageCustomer;
import com.ningpai.message.dao.MessageCustomerMapper;
import com.ningpai.message.dao.MessageMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 前台小组Service实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("MessageSiteService")
public class MessageSiteServiceImpl implements MessageSiteService {

    /**
     * 消息DAO
     */
    private MessageMapper messageMapper;

    /**
     * 消息接收DAO
     */
    private MessageCustomerMapper customerMapper;

    /***
     * 根据ID获取获取单个的消息对象
     * 
     * @param messageCustomerId
     *            消息ID
     * @return
     */
    @Override
    public Message selectMessageById(Long messageCustomerId) {
        return messageMapper.selectByPrimaryKey(messageCustomerId);
    }

/**
     * 添加消息并且发送
     * @param messageVo 消息VO{@link com.ningpai.group.vo}
     * @param customerId 用户ID{@link java.lang.Long]
     * @return int
     */
    @Transactional
    public int addMessage(MessageVo messageVo, Long customerId) {
        Message message = new Message();
        message.setMessageType(messageVo.getMessageType());
        message.setMessageTitle(messageVo.getMessageTitle());
        message.setMessageCreateTime(new Date());
        message.setGroupId(messageVo.getGroupId());
        message.setMessageDelFlag("0");
        message.setMessageTempId(messageVo.getMessageTempId());
        message.setMessageTempId(messageVo.getMessageTempId());
        message.setMessageContent(messageVo.getMessageContent());
        message.setTopicId(messageVo.getTopicId());
        message.setPicId(messageVo.getPicId());
        // 小组创建人
        message.setCustomerId(messageVo.getCustomerId());
        // 添加消息
        if (customerId != null) {
            // 消息创建者
            message.setMessageAuthorId(customerId);
        }
        int flag = messageMapper.insertSelective(message);
        Long messageId = messageMapper.lastMessageId();
        MessageCustomer messageCustomer = new MessageCustomer();
        messageCustomer.setMessageFlag("0");
        messageCustomer.setMessageId(messageId);
        messageCustomer.setMessageDelFlag("0");
        Long[] customerIds = messageVo.getCustomerRecId();
        if (customerIds != null && customerIds.length > 0) {
            for (int i = 0; i < customerIds.length; i++) {
                messageCustomer.setMessageRecCustomerId(customerIds[i]);
                flag += customerMapper.insertSelective(messageCustomer);
            }
        }
        if (flag > 1) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 根据用户Id和消息类型查询消息列表
     * 
     * @param customerId
     *            用户Id{@link java.lang.Long}
     * @param messageType
     *            消息类型{@link java.lang.String}
     * @param createAuthorId
     *            消息的创建者{@link java.lang.Long}
     * @param flag
     *            标记发送还是接收
     * @param pb
     *            分页{@link java.lang.String}
     * @return pb
     */
    public PageBean messagePb(Long customerId, String messageType, PageBean pb, Long createAuthorId, String flag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("messageType", messageType);
        paramMap.put("customerId", customerId);
        paramMap.put("createAuthorId", createAuthorId);
        if ("1".equals(flag)) {
            paramMap.put("messageDelFlag", '0');
            paramMap.put("messageRecDelFlag", '0');
        }
        // 已发私信
        if ("3".equals(flag)) {
            paramMap.put("messageDelFlag", '0');
            paramMap.put("messageRecDelFlag", null);
        }
        int rows = messageMapper.messageCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(messageMapper.messageTypeList(paramMap));
        return pb;
    }

    /**
     * 单个删除
     * 
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int deleteMessage(Long messageCustomerId) {
        Long[] messageCustomerIds = { messageCustomerId };
        return this.customerMapper.deleteMessage(messageCustomerIds);
    }

    /**
     * 根据用户Id删除消息
     * 
     * @param messageCustomerIds
     *            消息接收Id{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int deleteBatchMessage(Long[] messageCustomerIds) {
        return this.customerMapper.deleteMessage(messageCustomerIds);
    }

    /**
     * 单个标志已读
     * 
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int readMessage(Long messageCustomerId) {
        Long[] messageCustomerIds = { messageCustomerId };
        return this.customerMapper.readMessage(messageCustomerIds);
    }

    /**
     * 根据用户Id标记已读
     * 
     * @param messageCustomerId
     *            消息接收Id{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int readBatchMessage(Long[] messageCustomerId) {
        return this.customerMapper.readMessage(messageCustomerId);
    }

    /**
     * 批量标记已发私信为删除状态
     * 
     * @param messageIds
     *            消息Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int deleteBatchByMessageId(Long[] messageIds) {
        return this.messageMapper.deleteByMessageId(messageIds);
    }

    /**
     * 根据消息id删除消息
     * 
     * @param messageId
     *            消息Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int deleteByMessageId(Long messageId) {
        Long[] messageIds = { messageId };
        return this.messageMapper.deleteByMessageId(messageIds);
    }

    /**
     * 统计各类消息未读数目
     * 
     * @param customerId
     *            用户ID {@link java.lang.Long}
     * @param messageType
     *            消息类型 {@link java.lang.String}
     * @return int
     */
    public int messageCount(Long customerId, String messageType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("messageRecCustomerId", customerId);
        paramMap.put("messageType", messageType);
        return customerMapper.messageCount(paramMap);
    }

    public MessageMapper getMessageMapper() {
        return messageMapper;
    }

    @Resource(name = "MessageMapper")
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public MessageCustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "MessageCustomerMapper")
    public void setCustomerMapper(MessageCustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }
}
