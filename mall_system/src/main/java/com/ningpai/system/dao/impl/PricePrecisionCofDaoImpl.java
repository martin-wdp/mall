package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.PricePrecisionCof;
import com.ningpai.system.dao.IPricePrecisionCofDao;

/**
 * 价格精度设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-20 17:16:10
 * @version V1.0
 */
@Repository("pricePrecisionCofDaoImpl")
public class PricePrecisionCofDaoImpl extends BasicSqlSupport implements
        IPricePrecisionCofDao {

    /**
     * 保存价格精度设置
     * 
     * @param pricePrecisionCof
     *            价格精度设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final boolean savePricePrecisionCof(
            final PricePrecisionCof pricePrecisionCof) {
        return this
                .insert("com.ningpai.system.dao.PricePrecisionCofDaoImpl.savePricePrecisionCof",
                        pricePrecisionCof) >= 1 ? true : false;
    }

    /**
     * 修改价格精度设置
     * 
     * @param pricePrecisionCof
     *            待修改价格精度设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int updatePricePrecisionCof(
            final PricePrecisionCof pricePrecisionCof) {
        return this
                .update("com.ningpai.system.dao.PricePrecisionCofDaoImpl.updatePricePrecisionCof",
                        pricePrecisionCof);
    }

    /**
     * 根据价格精度设置对象的id查询价格精度设置对象
     * 
     * @param id
     *            价格精度设置id
     * @return 价格精度设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final PricePrecisionCof getPricePrecisionCofById(final int id) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.getPricePrecisionCofById",
                        id);
    }

    /**
     * 根据价格精度设置对象的id字符集合查询价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 价格精度设置对象集合
     * @author system
     * @since 2014-03-20 17:16:10
     */
    public final List<PricePrecisionCof> getPricePrecisionCofByIds(
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
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.getPricePrecisionCofByIds",
                        para);
    }

    /**
     * 根据价格精度设置对象的id字符集合删除价格精度设置对象
     * 
     * @param ids
     *            价格精度设置id字符集合(如果多个使用,分割)
     * @return 删除价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int deletePricePrecisionCof(final String ids) {
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
                .delete("com.ningpai.system.dao.PricePrecisionCofDaoImpl.deletePricePrecisionCof",
                        para);
    }

    /**
     * 更新价格精度设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新价格精度设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int updatePricePrecisionCofFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.PricePrecisionCofDaoImpl.updatePricePrecisionCofFieldById",
                        parameter);
    }

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int getPricePrecisionCofByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.getPricePrecisionCofByFieldTotal",
                        parameter);
    }

    /**
     * 根据价格精度设置对象的单个字段查询价格精度设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final List<PricePrecisionCof> getPricePrecisionCofByField(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.getPricePrecisionCofByField",
                        parameter);
    }

    /**
     * 查询价格精度设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final int queryPricePrecisionCofTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.queryPricePrecisionCofTotal",
                        parameter);
    }

    /**
     * 分页查询价格精度设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 价格精度设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-20 17:16:10
     */
    public final List<PricePrecisionCof> queryPricePrecisionCofByPage(
            final Map<String, Object> parameter) {
        return this
                .selectList(
                        "com.ningpai.system.dao.PricePrecisionCofDaoImpl.queryPricePrecisionCofByPage",
                        parameter);
    }
}
