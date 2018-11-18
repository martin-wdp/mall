/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.District;
import com.ningpai.system.dao.DistrictMapper;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.vo.DistrictVo;

/**
 * 区县DAO实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:49:43
 * @version 1.0
 */
@Repository("DistrictMapper")
public class DistrictMapperImpl extends BasicSqlSupport implements
        DistrictMapper {
    /**
     * 根据主键删除区县信息
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#deleteByPrimaryKey(java.lang.Long)
     */

    public int deleteByPrimaryKey(Long districtId) {
        return this.update(
                "com.ningpai.system.dao.DistrictMapper.deleteByPrimaryKey",
                districtId);
    }

    /**
     * 添加一条区县记录
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#insertSelective(com.ningpai.system
     * .bean.District)
     */

    public int insertSelective(District record) {
        return this
                .insert("com.ningpai.system.dao.DistrictMapper.insertSelective",
                        record);
    }

    /**
     * 根据主键查询区县信息
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#selectByPrimaryKey(java.lang.Long)
     */

    public District selectByPrimaryKey(Long districtId) {
        return this.selectOne(
                "com.ningpai.system.dao.DistrictMapper.selectByPrimaryKey",
                districtId);
    }

    /**
     * 更新区县信息
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#updateByPrimaryKeySelective(com
     * .ningpai.system.bean.District)
     */

    public int updateByPrimaryKeySelective(District record) {
        return this
                .update("com.ningpai.system.dao.DistrictMapper.updateByPrimaryKeySelective",
                        record);
    }

    /**
     * 根据 查询参数查询所有的行数
     * 
     * @see com.ningpai.system.dao.DistrictMapper#queryTotalCount(java.util.Map)
     */

    public int queryTotalCount(Map<String, Object> map) {
        return this.selectOne(
                "com.ningpai.system.dao.DistrictMapper.queryTotalCount", map);
    }

    /**
     * 根据分页参数和查询参数查询列表
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#queryDistrictListByPb(java.util
     * .Map)
     */

    public List<Object> queryDistrictListByPb(Map<String, Object> map) {
        return this.selectList(
                "com.ningpai.system.dao.DistrictMapper.queryDistrictListByPb",
                map);
    }

    /**
     * 根据城市ID查询区县的列表
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#queryDistrictListByCityId(java.
     * lang.Long)
     */

    public List<DistrictVo> queryDistrictListByCityId(Long cityId) {
        return this
                .selectList(
                        "com.ningpai.system.dao.DistrictMapper.queryDistrictListByCityId",
                        cityId);
    }

    /**
     * 根据区县名称查询是否存在
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#queryDistrictByDistrictName(java
     * .lang.String)
     */

    public int queryDistrictByDistrictName(Map<String, Object> map) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.DistrictMapper.queryDistrictByDistrictName",
                        map);
    }

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @see
     * com.ningpai.system.dao.DistrictMapper#queryAddressNameByDistrictId(java
     * .lang.Long)
     */

    public AddressUtil queryAddressNameByDistrictId(Long districtId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.DistrictMapper.queryAddressNameByDistrictId",
                        districtId);
    }

    /**
     * 根据城市id、地区名称查询地区信息
     * @param  map
     *          城市id cityId  地区名称 districtName
     * @return
     * add by付陈林  2015年12月26日
     * */
    @Override
    public District selectByDistrictName(Map map) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.DistrictMapper.selectByDistrictName",
                        map);
    }

}
