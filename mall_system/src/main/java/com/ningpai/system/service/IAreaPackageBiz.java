package com.ningpai.system.service;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.AreaPackage;
import com.ningpai.util.PageBean;

/**
 * 地区设置业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 14:04:12
 * @version V1.0
 */
public interface IAreaPackageBiz {

    /**
     * 保存地区设置
     * 
     * @param areaPackage
     *            地区设置对象
     * @return 是否保存成功 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    boolean saveAreaPackage(AreaPackage areaPackage);

    /**
     * 修改地区设置
     * 
     * @param areaPackage
     *            待修改地区设置对象
     * @return 更新影响的条数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int updateAreaPackage(AreaPackage areaPackage);

    /**
     * 根据地区设置对象的id查询地区设置对象
     * 
     * @param id
     *            地区设置id
     * @return 地区设置对象 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    AreaPackage getAreaPackageById(int id);

    /**
     * 根据地区设置对象的id字符集合查询地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 地区设置对象集合 @ 自定义异常对象
     * @author system
     * @since 2014-03-25 14:04:12
     */
    List<AreaPackage> getAreaPackageByIds(String ids);

    /**
     * 根据地区设置对象的id字符集合删除地区设置对象
     * 
     * @param ids
     *            地区设置id字符集合(如果多个使用,分割)
     * @return 删除地区设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int deleteAreaPackage(String ids);

    /**
     * 更新地区设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 更新地区设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int updateAreaPackageFieldById(Map<String, Object> parameter);

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int getAreaPackageByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据地区设置对象的单个字段查询地区设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 地区设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    PageBean getAreaPackageByField(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询地区设置对象信息总数
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    int queryAreaPackageTotal(Map<String, Object> parameter);

    /**
     * 分页查询地区设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 地区设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 14:04:12
     */
    PageBean queryAreaPackageByPage(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 根据主键删除地区包
     * @param areaPackageId 地区包id
     */
    void deleteById(Integer areaPackageId);
}
