/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.mobile.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.mobile.bean.MobSinglepageMark;
import com.ningpai.mobile.dao.MobSinglepageMarkMapper;

/**
 * 移动版单页标签dao层实现类
 * 
 * @author zhangsl
 * @since 2014年11月24日 上午10:52:58
 * @version 0.0.1
 */
@Repository("MobSinglepageMarkMapper")
public class MobSinglepageMarkMapperImpl extends BasicSqlSupport implements MobSinglepageMarkMapper {

    /**
     * 根据主键ID删除移动单页标签信息
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#deleteByPrimaryKey(java.lang.Long)
     */
    @Override
    public int deleteByPrimaryKey(Long markId) {

        return this.delete("com.ningpai.mobile.dao.MobSinglepageMarkMapper.deleteByPrimaryKey", markId);
    }

    /**
     * 添加移动单页标签信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#insert(com.ningpai.mobile.bean.MobSinglepageMark)
     */
    @Override
    public int insert(MobSinglepageMark record) {

        return this.insert("com.ningpai.mobile.dao.MobSinglepageMarkMapper.insert", record);
    }

    /**
     * 有选择性添加移动单页标签信息
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#insertSelective(com.ningpai.mobile.bean.MobSinglepageMark)
     */
    @Override
    public int insertSelective(MobSinglepageMark record) {

        return this.insert("com.ningpai.mobile.dao.MobSinglepageMarkMapper.insertSelective", record);
    }

    /**
     * 根据主键查询移动单页标签
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public MobSinglepageMark selectByPrimaryKey(Long markId) {

        return this.selectOne("com.ningpai.mobile.dao.MobSinglepageMarkMapper.selectByPrimaryKey", markId);
    }

    /**
     * 更新移动单页标签
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#updateByPrimaryKeySelective(com.ningpai.mobile.bean.MobSinglepageMark)
     */
    @Override
    public int updateByPrimaryKeySelective(MobSinglepageMark record) {

        return this.update("com.ningpai.mobile.dao.MobSinglepageMarkMapper.updateByPrimaryKeySelective", record);
    }

    /**
     * 根据主键ID更新移动单页标签
     *
     * @param record
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#updateByPrimaryKey(com.ningpai.mobile.bean.MobSinglepageMark)
     */
    @Override
    public int updateByPrimaryKey(MobSinglepageMark record) {

        return this.update("com.ningpai.mobile.dao.MobSinglepageMarkMapper.updateByPrimaryKey", record);
    }

    /**
     * 查询所有移动单页标签列表
     *
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#selectAllMarkInfo()
     */
    @Override
    public List<MobSinglepageMark> selectAllMarkInfo() {
        return this.selectList("com.ningpai.mobile.dao.MobSinglepageMarkMapper.selectAllMarkInfo");
    }

    /**
     * 根据主键ID逻辑删除移动单页标签数据 修改delflag的状态 0：未删除 1：已删除
     *
     * @param markId
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#updateDelStatus(java.lang.Long)
     */
    @Override
    public int updateDelStatus(Long markId) {

        return this.update("com.ningpai.mobile.dao.MobSinglepageMarkMapper.updateDelStatus", markId);
    }

    /**
     * 查询删除状态为0即不删除的移动单页标签列表
     *
     * @return
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#queryAllMarkInfoByDel()
     */
    @Override
    public List<MobSinglepageMark> queryAllMarkInfoByDel() {

        return this.selectList("com.ningpai.mobile.dao.MobSinglepageMarkMapper.queryAllMarkInfoByDel");
    }

    /**
     * 验证name是否存在
     *
     * @param name
     * @return 0:不存在 1：存在
     * @see com.ningpai.mobile.dao.MobSinglepageMarkMapper#checkNameExist(java.lang.String)
     */
    @Override
    public int checkNameExist(String name) {

        return this.selectOne("com.ningpai.mobile.dao.MobSinglepageMarkMapper.checkNameExist", name);
    }

}
