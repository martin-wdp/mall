/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.information.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.information.bean.InfoUserDefined;
import com.ningpai.information.dao.InfoUserDefinedMapper;
import com.ningpai.manager.base.BasicSqlSupport;

/**
 * @author NINGPAI-WangHaiYang
 * @since 2014年3月26日下午5:03:38
 */
@Repository("InfoUserDefinedMapper")
public class InfoUserDefinedMapperImpl extends BasicSqlSupport implements InfoUserDefinedMapper {

    /**
     * 根据主键删除
     *
     * @param infoUdId
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long infoUdId) {

        return this.delete("com.ningpai.information.dao.InfoUserDefinedMapper.deleteByPrimaryKey", infoUdId);
    }

    /**
     * 添加
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#insert(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int insert(InfoUserDefined record) {

        return this.insert("com.ningpai.information.dao.InfoUserDefinedMapper.insert", record);
    }

    /**
     * 添加-字段可为null
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#insertSelective(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int insertSelective(InfoUserDefined record) {

        return this.insert("com.ningpai.information.dao.InfoUserDefinedMapper.insertSelective", record);
    }

    /**
     * 修改-字段可为null
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#updateByPrimaryKeySelective(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int updateByPrimaryKeySelective(InfoUserDefined record) {

        return this.update("com.ningpai.information.dao.InfoUserDefinedMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#updateByPrimaryKey(com.ningpai.information.bean.InfoUserDefined)
     */
    @Override
    public int updateByPrimaryKey(InfoUserDefined record) {

        return this.update("com.ningpai.information.dao.InfoUserDefinedMapper.updateByPrimaryKey", record);
    }

    /**
     * 根据主键查询
     *
     * @param infoUdId
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public InfoUserDefined selectByPrimaryKey(Long infoUdId) {

        return this.selectOne("com.ningpai.information.dao.InfoUserDefinedMapper.selectByPrimaryKey", infoUdId);
    }

    /**
     * 查询所有
     *
     * @return
     * @see com.ningpai.information.dao.InfoUserDefinedMapper#selectAll()
     */
    @Override
    public List<InfoUserDefined> selectAll() {

        return this.selectList("com.ningpai.information.dao.InfoUserDefinedMapper.selectAll");
    }

}
