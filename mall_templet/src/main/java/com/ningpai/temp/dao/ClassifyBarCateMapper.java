package com.ningpai.temp.dao;

import java.util.List;

import com.ningpai.temp.bean.ClassifyBarCate;

/**
 * DAO-分类导航关联商品分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:26:49
 */
public interface ClassifyBarCateMapper {
    /**
     * 根据主键删除
     * 
     * @param classifybarCateId
     * @return
     */
    int deleteByPrimaryKey(Long classifybarCateId);

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
    int insert(ClassifyBarCate record);

    /**
     * 添加-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(ClassifyBarCate record);

    /**
     * 修改-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ClassifyBarCate record);

    /**
     * 修改
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(ClassifyBarCate record);

    /**
     * 根据主键查询
     * 
     * @param classifybarCateId
     * @return
     */
    ClassifyBarCate selectByPrimaryKey(Long classifybarCateId);

    /**
     * 根据分类导航ID查询
     * 
     * @param classifybarId
     * @return
     */
    List<ClassifyBarCate> selectByClassifyBarId(Long classifybarId);
}
