/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.topic.service.impl;

import com.ningpai.topic.bean.GroupTopic;
import com.ningpai.topic.bean.GroupTopicImg;
import com.ningpai.topic.bean.TopicRecommend;
import com.ningpai.topic.dao.GroupTopicImgMapper;
import com.ningpai.topic.dao.GroupTopicMapper;
import com.ningpai.topic.service.RecommendService;
import com.ningpai.topic.service.TopicService;
import com.ningpai.util.PageBean;
import com.ningpai.util.SelectBean;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * 话题后台Service实现类
 *
 * @author qiyuanyuan
 * @version 2014年5月26日 下午2:20:29
 */

@Service("TopicService")
public class TopicServiceImpl implements TopicService {

    /**
     * 小组话题DAO
     */
    private GroupTopicMapper topicMapper;

    /**
     * 话题图片DAO
     */
    private GroupTopicImgMapper topicImgMapper;

    /**
     * 点赞统计service
     */
    private RecommendService recommendService;

    /**
     * 后台查询话题列表
     *
     * @param pb
     * @param topic
     *            小组话题{@link com.ningpai.topic.bean.GroupTopic}
     * @return
     */
    public PageBean topicList(PageBean pb, GroupTopic topic, SelectBean selectBean) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("topicDelFlag", "0");
        paramMap.put("customerId", topic.getCustomerId());
        paramMap.put("groupTypeId", topic.getGroupTypeId());
        paramMap.put("groupSecret", topic.getGroupSecret());
        paramMap.put("topicTitle", topic.getTopicTitle());
        paramMap.put("topicContent", topic.getTopicContent());
        paramMap.put("customerName", topic.getCustomerName());
        paramMap.put("groupName", topic.getGroupName());
        paramMap.put("replyCount", topic.getReplyCount());
        paramMap.put("topicHot", topic.getTopicHot());
        paramMap.put("topicCreateTime", topic.getTopicCreateTime());
        paramMap.put("topicCreateTimeTo", topic.getTopicCreateTimeTo());
        paramMap.put("topicIndexView", topic.getTopicIndexView());
        paramMap.put("topicEssence", topic.getTopicEssence());
        paramMap.put("topicFever", topic.getTopicFever());
        paramMap.put("topicTopView", topic.getTopicTopView());
        if (selectBean.getCondition() != null && selectBean.getSearchText() != null) {
            if ("1".equals(selectBean.getCondition())) {
                paramMap.put("topicTitle", selectBean.getSearchText());
            }
            if ("2".equals(selectBean.getCondition())) {
                paramMap.put("topicContent", selectBean.getSearchText());
            }
            if ("3".equals(selectBean.getCondition())) {
                paramMap.put("customerName", selectBean.getSearchText());
            }
        }
        int rows = this.topicMapper.groupTopicCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put("startRunNum", pb.getStartRowNum());
        paramMap.put("endRumNum", pb.getEndRowNum());
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
        GroupTopic groupTopic = topicMapper.selectByPrimaryKey(topic.getTopicId());
        if (topic.getTopicContent() != null && !"".equals(topic.getTopicContent()) && flags > 0) {
                if ("1".equals(topic.getTopicFever()) && recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "6")) {
                        TopicRecommend recommend = new TopicRecommend();
                        recommend.setCustomerId(groupTopic.getCustomerId());
                        recommend.setRecommendShipId(groupTopic.getTopicId());
                        recommend.setRecommendType("6");
                        recommend.setDelFlag("0");
                        recommend.setRecommendCreateTime(new Date());
                        recommendService.insertSelective(recommend);

                }
                if ("1".equals(topic.getTopicEssence()) && recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "7")) {
                        TopicRecommend recommend = new TopicRecommend();
                        recommend.setCustomerId(groupTopic.getCustomerId());
                        recommend.setRecommendShipId(groupTopic.getTopicId());
                        recommend.setRecommendType("7");
                        recommend.setDelFlag("0");
                        recommend.setRecommendCreateTime(new Date());
                        recommendService.insertSelective(recommend);
                }
                if ("2".equals(topic.getTopicIndexView()) && recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "8")) {
                        TopicRecommend recommend = new TopicRecommend();
                        recommend.setCustomerId(groupTopic.getCustomerId());
                        recommend.setRecommendShipId(groupTopic.getTopicId());
                        recommend.setRecommendType("8");
                        recommend.setDelFlag("0");
                        recommend.setRecommendCreateTime(new Date());
                        recommendService.insertSelective(recommend);

                }
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
     * @param topicIds
     *            话题Id{@link java.lang.String}
     * @return
     */
    @Transactional
    public int deleteTopic(String[] topicIds) {
        int count = 0;
        if (topicIds != null) {
            for (String topicId : topicIds) {
                count += topicMapper.deleteByTopicId(Long.valueOf(topicId));
            }
        }
        return count;
    }

    /**
     * 申请到首页
     *
     * @param topicIds
     *            话题id {@link java.lang.Long}
     * @param topicIndexView
     *            首页标记 {@link java.lang.String}
     * @return int
     */
    @Transactional
    public int updateToIndex(String[] topicIds, String topicIndexView) {
        int count = 0;
        if (topicIds != null) {
            for (String topicId : topicIds) {
                GroupTopic groupTopic = topicMapper.selectByPrimaryKey(Long.valueOf(topicId));
                groupTopic.setTopicIndexView(topicIndexView);
                if (recommendService.checkIsRecordByCustomerId(groupTopic.getCustomerId(), groupTopic.getTopicId(), "8")) {
                    TopicRecommend recommend = new TopicRecommend();
                    recommend.setCustomerId(groupTopic.getCustomerId());
                    recommend.setRecommendShipId(groupTopic.getTopicId());
                    recommend.setRecommendType("8");
                    recommend.setDelFlag("0");
                    recommend.setRecommendCreateTime(new Date());
                    recommendService.insertSelective(recommend);

                }
                groupTopic.setTopicRecommendTime(new Date());
                count += topicMapper.updateToIndex(groupTopic);
            }
        }
        return count;
    }

    /**
     * 设置为使用心得
     *
     * @param topicIds
     * @param topicIsUse
     * @return
     */
    @Transactional
    public int useException(String[] topicIds, String topicIsUse) {
        int flag = 0;
        if (topicIds != null && topicIds.length != 0) {
            for (String topicId : topicIds) {
                GroupTopic groupTopic = topicMapper.selectByPrimaryKey(Long.valueOf(topicId));
                groupTopic.setTopicIsUse(topicIsUse);
                groupTopic.setTopicModifyTime(new Date());
                flag += topicMapper.useException(groupTopic);
            }
        }
        return flag;
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

    public RecommendService getRecommendService() {
        return recommendService;
    }

    @Resource(name = "RecommendService")
    public void setRecommendService(RecommendService recommendService) {
        this.recommendService = recommendService;
    }

}
