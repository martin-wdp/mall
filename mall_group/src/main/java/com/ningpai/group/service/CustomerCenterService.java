package com.ningpai.group.service;

import java.util.List;
import java.util.Map;

import com.ningpai.group.bean.Visitors;

/**
 * 个人中心Service
 * @author ggn
 *
 */
public interface CustomerCenterService {
    
    /**
     * 查询个人中心信息
     * @param customerId
     * @return Map
     */
    Map<String,Object> selectCenterMessage(Long customerId);
    
    /**
     * 查询访客信息 
     * @param customerId 用户id{@link java.lang.Long}
     * @param number 显示数目
     * @return
     */
    List<Visitors>selectVisitors(Long customerId,int number);
    
    /**
     * 插入访客信息
     * @param memberId
     * @param customerId
     * @return
     */
    int insertVistitor(Long memberId,Long customerId,String customerIp);
    
    /**
     * 查询访客
     * @param memberId 访客Id {@link java.lang.Long}
     * @param customerId 用户Id{@link java.lang.Long}
     * @return
     */
    Visitors selectOneVisitors(Long memberId,Long customerId);
    
    
}
