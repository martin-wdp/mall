package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.AuthCheckConf;
import com.ningpai.system.dao.IAuthCheckConfDao;

/**
 * 验证设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 09:58:51
 * @version V1.0
 */
@Repository("authCheckConfDaoImpl")
public class AuthCheckConfDaoImpl extends BasicSqlSupport implements
        IAuthCheckConfDao {

    /**
     * 保存验证设置
     * 
     * @param authCheckConf
     *            验证设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final boolean saveAuthCheckConf(final AuthCheckConf authCheckConf) {
        return this
                .insert("com.ningpai.system.dao.AuthCheckConfDaoImpl.saveAuthCheckConf",
                        authCheckConf) >= 1 ? true : false;
    }

    /**
     * 修改验证设置
     * 
     * @param authCheckConf
     *            待修改验证设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final int updateAuthCheckConf(final AuthCheckConf authCheckConf) {
        return this
                .update("com.ningpai.system.dao.AuthCheckConfDaoImpl.updateAuthCheckConf",
                        authCheckConf);
    }

    /**
     * 根据验证设置对象的id查询验证设置对象
     * 
     * @param id
     *            验证设置id
     * @return 验证设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final AuthCheckConf getAuthCheckConfById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.getAuthCheckConfById",
                        id);
    }

    /**
     * 根据验证设置对象的id字符集合查询验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 验证设置对象集合
     * @author system
     * @since 2014-03-25 09:58:51
     */
    public final List<AuthCheckConf> getAuthCheckConfByIds(final String ids) {
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
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.getAuthCheckConfByIds",
                        para);
    }

    /**
     * 根据验证设置对象的id字符集合删除验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 删除验证设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final int deleteAuthCheckConf(final String ids) {
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
                .delete("com.ningpai.system.dao.AuthCheckConfDaoImpl.deleteAuthCheckConf",
                        para);
    }

    /**
     * 更新验证设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新验证设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final int updateAuthCheckConfFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.AuthCheckConfDaoImpl.updateAuthCheckConfFieldById",
                        parameter);
    }

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final int getAuthCheckConfByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.getAuthCheckConfByFieldTotal",
                        parameter);
    }

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final List<AuthCheckConf> getAuthCheckConfByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.getAuthCheckConfByField",
                        parameter);
    }

    /**
     * 查询验证设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final int queryAuthCheckConfTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.queryAuthCheckConfTotal",
                        parameter);
    }

    /**
     * 分页查询验证设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    public final List<AuthCheckConf> queryAuthCheckConfByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.AuthCheckConfDaoImpl.queryAuthCheckConfByPage",
                        parameter);
    }
}
