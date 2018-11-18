package com.ningpai.third.auth.mapper;

import java.util.Map;
import com.ningpai.customer.bean.Customer;
import com.ningpai.third.auth.bean.ThirdManagerAuthority;

/**
 * <p>第三方 角色权限关系Mapper</p>
 * @author zhanghl
 * @since zhanghl
 * @version 2.0
 */
public interface ThirdManagerAuthorityMapper {
    /**
     * 查询该管理员ID对应的商家ID
     * @param manageId
     * @return
     */
    Long selectCustomerByManagerId(Long manageId);

    /**
     * 根据ID删除角色权限关系
     * @param id 权限关系ID
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入一条权限关系对象
     * @param record 权限关系ID
     * @return
     */
    int insert(ThirdManagerAuthority record);

    /**
     * 插入一条权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int insertSelective(ThirdManagerAuthority record);

    /**
     * 根据ID查询单个的权限关系对象
     * @param id  权限关系对象ID
     * @return
     */
    ThirdManagerAuthority selectByPrimaryKey(Long id);

    /**
     * 修改权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int updateByPrimaryKeySelective(ThirdManagerAuthority record);

    /**
     * 修改权限关系对象
     * @param record 权限关系对象
     * @return
     */
    int updateByPrimaryKey(ThirdManagerAuthority record);

    /**
     * 查询第三方权限编号
     * 
     * @param cust
     *            用户信息 只包含用户编号和商家编号
     * @return Long
     */
    Long selectAuthIdByCust(Customer cust);

    /**
     * 根据员工编号 查询权限ID
     * 
     * @param customerId
     *            员工编号 {@link Long}
     * @return 权限编号 {@link Long}
     */
    Long selectAuthIdByCustId(Long customerId);

    /**
     * 添加员工权限记录
     * 
     * @param paramMap
     *            {@link Map}
     * @return 0 失败 1成功
     */
    int addRecord(Map<String, Object> paramMap);
}
