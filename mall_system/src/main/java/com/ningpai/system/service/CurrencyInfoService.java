package com.ningpai.system.service;

import java.util.List;

import com.ningpai.system.bean.CurrencyBase;
import com.ningpai.system.bean.CurrencyInfo;
import com.ningpai.system.vo.CurrencyInfoVo;
import com.ningpai.util.PageBean;

/**
 * 货币信息SERVICE
 * 
 * @author NINGPAI-WangHaiYang
 * @since 2014年2月26日 17:19:25
 * @version 1.0
 */
public interface CurrencyInfoService {
    /**
     * 根据主键删除货币信息
     * 
     * @param id
     * @return
     */
    int deleteCurrencyInfo(Long id);

    /**
     * 批量删除货币信息
     * 
     * @param ids
     *            货币信息编号数组
     * @return
     */
    void batchDelCurrencyInfo(Long[] ids);

    /**
     * 添加货币信息
     * 
     * @param record
     * @return
     */
    int saveCurrencyInfo(CurrencyInfo record);

    /**
     * 更新货币信息
     * 
     * @param record
     * @return
     */
    int updateCurrencyInfo(CurrencyInfo record);

    /**
     * 根据主键查询货币信息
     * 
     * @param id
     * @return
     */
    CurrencyInfoVo getCurrencyInfo(Long id);

    /**
     * 根据分页参数查询货币信息集合
     * 
     * @param map
     * @return
     */
    PageBean queryCurrencyInfoByPageBean(PageBean pb);

    /**
     * 获取所有货币基础信息
     * 
     * @return
     */
    List<CurrencyBase> findAllCurrencyBase();

    /**
     * 根据编号查询货币基础信息
     * 
     * @param id
     * @return
     */
    CurrencyBase getCurrencyBaseById(Long id);
}
