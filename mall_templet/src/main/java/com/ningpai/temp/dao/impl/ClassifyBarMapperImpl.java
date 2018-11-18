/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.temp.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.ClassifyBar;
import com.ningpai.temp.dao.ClassifyBarMapper;
import com.ningpai.temp.vo.ClassifyBarVo;

/**
 * DAO实现类-分类导航
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年5月6日下午4:06:03
 */
@Repository("ClassifyBarMapper")
public class ClassifyBarMapperImpl extends BasicSqlSupport implements ClassifyBarMapper {

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long classifyBarId) {

        return this.delete("com.ningpai.temp.dao.ClassifyBarMapper.deleteByPrimaryKey", classifyBarId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#insert(com.ningpai.temp.bean.ClassifyBar)
     */
    @Override
    public int insert(ClassifyBar record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#insertSelective(com.ningpai.temp.bean.ClassifyBar)
     */
    @Override
    public int insertSelective(ClassifyBar record) {

        return this.insert("com.ningpai.temp.dao.ClassifyBarMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#updateByPrimaryKeySelective(com.ningpai.temp.bean.ClassifyBar)
     */
    @Override
    public int updateByPrimaryKeySelective(ClassifyBar record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#updateByPrimaryKey(com.ningpai.temp.bean.ClassifyBar)
     */
    @Override
    public int updateByPrimaryKey(ClassifyBar record) {

        return this.update("com.ningpai.temp.dao.ClassifyBarMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ClassifyBar selectByPrimaryKey(Long classifyBarId) {

        return this.selectOne("com.ningpai.temp.dao.ClassifyBarMapper.selectByPrimaryKey", classifyBarId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectClassifyBarCountByParam(java.util.Map)
     */
    @Override
    public Integer selectClassifyBarCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.temp.dao.ClassifyBarMapper.selectClassifyBarCountByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectClassifyBarByParam(java.util.Map)
     */
    @Override
    public List<Object> selectClassifyBarByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.temp.dao.ClassifyBarMapper.selectClassifyBarByParam", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectClassifyBarByParamSite(java.util.Map)
     */
    @Override
    public List<ClassifyBarVo> selectClassifyBarByParamSite(Map<String, Object> map) {
        return this.selectList("com.ningpai.temp.dao.ClassifyBarMapper.selectClassifyBarByParamSite", map);
    }

    /*
     *
     *
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectClassifyBarByParamSite(java.util.Map)
     */
    @Override
    public List<ClassifyBarVo> selectClassifyBarByParamSite2(Map<String, Object> map) {
        return this.selectList("com.ningpai.temp.dao.ClassifyBarMapper.selectClassifyBarByParamSite2", map);
    }

    /*
     *
     *
     * @see com.ningpai.temp.dao.ClassifyBarMapper#selectClassifyBarByParamSite(java.util.Map)
     */
    @Override
    public List<ClassifyBarVo> getIndexClassificationByfir(Long parentID) {
        return this.selectList("com.ningpai.temp.dao.ClassifyBarMapper.selectByParentIdForSite2", parentID);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.ClassifyBarMapper#deleteByPrimaryKeyAndPro(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKeyAndPro(Long classifyBarId) {
        return this.delete("com.ningpai.temp.dao.ClassifyBarMapper.deleteByPrimaryKeyAndPro", classifyBarId);
    }

    /*
     * 删除店铺分类导航
     * @see com.ningpai.temp.dao.ClassifyBarMapper#deleteClassBarById(java.util.Map)
     */
    @Override
    public int deleteClassBarById(Map<String, Object> map) {
        return this.delete("com.ningpai.temp.dao.ClassifyBarMapper.deleteClassBarById", map);
    }
}
