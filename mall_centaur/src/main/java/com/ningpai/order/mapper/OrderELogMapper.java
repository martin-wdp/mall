/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.order.bean.OrderELog;

/**
 * 订单e店宝日志dao
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月22日10:06:57
 * 
 */
public interface OrderELogMapper {

    /**
     * 更新日志
     * 
     * @param eLog
     *            更新参数
     * @return
     */
    int upLog(OrderELog eLog);

    /**
     * 查询日志列表
     * 
     * @param map
     *            分页参数
     * @return
     */
    List<Object> selectOrderELogList(Map<String, Object> map);

    /**
     * 查询日志总条数
     * 
     * @return 总数量
     */
    int selectOrderELogCount();

    /**
     * 记录日志
     * 
     * @param record
     *            日志信息
     * @return 插入结果
     */
    int addLog(OrderELog record);
}
