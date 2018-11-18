/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service;

import com.ningpai.system.bean.MessageServer;
import com.ningpai.util.PageBean;

/**
 * 短信服务器服务接口层
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2013年12月14日 下午5:17:30
 * @version
 */
public interface MessageServerService {

    /**
     * 查询短信接口信息
     * 
     * @return MessageServer
     */
    MessageServer findMessage();

    /**
     * 根据ID查询短信接口
     * 
     * @param smsId
     * @return
     */
    MessageServer getMessage(Long smsId);

    /**
     * 修改短信接口信息
     * 
     * @param messageServer
     * @return int
     */
    int updateMessage(MessageServer messageServer);

    /**
     * 分页查询所有短信接口
     * 
     * @param map
     * @return
     */
    PageBean selectAllByPb(PageBean pb);

    /**
     * 修改启用状态
     * 
     * @return
     */
    int updateSmsUsedStatus(Long smsId);
}
