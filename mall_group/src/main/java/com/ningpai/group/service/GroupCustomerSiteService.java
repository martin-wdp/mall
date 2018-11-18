package com.ningpai.group.service;

import java.util.List;

import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;

/**
 * 小组用户接口
 * @author qiyuanyuan
 *
 */
public interface GroupCustomerSiteService {
    
    /**
     * 同意or拒绝好友的加入小组申请
     * @param groupId 小组Id{@link java.lang.Long}
     * @param customerId 申请加入用户ID{@link java.lang.Long}
     * @param groupCustomerExamine 同意or拒绝 1：同意 2：拒绝
     * @param managerId 管理员ID{@link java.lang.Long}
     * @param flag
     * @return int
     */
    int applyJoin(Long groupId,Long customerId,String groupCustomerExamine,Long managerId,String flag);

    /**
     * 加入小组
     * @param groupId 小组Id{@link java.lang.Long}
     * @param customerId 申请加入用户ID{@link java.lang.Long}
     * @return
     */
    int addGroupCustomer(GroupCustomer customer);
    
    /**
     * 升级管理员
     * @param groupId 小组ID{@link java.lang.Long}
     * @param customerIds 要升级的管理员ID{@link java.lang.Long}
     * @param customerId 用户Id{@link java.lang.Long}
     * @return int
     */
    int addManagerMsg(Long groupId,Long[] customerIds, Long customerId);
    
    /**
     * 我管理和创建小组的数目
     * @param custoemrId 用户Id{@link java.lang.Long}
     * @return int
     */
    int myGroupCount(Long custoemrId);
    
    /**
     * 管理小组(同意或拒绝)
     * @param messageVo 消息Vo{@link com.ningpai.message.vo.MessageVo}
     * @param groupCustomerExamine 用户同意or拒绝 {@link java.lang.String}
     * @return
     */
    int managerGroup(MessageVo messageVo,String groupCustomerExamine);
    
    /**
     * 成为管理员
     * @param customer 小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    int addManager(GroupCustomer customer);
    
    /**
     * 免去小组管理员
     * @param groupId 小组Id{@link java.lang.Long}
     * @param customerIds 小组管理员Id{@link java.lang.Long}
     * @param createAuthorId 小组组长ID{@link java.lang.Long}
     * @return
     */
    int removeManagementBatch(Long groupId,Long[] customerIds,Long createAuthorId);
    
    /**
     * 转让小组
     * @param groupId 小组Id{@link java.lang.Long}
     * @param customerIds 小组管理员Id{@link java.lang.Long}
     * @param createAuthorId 小组组长ID{@link java.lang.Long}
     * @return
     */
    int transferGroupMsg(Long groupId, Long customerId, Long createAuthorId);
    
    /**
     * 转让小组(同意或拒绝)
     * @param messageVo 小组VO{@link com.ningpai.message.vo.MessageVo}
     * @param groupCustomerExamine 用户同意or拒绝 {@link java.lang.String}
     * @return
     */
    int zhuanrGroup(MessageVo messageVo,String groupCustomerExamine);
    
    /**
     * 转让小组成功
     * @param groupId 小组Id{@link java.lang.Long}
     * @param customerIds 小组管理员Id{@link java.lang.Long}
     * @param createAuthorId 小组组长ID{@link java.lang.Long}
     * @return
     */
    int  transferGroup(Long groupId, Long customerId, Long createAuthorId);
    
    /**
     * 验证此用户是否存在
     * @param customerId 用户Id{@link java.lang.Long}
     * @return
     */
    int checkCustomerId(Long customerId);
    
    /**
     * 邀请好友加入
     * @param groupId 小组Id {@link java.lang.Long}
     * @param customerId 用户Id {@link java.lang.Long}
     * @param customerIds 被邀请的好友Id {@link java.lang.Long}
     * @param content 邀请内容{@link java.lang.String}
     * @return
     */
    int inviteFriend(Long groupId,Long customerId,Long[] customerIds,String content);
    
    /**
     * 邀请好友加入(同意或拒绝)
     * @param messageVo 小组VO{@link com.ningpai.message.vo.MessageVo}
     * @param groupCustomerExamine 用户同意or拒绝 {@link java.lang.String}
     * @return
     */
    int inviteGroupFriend(MessageVo messageVo,String groupCustomerExamine);
    
    /**
     * 同意邀请加入小组
     * @param customer 小组成员 {@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     *//*
    int addGroupMember(GroupCustomer customer);*/
    
    /**
     * 查询我的社区
     * @param pb
     * @param flag
     * @return PageBean
     */
    PageBean myCommunityList(PageBean pb, String flag,Long customerId);
    
    /**
     * 用户推荐
     * @param customerId 用户Id{@link java.lang.Long}
     * @return list
     */
    List<GroupCustomer> selectRecommended(Long customerId);
}
