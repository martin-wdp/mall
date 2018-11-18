/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.EmailServer;
import com.ningpai.system.dao.EmailServerMapper;
import com.ningpai.system.service.EmailServerService;

/**
 * 邮箱服务器服务实现层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 上午11:26:36
 * @version
 */
@Service("emailServerService")
public class EmailServerServiceImpl implements EmailServerService {
    /** spring注解 */
    @Resource(name = "emailServerMapper")
    private EmailServerMapper emailServerMapper;

    /**
     * 查询email服务器信息
     * 
     * @return EmailServer
     */
    public EmailServer findServer() {
        return emailServerMapper.findServer();
    }

    /**
     * 修改email服务器信息
     * 
     * @return EmailServer
     */
    public int updateServer(EmailServer emailServer) {
        return emailServerMapper.updateByPrimaryKeySelective(emailServer);
    }

}
