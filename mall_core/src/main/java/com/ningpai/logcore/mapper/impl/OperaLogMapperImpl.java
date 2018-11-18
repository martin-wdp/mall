/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logcore.mapper.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.logcore.bean.OperationLog;
import com.ningpai.logcore.mapper.OperaLogMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.logger.mapper.OperaLogMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:28:37
 * @version 0.0.1
 */
@Repository("operaLogMapperCore")
public class OperaLogMapperImpl extends BasicSqlSupport implements OperaLogMapper {
    /*
     * 
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#addOperaLog(com.ningpai.logger .bean.OperationLog)
     */
    @Override
    public int addOperaLog(OperationLog log) {
        return this.insert("com.ningpai.logcore.mapper.OperaLogMapper.insertSelective", log);
    }

    /*
     * 
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectOperaSize(com.ningpai. logger.bean.OperationLog)
     */
    @Override
    public Long selectOperaSize(OperationLog log) {
        return this.selectOne("com.ningpai.logcore.mapper.OperaLogMapper.selectOperaSize", log);
    }

    /*
     * 
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectAllOpera(java.util.Map)
     */
    @Override
    public List<Object> selectAllOpera(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.logcore.mapper.OperaLogMapper.selectAllOpera", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectLogListByDays(java.lang .Long)
     */
    @Override
    public List<Object> selectLogListByDays(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.logcore.mapper.OperaLogMapper.selectLogListByDays", paramMap);
    }

    /*
     * 
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#deleteLog(java.util.Map)
     */
    @Override
    public int deleteLog(Map<String, Object> paramMap) {
        return this.delete("com.ningpai.logcore.mapper.OperaLogMapper.deleteLog", paramMap);
    }

}
