/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.ClassifyBarQuick;
import com.ningpai.temp.dao.ClassifyBarQuickMapper;

/**
 * DAO实现类-分类导航关联快捷分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:41:54
 */
@Repository("ClassifyBarQuickMapper")
public class ClassifyBarQuickMapperImpl extends BasicSqlSupport implements ClassifyBarQuickMapper {

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long classifybarQuickId) {

        return this.delete("com.ningpai.temp.dao.ClassifyBarQuickMapper.deleteByPrimaryKey", classifybarQuickId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#batchDeleteByClassifyBarId(java.lang.Long)
     */
    @Override
    public int batchDeleteByClassifyBarId(Long classifybarId) {
        return this.delete("com.ningpai.temp.dao.ClassifyBarQuickMapper.batchDeleteByClassifyBarId", classifybarId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#insert(com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int insert(ClassifyBarQuick record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarQuickMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#insertSelective(com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int insertSelective(ClassifyBarQuick record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarQuickMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#updateByPrimaryKeySelective(com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int updateByPrimaryKeySelective(ClassifyBarQuick record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarQuickMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#updateByPrimaryKey(com.ningpai.temp.bean.ClassifyBarQuick)
     */
    @Override
    public int updateByPrimaryKey(ClassifyBarQuick record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarQuickMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ClassifyBarQuick selectByPrimaryKey(Long classifybarQuickId) {

        return this.selectOne("com.ningpai.temp.dao.ClassifyBarQuickMapper.selectByPrimaryKey", classifybarQuickId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarQuickMapper#selectByClassifyBarId(java.lang.Long)
     */
    @Override
    public List<ClassifyBarQuick> selectByClassifyBarId(Long classifybarQuickId) {

        return this.selectList("com.ningpai.temp.dao.ClassifyBarQuickMapper.selectByClassifyBarId", classifybarQuickId);
    }

}
