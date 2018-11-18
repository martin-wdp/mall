/**
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */
package com.ningpai.group.dao.impl;

import com.ningpai.group.bean.CommonCustomer;
import com.ningpai.group.bean.Visitors;
import com.ningpai.group.dao.CustomerCenterMapper;
import com.ningpai.manager.base.BasicSqlSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 粉丝Dao实现类
 *
 */
@Repository("CustomerCenterMapper")
public class CustomerCenterMapperImpl extends BasicSqlSupport implements CustomerCenterMapper {

    /**
     * 粉丝数量
     *
     * @param customerId
     * @return Long
     */
    public Long selectFansCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectFansCount",customerId);
    }
    /**
     * 关注数量
     *
     * @param customerId
     * @return Long
     */
    
    public Long selectGuanZhuCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectGuanZhuCount",customerId);
    }

    /**
     * 系统
     *
     * @param customerId
     * @return Long
     */
    public Long selectMesCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectMesCount",customerId);
    }

    /**
     * 回复数量
     * @param customerId
     * @return Long
     */
    public Long selectReplyCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectReplyCount",customerId);
    }

   /**
     * 系统数量
     *
     * @param customerId
     * @return
     */
    
    public Long selectSysCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectSysCount",customerId);
    }
    /**
     * 查询用户信息
     *
     * @param customerId
     * @return CommonCustomer
     */
    public CommonCustomer selectCommonCustomer(Long customerId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("customerId", customerId);
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectCommonCustomer",paramMap);
    }

    /**
     * 心情数目
     *
     * @param customerId
     * @return
     */
    public Long selectMoodCount(Long customerId) {
        
        return this.selectOne("com.ningpai.dao.CustomerCenterMapper.selectMoodCount",customerId);
    }

    /**
     * 查询访客信息
     *
     * @param paramMap
     * @return
     */
    public List<Visitors> selectVisitors(Map<String, Object> paramMap) {
        
        return this.selectList("com.ningpai.web.dao.VisitorsMapper.selectVisitors", paramMap);
    }

    /**
     * 插入访客
     *
     * @param visitor 访客对象{@link com.ningpai.group.bean.Visitors}
     * @return int
     */
    public int insertVisitor(Visitors visitor) {
        
        return this.insert("com.ningpai.web.dao.VisitorsMapper.insertVisitors", visitor);
        
    }

    /**
     * 查询访客
     *
     * @param paramMap 查询参数
     * @return 对象
     */
    public Visitors selectOneVisitors(Map<String, Object> paramMap) {
        
        return this.selectOne("com.ningpai.web.dao.VisitorsMapper.selectOneVisitors", paramMap);
    }

}
