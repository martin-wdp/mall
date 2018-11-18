package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.OnLineService;
import com.ningpai.system.dao.IOnLineServiceDao;

/**
 * 在线客服数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:40:56
 * @version V1.0
 */
@Repository("onLineServiceDaoImpl")
public class OnLineServiceDaoImpl extends BasicSqlSupport implements
        IOnLineServiceDao {

    /**
     * 保存在线客服
     * 
     * @param onLineService
     *            在线客服对象
     * @return 在线客服记录ID
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int saveOnLineService(final OnLineService onLineService) {
        return this
                .insert("com.ningpai.system.dao.OnLineServiceDaoImpl.saveOnLineService",
                        onLineService) >= 1 ? onLineService
                .getOnLineServiceId() : 0;
    }

    /**
     * 修改在线客服
     * 
     * @param onLineService
     *            待修改在线客服对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int updateOnLineService(final OnLineService onLineService) {
        return this
                .update("com.ningpai.system.dao.OnLineServiceDaoImpl.updateOnLineService",
                        onLineService);
    }

    /**
     * 根据在线客服对象的id查询在线客服对象
     * 
     * @param id
     *            在线客服id
     * @return 在线客服对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final OnLineService getOnLineServiceById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.getOnLineServiceById",
                        id);
    }

    /**
     * 根据在线客服对象的id字符集合查询在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 在线客服对象集合
     * @author system
     * @since 2014-03-27 17:40:56
     */
    public final List<OnLineService> getOnLineServiceByIds(final String ids) {
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
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.getOnLineServiceByIds",
                        para);
    }

    /**
     * 根据在线客服对象的id字符集合删除在线客服对象
     * 
     * @param ids
     *            在线客服id字符集合(如果多个使用,分割)
     * @return 删除在线客服对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int deleteOnLineService(final String ids) {
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
                .delete("com.ningpai.system.dao.OnLineServiceDaoImpl.deleteOnLineService",
                        para);
    }

    /**
     * 更新在线客服对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int updateOnLineServiceFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.OnLineServiceDaoImpl.updateOnLineServiceFieldById",
                        parameter);
    }

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int getOnLineServiceByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.getOnLineServiceByFieldTotal",
                        parameter);
    }

    /**
     * 根据在线客服对象的单个字段查询在线客服对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final List<OnLineService> getOnLineServiceByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.getOnLineServiceByField",
                        parameter);
    }

    /**
     * 查询在线客服对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final int queryOnLineServiceTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.queryOnLineServiceTotal",
                        parameter);
    }

    /**
     * 分页查询在线客服对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:40:56
     */
    public final List<OnLineService> queryOnLineServiceByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.OnLineServiceDaoImpl.queryOnLineServiceByPage",
                        parameter);
    }

    @Override
    public OnLineService selectSetting() {
        return this
                .selectOne("com.ningpai.system.dao.OnLineServiceDaoImpl.selectSetting");
    }
}
