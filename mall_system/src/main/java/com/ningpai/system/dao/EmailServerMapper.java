/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao;

import com.ningpai.system.bean.EmailServer;

/**
 * 邮箱服务器接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 上午11:26:27
 * @version
 */
public interface EmailServerMapper {
    /**
     * 删除邮箱服务器
     * 
     * @param serverid
     * @return
     */
    int deleteByPrimaryKey(Long serverid);

    /**
     * 添加邮箱服务器
     * 
     * @param record
     * @return
     */
    int insert(EmailServer record);

    /**
     * 添加邮箱服务器--可选字段
     * 
     * @param record
     * @return
     */
    int insertSelective(EmailServer record);

    /**
     * 查询单个邮箱服务器
     * 
     * @param serverid
     * @return
     */
    EmailServer selectByPrimaryKey(Long serverid);

    /**
     * 修改邮箱服务器--可选字段
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(EmailServer record);

    /**
     * 修改邮箱服务器信息
     * 
     * @param record
     * @return int
     */
    int updateByPrimaryKey(EmailServer record);

    /**
     * 查询邮箱服务器信息
     * 
     * @return EmailServer
     */
    EmailServer findServer();
}
