package com.ningpai.group.service.impl;

import com.ningpai.group.bean.CustomerReply;
import com.ningpai.group.bean.Mood;
import com.ningpai.group.dao.MoodMapper;
import com.ningpai.group.service.MoodService;
import com.ningpai.util.PageBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 个人中心 心情service
 * 
 * @author qiyuanyuan
 *
 */
@Service("MoodService")
public class MoodServiceImpl implements MoodService {

    /**
     * 个人中心--个人心情接口
     */
    private MoodMapper moodMapper;

    /**
     * 发布心情
     * 
     * @param mood
     *            心情对象 {@link com.ningpai.group.bean.Mood}
     * @return int
     */
    public int sendMood(Mood mood) {

        // 发布心情
        mood.setMoodCreateTime(new Date());
        mood.setMoodDelFlag("0");
        return moodMapper.sendMood(mood);
    }

    /**
     * 查询心情列表 按时间排序
     * 
     * @param memberId
     *            用户Id{@link java.lang.Long}
     * @param pb
     *            分页
     */
    public PageBean moodList(Long memberId, PageBean pb) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", memberId);
        int rows = moodMapper.selectMoodCount(paramMap);
        if (rows > 0) {
            pb.setRows(rows);
        } else {
            pb.setRows(0);
        }
        paramMap.put("start", pb.getStartRowNum());
        paramMap.put("number", pb.getEndRowNum());
        pb.setList(moodMapper.selectMoodList(paramMap));
        return pb;
    }

    /**
     * 查询心情回复
     * 
     * @param memberId
     *            用户Id{@link java.lang.Long}
     * @param number
     * @return
     */
    public List<CustomerReply> moodReply(Long memberId, Integer number) {

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("customerId", memberId);
        paramMap.put("number", number);
        return moodMapper.moodReply(paramMap);
    }

    /**
     * 删除心情
     * 
     * @param moodId
     *            心情Id{@link java.lang.Long}
     * @return
     */
    public int delMood(Long moodId) {

        return moodMapper.delMood(moodId);
    }

    /**
     * 查询最新一条心情
     * 
     * @param customerId
     * @return
     */
    public Mood selectOneMood(Long customerId) {

        return moodMapper.selectOneMood(customerId);
    }

    public MoodMapper getMoodMapper() {
        return moodMapper;
    }

    @Resource(name = "MoodMapper")
    public void setMoodMapper(MoodMapper moodMapper) {
        this.moodMapper = moodMapper;
    }

}
