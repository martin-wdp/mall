/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.mapper.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.logger.bean.OperationLog;
import com.ningpai.logger.mapper.OperaLogMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @see com.ningpai.logger.mapper.OperaLogMapper
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:28:37
 * @version 0.0.1
 */
@Repository("operaLogMapper")
public class OperaLogMapperImpl extends BasicSqlSupport implements OperaLogMapper {


    /*
    * 根据ID获取对应日志的操作内容
    *
    * @see com.ningpai.logger.mapper.OperaLogMapper#addOperaLog(com.ningpai.logger .bean.OperationLog)
    */
    @Override
    public  OperationLog selectLogById(Long opid){
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("opid",opid);
        return this.selectOne("com.ningpai.logger.mapper.OperationLogMapper.selectLogById", map);
    }

    /*
     * 添加操作日志
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#addOperaLog(com.ningpai.logger .bean.OperationLog)
     */
    @Override
    public int addOperaLog(OperationLog log) {
        return this.insert("com.ningpai.logger.mapper.OperationLogMapper.insertSelective", log);
    }

    /*
     * 查询日志条数
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectOperaSize(com.ningpai. logger.bean.OperationLog)
     */
    @Override
    public Long selectOperaSize(Map<String, Object> map) {
        return this.selectOne("com.ningpai.logger.mapper.OperationLogMapper.selectOperaSize", map);
    }

    /*
     * 查询日志列表
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectAllOpera(java.util.Map)
     */
    @Override
    public List<Object> selectAllOpera(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.logger.mapper.OperationLogMapper.selectAllOpera", paramMap);
    }

    /**
     * 去重获取opname
     * @return
     */
    @Override
    public List<Object> selectDistinctOpName() {
        return this.selectList("com.ningpai.logger.mapper.OperationLogMapper.selectDistinctOpName");
    }

    /*
     * 根据时间段查询日志集合
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#selectLogListByDays(java.lang .Long)
     */
    @Override
    public List<Object> selectLogListByDays(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.logger.mapper.OperationLogMapper.selectLogListByDays", paramMap);
    }

    /*
     * 删除日志
     * 
     * @see com.ningpai.logger.mapper.OperaLogMapper#deleteLog(java.util.Map)
     */
    @Override
    public int deleteLog(Map<String, Object> paramMap) {
        return this.delete("com.ningpai.logger.mapper.OperationLogMapper.deleteLog", paramMap);
    }

}
