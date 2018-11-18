package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.StatisticsCode;
import com.ningpai.system.dao.IStatisticsCodeDao;

/**
 * 统计代码数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-21 17:03:45
 * @version V1.0
 */
@Repository("statisticsCodeDaoImpl")
public class StatisticsCodeDaoImpl extends BasicSqlSupport implements
        IStatisticsCodeDao {

    /**
     * 保存统计代码
     * 
     * @param statisticsCode
     *            统计代码对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final boolean saveStatisticsCode(final StatisticsCode statisticsCode) {
        return this
                .insert("com.ningpai.system.dao.StatisticsCodeDaoImpl.saveStatisticsCode",
                        statisticsCode) >= 1 ? true : false;
    }

    /**
     * 修改统计代码
     * 
     * @param statisticsCode
     *            待修改统计代码对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int updateStatisticsCode(final StatisticsCode statisticsCode) {
        return this
                .update("com.ningpai.system.dao.StatisticsCodeDaoImpl.updateStatisticsCode",
                        statisticsCode);
    }

    /**
     * 根据统计代码对象的id查询统计代码对象
     * 
     * @param id
     *            统计代码id
     * @return 统计代码对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final StatisticsCode getStatisticsCodeById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.getStatisticsCodeById",
                        id);
    }

    /**
     * 根据统计代码对象的id字符集合查询统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 统计代码对象集合
     * @author system
     * @since 2014-03-21 17:03:45
     */
    public final List<StatisticsCode> getStatisticsCodeByIds(final String ids) {
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
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.getStatisticsCodeByIds",
                        para);
    }

    /**
     * 根据统计代码对象的id字符集合删除统计代码对象
     * 
     * @param ids
     *            统计代码id字符集合(如果多个使用,分割)
     * @return 删除统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int deleteStatisticsCode(final String ids) {
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
                .delete("com.ningpai.system.dao.StatisticsCodeDaoImpl.deleteStatisticsCode",
                        para);
    }

    /**
     * 更新统计代码对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新统计代码对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int updateStatisticsCodeFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.StatisticsCodeDaoImpl.updateStatisticsCodeFieldById",
                        parameter);
    }

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int getStatisticsCodeByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.getStatisticsCodeByFieldTotal",
                        parameter);
    }

    /**
     * 根据统计代码对象的单个字段查询统计代码对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final List<StatisticsCode> getStatisticsCodeByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.getStatisticsCodeByField",
                        parameter);
    }

    /**
     * 查询统计代码对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final int queryStatisticsCodeTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.queryStatisticsCodeTotal",
                        parameter);
    }

    /**
     * 分页查询统计代码对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 统计代码对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-21 17:03:45
     */
    public final List<StatisticsCode> queryStatisticsCodeByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.StatisticsCodeDaoImpl.queryStatisticsCodeByPage",
                        parameter);
    }

    /*
     * 查询所有统计代码
     * 
     * @see com.ningpai.system.dao.IStatisticsCodeDao#selectAllStatisticsCode()
     */
    @Override
    public List<StatisticsCode> selectAllStatisticsCode() {
        return this
                .selectList("com.ningpai.system.dao.StatisticsCodeDaoImpl.selectAllStatisticsCode");
    }

    /*
     * 修改所有统计代码为不启用
     * 
     * @see com.ningpai.system.dao.IStatisticsCodeDao#updateUserdStatusToNo()
     */
    @Override
    public int updateUserdStatusToNo() {
        return this
                .update("com.ningpai.system.dao.StatisticsCodeDaoImpl.updateUserdStatusToNo");
    }
}
