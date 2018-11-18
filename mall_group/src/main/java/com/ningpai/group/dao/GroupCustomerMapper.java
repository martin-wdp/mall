package com.ningpai.group.dao;

import java.util.List;
import java.util.Map;

import com.ningpai.group.bean.GroupCustomer;

/**
 * 小组成员信息DAO
 * 
 * @author qiyuanyuan
 * 
 */
public interface GroupCustomerMapper {
    /**
     * 根据主键删除
     * 
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    int deleteByPrimaryKey(Long groupCustomerId);

    /**
     * 插入，空属性也会插入
     * 
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    int insert(GroupCustomer groupCustomer);

    /**
     * 插入，空属性不会插入
     * 
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    int insertSelective(GroupCustomer groupCustomer);

    /**
     * 根据主键查询
     * 
     * @param:groupCustomerId 主键{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    GroupCustomer selectByPrimaryKey(Long groupCustomerId);

    /**
     * 根据主键修改，空值条件不会修改成null
     * 
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    int updateByPrimaryKeySelective(GroupCustomer groupCustomer);

    /**
     * 根据主键修改，空值条件会修改成null
     * 
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    int updateByPrimaryKey(GroupCustomer groupCustomer);

    /**
     * 根据用户ID查询小组用户
     * 
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return 对象
     */
    GroupCustomer selectByCustomerId(GroupCustomer customer);

    /**
     * 根据小组ID 查询小组成员
     * 
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return List
     */
    List<Object> selectByGroupId(GroupCustomer customer);

    /**
     * 根据小组ID 查询小组成员
     * 
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return List
     */
    List<GroupCustomer> searchmemByGroupId(GroupCustomer customer);

    /**
     * 根据小组ID 查询小组成员数量
     * 
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    Long selectByGroupIdSize(GroupCustomer customer);

    /**
     * 踢出or解除黑名单小组
     * 
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    int delGroupcustomer(GroupCustomer customer);

    /**
     * 我管理和创建的小组
     * 
     * @param paramMap
     *            查询参数
     * @return int
     */
    int mygroup(Map<String, Object> paramMap);

    /**
     * 小组管理员
     * 
     * @param paramMap
     *            查询参数
     * @return
     */
    int addManager(Map<String, Object> paramMap);

    /**
     * 免去管理员
     * 
     * @param paramMap
     *            参数
     * @return int
     */
    int removeManager(Map<String, Object> paramMap);
    
    /**
     * 查询总数
     * @param paramMap
     * @return int
     */
    int myCommunityListCount(Map<String, Object> paramMap);

    /**
     * 查询列表
     * @param paramMap
     * @return List
     */
    List<Object> myCommunityList(Map<String, Object> paramMap);

    /**
     * 共同小组
     * @param paramMap
     * @return
     */
    List<GroupCustomer> groupRecommended(Map<String, Object> paramMap);
    
    /**
     * 共同城市
     * @param paramMap
     * @return
     */
    List<GroupCustomer> cityRecommended(Map<String, Object> paramMap);
    
    /**
     * 共同好友
     * @param paramMap
     * @return
     */
    List<GroupCustomer> friendRecommended(Map<String, Object> paramMap);
}
