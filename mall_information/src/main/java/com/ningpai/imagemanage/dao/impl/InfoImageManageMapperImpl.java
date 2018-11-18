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

import com.ningpai.imagemanage.bean.InfoImageManage;
import com.ningpai.imagemanage.dao.InfoImageManageMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-资源图片信息
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月25日上午11:45:21
 */
@Repository("InfoImageManageMapper")
public class InfoImageManageMapperImpl extends BasicSqlSupport implements InfoImageManageMapper {

    /**
     * 根据主键删除
     * @param imageManageId
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long imageManageId) {

        return this.delete("com.ningpai.imagemanage.dao.InfoImageManageMapper.deleteByPrimaryKey", imageManageId);
    }

    /**
     * 添加
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#insert(com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int insert(InfoImageManage record) {

        return this.insert("com.ningpai.imagemanage.dao.InfoImageManageMapper.insert", record);
    }

    /**
     * 添加-字段可选
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#insertSelective(com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int insertSelective(InfoImageManage record) {

        return this.insert("com.ningpai.imagemanage.dao.InfoImageManageMapper.insertSelective", record);
    }

    /**
     * 修改-字段可选
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#updateByPrimaryKeySelective(com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int updateByPrimaryKeySelective(InfoImageManage record) {

        return this.update("com.ningpai.imagemanage.dao.InfoImageManageMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改
     * @param record
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#updateByPrimaryKey(com.ningpai.imagemanage.bean.InfoImageManage)
     */
    @Override
    public int updateByPrimaryKey(InfoImageManage record) {

        return this.update("com.ningpai.imagemanage.dao.InfoImageManageMapper.updateByPrimaryKey", record);
    }

    /**
     * 根据ID查询
     * @param imageManageId
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public InfoImageManage selectByPrimaryKey(Long imageManageId) {

        return this.selectOne("com.ningpai.imagemanage.dao.InfoImageManageMapper.selectByPrimaryKey", imageManageId);
    }

    /**
     * 根据图片分类查询图片信息总行数
     * @param map
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#selectImageManageCountByParam(java.util.Map)
     */
    @Override
    public Integer selectImageManageCountByParam(Map<String, Object> map) {

        return this.selectOne("com.ningpai.imagemanage.dao.InfoImageManageMapper.selectImageManageCountByParam", map);
    }

    /**
     * 根据图片分类查询图片信息
     * @param map
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#selectImageManageByParam(java.util.Map)
     */
    @Override
    public List<Object> selectImageManageByParam(Map<String, Object> map) {

        return this.selectList("com.ningpai.imagemanage.dao.InfoImageManageMapper.selectImageManageByParam", map);
    }

    /**
     * 根据地单方ID和图片编号删除
     * @param param
     * @return
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#updateImage
     */
    @Override
    public Integer updateImage(Map<String, Object> param) {
        return this.update("com.ningpai.imagemanage.dao.InfoImageManageMapper.updateImage", param);
    }

    /**
     * 批量删除
     * @param param
     * @see com.ningpai.imagemanage.dao.InfoImageManageMapper#updateImages
     */
    @Override
    public void updateImages(Map<String, Object> param) {
        this.update("com.ningpai.imagemanage.dao.InfoImageManageMapper.updateImages", param);
    }

}
