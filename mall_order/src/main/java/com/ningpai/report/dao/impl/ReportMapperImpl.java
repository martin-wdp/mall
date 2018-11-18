/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.report.dao.impl;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.List;

import com.ningpai.report.bean.Report;
import com.ningpai.report.dao.ReportMapper;

/**  
 * @Description: np_report的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2014-12-17 14:06:51
 * @version V1.0  
 */
@Repository("ReportMapper") 
public class ReportMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements ReportMapper {

    /*
    *  
    * @see com.ningpai.report.dao.ReportMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long reportId) {
        return this.update("com.ningpai.mybatis.mapper.ReportMapper.deleteByPrimaryKey", reportId);
    }

    /*
    * 
    * @see com.ningpai.report.dao.ReportMapper#insertSelective(com.ningpai.report.bean.Report)
    */
    @Override
    public int insertSelective(Report record) {
        return this.insert("com.ningpai.mybatis.mapper.ReportMapper.insertSelective", record);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public Report selectByPrimaryKey(Long reportId) {
        return this.selectOne("com.ningpai.mybatis.mapper.ReportMapper.selectByPrimaryKey", reportId);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#updateByPrimaryKeySelective(com.ningpai.report.bean.Report)
    */
    @Override
    public int updateByPrimaryKeySelective(Report record) {
        return this.update("com.ningpai.mybatis.mapper.ReportMapper.updateByPrimaryKeySelective", record);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#deleteMuilti(java.lang.Long reportIds)
    */
    @Override
    public int deleteMuilti(Long[] reportIds) {
        return this.update("com.ningpai.mybatis.mapper.ReportMapper.deleteMuilti", reportIds);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.ReportMapper.selectList", map);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.ReportMapper.selectListCount", map);
    }
    
    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectReportCateList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.ReportMapper.selectReportCateList", map);
    }

    /* 
    * 
    * @see com.ningpai.report.dao.ReportMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectReportCateListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.ReportMapper.selectReportCateListCount", map);
    }
    
    @Override
    public List<Report> queryReportData(Map<String, Object> paramMap) {
        return this.selectList("com.ningpai.mybatis.mapper.ReportMapper.queryReportData", paramMap);
    }
    
    @Override
    public Report selectOneByParam(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.mybatis.mapper.ReportMapper.selectOneByParam", paramMap);
    }

    @Override
    public void deleteByStoreId(Long storeId) {
        this.update("com.ningpai.mybatis.mapper.ReportMapper.deleteByStoreId", storeId);
    }
    
    @Override
    public List<Object> selectSumListByParam(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.ReportMapper.selectSumListByParam", map);
    }

    @Override
    public List<Object> selectStoreCateReportList(Map<String, Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.ReportMapper.selectStoreCateReportList", map);
    }

    @Override
    public void settleReport(Map<String,Object> map) {
        this.update("com.ningpai.mybatis.mapper.ReportMapper.settleReport", map);
    }
}
