package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ExpressConf;
import com.ningpai.system.dao.IExpressConfDao;

/**
 * 配送方式设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-26 16:38:32
 * @version V1.0
 */
@Repository("expressConfDaoImpl")
public class ExpressConfDaoImpl extends BasicSqlSupport implements
        IExpressConfDao {

    /**
     * 保存配送方式设置
     * 
     * @param expressConf
     *            配送方式设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final boolean saveExpressConf(final ExpressConf expressConf) {
        return this.insert(
                "com.ningpai.system.dao.ExpressConfDaoImpl.saveExpressConf",
                expressConf) >= 1 ? true : false;
    }

    /**
     * 修改配送方式设置
     * 
     * @param expressConf
     *            待修改配送方式设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int updateExpressConf(final ExpressConf expressConf) {
        return this.update(
                "com.ningpai.system.dao.ExpressConfDaoImpl.updateExpressConf",
                expressConf);
    }

    /**
     * 根据配送方式设置对象的id查询配送方式设置对象
     * 
     * @param id
     *            配送方式设置id
     * @return 配送方式设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final ExpressConf getExpressConfById(final int id) {
        return this.selectOne(
                "com.ningpai.system.dao.ExpressConfDaoImpl.getExpressConfById",
                id);
    }

    /**
     * 根据配送方式设置对象的id字符集合查询配送方式设置对象
     * 
     * @param ids
     *            配送方式设置id字符集合(如果多个使用,分割)
     * @return 配送方式设置对象集合
     * @author system
     * @since 2014-03-26 16:38:32
     */
    public final List<ExpressConf> getExpressConfByIds(final String ids) {
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
                        "com.ningpai.system.dao.ExpressConfDaoImpl.getExpressConfByIds",
                        para);
    }

    /**
     * 根据配送方式设置对象的id字符集合删除配送方式设置对象
     * 
     * @param ids
     *            配送方式设置id字符集合(如果多个使用,分割)
     * @return 删除配送方式设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int deleteExpressConf(final String ids) {
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
        return this.delete(
                "com.ningpai.system.dao.ExpressConfDaoImpl.deleteExpressConf",
                para);
    }

    /**
     * 更新配送方式设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新配送方式设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int updateExpressConfFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.ExpressConfDaoImpl.updateExpressConfFieldById",
                        parameter);
    }

    /**
     * 根据配送方式设置对象的单个字段查询配送方式设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int getExpressConfByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.getExpressConfByFieldTotal",
                        parameter);
    }

    /**
     * 根据配送方式设置对象的单个字段查询配送方式设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final List<ExpressConf> getExpressConfByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.getExpressConfByField",
                        parameter);
    }

    /**
     * 得到启用自提设置
     * 
     * @return
     */
    public final ExpressConf getExpressConfByUsedField() {
        return this
                .selectOne("com.ningpai.system.dao.ExpressConfDaoImpl.getExpressConfByUsedField");
    }

    /**
     * 查询配送方式设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final int queryExpressConfTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.queryExpressConfTotal",
                        parameter);
    }

    /**
     * 分页查询配送方式设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 配送方式设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-26 16:38:32
     */
    public final List<ExpressConf> queryExpressConfByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.queryExpressConfByPage",
                        parameter);
    }

    /*
     * 根据配送方式查询物流公司快递100代码
     * 
     * @see
     * com.ningpai.system.dao.IExpressConfDao#queryKuaidi100CodeByExpressId(
     * java.lang.Long)
     */
    @Override
    public String queryKuaidi100CodeByExpressId(Long expressid) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.queryKuaidi100CodeByExpressId",
                        expressid);
    }

    /*
     * 根据物流公司ID查询配送方式<br/> 用于物流公司禁用时，禁用相应配送方式
     * 
     * @see
     * com.ningpai.system.dao.IExpressConfDao#updateExpressUserdStatusByLogisticsId
     * (java.util.Map)
     */
    @Override
    public int updateExpressUserdStatusByLogisticsId(Map<String, Object> map) {
        return this
                .update("com.ningpai.system.dao.ExpressConfDaoImpl.updateExpressUserdStatusByLogisticsId",
                        map);
    }

    /*
     * 根据物流公司ID查询配送方式的数量<br/> 用于删除、修改物流公司时，进行验证
     * 
     * @see
     * com.ningpai.system.dao.IExpressConfDao#queryExpressCountByLogistics(java
     * .lang.Long)
     */
    @Override
    public int queryExpressCountByLogistics(Long logisticsId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ExpressConfDaoImpl.queryExpressCountByLogistics",
                        logisticsId);
    }
}
