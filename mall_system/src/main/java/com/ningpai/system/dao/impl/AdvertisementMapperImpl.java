/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Advertisement;
import com.ningpai.system.dao.AdvertisementMapper;
import com.ningpai.system.util.SelectBean;

/**
 * 首页广告设置数据层实现类
 * 
 * @author NINGPAI-LiHaoZe
 * @since 2014年1月14日 下午7:50:09
 * @version 1.0
 */
@Repository("advertisementMapper")
public class AdvertisementMapperImpl extends BasicSqlSupport implements
        AdvertisementMapper {
    /**
     * 删除广告信息
     * 
     * @param adverId
     * @return int
     */
    public int deleteByPrimaryKey(Long adverId) {

        return this
                .update("com.ningpai.system.dao.AdvertisementMapper.deleteByPrimaryKey",
                        adverId);
    }

    /**
     * 添加首页广告
     * 
     * @param record
     * @return int
     */
    public int insert(Advertisement record) {
        return 0;
    }

    /**
     * 添加首页广告
     * 
     * @param record
     * @return int
     */
    public int insertSelective(Advertisement record) {

        return this.insert(
                "com.ningpai.system.dao.AdvertisementMapper.insertSelective",
                record);
    }

    /**
     * 根据Id查询首页广告信息
     * 
     * @param adverId
     * @return Advertisement
     */
    public Advertisement selectByPrimaryKey(Long adverId) {

        return this
                .selectOne(
                        "com.ningpai.system.dao.AdvertisementMapper.selectByPrimaryKey",
                        adverId);
    }

    /**
     * 修改首页广告信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKeySelective(Advertisement record) {

        return this
                .update("com.ningpai.system.dao.AdvertisementMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 修改首页广告信息
     * 
     * @param record
     * @return int
     */
    public int updateByPrimaryKey(Advertisement record) {
        return 0;
    }

    /**
     * 分页查询广告信息
     * 
     * @param map
     * @return
     */
    public List<Object> findByPageBean(Map<String, Object> map) {

        return this.selectList(
                "com.ningpai.system.dao.AdvertisementMapper.findByPageBean",
                map);
    }

    /**
     * 查询总行数
     * 
     * @return int
     */
    public int findTotalCount(SelectBean selectBean) {

        return this.selectOne(
                "com.ningpai.system.dao.AdvertisementMapper.findTotalCount",
                selectBean);
    }

}
