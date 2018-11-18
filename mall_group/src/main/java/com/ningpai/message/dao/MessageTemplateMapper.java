package com.ningpai.message.dao;

import java.util.Map;

import com.ningpai.message.bean.MessageTemplate;

/**
 * 消息模板接口
 * @author qiyuanyuan
 *
 */
public interface MessageTemplateMapper {
    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    int deleteByPrimaryKey(Long messageTempId);

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    int insert(MessageTemplate record);

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    int insertSelective(MessageTemplate record);

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    MessageTemplate selectByPrimaryKey(Long messageTempId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    int updateByPrimaryKeySelective(MessageTemplate record);

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    int updateByPrimaryKey(MessageTemplate record);
    
    /**
     * 根据消息类型和消息操作查找消息模板
     * @param paramMap 查询参数
     * @return 消息模板对象
     */
    MessageTemplate selectByType(Map<String,Object> paramMap);
}
