/**
 * Copyright 2014 NINGPAI, Inc.All rights reserved.
 * NINGPAI PROPRIETARY / CONFIDENTIAL.USE is subject to licence terms.
 */

package com.ningpai.app.dao.impl;

import com.ningpai.app.bean.AppServerBundle;
import com.ningpai.app.dao.AppServerBundleMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**  
 * @Description: np_app_server_bundle的dao的实现类:
 * @author Ningpai-Heh
 * @date 2015-08-18 13:14:42
 * @version V1.0  
 */
@Repository("AppServerBundleMapper") 
public class AppServerBundleMapperImpl extends com.ningpai.manager.base.BasicSqlSupport implements AppServerBundleMapper {

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#deleteByPrimaryKey(java.lang.Long)
    */
    @Override
    public int deleteByPrimaryKey(Long serverBundleId) {
        return this.update("com.ningpai.mybatis.mapper.AppServerBundleMapper.deleteByPrimaryKey", serverBundleId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#insertSelective(com.ningpai.app.bean.AppServerBundle)
    */
    @Override
    public int insertSelective(AppServerBundle record) {
        return this.insert("com.ningpai.mybatis.mapper.AppServerBundleMapper.insertSelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#selectByPrimaryKey(java.lang.Long)
    */
    @Override
    public AppServerBundle selectByPrimaryKey(Long serverBundleId) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppServerBundleMapper.selectByPrimaryKey", serverBundleId);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#updateByPrimaryKeySelective(com.ningpai.app.bean.AppServerBundle)
    */
    @Override
    public int updateByPrimaryKeySelective(AppServerBundle record) {
        return this.update("com.ningpai.mybatis.mapper.AppServerBundleMapper.updateByPrimaryKeySelective", record);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#deleteMuilti(java.lang.Long serverBundleIds)
    */
    @Override
    public int deleteMuilti(Long[] serverBundleIds) {
        return this.update("com.ningpai.mybatis.mapper.AppServerBundleMapper.deleteMuilti", serverBundleIds);
    }

    /**
     * 根据参数查询关联记录
     *
     * @param map 参数
     * @return 一条关联记录
     */
    @Override
    public AppServerBundle selectByParam(Map<String, Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppServerBundleMapper.selectByParam", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#selectList(java.util.Map<String,Object> map)
    */
    @Override
    public List<Object> selectList(Map<String,Object> map) {
        return this.selectList("com.ningpai.mybatis.mapper.AppServerBundleMapper.selectList", map);
    }

    /*
    * (non-Javadoc)
    * @see com.ningpai.app.dao.AppServerBundleMapper#selectListCount(java.util.Map<String,Object> map)
    */
    @Override
    public Integer selectListCount(Map<String,Object> map) {
        return this.selectOne("com.ningpai.mybatis.mapper.AppServerBundleMapper.selectListCount", map);
    }
}