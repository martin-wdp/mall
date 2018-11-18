package com.ningpai.group.service;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.Mood;
import com.ningpai.util.PageBean;

import java.util.List;


/**
 *个人中心-心情Service
 *
 */
public interface MoodService {
    /**
     * 发布心情
     * @param mood 心情对象 {@link com.ningpai.group.bean.Mood}
     * @return int
     */
    public int sendMood(Mood mood);
    
    /**
     * 查询心情列表 按时间排序
     * @param customerId  用户Id{@link java.lang.Long}
     * @param pb 分页
     */
    PageBean moodList(Long customerId, PageBean pb);
    
    /**
     * 查询心情回复
     * @param customerId 用户Id{@link java.lang.Long}
     * @param number
     * @return
     */
    public List<CustomerReply> moodReply(Long customerId,Integer number);
    
    /**
     * 删除心情
     * @param moodId 心情Id{@link java.lang.Long}
     * @return
     */
    public int delMood(Long moodId);
    
    /**
     * 查询最新一条心情
     * @param customerId
     * @return
     */
    Mood selectOneMood(Long customerId);
}
