/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.dataanalysis.dao.DataAnalysisMapper;
import com.ningpai.manager.base.BasicSqlSupport;
/**
 * 数据分dao层实现类
 * @author zhangsl
 * @since 2014年12月19日 下午1:44:59
 * @version
 */
@Repository("DataAnalysisMapper")
public class DataAnalysisMapperImpl extends BasicSqlSupport implements DataAnalysisMapper {
    
    /*
     * 查询数据分析所需要的所有信息
     * @see com.ningpai.dataanalysis.dao.DataAnalysisMapper#selectAllData(java.util.Map)
     */
    @Override
    public List<Object> selectAllData(Map<String, Object> paraMap) {
        
        return this.selectList("com.ningpai.dataanalysis.dao.DataAnalysisMapper.selectAllData", paraMap);
    }

    /*
     * 查询商铺总数
     * @see com.ningpai.dataanalysis.dao.DataAnalysisMapper#selectAllSize(java.util.Map)
     */
    @Override
    public int selectAllSize(Map<String, Object> paraMap) {
        
        return this.selectOne("com.ningpai.dataanalysis.dao.DataAnalysisMapper.selectAllSize",paraMap);
    }
    
    
    

}
