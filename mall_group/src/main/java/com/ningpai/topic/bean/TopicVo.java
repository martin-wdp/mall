package com.ningpai.topic.bean;

import java.util.List;

/**
 * 话题实体Vo
 * 
 * @author qiyuanyuan
 * 
 */
public class TopicVo extends GroupTopic {

    // 话题附件
    private List<GroupTopicImg> grouptopicImgs;

    public List<GroupTopicImg> getGrouptopicImgs() {
        return grouptopicImgs;
    }

    public void setGrouptopicImgs(List<GroupTopicImg> grouptopicImgs) {
        this.grouptopicImgs = grouptopicImgs;
    }
}
