/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.manager.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.util.OrderInfoBean;

/**
 * 消息Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年4月22日 上午10:03:05
 * @version 0.0.1
 */
public interface NoticeMapper {
    /**
     * 查询消息
     * 
     * @return List<OrderInfoBean> {@link java.util.List}
     */
    List<OrderInfoBean> selectNotice(Map<String, Object> paramMap);

    /**
     * 消息条数
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
