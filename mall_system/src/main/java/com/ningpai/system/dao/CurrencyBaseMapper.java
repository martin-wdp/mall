package com.ningpai.system.dao;

import java.util.List;

import com.ningpai.system.bean.CurrencyBase;

/**
 * 货币基本信息DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:30:25
 * @version 1.0
 */
public interface CurrencyBaseMapper {
    /**
     * 根据主键删除货币基本信息
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加货币基本信息
     * 
     * @param record
     * @return
     */
    int insert(CurrencyBase record);

    /**
     * 添加货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(CurrencyBase record);

    /**
     * 更新货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CurrencyBase record);

    /**
     * 更新货币基本信息-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(CurrencyBase record);

    /**
     * 根据主键查询货币基本信息
     * 
     * @param id
     * @return
     */
    CurrencyBase selectByPrimaryKey(Long id);

    /**
     * 获取所有货币基本信息
     * 
     * @return
     */
    List<CurrencyBase> selectAllCurrencyBase();
}
