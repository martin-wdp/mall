package com.ningpai.topic.service.impl;

import com.ningpai.customer.service.CustomerPointServiceMapper;
import com.ningpai.group.bean.GroupCustomer;
import com.ningpai.group.dao.CustomerReplyMapper;
import com.ningpai.group.service.GroupSiteService;
import com.ningpai.message.bean.MessageTemplate;
import com.ningpai.message.dao.MessageTemplateMapper;
import com.ningpai.message.service.MessageSiteService;
import com.ningpai.message.vo.MessageVo;
import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.bean.GroupTopicImg;
import com.ningpai.topic.bean.TopicRecommend;
import com.ningpai.topic.bean.TopicVo;
import com.ningpai.topic.dao.GroupTopicImgMapper;
import com.ningpai.topic.dao.GroupTopicMapper;
import com.ningpai.topic.dao.TopicRecommendMapper;
import com.ningpai.topic.service.RecommendService;
import com.ningpai.topic.service.TopicSiteService;
import com.ningpai.util.PageBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 话题前台Service实现类
 *
 * @author qiyuanyuan
 */
@Service("TopicSiteService")
public class TopicSiteServiceImpl implements TopicSiteService {

    private static final String CUSTOMERID = "customerId";
    private static final String GROUPID = "groupId";
    private static final String TOPICTITLE = "topicTitle";
    private static final String TOPICTOPVIEW = "topicTopView";
    private static final String TOPICDELFLAG = "topicDelFlag";
    private static final String TOPICFEVER = "topicFever";
    private static final String TOPICESSENCE = "topicEssence";
    private static final String STARTRUNNUM = "startRunNum";
    private static final String ENDRUMNUM = "endRumNum";
    private static final String MESSAGETYPE = "messageType";
    private static final String MESSAGEOPERATION = "messageOperation";

    /**
     * 小组话题DAO
     */
    private GroupTopicMapper topicMapper;

    /**
     * 话题图片DAO
     */
    private GroupTopicImgMapper topicImgMapper;

    /**
     * 客户回复DAO
     */
    private CustomerReplyMapper replyMapper;

    /**
     * 话题推荐接口
     */
    private TopicRecommendMapper recommendMapper;

    /**
     * 消息模板接口
     */
    private MessageTemplateMapper templateMapper;

    /**
     * 前台消息Servcie接口
     */
    private MessageSiteService messageSiteService;

    /**
     * 会员积分接口
     */
    private CustomerPointServiceMapper customerPointServiceMapper;

    /**
     * 点赞统计service
     */
    private RecommendService recommendService;

    /**
     * 前台小组service接口
     */
    private GroupSiteService groupService;

    /**
     * 添加话题
     *
     * @param topicVo
     * @return String
     */
    @Transactional
    public String saveTopic(TopicVo topicVo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(CUSTOMERID, topicVo.getCustomerId());
        paramMap.put(GROUPID, topicVo.getGroupId());
        paramMap.put("sysday", "sysday");
        // 用户在该小组发表的话题总数
        // int pubCount = topicMapper.pubTopicCount(paramMap);
        // 判断是否已经发表过这个标题的话题
        paramMap = new HashMap<String, Object>();
        paramMap.put(TOPICTITLE, topicVo.getTopicTitle());
        paramMap.put(CUSTOMERID, topicVo.getCustomerId());
        paramMap.put(GROUPID, topicVo.getGroupId());
        int count = topicMapper.pubTopicCount(paramMap);
        if (count == 1) {
            return "-1";
        }
        /*
         * if(topicVo.getTopicTitle() !=null &&
         * !"".equals(topicVo.getTopicTitle())){ try { String title = new
         * String(
         * topicVo.getTopicTitle().getBytes("ISO-8859-1"),ConstantUtil.UTF);
         * topicVo.setTopicTitle(title); } catch (UnsupportedEncodingException
         * e) { e.printStackTrace(); } } if(topicVo.getTopicContent() !=null &&
         * !"".equals(topicVo.getTopicContent())){ try { String content = new
         * String
         * (topicVo.getTopicContent().getBytes("ISO-8859-1"),ConstantUtil.UTF);
         * topicVo.setTopicContent(content); } catch
         * (UnsupportedEncodingException e) { e.printStackTrace(); } }
         */
        topicVo.setTopicCreateTime(new Date());
        topicVo.setTopicModifyTime(new Date());
        int flag = topicMapper.insertSelective(topicVo);
        Long topicId = topicMapper.selectLastId();
        if (flag > 0) {
            customerPointServiceMapper.addIntegralByType(topicVo.getCustomerId(), "7");
            List<GroupTopicImg> topicImgs = new ArrayList<GroupTopicImg>();
            Document doc = Jsoup.parseBodyFragment(topicVo.getTopicContent());
            Elements imgs = doc.getElementsByTag("img");
            if (imgs != null && !imgs.isEmpty()) {
                for (Element img : imgs) {
                    String imgSrc = img.attr("src");
                    /*
                     * UploadUtils utils = new UploadUtils(); String imgUrl =
                     * utils.UploadFileOne(imgSrc);
                     */
                    GroupTopicImg topicImg = new GroupTopicImg();
                    topicImg.setTopicId(topicId);
                    topicImg.setTopicImgCreateTime(new Date());
                    topicImg.setTopicImgDelFlag("0");
                    topicImg.setTopicImgUrl(imgSrc);
                    topicImg.setTopicType("0");
                    topicImgs.add(topicImg);
                }
                if (topicImgs != null && !topicImgs.isEmpty()) {
                    flag += topicImgMapper.insertBatchSelective(topicImgs);
                }
            } else {
                imgs = doc.getElementsByTag("embed");
                if (imgs != null && !imgs.isEmpty()) {
                    for (Element img : imgs) {
                        String imgSrc = img.attr("src");
                        // UploadUtils utils = new UploadUtils();
                        GroupTopicImg topicImg = new GroupTopicImg();
                        topicImg.setTopicId(topicId);
                        topicImg.setTopicImgCreateTime(new Date());
                        topicImg.setTopicImgDelFlag("0");
                        topicImg.setTopicImgUrl(imgSrc);
                        topicImg.setTopicType("1");
                        topicImgs.add(topicImg);
                    }
                    if (topicImgs != null && !topicImgs.isEmpty()) {
                        flag += topicImgMapper.insertBatchSelective(topicImgs);
                    }
                }
            }
        }
        return topicId.toString();
    }

    /**
     * 特别置顶话题
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    public GroupTopic specialHotTopic(GroupTopic topic) {
        GroupTopic topicNew = topic;
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICTOPVIEW, "2");
        topicNew = topicMapper.specGroupTopic(paramMap);
        if (topicNew != null) {
            topicNew.setTopicImgs(topicImgMapper.allGroupTopicList(topicNew.getTopicId()));
        }
        return topicNew;
    }

    /**
     * 一般置顶话题
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    public List<GroupTopic> commHotTopic(GroupTopic topic) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICTOPVIEW, "1");
        List<GroupTopic> topicList = topicMapper.commGroupTopic(paramMap);
        if (topicList != null) {
            for (int i = 0; i < topicList.size(); i++) {
                topicList.get(i).setTopicImgs(topicImgMapper.allGroupTopicList(topicList.get(i).getTopicId()));
            }
        }
        return topicList;
    }

    /**
     * 普通话题(分页)
     *
     * @param pb
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param sort
     *            排序 {@link java.lang.String}
     * @return
     */
    public PageBean groupTopics(PageBean pb, GroupTopic topic, String sort, String screening) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICTOPVIEW, "0");
        paramMap.put(TOPICDELFLAG, "0");
        /*
         * if(topic.getTopicTitle() !=null &&
         * !"".equals(topic.getTopicTitle())){ try { String title = new
         * String(topic
         * .getTopicTitle().getBytes("ISO-8859-1"),ConstantUtil.UTF); } catch
         * (UnsupportedEncodingException e) { e.printStackTrace(); } }
         */
        paramMap.put(TOPICTITLE, topic.getTopicTitle());
        /*
         * paramMap.put(TOPICFEVER,topic.getTopicFever());
         * paramMap.put(TOPICESSENCE,topic.getTopicEssence());
         */
        if (screening != null && !"".equals(screening)) {
            if ("fever".equals(screening)) {
                paramMap.put(TOPICFEVER, "1");
            }
            if ("essence".equals(screening)) {
                paramMap.put(TOPICESSENCE, "1");
            }
        }
        int rows = this.topicMapper.groupTopicCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put("sort", sort);
        pb.setPageSize(9);
        paramMap.put(STARTRUNNUM, pb.getStartRowNum());
        paramMap.put(ENDRUMNUM, pb.getEndRowNum());
        List<Object> objects = topicMapper.groupTopicList(paramMap);
        for (Object obj : objects) {
            GroupTopic toppic = (GroupTopic) obj;
            toppic.setTopicImgs(topicImgMapper.allGroupTopicList(toppic.getTopicId()));
            obj = toppic;
        }
        pb.setList(objects);
        return pb;
    }

    /**
     * 普通话题
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return list
     */
    public List<GroupTopic> commtopicList(GroupTopic topic) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put("topicTopTitle", topic.getTopicTitle());
        paramMap.put(TOPICTOPVIEW, "0");
        paramMap.put(TOPICFEVER, topic.getTopicFever());
        paramMap.put(TOPICESSENCE, topic.getTopicEssence());
        List<GroupTopic> groupTopics = topicMapper.topicList(paramMap);
        if (groupTopics != null) {
            for (int i = 0; i < groupTopics.size(); i++) {
                groupTopics.get(i).setTopicImgs(topicImgMapper.allGroupTopicList(groupTopics.get(i).getTopicId()));
            }
        }

        return groupTopics;
    }

    /**
     * 根据话题ID查询话题详情
     *
     * @param topicId
     *            话题ID{@link java.lang.Long}
     * @return 对象
     */
    public GroupTopic selectTopicById(Long topicId) {
        return this.topicMapper.topicDetail(topicId);
    }

    /**
     * 小组其他话题
     *
     * @param topic
     * @return
     */
    public List<GroupTopic> otherTopic(GroupTopic topic) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        if (topic.getTopicId() == null) {
            paramMap.put("topicId", Long.valueOf("-1"));
        } else {
            paramMap.put("topicId", topic.getTopicId());
        }
        paramMap.put("number", 5);
        return this.topicMapper.otherGroupTopic(paramMap);
    }

    /**
     * 修改小组话题
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return int
     */
    @Transactional
    public int editTopic(GroupTopic topic) {
        topic.setTopicModifyTime(new Date());

        int flags = topicMapper.updateByPrimaryKeySelective(topic);
        GroupTopic groupTopic = this.selectTopicById(topic.getTopicId());
        if ("1".equals(topic.getChangeVal()) && "fever".equals(topic.getChangeFlag())) {
            if (recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "6")) {
                TopicRecommend recommend = new TopicRecommend();
                recommend.setCustomerId(groupTopic.getCustomerId());
                recommend.setRecommendShipId(groupTopic.getTopicId());
                recommend.setRecommendType("6");
                recommend.setDelFlag("0");
                recommend.setRecommendCreateTime(new Date());
                recommendService.insertSelective(recommend);
                customerPointServiceMapper.addIntegralByType(groupTopic.getCustomerId(), "8");
            }
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(MESSAGETYPE, '1');
            paramMap.put(MESSAGEOPERATION, '6');
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setGroupId(groupTopic.getGroupId());
            messageVo.setTopicId(groupTopic.getTopicId());
            messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageTitle(template.getMessageTemplate());
            messageVo.setMessageType("0");
            messageSiteService.addMessage(messageVo, null);
        }
        if ("1".equals(topic.getChangeVal()) && "essence".equals(topic.getChangeFlag())) {
            if (recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "7")) {
                TopicRecommend recommend = new TopicRecommend();
                recommend.setCustomerId(groupTopic.getCustomerId());
                recommend.setRecommendShipId(groupTopic.getTopicId());
                recommend.setRecommendType("7");
                recommend.setDelFlag("0");
                recommend.setRecommendCreateTime(new Date());
                recommendService.insertSelective(recommend);
                customerPointServiceMapper.addIntegralByType(groupTopic.getCustomerId(), "9");
            }
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(MESSAGETYPE, '1');
            paramMap.put(MESSAGEOPERATION, '5');
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setGroupId(groupTopic.getGroupId());
            messageVo.setTopicId(groupTopic.getTopicId());
            messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageTitle(template.getMessageTemplate());
            messageVo.setMessageType("0");
            messageSiteService.addMessage(messageVo, null);
        }
        if (topic.getTopicContent() != null && !"".equals(topic.getTopicContent()) && flags > 0) {
                topicImgMapper.deleteGroupImgById(topic.getTopicId());
                List<GroupTopicImg> topicImgs = new ArrayList<GroupTopicImg>();
                Document doc = Jsoup.parseBodyFragment(topic.getTopicContent());
                Elements imgs = doc.getElementsByTag("img");
                Integer flag = 0;
                if (imgs != null && !imgs.isEmpty()) {
                    for (Element img : imgs) {
                        String imgSrc = img.attr("src");
                        GroupTopicImg topicImg = new GroupTopicImg();
                        topicImg.setTopicId(topic.getTopicId());
                        topicImg.setTopicImgCreateTime(new Date());
                        topicImg.setTopicImgDelFlag("0");
                        topicImg.setTopicImgUrl(imgSrc);
                        topicImg.setTopicType("0");
                        topicImgs.add(topicImg);
                    }
                    if (topicImgs != null && !topicImgs.isEmpty()) {
                        flag += topicImgMapper.insertBatchSelective(topicImgs);
                    }
                } else {
                    imgs = doc.getElementsByTag("embed");
                    if (imgs != null && !imgs.isEmpty()) {
                        for (Element img : imgs) {
                            String imgSrc = img.attr("src");
                            GroupTopicImg topicImg = new GroupTopicImg();
                            topicImg.setTopicId(topic.getTopicId());
                            topicImg.setTopicImgCreateTime(new Date());
                            topicImg.setTopicImgDelFlag("0");
                            topicImg.setTopicImgUrl(imgSrc);
                            topicImg.setTopicType("1");
                            topicImgs.add(topicImg);
                        }
                        if (topicImgs != null && !topicImgs.isEmpty()) {
                            flag += topicImgMapper.insertBatchSelective(topicImgs);
                        }
                    }
            }
        }
        return flags;
    }

    /**
     * 删除话题
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param customerId
     *            操作用户Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int delTopic(GroupTopic topic, Long customerId) {
        GroupTopic topicNew = topic;
        topicNew.setTopicDelFlag("1");
        topicNew.setTopicModifyTime(new Date());
        topicNew.setTopicDelCustomerId(customerId);
        int flag = this.topicMapper.updateByPrimaryKeySelective(topicNew);
        topicNew = topicMapper.selectByPrimaryKey(topicNew.getTopicId());
        if (flag > 0) {
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(MESSAGETYPE, '1');
            paramMap.put(MESSAGEOPERATION, "26");
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setGroupId(topicNew.getGroupId());
            messageVo.setTopicId(topicNew.getTopicId());
            messageVo.setCustomerRecId(new Long[] { topicNew.getCustomerId() });
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageTitle("新消息~话题管理");
            messageVo.setMessageContent(template.getMessageTemplate());
            messageVo.setMessageType("0");
            messageVo.setMessageCreateTime(topicNew.getTopicModifyTime());
            messageSiteService.addMessage(messageVo, customerId);
        }
        return flag;
    }

    /**
     * 批量删除话题
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @param customerId
     *            操作用户Id{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int delTopicBatch(Long[] topicId, Long customerId) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicDelFlag("1");
            topic.setTopicModifyTime(new Date());
            topic.setTopicDelCustomerId(customerId);
            flag = topicMapper.updateByPrimaryKeySelective(topic);
            topic = topicMapper.selectByPrimaryKey(topic.getTopicId());
            if (flag > 0) {
                MessageVo messageVo = new MessageVo();
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(MESSAGETYPE, '1');
                paramMap.put(MESSAGEOPERATION, "26");
                MessageTemplate template = this.templateMapper.selectByType(paramMap);
                messageVo.setGroupId(topic.getGroupId());
                messageVo.setTopicId(topic.getTopicId());
                messageVo.setCustomerRecId(new Long[] { topic.getCustomerId() });
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle("新消息~话题管理");
                messageVo.setMessageContent(template.getMessageTemplate());
                messageVo.setMessageType("0");
                messageVo.setMessageCreateTime(topic.getTopicModifyTime());
                messageSiteService.addMessage(messageVo, customerId);
            }
        }
        return flag;
    }

    /**
     * 批量回应话题
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int replyTopicBatch(Long[] topicId) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicReplyFlag("0");
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 批量禁止回应话题
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int unreplyTopicBatch(Long[] topicId) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicReplyFlag("1");
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 申请到首页
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int recommendTopicBatch(Long[] topicId) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicIndexView("1");
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 设精华
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @param essenceTopic
     *            精华标志
     * @return int
     */
    @Transactional
    public int essenceTopicBatch(Long[] topicId, String essenceTopic) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicEssence(essenceTopic);
            if ("1".equals(essenceTopic)) {
                GroupTopic groupTopic = this.selectTopicById(l);
                if (recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "7")) {
                    TopicRecommend recommend = new TopicRecommend();
                    recommend.setCustomerId(groupTopic.getCustomerId());
                    recommend.setRecommendShipId(groupTopic.getTopicId());
                    recommend.setRecommendType("7");
                    recommend.setDelFlag("0");
                    recommend.setRecommendCreateTime(new Date());
                    recommendService.insertSelective(recommend);
                    customerPointServiceMapper.addIntegralByType(groupTopic.getCustomerId(), "9");
                }
                MessageVo messageVo = new MessageVo();
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(MESSAGETYPE, '1');
                paramMap.put(MESSAGEOPERATION, '5');
                MessageTemplate template = this.templateMapper.selectByType(paramMap);
                messageVo.setGroupId(groupTopic.getGroupId());
                messageVo.setTopicId(groupTopic.getTopicId());
                messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setMessageType("0");
                messageSiteService.addMessage(messageVo, null);
                // 设置精华帖加积分
            }
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 设精华
     *
     * @param topicId
     *            小组话题ID {@link java.lang.Long}
     * @param fever
     *            热帖标志
     * @return int
     */
    @Transactional
    public int feverTopicBatch(Long[] topicId, String fever) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicFever(fever);
            if ("1".equals(fever)) {
                GroupTopic groupTopic = this.selectTopicById(l);
                if (recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "6")) {
                    TopicRecommend recommend = new TopicRecommend();
                    recommend.setCustomerId(groupTopic.getCustomerId());
                    recommend.setRecommendShipId(groupTopic.getTopicId());
                    recommend.setRecommendType("6");
                    recommend.setDelFlag("0");
                    recommend.setRecommendCreateTime(new Date());
                    recommendService.insertSelective(recommend);
                    customerPointServiceMapper.addIntegralByType(groupTopic.getCustomerId(), "8");
                }
                MessageVo messageVo = new MessageVo();
                Map<String, Object> paramMap = new HashMap<String, Object>();
                paramMap.put(MESSAGETYPE, '1');
                paramMap.put(MESSAGEOPERATION, '6');
                MessageTemplate template = this.templateMapper.selectByType(paramMap);
                messageVo.setGroupId(groupTopic.getGroupId());
                messageVo.setTopicId(groupTopic.getTopicId());
                messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
                messageVo.setMessageTempId(template.getMessageTempId());
                messageVo.setMessageTitle(template.getMessageTemplate());
                messageVo.setMessageType("0");
                messageSiteService.addMessage(messageVo, null);
            }
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 话题置顶设置
     *
     * @param topicId
     * @param top
     * @return
     */
    @Transactional
    public int topTopicBatch(Long[] topicId, String top) {
        Map<String, Object> paramMap = new HashMap<String, Object>();

        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            if ("2".equals(top)) {
                GroupTopic topic1 = topicMapper.topicDetail(l);
                topic.setGroupId(topic1.getGroupId());
                flag = topicMapper.noTopTopic(topic);
            }
            topic.setTopicTopView(top);
            if ("1".equals(top)) {
                GroupTopic topic1 = topicMapper.topicDetail(l);
                paramMap.put(TOPICTOPVIEW, top);
                paramMap.put(GROUPID, topic1.getGroupId());
                int count = topicMapper.groupTopicCount(paramMap);
                if (count >= 5) {
                    return flag;
                }
            }
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 禁止 回应的话题
     *
     * @param pb
     *            pageBean
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    public PageBean noResponseTopic(PageBean pb, GroupTopic topic) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICDELFLAG, "0");
        paramMap.put("topicReplyFlag", "1");
        int rows = this.topicMapper.groupTopicCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(9);
        paramMap.put(STARTRUNNUM, pb.getStartRowNum());
        paramMap.put(ENDRUMNUM, pb.getEndRowNum());
        pb.setList(topicMapper.groupTopicList(paramMap));
        return pb;
    }

    /**
     * 回收站(删除的话题)
     *
     * @param pb
     *            pageBean
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    public PageBean topicRecycle(PageBean pb, GroupTopic topic) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICDELFLAG, "1");
        int rows = this.topicMapper.groupTopicCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(9);
        paramMap.put(STARTRUNNUM, pb.getStartRowNum());
        paramMap.put(ENDRUMNUM, pb.getEndRowNum());
        pb.setList(topicMapper.groupTopicList(paramMap));
        return pb;
    }

    /**
     * 彻底删除回收站的话题
     *
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int delTopic(Long[] topicId) {
        int flag = 0;
        for (Long l : topicId) {
            flag = topicMapper.deleteByPrimaryKey(l);
        }
        return flag;
    }

    /**
     * 还原话题
     *
     * @param topicId
     *            话题Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int restore(Long[] topicId) {
        int flag = 0;
        for (Long l : topicId) {
            GroupTopic topic = new GroupTopic();
            topic.setTopicId(l);
            topic.setTopicDelFlag("0");
            flag = topicMapper.updateByPrimaryKeySelective(topic);
        }
        return flag;
    }

    /**
     * 话题列表
     *
     * @param pb
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param flag
     *            搜索标记{@link java.lang.String}
     * @param keyword
     *            查询条件{@link java.lang.String}
     * @return
     */
    public PageBean topicList(PageBean pb, GroupTopic topic, String flag, String keyword) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(GROUPID, topic.getGroupId());
        paramMap.put(TOPICDELFLAG, "0");
        paramMap.put(CUSTOMERID, topic.getCustomerId());
        paramMap.put("topicCateId", topic.getTopicCateId());
        paramMap.put("topicIndexView", topic.getTopicIndexView());
        paramMap.put("topicIsUse", topic.getTopicIsUse());
        paramMap.put("groupSecret", '0');

        if ("1".equals(flag)) {
            paramMap.put(TOPICTITLE, keyword);
        }
        if ("2".equals(flag)) {
            paramMap.put("topicContent", keyword);
        }
        if ("3".equals(flag)) {
            paramMap.put("customerName", keyword);
        }
        int rows = this.topicMapper.groupTopicCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(10);
        paramMap.put(STARTRUNNUM, pb.getStartRowNum());
        paramMap.put(ENDRUMNUM, pb.getEndRowNum());
        List<Object> objects = topicMapper.groupTopicList(paramMap);
        for (Object obj : objects) {
            GroupTopic toppic = (GroupTopic) obj;
            toppic.setTopicImgs(topicImgMapper.allGroupTopicList(toppic.getTopicId()));
            obj = toppic;
        }
        pb.setList(objects);
        return pb;
    }

    /**
     * 根据话题Id和用户Id查询用户推荐次数
     *
     * @param shipId
     *            被推荐的对象Id {@link java.lang.Long}
     * @param customerId
     *            用户Id {@link java.lang.Long}
     * @param recommendType
     *            被推荐的对象类型 {@link java.lang.String}
     * @return int
     */
    public int topicRecommendCount(Long shipId, Long customerId, String recommendType) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("recommendShipId", shipId);
        paramMap.put(CUSTOMERID, customerId);
        return this.recommendMapper.topicRecommendCount(paramMap);
    }

    /**
     * 添加推荐数据
     *
     * @param shipId
     *            被推荐的对象Id {@link java.lang.Long}
     * @param customerId
     *            用户Id {@link java.lang.Long}
     * @param recommendType
     *            被推荐的对象类型 {@link java.lang.String}
     * @return int
     */
    @Transactional
    public int insertTopicRecommend(Long shipId, Long customerId, String recommendType) {
        TopicRecommend recommend = new TopicRecommend();
        recommend.setRecommendShipId(shipId);
        recommend.setCustomerId(customerId);
        recommend.setRecommendType(recommendType);
        recommend.setDelFlag("0");
        recommend.setRecommendCreateTime(new Date());
        return this.recommendMapper.insertSelective(recommend);
    }

    /**
     * 更新推荐次数
     *
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param customerId
     *            登陆用户Id{@link java.lang.Long}
     * @return
     */
    @Transactional
    public int updateTopicRecommend(GroupTopic topic, Long customerId) {
        GroupTopic groupTopic = this.selectTopicById(topic.getTopicId());
        MessageVo messageVo = new MessageVo();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(MESSAGETYPE, '1');
        paramMap.put(MESSAGEOPERATION, "22");
        MessageTemplate template = this.templateMapper.selectByType(paramMap);

        messageVo.setGroupId(groupTopic.getGroupId());
        messageVo.setTopicId(groupTopic.getTopicId());
        messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
        messageVo.setMessageTempId(template.getMessageTempId());
        messageVo.setMessageContent(template.getMessageTemplate());
        messageVo.setMessageTitle("新消息~推荐话题");
        messageVo.setMessageType("0");
        messageSiteService.addMessage(messageVo, customerId);
        return this.topicMapper.updateTopicRecommend(topic);
    }

    /**
     * 查看精选热门话题
     *
     * @param pageNo
     *            当前页码
     * @param pb
     *            分页
     * @return
     */
    public PageBean topicEssence(PageBean pb, String pageNo) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put(TOPICFEVER, "1");
        paramMap.put(TOPICESSENCE, "1");
        int rows = topicMapper.topicEssenceListCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        pb.setPageSize(8);
        pb.setPageNo(Integer.parseInt(pageNo));
        paramMap.put(STARTRUNNUM, pb.getStartRowNum());
        paramMap.put(ENDRUMNUM, pb.getEndRowNum());
        List<Object> objects = topicMapper.topicEssenceList(paramMap);
        for (Object obj : objects) {
            GroupTopic toppic = (GroupTopic) obj;
            toppic.setTopicImgs(topicImgMapper.allGroupTopicList(toppic.getTopicId()));
            obj = toppic;
        }
        pb.setList(objects);
        return pb;
    }

    /**
     * 添加赞
     *
     * @param recommend
     *            推荐{@link com.ningpai.topic.bean.TopicRecommend}
     * @return
     */
    @Transactional
    public int insertRecommen(TopicRecommend recommend) {
        recommend.setRecommendCreateTime(new Date());
        recommend.setDelFlag("0");
        return this.recommendMapper.insertSelective(recommend);
    }

    /**
     * 查询赞的次数
     *
     * @param recommend
     *            推荐{@link com.ningpai.topic.bean.TopicRecommend}
     * @return
     */
    public int recommendCount(TopicRecommend recommend) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("recommendShipId", recommend.getRecommendShipId());
        paramMap.put(CUSTOMERID, recommend.getCustomerId());
        paramMap.put("customerIp", recommend.getCustomerIp());
        paramMap.put("recommendCreateTime", recommend.getRecommendCreateTime());
        return this.recommendMapper.topicRecommendCount(paramMap);
    }

    /**
     * 首页显示话题
     *
     * @param flag
     *            标记 0：热帖 1：精华 {@link java.lang.String}
     * @param number
     *            显示数目
     * @return List
     */
    public List<GroupTopic> topicIndexList(String flag, int number) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if ("0".equals(flag)) {
            paramMap.put(TOPICFEVER, "1");
        }
        if ("1".equals(flag)) {
            paramMap.put(TOPICESSENCE, "1");
        }
        paramMap.put("number", number);
        return topicMapper.topicIndexList(paramMap);
    }

    /**
     * 申请话题恢复
     *
     * @param groupTopic
     *            话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    @Transactional
    public int applyTopic(GroupTopic groupTopic) {
        int flag = this.topicMapper.updateByPrimaryKeySelective(groupTopic);
        if (flag > 0) {
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put(MESSAGETYPE, '1');
            paramMap.put(MESSAGEOPERATION, "27");
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setMessageTitle("申请恢复话题");
            messageVo.setMessageType("0");
            messageVo.setCustomerId(groupTopic.getCustomerId());// 申请恢复话题的用户Id
            messageVo.setGroupId(groupTopic.getGroupId());
            messageVo.setMessageDelFlag("0");
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageContent(template.getMessageTemplate());
            messageVo.setTopicId(groupTopic.getTopicId());
            GroupCustomer customer = new GroupCustomer();
            customer.setGroupId(groupTopic.getGroupId());
            // 小组管理员
            List<GroupCustomer> customers = groupService.customers(customer, "2");
            // 小组组长
            customers.add(groupService.customers(customer, "1").get(0));
            Long[] customerRecId = new Long[customers.size()];
            for (int j = 0; j < customers.size(); j++) {
                customerRecId[j] = customers.get(j).getCustomerId();
            }
            messageVo.setCustomerRecId(customerRecId);
            messageSiteService.addMessage(messageVo, groupTopic.getCustomerId());
        }
        return flag;
    }

    /**
     * 根据话题Id查询申请话题详情
     *
     * @param topicId
     *            话题ID{@link java.lang,Long}
     * @return 对象
     */
    public GroupTopic applyTopicDetail(Long topicId) {

        return this.topicMapper.applyTopic(topicId);
    }

    /**
     * 处理申诉详情
     *
     * @param groupTopic
     *            话题{@link com.ningpai.topic.bean.GroupTopic}
     * @param customerId
     *            用户ID{@link java.lang.Long}
     * @return int
     */
    @Transactional
    public int applyDetail(GroupTopic groupTopic, Long customerId) {
        GroupTopic groupTopicNew = groupTopic;
        groupTopicNew.setTopicApplyFlag("1");
        int flag = this.topicMapper.updateByPrimaryKeySelective(groupTopicNew);
        groupTopicNew = this.topicMapper.topicDetail(groupTopicNew.getTopicId());
        if (flag > 0) {
            MessageVo messageVo = new MessageVo();
            Map<String, Object> paramMap = new HashMap<String, Object>();
            if ("0".equals(groupTopicNew.getTopicDelFlag())) {
                paramMap.put(MESSAGETYPE, '1');
                paramMap.put(MESSAGEOPERATION, "28");
            } else {
                paramMap.put(MESSAGETYPE, '1');
                paramMap.put(MESSAGEOPERATION, "29");
            }
            MessageTemplate template = this.templateMapper.selectByType(paramMap);
            messageVo.setMessageType("0");
            messageVo.setCustomerId(groupTopicNew.getCustomerId());// 申请恢复话题的用户Id
            messageVo.setGroupId(groupTopicNew.getGroupId());
            messageVo.setMessageDelFlag("0");
            messageVo.setMessageTempId(template.getMessageTempId());
            messageVo.setMessageTitle(template.getMessageTemplate());
            messageVo.setTopicId(groupTopicNew.getTopicId());
            messageVo.setCustomerRecId(new Long[] { groupTopic.getCustomerId() });
            messageSiteService.addMessage(messageVo, customerId);
        }
        return flag;
    }

    /**
     * 话题总数
     *
     * @return
     */
    public int topicCount() {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("groupSecret", '0');
        paramMap.put(TOPICDELFLAG, '0');
        return this.topicMapper.topicCount(paramMap);
    }

    public GroupTopicMapper getTopicMapper() {
        return topicMapper;
    }

    @Resource(name = "GroupTopicMapper")
    public void setTopicMapper(GroupTopicMapper topicMapper) {
        this.topicMapper = topicMapper;
    }

    public GroupTopicImgMapper getTopicImgMapper() {
        return topicImgMapper;
    }

    @Resource(name = "GroupTopicImgMapper")
    public void setTopicImgMapper(GroupTopicImgMapper topicImgMapper) {
        this.topicImgMapper = topicImgMapper;
    }

    public CustomerReplyMapper getReplyMapper() {
        return replyMapper;
    }

    @Resource(name = "CustomerReplyMapper")
    public void setReplyMapper(CustomerReplyMapper replyMapper) {
        this.replyMapper = replyMapper;
    }

    public TopicRecommendMapper getRecommendMapper() {
        return recommendMapper;
    }

    @Resource(name = "TopicRecommendMapper")
    public void setRecommendMapper(TopicRecommendMapper recommendMapper) {
        this.recommendMapper = recommendMapper;
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

    public CustomerPointServiceMapper getCustomerPointServiceMapper() {
        return customerPointServiceMapper;
    }

    @Resource(name = "customerPointServiceMapper")
    public void setCustomerPointServiceMapper(CustomerPointServiceMapper customerPointServiceMapper) {
        this.customerPointServiceMapper = customerPointServiceMapper;
    }

    public RecommendService getRecommendService() {
        return recommendService;
    }

    @Resource(name = "RecommendService")
    public void setRecommendService(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

    public GroupSiteService getGroupService() {
        return groupService;
    }

    @Resource(name = "GroupSiteService")
    public void setGroupService(GroupSiteService groupService) {
        this.groupService = groupService;
    }

}
