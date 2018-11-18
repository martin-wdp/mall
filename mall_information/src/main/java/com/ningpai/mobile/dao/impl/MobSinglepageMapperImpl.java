/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobSinglepage;
import com.ningpai.mobile.dao.MobSinglepageMapper;

/**
 * 移动版单页dao层实现类
 * 
 * @author zhangsl
 * @since 2014年11月21日 下午5:52:16
 * @version 0.0.1
 */
@Repository("MobSinglepageMapper")
public class MobSinglepageMapperImpl extends BasicSqlSupport implements MobSinglepageMapper {

    /**
     * 根据主键Id删除移动版单页信息
     *
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long singlepageId) {

        return this.delete("com.ningpai.mobile.mapper.MobSinglepageMapper.deleteByPrimaryKey", singlepageId);
    }

    /**
     * 添加移动版单页信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#insert(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int insert(MobSinglepage record) {

        return this.insert("com.ningpai.mobile.mapper.MobSinglepageMapper.insert", record);
    }

    /**
     * 添加移动版单页信息(可选择性的添加)
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#insertSelective(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int insertSelective(MobSinglepage record) {
        return this.insert("com.ningpai.mobile.mapper.MobSinglepageMapper.insertSelective", record);
    }

    /**
     * 根据singlepageId查询移动版单页信息
     *
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobSinglepage selectByPrimaryKey(Long singlepageId) {

        return this.selectOne("com.ningpai.mobile.mapper.MobSinglepageMapper.selectByPrimaryKey", singlepageId);
    }

    /**
     * 根据singlepageId修改移动版单页信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#updateByPrimaryKeySelective(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int updateByPrimaryKeySelective(MobSinglepage record) {

        return this.update("com.ningpai.mobile.mapper.MobSinglepageMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 修改移动版单页信息(包括修改数据类型为longtext的content字段)
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#updateByPrimaryKeyWithBLOBs(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int updateByPrimaryKeyWithBLOBs(MobSinglepage record) {

        return this.update("com.ningpai.mobile.mapper.MobSinglepageMapper.updateByPrimaryKeyWithBLOBs", record);
    }

    /**
     * 根据主键ID修改移动版单页信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#updateByPrimaryKey(com.ningpai.mobile.bean.MobSinglepage)
     */
    @Override
    public int updateByPrimaryKey(MobSinglepage record) {

        return this.update("com.ningpai.mobile.mapper.MobSinglepageMapper.updateByPrimaryKey", record);
    }

    /**
     * 分页查询移动版单页信息
     *
     * @param paraMap
     *            分页参数
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#queryMobByPage(java.util.Map)
     */
    @Override
    public List<Object> queryMobByPage(Map<Object, Object> paraMap) {
        return this.selectList("com.ningpai.mobile.mapper.MobSinglepageMapper.queryMobByPage", paraMap);
    }

    /**
     * 查询移动单页列表的总条数
     *
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#queryMobAllCount(java.util.Map)
     */
    @Override
    public int queryMobAllCount(Map<Object, Object> paraMap) {
        return this.selectOne("com.ningpai.mobile.mapper.MobSinglepageMapper.queryMobAllCount", paraMap);

    }

    /**
     * 逻辑删除 根据singlepageId修改delflag状态 0:未删除 1：已删除
     *
     * @param singlepageId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#updatedelstatus(java.lang.Long)
     */
    @Override
    public int updatedelstatus(Long singlepageId) {

        return this.update("com.ningpai.mobile.mapper.MobSinglepageMapper.updatedelstatus", singlepageId);
    }

    /**
     * 根据MarkId查询符合条件的总条数
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#queryCountByMarkId(java.lang.Long)
     */
    @Override
    public int queryCountByMarkId(Long markId) {

        return this.selectOne("com.ningpai.mobile.mapper.MobSinglepageMapper.queryCountByMarkId", markId);
    }

    /**
     * 移动版单页与移动版单页标签左连接查询 分页查询
     *
     * @param paraMap
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#newQueryMobByPage(java.util.Map)
     */
    @Override
    public List<Object> newQueryMobByPage(Map<Object, Object> paraMap) {

        return this.selectList("com.ningpai.mobile.mapper.MobSinglepageMapper.newQueryMobByPage", paraMap);
    }
    /**
     * 新查询总条数 移动版单页与移动版单页标签左连接 条件查询总记录数
     *
     * @param paraMap
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMapper#newQueryMobAllCount(java.util.Map)
     */
    @Override
    public int newQueryMobAllCount(Map<Object, Object> paraMap) {

        return this.selectOne("com.ningpai.mobile.mapper.MobSinglepageMapper.newQueryMobAllCount", paraMap);
    }

}
