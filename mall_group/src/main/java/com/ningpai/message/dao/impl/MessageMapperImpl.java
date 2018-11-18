/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.message.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.message.bean.Message;
import com.ningpai.message.dao.MessageMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 消息DAO实现
 *@version 2014年5月26日 下午2:15:55
 *@author qiyuanyuan
 */

@Repository("MessageMapper")
public class MessageMapperImpl extends BasicSqlSupport implements MessageMapper{

    /**
     * 根据主键删除
     * @param:messageId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int deleteByPrimaryKey(Long messageId) {
        return this.delete("com.ningpai.message.mapper.MessageMapper.deleteByPrimaryKey", messageId);
    }

    /**
     * 插入，空属性也会插入
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int insert(Message message) {
        return this.insert("com.ningpai.message.mapper.MessageMapper.insert", message);
    }

    /**
     * 插入，空属性不会插入
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int insertSelective(Message message) {
        return this.insert("com.ningpai.message.mapper.MessageMapper.insertSelective", message);
    }

    /**
     * 根据主键查询
     * @param:messageId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public Message selectByPrimaryKey(Long messageId) {
        return this.selectOne("com.ningpai.message.mapper.MessageMapper.selectByPrimaryKey", messageId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int updateByPrimaryKeySelective(Message message) {
        return this.update("com.ningpai.message.mapper.MessageMapper.updateByPrimaryKeySelective", message);
    }

    /**
     * 根据主键修改，空值条件会修改成null,支持大字段类型
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int updateByPrimaryKeyWithBLOBs(Message message) {
        return this.update("com.ningpai.message.mapper.MessageMapper.updateByPrimaryKeyWithBLOBs", message);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:message 对象{@link com.ningpai.message.bean.Message}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:42
     */
    public int updateByPrimaryKey(Message message) {
        return this.update("com.ningpai.message.mapper.MessageMapper.updateByPrimaryKey", message);
    }

    /**
     * 获取插入消息的Id
     * @return Long
     */
    public Long lastMessageId() {
        return this.selectOne("com.ningpai.message.mapper.MessageMapper.selectLastId");
    }

    /**
     * 根据用户Id和消息类型查询消息列表
     * @param paramMap 查询参数
     * @return List
     */
    public List<Object> messageTypeList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.message.mapper.MessageMapper.messageTypeList", paramMap);
    }

    /**
     * 根据用户Id和消息类型统计消息数量
     * @param paramMap 参数
     * @return int
     */
    public int messageCount(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.message.mapper.MessageMapper.messagecount", paramMap);
    }

    /**
     * 修改已发私信为删除状态
     * @param messageId 消息Id
     * @return int
     */
    public int deleteByMessageId(Long[] messageId) {
        return this.update("com.ningpai.message.mapper.MessageMapper.updateByMessageId", messageId);
    }

}
