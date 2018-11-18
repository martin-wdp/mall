package com.ningpai.thirdproject.mapper.impl;


/*
 * Copyright 2013 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.ningpai.manager.base.BasicSqlSupport;
import com.ningpai.thirdproject.bean.ThirdProject;
import com.ningpai.thirdproject.mapper.ThirdProjectMapper;
/**
 * 第三方专题管理mapper的实现类
 * @author zhangsl
 * @since 2015年1月15日 下午4:49:34
 * @version
 */
@Repository("ThirdProjectMapper")
public class ThirdProjectMapperImpl extends BasicSqlSupport implements ThirdProjectMapper {

    
    /*
     * 添加
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#insertSelective(com.ningpai.third.project.bean.ThirdProject)
     */
    @Override
    public int insertSelective(ThirdProject record) {
        
        return this.insert("com.ningpai.third.project.dao.ThirdProjectMapper.insertSelective", record);
    }
    
    /*
     * 根据Id查询
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#selectByPrimaryKey(java.lang.Long)
     */
    @Override
    public ThirdProject selectByPrimaryKey(Long thirdProjectId) {
        
        return this.selectOne("com.ningpai.third.project.dao.ThirdProjectMapper.selectByPrimaryKey", thirdProjectId);
    }
    
    /*
     * 更新
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#updateByPrimaryKeySelective(com.ningpai.third.project.bean.ThirdProject)
     */
    @Override
    public int updateByPrimaryKeySelective(ThirdProject record) {
        
        return this.update("com.ningpai.third.project.dao.ThirdProjectMapper.updateByPrimaryKeySelective", record);
    }
    
    
    /*
     * 分页查询专题信息
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#queryThirdProjectByPage(java.util.Map)
     */
    @Override
    public List<Object> queryThirdProjectByPage(Map<String, Object> paraMap) {
        
        return this.selectList("com.ningpai.third.project.dao.ThirdProjectMapper.queryThirdProjectByPage", paraMap);
    }

    /*
     * 查询专题信息的总条数
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#queryThirdProjectCount()
     */
    @Override
    public int queryThirdProjectCount(ThirdProject record) {
        
        return this.selectOne("com.ningpai.third.project.dao.ThirdProjectMapper.queryThirdProjectCount", record);
    }

    /*
     * 根据Id删除专题信息（逻辑删除）
     * @see com.ningpai.third.project.mapper.ThirdProjectMapper#updateDelflagstatu(java.lang.Long)
     */
    @Override
    public int updateDelflagstatu(Map<String ,Object> map) {
        
        return this.update("com.ningpai.third.project.dao.ThirdProjectMapper.updateDelflagstatu", map);
    }

    /*
     * 获取最后id
     * @see com.ningpai.thirdproject.mapper.ThirdProjectMapper#selectLastId()
     */
    @Override
    public Long selectLastId() {
        
        return this.selectOne("com.ningpai.third.project.dao.ThirdProjectMapper.selectLastId");
    }

}
