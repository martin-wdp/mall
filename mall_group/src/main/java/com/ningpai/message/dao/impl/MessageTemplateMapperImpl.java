package com.ningpai.message.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 消息模板接口实现类
 * @author qiyuanyuan
 *
 */

@Repository("MessageTemplateMapper")
public class MessageTemplateMapperImpl extends BasicSqlSupport implements MessageTemplateMapper{

    /**
     * 根据主键删除
     * 参数:主键
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public int deleteByPrimaryKey(Long messageTempId) {
        return this.delete("com.ningpai.dao.MessageTemplateMapper.deleteByPrimaryKey", messageTempId);
    }

    /**
     * 插入，空属性也会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public int insert(MessageTemplate record) {
        return this.insert("com.ningpai.dao.MessageTemplateMapper.insert",record);
    }

    /**
     * 插入，空属性不会插入
     * 参数:pojo对象
     * 返回:删除个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public int insertSelective(MessageTemplate record) {
        
        return this.insert("com.ningpai.dao.MessageTemplateMapper.insertSelective", record);
    }

    /**
     * 根据主键查询
     * 参数:查询条件,主键值
     * 返回:对象
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public MessageTemplate selectByPrimaryKey(Long messageTempId) {
        
        return this.selectOne("com.ningpai.dao.MessageTemplateMapper.selectByPrimaryKey", messageTempId);
    }

    /**
     * 根据主键修改，空值条件不会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public int updateByPrimaryKeySelective(MessageTemplate record) {
        
        return this.update("com.ningpai.dao.MessageTemplateMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     * 参数:1.要修改成的值
     * 返回:成功修改个数
     * @ibatorgenerated 2014-07-31 16:59:31
     */
    public int updateByPrimaryKey(MessageTemplate record) {
        
        return this.update("com.ningpai.dao.MessageTemplateMapper.updateByPrimaryKey", record);
    }

    /**
     * 根据消息类型和消息操作查找消息模板
     * @param paramMap 查询参数
     * @return 消息模板对象
     */
    public MessageTemplate selectByType(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.dao.MessageTemplateMapper.selectByType", paramMap);
    }

}
