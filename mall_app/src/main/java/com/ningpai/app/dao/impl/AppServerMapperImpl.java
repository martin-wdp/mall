/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.AppServer;
import com.ningpai.app.dao.AppServerMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_server的dao的实现类:
 * @author Ningpai-HEHU
 * @date 2015-08-06 09:24:42
 * @version V1.0  
 */
@Repository("AppServerMapper") 
public class AppServerMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppServerMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long appServerId) {
        return this.update("com.ningpai.mybatis.mapper.AppServerMapper.deleteByPrimaryKey", appServerId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#insertSelective(com.ningpai.app.bean.AppServer)
    */
    @Override
    public int insertSelective(AppServer record) {
        return this.insert("com.ningpai.mybatis.mapper.AppServerMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public AppServer selectByPrimaryKey(Long appServerId) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppServerMapper.selectByPrimaryKey", appServerId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#updateByPrimaryKeySelective(com.ningpai.app.bean.AppServer)
    */
    @Override
    public int updateByPrimaryKeySelective(AppServer record) {
        return this.update("com.ningpai.mybatis.mapper.AppServerMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#deleteMuilti(java.lang.Long appServerIds)
    */
    @Override
    public int deleteMuilti(Long[] appServerIds) {
        return this.update("com.ningpai.mybatis.mapper.AppServerMapper.deleteMuilti", appServerIds);
    }

    /**
     * 查询所有客户端网站地址
     *
     * @param map serverType 1Boss，2商家，3前台
     * @return 所有客户端网站地址
     */
    @Override
    public List<AppServer> selectByParam(Map<String, Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppServerMapper.selectByParam", map);
    }

    /**
     * 根据参数更改主机类型
     *
     * @param map 网站地址ID
     */
    @Override
    public void updateByParam(Map<String, Object> map) {
        this.update("com.ningpai.mybatis.mapper.AppServerMapper.updateByParam", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppServerMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppServerMapper.selectListCount", map);
    }
}