package com.ningpai.channel.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.channel.bean.SysDictionary;
import com.ningpai.channel.dao.ChannelSysDictionaryMapper;
import com.ningpai.channel.service.ISysDictionaryBiz;
import com.ningpai.system.exception.NPException;
import com.ningpai.util.MyLogger;

/**
 * 系统字典业务类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
@Service("channelSysDictionaryBizImpl")
public class SysDictionaryBizImpl implements ISysDictionaryBiz {

    /** 日志记录 */
    private static final MyLogger LOGGER = new MyLogger(
            SysDictionaryBizImpl.class);

    /** 系统字典数据操作类 */
    private ChannelSysDictionaryMapper channelSysDicMapper;

    /**
     * 获得系统字典数据操作类
     * 
     * @return 系统字典数据操作类
     */
    public final ChannelSysDictionaryMapper getChannelSysDictionaryMapper() {
        return channelSysDicMapper;
    }

    /**
     * 自动注入赋值系统字典数据操作类
     * 
     * @param sysDictionaryDaoImpl
     *            系统字典数据操作类
     */
    @Resource(name = "ChannelSysDictionaryMapper")
    public final void setChannelSysDictionaryMapper(
            final ChannelSysDictionaryMapper channelSysDicMapper) {
        this.channelSysDicMapper = channelSysDicMapper;
    }

    /**
     * 根据系统字典对象的id查询系统字典对象
     * 
     * @param id
     *            系统字典id
     * @return 系统字典对象
     * @throws NPException
     *             自定义异常
     * @author NINGPAI_xiaomm
     * @see com.ningpai.channel.service.ISysDictionaryBiz#getSysDictionaryById(int)
     * @since 2014-03-20 11:03:23
     */
    public final SysDictionary getSysDictionaryById(final int id) {
        if (id == 0) {
            LOGGER.error("对象系统字典的id为0，无法查询对象！");
            return null;
        }
        try {
            return channelSysDicMapper.selectByPrimaryKey(id);
        } catch (Exception e) {
            LOGGER.error("",e);
            return null;
        }
    }

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息
     * 
     * @see com.ningpai.channel.service.ISysDictionaryBiz#getSysDictionaryByField(java.lang.Long)
     */
    public List<SysDictionary> getSysDictionaryByField(Long parentId) {
        return channelSysDicMapper.getSysDictionaryByField(parentId);
    }

}
