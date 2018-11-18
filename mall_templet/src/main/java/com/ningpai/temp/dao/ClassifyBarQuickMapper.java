package com.ningpai.temp.dao;

import java.util.List;

import com.ningpai.temp.bean.ClassifyBarQuick;

/**
 * DAO-分类导航关联快捷分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:39:06
 */
public interface ClassifyBarQuickMapper {
    /**
     * 根据主键删除
     * 
     * @param classifybarQuickId
     * @return
     */
    int deleteByPrimaryKey(Long classifybarQuickId);

    /**
     * 根据分类导航ID批量删除
     * 
     * @param classifybarId
     *            分类导航ID
     * @return
     */
    int batchDeleteByClassifyBarId(Long classifybarId);

    /**
     * 添加
     * 
     * @param record
     * @return
     */
    int insert(ClassifyBarQuick record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ClassifyBarQuick record);

    /**
     * 更新-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ClassifyBarQuick record);

    /**
     * 更新
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ClassifyBarQuick record);

    /**
     * 根据主键查询
     * 
     * @param classifybarQuickId
     * @return
     */
    ClassifyBarQuick selectByPrimaryKey(Long classifybarQuickId);

    /**
     * 根据分类导航ID查询
     * 
     * @param classifybarQuickId
     * @return
     */
    List<ClassifyBarQuick> selectByClassifyBarId(Long classifybarQuickId);
}
