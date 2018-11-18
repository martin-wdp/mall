package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.SysDictionary;

/**
 * 系统字典数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 11:03:23
 * @version V1.0
 */
@Repository
public interface ISysDictionaryDao {

    /**
     * 保存系统字典
     * 
     * @param sysDictionary
     *            系统字典对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    boolean saveSysDictionary(SysDictionary sysDictionary);

    /**
     * 修改系统字典
     * 
     * @param sysDictionary
     *            待修改系统字典对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    int updateSysDictionary(SysDictionary sysDictionary);

    /**
     * 根据系统字典对象的id查询系统字典对象
     * 
     * @param id
     *            系统字典id
     * @return 系统字典对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    SysDictionary getSysDictionaryById(int id);

    /**
     * 根据系统字典对象的id字符集合查询系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 系统字典对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    List<SysDictionary> getSysDictionaryByIds(String ids);

    /**
     * 根据系统字典对象的id字符集合删除系统字典对象
     * 
     * @param ids
     *            系统字典id字符集合(如果多个使用,分割)
     * @return 删除系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    int deleteSysDictionary(String ids);

    /**
     * 更新系统字典对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新系统字典对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    int updateSysDictionaryFieldById(Map<String, Object> parameter);

    /**
     * 根据系统字典对象的单个字段查询系统字典对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    int getSysDictionaryByFieldTotal(Map<String, Object> parameter);

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
    List<SysDictionary> getSysDictionaryByField(Map<String, Object> parameter);

    /**
     * 查询系统字典对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    int querySysDictionaryTotal(Map<String, Object> parameter);

    /**
     * 分页查询系统字典对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 系统字典对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 11:03:23
     */
    List<SysDictionary> querySysDictionaryByPage(Map<String, Object> parameter);
}
