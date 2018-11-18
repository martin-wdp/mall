package com.ningpai.group.service.impl;

import com.ningpai.common.util.ConstantUtil;
import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.GroupBlack;
import com.ningpai.group.bean.GroupImg;
import com.ningpai.group.bean.Mood;
import com.ningpai.group.dao.CustomerReplyMapper;
import com.ningpai.group.dao.GroupBlackMapper;
import com.ningpai.group.dao.GroupCustomerMapper;
import com.ningpai.group.dao.MoodMapper;
import com.ningpai.group.service.CustomerReplySiteService;
import com.ningpai.group.service.GroupImgSiteService;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.service.TopicSiteService;
import com.ningpai.util.PageBean;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户评论接口的实现类
 * 
 * @author qiyuanyuan
 *
 */
@Service("CustomerReplySiteService")
public class CustomerReplySiteServiceImpl implements CustomerReplySiteService {

    private static final Logger LOGGER = Logger.getLogger(CustomerReplySiteServiceImpl.class);

    private static final String REPLYTYPE = "replyType";
    private static final String MESSAGETYPE = "messageType";
    private static final String MESSAGEOPERATION = "messageOperation";

    /**
     * 客户回复DAO
     */
    private CustomerReplyMapper replyMapper;

    /**
     * 小组黑名单DAO
     */
    private GroupBlackMapper blackMapper;

    /**
     * 小组成员信息DAO
     */
    private GroupCustomerMapper customerMapper;

    /**
     * 消息模板接口
     */
    private MessageTemplateMapper messageTemplateMapper;

    /**
     * 前台消息Servcie接口
     */
    private MessageSiteService messageSiteService;

    /**
     * 话题前台Service
     */
    private TopicSiteService topicSiteService;

    /**
     * 小组图片Service接口
     */
    private GroupImgSiteService groupImgSiteService;

    /**
     * 个人中心--个人心情接口
     */
    private MoodMapper moodMapper;

    /**
     * 查询小组相片的评论
     * 
     * @param pb
     * @param groupImg
     * @param ta
     *            评论用户Id
     * @return
     */
    public PageBean groupImgReply(PageBean pb, GroupImg groupImg, Long ta) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupImgId", groupImg.getGroupImgId());
        paramMap.put(REPLYTYPE, "1");
        paramMap.put("ta", ta);
        int rows = replyMapper.groupImgReplyCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(6);
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(replyMapper.groupImgReplyList(paramMap));
        return pb;
    }

    /**
     * 话题评论
     * 
     * @param pb
     *            分页
     * @param topic
     * @param ta
     *            评论用户Id
     * @return
     */
    public PageBean topicReply(PageBean pb, GroupTopic topic, Long ta) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("topicId", topic.getTopicId());
        paramMap.put(REPLYTYPE, "0");
        paramMap.put("ta", ta);
        int rows = replyMapper.groupTopicReplyCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(6);
        paramMap.put("startRowNum", pb.getStartRowNum());
        paramMap.put("endRowNum", pb.getEndRowNum());
        pb.setList(replyMapper.groupTopicReplyList(paramMap));
        return pb;
    }

    /**
     * 发表评论
     * 
     * @param reply
     *            用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param customerId
     *            用户Id {@link java.lang.Long}
     * @param flag
     *            标志回复or评论 {@link java.lang.String}
     * @param reCustomerId
     *            回复对象的id{@link java.lang.Long}
     * @return int
     */
    public int pubGroupImgReply(CustomerReply reply, Long customerId, String flag, Long reCustomerId) {
        reply.setReplyType("1");
        reply.setReplyDelFlag("0");
        reply.setReplyTime(new Date());
        if (reply.getReplyContent() != null && !"".equals(reply.getReplyContent())) {
            try {
                String content = new String(reply.getReplyContent().getBytes("ISO-8859-1"), ConstantUtil.UTF);
                reply.setReplyContent(content);
            } catch (UnsupportedEncodingException e) {
                LOGGER.error("发表评论错误", e);
            }
        }
        GroupImg img = groupImgSiteService.groupImgById(reply.getReplyShipId());
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '2');
        messageVo.setPicId(reply.getReplyShipId());
        MessageTemplate template = null;
        messageVo.setMessageType("4");// 评论
        messageVo.setGroupId(img.getGroupId());
        // 相片回复评论
        if ("0".equals(flag)) {
            if (!customerId.equals(reCustomerId)) {
                paramMap.put(MESSAGEOPERATION, "17");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { reCustomerId });
                messageSiteService.addMessage(messageVo, null);
            }
        } else {
            if (!customerId.equals(img.getCustomerId())) {
                paramMap.put(MESSAGEOPERATION, "16");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { img.getCustomerId() });
                messageSiteService.addMessage(messageVo, null);
            }
        }
        return this.replyMapper.insertSelective(reply);
    }

    /**
     * 根据评论ID删除评论
     * 
     * @param replyId
     *            评论ID{@link java.lang.Long}
     * @return int
     */
    public int delReply(Long replyId) {
        return this.replyMapper.delReply(replyId);
    }

    /**
     * 根据评论Id查询用户评论
     * 
     * @param replyId
     *            评论Id{@link java.lang.Long}
     * @return 对象
     */
    public CustomerReply selectReply(Long replyId) {
        return this.replyMapper.selectReplyById(replyId);
    }

    /**
     * 查询小组相册的最新评论
     * 
     * @param groupId
     *            小组ID{@link java.lang.Long}
     * @return list
     */
    public List<CustomerReply> groupImgLastestReply(Long groupId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupId", groupId);
        paramMap.put("number", 5);
        return this.replyMapper.groupImgLastestReply(paramMap);
    }

    /**
     * 发表话题评论
     * 
     * @param reply
     *            用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param flag
     *            标志回复or评论 {@link java.lang.String}
     * @param reCustomerId
     *            回复对象的id{@link java.lang.Long}
     * @return int
     */
    public int pubGroupTopicReply(CustomerReply reply, Long customerId, String flag, Long reCustomerId) {
        reply.setReplyType("0");
        reply.setReplyDelFlag("0");
        reply.setReplyTime(new Date());
        GroupTopic topic = topicSiteService.selectTopicById(reply.getReplyShipId());
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '1');
        messageVo.setTopicId(reply.getReplyShipId());
        MessageTemplate template = null;
        messageVo.setMessageType("4");// 评论
        messageVo.setGroupId(topic.getGroupId());
        // 相片回复评论
        if ("0".equals(flag)) {
            if (!customerId.equals(reCustomerId)) {
                paramMap.put(MESSAGEOPERATION, "15");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { reCustomerId });
                messageSiteService.addMessage(messageVo, null);
            }
        } else {
            if (!customerId.equals(topic.getCustomerId())) {
                paramMap.put(MESSAGEOPERATION, "14");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { topic.getCustomerId() });
                messageSiteService.addMessage(messageVo, null);
            }
        }
        return this.replyMapper.insertSelective(reply);
    }

    /**
     * 删除并拉黑
     * 
     * @param reply
     *            用户评论{@link com.ningpai.group.bean.CustomerReply}
     * @param groupId
     *            小组Id{@link java.lang.Long}
     * @return int
     */
    public int delAndBlack(CustomerReply reply, Long groupId) {
        delReply(reply.getReplyId());
        GroupBlack black = new GroupBlack();
        black.setCustomerId(reply.getCustomerId());
        black.setGroupId(groupId);
        black.setBlackDelFlag("0");
        return this.blackMapper.insertSelective(black);
    }

    /**
     * 根据类型查询回复数目
     * 
     * @return int
     */
    public int customerReplyCount(String flag) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("replyDelFlag", '0');
        paramMap.put(REPLYTYPE, flag);
        return this.replyMapper.customerReplyCount(paramMap);
    }

    /**
     * 评论心情
     * 
     * @param reply
     * @return
     */
    public int sendmoodreply(CustomerReply reply, Long customerId, String flag, Long reCustomerId) {
        reply.setReplyType("3");
        reply.setReplyDelFlag("0");
        reply.setReplyTime(new Date());
        Mood mood = moodMapper.selectMoodDetail(reply.getReplyShipId());
        MessageVo messageVo = new MessageVo();
        messageVo.setPicId(reply.getReplyShipId());
        MessageTemplate template = null;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '6');
        messageVo.setMessageType("4");// 评论
        if ("0".equals(flag)) {
            if (!customerId.equals(reCustomerId)) {
                paramMap.put(MESSAGEOPERATION, "33");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { reCustomerId });
                messageSiteService.addMessage(messageVo, null);
            }
        } else {
            if (!customerId.equals(mood.getCustomerId())) {
                paramMap.put(MESSAGEOPERATION, "32");
                template = messageTemplateMapper.selectByType(paramMap);
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setCustomerRecId(new Long[] { mood.getCustomerId() });
                messageSiteService.addMessage(messageVo, null);
            }
        }
        return this.replyMapper.insertSelective(reply);

    }

    public CustomerReplyMapper getReplyMapper() {
        return replyMapper;
    }

    @Resource(name = "CustomerReplyMapper")
    public void setReplyMapper(CustomerReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public GroupBlackMapper getBlackMapper() {
        return blackMapper;
    }

    @Resource(name = "GroupBlackMapper")
    public void setBlackMapper(GroupBlackMapper blackMapper) {
        this.blackMapper = blackMapper;
    }

    public GroupCustomerMapper getCustomerMapper() {
        return customerMapper;
    }

    @Resource(name = "GroupCustomerMapper")
    public void setCustomerMapper(GroupCustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public MessageTemplateMapper getMessageTemplateMapper() {
        return messageTemplateMapper;
    }

    @Resource(name = "MessageTemplateMapper")
    public void setMessageTemplateMapper(MessageTemplateMapper messageTemplateMapper) {
        this.messageTemplateMapper = messageTemplateMapper;
    }

    public MessageSiteService getMessageSiteService() {
        return messageSiteService;
    }

    @Resource(name = "MessageSiteService")
    public void setMessageSiteService(MessageSiteService messageSiteService) {
        this.messageSiteService = messageSiteService;
    }

    public TopicSiteService getTopicSiteService() {
        return topicSiteService;
    }

    @Resource(name = "TopicSiteService")
    public void setTopicSiteService(TopicSiteService topicSiteService) {
        this.topicSiteService = topicSiteService;
    }

    public GroupImgSiteService getGroupImgSiteService() {
        return groupImgSiteService;
    }

    @Resource(name = "GroupImgSiteService")
    public void setGroupImgSiteService(GroupImgSiteService groupImgSiteService) {
        this.groupImgSiteService = groupImgSiteService;
    }

    public MoodMapper getMoodMapper() {
        return moodMapper;
    }

    @Resource(name = "MoodMapper")
    public void setMoodMapper(MoodMapper moodMapper) {
        this.moodMapper = moodMapper;
    }

}
