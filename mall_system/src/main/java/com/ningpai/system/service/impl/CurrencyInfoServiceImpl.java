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

import com.ningpai.system.bean.CurrencyBase;
import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.dao.CurrencyBaseMapper;
import com.ningpai.system.dao.CurrencyInfoMapper;
import com.ningpai.system.service.CurrencyInfoService;
import com.ningpai.system.vo.CurrencyInfoVo;
import com.ningpai.util.PageBean;

/**
 * 货币信息SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 17:19:25
 * @version 1.0
 */
@Service("CurrencyInfoService")
public class CurrencyInfoServiceImpl implements CurrencyInfoService {
    /** spring货品注解 */
    private CurrencyInfoMapper currencyInfoMapper;
    /** spring货币注解 */
    private CurrencyBaseMapper currencyBaseMapper;

    public CurrencyInfoMapper getCurrencyInfoMapper() {
        return currencyInfoMapper;
    }

    @Resource(name = "CurrencyInfoMapper")
    public void setCurrencyInfoMapper(CurrencyInfoMapper currencyInfoMapper) {
        this.currencyInfoMapper = currencyInfoMapper;
    }

    public CurrencyBaseMapper getCurrencyBaseMapper() {
        return currencyBaseMapper;
    }

    @Resource(name = "CurrencyBaseMapper")
    public void setCurrencyBaseMapper(CurrencyBaseMapper currencyBaseMapper) {
        this.currencyBaseMapper = currencyBaseMapper;
    }

    /**
     * 根据主键删除货币信息
     * 
     * @param id
     * @return
     */
    public int deleteCurrencyInfo(Long id) {
        return currencyInfoMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除货币信息
     * 
     * @param ids
     *            货币信息编号数组
     * @return
     */
    public void batchDelCurrencyInfo(Long[] ids) {
        if (null != ids && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                deleteCurrencyInfo(ids[i]);
            }
        }
    }

    /**
     * 添加货币信息
     * 
     * @param record
     * @return
     */
    public int saveCurrencyInfo(CurrencyInfo record) {
        return currencyInfoMapper.insert(record);
    }

    /**
     * 更新货币信息
     * 
     * @param record
     * @return
     */
    public int updateCurrencyInfo(CurrencyInfo record) {
        return currencyInfoMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 根据主键查询货币信息
     * 
     * @param id
     * @return
     */
    public CurrencyInfoVo getCurrencyInfo(Long id) {

        return currencyInfoMapper.selectByPrimaryKey(id);
    }

    /**
     * 根据分页参数查询货币信息集合
     * 
     * @param map
     * @return
     */
    public PageBean queryCurrencyInfoByPageBean(PageBean pb) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 查询数据的总行数并设置到PageBean中
            pb.setRows(currencyInfoMapper.queryTotalCount());
            map.put("startRowNum", pb.getStartRowNum());
            map.put("endRowNum", pb.getEndRowNum());
            pb.setList(currencyInfoMapper.queryByPageBean(map));
        } finally {
            map = null;
        }
        return pb;
    }

    /**
     * 获取所有货币基础信息
     * 
     * @return
     */
    public List<CurrencyBase> findAllCurrencyBase() {
        return currencyBaseMapper.selectAllCurrencyBase();
    }

    /**
     * 根据编号查询货币基础信息
     * 
     * @param id
     * @return
     */
    public CurrencyBase getCurrencyBaseById(Long id) {
        return currencyBaseMapper.selectByPrimaryKey(id);
    }
}
