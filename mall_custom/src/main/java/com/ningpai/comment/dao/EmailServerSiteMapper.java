package com.ningpai.comment.dao;

import com.ningpai.comment.bean.EmailServerSite;

/**
 * 邮箱服务器数据接口层
 * 
 * @author jiping
 * @since 2014年7月18日 上午10:39:01
 * @version 0.0.1
 */
public interface EmailServerSiteMapper {

    /**
     * 查询邮箱服务器信息
     * 
     * @return BasicEmailServer
     */
    EmailServerSite selectEmailServer();
}
