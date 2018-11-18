package com.ningpai.temp.service;

import java.util.List;

import com.ningpai.temp.bean.ClassifyBarQuick;

/**
 * SERVICE-分类导航关联快捷分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:39:06
 */
public interface ClassifyBarQuickService {
    /**
     * 根据分类导航ID查询
     * 
     * @param classifybarQuickId
     * @return
     */
    List<ClassifyBarQuick> selectByClassifyBarId(Long classifybarQuickId);
}
