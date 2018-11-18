/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.dataanalysis.dao;

import java.util.List;
import java.util.Map;


/**
 * 数据分析dao层接口
 * @author zhangsl
 * @since 2014年12月19日 下午1:42:56
 * @version
 */
public interface DataAnalysisMapper {
    /**
     * 查询数据分析所需要的所有信息
     * @param paraMap
     * @return
     */
    List<Object> selectAllData(Map<String, Object> paraMap);
    /**
     * 查询商铺总数
     * @param paraMap
     * @return
     */
    int selectAllSize(Map<String, Object> paraMap);
    
}
