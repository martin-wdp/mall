package com.ningpai.temp.service;

import java.util.List;

import com.ningpai.temp.bean.ClassifyBarCate;

/**
 * SERVICE-分类导航关联商品分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:26:49
 */
public interface ClassifyBarCateService {
    /**
     * 根据分类导航ID查询
     * 
     * @param classifybarId
     * @return
     */
    List<ClassifyBarCate> selectByClassifyBarId(Long classifybarId);
}
