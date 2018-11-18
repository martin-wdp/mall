/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ningpai.system.bean.District;
import com.ningpai.system.dao.DistrictMapper;
import com.ningpai.system.service.DistrictService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.system.vo.DistrictVo;
import com.ningpai.util.PageBean;

/**
 * 区县信息Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 上午10:59:57
 * @version 1.0
 */
@Service("DistrictService")
public class DistrictServiceImpl implements DistrictService {
    /** 区县信息注解 */
    private DistrictMapper districtMapper;

    public DistrictMapper getDistrictMapper() {
        return districtMapper;
    }

    @Resource(name = "DistrictMapper")
    public void setDistrictMapper(DistrictMapper districtMapper) {
        this.districtMapper = districtMapper;
    }

    /**
     * 增加区县信息
     * 
     * @see
     * com.ningpai.system.service.DistrictService#saveDistrict(com.ningpai.system
     * .bean.District)
     */

    public int saveDistrict(String districtName, String cityId,
            String districtSort) {
        District district = new District();
        try {
            if (null != districtName) {
                district.setDistrictName(districtName);
                district.setDistrictSort(districtSort);
                district.setCityId(Long.parseLong(cityId));
                district.setDelFlag("0");
                return this.districtMapper.insertSelective(district);
            }
            return 0;
        } finally {
            district = null;
        }

    }

    /**
     * 删除区县信息
     * 
     * @see
     * com.ningpai.system.service.DistrictService#delDistrict(java.lang.Long)
     */
    public int delDistrict(Long districtId) {
        return this.districtMapper.deleteByPrimaryKey(districtId);
    }

    /**
     * 更新区县信息
     * 
     * @see
     * com.ningpai.system.service.DistrictService#updateDistrict(com.ningpai
     * .system.bean.District)
     */
    public int updateDistrict(String districtId, String districtName,
            String districtSort) {
        District district = new District();
        try {
            if (null != districtId) {
                district.setDistrictId(Long.parseLong(districtId));
                district.setDistrictName(districtName);
                district.setDistrictSort(districtSort);
                return this.districtMapper
                        .updateByPrimaryKeySelective(district);
            } else {
                return 0;
            }
        } finally {
            district = null;
        }
    }

    /**
     * 根据主键查询实体
     * 
     * @see
     * com.ningpai.system.service.DistrictService#findDistrictByPrimaryKey(java
     * .lang.Long)
     */
    public District findDistrictByPrimaryKey(Long districtId) {
        return this.districtMapper.selectByPrimaryKey(districtId);
    }

    /**
     * 根据分页辅助Bean以及查询参数查询列表
     * 
     * @see
     * com.ningpai.system.service.DistrictService#findListByPageBean(com.ningpai
     * .util.PageBean, com.ningpai.system.util.SelectBean)
     */
    public PageBean findListByPageBean(PageBean pb, SelectBean selectBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.districtMapper.queryTotalCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.districtMapper.queryDistrictListByPb(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除区县信息
     * 
     * @see
     * com.ningpai.system.service.DistrictService#batchDelDistrict(java.lang
     * .Long[])
     */
    public int batchDelDistrict(String[] districtIds) {
        int count = 0;
        if (null != districtIds && districtIds.length > 0) {
            for (int i = 0; i < districtIds.length; i++) {
                count += this.districtMapper.deleteByPrimaryKey(Long
                        .parseLong(districtIds[i]));
            }
        }
        return count;
    }

    /**
     * 
     * 
     * @see
     * com.ningpai.system.service.DistrictService#queryDistrictByCityId(java
     * .lang.Long)
     */
    public List<DistrictVo> queryDistrictByCityId(Long cityId) {
        return this.districtMapper.queryDistrictListByCityId(cityId);
    }

    /**
     * 验证区县名称是否可用
     * 
     * @see
     * com.ningpai.system.service.DistrictService#checkDistrictName(java.lang
     * .String)
     */
    public boolean checkDistrictName(String districtName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("districtName", districtName);
            return this.districtMapper.queryDistrictByDistrictName(map) > 0 ? false
                    : true;
        } finally {
            map = null;
        }
    }

    /**
     * 根据区县ID查询所属的城市和省份名称
     * 
     * @see
     * com.ningpai.system.service.DistrictService#queryAddressNameByDistrictId
     * (java.lang.Long)
     */
    public AddressUtil queryAddressNameByDistrictId(Long districtId) {
        return this.districtMapper.queryAddressNameByDistrictId(districtId);
    }

    /**
     * 根据城市id和县区名称查询所有县区
     *
     * @param cityId
     *            城市id
     * @param districtName
     *            县区名称
     * @return
     */
    @Override
    public List<Object> queryDistrictByCityIdAndDistName(Long cityId,
            String districtName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cityId", cityId);
        paramMap.put("districtName", districtName);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        return this.districtMapper.queryDistrictListByPb(paramMap);
    }

}
