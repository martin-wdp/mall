/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.GoodsCateSystem;
import com.ningpai.system.bean.Storey;
import com.ningpai.system.dao.StoreyMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 楼层表接口实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月4日 下午5:41:47
 * @version 1.0
 */
@Repository("storeyMapper")
public class StoreyMapperImpl extends BasicSqlSupport implements StoreyMapper {

    /**
     * 删除楼层信息
     * 
     * @param seId
     * @return
     */
    public int deleteByPrimaryKey(Long seId) {
        // 删除楼层信息
        return this.delete(
                "com.ningpai.system.dao.StoreyMapper.deleteByPrimaryKey", seId);
    }

    /**
     * 查询所有商品父分类
     */
    public List<GoodsCateSystem> findParentGoodsCate() {
        // 查询所有商品分类
        return this
                .selectList("com.ningpai.system.dao.GoodsCateSystemMapper.findParentGoodsCate");
    }

    /**
     * 添加楼层信息
     */
    public int insert(Storey record) {
        return 0;
    }

    /**
     * 添加一条楼层信息
     * 
     * @param record
     * @return int
     */
    public int insertSelective(Storey record) {
        // 添加楼层信息
        return this.insert(
                "com.ningpai.system.dao.StoreyMapper.insertSelective", record);
    }

    /**
     * 根据ID查询楼层信息
     * 
     * @param seId
     * @return Storey
     */
    public Storey selectByPrimaryKey(Long seId) {
        // 根据ID查询楼层信息
        return this.selectOne(
                "com.ningpai.system.dao.StoreyMapper.selectByPrimaryKey", seId);
    }

    /**
     * 修改楼层信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Storey record) {
        // 修改楼层信息
        return this
                .update("com.ningpai.system.dao.StoreyMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改楼层信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Storey record) {
        return 0;
    }

    /**
     * 分页查询楼层信息
     * 
     * @param map
     * @return List
     */
    public List<Object> findByPageBean(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.StoreyMapper.findByPageBean", map);
    }

    /**
     * 查询楼层总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {
        return this.selectOne(
                "com.ningpai.system.dao.StoreyMapper.findTotalCount",
                selectBean);
    }

    /**
     * 根据楼层层数查询楼层信息
     * 
     * @param floorId
     * @return Floor
     */
    public Storey findStoreyByFloorId(Long floorId) {
        // 按照floorId查询楼层信息
        return this.selectOne(
                "com.ningpai.system.dao.StoreyMapper.findFloorByFloorId",
                floorId);
    }

}
