/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.service.impl;

import com.ningpai.group.bean.Group;
import com.ningpai.group.bean.GroupPermissions;
import com.ningpai.group.dao.GroupMapper;
import com.ningpai.group.dao.GroupPermissionsMapper;
import com.ningpai.group.service.GroupService;
import com.ningpai.group.vo.GroupVo;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageMapper;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * 小组service接口实现类
 * 
 * @version 2014年5月26日 下午2:13:10
 * @author qiyuanyuan
 */

@Service("GroupService")
public class GroupServiceImpl implements GroupService {

    private GroupMapper groupMapper;

    private GroupPermissionsMapper permissionsMapper;

    private MessageTemplateMapper templateMapper;

    private MessageMapper messageMapper;

    private MessageSiteService messageSiteService;

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService .bean.Group,
     *      com.ningpai.util.PageBean)
     */
    public PageBean selectGroupList(GroupVo groupVo, PageBean pageBean) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        int no = 0;
        try {
            paramMap.put("groupName", groupVo.getGroupName());
            paramMap.put("groupTypeId", groupVo.getGroupTypeId());
            paramMap.put("groupTypeName", groupVo.getGroupTypeName());
            paramMap.put("groupCreateTime", groupVo.getGroupCreateTime());
            paramMap.put("groupCreateTimeTo", groupVo.getGroupCreateTimeTo());
            paramMap.put("groupCheckFlag", groupVo.getGroupCheckFlag());
            paramMap.put("groupStatus", groupVo.getGroupStatus());
            paramMap.put("groupSecret", groupVo.getGroupSecret());
            paramMap.put("groupIsHot", groupVo.getGroupIsHot());
            paramMap.put("groupIsActive", groupVo.getGroupIsActive());
            paramMap.put("customerPower", groupVo.getCustomerPower());
            paramMap.put("groupCreateAuthorName", groupVo.getGroupCreateAuthorName());
            paramMap.put("sort", '3');
            // 设置总行数
            int rows = this.groupMapper.selectGroupSize(paramMap);
            if (rows > 0) {
                pageBean.setRows(rows);
            } else {
                pageBean.setRows(0);
            }
            no = pageBean.getRows() % pageBean.getPageSize() == 0 ? pageBean.getRows() / pageBean.getPageSize() : (pageBean.getRows() / pageBean.getPageSize() + 1);
            no = no == 0 ? 1 : no;

            // 若页码超过最大页码，则显示最后一个
            if (pageBean.getPageNo() >= no) {
                pageBean.setPageNo(no);
                pageBean.setStartRowNum((no - 1) * pageBean.getPageSize());
            }

            paramMap.put("startRowNum", pageBean.getStartRowNum());
            paramMap.put("endRowNum", pageBean.getEndRowNum());
            pageBean.setList(this.groupMapper.selectGroup(paramMap));
        } finally {
            paramMap = null;
        }
        return pageBean;
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService bean.Group)
     */
    @Transactional
    public int updateGroup(Group group) {
        group.setGroupModifyTime(new Date());
        return this.groupMapper.updateByPrimaryKeySelective(group);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#selectByGroupId(java.lang.Long)
     */
    public Group selectByGroupId(Long groupId) {
        return this.groupMapper.selectByPrimaryKey(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#dissolvGroupById(java.lang.Long)
     */
    @Transactional
    public int dissolvGroupById(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.dissolveGroupById(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#passGroup(java.lang.Long)
     */
    @Transactional
    public int passGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        int flag = this.groupMapper.passGroupById(groupId);
        if (flag > 0) {
            // GroupVo groupVo = this.selectGroupVoByGroupId(groupId);
            MessageVo message = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("messageType", '0');
            paramMap.put("messageOperation", '0');
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            // 小组消息
            message.setMessageType("0");
            message.setMessageTempId(template.getMessageTempId());
            message.setMessageTitle(template.getMessageTemplate());
            message.setCustomerId(group.getGroupCreateAuthorId());
            message.setGroupId(groupId);
            message.setMessageDelFlag("0");
            message.setMessageTempId(template.getMessageTempId());
            message.setCustomerRecId(new Long[] { group.getGroupCreateAuthorId() });
            messageSiteService.addMessage(message, null);
        }

        return flag;
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService
     */
    @Transactional
    public int refuseGroup(Long groupId, String reason) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        group.setRefuseReason(reason);
        int flag = this.groupMapper.refuseGroupById(group);
        if (flag > 0) {
            // GroupVo groupVo =this.selectGroupVoByGroupId(groupId);
            MessageVo message = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("messageType", '0');
            paramMap.put("messageOperation", 1);
            MessageTemplate template = templateMapper.selectByType(paramMap); // 小组消息
            message.setMessageType("0");
            message.setMessageTitle(template.getMessageTemplate());
            message.setCustomerId(group.getGroupCreateAuthorId());
            message.setGroupId(groupId);
            message.setMessageDelFlag("0");
            message.setMessageTempId(template.getMessageTempId());
            message.setCustomerRecId(new Long[] { group.getGroupCreateAuthorId() });
            messageSiteService.addMessage(message, null);
        }
        return flag;
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService .Long)
     */
    public GroupVo selectGroupVoByGroupId(Long groupId) {
        return this.groupMapper.selectGroupVoByGroupId(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#activeGroup(java.lang.Long)
     */
    @Transactional
    public int activeGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.activeGroupById(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#commonGroup(java.lang.Long)
     */
    @Transactional
    public int commonGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.commonGroupId(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#hotGroup(java.lang.Long)
     */
    @Transactional
    public int hotGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.hotGroupById(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#cancelHotGroup(java.lang.Long)
     */
    @Transactional
    public int cancelHotGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.cancelHotGroupId(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService#recommendGroup(java.lang.Long)
     */
    @Transactional
    public int recommendGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        group.setGroupRecommendTime(new Date());
        return this.groupMapper.recommendGroupById(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService .Long)
     */
    @Transactional
    public int cancelrecommendGroup(Long groupId) {
        Group group = this.groupMapper.selectByPrimaryKey(groupId);
        group.setGroupModifyTime(new Date());
        return this.groupMapper.cancelRecommendGroupId(groupId);
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService
     */
    public List<GroupVo> groupMemberById() {
        return this.groupMapper.groupMemberById();
    }

    /**
     * 
     * 
     * @see com.ningpai.group.service.GroupService bean.Group,
     *      com.ningpai.group.bean.GroupPermissions)
     */
    @Transactional
    public int updateGroup(Group group, GroupPermissions groupPermissions) {
        group.setGroupModifyTime(new Date());
        this.groupMapper.updateByPrimaryKeySelective(group);
        return this.permissionsMapper.updateByGroupId(groupPermissions);
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

    public MessageTemplateMapper getTemplateMapper() {
        return templateMapper;
    }

    @Resource(name = "MessageTemplateMapper")
    public void setTemplateMapper(MessageTemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    public MessageMapper getMessageMapper() {
        return messageMapper;
    }

    @Resource(name = "MessageMapper")
    public void setMessageMapper(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

}
