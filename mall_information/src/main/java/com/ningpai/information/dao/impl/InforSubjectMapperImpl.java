/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.information.bean.InforSubject;
import com.ningpai.information.dao.InforSubjectMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-资讯专题
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年7月24日上午10:31:42
 */
@Repository("InforSubjectMapper")
public class InforSubjectMapperImpl extends BasicSqlSupport implements InforSubjectMapper {

    /**
     * 根据主键删除
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long subjectId) {

        return this.delete("com.ningpai.information.dao.InforSubjectMapper.deleteByPrimaryKey", subjectId);
    }

    /**
     * 添加
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#insert(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int insert(InforSubject record) {

        return this.insert("com.ningpai.information.dao.InforSubjectMapper.insert", record);
    }

    /**
     * 添加-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#insertSelective(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int insertSelective(InforSubject record) {

        return this.insert("com.ningpai.information.dao.InforSubjectMapper.insertSelective", record);
    }

    /**
     * 根据主键查询
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public InforSubject selectByPrimaryKey(Long subjectId) {

        return this.selectOne("com.ningpai.information.dao.InforSubjectMapper.selectByPrimaryKey", subjectId);
    }

    /**
     * 修改-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int updateByPrimaryKeySelective(InforSubject record) {

        return this.update("com.ningpai.information.dao.InforSubjectMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改-包括内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#updateByPrimaryKeyWithBLOBs(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(InforSubject record) {

        return this.update("com.ningpai.information.dao.InforSubjectMapper.updateByPrimaryKeyWithBLOBs", record);
    }

    /**
     * 修改-不包括内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#updateByPrimaryKey(com.ningpai.information.bean.InforSubject)
     */
    @Override
    public int updateByPrimaryKey(InforSubject record) {

        return this.update("com.ningpai.information.dao.InforSubjectMapper.updateByPrimaryKey", record);
    }

    /**
     * 查询总行数-分页用
     *
     * @param title
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectCountByTitle(java.lang.String)
     */
    @Override
    public int selectCountByTitle(String title) {

        return this.selectOne("com.ningpai.information.dao.InforSubjectMapper.selectCountByTitle", title);
    }

    /**
     * 根据分页参数查询
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectByPageBean(java.util.Map)
     */
    @Override
    public List<Object> selectByPageBean(Map<String, Object> map) {

        return this.selectList("com.ningpai.information.dao.InforSubjectMapper.selectByPageBean", map);
    }

    /**
     * 批量删除
     *
     * @param list
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#batchDeleteByList(java.util.List)
     */
    @Override
    public int batchDeleteByList(List<Long> list) {

        return this.update("com.ningpai.information.dao.InforSubjectMapper.batchDeleteByList", list);
    }

    /**
     * 查询所有专题
     *
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectAllForSite()
     */
    @Override
    public List<InforSubject> selectAllForSite() {
        return this.selectList("com.ningpai.information.dao.InforSubjectMapper.selectAllForSite");
    }

    /**
     * 根据url查询专题数量
     *
     * @param url
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectCountByUrl(java.lang.String)
     */
    @Override
    public int selectCountByUrl(String url) {
        return this.selectOne("com.ningpai.information.dao.InforSubjectMapper.selectCountByUrl", url);
    }

    /**
     * 根据ID查看启用的专题
     *
     * @param subjectId
     * @return
     * @see com.ningpai.information.dao.InforSubjectMapper#selectByIsShowAndId(java.lang.Long)
     */
    @Override
    public InforSubject selectByIsShowAndId(Long subjectId) {
        return this.selectOne("com.ningpai.information.dao.InforSubjectMapper.selectByIsShowAndId", subjectId);
    }
}
