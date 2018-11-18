package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.OnLineServiceItem;
import com.ningpai.system.dao.IOnLineServiceItemDao;

/**
 * 在线客服项数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-27 17:44:30
 * @version V1.0
 */
@Repository("onLineServiceItemDaoImpl")
public class OnLineServiceItemDaoImpl extends BasicSqlSupport implements
        IOnLineServiceItemDao {

    /**
     * 保存在线客服项
     * 
     * @param onLineServiceItem
     *            在线客服项对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final boolean saveOnLineServiceItem(
            final OnLineServiceItem onLineServiceItem) {
        return this
                .insert("com.ningpai.system.dao.OnLineServiceItemDaoImpl.saveOnLineServiceItem",
                        onLineServiceItem) >= 1 ? true : false;
    }

    /**
     * 修改在线客服项
     * 
     * @param onLineServiceItem
     *            待修改在线客服项对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int updateOnLineServiceItem(
            final OnLineServiceItem onLineServiceItem) {
        return this
                .update("com.ningpai.system.dao.OnLineServiceItemDaoImpl.updateOnLineServiceItem",
                        onLineServiceItem);
    }

    /**
     * 根据在线客服项对象的id查询在线客服项对象
     * 
     * @param id
     *            在线客服项id
     * @return 在线客服项对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final OnLineServiceItem getOnLineServiceItemById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.getOnLineServiceItemById",
                        id);
    }

    /**
     * 根据在线客服项对象的id字符集合查询在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 在线客服项对象集合
     * @author system
     * @since 2014-03-27 17:44:30
     */
    public final List<OnLineServiceItem> getOnLineServiceItemByIds(
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
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.getOnLineServiceItemByIds",
                        para);
    }

    /**
     * 根据在线客服项对象的id字符集合删除在线客服项对象
     * 
     * @param ids
     *            在线客服项id字符集合(如果多个使用,分割)
     * @return 删除在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int deleteOnLineServiceItem(final String ids) {
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
                .delete("com.ningpai.system.dao.OnLineServiceItemDaoImpl.deleteOnLineServiceItem",
                        para);
    }

    /**
     * 更新在线客服项对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新在线客服项对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int updateOnLineServiceItemFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.OnLineServiceItemDaoImpl.updateOnLineServiceItemFieldById",
                        parameter);
    }

    /**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int getOnLineServiceItemByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.getOnLineServiceItemByFieldTotal",
                        parameter);
    }

    /**
     * 根据在线客服项对象的单个字段查询在线客服项对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final List<OnLineServiceItem> getOnLineServiceItemByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.getOnLineServiceItemByField",
                        parameter);
    }

    /**
     * 查询在线客服项对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final int queryOnLineServiceItemTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.queryOnLineServiceItemTotal",
                        parameter);
    }

    /**
     * 分页查询在线客服项对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 在线客服项对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-27 17:44:30
     */
    public final List<OnLineServiceItem> queryOnLineServiceItemByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.queryOnLineServiceItemByPage",
                        parameter);
    }

    /**
     * 根据在线客服id删除在线客服项
     * 
     * @param onLineServiceId
     *            在线客服id
     * @return 删除的个数
     */
    public final int deleteOnLineServiceItem(final int onLineServiceId) {
        return this
                .delete("com.ningpai.system.dao.OnLineServiceItemDaoImpl.deleteOnLineServiceItem2",
                        onLineServiceId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.IOnLineServiceItemDao#selectCountByOnLineService
     * (int)
     */
    @Override
    public int selectCountByOnLineService(int onLineServiceId) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.selectCountByOnLineService",
                        onLineServiceId);
    }

    /*
     * 
     * 
     * @see
     * com.ningpai.system.dao.IOnLineServiceItemDao#delOnLineServiceItem(int)
     */
    @Override
    public int delOnLineServiceItem(int onLineServiceItemId) {
        return this
                .delete("com.ningpai.system.dao.OnLineServiceItemDaoImpl.delOnLineServiceItem",
                        onLineServiceItemId);
    }

    @Override
    public List<OnLineServiceItem> selectItemsByType(int type) {
        return this
                .selectList(
                        "com.ningpai.system.dao.OnLineServiceItemDaoImpl.selectItemsByType",
                        type);
    }
}
