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

import com.ningpai.information.bean.InfoOPTag;
import com.ningpai.information.dao.InfoOPTagMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * DAO实现类-单页标签
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年4月10日下午1:32:02
 */
@Repository("InfoOPTagMapper")
public class InfoOPTagMapperImpl extends BasicSqlSupport implements InfoOPTagMapper {

    /**
     * 根据主键删除
     *
     * @param infoopTagId
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long infoopTagId) {

        return this.delete("com.ningpai.information.dao.InfoOPTagMapper.deleteByPrimaryKey", infoopTagId);
    }

    /**
     * 添加
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#insert(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int insert(InfoOPTag record) {

        return this.insert("com.ningpai.information.dao.InfoOPTagMapper.insert", record);
    }

    /**
     * 添加-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#insertSelective(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int insertSelective(InfoOPTag record) {

        return this.insert("com.ningpai.information.dao.InfoOPTagMapper.insertSelective", record);
    }

    /**
     * 修改-字段可选
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int updateByPrimaryKeySelective(InfoOPTag record) {

        return this.update("com.ningpai.information.dao.InfoOPTagMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#updateByPrimaryKey(com.ningpai.information.bean.InfoOPTag)
     */
    @Override
    public int updateByPrimaryKey(InfoOPTag record) {

        return this.update("com.ningpai.information.dao.InfoOPTagMapper.updateByPrimaryKey", record);
    }

    /**
     * 根据主键查询
     *
     * @param infoopTagId
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public InfoOPTag selectByPrimaryKey(Long infoopTagId) {

        return this.selectOne("com.ningpai.information.dao.InfoOPTagMapper.selectByPrimaryKey", infoopTagId);
    }

    /**
     * 根据模板ID查询
     *
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#selectAll(java.lang.String)
     */
    @Override
    public List<InfoOPTag> selectAll(String tempId) {

        return this.selectList("com.ningpai.information.dao.InfoOPTagMapper.selectAll", tempId);
    }

    /**
     * 查询所有
     *
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#findAll()
     */
    @Override
    public List<InfoOPTag> findAll() {
        return this.selectList("com.ningpai.information.dao.InfoOPTagMapper.findAll");
    }
    /**
     * 分页查询所有
     *
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#findAllPage(java.util.Map)
     */
    @Override
    public List<Object> findAllPage(Map<String, Object> map) {
        return this.selectList("com.ningpai.information.dao.InfoOPTagMapper.selectpage",map);
    }
    /**
     * 查询总条数
     *
     * @return
     * @see com.ningpai.information.dao.InfoOPTagMapper#findAllPagecount()
     */
    @Override
    public int findAllPagecount() {
        return this.selectOne("com.ningpai.information.dao.InfoOPTagMapper.selectpagecount");
    }
}
