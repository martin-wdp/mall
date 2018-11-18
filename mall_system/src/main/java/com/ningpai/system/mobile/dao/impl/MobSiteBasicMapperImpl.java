/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.mobile.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.mobile.bean.MobSiteBasic;
import com.ningpai.system.mobile.dao.MobSiteBasicMapper;

/**
 * DAO实现类-移动版站点基础设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月19日下午2:36:23
 */
@Repository("MobSiteBasicMapper")
public class MobSiteBasicMapperImpl extends BasicSqlSupport implements MobSiteBasicMapper {

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long siteBasicId) {

        return this.delete("com.ningpai.system.mobile.dao.MobSiteBasicMapper.deleteByPrimaryKey", siteBasicId);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#insert(com.ningpai.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int insert(MobSiteBasic record) {

        return this.insert("com.ningpai.system.mobile.dao.MobSiteBasicMapper.insert", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#insertSelective(com.ningpai.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int insertSelective(MobSiteBasic record) {

        return this.insert("com.ningpai.system.mobile.dao.MobSiteBasicMapper.insertSelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobSiteBasic selectByPrimaryKey(Long siteBasicId) {

        return this.selectOne("com.ningpai.system.mobile.dao.MobSiteBasicMapper.selectByPrimaryKey", siteBasicId);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#updateByPrimaryKeySelective(com.ningpai.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int updateByPrimaryKeySelective(MobSiteBasic record) {

        return this.update("com.ningpai.system.mobile.dao.MobSiteBasicMapper.updateByPrimaryKeySelective", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#updateByPrimaryKey(com.ningpai.system.mobile.bean.MobSiteBasic)
     */
    @Override
    public int updateByPrimaryKey(MobSiteBasic record) {

        return this.update("com.ningpai.system.mobile.dao.MobSiteBasicMapper.updateByPrimaryKey", record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.mobile.dao.MobSiteBasicMapper#selectCurr()
     */
    @Override
    public MobSiteBasic selectCurr() {

        return this.selectOne("com.ningpai.system.mobile.dao.MobSiteBasicMapper.selectCurr");
    }

}
