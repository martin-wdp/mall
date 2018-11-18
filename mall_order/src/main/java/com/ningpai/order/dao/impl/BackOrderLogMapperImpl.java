package com.ningpai.order.dao.impl;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.order.bean.BackOrderLog;
import com.ningpai.order.dao.BackOrderLogMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Zhoux on 2015/6/10.
 */
@Repository("BackOrderLogMapper")
public class BackOrderLogMapperImpl extends BasicSqlSupport implements
        BackOrderLogMapper {

    /**
     * 退单操作日志
     * 
     * @param backOrderLog
     * @return
     */
    @Override
    public int insert(BackOrderLog backOrderLog) {
        return this.insert("com.ningpai.order.dao.BackOrderLogMapper.insert",
                backOrderLog);
    }

    /**
     * 获取退单日志信息
     * 
     * @param backOrderId
     * @return
     */
    @Override
    public List<BackOrderLog> queryByBackId(Long backOrderId) {
        return this.selectList(
                "com.ningpai.order.dao.BackOrderLogMapper.queryByBackId",
                backOrderId);
    }

}
