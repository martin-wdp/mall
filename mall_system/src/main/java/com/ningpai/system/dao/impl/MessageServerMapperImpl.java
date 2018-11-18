/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.MessageServer;
import com.ningpai.system.dao.MessageServerMapper;

/**
 * 短信服务器接口实现层
 *
 * @author NINGPAI-LiHaoZe
 * @version 1.0
 * @since 2013年12月14日 下午5:14:58
 */
@Repository("messageServerMapper")
public class MessageServerMapperImpl extends BasicSqlSupport implements
        MessageServerMapper {
    /**
     * 查询短信接口信息
     *
     * @return
     */
    public MessageServer findMessage() {
        MessageServer server = null;
        List<MessageServer> mlist = this
                .selectList("com.ningpai.system.dao.MessageServerMapper.selectByPrimaryKey");
        if (mlist.isEmpty()) {
            server = new MessageServer();
            server.setSmsId(1L);
            this.insert(
                    "com.ningpai.system.dao.MessageServerMapper.insertSelective",
                    server);
        } else {
            server = mlist.get(0);
        }
        return server;
    }

    /**
     * 删除短信服务器
     *
     * @param smsId
     * @return
     */
    public int deleteByPrimaryKey(Long smsId) {
        return 0;
    }

    /**
     * 添加短信服务器
     *
     * @param record
     * @return
     */
    public int insert(MessageServer record) {
        return 0;
    }

    /**
     * 添加短信服务器--可选字段
     *
     * @param record
     * @return
     */
    public int insertSelective(MessageServer record) {
        return 0;
    }

    /**
     * 查询单个短信服务器
     *
     * @param smsId
     * @return
     */
    public MessageServer selectByPrimaryKey(Long smsId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.MessageServerMapper.selectByPrimaryKey",
                        smsId);
    }

    /**
     * 修改短信接口服务器信息
     */
    public int updateByPrimaryKeySelective(MessageServer record) {
        return this
                .update("com.ningpai.system.dao.MessageServerMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改短信服务器
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKey(MessageServer record) {
        return 0;
    }

    /**
     * 分页查询所有短信接口
     *
     * @see com.ningpai.system.dao.MessageServerMapper#selectAllByPb(java.util.Map)
     */
    @Override
    public List<Object> selectAllByPb(Map<String, Object> map) {
        return this
                .selectList(
                        "com.ningpai.system.dao.MessageServerMapper.selectAllByPb",
                        map);
    }

    /**
     * 查询所有短信接口数量
     *
     * @see com.ningpai.system.dao.MessageServerMapper#selectAllCount()
     */
    @Override
    public int selectAllCount() {
        return this
                .selectOne("com.ningpai.system.dao.MessageServerMapper.selectAllCount");
    }

    /**
     * 关闭所有短信接口
     *
     * @see com.ningpai.system.dao.MessageServerMapper#closeAll()
     */
    @Override
    public int closeAll() {
        return this
                .update("com.ningpai.system.dao.MessageServerMapper.closeAll");
    }
}
