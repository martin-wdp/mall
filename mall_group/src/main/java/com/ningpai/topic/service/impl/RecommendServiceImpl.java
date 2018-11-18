package com.ningpai.topic.service.impl;

import com.ningpai.topic.bean.TopicRecommend;
import com.ningpai.topic.dao.TopicRecommendMapper;
import com.ningpai.topic.service.RecommendService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 点赞统计service实现
 * 
 * @author zhangyue
 * 
 */
@Service("RecommendService")
public class RecommendServiceImpl implements RecommendService {

    private static final String CUSTOMERIP = "customerIp";
    private static final String RECOMMENDSHIPID = "recommendShipId";
    private static final String RECOMMENDTYPE = "recommendType";
    private static final String CUSTOMERID = "customerId";
    private static final String NOWDATE = "nowDate";
    private static final String DATE = "yyyy-MM-dd";

    /**
     * 话题推荐接口
     */
    @Resource(name = "TopicRecommendMapper")
    private TopicRecommendMapper recommendMapper;

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:添加个数
     *
     * @date 2014-08-15 14:44:46
     */
    @Transactional
    public int insertSelective(TopicRecommend record) {
        record.setRecommendCreateTime(new Date());
        record.setRecommendModifyTime(new Date());
        record.setDelFlag("0");
        return recommendMapper.insertSelective(record);
    }

    /**
     * 查询点赞数
     *
     * @param customerIp
     * @param recommendShipId
     * @param recommendType
     * @return int
     */
    public int selectViewCount(String customerIp, Long recommendShipId, String recommendType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CUSTOMERIP, customerIp);
        map.put(RECOMMENDSHIPID, recommendShipId);
        map.put(RECOMMENDTYPE, recommendType);
        return recommendMapper.topicRecommendCount(map);
    }

    /**
     * 检测是否赞过
     *
     * @param customerIp
     * @param customerIp
     * @param recommendShipId
     * @return
     */
    public boolean checkIsView(Long customerId, String customerIp, Long recommendShipId, String recommendType) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CUSTOMERID, customerId);
        map.put(CUSTOMERIP, customerIp);
        map.put(RECOMMENDSHIPID, recommendShipId);
        map.put(RECOMMENDTYPE, recommendType);
        map.put(NOWDATE, new SimpleDateFormat(DATE).format(new Date()));
        if (recommendMapper.topicRecommendCount(map) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 验证是否记录过了相关记录
     *
     * @param customerId
     *            用户id
     * @param recommendShipId
     *            关系id
     * @param type
     *            类型
     * @return boolean true 可以记录 false 已经记录过了
     */
    public boolean checkIsRecordByCustomerId(Long customerId, Long recommendShipId, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CUSTOMERID, customerId);
        map.put(RECOMMENDSHIPID, recommendShipId);
        map.put(RECOMMENDTYPE, type);
        if (recommendMapper.topicRecommendCount(map) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 验证 通过用户id和时间当前 确定是否记录过
     *
     * @param customerId
     * @param recommendShipId
     * @param type
     * @return boolean true 可以记录 false 已经记录过了
     */
    public boolean checkIsRecordByCustomerIdAndDay(Long customerId, Long recommendShipId, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CUSTOMERID, customerId);
        map.put(RECOMMENDSHIPID, recommendShipId);
        map.put(RECOMMENDTYPE, type);
        map.put(NOWDATE, new SimpleDateFormat(DATE).format(new Date()));
        if (recommendMapper.topicRecommendCount(map) > 0) {
            return false;
        }
        return true;
    }

    /**
     * 通过用户ip和当天 验证是否记录过
     *
     * @param customerIp
     * @param customerId
     * @param recommendShipId
     * @return boolean true 可以记录 false 已经记录过了
     */
    public boolean checkIsRecordByCustomerIPAndDay(String customerIp, Long customerId, Long recommendShipId, String type) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(CUSTOMERIP, customerIp);
        map.put(CUSTOMERID, customerId);
        map.put(RECOMMENDSHIPID, recommendShipId);
        map.put(RECOMMENDTYPE, type);
        map.put(NOWDATE, new SimpleDateFormat(DATE).format(new Date()));
        if (recommendMapper.topicRecommendCount(map) > 0) {
            return false;
        }
        return true;
    }

}
