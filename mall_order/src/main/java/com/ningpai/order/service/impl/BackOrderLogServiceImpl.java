package com.ningpai.order.service.impl;

import com.ningpai.order.bean.BackOrderLog;
import com.ningpai.order.dao.BackOrderLogMapper;
import com.ningpai.order.service.BackOrderLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Zhoux on 2015/6/10.
 */
@Service("BackOrderLogService")
public class BackOrderLogServiceImpl implements BackOrderLogService {

    @Resource(name = "BackOrderLogMapper")
    private BackOrderLogMapper backOrderLogMapper;

    /**
     * 退单操作日志
     * 
     * @param backOrderLog
     * @return
     */
    public int insert(BackOrderLog backOrderLog) {
        return backOrderLogMapper.insert(backOrderLog);
    }

    /**
     * 获取退单日志信息
     * 
     * @param backOrderId
     * @return
     */
    public List<BackOrderLog> queryByBackId(Long backOrderId) {
        return backOrderLogMapper.queryByBackId(backOrderId);
    }
}
