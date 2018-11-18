/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.message.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.message.bean.MessageCustomer;
import com.ningpai.message.dao.MessageCustomerMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 消息接收实现
 *@version 2014年5月27日 上午11:51:38
 *@author qiyuanyuan
 */

@Repository("MessageCustomerMapper")
public class MessageCustomerMapperImpl extends BasicSqlSupport implements MessageCustomerMapper{

    /**
     * 根据主键删除
     * @param:messageCutomerId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public int deleteByPrimaryKey(Long messageCutomerId) {
        return this.delete("com.ningpai.message.mapper.MessageCustomerMapper.deleteByPrimaryKey", messageCutomerId);
    }

    /**
     * 插入，空属性也会插入
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public int insert(MessageCustomer messageCustomer) {
        return this.insert("com.ningpai.message.mapper.MessageCustomerMapper.insert", messageCustomer);
    }

    /**
     * 插入，空属性不会插入
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public int insertSelective(MessageCustomer messageCustomer) {
        return this.insert("com.ningpai.message.mapper.MessageCustomerMapper.insertSelective", messageCustomer);
    }
    /**
     * 根据主键查询
     * @param:messageCutomerId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public MessageCustomer selectByPrimaryKey(Long messageCutomerId) {
        return this.selectOne("com.ningpai.message.mapper.MessageCustomerMapper.selectByPrimaryKey", messageCutomerId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:1messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public int updateByPrimaryKeySelective(MessageCustomer messageCustomer) {
        return this.update("com.ningpai.message.mapper.MessageCustomerMapper.updateByPrimaryKeySelective",messageCustomer);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    public int updateByPrimaryKey(MessageCustomer messageCustomer) {
        return this.update("com.ningpai.message.mapper.MessageCustomerMapper.updateByPrimaryKeyb", messageCustomer);
    }

    /**
     * 根据接收消息Id删除消息
     * @param customerId 用户Id{@link java.lang.Long}
     * @return int
     */
    public int deleteMessage(Long[] customerId) {
        return this.update("com.ningpai.message.mapper.MessageCustomerMapper.deleteMessage",customerId);
    }


    /**
     * 根据接收消息Id修改已读状态
     * @param customerId 用户Id{@link java.lang.Long}
     * @return
     */
    public int readMessage(Long[] customerId) {
        return this.update("com.ningpai.message.mapper.MessageCustomerMapper.readMessage", customerId);
    }

    /**
     * 查询消息分类数目
     * @param paramMap
     * @return
     */
    public int messageCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.message.mapper.MessageCustomerMapper.mssagecount", paramMap);
    }

}
