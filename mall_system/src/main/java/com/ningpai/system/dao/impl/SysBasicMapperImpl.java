/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
/**
 * 
 */
package com.ningpai.system.dao.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.SysBasic;
import com.ningpai.system.dao.SysBasicMapper;

/**
 * DAO实现类-后台Logo设置
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年9月24日下午8:36:58
 */
@Repository("SysBasicMapper")
public class SysBasicMapperImpl extends BasicSqlSupport implements
        SysBasicMapper {

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long basicId) {

        return this.delete(
                "com.ningpai.system.dao.SysBasicMapper.deleteByPrimaryKey",
                basicId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#insert(com.ningpai.system.bean.
     * SysBasic)
     */
    @Override
    public int insert(SysBasic record) {

        return this.insert("com.ningpai.system.dao.SysBasicMapper.insert",
                record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#insertSelective(com.ningpai.system
     * .bean.SysBasic)
     */
    @Override
    public int insertSelective(SysBasic record) {

        return this
                .insert("com.ningpai.system.dao.SysBasicMapper.insertSelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public SysBasic selectByPrimaryKey(Long basicId) {

        return this.selectOne(
                "com.ningpai.system.dao.SysBasicMapper.selectByPrimaryKey",
                basicId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#updateByPrimaryKeySelective(com
     * .ningpai.system.bean.SysBasic)
     */
    @Override
    public int updateByPrimaryKeySelective(SysBasic record) {

        return this
                .update("com.ningpai.system.dao.SysBasicMapper.updateByPrimaryKeySelective",
                        record);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.SysBasicMapper#updateByPrimaryKey(com.ningpai.
     * system.bean.SysBasic)
     */
    @Override
    public int updateByPrimaryKey(SysBasic record) {

        return this.update(
                "com.ningpai.system.dao.SysBasicMapper.updateByPrimaryKey",
                record);
    }

    /*
     * 
     * 
     * @see com.ningpai.system.dao.SysBasicMapper#selectCurr()
     */
    @Override
    public SysBasic selectCurr() {

        return this
                .selectOne("com.ningpai.system.dao.SysBasicMapper.selectCurr");
    }



}
