package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.AreaPackage;

/**
 * 地区设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
@Repository
public interface IAreaPackageDao {

    /**
     * 保存地区设置
     * 
     * @param areaPackage
     *            地区设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    boolean saveAreaPackage(AreaPackage areaPackage);

    /**
     * 修改地区设置
     * 
     * @param areaPackage
     *            待修改地区设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int updateAreaPackage(AreaPackage areaPackage);

    /**
     * 根据地区设置对象的id查询地区设置对象
     * 
     * @param id
     *            地区设置id
     * @return 地区设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    AreaPackage getAreaPackageById(int id);

    /**
     * 根据地区设置对象的id字符集合查询地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 地区设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    List<AreaPackage> getAreaPackageByIds(String ids);

    /**
     * 根据地区设置对象的id字符集合删除地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 删除地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int deleteAreaPackage(String ids);

    /**
     * 更新地区设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新地区设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int updateAreaPackageFieldById(Map<String, Object> parameter);

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int getAreaPackageByFieldTotal(Map<String, Object> parameter);

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
    List<AreaPackage> getAreaPackageByField(Map<String, Object> parameter);

    /**
     * 查询地区设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int queryAreaPackageTotal(Map<String, Object> parameter);

    /**
     * 分页查询地区设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    List<AreaPackage> queryAreaPackageByPage(Map<String, Object> parameter);

    /**
     * 将所有记录的默认状态改为非默认
     */
    void changeAllDefaultStatusToNot();
}
