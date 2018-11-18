/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.information.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.information.bean.InformationOnePage;
import com.ningpai.information.dao.InformationOnePageMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * 资讯单页DAO实现
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年02月24日 17:05:10
 * @version
 */
@Repository("InformationOnePageMapper")
public class InformationOnePageMapperImpl extends BasicSqlSupport implements InformationOnePageMapper {
    /**
     * 根据主键删除
     *
     * @param infoOPId
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#deleteByPrimaryKey(java.lang.Long)
     */
    public int deleteByPrimaryKey(Long infoOPId) {

        return this.delete("com.ningpai.information.dao.InformationOnePageMapper.deleteByPrimaryKey", infoOPId);
    }
    /**
     * 添加资讯单页
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#insert(com.ningpai.information.bean.InformationOnePage)
     */
    public int insert(InformationOnePage record) {

        return this.insert("com.ningpai.information.dao.InformationOnePageMapper.insert", record);
    }
    /**
     * 添加资讯单页-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#insertSelective(com.ningpai.information.bean.InformationOnePage)
     */
    public int insertSelective(InformationOnePage record) {

        return this.insert("com.ningpai.information.dao.InformationOnePageMapper.insertSelective", record);
    }
    /**
     * 更新资讯单页-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InformationOnePage)
     */
    public int updateByPrimaryKeySelective(InformationOnePage record) {

        return this.update("com.ningpai.information.dao.InformationOnePageMapper.updateByPrimaryKeySelective", record);
    }
    /**
     * 更新资讯单页-包含内容
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#updateByPrimaryKeyWithBLOBs(com.ningpai.information.bean.InformationOnePage)
     */
    public int updateByPrimaryKeyWithBLOBs(InformationOnePage record) {

        return this.update("com.ningpai.information.dao.InformationOnePageMapper.updateByPrimaryKeyWithBLOBs", record);
    }
    /**
     * 更新资讯单页
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#updateByPrimaryKey(com.ningpai.information.bean.InformationOnePage)
     */
    public int updateByPrimaryKey(InformationOnePage record) {

        return this.update("com.ningpai.information.dao.InformationOnePageMapper.updateByPrimaryKey", record);
    }
    /**
     * 根据主键查询
     *
     * @param infoOPId
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#selectByPrimaryKey(java.lang.Long)
     */
    public InformationOnePage selectByPrimaryKey(Long infoOPId) {

        return this.selectOne("com.ningpai.information.dao.InformationOnePageMapper.selectByPrimaryKey", infoOPId);
    }

    /**
     * 根据map参数查询所有行数
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#queryTotalCount(java.util.Map)
     */
    public Integer queryTotalCount(Map<String, Object> map) {

        return this.selectOne("com.ningpai.information.dao.InformationOnePageMapper.queryTotalCount", map);
    }
    /**
     * 根据分页参数查询资讯单页列表
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#queryByPageBean(java.util.Map)
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {

        return this.selectList("com.ningpai.information.dao.InformationOnePageMapper.queryByPageBean", map);
    }

    /**
     * 根据单页标题查询单页数量
     *
     * @param title
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#selectInfoOPCountByTitle(java.lang.String)
     */
    @Override
    public Integer selectInfoOPCountByTitle(String title) {
        return this.selectOne("com.ningpai.information.dao.InformationOnePageMapper.selectInfoOPCountByTitle", title);
    }

    /**
     * 根据标签查询单页
     *
     * @param map
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#selectByTempTag(java.util.Map)
     */
    @Override
    public List<InformationOnePage> selectByTempTag(Map<String, Object> map) {
        return this.selectList("com.ningpai.information.dao.InformationOnePageMapper.selectByTempTag", map);
    }

    /**
     * 根据单页标签查询单页数量，判断是否可删除标签
     *
     * @param infoOPTagId
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#selectInfoOPCountByTag(java.lang.Long)
     */
    @Override
    public Integer selectInfoOPCountByTag(Long infoOPTagId) {
        return this.selectOne("com.ningpai.information.dao.InformationOnePageMapper.selectInfoOPCountByTag", infoOPTagId);
    }

    /**
     * 删除单页标签时，根据单页标签修改单页
     *
     * @param infoOPTagId
     * @return
     * @see com.ningpai.information.dao.InformationOnePageMapper#updateTagByTagId(java.lang.Long)
     */
    @Override
    public int updateTagByTagId(Long infoOPTagId) {
        return this.update("com.ningpai.information.dao.InformationOnePageMapper.updateTagByTagId", infoOPTagId);
    }
}
