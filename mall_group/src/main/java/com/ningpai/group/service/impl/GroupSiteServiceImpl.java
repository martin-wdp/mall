/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service.impl;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.customer.dao.CustomerMapper;
import com.ningpai.group.bean.*;
import com.ningpai.group.dao.*;
import com.ningpai.group.service.GroupSiteService;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.other.bean.CustomerAllInfo;
import com.ningpai.util.PageBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * 前台小组service 接口实现类
 *
 * @author qiyuanyuan
 * @version 2014年6月4日 下午3:58:18
 */

@Service("GroupSiteService")
public class GroupSiteServiceImpl implements GroupSiteService {

    private static final Logger LOGGER = Logger.getLogger(GroupSiteServiceImpl.class);

    private static final String GROUPTYPEID = "groupTypeId";
    private static final String GROUPCHECKFLAG = "groupCheckFlag";
    private static final String GROUPSECRET = "groupSecret";
    private static final String STARTROWNUM = "startRowNum";
    private static final String ENDROWNUM = "endRowNum";

    /**
     * 小组DAO
     */
    private GroupMapper groupMapper;

    /**
     * 小组权限DAO
     */
    private GroupPermissionsMapper permissionsMapper;

    /**
     * 小组成员信息DAO
     */
    private GroupCustomerMapper customerMapper;

    /**
     * 会员底层Mapper
     */
    private CustomerMapper customerMapper2;

    /**
     * 小组黑名单DAO
     */
    private GroupBlackMapper blackMapper;

    private MessageSiteService messageSiteService;

    private MessageTemplateMapper templateMapper;

    private LabelGroupMapper labelGroupMapper;

    /**
     * 创建小组
     *
     * @param group
     *            小组对象{@link com.ningpai.group.bean.Group}
     * @return int
     */
    public int saveGroup(Group group, GroupPermissions permissions, String type) {
        if (group.getGroupName() != null && !"".equals(group.getGroupName())) {
            try {
                String name = new String(group.getGroupName().getBytes("ISO-8859-1"), ConstantUtil.UTF);
                group.setGroupName(name);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("创建小组错误", e);
            }
        }
        if (group.getGroupRemark() != null && !"".equals(group.getGroupRemark())) {
            try {
                String remark = new String(group.getGroupRemark().getBytes("ISO-8859-1"), ConstantUtil.UTF);
                group.setGroupRemark(remark);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("创建小组错误", e);
            }
        }
        group.setGroupCreateTime(new Date());
        group.setGroupModifyTime(new Date());
        group.setGroupCheckFlag("0");
        group.setGroupStatus("0");
        group.setGroupSecret(type);
        group.setGroupDissolve("0");
        group.setGroupIsHot("0");
        group.setGroupIsActive("0");
        group.setGroupLimitMember((long) 2000);
        group.setGroupBackgroundType("0");
        this.groupMapper.insertSelective(group);
        if (group.getGroupLabelIds() != null) {
            for (String groupLabelId : group.getGroupLabelIds()) {
                Long labelId = Long.valueOf(groupLabelId);
                LabelGroup labelGroup = new LabelGroup();
                labelGroup.setGroupId(group.getGroupId());
                labelGroup.setCreateTime(new Date());
                labelGroup.setGroupLabelId(labelId);
                labelGroup.setDelFlag("0");
                labelGroup.setGroupTypeId(group.getGroupTypeId());
                labelGroupMapper.insertSelective(labelGroup);
            }
        }
        GroupCustomer customer = new GroupCustomer();
        customer.setGroupId(group.getGroupId());
        customer.setCustomerId(group.getGroupCreateAuthorId());
        customer.setCustomerPower("2");
        customer.setDelFlag("0");
        this.customerMapper.insertSelective(customer);
        permissions.setGroupId(group.getGroupId());
        permissions.setLimitCreatePicType("0");
        permissions.setLimitCreateTopicType("0");
        permissions.setLimitReplyType("0");
        permissions.setLimitDelTopicType("1");
        permissions.setLimitDelPicType("0");
        permissions.setLimitModifyTopicType("0");
        permissions.setLimitDelFlag("0");
        if ("0".equals(type)) {
            permissions.setLimitReplyDelType("1");
            permissions.setLimitAccessType("0");
            return this.permissionsMapper.insertSelective(permissions);
        } else {
            permissions.setLimitAddType("2");
            permissions.setLimitCondition("0");
            permissions.setLimitAccessType("2");
            return this.permissionsMapper.insertSelective(permissions);
        }

    }

    /**
     * 解散小组
     *
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return int
     */
    public int dissolveGroupById(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.deleteByGroupId(groupId);
    }

    /**
     * 修改小组
     *
     * @param group
     *            小组对象{@link com.ningpai.group.bean.Group}
     * @return int
     */
    public int updateGroup(Group group) {
        group.setGroupModifyTime(new Date());
        return this.groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 根据小组id查找小组
     *
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return 对象
     */
    public Group findByGroupId(Long groupId) {
        return this.groupMapper.selectByPrimaryKey(groupId);
    }

    /**
     * 查询分类小组
     *
     * @return List
     */
    public List<GroupVo> findGroupList(GroupVo groupVo, String flag) {
        groupVo.setGroupSecret("0");
        // 特别推荐小组
        if ("1".equals(flag)) {
            groupVo.setGroupRecommend("1");
            groupVo.setGroupIsHot("");
            groupVo.setGroupIsActive("");
            groupVo.setLimitNum(4);
            return this.groupMapper.findGroupList(groupVo);
        } else if ("2".equals(flag)) { // 热门小组
            groupVo.setGroupIsHot("1");
            groupVo.setGroupRecommend("");
            groupVo.setGroupIsActive("");
            groupVo.setLimitNum(4);
            return this.groupMapper.findGroupList(groupVo);
        } else if ("3".equals(flag)) { // 活跃小组
            groupVo.setGroupIsActive("1");
            groupVo.setGroupIsHot("");
            groupVo.setGroupRecommend("");
            groupVo.setLimitNum(4);
            return this.groupMapper.findGroupList(groupVo);
        } else { // 其他小组
            groupVo.setGroupIsActive("0");
            groupVo.setGroupRecommend("0");
            groupVo.setGroupIsHot("0");
            groupVo.setLimitNum(5);
            return this.groupMapper.findGroupList(groupVo);
        }
    }

    /**
     * 根据PageBean 分页查询小组列表
     *
     * @param pb
     *            分页辅助类
     * @param groupVo
     *            小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @param sort
     *            排序 {@link java.lang.String}
     * @return
     */
    public PageBean queryByPageBean(PageBean pb, GroupVo groupVo, String sort) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPTYPEID, groupVo.getGroupTypeId());
        if (groupVo.getGroupLabelIds() != null) {
            String labelid = "";
            paramMap.put("groupLabelIds", labelid);
        }
        paramMap.put(GROUPCHECKFLAG, "1");
        paramMap.put(GROUPSECRET, "0");
        int rows = (int) this.groupMapper.selectGroupSize(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        paramMap.put("sort", sort);
        // 设置列表
        pb.setList(this.groupMapper.selectGroup(paramMap));
        return pb;
    }

    /**
     * 小组总数
     *
     * @return long
     */
    public long groupCount() {
        return this.groupMapper.groupCount();
    }

    /**
     * 小组成员总数
     *
     * @return long
     */
    public long groupMember() {
        return this.groupMapper.groupMember();
    }

    /**
     * 根据用户ID查询小组用户
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return 对象
     */
    public GroupCustomer searchByCustomerId(GroupCustomer customer, String flag) {
        if ("1".equals(flag)) {
            customer.setDelFlag("0");
        }
        if ("2".equals(flag)) {
            customer.setDelFlag("1");
        }
        return this.customerMapper.selectByCustomerId(customer);
    }

    /**
     * 加入小组
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    public int addCustomer(Long customerId, GroupVo groupVo, GroupCustomer customer, CustomerAllInfo info) {
        // 如果用户之前加入该小组直接改状态 否则新增成员
        int i = 0;
        if ("0".equals(groupVo.getLimitAddType())) {
            if (customerIsExixt(customerId, customer)) {
                if (customerIsBlack(customerId, groupVo.getGroupId())) {
                    customer.setDelFlag("0");
                    customer.setCustomerPower("0");
                    return this.customerMapper.delGroupcustomer(customer);
                } else {
                    return -2;
                }
            } else {
                customer.setGroupId(groupVo.getGroupId());
                customer.setCustomerId(customerId);
                customer.setCustomerPower("0");
                customer.setDelFlag("0");
                customer.setCustomerCreateTime(new Date());
                i = this.customerMapper.insertSelective(customer);
            }
        }
        if ("1".equals(groupVo.getLimitAddType())) {
            // 需要小组管理人员批准后才能加入小组
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("messageType", '0');
            paramMap.put("messageOperation", '2');
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setMessageTitle("小组加入申请");
            messageVo.setMessageType("0");
            messageVo.setCustomerId(customerId);// 申请加入的用户Id
            messageVo.setGroupId(groupVo.getGroupId());
            messageVo.setMessageDelFlag("0");
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageContent(template.getMessageTemplate());
            // 小组管理员
            List<GroupCustomer> customers = customers(customer, "2");
            // 小组组长
            customers.add(customers(customer, "1").get(0));
            Long[] customerRecId = new Long[customers.size()];
            for (int j = 0; j < customers.size(); j++) {
                customerRecId[j] = customers.get(j).getCustomerId();
            }
            messageVo.setCustomerRecId(customerRecId);
            messageSiteService.addMessage(messageVo, null);
            i = -1;
        }
        if ("2".equals(groupVo.getLimitAddType())) {
            // 只有接受组员邀请才能加入小组
            i = 0;
        }
        return i;
    }

    /**
     * 加入小组前判断用户之前是否加入过该小组
     *
     * @param customer
     *            小组成员 {@link com.ningpai.group.bean.GroupCustomer}
     * @return boolean
     */
    public Boolean customerIsExixt(Long customerId, GroupCustomer customer) {
        GroupCustomer customerNew = customer;
        customerNew.setCustomerId(customerId);
        customerNew = searchByCustomerId(customerNew, "2");
        if (customerNew != null) {
            return true;
        }
        return false;
    }

    /**
     * 加入之前判断用户是否在于黑名单中
     *
     * @param customerId
     * @param groupId
     * @return
     */
    public Boolean customerIsBlack(Long customerId, Long groupId) {
        GroupBlack black = new GroupBlack();
        black.setCustomerId(customerId);
        black.setGroupId(groupId);
        black = blackMapper.selectById(black);
        if (black == null) {
            return true;
        }
        return false;
    }

    /**
     * 根据用户ID 查询用户信息
     *
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return 对象
     */
    public CustomerAllInfo selectById(Long customerId) {
        return customerMapper2.selectByPrimaryKey(customerId);
    }

    /**
     * 退出小组
     *
     * @param customer
     *            小组用户{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    public int updateCustomer(GroupCustomer customer) {
        GroupCustomer customerNew = customer;
        customerNew.setDelFlag("0");
        customerNew = customerMapper.selectByCustomerId(customerNew);
        if ("1".equals(customerNew.getCustomerPower())) {
            customerNew.setCustomerPower("0");
        }
        customer.setDelFlag("1");
        return customerMapper.updateByPrimaryKeySelective(customerNew);
    }

    /**
     * 小组权限管理
     *
     * @param group
     *            小组对象{@link com.ningpai.group.bean.Group}
     * @param groupPermissions
     *            小组权限对象{@link com.ningpai.group.bean.GroupPermissions}
     * @return
     */
    public int updateGroupLimit(Group group, GroupPermissions groupPermissions) {
        group.setGroupModifyTime(new Date());
        if ("1".equals(group.getGroupSecret())) {
            groupPermissions.setLimitAddType("2");
        }
        this.groupMapper.updateByPrimaryKeySelective(group);
        return this.permissionsMapper.updateByGroupId(groupPermissions);
    }

    /**
     * 根据小组ID 查询小组成员
     *
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return 分页
     */
    public PageBean selectByGroupId(PageBean pb, GroupCustomer customer, String flag) {
        if ("1".equals(flag)) {
            customer.setCustomerPower("0");
        }
        if ("2".equals(flag)) {
            customer.setCustomerPower("1");
        }
        Long rows = this.customerMapper.selectByGroupIdSize(customer);
        if (rows != null) {
            pb.setRows(rows.intValue());
        } else {
            pb.setRows(0);
        }
        customer.setPageFlag("1");
        pb.setPageSize(20);
        customer.setStartRowNum(pb.getStartRowNum());
        customer.setEndRowNum(pb.getEndRowNum());
        // 设置列表
        pb.setList(this.customerMapper.selectByGroupId(customer));
        return pb;
    }

    /**
     * 踢出小组
     *
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    public int delGroupcustomer(GroupCustomer customer) {
        customer.setDelFlag("1");
        return this.customerMapper.delGroupcustomer(customer);
    }

    /**
     * 加入黑名单
     *
     * @param black
     *            小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return int
     */
    public int addBlack(GroupCustomer customer, GroupBlack black) {
        black.setCustomerId(customer.getCustomerId());
        black.setGroupId(customer.getGroupId());
        black.setBlackDelFlag("0");
        return this.blackMapper.insertSelective(black);
    }

    /**
     * 黑名单列表
     *
     * @param pb
     * @param black
     *            小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return PageBean
     */
    public PageBean balckList(PageBean pb, GroupBlack black) {
        Long rows = this.blackMapper.blackListSize(black);
        if (rows != null) {
            pb.setRows(rows.intValue());
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(20);
        black.setStartRowNum(pb.getStartRowNum());
        black.setEndRowNum(pb.getEndRowNum());
        // 设置列表
        pb.setList(this.blackMapper.blackList(black));
        return pb;
    }

    /**
     * 解除黑名单
     *
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @param black
     *            小组黑名单{@link com.ningpai.group.bean.GroupBlack}
     * @return int
     */
    public int dissolvedBlack(GroupCustomer customer, GroupBlack black) {
        return this.blackMapper.dissolvedBlack(black);
    }

    /**
     * 小组搜索列表（分页）
     *
     * @param pb
     *            分页
     * @param group
     *            小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @return PageBean
     */
    public PageBean groupSearchList(PageBean pb, GroupVo group) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPCHECKFLAG, "1");
        paramMap.put(GROUPSECRET, "0");
        paramMap.put("groupName", group.getGroupName());
        paramMap.put("customerId", group.getCustomerId());
        int rows = (int) this.groupMapper.selectGroupSize(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(9);
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        paramMap.put("sort", "3");
        // 设置列表
        pb.setList(this.groupMapper.selectGroup(paramMap));
        return pb;
    }

    /**
     * 小组成员
     *
     * @param customer
     * @return
     */
    public List<GroupCustomer> customers(GroupCustomer customer) {
        customer.setLimitNum(15);
        return this.customerMapper.searchmemByGroupId(customer);
    }

    /**
     * 小组成员列表(组长or管理员)
     *
     * @param customer
     * @return
     */
    public List<GroupCustomer> customers(GroupCustomer customer, String flag) {
        if ("1".equals(flag)) {
            // 小组组长
            customer.setCustomerPower("2");
            return this.customerMapper.searchmemByGroupId(customer);
        } else {
            // 小组管理员
            customer.setCustomerPower("1");
            return this.customerMapper.searchmemByGroupId(customer);
        }
    }

    /**
     * 小组其他成员
     *
     * @param pb
     *            分页
     * @param customer
     *            小组成员{@link com.ningpai.group.bean.GroupCustomer}
     * @return
     */
    public PageBean groupMember(PageBean pb, GroupCustomer customer) {
        customer.setCustomerPower("0");
        Long rows = this.customerMapper.selectByGroupIdSize(customer);
        if (rows > 0) {
            pb.setRows(rows.intValue());
        } else {
            pb.setRows(0);
        }
        customer.setPageFlag("1");
        pb.setPageSize(15);
        customer.setStartRowNum(pb.getStartRowNum());
        customer.setEndRowNum(pb.getEndRowNum());
        pb.setList(customerMapper.selectByGroupId(customer));
        return pb;
    }

    /**
     * 前台显示我管理的小组
     *
     * @param groupVo
     *            小组VO {@link com.ningpai.group.vo.GroupVo}
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return List
     */
    public List<GroupVo> myManagerGroups(Long customerId, GroupVo groupVo) {
        groupVo.setCustomerId(customerId);
        return this.groupMapper.myManagerGroupList(groupVo);
    }

    /**
     * 前台显示我加入的小组
     *
     * @param pb
     *            分页
     * @param group
     *            小组VO {@link com.ningpai.group.vo.GroupVo}
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return pb
     */
    public PageBean myJoinedGroup(PageBean pb, Long customerId, GroupVo group) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", customerId);
        int rows = this.groupMapper.myJoinedGroupCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(27);
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        pb.setList(groupMapper.myJoinedGroupList(paramMap));
        return pb;
    }

    /**
     * 根据分类ID查询小组标签
     *
     * @param groupTypeId
     *            分类ID{@link java.lang.Long}
     * @return list
     */
    public List<GroupLabel> groupLabelList(Long groupTypeId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPTYPEID, groupTypeId);
        return this.groupMapper.groupLabelList(paramMap);
    }

    /**
     * 小组背景
     *
     * @param group
     * @return
     */
    public int editGroupBg(GroupVo group) {
        return this.groupMapper.editGroupbg(group);
    }

    /**
     * 查询小组
     *
     * @param groupVo
     * @return List
     */
    public List<Object> selectGroup(GroupVo groupVo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupId", groupVo.getGroupId());
        paramMap.put(GROUPCHECKFLAG, '1');
        paramMap.put(STARTROWNUM, 0);
        paramMap.put(ENDROWNUM, 10);
        return groupMapper.selectGroup(paramMap);
    }

    /**
     * 根据用户Id查询他加入的小组
     *
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return list
     */
    public List<Group> joinedGroup(Long customerId) {
        return this.groupMapper.joinedGroup(customerId);
    }

    /**
     * 根据PageBean 分页查询小组列表
     *
     * @param pb
     *            分页辅助类
     * @param groupVo
     *            小组Vo {@link com.ningpai.group.vo.GroupVo}
     * @param sort
     *            排序 {@link java.lang.String}
     * @return
     */
    public PageBean labelGroup(PageBean pb, GroupVo groupVo, String sort) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPTYPEID, groupVo.getGroupTypeId());
        if (groupVo.getGroupLabelIds() != null && !"".equals(groupVo.getGroupLabelIds())) {
            List<Long> s = new ArrayList<Long>();
            for (String v : groupVo.getGroupLabelIds().split(",")) {
                s.add(Long.valueOf(v));
            }
            paramMap.put("groupLabelIds", s);
        }
        paramMap.put(GROUPCHECKFLAG, "1");
        paramMap.put(GROUPSECRET, "0");
        int rows = (int) this.groupMapper.labelGroupCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put(STARTROWNUM, pb.getStartRowNum());
        paramMap.put(ENDROWNUM, pb.getEndRowNum());
        paramMap.put("sort", sort);
        // 设置列表
        pb.setList(this.groupMapper.labelGroup(paramMap));
        return pb;
    }

    public GroupMapper getGroupMapper() {
        return groupMapper;
    }

    @Resource(name = "GroupMapper")
    public void setGroupMapper(GroupMapper groupMapper) {
        this.groupMapper = groupMapper;
    }

    public GroupPermissionsMapper getPermissionsMapper() {
        return permissionsMapper;
    }

    @Resource(name = "GroupPermissionsMapper")
    public void setPermissionsMapper(GroupPermissionsMapper permissionsMapper) {
        this.permissionsMapper = permissionsMapper;
    }

    public GroupCustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "GroupCustomerMapper")
    public void setCustomerMapper(GroupCustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public CustomerMapper getCustomerMapper2() {
        return customerMapper2;
    }

    @Resource(name = "customerMapper")
    public void setCustomerMapper2(CustomerMapper customerMapper2) {
        this.customerMapper2 = customerMapper2;
    }

    public GroupBlackMapper getBlackMapper() {
        return blackMapper;
    }

    @Resource(name = "GroupBlackMapper")
    public void setBlackMapper(GroupBlackMapper blackMapper) {
        this.blackMapper = blackMapper;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

    public MessageTemplateMapper getTemplateMapper() {
        return templateMapper;
    }

    @Resource(name = "MessageTemplateMapper")
    public void setTemplateMapper(MessageTemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    public LabelGroupMapper getLabelGroupMapper() {
        return labelGroupMapper;
    }

    @Resource(name = "LabelGroupMapper")
    public void setLabelGroupMapper(LabelGroupMapper labelGroupMapper) {
        this.labelGroupMapper = labelGroupMapper;
    }

}
