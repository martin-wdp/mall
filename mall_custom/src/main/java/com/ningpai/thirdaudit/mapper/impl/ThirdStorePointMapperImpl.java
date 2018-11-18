/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.thirdaudit.mapper.impl;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdaudit.bean.ThirdStorePoint;
import com.ningpai.thirdaudit.mapper.ThirdStorePointMapper;
/**
 * 商家信息接口实现类
 *
 * */
@Repository("ThirdStorePointMapper")
public class ThirdStorePointMapperImpl extends BasicSqlSupport implements
        ThirdStorePointMapper {
    /**
     * 根据主键删除
     *
     * @param storePointId
     * */
    @Override
    public int deleteByPrimaryKey(Long storePointId) {

        return 0;
    }
    /**
     * 插入一条记录
     *
     * @param record
     * */
    @Override
    public int insert(ThirdStorePoint record) {

        return 0;
    }
    /**
     * 插入记录
     *
     * @param record
     * */
    @Override
    public int insertSelective(ThirdStorePoint record) {

        return this
                .insert("com.ningpai.thirdaudit.mapper.ThirdStorePointMapper.insertSelective",
                        record);
    }
    /**
     * 根据主键查询
     *
     * @param storePointId
     * */
    @Override
    public ThirdStorePoint selectByPrimaryKey(Long storePointId) {

        return null;
    }
    /**
     * 修改记录
     *
     * @param record
     * */
    @Override
    public int updateByPrimaryKeySelective(ThirdStorePoint record) {

        return 0;
    }
    /**
     * 根据主键修改
     *
     * @param record
     * */
    @Override
    public int updateByPrimaryKey(ThirdStorePoint record) {

        return 0;
    }

}
