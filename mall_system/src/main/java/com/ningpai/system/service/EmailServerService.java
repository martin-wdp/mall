/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.EmailServer;

/**
 * 邮箱服务器服务接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 上午11:27:02
 * @version 1.0
 */
public interface EmailServerService {

    /**
     * 查询邮箱服务器信息
     * 
     * @return EmailServer
     */
    EmailServer findServer();

    /**
     * 修改服务器信息
     * 
     * @param emailServer
     * @return int
     */
    int updateServer(EmailServer emailServer);
}
