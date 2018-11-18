/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.EmailServer;
import com.ningpai.system.dao.EmailServerMapper;

/**
 * 邮箱服务器接口实现层
 *
 * @author NINGPAI-LiHaoZe
 * @version 1.0
 * @since 2013年12月14日 上午11:25:57
 */
@Repository("emailServerMapper")
public class EmailServerMapperImpl extends BasicSqlSupport implements
        EmailServerMapper {
    /**
     * 查询邮箱服务器信息
     *
     * @return EmailServer
     */
    public EmailServer findServer() {
        EmailServer server = null;
        List<EmailServer> slist = this
                .selectList("com.ningpai.system.dao.EmailServerMapper.selectByPrimaryKey");
        if (slist.isEmpty()) {
            server = new EmailServer();
            server.setServerid(1L);
            this.insert(
                    "com.ningpai.system.dao.EmailServerMapper.insertSelective",
                    server);
        } else {
            server = slist.get(0);
        }
        return server;
    }

    /**
     * 删除邮箱服务器
     *
     * @param serverid
     * @return
     */
    public int deleteByPrimaryKey(Long serverid) {
        return 0;
    }

    /**
     * 添加邮箱服务器
     *
     * @param record
     * @return
     */
    public int insert(EmailServer record) {
        return 0;
    }

    /**
     * 添加邮箱服务器--可选字段
     *
     * @param record
     * @return
     */
    public int insertSelective(EmailServer record) {
        return 0;
    }

    /**
     * 查询单个邮箱服务器
     *
     * @param serverid
     * @return
     */
    public EmailServer selectByPrimaryKey(Long serverid) {
        return null;
    }

    /**
     * 修改邮箱服务器--可选字段
     *
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(EmailServer record) {
        return this
                .update("com.ningpai.system.dao.EmailServerMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改邮箱服务器信息
     *
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(EmailServer record) {
        return 0;
    }

}
