/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.order.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.OrderELog;
import com.ningpai.order.mapper.OrderELogMapper;

/**
 * 订单e店宝日志
 * 
 * @author NINGPAI-LIH
 * @since 2015年1月22日10:01:28
 *
 */
@Repository("OrderELogMapper")
public class OrderELogMapperImpl extends BasicSqlSupport implements OrderELogMapper {

    /**
     * 记录日志
     * 
     * @see com.ningpai.order.mapper.OrderELogMapper#insertSelective(com.ningpai.order.bean.OrderELog)
     */
    public int addLog(OrderELog record) {
        return this.insert("com.ningpai.order.dao.OrderELogMapper.insertSelective", record);
    }

    /**
     * 查询日志列表
     * 
     * @see com.ningpai.order.mapper.OrderELogMapper#selectOrderELogList(java.util.Map)
     */
    public List<Object> selectOrderELogList(Map<String, Object> map) {
        return this.selectList("com.ningpai.order.dao.OrderELogMapper.selectOrderELogList", map);
    }

    /**
     * 查询日志总条数
     * 
     * @see com.ningpai.order.mapper.OrderELogMapper#selectOrderELogCount()
     */
    public int selectOrderELogCount() {
        return this.selectOne("com.ningpai.order.dao.OrderELogMapper.selectOrderELogCount");
    }

    /**
     * 更新日志
     * 
     * @see com.ningpai.order.mapper.OrderELogMapper#upLog(com.ningpai.order.bean.OrderELog)
     */
    public int upLog(OrderELog eLog) {
        return this.update("com.ningpai.order.dao.OrderELogMapper.updateByPrimaryKeySelective", eLog);
    }

}
