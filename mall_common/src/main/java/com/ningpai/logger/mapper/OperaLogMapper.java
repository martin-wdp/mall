/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.logger.bean.OperationLog;

/**
 * 操作日志Mapper
 * 
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:27:46
 * @version 0.0.1
 */
public interface OperaLogMapper {
    /***
     * 根据ID获取对应日志的操作内容
     * @param opid
     * @return
     */
    OperationLog selectLogById(Long opid);
    /**
     * 添加操作日志
     * 
     * @param log
     *            日志对象 {@link OperationLog}
     * @return
     */
    int addOperaLog(OperationLog log);

    /**
     * 查询日志条数
     * 
     * @param paramMap
     *            日志对象
     * @return 日志条数 {@link Long}
     */
    Long selectOperaSize(Map<String, Object> paramMap);

    /**
     * 查询日志列表
     * 
     * @param paramMap
     *            查询条件 {@link Map}
     * @return 日志集合 List<Object> {@link List}
     */
    List<Object> selectAllOpera(Map<String, Object> paramMap);

    /**
     * 去重获取opname
     * @return
     */
    List<Object> selectDistinctOpName();

    /**
     * 根据时间段查询日志集合
     * 
     * @param paramMap
     *            天 {@link Long}
     * @return 日志集合 List<OperationLog>
     */
    List<Object> selectLogListByDays(Map<String, Object> paramMap);

    /**
     * 删除日志
     * 
     * @param paramMap
     *            opIds 日志ID
     * @return 0 删除失败 1成功
     */
    int deleteLog(Map<String, Object> paramMap);
}
