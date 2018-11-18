package com.ningpai.system.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.system.bean.UpyunConf;
import com.ningpai.system.dao.IUpyunConfDao;

/**
 * 又拍云设置数据操作类
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-24 15:44:09
 * @version V1.0
 */
@Repository("upyunConfDaoImpl")
public class UpyunConfDaoImpl extends BasicSqlSupport implements IUpyunConfDao {

    /**
     * 保存又拍云设置
     * 
     * @param upyunConf
     *            又拍云设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final boolean saveUpyunConf(final UpyunConf upyunConf) {
        return this.insert(
                "com.ningpai.system.dao.UpyunConfDaoImpl.saveUpyunConf",
                upyunConf) >= 1 ? true : false;
    }

    /**
     * 修改又拍云设置
     * 
     * @param upyunConf
     *            待修改又拍云设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int updateUpyunConf(final UpyunConf upyunConf) {
        return this.update(
                "com.ningpai.system.dao.UpyunConfDaoImpl.updateUpyunConf",
                upyunConf);
    }

    /**
     * 根据又拍云设置对象的id查询又拍云设置对象
     * 
     * @param id
     *            又拍云设置id
     * @return 又拍云设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final UpyunConf getUpyunConfById(final int id) {
        return this.selectOne(
                "com.ningpai.system.dao.UpyunConfDaoImpl.getUpyunConfById", id);
    }

    /**
     * 根据又拍云设置对象的id字符集合查询又拍云设置对象
     * 
     * @param ids
     *            又拍云设置id字符集合(如果多个使用,分割)
     * @return 又拍云设置对象集合
     * @author system
     * @since 2014-03-24 15:44:09
     */
    public final List<UpyunConf> getUpyunConfByIds(final String ids) {
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
        return this.selectList(
                "com.ningpai.system.dao.UpyunConfDaoImpl.getUpyunConfByIds",
                para);
    }

    /**
     * 根据又拍云设置对象的id字符集合删除又拍云设置对象
     * 
     * @param ids
     *            又拍云设置id字符集合(如果多个使用,分割)
     * @return 删除又拍云设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int deleteUpyunConf(final String ids) {
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
                .delete("com.ningpai.system.dao.UpyunConfDaoImpl.deleteUpyunConf",
                        para);
    }

    /**
     * 更新又拍云设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新又拍云设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int updateUpyunConfFieldById(
            final Map<String, Object> parameter) {
        return this
                .update("com.ningpai.system.dao.UpyunConfDaoImpl.updateUpyunConfFieldById",
                        parameter);
    }

    /**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件
     * 则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int getUpyunConfByFieldTotal(
            final Map<String, Object> parameter) {
        return this
                .selectOne(
                        "com.ningpai.system.dao.UpyunConfDaoImpl.getUpyunConfByFieldTotal",
                        parameter);
    }

    /**
     * 根据又拍云设置对象的单个字段查询又拍云设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final List<UpyunConf> getUpyunConfByField(
            final Map<String, Object> parameter) {
        return this.selectList(
                "com.ningpai.system.dao.UpyunConfDaoImpl.getUpyunConfByField",
                parameter);
    }

    /**
     * 查询又拍云设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final int queryUpyunConfTotal(final Map<String, Object> parameter) {
        return this.selectOne(
                "com.ningpai.system.dao.UpyunConfDaoImpl.queryUpyunConfTotal",
                parameter);
    }

    /**
     * 分页查询又拍云设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param _parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 又拍云设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-24 15:44:09
     */
    public final List<UpyunConf> queryUpyunConfByPage(
            final Map<String, Object> parameter) {
        return this.selectList(
                "com.ningpai.system.dao.UpyunConfDaoImpl.queryUpyunConfByPage",
                parameter);
    }
}
