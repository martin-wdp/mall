package com.ningpai.system.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.system.bean.AuthCheckConf;

/**
 * 验证设置数据操作接口
 * 
 * @Description:
 * @author NINGPAI_xiaomm
 * @since 2014-03-25 09:58:51
 * @version V1.0
 */
@Repository
public interface IAuthCheckConfDao {

    /**
     * 保存验证设置
     * 
     * @param authCheckConf
     *            验证设置对象
     * @return 是否保存成功
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    boolean saveAuthCheckConf(AuthCheckConf authCheckConf);

    /**
     * 修改验证设置
     * 
     * @param authCheckConf
     *            待修改验证设置对象
     * @return 更新影响的条数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int updateAuthCheckConf(AuthCheckConf authCheckConf);

    /**
     * 根据验证设置对象的id查询验证设置对象
     * 
     * @param id
     *            验证设置id
     * @return 验证设置对象
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    AuthCheckConf getAuthCheckConfById(int id);

    /**
     * 根据验证设置对象的id字符集合查询验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 验证设置对象集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    List<AuthCheckConf> getAuthCheckConfByIds(String ids);

    /**
     * 根据验证设置对象的id字符集合删除验证设置对象
     * 
     * @param ids
     *            验证设置id字符集合(如果多个使用,分割)
     * @return 删除验证设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int deleteAuthCheckConf(String ids);

    /**
     * 更新验证设置对象的单个字段 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 更新验证设置对象的数目
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int updateAuthCheckConfFieldById(Map<String, Object> parameter);

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息总数 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int getAuthCheckConfByFieldTotal(Map<String, Object> parameter);

    /**
     * 根据验证设置对象的单个字段查询验证设置对象信息 其中要包含ids键，保存更新对象的id（多个id以，号分割）
     * 如果查询中有时间条件，则字段名格式为：开始时间（字段名Start），结束时间（字段名End）
     * 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    List<AuthCheckConf> getAuthCheckConfByField(Map<String, Object> parameter);

    /**
     * 查询验证设置对象信息总数 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象信息总数
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    int queryAuthCheckConfTotal(Map<String, Object> parameter);

    /**
     * 分页查询验证设置对象信息 如果需要分页查询，开始为：startRowNum，结束为：endRowNum
     * 
     * @param parameter
     *            更新的参数Map(key: 字段名 value: 字段值)
     * @return 验证设置对象的集合
     * @author NINGPAI_xiaomm
     * @since 2014-03-25 09:58:51
     */
    List<AuthCheckConf> queryAuthCheckConfByPage(Map<String, Object> parameter);
}
