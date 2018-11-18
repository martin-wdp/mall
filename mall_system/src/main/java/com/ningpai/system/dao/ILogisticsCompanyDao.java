package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.LogisticsCompany;

/**
 * 物流公司设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:55:21
 * @version V1.0
 */
@Repository
public interface ILogisticsCompanyDao {

    /**
     * 保存物流公司设置
     * 
     * @param logisticsCompany
     *            物流公司设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    boolean saveLogisticsCompany(LogisticsCompany logisticsCompany);

    /**
     * 修改物流公司设置
     * 
     * @param logisticsCompany
     *            待修改物流公司设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int updateLogisticsCompany(LogisticsCompany logisticsCompany);

    /**
     * 根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    LogisticsCompany getLogisticsCompanyById(int id);

    /**
     * 第三方根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    LogisticsCompany getThirdLogisticsCompanyById(int id);

    /**
     * 根据物流公司设置对象的id字符集合查询物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 物流公司设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    List<LogisticsCompany> getLogisticsCompanyByIds(String ids);

    /**
     * 根据物流公司设置对象的id字符集合删除物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 删除物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int deleteLogisticsCompany(String ids);

    /**
     * 更新物流公司设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int updateLogisticsCompanyFieldById(Map<String, Object> parameter);

    /**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int getLogisticsCompanyByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    List<LogisticsCompany> getLogisticsCompanyByField(
            Map<String, Object> parameter);

    /**
     * 查询物流公司设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int queryLogisticsCompanyTotal(Map<String, Object> parameter);

    /**
     * 分页查询物流公司设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    List<LogisticsCompany> queryLogisticsCompanyByPage(
            Map<String, Object> parameter);

    /**
     * 获得物流公司当前最大排序值
     * 
     * @return 当前最大排序值
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    int getLogisticsCompanyMaxSort();

    /**
     * 查询所有未删除、已启用的物流公司对象信息
     * 
     * @return
     */
    List<LogisticsCompany> queryAllLogisticsCompany();
}
