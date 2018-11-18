/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.imagemanage.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.imagemanage.bean.InfoImageClassify;
import com.ningpai.imagemanage.dao.InfoImageClassifyMapper;
import com.ningpai.imagemanage.vo.InfoImageClassifyVo;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-资源图片类型
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:39:38
 */
@Repository("InfoImageClassifyMapper")
public class InfoImageClassifyMapperImpl extends BasicSqlSupport implements InfoImageClassifyMapper {

    /**
     * 根据ID删除
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long classifyId) {

        return this.delete("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.deleteByPrimaryKey", classifyId);
    }

    /**
     * 添加
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#insert(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int insert(InfoImageClassify record) {

        return this.insert("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.insert", record);
    }

    /**
     * 添加-字段可选
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#insertSelective(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int insertSelective(InfoImageClassify record) {

        return this.insert("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.insertSelective", record);
    }

    /**
     * 修改-字段可选
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#updateByPrimaryKeySelective(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int updateByPrimaryKeySelective(InfoImageClassify record) {

        return this.update("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#updateByPrimaryKey(com.ningpai.imagemanage.bean.InfoImageClassify)
     */
    @Override
    public int updateByPrimaryKey(InfoImageClassify record) {

        return this.update("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.updateByPrimaryKey", record);
    }

    /**
     * 根据主键查询
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public InfoImageClassify selectByPrimaryKey(Long classifyId) {

        return this.selectOne("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectByPrimaryKey", classifyId);
    }

    /**
     * 查询图片管理分类总行数
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectImageClassifyCountByParam()
     */
    @Override
    public Integer selectImageClassifyCountByParam() {

        return this.selectOne("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectImageClassifyCountByParam");
    }

    /**
     * 查询图片管理分类
     * @param map
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectImageClassifyByParam(java.util.Map)
     */
    @Override
    public List<Object> selectImageClassifyByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectImageClassifyByParam", map);
    }

    /**
     * 查询所有图片管理分类<br/>
     * 分类管理选择父分类
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectAllImageClassify()
     */
    @Override
    public List<InfoImageClassify> selectAllImageClassify() {
        return this.selectList("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectAllImageClassify");
    }

    /**
     * 根据分类ID查询子分类
     * @param classifyId
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectByParentId(java.lang.Long)
     */
    @Override
    public List<InfoImageClassifyVo> selectByParentId(Long classifyId) {
        return this.selectList("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectByParentId", classifyId);
    }

    /**
     * 查询所有图片管理分类<br/>
     * 上传图片选择分类用
     * 
     * @see com.ningpai.imagemanage.dao.InfoImageClassifyMapper#selectAllImageClassifyForImg()
     */
    @Override
    public List<InfoImageClassify> selectAllImageClassifyForImg() {
        return this.selectList("com.ningpai.imagemanage.dao.InfoImageClassifyMapper.selectAllImageClassifyForImg");
    }
}
