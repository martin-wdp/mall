/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.service;

import java.util.List;

import com.ningpai.util.OrderInfoBean;

/**
 * 消息Service
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 上午10:02:34
 * @version 0.0.1
 */
public interface NoticeService {
    /**
     * 查询消息
     * 
     * @return List<OrderInfoBean> {@link java.util.List}
     */
    List<OrderInfoBean> selectNotice(Long count);

    /**
     * 消息数量
     * 
     * @return
     */
    Long selectNoticeNum();

    /**
     * 删除所有消息
     * 
     * @return
     */
    int updateNotice();

    /**
     * 更具id修改新增消息状态
     * 
     * @param id
     * @return
     */
    int updateById(Long id);
}
