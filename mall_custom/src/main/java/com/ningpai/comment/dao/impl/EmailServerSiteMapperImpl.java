/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.comment.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.comment.bean.EmailServerSite;
import com.ningpai.comment.dao.EmailServerSiteMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 邮箱服务器数据接口层
 * 
 * @author jiping
 * @since 2014年7月18日 上午10:38:37
 * @version 0.0.1
 */
@Repository("emailSiteMapper")
public class EmailServerSiteMapperImpl extends BasicSqlSupport implements EmailServerSiteMapper {

    /**
     * 查询邮箱服务器信息
     *
     * @return BasicEmailServer
     */
    public EmailServerSite selectEmailServer() {
        return this.selectOne("com.ningpai.common.dao.EmailServerSiteMapper.selectEmailServer");
    }

}
