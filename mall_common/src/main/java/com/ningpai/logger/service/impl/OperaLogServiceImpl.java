/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.logger.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.ningpai.logger.bean.OperationLog;
import com.ningpai.logger.mapper.OperaLogMapper;
import com.ningpai.logger.service.OperaLogService;
import com.ningpai.logger.util.ExportLog;
import com.ningpai.util.MapUtil;
import com.ningpai.util.PageBean;
import com.ningpai.version.util.CommonConstant;

/**
 * @see com.ningpai.logger.service.OperaLogService
 * @author NINGPAI-zhangqiang
 * @since 2014年6月25日 上午10:26:31
 * @version 0.0.1
 */
@Service("operaLogService")
public class OperaLogServiceImpl implements OperaLogService {
    private OperaLogMapper operaLogMapper;

    /*
     * 根据ID获取对应日志的操作内容
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#addOperaLog(com.ningpai.logger
     * .bean.OperationLog)
     */
    @Override
    public OperationLog selectLogById(Long opid) {
        return operaLogMapper.selectLogById(opid);
    }

    /*
     * 添加操作日志
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#addOperaLog(com.ningpai.logger
     * .bean.OperationLog)
     */
    @Override
    public int addOperaLog(OperationLog log) {
        return operaLogMapper.addOperaLog(log);
    }

    /**
     * 去重获取opname
     * @return
     */
    @Override
    public List<Object> selectDistinctOpName() {
        return operaLogMapper.selectDistinctOpName();
    }

    /*
     * 查询操作日志
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#selectAllOpera(com.ningpai
     * .util.PageBean, com.ningpai.logger.bean.OperationLog)
     */
    @Override
    public PageBean selectAllOpera(PageBean pageBean, OperationLog log,
            String starTime, String endTime) {
        // 设置查询参数
        Map<String, Object> paramMap  = MapUtil.getParamsMap(log);
        paramMap.put("startTime", starTime);
        paramMap.put("endTime", endTime);
        // 查询总数
        Long count = operaLogMapper.selectOperaSize(paramMap);
        pageBean.setRows(Integer.parseInt(count == null ? "0" : count
                .toString()));
        try {

            if (pageBean.getPageNo() > pageBean.getLastPageNo()) {
                pageBean.setPageNo(pageBean.getLastPageNo());
            }
            if (pageBean.getPageNo() == 0) {
                pageBean.setPageNo(1);
            }

            paramMap.put(CommonConstant.STARTNUM, pageBean.getStartRowNum());
            paramMap.put(CommonConstant.ENDNUM, pageBean.getEndRowNum());
            // 查询列表
            pageBean.setList(operaLogMapper.selectAllOpera(paramMap));
            pageBean.setObjectBean(log);
        } finally {
            // 置空对象
            paramMap = null;
        }
        return pageBean;
    }

    /*
     * 根据时间段导出日志
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#exportExcel(java.lang.Long)
     */
    @Override
    public void exportExcel(HttpServletResponse response, Long days) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            // 设置查询参数
            paramMap.put("days", days);
            ExportLog.exportForLog(
                    operaLogMapper.selectLogListByDays(paramMap), response);
        } finally {
            paramMap = null;
        }

    }

    /*
     * 删除日志
     * 
     * @see
     * com.ningpai.logger.service.OperaLogService#deleteLog(java.lang.String[])
     */
    @Override
    public int deleteLog(String[] opIds) {
        int count = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        try {
            paramMap.put("opIds", opIds);
            count = operaLogMapper.deleteLog(paramMap);
        } finally {
            paramMap = null;
        }
        return count;
    }

    /**
     * 查看最新的订单日志
     *
     * @param pageBean
     *            分页参数
     * @return 日志列表
     * @author lih
     */
    @Override
    public PageBean queryNewLog(PageBean pageBean) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Long count = operaLogMapper.selectOperaSize(paramMap);
        pageBean.setRows(Integer.parseInt(count == null ? "0" : count
                .toString()));
        // 设置分页参数
        if (pageBean.getPageNo() > pageBean.getLastPageNo()) {
            pageBean.setPageNo(pageBean.getLastPageNo());
        }
        // 判断分页参数是否为0
        if (pageBean.getPageNo() == 0) {
            pageBean.setPageNo(1);
        }
        // 设置开始行数
        paramMap.put(CommonConstant.STARTNUM, pageBean.getStartRowNum());
        // 设置结束行数
        paramMap.put(CommonConstant.ENDNUM, pageBean.getEndRowNum());
        // 设置集合
        pageBean.setList(operaLogMapper.selectAllOpera(paramMap));
        return pageBean;
    }

    public OperaLogMapper getOperaLogMapper() {
        return operaLogMapper;
    }

    @Resource(name = "operaLogMapper")
    public void setOperaLogMapper(OperaLogMapper operaLogMapper) {
        this.operaLogMapper = operaLogMapper;
    }

}
