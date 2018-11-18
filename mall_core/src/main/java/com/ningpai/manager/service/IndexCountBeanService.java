package com.ningpai.manager.service;

import java.util.Map;

import com.ningpai.manager.bean.IndexCountBean;

/**
 * 首页待办Service
 * 
 * @author ggn
 *
 */
public interface IndexCountBeanService {

    /**
     * 查询首页待办信息
     * 
     * @return IndexCountBean
     */
    IndexCountBean selectIndexCount();

    /**
     * 查询首页Echarts数据
     * 
     * @return EchartsBean
     */
    Map<String, Object> selectIndexEchartsData();
}
