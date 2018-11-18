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
import com.ningpai.mobile.bean.MobAdver;
import com.ningpai.mobile.dao.MobAdverMapper;

/**
 * DAO实现类-移动版广告
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年8月21日上午11:16:18
 */
@Repository("MobAdverMapper")
public class MobAdverMapperImpl extends BasicSqlSupport implements MobAdverMapper {

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long modAdverId) {

        return this.delete("com.ningpai.mobile.dao.MobAdverMapper.deleteByPrimaryKey", modAdverId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#batchDelMobAdver(java.util.List)
     */
    @Override
    public int batchDelMobAdver(List<Long> ids) {

        return this.delete("com.ningpai.mobile.dao.MobAdverMapper.batchDelMobAdver", ids);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#insert(com.ningpai.mobile.bean.MobAdver)
     */
    @Override
    public int insert(MobAdver record) {

        return this.insert("com.ningpai.mobile.dao.MobAdverMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#insertSelective(com.ningpai.mobile.bean.MobAdver)
     */
    @Override
    public int insertSelective(MobAdver record) {

        return this.insert("com.ningpai.mobile.dao.MobAdverMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#updateByPrimaryKeySelective(com.ningpai.mobile.bean.MobAdver)
     */
    @Override
    public int updateByPrimaryKeySelective(MobAdver record) {

        return this.update("com.ningpai.mobile.dao.MobAdverMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#updateByPrimaryKey(com.ningpai.mobile.bean.MobAdver)
     */
    @Override
    public int updateByPrimaryKey(MobAdver record) {

        return this.update("com.ningpai.mobile.dao.MobAdverMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobAdver selectByPrimaryKey(Long modAdverId) {

        return this.selectOne("com.ningpai.mobile.dao.MobAdverMapper.selectByPrimaryKey", modAdverId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#selectCountByStoreyId(java.lang.Long)
     */
    @Override
    public int selectCountByStoreyId(Long storeyId) {

        return this.selectOne("com.ningpai.mobile.dao.MobAdverMapper.selectCountByStoreyId", storeyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#selectByStoreyIdAndPb(java.util.Map)
     */
    @Override
    public List<Object> selectByStoreyIdAndPb(Map<String, Object> map) {

        return this.selectList("com.ningpai.mobile.dao.MobAdverMapper.selectByStoreyIdAndPb", map);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#selectByStoreyIdForSite(java.lang.Long)
     */
    @Override
    public List<MobAdver> selectByStoreyIdForSite(Long storeyId) {

        return this.selectList("com.ningpai.mobile.dao.MobAdverMapper.selectByStoreyIdForSite", storeyId);
    }

    /*
     * 
     * 
     * @see com.ningpai.mobile.dao.MobAdverMapper#deleteByStoreyId(java.lang.Long)
     */
    @Override
    public int deleteByStoreyId(Long storeyId) {
        return this.delete("com.ningpai.mobile.dao.MobAdverMapper.deleteByStoreyId", storeyId);
    }
}
