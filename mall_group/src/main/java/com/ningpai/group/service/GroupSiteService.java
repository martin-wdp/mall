/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service;

import com.ningpai.group.bean.*;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.util.PageBean;

import java.util.List;

/**
 * 前台小组service接口
 *@version 2014年6月4日 下午3:57:41
 *@author qiyuanyuan
 */

public interface GroupSiteService {
    /**
     * 创建小组
     * @param group 小组对象{@link com.ningpai.group.bean.Group}
     * @return int
     */
    int saveGroup(Group group,GroupPermissions permissions,String type);
    
    /**
     * 解散小组
     * @param groupId 小组ID{@link java.lang.Long}
     * @return int
     */
    int dissolveGroupById(Long groupId);
    
    /**
     * 修改小组
     * @param group 小组对象{@link com.ningpai.group.bean.Group}
     * @return int
     */
    int  updateGroup(Group group);
    
    /**
     * 根据小组id查找小组
     * @param groupId 小组ID{@link java.lang.Long}
     * @return 对象
     */
    Group findByGroupId(Long groupId);
    
    /**
     * 查询分类小组
     * @return List
     */
    List<GroupVo> findGroupList(GroupVo groupVo,String flag);
    
    /**
     * 根据PageBean 分页查询小组列表
     * @param pb 分页辅助类
     * @param groupVo 小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @param sort 排序 {@link java.lang.String}
     * @return 
     */
    PageBean queryByPageBean(PageBean pb,GroupVo groupVo,String sort);
    
    /**
     * 小组总数
     * @return long
     */
    long groupCount();
    
    /**
     * 小组成员总数
     * @return long
     */
    long groupMember();
    
    /**
     * 查询小组
     * @param groupVo
     * @return List
     */
    List<Object> selectGroup(GroupVo groupVo);
    
    /**
     * 根据用户ID查询小组用户
     * @param customer 小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return 对象
     */
    GroupCustomer searchByCustomerId(GroupCustomer customer,String flag);
    
    /**
     * 加入小组
     * @param customer 小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    int addCustomer(Long customerId,GroupVo groupVo,GroupCustomer customer,CustomerAllInfo info);
    
    
    /**
     * 根据用户ID 查询用户信息
     * @param customerId 用户ID{@link java.lang.Long}
     * @return 对象
     */
    CustomerAllInfo selectById(Long customerId);
    
    /**
     * 退出小组
     * @param customer 小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    int updateCustomer(GroupCustomer customer);
    
    /**
     * 小组成员
     * @param customer
     * @return
     */
    List<GroupCustomer> customers(GroupCustomer customer);
    
    
    /**
     * 小组权限管理
     * @param group 小组对象{@link com.ningpai.group.bean.Group}
     * @param groupPermissions 小组权限对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return
     */
    int updateGroupLimit(Group group,GroupPermissions groupPermissions);
    
    /**
     * 根据小组ID 查询小组成员
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return 分页
     */
    PageBean selectByGroupId(PageBean pb,GroupCustomer customer,String flag);
    
    /**
     * 踢出小组
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    int delGroupcustomer(GroupCustomer customer);
    
    /**
     * 加入黑名单
     * @param black 小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    int addBlack(GroupCustomer customer,GroupBlack black);
    
    /**
     * 黑名单列表
     * @param pb 
     * @param black 小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return PageBean
     */
    PageBean balckList(PageBean pb,GroupBlack black);
    
    /**
     * 解除黑名单
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @param black 小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return int
     */
    int dissolvedBlack(GroupCustomer customer,GroupBlack black);
    
    /**
     * 小组搜索列表（分页）
     * @param pb 分页
     * @param group 小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @return PageBean
     */
    PageBean groupSearchList(PageBean pb,GroupVo group);
    
    /**
     * 小组成员列表(组长or管理员)
     * @param customer
     * @return
     */
    List<GroupCustomer> customers(GroupCustomer customer,String flag);
    
    /**
     * 小组其他成员
     * @param pb 分页
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    PageBean groupMember(PageBean pb,GroupCustomer customer);
    
    /**
     * 前台显示我管理的小组
     * @param groupVo 小组VO {@link com.ningpai.group.vo.GroupVo}
     * @param customerId 用户ID{@link java.lang.Long}
     * @return List
     */
    List<GroupVo> myManagerGroups(Long customerId,GroupVo groupVo);
    
    /**
     * 前台显示我加入的小组
     * @param pb 分页
     * @param groupVo 小组VO {@link com.ningpai.group.vo.GroupVo}
     * @param customerId 用户ID{@link java.lang.Long}
     * @return pb
     */
    PageBean myJoinedGroup(PageBean pb,Long customerId,GroupVo groupVo);
    
    /**
       * 根据分类ID查询小组标签
       * @param groupTypeId 分类ID{@link java.lang.Long}
       * @return list
       */
      List<GroupLabel> groupLabelList(Long groupTypeId);
      
      /**
       * 小组背景
       * @param group
       * @return
       */
      int editGroupBg(GroupVo group);
      
      /**
       * 根据用户Id查询他加入的小组
       * @param customerId 用户ID{@link java.lang.Long}
       * @return list
       */
      List<Group> joinedGroup(Long customerId);
      
      /**
     * 根据PageBean 分页查询小组列表
     * @param pb 分页辅助类
     * @param groupVo 小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @param sort 排序 {@link java.lang.String}
     * @return 
     */
    PageBean labelGroup(PageBean pb,GroupVo groupVo,String sort);
      
}
