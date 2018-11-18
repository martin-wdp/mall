package com.ningpai.message.dao;

import com.ningpai.message.bean.MessageCustomer;

import java.util.Map;

/**
 * 消息接收DAO
 * @author qiyuanyuan
 *
 */
public interface MessageCustomerMapper {
    /**
     * 根据主键删除
     * @param:messageCutomerId 主键{@link java.lang.Long}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    int deleteByPrimaryKey(Long messageCutomerId);

    /**
     * 插入，空属性也会插入
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    int insert(MessageCustomer messageCustomer);

    /**
     * 插入，空属性不会插入
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    int insertSelective(MessageCustomer messageCustomer);

    /**
     * 根据主键查询
     * @param:messageCutomerId 查询条件,主键值{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    MessageCustomer selectByPrimaryKey(Long messageCutomerId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * @param:1messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    int updateByPrimaryKeySelective(MessageCustomer messageCustomer);

    /**
     * 根据主键修改，空值条件会修改成null
     * @param:messageCustomer 对象{@link com.ningpai.message.bean.MessageCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 17:21:41
     */
    int updateByPrimaryKey(MessageCustomer messageCustomer);
    
    /**
     * 根据接收消息Id删除消息
     * @param messageCutomerId 用户Id{@link java.lang.Long}
     * @return int
     */
    int deleteMessage(Long[] messageCutomerId);
    
    /**
     * 根据接收消息Id修改已读状态
     * @param messageCutomerId 用户Id{@link java.lang.Long}
     * @return
     */
    int readMessage(Long[] messageCutomerId);
    
    /**
     * 查询消息分类数目
     * @param paramMap
     * @return
     */
    int messageCount(Map<String,Object> paramMap);
    
}
