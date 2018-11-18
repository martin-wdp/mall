package com.ningpai.temp.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.temp.bean.SysTemp;

/**
 * 模板设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-29 17:43:53
 * @version V1.0
 */
public interface SysTempDao {

    /**
     * 保存模板设置
     * 
     * @param sysTemp
     *            模板设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    boolean saveSysTemp(SysTemp sysTemp);

    /**
     * 修改模板设置
     * 
     * @param sysTemp
     *            待修改模板设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    int updateSysTemp(SysTemp sysTemp);

    /**
     * 根据模板设置对象的id查询模板设置对象
     * 
     * @param id
     *            模板设置id
     * @return 模板设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    SysTemp getSysTempById(int id);

    /**
     * 根据模板设置对象的id字符集合查询模板设置对象
     * 
     * @param ids
     *            模板设置id字符集合(如果多个使用,分割)
     * @return 模板设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    List<SysTemp> getSysTempByIds(String ids);

    /**
     * 根据模板设置对象的id字符集合删除模板设置对象
     * 
     * @param ids
     *            模板设置id字符集合(如果多个使用,分割)
     * @return 删除模板设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    int deleteSysTemp(String ids);

    /**
     * 更新模板设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新模板设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    int updateSysTempFieldById(Map<String, Object> parameter);

    /**
     * 根据模板设置对象的单个字段查询模板设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    int getSysTempByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据模板设置对象的单个字段查询模板设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    List<SysTemp> getSysTempByField(Map<String, Object> parameter);

    /**
     * 查询模板设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    int querySysTempTotal(Map<String, Object> parameter);

    /**
     * 分页查询模板设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 模板设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-29 17:43:53
     */
    List<SysTemp> querySysTempByPage(Map<String, Object> parameter);

    /**
     * 设置模板为当前使用模板
     * 
     * @param tempId
     * @return
     */
    int setUserd(Long tempId);

    /**
     * 设置除该ID模板外的模板为不使用模板
     * 
     * @param tempId
     * @return
     */
    int removeUserd(Long tempId);
}
