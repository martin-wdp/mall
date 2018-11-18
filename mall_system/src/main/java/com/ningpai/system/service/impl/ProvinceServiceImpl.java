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

import com.ningpai.system.bean.Province;
import com.ningpai.system.dao.ProvinceMapper;
import com.ningpai.system.service.ProvinceService;
import com.ningpai.system.util.SelectBean;
import com.ningpai.util.PageBean;

/**
 * 地区管理省份Service
 * 
 * @author NINGPAI-YuanKangKang
 * @since 2014年2月12日 下午4:42:33
 * @version 1.0
 */
@Service("ProvinceService")
public class ProvinceServiceImpl implements ProvinceService {

    private static final String SEARCHTEXT = "searchText";

    /** 地区管理省份dao */
    private ProvinceMapper provinceMapper;

    /** 结束行数 */
    private static final int ENDROWNUM = 10000;

    /**
     * 保存省份信息
     *
     */
    public int saveProvince(String provinceName, String provinceSort) {
        Province province = new Province();
        try {
            if (null != provinceName) {
                province.setProvinceName(provinceName);
                province.setProvinceSort(provinceSort);
                province.setDelFlag("0");
                return this.provinceMapper.insertSelective(province);
            }
            return 0;
        } finally {
            province = null;
        }

    }

    /**
     * 删除省份信息
     * 
     * @see com.ningpai.system.service.ProvinceService#delProvince(java.lang.Long)
     */
    public int delProvince(Long provinceId) {
        return this.provinceMapper.deleteByPrimaryKey(provinceId);
    }

    /**
     * 更新省份信息
     * 
     */
    public int updateProvince(String provinceId, String provinceName, String provinceSort) {
        Province province = new Province();
        try {
            if (null != provinceId) {
                province.setProvinceId(Long.parseLong(provinceId));
                province.setProvinceName(provinceName);
                province.setProvinceSort(provinceSort);
                return this.provinceMapper.updateByPrimaryKeySelective(province);
            } else {
                return 0;
            }
        } finally {
            province = null;
        }
    }

    /**
     * 根据主键ID查询省份信息
     *
     */
    public Province findProvinceByPrimaryKey(Long provinceId) {
        return this.provinceMapper.selectByPrimaryKey(provinceId);
    }

    /**
     * 根据分页实体以及查询参数查询列表
     *
     */
    public PageBean findListByPageBean(PageBean pb, SelectBean selectBean) {
        Map<String, Object> limitmap = new HashMap<String, Object>();
        try {
            limitmap.put("condition", selectBean.getCondition());
            limitmap.put(SEARCHTEXT, selectBean.getSearchText());
            pb.setRows(this.provinceMapper.queryTotalCount(limitmap));
            limitmap.put("startRowNum", pb.getStartRowNum());
            limitmap.put("endRowNum", ENDROWNUM);
            pb.setList(this.provinceMapper.queryAllProvice(limitmap));
        } finally {
            limitmap = null;
        }
        return pb;
    }

    /**
     * 批量删除省份实体
     *
     */
    public int batchDelProvince(String[] provinceIds) {
        Integer count = 0;
        if (null != provinceIds && provinceIds.length > 0) {
            for (int i = 0; i < provinceIds.length; i++) {
                count = +this.delProvince(Long.parseLong(provinceIds[i]));
            }
        }
        return count;
    }

    /**
     * 验证省份名称是否可用
     *
     */
    public boolean checkProvinceName(String provinceName) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            map.put("provinceName", provinceName);
            return this.provinceMapper.queryProvinceByName(map) > 0 ? false : true;
        } finally {
            map = null;
        }
    }

    /**
     * 查询所有的省份信息
     * 
     * @see com.ningpai.system.service.ProvinceService#queryAllProvince()
     */
    public List<Object> queryAllProvince() {
        return this.provinceMapper.queryAllProvince();
    }

    /**
     * 查询所有省份、城市、地区、街道
     */
    @Override
    public PageBean findListByPageBeanNew(PageBean pb, SelectBean selectBean) {
        Map<String, Object> limitmap = new HashMap<String, Object>();
        try {
            limitmap.put(SEARCHTEXT, selectBean.getSearchText());
            pb.setList(this.provinceMapper.queryAllProviceNew(limitmap));
        } finally {
            limitmap = null;
        }
        return pb;
    }

    /**
     * 根据名称查询所有省份
     *
     * @param provinceName
     *            省份名称
     * @return
     */
    @Override
    public List<Object> queryAllProvinceByName(String provinceName) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(SEARCHTEXT, provinceName);
        paramMap.put("condition", "1");
        paramMap.put("startRowNum", 0);
        paramMap.put("endRowNum", 1000);
        return provinceMapper.queryAllProvice(paramMap);
    }

    public ProvinceMapper getProvinceMapper() {
        return provinceMapper;
    }

    @Resource(name = "ProvinceMapper")
    public void setProvinceMapper(ProvinceMapper provinceMapper) {
        this.provinceMapper = provinceMapper;
    }

}
