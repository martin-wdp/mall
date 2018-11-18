package com.ningpai.system.service;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.SysErrorPage;
import com.ningpai.util.PageBean;

/**
 * 异常页面设置业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 10:10:44
 * @version V1.0
 */
public interface ISysErrorPageBiz {

    /**
     * 保存异常页面设置
     * 
     * @param sysErrorPage
     *            异常页面设置对象
     * @return 是否保存成功 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    boolean saveSysErrorPage(SysErrorPage sysErrorPage);

    /**
     * 修改异常页面设置
     * 
     * @param sysErrorPage
     *            待修改异常页面设置对象
     * @return 更新影响的条数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    int updateSysErrorPage(SysErrorPage sysErrorPage);

    /**
     * 根据异常页面设置对象的id查询异常页面设置对象
     * 
     * @param id
     *            异常页面设置id
     * @return 异常页面设置对象 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    SysErrorPage getSysErrorPageById(int id);

    /**
     * 根据异常页面设置对象的id字符集合查询异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 异常页面设置对象集合 @ 自定义异常对象
     * @author system
     * @since 2014-03-25 10:10:44
     */
    List<SysErrorPage> getSysErrorPageByIds(String ids);

    /**
     * 根据异常页面设置对象的id字符集合删除异常页面设置对象
     * 
     * @param ids
     *            异常页面设置id字符集合(如果多个使用,分割)
     * @return 删除异常页面设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    int deleteSysErrorPage(String ids);

    /**
     * 更新异常页面设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 更新异常页面设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    int updateSysErrorPageFieldById(Map<String, Object> parameter);

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    int getSysErrorPageByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据异常页面设置对象的单个字段查询异常页面设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 异常页面设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    PageBean getSysErrorPageByField(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询异常页面设置对象信息总数
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    int querySysErrorPageTotal(Map<String, Object> parameter);

    /**
     * 分页查询异常页面设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 异常页面设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 10:10:44
     */
    PageBean querySysErrorPageByPage(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 根据页面名字查询异常页面
     * @param pageName    页面名称
     * @return
     */
    SysErrorPage querySysErrorByPageName(String pageName);
}
