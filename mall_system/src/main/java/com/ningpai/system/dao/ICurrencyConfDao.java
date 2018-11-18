package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.CurrencyConf;

/**
 * 货币设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 13:17:21
 * @version V1.0
 */
@Repository
public interface ICurrencyConfDao {

    /**
     * 保存货币设置
     * 
     * @param currencyConf
     *            货币设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    boolean saveCurrencyConf(CurrencyConf currencyConf);

    /**
     * 修改货币设置
     * 
     * @param currencyConf
     *            待修改货币设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    int updateCurrencyConf(CurrencyConf currencyConf);

    /**
     * 根据货币设置对象的id查询货币设置对象
     * 
     * @param id
     *            货币设置id
     * @return 货币设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    CurrencyConf getCurrencyConfById(int id);

    /**
     * 根据货币设置对象的id字符集合查询货币设置对象
     * 
     * @param ids
     *            货币设置id字符集合(如果多个使用,分割)
     * @return 货币设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    List<CurrencyConf> getCurrencyConfByIds(String ids);

    /**
     * 根据货币设置对象的id字符集合删除货币设置对象
     * 
     * @param ids
     *            货币设置id字符集合(如果多个使用,分割)
     * @return 删除货币设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    int deleteCurrencyConf(String ids);

    /**
     * 更新货币设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新货币设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    int updateCurrencyConfFieldById(Map<String, Object> parameter);

    /**
     * 根据货币设置对象的单个字段查询货币设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    int getCurrencyConfByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据货币设置对象的单个字段查询货币设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    List<CurrencyConf> getCurrencyConfByField(Map<String, Object> parameter);

    /**
     * 查询货币设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    int queryCurrencyConfTotal(Map<String, Object> parameter);

    /**
     * 分页查询货币设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 货币设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 13:17:21
     */
    List<CurrencyConf> queryCurrencyConfByPage(Map<String, Object> parameter);
}
