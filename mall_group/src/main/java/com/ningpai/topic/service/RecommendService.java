/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.topic.service;

import com.ningpai.topic.bean.TopicRecommend;

/**
 * @Description: 点赞统计service
 * @author zhangyue
 * @date 2014年8月27日
 * @version V1.0
 */
public interface RecommendService {

    /**
     * 插入，空属性不会插入 参数:pojo对象 返回:添加个数
     * 
     * @date 2014-08-15 14:44:46
     */
    int insertSelective(TopicRecommend record);

    /**
     * 查询点赞数
     * 
     * @param customerIp
     * @param recommendShipId
     * @param recommendType
     * @return int
     */
    int selectViewCount(String customerIp, Long recommendShipId, String recommendType);

    /**
     * 检测是否赞过
     * 
     * @param customerIp
     * @param customerIp
     * @param recommendShipId
     * @return
     */
    boolean checkIsView(Long customerId, String customerIp, Long recommendShipId, String recommendType);

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
    boolean checkIsRecordByCustomerId(Long customerId, Long recommendShipId, String type);

    /**
     * 验证 通过用户id和时间当前 确定是否记录过
     * 
     * @param customerId
     * @param recommendShipId
     * @param type
     * @return boolean true 可以记录 false 已经记录过了
     */
    boolean checkIsRecordByCustomerIdAndDay(Long customerId, Long recommendShipId, String type);

    /**
     * 通过用户ip和当天 验证是否记录过
     * 
     * @param customerIp
     * @param customerId
     * @param recommendShipId
     * @return boolean true 可以记录 false 已经记录过了
     */
    boolean checkIsRecordByCustomerIPAndDay(String customerIp, Long customerId, Long recommendShipId, String type);
}
