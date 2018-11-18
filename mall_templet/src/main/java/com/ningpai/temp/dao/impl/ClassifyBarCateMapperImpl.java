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
import com.ningpai.temp.bean.ClassifyBarCate;
import com.ningpai.temp.dao.ClassifyBarCateMapper;

/**
 * DAO实现类-分类导航关联商品分类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月3日上午10:30:28
 */
@Repository("ClassifyBarCateMapper")
public class ClassifyBarCateMapperImpl extends BasicSqlSupport implements ClassifyBarCateMapper {

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long classifybarCateId) {

        return this.delete("com.ningpai.temp.dao.ClassifyBarCateMapper.deleteByPrimaryKey", classifybarCateId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#batchDeleteByClassifyBarId(java.lang.Long)
     */
    @Override
    public int batchDeleteByClassifyBarId(Long classifyBarId) {
        return this.delete("com.ningpai.temp.dao.ClassifyBarCateMapper.batchDeleteByClassifyBarId", classifyBarId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#insert(com.ningpai.temp.bean.ClassifyBarCate)
     */
    @Override
    public int insert(ClassifyBarCate record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarCateMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#insertSelective(com.ningpai.temp.bean.ClassifyBarCate)
     */
    @Override
    public int insertSelective(ClassifyBarCate record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarCateMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#updateByPrimaryKeySelective(com.ningpai.temp.bean.ClassifyBarCate)
     */
    @Override
    public int updateByPrimaryKeySelective(ClassifyBarCate record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarCateMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#updateByPrimaryKey(com.ningpai.temp.bean.ClassifyBarCate)
     */
    @Override
    public int updateByPrimaryKey(ClassifyBarCate record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarCateMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ClassifyBarCate selectByPrimaryKey(Long classifybarCateId) {

        return this.selectOne("com.ningpai.temp.dao.ClassifyBarCateMapper.selectByPrimaryKey", classifybarCateId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarCateMapper#selectByClassifyBarId(java.lang.Long)
     */
    @Override
    public List<ClassifyBarCate> selectByClassifyBarId(Long classifybarId) {

        return this.selectList("com.ningpai.temp.dao.ClassifyBarCateMapper.selectByClassifyBarId", classifybarId);
    }

}
