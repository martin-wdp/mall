/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logcore.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.logcore.bean.OperationLog;
import com.ningpai.logcore.mapper.OperaLogMapper;
import com.ningpai.logcore.service.OperaLogService;

/**
 * @see com.ningpai.logger.service.OperaLogService
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:26:31
 * @version 0.0.1
 */
@Service("operaLogServiceCore")
public class OperaLogServiceImpl implements OperaLogService {
    // Spring注入
    private OperaLogMapper operaLogMapper;

    /*
     * 
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#addOperaLog(com.ningpai.logger
     * .bean.OperationLog)
     */
    @Override
    public int addOperaLog(OperationLog log) {
        // 插入操作日志
        return operaLogMapper.addOperaLog(log);
    }

    public OperaLogMapper getOperaLogMapper() {
        return operaLogMapper;
    }

    @Resource(name = "operaLogMapperCore")
    public void setOperaLogMapper(OperaLogMapper operaLogMapper) {
        this.operaLogMapper = operaLogMapper;
    }

}
