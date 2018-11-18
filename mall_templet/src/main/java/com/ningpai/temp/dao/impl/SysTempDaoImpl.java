package com.ningpai.temp.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.temp.bean.SysTemp;
import com.ningpai.temp.dao.SysTempDao;

/**
 * 模板设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-29 17:43:53
 * @version V1.0
 */
@Repository("SysTempDao")
public class SysTempDaoImpl extends BasicSqlSupport implements SysTempDao {

    /**
     * 保存模板设置
     * 
     * @param sysTemp
     *            模板设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final boolean saveSysTemp(final SysTemp sysTemp) {
        return this.insert("com.ningpai.temp.dao.SysTempDaoImpl.saveSysTemp", sysTemp) >= 1 ? true : false;
    }

    /**
     * 修改模板设置
     * 
     * @param sysTemp
     *            待修改模板设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int updateSysTemp(final SysTemp sysTemp) {
        return this.update("com.ningpai.temp.dao.SysTempDaoImpl.updateSysTemp", sysTemp);
    }

    /**
     * 根据模板设置对象的id查询模板设置对象
     * 
     * @param id
     *            模板设置id
     * @return 模板设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final SysTemp getSysTempById(final int id) {
        return this.selectOne("com.ningpai.temp.dao.SysTempDaoImpl.getSysTempById", id);
    }

    /**
     * 根据模板设置对象的id字符集合查询模板设置对象
     * 
     * @param ids
     *            模板设置id字符集合(如果多个使用,分割)
     * @return 模板设置对象集合
     * @author system
     * @since 2014-03-29 17:43:53
     */
    public final List<SysTemp> getSysTempByIds(final String ids) {
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
        return this.selectList("com.ningpai.temp.dao.SysTempDaoImpl.getSysTempByIds", para);
    }

    /**
     * 根据模板设置对象的id字符集合删除模板设置对象
     * 
     * @param ids
     *            模板设置id字符集合(如果多个使用,分割)
     * @return 删除模板设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int deleteSysTemp(final String ids) {
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
        return this.delete("com.ningpai.temp.dao.SysTempDaoImpl.deleteSysTemp", para);
    }

    /**
     * 更新模板设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新模板设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int updateSysTempFieldById(final Map<String, Object> parameter) {
        return this.update("com.ningpai.temp.dao.SysTempDaoImpl.updateSysTempFieldById", parameter);
    }

    /**
     * 根据模板设置对象的单个字段查询模板设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件 则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int getSysTempByFieldTotal(final Map<String, Object> parameter) {
        return this.selectOne("com.ningpai.temp.dao.SysTempDaoImpl.getSysTempByFieldTotal", parameter);
    }

    /**
     * 根据模板设置对象的单个字段查询模板设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final List<SysTemp> getSysTempByField(final Map<String, Object> parameter) {
        return this.selectList("com.ningpai.temp.dao.SysTempDaoImpl.getSysTempByField", parameter);
    }

    /**
     * 查询模板设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final int querySysTempTotal(final Map<String, Object> parameter) {
        return this.selectOne("com.ningpai.temp.dao.SysTempDaoImpl.querySysTempTotal", parameter);
    }

    /**
     * 分页查询模板设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    public final List<SysTemp> querySysTempByPage(final Map<String, Object> parameter) {
        return this.selectList("com.ningpai.temp.dao.SysTempDaoImpl.querySysTempByPage", parameter);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.SysTempDao#setUserd(java.lang.Long)
     */
    @Override
    public int setUserd(Long tempId) {
        return this.update("com.ningpai.temp.dao.SysTempDaoImpl.setUserd", tempId);
    }

    /*
     * 
     * 
     * @see com.ningpai.temp.dao.SysTempDao#removeUserd(java.lang.Long)
     */
    @Override
    public int removeUserd(Long tempId) {
        return this.update("com.ningpai.temp.dao.SysTempDaoImpl.removeUserd", tempId);
    }
}
