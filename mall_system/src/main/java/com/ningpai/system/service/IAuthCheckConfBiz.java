package com.ningpai.system.service;

import java.util.List;
import java.util.Map;

import com.ningpai.system.bean.AuthCheckConf;
import com.ningpai.util.PageBean;

/**
 * 验证设置业务接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 09:58:51
 * @version V1.0
 */
public interface IAuthCheckConfBiz {

    /**
     * 保存验证设置
     * 
     * @param authCheckConf
     *            验证设置对象
     * @return 是否保存成功 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    boolean saveAuthCheckConf(AuthCheckConf authCheckConf);

    /**
     * 修改验证设置
     * 
     * @param authCheckConf
     *            待修改验证设置对象
     * @return 更新影响的条数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int updateAuthCheckConf(AuthCheckConf authCheckConf);

    /**
     * 根据验证设置对象的id查询验证设置对象
     * 
     * @param id
     *            验证设置id
     * @return 验证设置对象 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    AuthCheckConf getAuthCheckConfById(int id);

    /**
     * 根据验证设置对象的id字符集合查询验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 验证设置对象集合 @ 自定义异常对象
     * @author system
     * @since 2014-03-25 09:58:51
     */
    List<AuthCheckConf> getAuthCheckConfByIds(String ids);

    /**
     * 根据验证设置对象的id字符集合删除验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 删除验证设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int deleteAuthCheckConf(String ids);

    /**
     * 更新验证设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 更新验证设置对象的数目 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int updateAuthCheckConfFieldById(Map<String, Object> parameter);

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End） 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int getAuthCheckConfByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割） 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @param pageBean
     *            分页对象
     * @return 验证设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    PageBean getAuthCheckConfByField(Map<String, Object> parameter, PageBean pageBean);

    /**
     * 查询验证设置对象信息总数
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int queryAuthCheckConfTotal(Map<String, Object> parameter);

    /**
     * 分页查询验证设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象的集合 @ 自定义异常对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    PageBean queryAuthCheckConfByPage(Map<String, Object> parameter, PageBean pageBean);

}
