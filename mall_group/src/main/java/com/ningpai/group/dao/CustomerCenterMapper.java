package com.ningpai.group.dao;

import com.ningpai.group.bean.CommonCustomer;
import com.ningpai.group.bean.Visitors;

import java.util.List;
import java.util.Map;

/**
 * 粉丝Dao
 */
public interface CustomerCenterMapper {

    /**
     * 粉丝数量
     * 
     * @param customerId
     * @return Long
     */
    Long selectFansCount(Long customerId);

    /**
     * 关注数量
     * 
     * @param customerId
     * @return Long
     */
    Long selectGuanZhuCount(Long customerId);

    /**
     * 系统
     * 
     * @param customerId
     * @return Long
     */ 
    Long selectMesCount(Long customerId);

    /**
     * 回复数量
     * @param customerId
     * @return Long
     */
    Long selectReplyCount(Long customerId);

    /**
     * 系统数量
     * 
     * @param customerId
     * @return
     */
    Long selectSysCount(Long customerId);

    /**
     * 查询用户信息
     * 
     * @param customerId
     * @return CommonCustomer
     */
    CommonCustomer selectCommonCustomer(Long customerId);
    
    /**
     * 心情数目
     * 
     * @param customerId
     * @return
     */
    Long selectMoodCount(Long customerId);
    
    /**
     * 查询访客信息 
     * 
     * @param paramMap
     * @return
     */
    List<Visitors>selectVisitors(Map<String, Object> paramMap);

    /**
     * 插入访客
     * 
     * @param visitor 访客对象{@link com.ningpai.group.bean.Visitors}
     * @return int
     */
    int insertVisitor(Visitors visitor);
    
    /**
     * 查询访客
     * 
     * @param paramMap 查询参数
     * @return 对象
     */
    Visitors selectOneVisitors(Map<String, Object> paramMap);
}
