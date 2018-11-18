package com.ningpai.order.dao;

import com.ningpai.order.bean.BackOrderLog;

import java.util.List;

/**
 * Created by Zhoux on 2015/6/10.
 */
public interface BackOrderLogMapper {

    /**
     * 退单操作日志
     * 
     * @param backOrderLog
     * @return
     */
    int insert(BackOrderLog backOrderLog);

    /**
     * 获取退单日志信息
     * 
     * @param backOrderId
     * @return
     */
    List<BackOrderLog> queryByBackId(Long backOrderId);

}
