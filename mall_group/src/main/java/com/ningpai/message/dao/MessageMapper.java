package com.ningpai.message.dao;

import com.ningpai.message.bean.Message;

import java.util.List;
import java.util.Map;

/**
 * 消息DAO
 * @author qiyuanyuan
 *
 */
public interface MessageMapper {
    /**
     * 根据主键删除
     * @param:messageId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int deleteByPrimaryKey(Long messageId);

    /**
     * 插入，空属性也会插入
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int insert(Message message);

    /**
     * 插入，空属性不会插入
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int insertSelective(Message message);

    /**
     * 根据主键查询
     * @param:messageId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    Message selectByPrimaryKey(Long messageId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int updateByPrimaryKeySelective(Message message);

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int updateByPrimaryKeyWithBLOBs(Message message);

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    int updateByPrimaryKey(Message message);
    
    /**
     * 获取插入消息的Id
     * @return Long
     */
    Long lastMessageId();
    
    /**
     * 根据用户Id和消息类型查询消息列表
     * @param paramMap 查询参数
     * @return List
     */
    List<Object> messageTypeList(Map<String,Object> paramMap);
    
    /**
     * 根据用户Id和消息类型统计消息数量
     * @param paramMap 参数
     * @return int
     */
    int messageCount(Map<String,Object> paramMap);
    
    /**
     * 修改已发私信为删除状态
     * @param messageId 消息Id
     * @return int
     */
    int deleteByMessageId(Long[] messageId);
    
}
