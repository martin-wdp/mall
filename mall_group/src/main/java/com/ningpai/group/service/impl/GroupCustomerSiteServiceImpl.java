package com.ningpai.group.service.impl;

import com.ningpai.group.bean.CommonCustomer;
import com.ningpai.group.bean.GroupBlack;
import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.dao.CustomerCenterMapper;
import com.ningpai.group.dao.GroupBlackMapper;
import com.ningpai.group.dao.GroupCustomerMapper;
import com.ningpai.group.service.GroupCustomerSiteService;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * 小组用户接口的实现类
 *
 * @author qiyuanyuan
 */
@Service("GroupCustomerSiteService")
public class GroupCustomerSiteServiceImpl implements GroupCustomerSiteService {

    private static final String MESSAGETYPE = "messageType";
    private static final String MESSAGEOPERATION = "messageOperation";
    private static final String CUSTOMERID = "customerId";
    private static final String CUSTOMERPOWER = "customerPower";
    private static final String GROUPID = "groupId";

    private MessageTemplateMapper templateMapper;

    private MessageSiteService messageSiteService;

    private GroupCustomerMapper customerMapper;

    private GroupBlackMapper blackMapper;

    private CustomerCenterMapper customerCenterMapper;

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#applyJoin(java.lang.Long,
     *      java.lang.Long, java.lang.String, java.lang.Long, java.lang.String)
     */
    public int applyJoin(Long groupId, Long customerId, String groupCustomerExamine, Long managerId, String flag) {
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if ("1".equals(groupCustomerExamine)) {
            paramMap.put(MESSAGETYPE, '0');
            paramMap.put(MESSAGEOPERATION, '3');

        } else if ("2".equals(groupCustomerExamine)) {
            paramMap.put(MESSAGETYPE, '0');
            paramMap.put(MESSAGEOPERATION, '4');
        }
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setGroupId(groupId);
        messageVo.setCustomerRecId(new Long[] { customerId });
        messageVo.setMessageTitle(template.getMessageTemplate());
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageType("0");
        // messageVo.setMessageAuthorId(managerId);
        return messageSiteService.addMessage(messageVo, managerId);
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService
     */
    public int addGroupCustomer(GroupCustomer customer) {

        GroupCustomer groupCustomer = searchByCustomerId(customer, "1");
        if (groupCustomer != null) {
            return -1;// 已经是小组成员
        } else {
            if (customerIsExixt(customer.getCustomerId(), customer)) {
                if (customerIsBlack(customer.getCustomerId(), customer.getGroupId())) {
                    customer.setDelFlag("0");
                    return this.customerMapper.delGroupcustomer(customer);
                } else {
                    return -2; // 小组成员黑名单
                }
            } else {
                /**
                 * customer.setGroupId(customer.getGroupId());
                 * customer.setCustomerId(customer.getCustomerId());
                 */
                customer.setCustomerPower("0");
                customer.setDelFlag("0");
                customer.setCustomerCreateTime(new Date());
                return this.customerMapper.insertSelective(customer);
            }
        }
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
        } else {
            return false;
        }
    }

    /**
     * 判断用户是否在黑名单中
     *
     * @param customerId
     *            用户id {@link java.lang.Long}
     * @param groupId
     *            小组Id {@link java.lang.Long}
     * @return
     */
    public Boolean customerIsBlack(Long customerId, Long groupId) {
        GroupBlack black = new GroupBlack();
        black.setCustomerId(customerId);
        black.setGroupId(groupId);
        black = blackMapper.selectById(black);
        if (black == null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据用户Id查找用户
     *
     * @param customer
     * @param flag
     * @return
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
     * @see com.ningpai.group.service.GroupCustomerSiteService
     */
    public int addManagerMsg(Long groupId, Long[] customerIds, Long customerId) {
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '0');
        paramMap.put(MESSAGEOPERATION, '7');
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        // messageVo.setMessageAuthorId(customerId);
        messageVo.setMessageTitle("新消息~  小组管理");
        messageVo.setMessageContent(template.getMessageTemplate());
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setGroupId(groupId);
        messageVo.setCustomerRecId(customerIds);
        messageVo.setMessageType("0");
        messageVo.setMessageDelFlag("0");
        return this.messageSiteService.addMessage(messageVo, customerId);
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#myGroupCount(java.lang.Long)
     */
    public int myGroupCount(Long custoemrId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, custoemrId);
        return this.customerMapper.mygroup(paramMap);
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService
     */
    public int managerGroup(MessageVo messageVo, String groupCustomerExamine) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, "0");
        // 2:拒绝
        if ("2".equals(groupCustomerExamine)) {
            paramMap.put(MESSAGEOPERATION, "9");
        } else {
            paramMap.put(MESSAGEOPERATION, "8");
        }
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle(template.getMessageTemplate());
        messageVo.setCustomerRecId(new Long[] { messageVo.getMessageRecCustomerId() });
        messageVo.setMessageType("0");
        messageVo.setMessageDelFlag("0");
        return this.messageSiteService.addMessage(messageVo, messageVo.getMessageAuthorId());
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#addManager(com.ningpai.group.bean.GroupCustomer)
     */
    public int addManager(GroupCustomer customer) {
        // 判断是否是小组管理员
        customer.setDelFlag("0");
        GroupCustomer customer1 = customerMapper.selectByCustomerId(customer);
        if (customer1 != null && "2".equals(customer1.getCustomerPower())) {
            return -1;
        }
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customer.getCustomerId());
        paramMap.put(CUSTOMERPOWER, "1");
        paramMap.put(GROUPID, customer.getGroupId());
        return this.customerMapper.addManager(paramMap);
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService
     */
    public int removeManagementBatch(Long groupId, Long[] customerIds, Long createAuthorId) {
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '0');
        paramMap.put(MESSAGEOPERATION, "10");
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setCustomerRecId(customerIds);
        messageVo.setGroupId(groupId);
        messageVo.setMessageDelFlag("0");
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle("新消息~小组管理");
        messageVo.setMessageContent(template.getMessageTemplate());
        messageVo.setMessageType("0");
        messageSiteService.addMessage(messageVo, createAuthorId);
        Map<String, Object> paramMap2 = new HashMap<String, Object>();
        paramMap2.put(GROUPID, groupId);
        paramMap2.put(CUSTOMERPOWER, "0");
        paramMap2.put("array", customerIds);
        return this.customerMapper.removeManager(paramMap2);
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#transferGroupMsg(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    public int transferGroupMsg(Long groupId, Long customerId, Long createAuthorId) {
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '0');
        paramMap.put(MESSAGEOPERATION, "11");
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setCustomerRecId(new Long[] { customerId });
        messageVo.setGroupId(groupId);
        messageVo.setMessageDelFlag("0");
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle("新消息~  小组转让");
        messageVo.setMessageContent(template.getMessageTemplate());
        messageVo.setMessageType("0");
        return this.messageSiteService.addMessage(messageVo, createAuthorId);

    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#zhuanrGroup(com.ningpai.message.vo.MessageVo,
     *      java.lang.String)
     */
    public int zhuanrGroup(MessageVo messageVo, String groupCustomerExamine) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, "0");
        // 2:拒绝
        if ("2".equals(groupCustomerExamine)) {
            paramMap.put(MESSAGEOPERATION, "13");
        } else {
            paramMap.put(MESSAGEOPERATION, "12");
        }
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle(template.getMessageTemplate());
        messageVo.setCustomerRecId(new Long[] { messageVo.getMessageRecCustomerId() });
        messageVo.setMessageType("0");
        messageVo.setMessageDelFlag("0");
        return this.messageSiteService.addMessage(messageVo, messageVo.getMessageAuthorId());
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#transferGroup(java.lang.Long,
     *      java.lang.Long, java.lang.Long)
     */
    public int transferGroup(Long groupId, Long customerId, Long createAuthorId) {
        int flag = 0;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, createAuthorId);
        paramMap.put(CUSTOMERPOWER, "0");
        paramMap.put(GROUPID, groupId);
        flag = customerMapper.addManager(paramMap);
        if (flag > 0) {
            paramMap = new HashMap<String, Object>();
            paramMap.put(CUSTOMERID, customerId);
            paramMap.put(CUSTOMERPOWER, "2");
            paramMap.put(GROUPID, groupId);
            flag = customerMapper.addManager(paramMap);
        }
        return flag;
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#checkCustomerId(java.lang.Long)
     */
    public int checkCustomerId(Long customerId) {
        CommonCustomer cc = customerCenterMapper.selectCommonCustomer(customerId);
        if (cc != null) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#inviteFriend(java.lang.Long,
     *      java.lang.Long, java.lang.Long[], java.lang.String)
     */
    public int inviteFriend(Long groupId, Long customerId, Long[] customerIds, String content) {
        // int flag = 0;
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '0');
        paramMap.put(MESSAGEOPERATION, "23");
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setCustomerRecId(customerIds);
        messageVo.setGroupId(groupId);
        messageVo.setMessageDelFlag("0");
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle("小组邀请");
        messageVo.setMessageContent(content + template.getMessageTemplate());
        messageVo.setMessageType("0");
        // 判断邀请好友是否已经是小组成员如果是则不发消息
        GroupCustomer groupCustomer = new GroupCustomer();
        groupCustomer.setGroupId(groupId);

        return this.messageSiteService.addMessage(messageVo, customerId);
    }

    /**
     * 加入小组前判断用户之前是否加入过该小组
     *
     * @param customer
     *            小组成员 {@link com.ningpai.group.bean.GroupCustomer}
     * @return boolean
     */
    public Boolean customerIsMember(Long customerId, GroupCustomer customer) {
        GroupCustomer customerNew = customer;
        customerNew.setCustomerId(customerId);
        customerNew = searchByCustomerId(customerNew, "1");
        if (customerNew != null) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#inviteGroupFriend(com.ningpai.message.vo.MessageVo,
     *      java.lang.String)
     */
    public int inviteGroupFriend(MessageVo messageVo, String groupCustomerExamine) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, "0");
        // 2:拒绝
        if ("2".equals(groupCustomerExamine)) {
            paramMap.put(MESSAGEOPERATION, "25");
        } else {
            paramMap.put(MESSAGEOPERATION, "24");
        }
        MessageTemplate template = this.templateMapper.selectByType(paramMap);
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageTitle(template.getMessageTemplate());
        messageVo.setCustomerRecId(new Long[] { messageVo.getMessageRecCustomerId() });
        messageVo.setMessageType("0");
        messageVo.setMessageDelFlag("0");
        return this.messageSiteService.addMessage(messageVo, messageVo.getMessageAuthorId());
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#myCommunityList(com.ningpai.util.PageBean,
     *      java.lang.String, java.lang.Long)
     */
    public PageBean myCommunityList(PageBean pb, String flag, Long customerId) {
        // 分装查询参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        // 默认删除标记
        paramMap.put("topicDelFlag", "0");
        paramMap.put(CUSTOMERID, customerId);
        if ("0".equals(flag)) {
            // 查询总数 我的帖子
            int rows = customerMapper.myCommunityListCount(paramMap);
            if (rows > 0) {
                pb.setRows(rows);
            } else {
                pb.setRows(0);
            }
            // 计算分页

            // 查询条件封装
            paramMap.put("start", pb.getStartRowNum());
            paramMap.put("number", pb.getEndRowNum());
        } else {
            paramMap.put("start", 0);
            paramMap.put("number", 5);

        }
        try {
            // 查询列表页
            pb.setList(customerMapper.myCommunityList(paramMap));
        } finally {
            paramMap = null;
        }
        return pb;
    }

    /**
     * @see com.ningpai.group.service.GroupCustomerSiteService#selectRecommended(java.lang.Long)
     */
    public List<GroupCustomer> selectRecommended(Long customerId) {

        int fsite = 0;
        List<GroupCustomer> clist = new ArrayList<GroupCustomer>();
        List<Long> cIdlist = new ArrayList<Long>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, customerId);
        // 共同好友
        List<GroupCustomer> flist = customerMapper.friendRecommended(paramMap);
        if (flist != null) {
            for (int i = 0; i < flist.size(); i++) {
                GroupCustomer groupCustomer = flist.get(i);
                groupCustomer.setFocusFlag(3);
                clist.add(groupCustomer);
                cIdlist.add(groupCustomer.getCustomerId());
                fsite++;
                if (fsite >= 6) {
                    return clist;
                }
            }
        }
        if (clist.isEmpty()) {
            clist = null;
        }
        if (cIdlist.isEmpty()) {
            cIdlist = null;
        }
        paramMap.put("clist", cIdlist);
        // 共同小组
        List<GroupCustomer> glist = customerMapper.groupRecommended(paramMap);
        if (glist != null) {
            if (clist == null) {
                clist = new ArrayList<GroupCustomer>();
            }
            if (cIdlist == null) {
                cIdlist = new ArrayList<Long>();
            }
            for (int i = 0; i < glist.size(); i++) {
                GroupCustomer groupCustomer = glist.get(i);
                groupCustomer.setFocusFlag(1);
                clist.add(groupCustomer);
                cIdlist.add(groupCustomer.getCustomerId());
                fsite++;
                if (fsite >= 6) {
                    return clist;
                }
            }
        }
        if (cIdlist.isEmpty()) {
            cIdlist = null;
        }
        paramMap.put("clist", cIdlist);
        // 共同城市
        List<GroupCustomer> citylist = customerMapper.cityRecommended(paramMap);
        if (citylist != null) {
            if (clist == null) {
                clist = new ArrayList<GroupCustomer>();
            }
            for (int i = 0; i < citylist.size(); i++) {
                GroupCustomer groupCustomer = citylist.get(i);
                groupCustomer.setFocusFlag(2);
                clist.add(groupCustomer);
                fsite++;
                if (fsite >= 6) {
                    return clist;
                }
            }
        }
        return clist;
    }

    public MessageTemplateMapper getTemplateMapper() {
        return templateMapper;
    }

    @Resource(name = "MessageTemplateMapper")
    public void setTemplateMapper(MessageTemplateMapper templateMapper) {
        this.templateMapper = templateMapper;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

    public GroupCustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "GroupCustomerMapper")
    public void setCustomerMapper(GroupCustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public GroupBlackMapper getBlackMapper() {
        return blackMapper;
    }

    @Resource(name = "GroupBlackMapper")
    public void setBlackMapper(GroupBlackMapper blackMapper) {
        this.blackMapper = blackMapper;
    }

    public CustomerCenterMapper getCustomerCenterMapper() {
        return customerCenterMapper;
    }

    @Resource(name = "CustomerCenterMapper")
    public void setCustomerCenterMapper(CustomerCenterMapper customerCenterMapper) {
        this.customerCenterMapper = customerCenterMapper;
    }

    /**
     * private GroupSiteService groupSiteService;
     * 
     * 
     * public GroupSiteService getGroupSiteService() { return groupSiteService;
     * }
     * 
     * @Resource(name="GroupSiteService") public void
     *                                    setGroupSiteService(GroupSiteService
     *                                    groupSiteService) {
     *                                    this.groupSiteService =
     *                                    groupSiteService; }
     */

}
