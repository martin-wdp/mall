package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.ShopConf;
import com.ningpai.system.dao.IShopConfDao;

/**
 * 购物设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 17:58:48
 * @version V1.0
 */
@Repository("shopConfDaoImpl")
public class ShopConfDaoImpl extends BasicSqlSupport implements IShopConfDao {

    /**
     * 保存购物设置
     * 
     * @param shopConf
     *            购物设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final boolean saveShopConf(final ShopConf shopConf) {
        return this
                .insert("com.ningpai.system.dao.ShopConfDaoImpl.saveShopConf",
                        shopConf) >= 1 ? true : false;
    }

    /**
     * 修改购物设置
     * 
     * @param shopConf
     *            待修改购物设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int updateShopConf(final ShopConf shopConf) {
        return this.update(
                "com.ningpai.system.dao.ShopConfDaoImpl.updateShopConf",
                shopConf);
    }

    /**
     * 根据购物设置对象的id查询购物设置对象
     * 
     * @param id
     *            购物设置id
     * @return 购物设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final ShopConf getShopConfById(final int id) {
        return this.selectOne(
                "com.ningpai.system.dao.ShopConfDaoImpl.getShopConfById", id);
    }

    /**
     * 根据购物设置对象的id字符集合查询购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 购物设置对象集合
     * @author system
     * @since 2014-03-24 17:58:48
     */
    public final List<ShopConf> getShopConfByIds(final String ids) {
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
                        "com.ningpai.system.dao.ShopConfDaoImpl.getShopConfByIds",
                        para);
    }

    /**
     * 根据购物设置对象的id字符集合删除购物设置对象
     * 
     * @param ids
     *            购物设置id字符集合(如果多个使用,分割)
     * @return 删除购物设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int deleteShopConf(final String ids) {
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
                "com.ningpai.system.dao.ShopConfDaoImpl.deleteShopConf", para);
    }

    /**
     * 更新购物设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新购物设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int updateShopConfFieldById(final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.ShopConfDaoImpl.updateShopConfFieldById",
                        parameter);
    }

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int getShopConfByFieldTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.ShopConfDaoImpl.getShopConfByFieldTotal",
                        parameter);
    }

    /**
     * 根据购物设置对象的单个字段查询购物设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final List<ShopConf> getShopConfByField(
            final Map<String, Object> parameter) {
        return this.selectList(
                "com.ningpai.system.dao.ShopConfDaoImpl.getShopConfByField",
                parameter);
    }

    /**
     * 查询购物设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final int queryShopConfTotal(final Map<String, Object> parameter) {
        return this.selectOne(
                "com.ningpai.system.dao.ShopConfDaoImpl.queryShopConfTotal",
                parameter);
    }

    /**
     * 分页查询购物设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 购物设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 17:58:48
     */
    public final List<ShopConf> queryShopConfByPage(
            final Map<String, Object> parameter) {
        return this.selectList(
                "com.ningpai.system.dao.ShopConfDaoImpl.queryShopConfByPage",
                parameter);
    }
}
