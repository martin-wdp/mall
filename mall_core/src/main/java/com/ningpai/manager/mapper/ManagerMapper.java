package com.ningpai.manager.mapper;

import java.util.List;
import java.util.Map;

import com.ningpai.manager.bean.Manager;

/**
 * 管你员Mapper
 * 
 * @author AthrunNatu
 * @since 2013年11月20日下午2:45:15
 */
public interface ManagerMapper {
    /**
     * 根据ID删除管理员信息
     * 
     * @param id
     *            管理员ID
     * @return int 1表示成功 0表示失败
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入管理员信息
     * 
     * @param record
     *            <code>Manager</code>对象
     *            {@link com.ningpai.manager.bean.Manager}
     * @return int 1表示成功 0表示失败
     */
    int insert(Manager record);

    /**
     * 插入管理员信息
     * 
     * @param record
     *            <code>Manager</code>对象
     *            {@link com.ningpai.manager.bean.Manager}
     * @return int 1表示成功 0表示失败
     */
    int insertSelective(Manager record);

    /**
     * 根据ID选取管理员信息
     * 
     * @param id
     *            管理员ID
     * @return <code>Manager</code>对象 {@link com.ningpai.manager.bean.Manager}
     */
    Manager selectByPrimaryKey(Long id);

    /**
     * 根据ID选取管理员信息(获取用户id与用户名)
     * 
     * @param id
     *            管理员ID
     * @return <code>Manager</code>对象 {@link com.ningpai.manager.bean.Manager}
     */
    Manager selectById(Long id);

    /**
     * 修改管理员信息
     * 
     * @param record
     *            <code>Manager</code>对象
     *            {@link com.ningpai.manager.bean.Manager}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKeySelective(Manager record);

    /**
     * 修改管理员信息
     * 
     * @param record
     *            <code>Manager</code>对象
     *            {@link com.ningpai.manager.bean.Manager}
     * @return int 1表示成功 0表示失败
     */
    int updateByPrimaryKey(Manager record);

    /**
     * 通过用户名称选取用户信息
     * 
     * @param name
     *            用户名称
     * @return <code>Manager</code>对象 {@link com.ningpai.manager.bean.Manager}
     *         用户信息
     */
    Manager selectByName(String name);

    /**
     * 通过用户名称选取已冻结用户信息
     *
     * @param name
     *            用户名称
     * @return <code>Manager</code>对象 {@link com.ningpai.manager.bean.Manager}
     *         用户信息
     */
    Manager selectDelManagerByName(String name);

    /**
     * 获取管理员列表
     * 
     * @return java.util.List <code>Manager</code>对象
     *         {@link com.ningpai.manager.bean.Manager}管理员信息
     */
    List<Manager> queryManagerList(Manager manager);

    /**
     * 
     * 分页获取管理员列表
     * 
     * @param paramMap
     *            起始页码 和查询条数
     * @return java.util.List <code>Manager</code>对象
     *         {@link com.ningpai.manager.bean.Manager}管理员信息
     */
    List<Object> selectManagerByLimit(Map<String, Integer> paramMap);

    /**
     * 
     * 通过条件 分页获取管理员列表
     * 
     * @param manager
     *            条件信息
     * @return java.util.List <code>Manager</code>对象
     *         {@link com.ningpai.manager.bean.Manager}管理员信息
     */
    List<Object> selectManagerByManager(Manager manager);

    /**
     * 添加管理员
     * 
     * @param manager
     *            管理员信息
     * @param authorityId
     *            权限编号
     * @return
     */
    int addManager(Manager manager, String authorityId);

    /**
     * 根据ID删除管理员信息
     * 
     * @param parameterIds
     *            管理员ID
     * @return int 1表示成功 0表示失败
     */
    int delectMangerById(Long parameterIds);

    /**
     * 根据Map删除管理员信息
     * 
     * @param paramMap
     *            管理员ID Map
     * @return int 1表示成功 0表示失败
     */
    int delectMangerByIds(Map<String, Object> paramMap);

    /**
     * 根据Map启用管理员信息
     *
     * @param paramMap
     *            管理员ID Map
     * @return int 1表示成功 0表示失败
     */
    int enabledMangerByIds(Map<String, Object> paramMap);

    /**
     * 查询管理员总数
     * 
     * @param manager
     * @return int {@link java.util.Integer}
     */
    int queryManagerCount(Manager manager);

    /**
     * 根据Id查询管理员
     * 
     * @param managerId
     *            管理员编号 {@link Long}
     * @return 管理员 {@link Manager}
     */
    Manager selectCaptcha(Long managerId);

    /**
     * 修改验证码
     * 
     * @param manager
     *            管理员信息 {@link Manager}
     * @return 0失败 1成功
     */
    int updateSmsCaptcha(Manager manager);

    /**
     * 根据用户名验证管理员是否存在
     * 
     * @param name
     *            管理员用户名
     * @return Long 1存在 0不存在
     */
    Long checkexistsByCustName(String name);

    /**
     * 查找管理员
     * 
     * @param paramMap
     *            参数列表
     * @return 管理员 {@link Manager}
     */
    Manager selectCustomerByNamePwd(Map<String, Object> paramMap);

    /**
     * 查询私有云配置账号
     */
    List<Manager> queryCloudManagerList();
}
