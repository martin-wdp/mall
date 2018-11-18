/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.Province;
import com.ningpai.system.dao.ProvinceMapper;

/**
 * 地区管理省份DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午3:33:39
 * @version 1.0
 */
@Repository("ProvinceMapper")
public class ProvinceMapperImpl extends BasicSqlSupport implements
        ProvinceMapper {
    /**
     * 根据主键ID删除省份信息
     * 
     * @see
     * com.ningpai.system.dao.ProvinceMapper#deleteByPrimaryKey(java.lang.Long)
     */

    public int deleteByPrimaryKey(Long provinceId) {
        return this.update(
                "com.ningpai.system.dao.ProvinceMapper.deleteByPrimaryKey",
                provinceId);
    }

    /**
     * 插入一条新的记录
     * 
     * @see
     * com.ningpai.system.dao.ProvinceMapper#insertSelective(com.ningpai.system
     * .bean.Province)
     */

    public int insertSelective(Province record) {
        return this
                .insert("com.ningpai.system.dao.ProvinceMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询
     * 
     * @see
     * com.ningpai.system.dao.ProvinceMapper#selectByPrimaryKey(java.lang.Long)
     */

    public Province selectByPrimaryKey(Long provinceId) {
        return this.selectOne(
                "com.ningpai.system.dao.ProvinceMapper.selectByPrimaryKey",
                provinceId);
    }

    /**
     * 更新记录
     * 
     * @see
     * com.ningpai.system.dao.ProvinceMapper#updateByPrimaryKeySelective(com
     * .ningpai.system.bean.Province)
     */

    public int updateByPrimaryKeySelective(Province record) {
        return this
                .update("com.ningpai.system.dao.ProvinceMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 分页查询省份信息
     * 
     * @see com.ningpai.system.dao.ProvinceMapper#queryAllProvice(java.util.Map)
     */

    public List<Object> queryAllProvice(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.ProvinceMapper.queryAllProvice", map);
    }

    /**
     * 根据参数查询所有的行数
     * 
     * @see com.ningpai.system.dao.ProvinceMapper#queryTotalCount(java.util.Map)
     */

    public Integer queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.ProvinceMapper.queryTotalCount", map);
    }

    /**
     * 根据名称查询是否存在
     * 
     * @see
     * com.ningpai.system.dao.ProvinceMapper#queryProvinceByName(java.lang.String
     * )
     */

    public int queryProvinceByName(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.ProvinceMapper.queryProvinceByName",
                map);
    }

    /**
     * 查询所有的省份信息
     * 
     * @see com.ningpai.system.dao.ProvinceMapper#queryAllProvince()
     */

    public List<Object> queryAllProvince() {
        return this
                .selectList("com.ningpai.system.dao.ProvinceMapper.queryAllProvince");
    }

    /**
     * 查询所有省份、城市、县区、街道
     */
    @Override
    public List<Object> queryAllProviceNew(Map<String, Object> limitmap) {
        return this.selectList(
                "com.ningpai.system.dao.ProvinceMapper.queryAllProviceNew",
                limitmap);
    }
    /**
     * 根据省份名称获取省份
     * add by 付陈林 2015年12月26日
     *
     *
     */
    @Override
    public Province selectByProvinceName(String provinceName) {
        return this.selectOne("com.ningpai.system.dao.ProvinceMapper.selectByProvinceName",provinceName);
    }

}
