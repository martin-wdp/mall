package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.system.dao.ISysErrorPageDao;

/**
 * 异常页面设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 10:10:44
 * @version V1.0
 */
@Repository("sysErrorPageDaoImpl")
public class SysErrorPageDaoImpl extends BasicSqlSupport implements
        ISysErrorPageDao {

    /**
     * 保存异常页面设置
     * 
     * @param sysErrorPage
     *            异常页面设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final boolean saveSysErrorPage(final SysErrorPage sysErrorPage) {
        return this.insert(
                "com.ningpai.system.dao.SysErrorPageDaoImpl.saveSysErrorPage",
                sysErrorPage) >= 1 ? true : false;
    }

    /**
     * 修改异常页面设置
     * 
     * @param sysErrorPage
     *            待修改异常页面设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int updateSysErrorPage(final SysErrorPage sysErrorPage) {
        return this
                .update("com.ningpai.system.dao.SysErrorPageDaoImpl.updateSysErrorPage",
                        sysErrorPage);
    }

    /**
     * 根据异常页面设置对象的id查询异常页面设置对象
     * 
     * @param id
     *            异常页面设置id
     * @return 异常页面设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final SysErrorPage getSysErrorPageById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.getSysErrorPageById",
                        id);
    }

    /**
     * 根据异常页面设置对象的id字符集合查询异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 异常页面设置对象集合
     * @author system
     * @since 2014-03-25 10:10:44
     */
    public final List<SysErrorPage> getSysErrorPageByIds(final String ids) {
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
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.getSysErrorPageByIds",
                        para);
    }

    /**
     * 根据异常页面设置对象的id字符集合删除异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 删除异常页面设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int deleteSysErrorPage(final String ids) {
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
                .delete("com.ningpai.system.dao.SysErrorPageDaoImpl.deleteSysErrorPage",
                        para);
    }

    /**
     * 更新异常页面设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新异常页面设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int updateSysErrorPageFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.SysErrorPageDaoImpl.updateSysErrorPageFieldById",
                        parameter);
    }

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int getSysErrorPageByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.getSysErrorPageByFieldTotal",
                        parameter);
    }

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final List<SysErrorPage> getSysErrorPageByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.getSysErrorPageByField",
                        parameter);
    }

    /**
     * 查询异常页面设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final int querySysErrorPageTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.querySysErrorPageTotal",
                        parameter);
    }

    /**
     * 分页查询异常页面设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    public final List<SysErrorPage> querySysErrorPageByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.querySysErrorPageByPage",
                        parameter);
    }

    /**
     * 根据页面名字查询异常页面
     * 
     * @param pageName
     *            页面名称
     * @return
     */
    public SysErrorPage querySysErrorByPageName(String pageName) {

        return this
                .selectOne(
                        "com.ningpai.system.dao.SysErrorPageDaoImpl.querySysErrorByPageName",
                        pageName);
    }
}
