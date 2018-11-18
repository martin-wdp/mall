package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.LogisticsCompany;
import com.ningpai.system.dao.ILogisticsCompanyDao;

/**
 * 物流公司设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:55:21
 * @version V1.0
 */
@Repository("logisticsCompanyDaoImpl")
public class LogisticsCompanyDaoImpl extends BasicSqlSupport implements
        ILogisticsCompanyDao {

    /**
     * 保存物流公司设置
     * 
     * @param logisticsCompany
     *            物流公司设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final boolean saveLogisticsCompany(
            final LogisticsCompany logisticsCompany) {
        return this
                .insert("com.ningpai.system.dao.LogisticsCompanyDaoImpl.saveLogisticsCompany",
                        logisticsCompany) >= 1 ? true : false;
    }

    /**
     * 修改物流公司设置
     * 
     * @param logisticsCompany
     *            待修改物流公司设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int updateLogisticsCompany(
            final LogisticsCompany logisticsCompany) {
        return this
                .update("com.ningpai.system.dao.LogisticsCompanyDaoImpl.updateLogisticsCompany",
                        logisticsCompany);
    }

    /**
     * 根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final LogisticsCompany getLogisticsCompanyById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.getLogisticsCompanyById",
                        id);
    }

    /**
     * 第三方根据物流公司设置对象的id查询物流公司设置对象
     * 
     * @param id
     *            物流公司设置id
     * @return 物流公司设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final LogisticsCompany getThirdLogisticsCompanyById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.getThirdLogisticsCompanyById",
                        id);
    }

    /**
     * 根据物流公司设置对象的id字符集合查询物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 物流公司设置对象集合
     * @author system
     * @since 2014-03-21 17:55:21
     */
    public final List<LogisticsCompany> getLogisticsCompanyByIds(
            final String ids) {
        // 将字符串ids转换为集合，用于sql赋值
        Map<String, Object> para = new HashMap<String, Object>(1);
        List<String> idList = new ArrayList<String>();
        if (ids.contains(",")) {
            for (String id : ids.split(",")) {
                idList.add(id);
            }
        } else {
            idList.add(ids);
        }
        para.put("ids", idList);
        return this
                .selectList(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.getLogisticsCompanyByIds",
                        para);
    }

    /**
     * 根据物流公司设置对象的id字符集合删除物流公司设置对象
     * 
     * @param ids
     *            物流公司设置id字符集合(如果多个使用,分割)
     * @return 删除物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int deleteLogisticsCompany(final String ids) {
        Map<String, Object> para = new HashMap<String, Object>(1);
        List<String> idList = new ArrayList<String>();
        if (ids.contains(",")) {
            for (String id : ids.split(",")) {
                idList.add(id);
            }
        } else {
            idList.add(ids);
        }
        para.put("ids", idList);
        return this
                .delete("com.ningpai.system.dao.LogisticsCompanyDaoImpl.deleteLogisticsCompany",
                        para);
    }

    /**
     * 更新物流公司设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新物流公司设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int updateLogisticsCompanyFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.LogisticsCompanyDaoImpl.updateLogisticsCompanyFieldById",
                        parameter);
    }

    /**
     * 根据物流公司设置对象的单个字段查询物流公司设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int getLogisticsCompanyByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.getLogisticsCompanyByFieldTotal",
                        parameter);
    }

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
    public final List<LogisticsCompany> getLogisticsCompanyByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.getLogisticsCompanyByField",
                        parameter);
    }

    /**
     * 查询物流公司设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final int queryLogisticsCompanyTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.queryLogisticsCompanyTotal",
                        parameter);
    }

    /**
     * 分页查询物流公司设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 物流公司设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public final List<LogisticsCompany> queryLogisticsCompanyByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.LogisticsCompanyDaoImpl.queryLogisticsCompanyByPage",
                        parameter);
    }

    /**
     * 获得物流公司当前最大排序值
     * 
     * @return 当前最大排序值
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:55:21
     */
    public int getLogisticsCompanyMaxSort() {
        return this
                .selectOne("com.ningpai.system.dao.LogisticsCompanyDaoImpl.getLogisticsCompanyMaxSort");
    }

    /*
     * 查询所有未删除、已启用的物流公司对象信息
     */
    @Override
    public List<LogisticsCompany> queryAllLogisticsCompany() {
        return this
                .selectList("com.ningpai.system.dao.LogisticsCompanyDaoImpl.queryAllLogisticsCompany");
    }
}
