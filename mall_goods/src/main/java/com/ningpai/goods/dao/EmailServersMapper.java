package com.ningpai.goods.dao;

import com.ningpai.goods.vo.EmailServer;

/**
 * 邮箱服务器数据接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 上午11:54:59
 * @version 1.0
 */
public interface EmailServersMapper {

    /**
     * 查询邮箱服务器信息
     * 
     * @return BasicEmailServer
     */
    EmailServer selectEmailServer();
}
