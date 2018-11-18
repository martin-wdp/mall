package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.vo.CurrencyInfoVo;

/**
 * 货币信息DAO
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 16:40:25
 * @version 1.0
 */
public interface CurrencyInfoMapper {
    /**
     * 根据主键删除货币信息
     * 
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 添加货币信息
     * 
     * @param record
     * @return
     */
    int insert(CurrencyInfo record);

    /**
     * 添加货币信息-字段可选
     * 
     * @param record
     * @return
     */
    int insertSelective(CurrencyInfo record);

    /**
     * 更新货币信息-字段可选
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(CurrencyInfo record);

    /**
     * 更新货币信息
     * 
     * @param record
     * @return
     */
    int updateByPrimaryKey(CurrencyInfo record);

    /**
     * 根据主键查询货币信息
     * 
     * @param id
     * @return
     */
    CurrencyInfoVo selectByPrimaryKey(Long id);

    /**
     * 查询所有货币信息的行数
     * 
     * @return
     */
    Integer queryTotalCount();

    /**
     * 根据分页参数查询货币信息集合
     * 
     * @param map
     * @return
     */
    List<Object> queryByPageBean(Map<String, Object> map);
}
