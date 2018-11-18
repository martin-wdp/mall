package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.SysDictionary;
import com.ningpai.system.dao.ISysDictionaryDao;

/**
 * 系统字典数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
@Repository("sysDictionaryDaoImpl")
public class SysDictionaryDaoImpl extends BasicSqlSupport implements
        ISysDictionaryDao {

    /**
     * 保存系统字典
     * 
     * @param sysDictionary
     *            系统字典对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final boolean saveSysDictionary(final SysDictionary sysDictionary) {
        return this
                .insert("com.ningpai.system.dao.SysDictionaryDaoImpl.saveSysDictionary",
                        sysDictionary) >= 1 ? true : false;
    }

    /**
     * 修改系统字典
     * 
     * @param sysDictionary
     *            待修改系统字典对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int updateSysDictionary(final SysDictionary sysDictionary) {
        return this
                .update("com.ningpai.system.dao.SysDictionaryDaoImpl.updateSysDictionary",
                        sysDictionary);
    }

    /**
     * 根据系统字典对象的id查询系统字典对象
     * 
     * @param id
     *            系统字典id
     * @return 系统字典对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final SysDictionary getSysDictionaryById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.getSysDictionaryById",
                        id);
    }

    /**
     * 根据系统字典对象的id字符集合查询系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 系统字典对象集合
     * @author system
     * @since 2014-03-20 11:03:23
     */
    public final List<SysDictionary> getSysDictionaryByIds(final String ids) {
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
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.getSysDictionaryByIds",
                        para);
    }

    /**
     * 根据系统字典对象的id字符集合删除系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 删除系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int deleteSysDictionary(final String ids) {
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
                .delete("com.ningpai.system.dao.SysDictionaryDaoImpl.deleteSysDictionary",
                        para);
    }

    /**
     * 更新系统字典对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int updateSysDictionaryFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.SysDictionaryDaoImpl.updateSysDictionaryFieldById",
                        parameter);
    }

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int getSysDictionaryByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.getSysDictionaryByFieldTotal",
                        parameter);
    }

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final List<SysDictionary> getSysDictionaryByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.getSysDictionaryByField",
                        parameter);
    }

    /**
     * 查询系统字典对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final int querySysDictionaryTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.querySysDictionaryTotal",
                        parameter);
    }

    /**
     * 分页查询系统字典对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    public final List<SysDictionary> querySysDictionaryByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.SysDictionaryDaoImpl.querySysDictionaryByPage",
                        parameter);
    }
}
