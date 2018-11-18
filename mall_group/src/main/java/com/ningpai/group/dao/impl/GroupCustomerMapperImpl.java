/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.dao.GroupCustomerMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 小组用户信息实现
 * 
 * @version 2014年5月27日 下午2:33:19
 * @author qiyuanyuan
 */

@Repository("GroupCustomerMapper")
public class GroupCustomerMapperImpl extends BasicSqlSupport implements GroupCustomerMapper {

    /**
     * 根据主键删除
     *
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public int deleteByPrimaryKey(Long groupCustomerId) {
        return this.delete("com.ningpai.group.mapper.GroupCustomerMapper.deleteByPrimaryKey", groupCustomerId);
    }

    /**
     * 插入，空属性也会插入
     *
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public int insert(GroupCustomer groupCustomer) {
        return this.insert("com.ningpai.group.mapper.GroupCustomerMapper.insert", groupCustomer);
    }

    /**
     * 插入，空属性不会插入
     *
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:删除个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public int insertSelective(GroupCustomer groupCustomer) {
        return this.insert("com.ningpai.group.mapper.GroupCustomerMapper.insertSelective", groupCustomer);
    }


    /**
     * 根据主键查询
     *
     * @param:groupCustomerId 主键{@link java.lang.Long}
     * @return:对象
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public GroupCustomer selectByPrimaryKey(Long groupCustomerId) {
        return this.selectOne("com.ningpai.group.mapper.GroupCustomerMapper.selectByPrimaryKey", groupCustomerId);
    }


    /**
     * 根据主键修改，空值条件不会修改成null
     *
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public int updateByPrimaryKeySelective(GroupCustomer groupCustomer) {
        return this.update("com.ningpai.group.mapper.GroupCustomerMapper.updateByPrimaryKeySelective", groupCustomer);
    }

    /**
     * 根据主键修改，空值条件会修改成null
     *
     * @param:groupCustomer 对象{@link com.ningpai.group.bean.GroupCustomer}
     * @return:成功修改个数
     * @ibatorgenerated 2014-05-26 16:29:39
     */
    public int updateByPrimaryKey(GroupCustomer groupCustomer) {
        return this.update("com.ningpai.group.mapper.GroupCustomerMapper.updateByPrimaryKey", groupCustomer);
    }

    /**
     * 根据用户ID查询小组用户
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return 对象
     */
    public GroupCustomer selectByCustomerId(GroupCustomer customer) {
        return this.selectOne("com.ningpai.group.mapper.GroupCustomerMapper.searchByCustomerId", customer);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.dao.GroupCustomerMapper
     */
    public List<GroupCustomer> selectByGroupId(Long groupId) {
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.searchByGroupId", groupId);
    }

    /**
     * 根据小组ID 查询小组成员
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return List
     */
    public List<Object> selectByGroupId(GroupCustomer customer) {
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.searchByGroupId", customer);
    }

    /**
     * 根据小组ID 查询小组成员数量
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    public Long selectByGroupIdSize(GroupCustomer customer) {
        return this.selectOne("com.ningpai.group.mapper.GroupCustomerMapper.searchByGroupIdSize", customer);
    }

    /**
     * 踢出or解除黑名单小组
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    public int delGroupcustomer(GroupCustomer customer) {
        return this.update("com.ningpai.group.mapper.GroupCustomerMapper.delGroupcustomer", customer);
    }

    /**
     * 根据小组ID 查询小组成员
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return List
     */
    public List<GroupCustomer> searchmemByGroupId(GroupCustomer customer) {
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.searchmemByGroupId", customer);
    }

    /**
     * 我管理和创建的小组
     *
     * @param paramMap
     *            查询参数
     * @return int
     */
    public int mygroup(Map<String, Object> paramMap) {
        return this.selectOne("com.ningpai.group.mapper.GroupCustomerMapper.mygroup", paramMap);
    }

    /**
     * 小组管理员
     *
     * @param paramMap
     *            查询参数
     * @return
     */
    public int addManager(Map<String, Object> paramMap) {
        return this.update("com.ningpai.group.mapper.GroupCustomerMapper.manager", paramMap);
    }

    /**
     * 免去管理员
     *
     * @param paramMap
     *            参数
     * @return int
     */
    public int removeManager(Map<String, Object> paramMap) {
        return this.update("com.ningpai.group.mapper.GroupCustomerMapper.removemanager", paramMap);
    }

    /**
     * 查询总数
     * @param paramMap
     * @return int
     */
    public int myCommunityListCount(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.topic.mapper.GroupTopicMapper.myCommunityListCount",paramMap);
    }

    /**
     * 查询列表
     * @param paramMap
     * @return List
     */
    public List<Object> myCommunityList(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.topic.mapper.GroupTopicMapper.myCommunityList",paramMap);
    }

    /**
     * 共同小组
     * @param paramMap
     * @return
     */
    public List<GroupCustomer> groupRecommended(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.groupRecommended", paramMap);
    }

    /**
     * 共同城市
     * @param paramMap
     * @return
     */
    public List<GroupCustomer> cityRecommended(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.cityRecommended", paramMap);
    }

    /**
     * 共同好友
     * @param paramMap
     * @return
     */
    public List<GroupCustomer> friendRecommended(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.group.mapper.GroupCustomerMapper.friendRecommended", paramMap);
    }

}
