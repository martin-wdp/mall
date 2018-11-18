package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.AreaPackage;
import com.ningpai.system.dao.IAreaPackageDao;

/**
 * 地区设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
@Repository("areaPackageDaoImpl")
public class AreaPackageDaoImpl extends BasicSqlSupport implements
        IAreaPackageDao {

    /**
     * 保存地区设置
     * 
     * @param areaPackage
     *            地区设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final boolean saveAreaPackage(final AreaPackage areaPackage) {
        return this.insert(
                "com.ningpai.system.dao.AreaPackageDaoImpl.saveAreaPackage",
                areaPackage) >= 1 ? true : false;
    }

    /**
     * 修改地区设置
     * 
     * @param areaPackage
     *            待修改地区设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final int updateAreaPackage(final AreaPackage areaPackage) {
        return this.update(
                "com.ningpai.system.dao.AreaPackageDaoImpl.updateAreaPackage",
                areaPackage);
    }

    /**
     * 根据地区设置对象的id查询地区设置对象
     * 
     * @param id
     *            地区设置id
     * @return 地区设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final AreaPackage getAreaPackageById(final int id) {
        return this.selectOne(
                "com.ningpai.system.dao.AreaPackageDaoImpl.getAreaPackageById",
                id);
    }

    /**
     * 根据地区设置对象的id字符集合查询地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 地区设置对象集合
     * @author system
     * @since 2014-03-25 14:04:12
     */
    public final List<AreaPackage> getAreaPackageByIds(final String ids) {
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
                        "com.ningpai.system.dao.AreaPackageDaoImpl.getAreaPackageByIds",
                        para);
    }

    /**
     * 根据地区设置对象的id字符集合删除地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 删除地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final int deleteAreaPackage(final String ids) {
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
                "com.ningpai.system.dao.AreaPackageDaoImpl.deleteAreaPackage",
                para);
    }

    /**
     * 更新地区设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final int updateAreaPackageFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.AreaPackageDaoImpl.updateAreaPackageFieldById",
                        parameter);
    }

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final int getAreaPackageByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AreaPackageDaoImpl.getAreaPackageByFieldTotal",
                        parameter);
    }

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final List<AreaPackage> getAreaPackageByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.AreaPackageDaoImpl.getAreaPackageByField",
                        parameter);
    }

    /**
     * 查询地区设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final int queryAreaPackageTotal(final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.AreaPackageDaoImpl.queryAreaPackageTotal",
                        parameter);
    }

    /**
     * 分页查询地区设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    public final List<AreaPackage> queryAreaPackageByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.AreaPackageDaoImpl.queryAreaPackageByPage",
                        parameter);
    }

    /**
     * 将所有记录的默认状态改为非默认
     */
    @Override
    public void changeAllDefaultStatusToNot() {
        update("com.ningpai.system.dao.AreaPackageDaoImpl.changeAllDefaultStatusToNot");
    }
}
