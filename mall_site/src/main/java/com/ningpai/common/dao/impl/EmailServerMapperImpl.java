/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.common.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.common.bean.EmailServer;
import com.ningpai.common.dao.EmailServerMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 邮箱服务器数据接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 上午11:56:44
 * @version 1.0
 */
@Repository("emailServerMapperSite")
public class EmailServerMapperImpl extends BasicSqlSupport implements EmailServerMapper {

    /**
     * 查询邮箱服务器信息
     * @return
     */
    public EmailServer selectEmailServer() {
        return this.selectOne("com.ningpai.common.dao.EmailServerMapper.selectEmailServer");
    }

}
