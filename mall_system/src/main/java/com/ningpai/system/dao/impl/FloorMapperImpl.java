/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Floor;
import com.ningpai.system.dao.FloorMapper;

/**
 * 楼层层数接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月6日 上午11:14:23
 * @version 1.0
 */
@Repository("floorMapper")
public class FloorMapperImpl extends BasicSqlSupport implements FloorMapper {
    /**
     * 删除楼层层数
     * 
     * @param floorId
     * @return
     */
    public int deleteByPrimaryKey(Long floorId) {
        return 0;
    }

    /**
     * 添加楼层层数
     * 
     * @param record
     * @return
     */
    public int insert(Floor record) {
        return 0;
    }

    /**
     * 添加楼层层数--可选字段
     * 
     * @param record
     * @return
     */
    public int insertSelective(Floor record) {
        return 0;
    }

    /**
     * 查询单个楼层层数
     * 
     * @param floorId
     * @return
     */
    public Floor selectByPrimaryKey(Long floorId) {
        return null;
    }

    /**
     * 修改楼层层数--可选字段
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Floor record) {
        return 0;
    }

    /**
     * 修改楼层层数
     * 
     * @param record
     * @return
     */
    public int updateByPrimaryKey(Floor record) {
        return 0;
    }

    /**
     * 查询所有楼层层数信息
     * 
     * @return List
     */
    public List<Floor> findAll() {
        // 查询所有楼层层数信息
        return this.selectList("com.ningpai.system.dao.FloorMapper.findAll");

    }

}
