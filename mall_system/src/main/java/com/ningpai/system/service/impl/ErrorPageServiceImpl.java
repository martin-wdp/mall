/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.ErrorPage;
import com.ningpai.system.dao.ErrorPageMapper;
import com.ningpai.system.service.ErrorPageService;
import com.ningpai.util.PageBean;

/**
 * 异常页面SERVICE实现类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月25日 16:17:25
 * @version 1.0
 */
@Service("ErrorPageService")
public class ErrorPageServiceImpl implements ErrorPageService {
    /** spring注解 */
    private ErrorPageMapper errorPageMapper;

    public ErrorPageMapper getErrorPageMapper() {
        return errorPageMapper;
    }

    @Resource(name = "ErrorPageMapper")
    public void setErrorPageMapper(ErrorPageMapper errorPageMapper) {
        this.errorPageMapper = errorPageMapper;
    }

    /**
     * 根据主键删除
     * 
     * @param id
     * @return
     */
    public int delErrorPage(Long id) {
        return errorPageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除异常页面
     * 
     * @param ids
     *            异常页面编号数组
     * @return
     */
    public void batchDelErrorPage(Long[] ids) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                delErrorPage(ids[i]);
            }
        }
    }

    /**
     * 添加异常页面
     * 
     * @param record
     * @return
     */
    public int saveErrorPage(ErrorPage record) {

        return errorPageMapper.insert(record);
    }

    /**
     * 更新异常页面
     * 
     * @param record
     * @return
     */
    public int updateErrorPage(ErrorPage record) {
        return errorPageMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键查询异常页面
     * 
     * @param id
     * @return
     */
    public ErrorPage getErrorPage(Long id) {

        return errorPageMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据分页参数查询异常页面列表
     * 
     * @param pb
     * @return
     */
    public PageBean queryErrorPageByPageBean(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            pb.setRows(this.errorPageMapper.queryTotalCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.errorPageMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }
}
