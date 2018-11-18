/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.mobile.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobStorey;
import com.ningpai.mobile.dao.MobStoreyMapper;

/**
 * DAO实现类-移动版楼层
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日下午8:04:42
 */
@Repository("MobStoreyMapper")
public class MobStoreyMapperImpl extends BasicSqlSupport implements MobStoreyMapper {

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long mobStoreyId) {

        return this.delete("com.ningpai.mobile.dao.MobStoreyMapper.deleteByPrimaryKey", mobStoreyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#insert(com.ningpai.mobile.bean.MobStorey)
     */
    @Override
    public int insert(MobStorey record) {

        return this.insert("com.ningpai.mobile.dao.MobStoreyMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#insertSelective(com.ningpai.mobile.bean.MobStorey)
     */
    @Override
    public int insertSelective(MobStorey record) {

        return this.insert("com.ningpai.mobile.dao.MobStoreyMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#updateByPrimaryKeySelective(com.ningpai.mobile.bean.MobStorey)
     */
    @Override
    public int updateByPrimaryKeySelective(MobStorey record) {

        return this.update("com.ningpai.mobile.dao.MobStoreyMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#updateByPrimaryKey(com.ningpai.mobile.bean.MobStorey)
     */
    @Override
    public int updateByPrimaryKey(MobStorey record) {

        return this.update("com.ningpai.mobile.dao.MobStoreyMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobStorey selectByPrimaryKey(Long mobStoreyId) {

        return this.selectOne("com.ningpai.mobile.dao.MobStoreyMapper.selectByPrimaryKey", mobStoreyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#selectCount()
     */
    @Override
    public int selectCount() {

        return this.selectOne("com.ningpai.mobile.dao.MobStoreyMapper.selectCount");
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#selectByPb(java.util.Map)
     */
    @Override
    public List<Object> selectByPb(Map<String, Object> map) {

        return this.selectList("com.ningpai.mobile.dao.MobStoreyMapper.selectByPb", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobStoreyMapper#selectAllForSite()
     */
    @Override
    public List<MobStorey> selectAllForSite() {

        return this.selectList("com.ningpai.mobile.dao.MobStoreyMapper.selectAllForSite");
    }

}
