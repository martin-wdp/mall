/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.Street;
import com.ningpai.system.dao.StreetMapper;
import com.ningpai.system.service.StreetService;
import com.ningpai.system.util.AddressUtil;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 街道Service实现
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月13日 下午1:35:58
 * @version 1.0
 */
@Repository("StreetService")
public class StreetServiceImpl implements StreetService {
    /** spring注解 */
    private StreetMapper streetMapper;

    public StreetMapper getStreetMapper() {
        return streetMapper;
    }
    //spring注入
    @Resource(name = "StreetMapper")
    public void setStreetMapper(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }

    /**
     * 添加
     * 
     * @see com.ningpai.system.service.StreetService#saveStreet(com.ningpai.system
     *      .bean.Street)
     */
    public int saveStreet(String streetName, String districtId,
            String streetSort) {
        Street street = new Street();
        try {
            if (null != streetName) {
                street.setStreetName(streetName);
                street.setStreetSort(streetSort);
                street.setDistrictId(Long.parseLong(districtId));
                street.setDelFlag("0");
                return this.streetMapper.insertSelective(street);
            }
            return 0;
        } finally {
            street = null;
        }

    }

    /**
     * 删除街道信息
     * 
     * @param streetId
     *            街道ID
     * @return 删除的行数
     */
    public int delStreet(Long streetId) {
        return this.streetMapper.deleteByPrimaryKey(streetId);
    }

    /**
     * 更新街道信息
     * 
     * @see com.ningpai.system.service.StreetService#updateStreet(com.ningpai.system
     *      .bean.Street)
     */
    public int updateStreet(String streetId, String streetName,
            String streetSort) {
        Street street = new Street();
        try {
            if (null != streetId) {
                street.setStreetId(Long.parseLong(streetId));
                street.setStreetName(streetName);
                street.setStreetSort(streetSort);
                return this.streetMapper.updateByPrimaryKeySelective(street);
            } else {
                return 0;
            }
        } finally {
            street = null;
        }
    }

    /**
     * 根据主键查询
     * 
     * @see com.ningpai.system.service.StreetService#findStreetByPrimaryKey(java.
     *      lang.Long)
     */
    public Street findStreetByPrimaryKey(Long streetId) {
        return this.streetMapper.selectByPrimaryKey(streetId);
    }

    /**
     * 根据分页参数以及查询参数查询列表
     * 
     * @see com.ningpai.system.service.StreetService#findListByPageBean(com.ningpai
     *      .util.PageBean, com.ningpai.system.util.SelectBean)
     */

    public PageBean findListByPageBean(PageBean pb, SelectBean selectBean) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            pb.setRows(this.streetMapper.queryTotalCount(map));
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(this.streetMapper.queryAllStreetByPb(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 批量删除记录
     * 
     * @see com.ningpai.system.service.StreetService#batchDelStreet(java.lang.Long[])
     */
    public int batchDelStreet(String[] streetIds) {
        int count = 0;
        if (null != streetIds && streetIds.length > 0) {
            for (int i = 0; i < streetIds.length; i++) {
                count += this.delStreet(Long.parseLong(streetIds[i]));
            }
        }
        return count;
    }

    /**
     * 根据区县ID查询街道信息
     * 
     * @see com.ningpai.system.service.StreetService#queryStreetByDistrictId(java
     *      .lang.Long)
     */

    public List<Street> queryStreetByDistrictId(Long districtId) {
        return this.streetMapper.queryStreetByDistrictId(districtId);
    }

    /**
     * 验证街道名称是否可用
     * 
     * @see
     * com.ningpai.system.service.StreetService#checkStreetName(java.lang.String
     * )
     */

    public boolean checkStreetName(String streetName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("streetName", streetName);
            return this.streetMapper.queryStreetByStreetName(map) > 0 ? false
                    : true;
        } finally {
            map = null;
        }
    }

    /**
     * 根据街道ID查询所属的区县，城市和省份ID
     * 
     * @see com.ningpai.system.service.StreetService#queryAddressNameByStreetId(java
     *      .lang.Long)
     */

    public AddressUtil queryAddressNameByStreetId(Long streetId) {
        return this.streetMapper.queryAddressNameByStreetId(streetId);
    }

    /**
     * 根据县区id和街道名称查询所有街道
     * 
     * @param districtId
     *            县区id
     * @param streetName
     *            街道名称
     * @return
     */
    @Override
    public List<Object> queryStreetByDistrictIdAndStreetName(Long districtId,
            String streetName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("districtId", districtId);
        paramMap.put("streetName", streetName);
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        return this.streetMapper.queryAllStreetByPb(paramMap);
    }

}
