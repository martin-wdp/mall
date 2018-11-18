/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.CurrencyBase;
import com.ningpai.system.dao.CurrencyBaseMapper;

/**
 * 货币基本信息DAO实现类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:30:25
 * @version 1.0
 */
@Repository("CurrencyBaseMapper")
public class CurrencyBaseMapperImpl extends BasicSqlSupport implements
        CurrencyBaseMapper {
    /**
     * 根据主键删除货币基本信息
     * 
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id) {

        return this.delete(
                "com.ningpai.system.dao.CurrencyBaseMapper.deleteByPrimaryKey",
                id);
    }

    /**
     * 添加货币基本信息
     * 
     * @param record
     * @return
     */
    public int insert(CurrencyBase record) {

        return this.insert("com.ningpai.system.dao.CurrencyBaseMapper.insert",
                record);
    }

    /**
     * 添加货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    public int insertSelective(CurrencyBase record) {

        return this.insert(
                "com.ningpai.system.dao.CurrencyBaseMapper.insertSelective",
                record);
    }

    /**
     * 更新货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CurrencyBase record) {

        return this
                .update("com.ningpai.system.dao.CurrencyBaseMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CurrencyBase record) {

        return this.update(
                "com.ningpai.system.dao.CurrencyBaseMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据主键查询货币基本信息
     * 
     * @param id
     * @return
     */
    public CurrencyBase selectByPrimaryKey(Long id) {

        return this.selectOne(
                "com.ningpai.system.dao.CurrencyBaseMapper.selectByPrimaryKey",
                id);
    }

    /**
     * 获取所有货币基本信息
     * 
     * @return
     */
    public List<CurrencyBase> selectAllCurrencyBase() {

        return this
                .selectList("com.ningpai.system.dao.CurrencyBaseMapper.selectAll");
    }

}
