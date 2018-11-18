/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.dao.CurrencyInfoMapper;
import com.ningpai.system.vo.CurrencyInfoVo;

/**
 * 货币信息DAO实现类
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:30:25
 * @version 1.0
 */
@Repository("CurrencyInfoMapper")
public class CurrencyInfoMapperImpl extends BasicSqlSupport implements
        CurrencyInfoMapper {
    /**
     * 根据主键删除货币信息
     * 
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(Long id) {

        return this.delete(
                "com.ningpai.system.dao.CurrencyInfoMapper.deleteByPrimaryKey",
                id);
    }

    /**
     * 添加货币信息
     * 
     * @param record
     * @return
     */
    public int insert(CurrencyInfo record) {

        return this.insert("com.ningpai.system.dao.CurrencyInfoMapper.insert",
                record);
    }

    /**
     * 添加货币信息-字段可选
     * 
     * @param record
     * @return
     */
    public int insertSelective(CurrencyInfo record) {

        return this.insert(
                "com.ningpai.system.dao.CurrencyInfoMapper.insertSelective",
                record);
    }

    /**
     * 更新货币信息-字段可选
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(CurrencyInfo record) {

        return this
                .update("com.ningpai.system.dao.CurrencyInfoMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 更新货币信息
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(CurrencyInfo record) {

        return this.update(
                "com.ningpai.system.dao.CurrencyInfoMapper.updateByPrimaryKey",
                record);
    }

    /**
     * 根据主键查询货币信息
     * 
     * @param id
     * @return
     */
    public CurrencyInfoVo selectByPrimaryKey(Long id) {

        return this.selectOne(
                "com.ningpai.system.dao.CurrencyInfoMapper.selectByPrimaryKey",
                id);
    }

    /**
     * 查询所有货币信息的行数
     * 
     * @return
     */
    public Integer queryTotalCount() {

        return this
                .selectOne("com.ningpai.system.dao.CurrencyInfoMapper.queryTotalCount");
    }

    /**
     * 根据分页参数查询货币信息集合
     * 
     * @param map
     * @return
     */
    public List<Object> queryByPageBean(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.CurrencyInfoMapper.queryByPageBean",
                map);
    }

}
